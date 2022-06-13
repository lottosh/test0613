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
	
	// ȸ������ ��
	@RequestMapping("MemInsert.do")
	public String meminsert() {
		return "meminsert";
	}
	
	// ȸ������ ó��
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
	
	// ���̵� �ߺ�Ȯ��
	@RequestMapping(value="isIdExsit.do", method=RequestMethod.POST)	
	@ResponseBody
	public HashMap<String, String> ajaxCheck(@RequestBody MemDTO dto) {
		HashMap<String, String> map = new HashMap<String, String>();
		
		String result="";
		if(memDao.isExsitId(dto.getId()) == 0) {
			result = "����� �� �ִ� ���̵� �Դϴ�.";
		}else if(memDao.isExsitId(dto.getId()) == 1) {
			result = "����� �� ���� ���̵� �Դϴ�.";
		}
		
		map.put("res",result);		
		return map;
	}
	
	// �α��� ��
	@RequestMapping("Login.do")
	public String login() {
		return "login";
	}
	
	// �α��� ó��
	@RequestMapping("LoginProc.do")
	public String loginProc(String id, String pw, HttpSession session) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);
		
		MemDTO memDto = memDao.loginInfo(map); // dto
		if(memDto != null) { // id, pw�� �´ٸ�
			session.setAttribute("userInfo", memDto);
		}
		
		return "home";
	}

	
	// �α׾ƿ� ó��
	@RequestMapping("Logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "home";
	}
	
	// ���ȸ������ ����Ʈ
	@RequestMapping("MemListAll.do")
	public String memberlistall(Model model) {
		model.addAttribute("memberListAll", memDao.memListAll());
		
		return "memlist";
	}
	

///////////////////////////////////////////////////////////////////	
	
	// �Խ��� ������
	@RequestMapping("Board.do")
	public String board(Model model) {
		model.addAttribute("boardList", boardDao.boardListAll());
		
		return "board";
	}
	
	// �Խ��� ������(����¡)
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
		model.addAttribute("pageCount", pageCount); // �� �������� , jsp���� for�� �̿���.
		
		return "board";
	}

	// �Խ��� ������(����¡+�˻�)
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
		System.out.println("�˻�����:"+searchCnt);
		
		Map<String, Object> map = new HashMap<>();
		map.put("title", title);
		map.put("numTmp", numTmp);
		map.put("pageSize", pageSize);
		
		
		mv.setViewName("board"); // ModelAndView�� �������� �����
		
		mv.addObject("cnt", searchCnt);
		mv.addObject("boardList", boardDao.boardSearchList(map));
		mv.addObject("pageCount",pageCount);// �� �������� , jsp���� for�� �̿���.
		
		return mv;
	}
	
	// �Խ��� �۾���
	@RequestMapping("BoardInsert.do")
	public String boardinsert() {
		return "boardinsert";
	}
	
	/*
	// �Խ��� �۾��� ó��
	@RequestMapping("BoardInsertProc.do")
	public String boardinsertProc(BoardDTO boardDto) {
		boardDao.boardinsertProc(boardDto);
		
		//return "redirect:/Board.do"; // �׳� �Խ���
		return "redirect:/BoardList.do"; // ����¡ �Խ���
	}
	*/
	
	// �Խ��� �۾��� ó��(���Ͼ��ε�)
	@Autowired
	ServletContext context;
	
	@RequestMapping("BoardInsertProc.do")
	public String boardinsertProc(MultipartHttpServletRequest mrequest) {
		
		MultipartFile file = mrequest.getFile("img"); // �Ѿ�� ���� ��ü
		
		String filename = file.getOriginalFilename(); // ��ü���� �̸� �̾Ƴ�
		
		// �Ѿ�� �� ��������
		String id = mrequest.getParameter("id");
		String name = mrequest.getParameter("name");
		String title = mrequest.getParameter("title");
		String content = mrequest.getParameter("content");
		
		// ���� ���� ��ġ - ���� ������ ���� ��ġ�� ����. ex) c:/user/main/...			
		String path = context.getRealPath("/resources/upload/");
		
		try {
			//���Ͼ��ε� �޼���
			//file.transferTo(new File("����� ��ġ�� ������ ���ϸ�"));
			file.transferTo(new File(path+filename));
			// c:/~~~/resources/upload/asdf.jpg
			
			System.out.println("���ε� �Ϸ�! : "+ path+filename);
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
		
		//return "redirect:/Board.do"; // �׳� �Խ���
		return "redirect:/BoardList.do"; // ����¡ �Խ���
	}
	
	// �Խñ� ��ȸ
	@RequestMapping("/BoardSelectView.do")
	public String boardSelectView(int idx, Model model) {
		System.out.println("�۹�ȣ: " + idx);
		
		List<BoardDTO> dto = boardDao.boardListOne(idx);
		model.addAttribute("boardDto", dto.get(0));
		
		return "boardread";
	}
	
	// �Խñ� ����
	@RequestMapping("/BoardDelete.do")
	public String boardDelete(int idx) {
		boardDao.boarddelete(idx);
		
		//return "redirect:/Board.do"; // �׳� �Խ���
		return "redirect:/BoardList.do"; // ����¡ �Խ���
	}
	
	// �Խñ� ���� ��
	@RequestMapping("BoardUpdate.do")
	public String updateBoard(int idx, Model model) {
		
		List<BoardDTO> dto = boardDao.boardListOne(idx);
		model.addAttribute("boardDto", dto.get(0));
		
		return "boardupdate";
	}
	
	// �Խ��� ���� ó��	
	@RequestMapping("BoardUpdateProc.do")
	public String updateBoardProc(MultipartHttpServletRequest mrequest) {
		MultipartFile file = mrequest.getFile("img"); // �Ѿ�� ���� ��ü
		
		String filename = file.getOriginalFilename(); // ��ü���� �̸� �̾Ƴ�
		
		// �Ѿ�� �� ��������
		int idx = Integer.parseInt(mrequest.getParameter("idx"));
		String title = mrequest.getParameter("title");
		String content = mrequest.getParameter("content");
		
		// ���� ���� ��ġ - ���� ������ ���� ��ġ�� ����. ex) c:/user/main/...			
		String path = context.getRealPath("/resources/upload/");
		
		try {
			//���Ͼ��ε� �޼���
			//file.transferTo(new File("����� ��ġ�� ������ ���ϸ�"));
			file.transferTo(new File(path+filename));
			// c:/~~~/resources/upload/asdf.jpg
			
			System.out.println("���ε� �Ϸ�! : "+ path+filename);
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
		
		//return "redirect:/Board.do"; // �׳� �Խ���
		return "redirect:/BoardList.do"; // ����¡ �Խ���
	}
	
	
	
	
}
