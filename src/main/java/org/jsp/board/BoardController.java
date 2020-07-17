package org.jsp.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsp.board.service.BoardDAO;
import org.jsp.board.vo.SBoard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BoardController {

	@Inject
	BoardDAO dao;

	ArrayList<SBoard> sList = new ArrayList<SBoard>();

	// 문의 페이지로 이동
	@RequestMapping(value = "visit", method = RequestMethod.GET)
	public String visit(HttpSession session, Model model) {

		sList = (ArrayList) dao.selectAll();

		session.setAttribute("sList", sList);

		return "listBoard";
	}

	// boardnum에 따라 게시판의 글을 봄
	@RequestMapping(value = "readBoard", method = RequestMethod.GET)
	public String readBoard(HttpSession session, int boardnum, Model model) {

		sList = (ArrayList) dao.getBoard(boardnum);
		dao.addHits(boardnum);

		model.addAttribute("boardnum");
		session.setAttribute("sList", sList);

		return "readBoard";
	}

	// 작성가능한 폼이 있는 페이지로 이동
	@RequestMapping(value = "writeBoard", method = RequestMethod.POST)
	public String write() {

		return "recordForm";
	}

	// 게시판에 글을 작성하고 등록함
	@RequestMapping(value = "recordVisit", method = RequestMethod.POST)
	public String record(Model model, HttpSession session, SBoard s) {

		// 글의 정보를 리스트와 데이터베이스에 넣음
		sList = (ArrayList) dao.selectAll();

		session.getAttribute("sList");

		model.addAttribute("custid", session.getAttribute("loginid"));
		model.addAttribute("title");
		model.addAttribute("content");
		model.addAttribute("pw");
		sList.add(s);

		session.setAttribute("sList", sList);
		System.out.println(sList);
		dao.insertBoard(s);

		return "redirect:visit";
	}

	// 글을 삭제
	@RequestMapping(value = "deleteBoard", method = RequestMethod.POST)
	public String delete(Model model, SBoard s, HttpSession session, String pw) {

		sList = (ArrayList) dao.selectAll();

		int count = dao.checkPw(pw);

		if (count != 0) {
			sList.remove(s);
			dao.deleteBoard(pw);
		}

		return "redirect:visit";
	}

	// 글을 업데이트하는 페이지로 이동
	@RequestMapping(value = "goupdateform", method = RequestMethod.POST)
	public String goupdateBoard(SBoard sb, Model model) {

		return "updateBoardForm";
	}

	// 수정한 글의 정보를 업데이트
	@RequestMapping(value = "updateBoard", method = RequestMethod.POST)
	public String updateBoard(HttpSession session, Model model, SBoard sb, HttpServletResponse resp)
			throws IOException {

		dao.updateBoard(sb);

		return "redirect:visit";
	}

}
