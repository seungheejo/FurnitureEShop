package org.jsp.board.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 会員のログイン確認のためのインターセプター 
 * HandlerInterceptorAdapterを相続してもらって使う
 */

public class LoginInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	// コントローラのメソッドを実行する前に処理
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		logger.debug("LoginInterceptor 실행");

		// セッションのログイン情報読取
		HttpSession session = request.getSession();
		String loginid = (String) session.getAttribute("loginid");

		// ログインされない場合、ログインページに移動
		if (loginid == null) {
			response.sendRedirect(request.getContextPath() + "/gologinform");
			return false;
		}

		// ログインされた場合、要求した経路で進行
		return super.preHandle(request, response, handler);
	}

}
