package org.jsp.board.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 회원 로그인을 확인하기 위한 인터셉터 
 * HandlerInterceptorAdapter를 상속받아 사용
 */

public class LoginInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	// 컨트롤러의 메소드를 실행하기 전에 처리
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		logger.debug("LoginInterceptor 실행");

		// 세션 로그인 정보 읽기
		HttpSession session = request.getSession();
		String loginid = (String) session.getAttribute("loginid");

		// 로그인 되어있지 않을 경우, 로그인 페이지로 이동
		if (loginid == null) {
			response.sendRedirect(request.getContextPath() + "/gologinform");
			return false;
		}

		// 로그인 되어있을 경우, 요청한 경로로 진행
		return super.preHandle(request, response, handler);
	}

}
