package test.spring.mvc.service;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import test.spring.mvc.bean.BoardFileDTO;
import test.spring.mvc.bean.NoticeDTO;
import test.spring.mvc.repository.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	private NoticeMapper mapper;
	
	@Autowired
	private HashMap noticeMap;

	@Override
	public int noticeWrite(String path,NoticeDTO dto,List<MultipartFile> file,BoardFileDTO bdto) {
		if(dto.getIsFile()>0) {
			for(int i=0;i<file.size();i++) {
				String ofn = file.get(i).getOriginalFilename();
				String ext = ofn.substring(ofn.lastIndexOf("."));
				int rnum=mapper.getRecentNum();
				String fileRename = rnum+"_"+(i+1)+ext;
				bdto.setBoardNum(rnum);
				bdto.setFilename(fileRename);
				if(ofn!=null&&ofn!="") {
					File copy = new File(path+fileRename);
					try {
						file.get(i).transferTo(copy);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					mapper.fileupload(bdto);
				}
			}
		}
		return mapper.noticeWrite(dto);
	}
	
	
	

	@Override
	public void isFileChange(int noticeNum, int isFile) {
		
		mapper.isFileChange(noticeNum, isFile);
	}
	@Override
	public int getRecentNum() {
		
		return mapper.getRecentNum();
	}
	@Override
	public int fileupload(BoardFileDTO dto) {

		return mapper.fileupload(dto);
	}
	@Override
	public List<NoticeDTO> noticeList(int pageNum, Model model,String id) {
		int grade=mapper.getGrade(id);
		int pageSize = 10;
		int startRow = (pageNum - 1) * pageSize + 1;
		int endRow = pageNum * pageSize;
		int count = mapper.count();
		List<NoticeDTO> list = Collections.EMPTY_LIST;
		if (count > 0) {
			noticeMap.put("start", startRow);
			noticeMap.put("end", endRow);
			list = mapper.noticeList(noticeMap);
		}
		for(NoticeDTO dto : list) {
			mapper.isFileChange(dto.getNoticeNum(), mapper.isFileCount(dto.getNoticeNum()));
		}
		for(NoticeDTO dto : list) {
			if(dto.getIsFile()>0) {
				dto.setFilename(mapper.test(dto.getNoticeNum()).get(0));
			}else {
				dto.setFilename("default.jpg");
			}
		}
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("grade",grade );

		// page
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = (int) (pageNum / 10) * 10 + 1;
		int pageBlock = 10;
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("startPage", startPage);
		model.addAttribute("pageBlock", pageBlock);
		model.addAttribute("endPage", endPage);
		return null;
	}


	@Override
	public int getGrade(String id) {
		
		return mapper.getGrade(id);
	}

	@Override
	public NoticeDTO getNoticeContent(int noticeNum) {
		return mapper.getNoticeContent(noticeNum);
	}

	@Override
	public int getReviewCount(int ref) {
		return mapper.getReviewCount(ref);
	}

	@Override
	public List<NoticeDTO> getReviews(int ref,int rpageNum, Model model,String id,int pageNum) {
		int grade=mapper.getGrade(id);
		int rpageSize = 10;
		int rstartRow = (rpageNum - 1) * rpageSize + 1;
		int rendRow = rpageNum * rpageSize;
		int rcount = mapper.getReviewCount(ref);
		List<NoticeDTO> rlist = Collections.EMPTY_LIST;
		
		NoticeDTO dto = getNoticeContent(ref);
		
		List<BoardFileDTO> fileNames =mapper.getFileNames(ref);
		if (rcount > 0) {
			noticeMap.put("rstart", rstartRow);
			noticeMap.put("rend", rendRow);
			noticeMap.put("ref", ref);
			rlist = mapper.getReviews(noticeMap);
		}
		if(fileNames.size()>0) {
		model.addAttribute("fileNames", fileNames);
		}
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("grade",grade);
		model.addAttribute("dto", dto);
		model.addAttribute("reviews", rlist);
		model.addAttribute("rcount", rcount);
		model.addAttribute("rpageNum", rpageNum);
		model.addAttribute("rpageSize", rpageSize);

		// page
		int rpageCount = rcount / rpageSize + (rcount % rpageSize == 0 ? 0 : 1);
		int rstartPage = (int) (rpageNum / 10) * 10 + 1;
		int rpageBlock = 10;
		int rendPage = rstartPage + rpageBlock - 1;
		if (rendPage > rpageCount) {
			rendPage = rpageCount;
		}
		model.addAttribute("rpageCount", rpageCount);
		model.addAttribute("rstartPage", rstartPage);
		model.addAttribute("rpageBlock", rpageBlock);
		model.addAttribute("rendPage", rendPage);
		return null;
	}

	@Override
	public void reviewWrite(NoticeDTO dto) {
		
		
		mapper.reviewWrite(dto);
		
	}

	@Override
	public void reviewDelete(int noticenum) {

		mapper.reviewDelete(noticenum);
	}

	@Override
	public void noticeDelete(int ref,String path) {
		List<BoardFileDTO> fileNames= mapper.getFileNames(ref);
		if(fileNames.size()>0) {
			for(BoardFileDTO f : fileNames) {
				
				String fileName=path+f.getFilename();
				File file = new File(fileName);
				if(file.exists()) {
					file.delete();
				}
			}
		}
		mapper.fileDelete(ref);
		mapper.noticeDelete(ref);
	}

	@Override
	public void noticeUpdate(NoticeDTO dto,List<MultipartFile> files,String path) {
		BoardFileDTO bdto=new BoardFileDTO();
		System.out.println("=======================isFIle"+dto.getIsFile());
		if(dto.getIsFile()>0) {
				List<BoardFileDTO> fileNames=mapper.getFileNames(dto.getRef());
				if(fileNames.size()>0) {
					for(BoardFileDTO f : fileNames) {
						String fileName=f.getFilename();
						File file = new File(path+fileName);
						if(file.exists()) {
							file.delete();
						}
					}
					mapper.fileDelete(dto.getRef());
				}
				for(int i=0;i<files.size();i++) {
					String ofn = files.get(i).getOriginalFilename();
					String ext = ofn.substring(ofn.lastIndexOf("."));
					String fileRename = dto.getNoticeNum()+"_"+(i+1)+ext;
					bdto.setBoardNum(dto.getRef());
					bdto.setFilename(fileRename);
					if(ofn!=null&&ofn!="") {
						File copy = new File(path+fileRename);
						try {
							files.get(i).transferTo(copy);
						} catch (Exception e) {
							e.printStackTrace();
						} 
						mapper.fileupload(bdto);
					}
				}
			}
		mapper.noticeUpdate(dto);
	}




	@Override
	public void readCountUp(int noticenum) {
			mapper.readCountUp(noticenum);
	}




	@Override
	public void deleteFileUnit(int num,int ref,String path) {
			 mapper.isFileDown(ref);
			 BoardFileDTO dto=mapper.getFile(num);
			 File file = new File(path+dto.getFilename());
				if(file.exists()) {
					file.delete();
				}
		mapper.deleteFileUnit(num);
	}




	@Override
	public BoardFileDTO getFile(int num) {
		return mapper.getFile(num);
	}




	@Override
	public List<String> test(int boardnum) {
		return mapper.test(boardnum);
	}




	@Override
	public int isFileCount(int boardnum) {
		return mapper.isFileCount(boardnum);
	}





	
}
