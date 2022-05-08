package aiss.api.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import aiss.model.Library;
import aiss.model.Film;
import aiss.model.repository.MapLibraryRepository;
import aiss.model.repository.LibraryRepository;

import java.net.URI;
import java.util.Collection;



@Path("/songs")
public class FilmResource {

	public static FilmResource _instance=null;
	LibraryRepository repository;
	
	private FilmResource(){
		repository=MapLibraryRepository.getInstance();
	}
	
	public static FilmResource getInstance()
	{
		if(_instance==null)
			_instance=new FilmResource();
		return _instance; 
	}
	
	@GET
	@Produces("application/json")
	public Collection<Film> getAll()
	{
		return repository.getAllSongs();
	}
	
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Film get(@PathParam("id") String songId)
	{
		Film song=repository.getSong(songId);
		
		if (song == null) {
			throw new NotFoundException("The song with id="+ songId +" was not found");			
		}
		
		return song;
	
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addSong(@Context UriInfo uriInfo, Film film) {
		if (film.getTitle() == null || "".equals(film.getTitle()))
			throw new BadRequestException("The name of the playlist must not be null");
		
		repository.addSong(film);

		// Builds the response. Returns the playlist the has just been added.
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(film.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(film);			
		return resp.build();
	}
	
	
	@PUT
	@Consumes("application/json")
	public Response updateSong(Film film) {
		Film oldsong = repository.getSong(film.getId());
		if (oldsong == null) {
			throw new NotFoundException("The song with id="+ film.getId() +" was not found");			
		}
		
		if (film.getAlbum()!=null)
			oldsong.setAlbum(film.getAlbum());
		
		if (film.getArtist()!=null)
			oldsong.setArtist(film.getArtist());
		
		if (film.getTitle()!=null)
			oldsong.setTitle(film.getTitle());
		
		if (film.getYear()!=null)
			oldsong.setYear(film.getYear());

		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response removeSong(@PathParam("id") String songId) {
		Film toberemoved=repository.getSong(songId);
		if (toberemoved == null)
			throw new NotFoundException("The playlist with id="+ songId +" was not found");
		else
			repository.deleteSong(songId);
		
		return Response.noContent().build();
	}
	
}