package org.jsp.board.service;

import java.util.HashMap;

import org.jsp.board.vo.SCustomer;

public interface SCustomerMapper {

	// 회원가입 insert
	public int joinCustomer(SCustomer sc);

	// 회원정보 수정 update
	public int updateCustomer(SCustomer sc);

	// 탈퇴 delete
	public int deleteCustomer(HashMap<String, String> map);

	// 로그인 select
	public SCustomer login(SCustomer sc);

	// id확인 select
	public String checkId(String custid);

}
