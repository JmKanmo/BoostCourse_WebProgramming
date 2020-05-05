package kr.or.connect.reservation.interceptor;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LogInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private String getFormattedTime(long currentTime) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월dd일 HH시mm분ss초");
		return formatter.format(currentTime);
	}

	private String getClientIP(HttpServletRequest request) {
		String ip = request.getHeader("X-FORWARDED-FOR");

		if (ip == null || ip.length() == 0) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	private void printLogInfo(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) {
//		System.out.println("<--------------------------->");
//		System.out.println("[postHandle] ====> 수행작업:" + handler.toString());
//		System.out.println("[postHandle] ====> 요청URL:" + request.getRequestURI());
//		System.out.println("[postHandle] ====> 요청시간:" + getFormattedTime(System.currentTimeMillis()));
//		System.out.println("[postHandle] ====> IP주소:" + getClientIP(request));
//
		if (modelAndView != null) {
//			System.out.println("[postHandle] ====> 호출된 뷰:" + modelAndView.getViewName());
			logger.debug("[postHandle] ==> 호출된 뷰:{}", modelAndView.getViewName());
		}

//		System.out.println("<--------------------------->\n");

		logger.debug("[postHandle] ==> 수행작업:{}, 요청URL:{}, 요청시간:{}, IP주소:{}", handler.toString(),
				request.getRequestURI(), getFormattedTime(System.currentTimeMillis()), getClientIP(request));
	}

	private void printLogInfo(HttpServletRequest request, HttpServletResponse response, Object handler) {
//		System.out.println("<--------------------------->");
//		System.out.println("[preHandle] ====> 수행작업:" + handler.toString());
//		System.out.println("[preHandle] ====> 요청URL:" + request.getRequestURI());
//		System.out.println("[preHandle] ====> 요청시간:" + getFormattedTime(System.currentTimeMillis()));
//		System.out.println("[preHandle] ====> IP주소:" + getClientIP(request));
//		System.out.println("<--------------------------->\n");

		logger.debug("[postHandle] ==> 수행작업:{}, 요청URL:{}, 요청시간:{}, IP주소:{}", handler.toString(),
				request.getRequestURI(), getFormattedTime(System.currentTimeMillis()), getClientIP(request));
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		printLogInfo(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		printLogInfo(request, response, handler);
		return true;
	}
}