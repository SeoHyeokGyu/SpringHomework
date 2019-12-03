package kr.ac.hansung.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Lecture {
	
	private int year;
	
	@Range(min=1,max=2)
	private int semester;
	
	@NotEmpty(message="The code cannot be empty")
	private String code;
	
	@Size(min=2, max=100, message="Name must be between 2 - 100 chars")
	private String name; 
	
	@NotEmpty(message="The division cannot be empty")
	private String division;
	
	private int point;
}
