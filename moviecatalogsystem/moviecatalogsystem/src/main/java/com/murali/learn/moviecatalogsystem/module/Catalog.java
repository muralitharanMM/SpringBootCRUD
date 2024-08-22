package com.murali.learn.moviecatalogsystem.module;

public class Catalog {
private String moviename;
private int rating;
private String desc;
public Catalog(String moviename, int rating, String desc) {
	super();
	this.moviename = moviename;
	this.rating = rating;
	this.desc = desc;
}
public Catalog() {
	super();
}
public String getMoviename() {
	return moviename;
}
public void setMoviename(String moviename) {
	this.moviename = moviename;
}
public int getRating() {
	return rating;
}
public void setRating(int rating) {
	this.rating = rating;
}
public String getDesc() {
	return desc;
}
public void setDesc(String desc) {
	this.desc = desc;
}

}
