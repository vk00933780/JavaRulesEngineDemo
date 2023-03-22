package com.drools.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drools.demo.entity.Recommendation;
import com.drools.demo.model.Customer;
import com.drools.demo.model.Gift;
import com.drools.demo.repository.RecommendationRepository;

@Service
public class GiftRecommendationServiceImpl {

	@Autowired
	private KieContainer kieContainer;
	
	@Autowired
	private RecommendationRepository recommendationRepository;

	public Customer getWelcomeGifts(Customer customer) {
		KieSession welcomeGiftSession  = kieContainer.newKieSession("welcome-rules");
		welcomeGiftSession.insert(customer);
		welcomeGiftSession.fireAllRules();
		List<Gift> gifts = getGiftsFromWorkingMemory(welcomeGiftSession);
		welcomeGiftSession.dispose();
		
		customer.setGifts(gifts);
		
		List<Recommendation> recommendations = getRecommendation(gifts, customer);
		recommendationRepository.saveAll(recommendations);
		return customer;
	}

	public Customer getGoodbyeGifts(Customer customer) {
		KieSession goodbyeGiftSession  = kieContainer.newKieSession("goodbye-rules");
		goodbyeGiftSession.insert(customer);
		goodbyeGiftSession.fireAllRules();
		List<Gift> gifts = getGiftsFromWorkingMemory(goodbyeGiftSession);
		goodbyeGiftSession.dispose();

		customer.setGifts(gifts);
		
		List<Recommendation> recommendations = getRecommendation(gifts, customer);
		recommendationRepository.saveAll(recommendations);
		
		return customer;
	}

	private List<Gift> getGiftsFromWorkingMemory(KieSession kieSession) {
		List<Gift> gifts = new ArrayList<>();
		kieSession.getObjects(obj -> obj instanceof Gift)
		.forEach(obj -> gifts.add((Gift) obj));
		return gifts;
	}

	private List<Recommendation> getRecommendation(List<Gift> gifts, Customer customer) {
		
		List<Recommendation> recommendations = new ArrayList<>();
		
		for(Gift gift : gifts)
		{
			Recommendation recommendation = new Recommendation();
			
			recommendation.setAssetCategory("Gift");
			recommendation.setAssetId(gift.getGiftId());
			recommendation.setAssetName(gift.getGiftName());
			recommendation.setCreatedOn(LocalDateTime.now());
			recommendation.setUpdatedOn(LocalDateTime.now());
			recommendation.setCustomerCategory(customer.getCategory());
			recommendation.setCustomerFirstName(customer.getCustomerFirstName());
			recommendation.setCustomerMiddleName(customer.getCustomerMiddleName());
			recommendation.setCustomerLastName(customer.getCustomerLastName());
			recommendation.setCustomerId(customer.getCustomerId());
			recommendation.setRecommendationDateTime(LocalDateTime.now());
			recommendation.setRecommendationType("Personalized");
			//recommendation.setRecommendationId(0);
			
			recommendations.add(recommendation);
		}
		
		return recommendations;
	}
}
