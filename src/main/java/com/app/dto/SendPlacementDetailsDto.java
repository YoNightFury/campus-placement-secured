package com.app.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SendPlacementDetailsDto {
	private String cid;
	private String  round;
	private String isSelected;
	private String CompanyName;

	// default construcotr
	public SendPlacementDetailsDto() {
		System.out.println("PlacementDetailsDto.PlacementDetailsDto()");
	}

}
