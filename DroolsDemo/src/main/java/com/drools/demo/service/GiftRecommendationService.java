package com.drools.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drools.demo.model.Customer;
import com.drools.demo.model.Gift;

@Service
public class GiftRecommendationService {
	
	@Autowired
	KieContainer kieContainer;

    public Customer getWelcomeGifts(Customer customer) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(customer);
        kieSession.fireAllRules();
        List<Gift> gifts = getGiftsFromWorkingMemory(kieSession);
       kieSession.dispose();
        
        customer.setGifts(gifts);
        return customer;
    }

    public Customer getGoodbyeGifts(Customer customer) {
        KieSession kieSession = kieContainer.newKieSession("GoodbyeGiftSession");
        kieSession.insert(customer);
        kieSession.fireAllRules();
        List<Gift> gifts = getGiftsFromWorkingMemory(kieSession);
        kieSession.dispose();
        
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
