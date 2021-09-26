package com.app.dto;

import lombok.Getter;
import lombok.Setter;


// dto to send the  custom successful message to the front end. 
@Getter
@Setter
public class SuccessMessageDto {

	private String message;
	public SuccessMessageDto(String message) {
          System.out.println("SuccessMessageDto.SuccessMessageDto()");
          this.message=message;
	}

}
