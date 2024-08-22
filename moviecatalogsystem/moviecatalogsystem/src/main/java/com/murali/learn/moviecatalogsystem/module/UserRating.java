package com.murali.learn.moviecatalogsystem.module;

import java.util.List;

public class UserRating {
private List<Rating> listOfMovieAndRating ;

public List<Rating> getListOfMovieAndRating() {
	return listOfMovieAndRating;
}

public void setListOfMovieAndRating(List<Rating> listOfMovieAndRating) {
	this.listOfMovieAndRating = listOfMovieAndRating;
}

public UserRating() {
	super();
}

public UserRating(List<Rating> listOfMovieAndRating) {
	super();
	this.listOfMovieAndRating = listOfMovieAndRating;
}

}
