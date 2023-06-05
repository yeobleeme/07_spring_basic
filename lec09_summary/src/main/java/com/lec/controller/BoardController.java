package com.lec.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.lec.domain.Board;
import com.lec.domain.Member;
import com.lec.domain.PagingInfo;
import com.lec.service.BoardService;

@Controller
@SessionAttributes({"member", "pagingInfo"})
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	Environment environment;
	
	public PagingInfo pagingInfo = new PagingInfo();
	
	@Value("${path.upload}")
	public String uploadFolder;
	
	@Value("${path.download}")
	public String downloadFolder;
	
	@ModelAttribute("member")
	public Member setMember() {
		return new Member();
	}
	
	@GetMapping("/getBoardList")
	public String getBoardList(Model model,
			@RequestParam(defaultValue = "0") int curPage,
			@RequestParam(defaultValue = "10") int rowSizePerPage,
			@RequestParam(defaultValue = "title") String searchType,
			@RequestParam(defaultValue = "") String searchWord) {
		
		Pageable pageable = PageRequest.of(curPage, rowSizePerPage, Sort.by(searchType).ascending());
		Page<Board> pagedResult = boardService.getBoardList(pageable, searchType, searchWord);
		
		int totalRowCount  = pagedResult.getNumberOfElements();
		int totalPageCount = pagedResult.getTotalPages();
		int pageSize       = pagingInfo.getPageSize();
		int startPage      = curPage / pageSize * pageSize + 1;
		int endPage        = startPage + pageSize + 1;
		endPage = endPage > totalPageCount ? (totalPageCount > 0 ? totalPageCount : 1) : endPage;

		pagingInfo.setCurPage(curPage);
		pagingInfo.setTotalRowCount(totalRowCount);
		pagingInfo.setTotalPageCount(totalPageCount);
		pagingInfo.setStartPage(startPage);
		pagingInfo.setEndPage(endPage);
		pagingInfo.setSearchType(searchType);
		pagingInfo.setSearchWord(searchWord);
		model.addAttribute("pagingInfo", pagingInfo);

		model.addAttribute("pagedResult", pagedResult);
		model.addAttribute("pageable", pageable);
		model.addAttribute("cp", curPage);
		model.addAttribute("sp", startPage);
		model.addAttribute("ep", endPage);
		model.addAttribute("ps", pageSize);
		model.addAttribute("rp", rowSizePerPage);
		model.addAttribute("tp", totalPageCount);
		model.addAttribute("st", searchType);
		model.addAttribute("sw", searchWord);
		
		return "board/getBoardList";		
	}
	
	@GetMapping("/insertBoard")
	public String insertBoardForm(@ModelAttribute("member") Member member) {
		
		if(member.getId() == null) {
			return "redirect:login";
		}			
		return "board/insertBoard";
	}

	@GetMapping("/getBoard")
	public String getBoard(@ModelAttribute("member") Member member, Board board, Model model) {
		
		if(member.getId() == null) {
			return "redirect:login";
		}	
		
		model.addAttribute("board", boardService.getBoard(board));
		return "board/getBoard";		
	}
	
	@PostMapping("/insertBoard")
	public String insertBoard(@ModelAttribute("member") Member member, Board board) 
			throws IOException {
		
		if(member.getId() == null) {
			return "redirect:login";
		}		
		
		// 파일업로드
		MultipartFile uploadFile = board.getUploadFile();
		if(!uploadFile.isEmpty()) {
			String fileName = uploadFile.getOriginalFilename();
			uploadFile.transferTo(new File(uploadFolder + fileName));
			board.setFileName(fileName);
		}
		boardService.insertBoard(board);
		return "board/getBoardList";
	}
	
	@PostMapping("/updateBoard")
	public String updateBoard(@ModelAttribute("member") Member member, Board board) {
		
		if(member.getId() == null) {
			return "redirect:login";
		}	
		boardService.updateBoard(board);
		return "forward:getBoardList";
	}
	
	@GetMapping("/deleteBoard")
	public String deleteBoard(@ModelAttribute("member") Member member, Board board) {
		
		if(member.getId() == null) {
			return "redirect:login";
		}	
		boardService.deleteBoard(board);
		return "forward:getBoardList";
	}
	
	@GetMapping("/download")
	public ResponseEntity<Resource> download(HttpServletRequest req, 
			@RequestParam int seq, @RequestParam String fn) {
		req.setCharacterEncoding("utf-8");
		String fileName = req.getParameter(fn);
		Path filePath = Paths.get(downloadFolder + fileName).toAbsolutePath();
		Resource resource = null;
		
		try {
			resource = new UrlResource(filePath.toUri());
			if(resource.exists()) {
				return ResponseEntity.ok()
						.contentType(MediaType.APPLICATION_OCTET_STREAM)
						.header(HttpHeaders.CONTENT_DISPOSITION, null)
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
}




















