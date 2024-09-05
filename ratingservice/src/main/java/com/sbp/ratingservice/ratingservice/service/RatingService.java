package com.sbp.ratingservice.ratingservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbp.ratingservice.ratingservice.entity.Rating;
import com.sbp.ratingservice.ratingservice.repository.RatingRepository;

@Service
public class RatingService {

	@Autowired
	private RatingRepository ratingRepository;

	public Rating create(Rating rating) {
		return ratingRepository.save(rating);
	}

	public List<Rating> getAllRatings() {
		return ratingRepository.findAll();
	}

	public List<Rating> getRatingByUserId(String userId) {
		return ratingRepository.findByUserId(userId);
	}

	public List<Rating> getRatingByHotelId(String hotelId) {
		return ratingRepository.findByHotelId(hotelId);
	}

}
