package org.jsp.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsp.board.service.ProductDAO;
import org.jsp.board.vo.Orderproduct;
import org.jsp.board.vo.Product;
import org.jsp.board.vo.Shoppinglist;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductController {

	@Inject
	ProductDAO pdao;

	ArrayList<Product> scdList = new ArrayList<Product>();
	ArrayList<Product> bList = new ArrayList<Product>();
	ArrayList<Product> lList = new ArrayList<Product>();
	ArrayList<Product> pList = new ArrayList<Product>();

	ArrayList<Shoppinglist> slList = new ArrayList<Shoppinglist>();

	// 소파・의자・책상 메뉴
	@RequestMapping(value = "menu1", method = RequestMethod.GET)
	public String menu1(Model model) {

		/*
		 * 데이터베이스로부터 소파・의자・책상 상품정보를 가져와서 리스트에 넣음
		 */
		scdList = (ArrayList) pdao.getSCDInfo();
		model.addAttribute("scdList", scdList);

		return "menu1";
	}

	// 베드・매트리스 메뉴
	@RequestMapping(value = "menu2", method = RequestMethod.GET)
	public String menu2(Model model) {

		/*
		 * 데이터베이스로부터 베드・매트리스의 상품정보를 가져와서 리스트에 넣음
		 */
		bList = (ArrayList) pdao.getBInfo();
		model.addAttribute("bList", bList);

		return "menu2";
	}

	// 조명 메뉴
	@RequestMapping(value = "menu3", method = RequestMethod.GET)
	public String menu3(Model model) {

		/*
		 * 데이터베이스로부터 조명 상품정보를 가져와서 리스트에 넣음
		 */
		lList = (ArrayList) pdao.getLInfo();
		model.addAttribute("lList", lList);

		return "menu3";
	}

	// 상품재고 확인 페이지로 이동
	@RequestMapping(value = "checkcheck", method = RequestMethod.GET)
	public String stockcheck() {

		return "stockcheck";
	}

	// 상품재고 확인의 결과를 봄
	@RequestMapping(value = "gostockcheck", method = RequestMethod.GET)
	public String gostockcheck(Model model, String productserialnumber, String location) {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("location", location);
		map.put("productserialnumber", productserialnumber);

		pList = (ArrayList) pdao.getProductStockInfo(map);

		model.addAttribute("pList", pList);

		return "stockcheck";
	}

	// View details버튼을 클릭해서 상품정보를 봄
	@RequestMapping(value = "goviewdetail", method = RequestMethod.GET, produces = "application/text;charset=utf8")
	public String goviewdetail(String status, String category, String productserialnumber, int price, int stock,
			String location, String productname, Model model) {

		Product p = new Product(category, productserialnumber, price, stock, location, productname);

		model.addAttribute("p", p);
		model.addAttribute("status", status);
		model.addAttribute("category", category);

		return "menuviewdetail";
	}

	// 주문서 페이지로 이동
	@RequestMapping(value = "gobuy", method = RequestMethod.GET)
	public String gobuy(Model model, String productname, String productserialnumber, int amount, int price) {

		model.addAttribute("amount", amount);
		model.addAttribute("price", price);
		model.addAttribute("productname", productname);
		model.addAttribute("productserialnumber", productserialnumber);

		return "billingForm";
	}

	// 주문서 정보를 가져옴
	@RequestMapping(value = "buyproduct", method = RequestMethod.GET)
	public String buyproduct(Model model, Orderproduct op, String amount, String productserialnumber,
			HttpServletResponse resp) throws IOException {

		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();

		model.addAttribute("op", op);
		model.addAttribute("amount", amount);
		model.addAttribute("productserialnumber", productserialnumber);

		int result = pdao.insertOrderProduct(op);
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("amount", amount);
		map.put("productserialnumber", productserialnumber);

		// 주문한 수량만큼 데이터베이스의 상품재고를 감소시킴
		pdao.updateStock(map);

		// 주문에 성공하면 alert를 만들어 메인 페이지로 돌아감
		if (result != 0) {

			out.println("<script language='javascript'>");
			out.println("alert('주문되었습니다');");
			out.println("</script>");
			out.flush();

			return "home";
		}

		return "gobuy";
	}

	// 장바구니 페이지로 이동
	@RequestMapping(value = "goshoppingbasket", method = RequestMethod.GET)
	public String goshoppingbasket(HttpSession session, Model model) {

		String custid = (String) session.getAttribute("loginid");
		slList = (ArrayList) pdao.getShoppingList(custid);

		model.addAttribute("slList", slList);

		return "shoppingbasket";
	}

	// 장바구니에 넣음
	@RequestMapping(value = "putproduct", method = RequestMethod.GET)
	public String putproduct(HttpSession session, Shoppinglist sl, Model model) {

		// 데이터베이스와 장바구니 리스트에 정보를 추가
		String custid = (String) session.getAttribute("loginid");
		slList = (ArrayList) pdao.getShoppingList(custid);

		model.addAttribute("slList", slList);
		model.addAttribute("sl", sl);
		slList.add(sl);

		pdao.insertShoppinglist(sl);

		// 변경된 정보를 적용한 페이지를 보기위해
		return "redirect:goshoppingbasket";
	}

	// 장바구니에 있는 상품을 삭제
	@RequestMapping(value = "deleteshoppingbasket", method = RequestMethod.GET)
	public String deleteshoppingbasket(Shoppinglist sl, HttpSession session, String productserialnumber, Model model) {

		String custid = (String) session.getAttribute("loginid");
		slList = (ArrayList) pdao.getShoppingList(custid);

		int result = pdao.deleteShoppinglist(productserialnumber);
		model.addAttribute("sl", sl);

		if (result != 0) {
			slList.remove(sl);
		}

		model.addAttribute("slList", slList);

		return "redirect:goshoppingbasket";
	}
}
