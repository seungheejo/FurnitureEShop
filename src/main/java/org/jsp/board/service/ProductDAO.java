package org.jsp.board.service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.jsp.board.vo.Orderproduct;
import org.jsp.board.vo.Product;
import org.jsp.board.vo.Shoppinglist;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAO implements ProductMapper {

	@Inject
	SqlSession session;

	@Override
	public List<Product> getProductStockInfo(HashMap<String, String> map) {

		return session.getMapper(ProductMapper.class).getProductStockInfo(map);
	}

	@Override
	public List<Product> getSCDInfo() {

		return session.getMapper(ProductMapper.class).getSCDInfo();
	}

	@Override
	public List<Product> getBInfo() {

		return session.getMapper(ProductMapper.class).getBInfo();
	}

	@Override
	public List<Product> getLInfo() {

		return session.getMapper(ProductMapper.class).getLInfo();
	}

	@Override
	public List<Shoppinglist> getShoppingList(String custid) {

		return session.getMapper(ProductMapper.class).getShoppingList(custid);
	}

	@Override
	public int insertOrderProduct(Orderproduct op) {

		return session.getMapper(ProductMapper.class).insertOrderProduct(op);
	}

	@Override
	public int updateStock(HashMap<String, String> map) {

		return session.getMapper(ProductMapper.class).updateStock(map);
	}

	@Override
	public int insertShoppinglist(Shoppinglist sl) {

		return session.getMapper(ProductMapper.class).insertShoppinglist(sl);
	}

	@Override
	public int deleteShoppinglist(String productserialnumber) {

		return session.getMapper(ProductMapper.class).deleteShoppinglist(productserialnumber);
	}
}
