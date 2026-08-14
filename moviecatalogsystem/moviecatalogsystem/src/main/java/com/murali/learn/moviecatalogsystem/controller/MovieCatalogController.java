package com.murali.learn.moviecatalogsystem.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.murali.learn.moviecatalogsystem.module.Catalog;
import com.murali.learn.moviecatalogsystem.module.CatalogItem;
import com.murali.learn.moviecatalogsystem.module.Movie;
import com.murali.learn.moviecatalogsystem.module.UserRating;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

//https://github.com/koushikkothagal/spring-boot-microservices-workshop


@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

	@Autowired
	private RestTemplate restTemplate;
	
	private final AtomicInteger requestCount = new AtomicInteger();
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
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
	
	public CatalogItem ratingFallback(String userid, Throwable t) {
		CatalogItem fallbackCatalogItem = new CatalogItem();
		fallbackCatalogItem.setListOfCatalog(Collections.singletonList(new Catalog("Service Unavailable ",0,"Rating Service is down")));
		return fallbackCatalogItem;
	}
	
	@RequestMapping("/userid/{userid}")
	@CircuitBreaker(name="ratingService", fallbackMethod="ratingFallback")
	public CatalogItem getUserRatingRestTemplate(@PathVariable("userid") String userid) {
		
		CatalogItem catalogItems = new CatalogItem();	
		int currentCount = requestCount.incrementAndGet();
		//System.out.println("Rquest number is "+currentCount);
		try {
		UserRating uor = restTemplate.getForObject("http://MOVIERATINGSYSTEM/rating/userid/"+userid, UserRating.class);
		Thread.sleep(1000);
		List<Catalog> loc = uor.getListOfMovieAndRating().stream().map(lor1->{
			Movie m = restTemplate.getForObject("http://MOVIEINFOSYSTEM/movie/"+lor1.getMovieId(), Movie.class);
			return new Catalog(m.getName(),lor1.getRating(),m.getDesc());
		}).collect(Collectors.toList());
		
		catalogItems.setListOfCatalog(loc);
		}catch (Exception e) {
			catalogItems.setListOfCatalog(Collections.singletonList(new Catalog("Error",0,"Some service is not available ")));
		}
		return catalogItems;
	}
	
	@RequestMapping("/useridwebclient/{userid}")
	public CatalogItem getUserRatingWebClient(@PathVariable("userid") String userid) {
		CatalogItem catalogItems = new CatalogItem();				
	
		WebClient webClient = webClientBuilder.build();
		UserRating uor = webClient.get()
								 .uri("http://MOVIERATINGSYSTEM/rating/userid/{userid}",userid)
								 .retrieve()
								 .bodyToMono(UserRating.class)
								 .block();
						
		List<Catalog> loc = uor.getListOfMovieAndRating().stream().map(lor ->{
			Movie m =webClient.get()
					.uri("http://MOVIEINFOSYSTEM/movie/"+lor.getMovieId())
					.retrieve()
					.bodyToMono(Movie.class)
					.block();
			return new Catalog(m.getName(),lor.getRating(),m.getDesc());
		}).collect(Collectors.toList());
		catalogItems.setListOfCatalog(loc);
		return catalogItems;
	}
}
