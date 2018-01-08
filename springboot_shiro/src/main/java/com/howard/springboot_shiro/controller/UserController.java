package com.howard.springboot_shiro.controller;



import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.Logical;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    //@RequiresAuthentication //必须登录 这里登录拦截统一配置 所以只注解权限
    @RequiresPermissions("add")
    @RequestMapping("/add")
    public String add() {
        System.out.println("add");
        return "add";
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String addUser(String username,String password) {
        //加密，参考：http://jinnianshilongnian.iteye.com/blog/2021439
        System.out.println(username+"-"+password);
        String algorithmName = "md5";
        String salt1 = username;
        //随机数
        String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
        System.out.println("salt"+salt2);
        int hashIterations = 2;

        SimpleHash hash = new SimpleHash(algorithmName, password, salt1+salt2, hashIterations);
        String encodedPassword = hash.toHex();
        //System.out.println(salt1+salt2);
        //System.out.println(encodedPassword);
        //执行保存操作
        //System.out.println("add");
        return "permission";
    }

    @RequiresPermissions("update")
    @RequestMapping("update")
    public String update() {
        System.out.println("update");
        return "update";
    }

    @RequiresPermissions("delete")
    @RequestMapping("delete")
    public String delete() {
        System.out.println("delete");
        return "delete";
    }
    //@RequiresPermissions("select")
    @RequiresRoles(value = {"admin","customer"},logical = Logical.OR) //OR AND
    @RequestMapping("select")
    public String select() {
        System.out.println("select");
        return "select";
    }




    //注解方式下拦截未登录和权限不够的异常
    @ExceptionHandler({UnauthenticatedException.class})
    @ResponseStatus(value= HttpStatus.UNAUTHORIZED)
    public String processUnauthenticatedException(UnauthenticatedException e,Model model) {
        model.addAttribute("msg","用户未登录，请先登录");
        return "error";
    }
    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(value=HttpStatus.UNAUTHORIZED)
    public String processUnauthorizedException(UnauthorizedException e, Model model) {
        model.addAttribute("msg","权限不够");
        return "/error";
    }
}
