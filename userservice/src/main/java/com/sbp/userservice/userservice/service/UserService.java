package com.sbp.userservice.userservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sbp.userservice.userservice.entity.Hotel;
import com.sbp.userservice.userservice.entity.Rating;
import com.sbp.userservice.userservice.entity.User;
import com.sbp.userservice.userservice.exceptions.ResourceNotFoundException;
import com.sbp.userservice.userservice.repository.UserRepository;
import com.sbp.userservice.userservice.service.external.HotelService;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	public User saveUser(User user) {
		String randomId = UUID.randomUUID().toString();
		user.setUserId(randomId);
		return userRepository.save(user);
	}
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public User getUser(String userId) {
		User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with id "+ userId + " not found!"));
		ResponseEntity<List<Rating>> response = restTemplate.exchange(
	            "http://RATINGSERVICE/ratings/users/" + user.getUserId(),
	            HttpMethod.GET,
	            null,
	            new ParameterizedTypeReference<List<Rating>>() {}
	    );
	    List<Rating> ratings = response.getBody();
		List<Rating> newRatings = ratings.stream().map(rating->{
//			ResponseEntity<Hotel> hotel = restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);
//			rating.setHotel(hotel.getBody());
//			using feign client
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());
		user.setRatings(new ArrayList<>(newRatings));
		return user;
	}
	
}
