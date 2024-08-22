package com.murali.learn.movieratingsystem.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.murali.learn.movieratingsystem.module.Rating;
import com.murali.learn.movieratingsystem.module.UserRating;

@RestController
@RequestMapping("/rating")
public class RatingServices {

	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating("titanic", 8);
	}
	
	@RequestMapping("/userid/{userid}")
	public UserRating getUserRatings(@PathVariable("userid") String userid) {
		UserRating ur = new UserRating();
		List<Rating> lor = Arrays.asList(new Rating("movie1", 3),new Rating("movie2", 9), new Rating("movie3",7));
		ur.setListOfMovieAndRating(lor);
		return ur;
		
	}
}
