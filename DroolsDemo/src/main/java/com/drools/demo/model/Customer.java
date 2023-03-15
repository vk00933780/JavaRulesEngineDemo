package com.drools.demo.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	private int customerId;
	private String customerFirstName;
	private String customerLastName;
	private String customerMiddleName;
	private String preferredName;
	private String primaryContactNumber;
	private String secondaryContactNumner;
	private String address;
	private String emailId;
	private int age;
	private String dateOfBirth;
	private String gender;
	private String race;
	private String religion;
	private String maritalStatus;
	private String ethnicity;
	private String hobbies;
	private String category;
	private String personality;
	private List<String> preferences;
	private int primaryGuestId;
	private String primaryGuestRelationship;
	private String createdDate;
	private String updatedDated;
	private List<Gift> gifts;

}
