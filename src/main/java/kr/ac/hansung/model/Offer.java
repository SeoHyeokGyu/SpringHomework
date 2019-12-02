package kr.ac.hansung.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Offer {
	
	
	private int year;
	private int semester;
	private String code;
	
	@Size(min=2, max=100, message="Name must be between 2 - 100 chars")
	private String name; 
	
	@NotEmpty(message="The  cannot be empty")
	private String division;
	
	private int point;
	
}
