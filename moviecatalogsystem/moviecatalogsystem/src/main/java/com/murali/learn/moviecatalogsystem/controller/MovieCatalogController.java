package com.murali.learn.moviecatalogsystem.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.murali.learn.moviecatalogsystem.module.Catalog;
import com.murali.learn.moviecatalogsystem.module.CatalogItem;
import com.murali.learn.moviecatalogsystem.module.Movie;
import com.murali.learn.moviecatalogsystem.module.UserRating;


@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

//	@RequestMapping("/userid/{userid}")
//	public List<Catalog> getUserRating(@PathVariable("userid") String userid) {
//		RestTemplate restTemplate = new RestTemplate();		
//		
//		UserRating uor = restTemplate.getForObject("http://localhost:8081/rating/userid/"+userid, UserRating.class);
//		
//		return uor.getListOfMovieAndRating().stream().map(lor1->{
//			Movie m = restTemplate.getForObject("http://localhost:8082/movie/"+lor1.getMovieId(), Movie.class);
//			return new Catalog(m.getName(),lor1.getRating(),m.getDesc());
//		}).collect(Collectors.toList());				
//	}
	
	@RequestMapping("/userid/{userid}")
	public CatalogItem getUserRating(@PathVariable("userid") String userid) {
		CatalogItem catalogItems = new CatalogItem();
		RestTemplate restTemplate = new RestTemplate();		
		
		UserRating uor = restTemplate.getForObject("http://localhost:8081/rating/userid/"+userid, UserRating.class);
		
		List<Catalog> loc = uor.getListOfMovieAndRating().stream().map(lor1->{
			Movie m = restTemplate.getForObject("http://localhost:8082/movie/"+lor1.getMovieId(), Movie.class);
			return new Catalog(m.getName(),lor1.getRating(),m.getDesc());
		}).collect(Collectors.toList());
		
		catalogItems.setListOfCatalog(loc);
		return catalogItems;
	}
}
