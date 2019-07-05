package com.cashier.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.cashier.entity.CustomException;

/**
 * @author 
 * @return
 * @author
 */
/**
 *
 * @ClassName: CustomExceptionResolver

 * @description 
 *
 * @author 
 * @createDate 2018年10月18日
 */

public class CustomExceptionResolver implements HandlerExceptionResolver {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerExceptionResolver#resolveException(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	/*@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		// TODO Auto-generated method stub
		return null;
	}*/

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.HandlerExceptionResolver#resolveException(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        // TODO Auto-generated method stub
        CustomException customException =new CustomException("系统哥哥跑丢了，联系管理员吧！");
        if (ex instanceof CustomException) {
            customException = (CustomException) ex;
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("error", customException.getMsg());
            modelAndView.setViewName("Login/error");
            return modelAndView;
        } 
        return null;
    }

}
