package com.sh.app;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.sh.app.boardbean.BoardDTO;
import com.sh.app.boarddao.BoardDAO;
import com.sh.app.membean.MemDTO;
import com.sh.app.memdao.MemDAO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private MemDAO memDao;
	@Autowired
	private BoardDAO boardDao;
	
	
	@RequestMapping(value = "main.do")
	public String home() {
		return "home";
	}
	
	// 회원가입 폼
	@RequestMapping("MemInsert.do")
	public String meminsert() {
		return "meminsert";
	}
	
	// 회원가입 처리
	@RequestMapping("MemInsertProc.do")
	public String meminsertProc(MemDTO memDto) {
		Map<String, String> map = new HashMap<>();
		map.put("id", memDto.getId());
		map.put("pw", memDto.getPw());
		map.put("email", memDto.getEmail());
		map.put("name", memDto.getName());
		map.put("phone", memDto.getPhone());
		map.put("birth", memDto.getBirth());
		
		memDao.memInsert(map);
		
		return "home";
	}
	
	// 아이디 중복확인
	@RequestMapping(value="isIdExsit.do", method=RequestMethod.POST)	
	@ResponseBody
	public HashMap<String, String> ajaxCheck(@RequestBody MemDTO dto) {
		HashMap<String, String> map = new HashMap<String, String>();
		
		String result="";
		if(memDao.isExsitId(dto.getId()) == 0) {
			result = "사용할 수 있는 아이디 입니다.";
		}else if(memDao.isExsitId(dto.getId()) == 1) {
			result = "사용할 수 없는 아이디 입니다.";
		}
		
		map.put("res",result);		
		return map;
	}
	
	// 로그인 폼
	@RequestMapping("Login.do")
	public String login() {
		return "login";
	}
	
	// 로그인 처리
	@RequestMapping("LoginProc.do")
	public String loginProc(String id, String pw, HttpSession session) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);
		
		MemDTO memDto = memDao.loginInfo(map); // dto
		if(memDto != null) { // id, pw가 맞다면
			session.setAttribute("userInfo", memDto);
		}
		
		return "home";
	}

	
	// 로그아웃 처리
	@RequestMapping("Logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "home";
	}
	
	// 모든회원정보 리스트
	@RequestMapping("MemListAll.do")
	public String memberlistall(Model model) {
		model.addAttribute("memberListAll", memDao.memListAll());
		
		return "memlist";
	}
	

///////////////////////////////////////////////////////////////////	
	
	// 게시판 페이지
	@RequestMapping("Board.do")
	public String board(Model model) {
		model.addAttribute("boardList", boardDao.boardListAll());
		
		return "board";
	}
	
	// 게시판 페이지(페이징)
	@RequestMapping("BoardList.do")
	public String boardlist(@RequestParam(value="pageNum", required=false, defaultValue="1")String strNum, Model model) {
		
		int pageSize = 10;
		int totalBoardCnt = boardDao.boardAllCnt();
		//System.out.println(totalBoardCnt);
		int pageCount = totalBoardCnt/pageSize;
			if(totalBoardCnt % pageSize > 0) {
				pageCount++;
			}
		int numTmp = (Integer.parseInt(strNum)-1)*pageSize;
		
		Map<String, Integer> map = new HashMap<>();
		map.put("numTmp", numTmp);
		map.put("pageSize", pageSize);
		
		model.addAttribute("boardList", boardDao.boardListPaging(map));
		model.addAttribute("cnt", boardDao.boardAllCnt());
		model.addAttribute("pageCount", pageCount); // 총 페이지수 , jsp에서 for에 이용함.
		
		return "board";
	}

	// 게시판 페이지(페이징+검색)
	@RequestMapping("BoardSearchList.do")
	public ModelAndView boardsearchlist(@RequestParam(value="pageNum", required=false, defaultValue="1")String strNum, ModelAndView mv, String title) {
		int pageSize = 10;
		int totalBoardCnt = boardDao.boardAllCnt();
		int pageCount = totalBoardCnt/pageSize;
			if(totalBoardCnt % pageSize > 0) {
				pageCount++;
			}
		int numTmp = (Integer.parseInt(strNum)-1)*pageSize;
		
		int searchCnt = boardDao.boardSearchCnt(title);
		System.out.println("검색갯수:"+searchCnt);
		
		Map<String, Object> map = new HashMap<>();
		map.put("title", title);
		map.put("numTmp", numTmp);
		map.put("pageSize", pageSize);
		
		
		mv.setViewName("board"); // ModelAndView는 페이지도 써줘야
		
		mv.addObject("cnt", searchCnt);
		mv.addObject("boardList", boardDao.boardSearchList(map));
		mv.addObject("pageCount",pageCount);// 총 페이지수 , jsp에서 for에 이용함.
		
		return mv;
	}
	
	// 게시판 글쓰기
	@RequestMapping("BoardInsert.do")
	public String boardinsert() {
		return "boardinsert";
	}
	
	/*
	// 게시판 글쓰기 처리
	@RequestMapping("BoardInsertProc.do")
	public String boardinsertProc(BoardDTO boardDto) {
		boardDao.boardinsertProc(boardDto);
		
		//return "redirect:/Board.do"; // 그냥 게시판
		return "redirect:/BoardList.do"; // 페이징 게시판
	}
	*/
	
	// 게시판 글쓰기 처리(파일업로드)
	@Autowired
	ServletContext context;
	
	@RequestMapping("BoardInsertProc.do")
	public String boardinsertProc(MultipartHttpServletRequest mrequest) {
		
		MultipartFile file = mrequest.getFile("img"); // 넘어온 파일 객체
		
		String filename = file.getOriginalFilename(); // 객체에서 이름 뽑아냄
		
		// 넘어온 값 가져오기
		String id = mrequest.getParameter("id");
		String name = mrequest.getParameter("name");
		String title = mrequest.getParameter("title");
		String content = mrequest.getParameter("content");
		
		// 파일 저장 위치 - 실제 서버의 파일 위치를 쓴다. ex) c:/user/main/...			
		String path = context.getRealPath("/resources/upload/");
		
		try {
			//파일업로드 메서드
			//file.transferTo(new File("저장될 위치를 포함한 파일명"));
			file.transferTo(new File(path+filename));
			// c:/~~~/resources/upload/asdf.jpg
			
			System.out.println("업로드 완료! : "+ path+filename);
		}catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("name", name);
		map.put("title", title);
		map.put("content", content);
		map.put("filename", filename);
		
		boardDao.boardinsertProc(map);
		
		//return "redirect:/Board.do"; // 그냥 게시판
		return "redirect:/BoardList.do"; // 페이징 게시판
	}
	
	// 게시글 조회
	@RequestMapping("/BoardSelectView.do")
	public String boardSelectView(int idx, Model model) {
		System.out.println("글번호: " + idx);
		
		List<BoardDTO> dto = boardDao.boardListOne(idx);
		model.addAttribute("boardDto", dto.get(0));
		
		return "boardread";
	}
	
	// 게시글 삭제
	@RequestMapping("/BoardDelete.do")
	public String boardDelete(int idx) {
		boardDao.boarddelete(idx);
		
		//return "redirect:/Board.do"; // 그냥 게시판
		return "redirect:/BoardList.do"; // 페이징 게시판
	}
	
	// 게시글 수정 폼
	@RequestMapping("BoardUpdate.do")
	public String updateBoard(int idx, Model model) {
		
		List<BoardDTO> dto = boardDao.boardListOne(idx);
		model.addAttribute("boardDto", dto.get(0));
		
		return "boardupdate";
	}
	
	// 게시판 수정 처리	
	@RequestMapping("BoardUpdateProc.do")
	public String updateBoardProc(MultipartHttpServletRequest mrequest) {
		MultipartFile file = mrequest.getFile("img"); // 넘어온 파일 객체
		
		String filename = file.getOriginalFilename(); // 객체에서 이름 뽑아냄
		
		// 넘어온 값 가져오기
		int idx = Integer.parseInt(mrequest.getParameter("idx"));
		String title = mrequest.getParameter("title");
		String content = mrequest.getParameter("content");
		
		// 파일 저장 위치 - 실제 서버의 파일 위치를 쓴다. ex) c:/user/main/...			
		String path = context.getRealPath("/resources/upload/");
		
		try {
			//파일업로드 메서드
			//file.transferTo(new File("저장될 위치를 포함한 파일명"));
			file.transferTo(new File(path+filename));
			// c:/~~~/resources/upload/asdf.jpg
			
			System.out.println("업로드 완료! : "+ path+filename);
		}catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		Map<String, Object> map = new HashMap<>();
		map.put("idx", idx);
		map.put("title", title);
		map.put("content", content);
		map.put("filename", filename);
		
		boardDao.boardupdate(map);
		
		//return "redirect:/Board.do"; // 그냥 게시판
		return "redirect:/BoardList.do"; // 페이징 게시판
	}
	
	
	
	
}
