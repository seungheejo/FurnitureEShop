package org.jsp.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsp.board.service.SCustomerDAO;
import org.jsp.board.vo.SCustomer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SCustomerController {

	@Inject
	SCustomerDAO sdao;

	// 회원가입 페이지로 이동
	@RequestMapping("/gojoinform")
	public String gojoinform() {

		return "/jsp/scustomer/customerJoinForm";

	}

	// id체크하기위한 윈도우 창으로 이동
	@RequestMapping("/idcheckform")
	public String idcheckform() {

		return "/jsp/scustomer/idcheckform";

	}

	// id체크용 윈도우에서 확인하면
	@RequestMapping("/idcheck")
	public String idcheck(Model model, String custid) {

		String idok = sdao.checkId(custid);

		if (idok != null) {
			// 데이터베이스에 id가 이미 있는 경우는 이용할 수 없음
			model.addAttribute("idok", false);
		} else {
			model.addAttribute("idok", true);
		}

		model.addAttribute("custid", custid);

		return "/jsp/scustomer/idcheckform";
	}

	// 회원정보를 가져옴
	@RequestMapping(value = "/joincustomer", method = RequestMethod.POST)
	public String joincustomer(SCustomer sc, Model model, String custid, String password, String name, String email,
			String division, String idno, String address) {

		sc = new SCustomer(custid, password, name, email, division, idno, address);
		System.out.println(sc);

		model.addAttribute("sc");

		// 데이터베이스에 저장
		sdao.joinCustomer(sc);

		// 메인 페이지로 돌아감
		return "home";
	}

	// 로그인 페이지로 이동
	@RequestMapping("/gologinform")
	public String gologinform() {

		return "/jsp/scustomer/logincustomer";

	}

	// 로그인 정보를 가져옴
	@RequestMapping("/login")
	public String login(HttpSession session, SCustomer sc, HttpServletResponse resp) throws IOException {

		sc = sdao.login(sc);

		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();

		if (sc != null) {

			session.setAttribute("loginid", sc.getCustid());

			return "redirect:./";

		} else {

			/*
			 * 로그인에 실패했을 경우, alert를 만들어 로그인 페이지로 돌아감
			 */
			out.println("<script language='javascript'>");
			out.println("alert('정보가 맞지않습니다. 다시 로그인 해주세요.');");
			out.println("</script>");
			out.flush();

			return "/jsp/scustomer/logincustomer";
		}

	}

	// 로그아웃 버튼을 클릭하면
	@RequestMapping("/logout")
	public String logout(HttpSession session) {

		// 세션에서 loginid를 삭제
		session.removeAttribute("loginid");

		return "redirect:./";
	}

	// 비밀번호 체크 페이지로 이동
	@RequestMapping("/checkpassword")
	public String checkpassword() {

		return "/jsp/scustomer/checkpassword";
	}

	// ID, 비밀번호를 받아서 로그인
	@RequestMapping("/confirmupdate")
	public String confirmupdate(SCustomer sc, HttpSession session) {
		sc = sdao.login(sc);

		// 로그인 성공하면 어트리뷰트 설정
		if (sc != null) {
			session.setAttribute("customer", sc);
		} else {
			session.removeAttribute("customer");
		}

		// 로그인 결과를 confirmresult에 리턴
		return "/jsp/scustomer/confirmresult";
	}

	// 개인정보수정 페이지로 이동
	@RequestMapping("/goupdateform")
	public String goupdateform() {

		return "/jsp/scustomer/scustomerUpdateForm";
	}

	/*
	 * 수정된 정보를 가져와서 데이터베이스를 업데이트
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(SCustomer sc, Model model) {

		model.addAttribute("customer");
		sdao.updateCustomer(sc);

		return "redirect:./";
	}

	// 탈퇴하기 위한 비밀번호 확인 페이지로 이동
	@RequestMapping("/deletemember")
	public String deletemember() {

		return "/jsp/scustomer/deletemember";
	}

	// ID, 비밀번호를 받아서 탈퇴
	@RequestMapping(value = "/confirmdelete", method = RequestMethod.POST)
	public String confirmdelete(String password, HttpSession session) {

		String custid = (String) session.getAttribute("loginid");
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("custid", custid);
		map.put("password", password);

		int result = sdao.deleteCustomer(map);

		// 탈퇴가 되면 loginid의 어트리뷰트를 삭제
		if (result != 0) {
			session.removeAttribute("loginid");
		}

		return "/jsp/scustomer/deleteresult";

	}
}
