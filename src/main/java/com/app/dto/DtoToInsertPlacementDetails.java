package com.app.dto;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
/**
 * this pojo is used to store the  placement details of the student comming from the client
 * @author Ashish kumar yadav
 *
 */
public class DtoToInsertPlacementDetails {

	private String companyName;
	private String round;
	private String isSelected;
	public DtoToInsertPlacementDetails() {
		System.out.println("DtoToInsertPlacementDetails.DtoToInsertPlacementDetails()");
	}

}
