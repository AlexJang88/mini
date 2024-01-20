package test.spring.mvc.repository;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import test.spring.mvc.bean.BoardFileDTO;
import test.spring.mvc.bean.NoticeDTO;

public interface NoticeMapper {
	public int noticeWrite(NoticeDTO dto);
	public void isFileChange(@Param("noticeNum") int noticeNum,@Param("isFile") int isFile);
	public int getRecentNum();
	public int fileupload(BoardFileDTO dto);
	public List<NoticeDTO> noticeList(HashMap hashmap);
	public int count();
	public int getGrade(String id);
	public NoticeDTO getNoticeContent(int noticeNum);
	public int getReviewCount(int ref);
	public List<NoticeDTO> getReviews(HashMap hashmap);
	public void reviewWrite(NoticeDTO dto);
	public void reviewDelete(int noticenum);
	public void noticeDelete(int ref);
	public void fileDelete(int ref);
	public List<BoardFileDTO> getFileNames(int ref);
	public void readCountUp(int noticenum);
	public void noticeUpdate(NoticeDTO dto);
	public void deleteFileUnit(int num);
	public void isFileDown(int noticenum);
	public BoardFileDTO getFile(int num);
	public List<String> test(int boardnum);
	public int isFileCount(int boardnum);
}
