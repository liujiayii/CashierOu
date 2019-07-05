package com.cashier.entity;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author 
 * @return
 * @author
 */
/**
 *
 * @ClassName: MySessionListener

 * @description 
 *
 * @author 
 * @createDate 2018年11月29日
 */

public class MySessionListener  implements HttpSessionListener {
    
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        MySessionContex.AddSession(httpSessionEvent.getSession());
        }

        public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
            HttpSession session = httpSessionEvent.getSession();
            MySessionContex.DelSession(session);
        }
}
