package com.howard.springboot_shiro.controller;

import com.howard.springboot_shiro.service.UserService;
import com.howard.springboot_shiro.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("test")
    public User index() {
        User user = userService.findByUserName("howard");
        System.out.println(user);
        return user;
    }

    @RequestMapping("/index")
    public String toIndex(Model model) {
        model.addAttribute("hello","hello world!");
        return "index";
    }

    @RequestMapping("login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submit(String username, String password,Model model,HttpServletRequest request) {
//        User user = new User("shiro", "123456");
//        user.setRole(new Role("member"));
        System.out.println(username+"----"+password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
          /*   // 如果登陆成功
               if (user.getName().equals(username) && user.getPassword().equals(password)) {
                    UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user
                               .getPassword().toString());
                    Subject subject = SecurityUtils.getSubject();
                    subject.login(token);
               }*/

            subject.login(token); //这里会先调用realm的 doGetAuthenticationInfo方法 之后这里再login，根据结果判定 登录信息是否正确
        } catch(UnknownAccountException uae){
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
            //           model.setAttribute("message_login", "未知账户");
            model.addAttribute("msg", "未知账户");
        }catch(IncorrectCredentialsException ice){
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
            //           request.setAttribute("message_login", "密码不正确");
            model.addAttribute("msg", "密码不正确");
        }catch(LockedAccountException lae){
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
            //           request.setAttribute("message_login", "账户已锁定");
            model.addAttribute("msg", "账户已锁定");
        }catch(ExcessiveAttemptsException eae){
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
            //           request.setAttribute("message_login", "用户名或密码错误次数过多");
            model.addAttribute("msg", "用户名或密码错误次数过多");
        }catch(AuthenticationException ae){
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
            //           request.setAttribute("message_login", "用户名或密码不正确");
            model.addAttribute("msg", "用户名或密码不正确");
        }
        System.out.println(subject.isAuthenticated());
        if(subject.isAuthenticated() ){
            //没有认证的用户请求需要认证的链接时，shiro在跳转前会把跳转过来的页面链接保存到session的attribute中，key的值叫shiroSavedRequest，我们可以能过WebUtils类拿到。
            SavedRequest savedRequest = WebUtils.getSavedRequest(request);

            System.out.println("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
            // 获取保存的URL
            //如果不存在
            if (savedRequest == null || savedRequest.getRequestUrl() == null) {
                System.out.println("index");
                return "/permission";
            }
            //获取项目名
            String contextPath = request.getContextPath();
            System.out.println(contextPath);
            //存在之前的登录前url，仅限上面的说的请求需要登录的资源而未登录时才会保存
            //shiro-web-example/member/test 打印出来发现带有项目名
            //截取第二个/之后的字符串
            String url = savedRequest.getRequestUrl();
            if (contextPath == null || contextPath.trim().equals("")) {
                return "redirect:" + url;
            }
            //获取项目名开始时的下标
            int i = savedRequest.getRequestUrl().indexOf(contextPath);
            //截取项目名后的路径
            url = url.substring(i+contextPath.length());
            return "redirect:" + url;
        }else{
            token.clear();
            return "/login";
        }

    }

    @RequestMapping("/logout")
    public String logOut(HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
//        session.removeAttribute("user");
        return "login";
    }
}
