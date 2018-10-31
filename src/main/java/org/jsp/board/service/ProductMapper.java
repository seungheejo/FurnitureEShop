package org.jsp.board.service;

import java.util.HashMap;
import java.util.List;

import org.jsp.board.vo.Orderproduct;
import org.jsp.board.vo.Product;
import org.jsp.board.vo.Shoppinglist;

public interface ProductMapper {

	// 商品の在庫情報 select
	public List<Product> getProductStockInfo(HashMap<String, String> map);

	// id別の買い物かごに入れたもの select
	public List<Shoppinglist> getShoppingList(String custid);

	// ソファ・椅子・机の商品情報 select
	public List<Product> getSCDInfo();

	// ベッド・マットレスの商品情報 select
	public List<Product> getBInfo();

	// 照明の商品情報 select
	public List<Product> getLInfo();

	// 商品を注文する insert
	public int insertOrderProduct(Orderproduct op);

	// 買い物かごに入れる insert
	public int insertShoppinglist(Shoppinglist sl);

	// 買い物かごから入れた商品を削除 delete
	public int deleteShoppinglist(String productserialnumber);

	// 注文したらデータベースから注文した数量が減少する update
	public int updateStock(HashMap<String, String> map);

}
