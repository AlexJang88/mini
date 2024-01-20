package test.spring.mvc.service;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import test.spring.mvc.bean.BoardFileDTO;
import test.spring.mvc.bean.NoticeDTO;

public interface NoticeService {
	
	public int noticeWrite(String path,NoticeDTO dto,List<MultipartFile> file,BoardFileDTO bdto); 
	public void isFileChange(int noticeNum,int isFile);
	public int getRecentNum();
	public int fileupload(BoardFileDTO dto);
	public List<NoticeDTO> noticeList(int pageNum,Model model,String id);
	public int getGrade(String id);
	public NoticeDTO getNoticeContent(int noticeNum);
	public int getReviewCount(int ref);
	public List<NoticeDTO> getReviews(int ref,int rpageNum, Model model,String id,int pageNum);
	public void reviewWrite(NoticeDTO dto);
	public void reviewDelete(int noticenum);
	public void noticeDelete(int ref,String path);
	public void noticeUpdate(NoticeDTO dto,List<MultipartFile> file,String path);
	public void readCountUp(int noticenum);
	public void deleteFileUnit(int num,int ref,String path);
	public BoardFileDTO getFile(int num);
	public List<String> test(int boardnum);
	public int isFileCount(int boardnum);
	
}
