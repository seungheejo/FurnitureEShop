package org.jsp.board.service;

import java.util.HashMap;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.jsp.board.vo.SCustomer;
import org.springframework.stereotype.Repository;

@Repository
public class SCustomerDAO implements SCustomerMapper {
	
	@Inject
	private SqlSession session;
	
	@Override
	public int joinCustomer(SCustomer sc) {
		
		return session.getMapper(SCustomerMapper.class).joinCustomer(sc);
	}

	@Override
	public int updateCustomer(SCustomer sc) {
		
		return session.getMapper(SCustomerMapper.class).updateCustomer(sc);
	}

	@Override
	public int deleteCustomer(HashMap<String, String> map) {
	
		return session.getMapper(SCustomerMapper.class).deleteCustomer(map);
	}

	@Override
	public SCustomer login(SCustomer sc) {
		
		return session.getMapper(SCustomerMapper.class).login(sc);
	}

	@Override
	public String checkId(String custid) {
		
		return session.getMapper(SCustomerMapper.class).checkId(custid);
	}
	

}
