package com.drools.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drools.demo.model.Customer;
import com.drools.demo.service.GiftRecommendationServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/gift")
@AllArgsConstructor
public class GiftRecommendationController {
	
    private GiftRecommendationServiceImpl giftRecommendationService;

   /* public GiftRecommendationController(GiftRecommendationService giftService) {
        this.giftService = giftService;
    }*/

    @PostMapping("/welcome")
    public Customer recommendWelcomeGift(@RequestBody Customer customer) {
       return giftRecommendationService.getWelcomeGifts(customer);
    }
    
    @PostMapping("/goodbye")
    public Customer recommendGoodbyeGift(@RequestBody Customer customer) {
        return giftRecommendationService.getGoodbyeGifts(customer);
    }
    
}
