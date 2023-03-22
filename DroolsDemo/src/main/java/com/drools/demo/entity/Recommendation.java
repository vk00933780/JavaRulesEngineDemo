package com.drools.demo.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "T_CPM_Recommendation")
public class Recommendation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Recommendation_Id", nullable = false)
	private long recommendationId;
	
	@Column(name = "Recommendation_Type")
	private String recommendationType;
	
	@Column(name = "Customer_Id")
	private int customerId;
	
	@Column(name = "Customer_FirstName")
	private String customerFirstName;
	
	@Column(name = "Customer_LastName")
	private String customerLastName;
	
	@Column(name = "Customer_MiddleName")
	private String customerMiddleName;
	
	@Column(name = "Customer_Category")
	private String customerCategory;
	
	@Column(name = "Recommendation_DateTime")
	private LocalDateTime recommendationDateTime;
	
	@Column(name = "Asset_Id")
	private long assetId;
	
	@Column(name = "Asset_Name")
	private String assetName;
	
	@Column(name = "Asset_Category")
	private String assetCategory;
	
	@Column(name = "Created_On")
	private LocalDateTime createdOn;
	
	@Column(name = "Last_Updated_On")
	private LocalDateTime updatedOn;
	
}
