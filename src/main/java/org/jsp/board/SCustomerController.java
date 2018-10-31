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

	// 会員加入のページに移動
	@RequestMapping("/gojoinform")
	public String gojoinform() {

		return "/jsp/scustomer/customerJoinForm";

	}

	// idチェックのためのウィンドウに移動
	@RequestMapping("/idcheckform")
	public String idcheckform() {

		return "/jsp/scustomer/idcheckform";

	}

	// idチェックのウィンドウで確認すると
	@RequestMapping("/idcheck")
	public String idcheck(Model model, String custid) {

		String idok = sdao.checkId(custid);

		if (idok != null) {
			// データベースでidがもうある場合は利用できない
			model.addAttribute("idok", false);
		} else {
			model.addAttribute("idok", true);
		}

		model.addAttribute("custid", custid);

		return "/jsp/scustomer/idcheckform";
	}

	// 会員の情報を持って来る
	@RequestMapping(value = "/joincustomer", method = RequestMethod.POST)
	public String joincustomer(SCustomer sc, Model model, String custid, String password, String name, String email,
			String division, String idno, String address) {

		sc = new SCustomer(custid, password, name, email, division, idno, address);
		System.out.println(sc);

		model.addAttribute("sc");

		// データベースに保存して
		sdao.joinCustomer(sc);

		// メインページに戻る
		return "home";
	}

	// ログインのページに移動
	@RequestMapping("/gologinform")
	public String gologinform() {

		return "/jsp/scustomer/logincustomer";

	}

	// ログインの情報を持って来る
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
			 * ログインが失敗した場合、 alertを作ってログインページに戻る
			 */
			out.println("<script language='javascript'>");
			out.println("alert('정보가 맞지않습니다. 다시 로그인 해주세요.');");
			out.println("</script>");
			out.flush();

			return "/jsp/scustomer/logincustomer";
		}

	}

	// ログアウトボタンをクリックすると
	@RequestMapping("/logout")
	public String logout(HttpSession session) {

		// セッションでloginidを削除
		session.removeAttribute("loginid");

		return "redirect:./";
	}

	// パスワードチェックのページに移動
	@RequestMapping("/checkpassword")
	public String checkpassword() {

		return "/jsp/scustomer/checkpassword";
	}

	// IDとパスワードをもらってログイン
	@RequestMapping("/confirmupdate")
	public String confirmupdate(SCustomer sc, HttpSession session) {
		sc = sdao.login(sc);

		// ログインが成功したらアトリビュート設定
		if (sc != null) {
			session.setAttribute("customer", sc);
		} else {
			session.removeAttribute("customer");
		}

		// ログインの結果をconfirmresultにリターン
		return "/jsp/scustomer/confirmresult";
	}

	// 個人情報修正のページに移動
	@RequestMapping("/goupdateform")
	public String goupdateform() {

		return "/jsp/scustomer/scustomerUpdateForm";
	}

	/*
	 * 修正された情報を持って来て データベースをアップデート
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(SCustomer sc, Model model) {

		model.addAttribute("customer");
		sdao.updateCustomer(sc);

		return "redirect:./";
	}

	// 脱退のためのパスワード確認ページに移動
	@RequestMapping("/deletemember")
	public String deletemember() {

		return "/jsp/scustomer/deletemember";
	}

	// IDとパスワードをもらって脱退
	@RequestMapping(value = "/confirmdelete", method = RequestMethod.POST)
	public String confirmdelete(String password, HttpSession session) {

		String custid = (String) session.getAttribute("loginid");
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("custid", custid);
		map.put("password", password);

		int result = sdao.deleteCustomer(map);

		// 脱退ができたらloginidのアトリビュートを削除
		if (result != 0) {
			session.removeAttribute("loginid");
		}

		return "/jsp/scustomer/deleteresult";

	}
}
