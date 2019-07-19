package  com.cashier.fiter;

import java.io.IOException;
import java.math.BigInteger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName="Servlet3Filter",urlPatterns="/*")
public class fiter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		/**
		 * 1,doFilter方法的第一个参数为ServletRequest对象。此对象给过滤器提供了对进入的信息（包括*
		 * 表单数据、cookie和HTTP请求头）的完全访问。第二个参数为ServletResponse，通常在简单的过*
		 * 滤器中忽略此参数。最后一个参数为FilterChain，此参数用来调用servlet或JSP页。
		 */
		HttpServletRequest Request = (HttpServletRequest) request;
		/**
		 * 如果处理HTTP请求，并且需要访问诸如getHeader或getCookies等在ServletRequest中*
		 * 无法得到的方法，就要把此request对象构造成HttpServletRequest
		 */
		HttpServletResponse Response = (HttpServletResponse) response;
		  String uri = Request.getRequestURI();
	       String targetURL = uri.substring(uri.indexOf("/", -1), uri.length());
	       if(uri.indexOf("/login.action")>-1){//登录接口不过滤
	    	   chain.doFilter(request, response);//递交给下一个过滤器
	            return;
	        }
		/*
		 * if(uri.indexOf("/updateOrderState.action")>-1){//app接口不过滤
		 * chain.doFilter(request, response);//递交给下一个过滤器 return; }
		 * if(uri.indexOf("/Querymembershipstatus.action")>-1){//app接口不过滤
		 * chain.doFilter(request, response);//递交给下一个过滤器 return; }
		 * if(uri.indexOf("/quershopping.action")>-1){//app接口不过滤 chain.doFilter(request,
		 * response);//递交给下一个过滤器 return; }
		 * if(uri.indexOf("/EnquiryonPreferentialPrice.action")>-1){//app接口不过滤
		 * chain.doFilter(request, response);//递交给下一个过滤器 return; }
		 * if(uri.indexOf("/appdylistOrderByOption.action")>-1){//app接口不过滤
		 * chain.doFilter(request, response);//递交给下一个过滤器 return; }
		 * if(uri.indexOf("/buyshuopping.action")>-1){//app接口不过滤 chain.doFilter(request,
		 * response);//递交给下一个过滤器 return; }
		 */           
	       if(uri.indexOf("/loginForAn.action")>-1){//app接口不过滤
	    	   chain.doFilter(request, response);//递交给下一个过滤器
	            return;
	        }
	     
		/*
		 * if(uri.indexOf("/applistOrderByOption.action")>-1){//app接口不过滤
		 * chain.doFilter(request, response);//递交给下一个过滤器 return; }
		 */
	       
	        if(uri.indexOf("/toLogin")>-1){//注册页面不过滤
	        	chain.doFilter(request, response);
	            return;
	        }
	        if(uri.indexOf("/logout")>-1){//注册页面不过滤
	        	chain.doFilter(request, response);
	            return;
	        }
	        if(uri.indexOf("/css")>-1){//注册页面不过滤
	        	chain.doFilter(request, response);
	            return;
	        }
	        if(uri.indexOf("/js")>-1){//注册页面不过滤
	        	chain.doFilter(request, response);
	            return;
	        }
	        if (uri.equals("/")) {//注册页面不过滤
	        	chain.doFilter(request, response);
	            return;
	        }
	        
	        HttpSession session=Request.getSession(true);
	        
	        String ip = Request.getHeader("X-Forwarded-For");
            //System.out.println(ip);
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = Request.getHeader("Proxy-Client-IP");
                //System.out.println("1:"+ip);
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = Request.getHeader("WL-Proxy-Client-IP");
                //System.out.println("2:"+ip);
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = Request.getHeader("HTTP_CLIENT_IP");
                //System.out.println("3:"+ip);
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = Request.getHeader("HTTP_X_FORWARDED_FOR");
                //System.out.println("4:"+ip);
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = Request.getRemoteAddr();
                //System.out.println("5:"+ip);
            }
            BigInteger shopId = (BigInteger) session.getAttribute("shopId");
	        String username = (String)session.getAttribute("username");
	        String urs=uri.substring(0,5); 
	        
	        if(!urs.equals("/sock")) {
	        	System.out.println("访问电脑地址"+"  "+ip+"  "+"登录用户名"+"  "+username+"  "+"请求的地址"+"  "+uri+"  "+"店铺id"+"  "+shopId);
	        }
	      
	       
	        if (username != null||shopId!=null) {//判断获取的session值是否为空
	        	chain.doFilter(request, response);
                return;
            } else {

            	Response.sendRedirect("/toLogin.action");
                return;

            }

		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
