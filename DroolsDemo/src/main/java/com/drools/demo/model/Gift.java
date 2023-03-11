package com.drools.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gift {
	
	private int giftId;
	private String giftName;
	private String description;
}
