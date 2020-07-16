package org.jsp.board.service;

import java.util.HashMap;
import java.util.List;

import org.jsp.board.vo.Orderproduct;
import org.jsp.board.vo.Product;
import org.jsp.board.vo.Shoppinglist;

public interface ProductMapper {

	// 상품 재고정보 select
	public List<Product> getProductStockInfo(HashMap<String, String> map);

	// id별 장바구니에 넣은 것 select
	public List<Shoppinglist> getShoppingList(String custid);

	// 소파・의자・책상 상품정보 select
	public List<Product> getSCDInfo();

	// 베드・매트리스 상품정보 select
	public List<Product> getBInfo();

	// 조명 상품정보 select
	public List<Product> getLInfo();

	// 상품을 주문 insert
	public int insertOrderProduct(Orderproduct op);

	// 장바구니에 넣기 insert
	public int insertShoppinglist(Shoppinglist sl);

	// 장바구니에 넣었던 상품 삭제 delete
	public int deleteShoppinglist(String productserialnumber);

	// 주문하면 데이터베이스에서 주문한 수량만큼 감소함 update
	public int updateStock(HashMap<String, String> map);

}
