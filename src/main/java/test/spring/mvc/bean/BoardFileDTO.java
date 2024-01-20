package test.spring.mvc.bean;

import lombok.Data;

@Data
public class BoardFileDTO {
	private int num;
	private int boardNum;
	private String filename;
	private int category;
	
}
