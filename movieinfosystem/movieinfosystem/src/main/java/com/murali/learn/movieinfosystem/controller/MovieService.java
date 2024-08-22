package com.murali.learn.movieinfosystem.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.murali.learn.movieinfosystem.model.Movie;

@RestController
@RequestMapping("/movie")
public class MovieService {

	@RequestMapping("/{movieid}")
	public Movie getMovie(@PathVariable("movieid") String movieId) {
		return new Movie("tit", "titanic", "good");
	}
}
