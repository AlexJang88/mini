package test.spring.mvc;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import test.spring.mvc.bean.BoardFileDTO;
import test.spring.mvc.bean.NoticeDTO;
import test.spring.mvc.service.NoticeService;

@Controller
@RequestMapping("/notice/*")
public class NoticeController {

	@Autowired
	private NoticeService noticeServiceImpl;
	
	@RequestMapping("noticeList")
	public String noticeList(HttpSession session,Model model,@RequestParam(value = "pageNum", defaultValue = "1")int pageNum) {
		session.setAttribute("memId", "admin");
		String id = (String)session.getAttribute("memId");
		
		noticeServiceImpl.noticeList(pageNum, model,id);
		return "notice/noticeList";
	}
	
	@RequestMapping("noticeWriteForm")
	public String noticeWriteForm() {
			
		return "notice/noticeWriteForm";
	}
	@RequestMapping("noticeWritePro")
	public String noticeWritePro(HttpServletRequest request,NoticeDTO dto,HttpSession session,List<MultipartFile> file,BoardFileDTO bdto) {
		dto.setWriter((String)session.getAttribute("memId"));
		String path = request.getServletContext().getRealPath("/resources/file/notice/");
		int isFile =0;
		for(int i=0;i<file.size();i++) {
			if(file.get(i).getOriginalFilename()!="" && file.get(i).getOriginalFilename()!=null) {
				isFile++;
			}
		}
		dto.setIsFile(isFile);
		noticeServiceImpl.noticeWrite(path,dto,file,bdto);
		return "redirect:/notice/noticeList";
	}
	
	@RequestMapping("noticeContent")
	public String noticeContent(RedirectAttributes rtts,int noticeNum,int pageNum,@RequestParam(value="rpageNum" ,defaultValue="1") int rpageNum) {
			rtts.addAttribute("noticeNum",noticeNum);
			rtts.addAttribute("rpageNum",rpageNum);
			rtts.addAttribute("pageNum",pageNum);
			rtts.addFlashAttribute("read","true");
			
			
		return "redirect:/notice/noticeContentPro";
	}
	
	@RequestMapping("noticeContentPro")
	public String noticeContentPro(@ModelAttribute("read") String read,
			@RequestParam("noticeNum")int noticeNum,
			@RequestParam(value="rpageNum" ,defaultValue="1")int rpageNum,
			Model model,
			HttpSession session,
			@RequestParam("pageNum")int pageNum) {
		if(read.equals("true")) {
			noticeServiceImpl.readCountUp(noticeNum);
		}
		String id = (String)session.getAttribute("memId");
		noticeServiceImpl.getReviews(noticeNum, rpageNum, model,id,pageNum);
		return "notice/noticeContent";
	}
	
	@RequestMapping("noticeReviewPro")
	public String noticeReviewPro(NoticeDTO dto,int pageNum) {
			noticeServiceImpl.reviewWrite(dto);
		return "redirect:/notice/noticeContentPro?noticeNum="+dto.getRef()+"&ref="+dto.getRef()+"&rpageNum=1&pageNum="+pageNum;
	}
	
	@RequestMapping("deleteReview")
	public String deleteReview(int noticeNum,int ref,int pageNum,@RequestParam(value="rpageNum" ,defaultValue="1") int rpageNum) {
		 noticeServiceImpl.reviewDelete(noticeNum);
		return "redirect:/notice/noticeContentPro?noticeNum="+ref+"&ref="+ref+"&rpageNum="+rpageNum+"&pageNum="+pageNum;
	}
	@RequestMapping("deleteNotice")
	public String deleteNotice(HttpServletRequest request,int ref) {
		String path = request.getServletContext().getRealPath("/resources/file/notice/");
		noticeServiceImpl.noticeDelete(ref,path);

		return "redirect:/notice/noticeList";
	}
	@RequestMapping("noticeUpdateForm")
	public String noticeUpdateForm(int noticeNum,Model model) {
			NoticeDTO dto=noticeServiceImpl.getNoticeContent(noticeNum);
			model.addAttribute("dto", dto);
		return "/notice/noticeUpdateForm";
	}
	@RequestMapping("noticeUpdatePro")
	public String noticeUpdatePro(NoticeDTO dto,List<MultipartFile> file,HttpServletRequest request) {
		String path = request.getServletContext().getRealPath("/resources/file/notice/");
		int isFile =0;
		for(int i=0;i<file.size();i++) {
			if(file.get(i).getOriginalFilename()!="" && file.get(i).getOriginalFilename()!=null) {
				isFile++;
			}
		}
		dto.setIsFile(isFile);
		noticeServiceImpl.noticeUpdate(dto, file, path);
		return "redirect:/notice/noticeList";
	}
	@RequestMapping("deleteFileUnit")
	public String deleteFileUnit(int ref,int pageNum,int filenum,HttpServletRequest request) {
		String path = request.getServletContext().getRealPath("/resources/file/notice/");	
		noticeServiceImpl.deleteFileUnit(filenum,ref,path);
			
		return "redirect:/notice/noticeContentPro?noticeNum="+ref+"&ref="+ref+"&pageNum="+pageNum;
	}
	
	
	
	
	
	
}
