package cn.edu.gdin.filter;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class LoginFilter extends HttpServlet implements Filter {
	private FilterConfig filterConfig;

	// Handle the passed-in FilterConfig
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		System.out.println("loginfilter-------------------");
	}

	// Process the request/response pair
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain filterChain) {
		try {
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) resp;
			HttpSession sess = request.getSession();
			String page = request.getServletPath();
			if (!"/login.jsp".equals(page)) {
				// ��ǰ���ʵĲ��ǵ�¼ҳ��
				// ��û�е�¼��
				if("/register.jsp".equals(page)||"/index.jsp".equals(page)){
					
				}else if (sess.getAttribute("username") == null) {
					// û�е�¼
					response.sendRedirect("http://127.0.0.1:8080/Zhuo/login.jsp");
				}
			}
			filterChain.doFilter(req, resp);
		} catch (ServletException sx) {
			filterConfig.getServletContext().log(sx.getMessage());
		} catch (IOException iox) {
			filterConfig.getServletContext().log(iox.getMessage());
		}
	}

	// Clean up resources
	public void destroy() {
	}
}
