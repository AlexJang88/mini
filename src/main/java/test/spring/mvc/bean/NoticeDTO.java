package test.spring.mvc.bean;

import java.util.Date;

import lombok.Data;

@Data
public class NoticeDTO {
	private int noticeNum;
	private String writer;
	private String noticeTitle;
	private String noticeContent;
	private int readCount;
	private Date reg_date;
	private int ref;
	private int re_step;
	private int isFile;
	private int r;
	private String filename;
	
}
