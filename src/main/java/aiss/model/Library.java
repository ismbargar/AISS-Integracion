package aiss.model;

import java.util.ArrayList;
import java.util.List;

public class Library {

	private String id;
	private String username;
	private List<String> usersLike;
	private String name;
	private String description;
	private Boolean hidden;
	private Integer likes;
	private List<Film> films;
	
	public Library() {
		
	}
	
	public Library(String name) {
		this.name = name;
		this.usersLike=new ArrayList<>();
		this.films=new ArrayList<>();
	}
	
	public void setFilms(List<Film> s) {
		this.films = s;
	}
	
	public Integer getLikes() {
		return this.likes;
	}
	
	public void setLikes(Integer n) {
		likes = n;
	}
	
	public void setLikesToZero() {
		likes = 0;
		usersLike=new ArrayList<>();
	}
	
	public void addRemoveLikes(String user) {
		if(usersLike.contains(user)) {
			usersLike.remove(usersLike.indexOf(user));
			likes--;
		}else {
			usersLike.add(user);
			likes++;
		}
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String name) {
		username = name;
	}
	
	public List<String> getUsersLike() {
		return this.usersLike;
	}
	
	public void addUserLike(String username) {
		if(usersLike == null) {
			usersLike = new ArrayList<String>();
		}
		if(!usersLike.contains(username)) {
			usersLike.add(username);
		}
	}
	
	public void deleteUserLike(String username) {
		usersLike.remove(username);
	}

	public void addLike(Integer likes) {
		this.likes = likes;
	}
	
	public Boolean getHidden() {
		return this.hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Film> getFilms() {
		return films;
	}
	
	public Film getFilm(String id) {
		if (films==null)
			return null;
		
		Film film =null;
		for(Film f: films)
			if (f.getId().equals(id))
			{
				film=f;
				break;
			}
		
		return film;
	}
	
	public void addFilm(Film f) {
		if (films==null)
			films = new ArrayList<Film>();
		films.add(f);
	}
	
	public void deleteFilm(Film f) {
		films.remove(f);
	}
	
	public void deleteFilm(String id) {
		Film f = getFilm(id);
		if (f!=null)
			films.remove(f);
	}
	
}
