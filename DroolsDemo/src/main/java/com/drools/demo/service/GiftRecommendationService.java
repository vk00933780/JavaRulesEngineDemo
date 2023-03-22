package com.drools.demo.service;

import com.drools.demo.model.Customer;

public interface GiftRecommendationService {
	
	Customer getWelcomeGifts(Customer customer);
	Customer getGoodbyeGifts(Customer customer);
}
