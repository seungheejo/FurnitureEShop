package org.jsp.board.service;

import java.util.HashMap;

import org.jsp.board.vo.SCustomer;

public interface SCustomerMapper {

	// 会員加入 insert
	public int joinCustomer(SCustomer sc);

	// 会員情報の修正 update
	public int updateCustomer(SCustomer sc);

	// 脱退 delete
	public int deleteCustomer(HashMap<String, String> map);

	// ログイン select
	public SCustomer login(SCustomer sc);

	// idを確認 select
	public String checkId(String custid);

}