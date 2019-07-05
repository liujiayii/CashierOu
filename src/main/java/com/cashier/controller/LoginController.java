package com.cashier.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cashier.entity.CustomException;
import com.cashier.entity.MySessionContex;
import com.cashier.entity.Product;
import com.cashier.entity.Regulation;
import com.cashier.entity.Shop;
import com.cashier.entity.User;
import com.cashier.service.LoginService;
import com.cashier.service.ShopService;
import com.cashier.util.JedisClientSingle;

/*import com.cashier.dao.RegulationMapper;
import com.cashier.dao.SpecialOffersMapper;
import com.cashier.entityVo.ActivityAndProductVo;
*/
/**
 * @ClassName: LoginController
 * @description 登陆注册相关操作
 * @author zhoujiaxin
 * @createDate 2018年11月19日
 */
@Controller
public class LoginController {
    
    @Resource
    private LoginService loginService;
    @Resource
    JedisClientSingle jedisClientSingle;
    @Autowired
    private ShopService shopService;
   /* @Autowired
    private SpecialOffersMapper specialOffersMapper;
    @Autowired
    private RegulationMapper regulationMapper;*/
    
    
    /**
     * 
         * @Title: pc端用户登陆
         * @description  login
         * @param  username 用户名  password 密码
         * @return   
         * @author chenshuxian	
         * @createDate 2019年7月5日
     */
    @RequestMapping("/login")
    @ResponseBody
    public Map<String,Object> login(String username, String password,  
            HttpServletRequest request, HttpServletResponse response,HttpSession session, Model model) throws Exception{
        // 用户登陆时先判断有没有被限制
        String usernames = jedisClientSingle.get(username);
        
        Map<String,Object> map =  new HashMap<>();
        if (usernames!="" && usernames!=null && usernames.matches("[0-9]{1,}")) {
            if (Integer.parseInt(usernames)>=3) {
                //throw new CustomException("你可能忘记你的用户名密码了，等10分钟再试试吧");
                /*model.addAttribute("username", username);
                model.addAttribute("password", password);
                model.addAttribute("message", "你可能忘记用户名密码了，等10分钟再试试吧");*/
            	map.put("code", -1);
            	map.put("username", username);
            	map.put("password", password);
            	map.put("msg", "你可能忘记用户名密码了，等10分钟再试试吧");
                return map;
            }
        }
        // 获取Subject
        Subject subject = SecurityUtils.getSubject();
        // 获取token
        AuthenticationToken token = new UsernamePasswordToken(username, password);
        // 登陆
        try {
            subject.login(token);
        } catch (Exception e) {
            //throw new CustomException("你输入的用户名和密码不匹配");
            String usernamess = jedisClientSingle.get(username);
            if (usernamess != "" && usernamess!=null && usernamess.matches("[0-9]{1,}")) {
                int num = Integer.parseInt(usernamess)+1;
                jedisClientSingle.set(username, ""+num);
                if (num>=3) {
                    jedisClientSingle.setExpire(username, 600);
                }
            }else{
                jedisClientSingle.set(username, "1");
            }
            
           /* model.addAttribute("username", username);
            model.addAttribute("password", password);
            model.addAttribute("message", "你输入的用户名和密码不匹配");*/
            map.put("code", -1);
        	map.put("username", username);
        	map.put("password", password);
        	map.put("msg", "你输入的用户名和密码不匹配");
            return map;
        }
       
        // 通过jedis保存用户信息
        try {
            
            String beforSessionId = jedisClientSingle.get(username);
            if ("".equals(beforSessionId) || beforSessionId == null   ) {
                String sessionId = session.getId();
                jedisClientSingle.set(username, sessionId);
                jedisClientSingle.setExpire(username, 1800);
                MySessionContex.AddSession(session);
            }else{
                HttpSession oldSession = MySessionContex.getSession(beforSessionId);
                if (oldSession !=null && !oldSession.equals(session)) {
                    MySessionContex.DelSession(oldSession);
                    oldSession.invalidate();
                }
                String sessionId = session.getId();
                jedisClientSingle.set(username, sessionId);
                jedisClientSingle.setExpire(username, 1800);
                MySessionContex.AddSession(session);
            }
            
            // 存shopId username 到session
            User user = loginService.selectUserByUsername(username);
            Shop s = new Shop();
            s.setId(user.getShopId());
            Shop shop = shopService.getId(s);
            session.setAttribute("user", user);
            session.setAttribute("shop", shop);
            session.setAttribute("username", username);
            session.setAttribute("shopId", user.getShopId());
            map.put("code", 1);
            map.put("msg", "登陆成功");
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        return map;
    }
    
    
    @RequestMapping("/loginForAn")
    @ResponseBody
    public Map<String, Object> loginForAn(String username, String password,  
            HttpServletRequest request, HttpServletResponse response,HttpSession session, Model model) throws Exception{
    	Map<String, Object> map = new HashMap<>();
        // 获取Subject
        Subject subject = SecurityUtils.getSubject();
        // 获取token
        AuthenticationToken token = new UsernamePasswordToken(username, password);
        // 登陆
        try {
            subject.login(token);
        } catch (Exception e) {
            map.put("code", 0);
            map.put("msg", "登陆失败");
            return map;// 账号或密码错误
        }
       
        // 通过jedis保存用户信息
        try {
            
            String beforSessionId = jedisClientSingle.get(username);
            if ("".equals(beforSessionId) || beforSessionId == null   ) {
                String sessionId = session.getId();
                jedisClientSingle.set(username, sessionId);
                jedisClientSingle.setExpire(username, 1800);
                
                MySessionContex.AddSession(session);
            }else{
                HttpSession oldSession = MySessionContex.getSession(beforSessionId);
                if (oldSession !=null && !oldSession.equals(session)) {
                    MySessionContex.DelSession(oldSession);
                    oldSession.invalidate();
                }
                String sessionId = session.getId();
                jedisClientSingle.set(username, sessionId);
                jedisClientSingle.setExpire(username, 1800);
                MySessionContex.AddSession(session);
            }
            
            // 存shopId username 到session
            User user = loginService.selectUserByUsername(username);
            session.setAttribute("username", username);
            session.setAttribute("shopId", user.getShopId());
            Shop s = new Shop();
            s.setId(user.getShopId());
            //根据shopId查询店铺详情
            Shop shop = shopService.getId(s);
            map.put("shop", shop);
            // 根据店铺ID获取当前店铺的优惠信息
            //PreferentialManagement preferentialManagement = new PreferentialManagement();
            //preferentialManagement.setShopId(user.getShopId());
            // 根据shopID查询出该店铺参加的优惠情况（查询参加优惠数据，按优惠等级降序，）
            //List<Integer> list = preferentialManagementService.listPreferentialManagementOrder(preferentialManagement);
            //jedisClientSingle.setExpire("shopDiscount"+user.getShopId(), 0);
            /*if (list.size()>0) {
                jedisClientSingle.set("shopDiscount"+user.getShopId(), list.toString());
                jedisClientSingle.setExpire("shopDiscount"+user.getShopId(), 1800);
                // 22通过活动商品表查询出当前进行活动中的所有商品信息（商品不重复）
                List<ActivityAndProductVo> activityAndProductVoList = new ArrayList<>();
                Product product = new Product();
                product.setShopId(user.getShopId());
                activityAndProductVoList = specialOffersMapper.listProductsbyOngoingActivity(product);
                jedisClientSingle.setExpire("selectProductByActivity"+user.getShopId(),0);
                if (activityAndProductVoList.size()>0) {
                    jedisClientSingle.set("selectProductByActivity"+user.getShopId(), net.sf.json.JSONArray.fromObject(activityAndProductVoList).toString());
                    jedisClientSingle.setExpire("selectProductByActivity"+user.getShopId(),1800);
                }
                // 33从数据库中查找满减规则
                List<Regulation> FullReduceList = new ArrayList<>();
                Regulation regulation = new Regulation();
                regulation.setShopId(user.getShopId());
                FullReduceList = regulationMapper.listAllRules(regulation);
                jedisClientSingle.setExpire("selectFullReduce"+user.getShopId(), 0);
                if(FullReduceList.size()>0){
                    jedisClientSingle.set("selectFullReduce"+user.getShopId(), net.sf.json.JSONArray.fromObject(FullReduceList).toString());
                    jedisClientSingle.setExpire("selectFullReduce"+user.getShopId(), 1800);
                }
                
                map.put("code", 1);
                map.put("msg", "登陆成功");
                return map;
            }else{
                jedisClientSingle.set("shopDiscount"+user.getShopId(), "noDiscount");
                jedisClientSingle.setExpire("shopDiscount"+user.getShopId(), 1800);
                // 22通过活动商品表查询出当前进行活动中的所有商品信息（商品不重复）
                List<ActivityAndProductVo> activityAndProductVoList = new ArrayList<>();
                Product product = new Product();
                product.setShopId(user.getShopId());
                activityAndProductVoList = specialOffersMapper.listProductsbyOngoingActivity(product);
                jedisClientSingle.setExpire("selectProductByActivity"+user.getShopId(),0);
                if (activityAndProductVoList.size()>0) {
                    jedisClientSingle.set("selectProductByActivity"+user.getShopId(), net.sf.json.JSONArray.fromObject(activityAndProductVoList).toString());
                    jedisClientSingle.setExpire("selectProductByActivity"+user.getShopId(),1800);
                }
                // 33从数据库中查找满减规则
                List<Regulation> FullReduceList = new ArrayList<>();
                Regulation regulation = new Regulation();
                regulation.setShopId(user.getShopId());
                FullReduceList = regulationMapper.listAllRules(regulation);
                jedisClientSingle.setExpire("selectFullReduce"+user.getShopId(), 0);
                if(FullReduceList.size()>0){
                    jedisClientSingle.set("selectFullReduce"+user.getShopId(), net.sf.json.JSONArray.fromObject(FullReduceList).toString());
                    jedisClientSingle.setExpire("selectFullReduce"+user.getShopId(), 1800);
                }
                
                map.put("code", 1);
                map.put("msg", "登陆成功");
                return map;
            }*/
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        map.put("code", 1);
        map.put("msg", "登陆成功");
        return map;
    }

   /***
     * @description 退出登录
     * @author zhoujiaxin
     * @createDate 2018年1月31日
     */
    @RequestMapping("/logout")
    @ResponseBody
    public Map<String,Object> logout(Model model){
    	Map<String,Object> map = new HashMap<>();
    	map.put("code", 1);
    	map.put("msg", "退出登陆");
        return map;
    }
}
