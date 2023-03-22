package com.drools.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drools.demo.entity.Recommendation;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {

}
