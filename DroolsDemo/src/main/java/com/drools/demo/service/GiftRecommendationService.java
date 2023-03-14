package com.drools.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drools.demo.model.Customer;
import com.drools.demo.model.Gift;

@Service
public class GiftRecommendationService {

	@Autowired
	KieSession welcomeGiftSession;

	@Autowired
	KieSession goodbyeGiftSession;

	public Customer getWelcomeGifts(Customer customer) {
		welcomeGiftSession.insert(customer);
		welcomeGiftSession.fireAllRules();
		List<Gift> gifts = getGiftsFromWorkingMemory(welcomeGiftSession);
		welcomeGiftSession.dispose();

		customer.setGifts(gifts);
		return customer;
	}

	public Customer getGoodbyeGifts(Customer customer) {
		goodbyeGiftSession.insert(customer);
		goodbyeGiftSession.fireAllRules();
		List<Gift> gifts = getGiftsFromWorkingMemory(goodbyeGiftSession);
		goodbyeGiftSession.dispose();

		customer.setGifts(gifts);
		return customer;
	}

	private List<Gift> getGiftsFromWorkingMemory(KieSession kieSession) {
		List<Gift> gifts = new ArrayList<>();
		kieSession.getObjects(obj -> obj instanceof Gift)
		.forEach(obj -> gifts.add((Gift) obj));
		return gifts;
	}

}
