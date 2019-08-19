package com.cf.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cf.pojo.group.LoginUser;
import com.cf.pojo.result.Result;

/**
 * 登录控制
 * @author Administrator
 *
 */

@RestController
public class LoginController {

	@RequestMapping(value="/login",method=RequestMethod.POST)
	public Result login(@RequestBody LoginUser loginUser) {
		//获取subject主题
		Subject subject = SecurityUtils.getSubject();
		//获取shiro中session的验证码
		Session session = subject.getSession();
		String sessionCode = (String) session.getAttribute("sessionSecCode");
		//获取登录页输入的验证码
		String secCode = loginUser.getSecCode();
//		System.out.println("session中"+sessionCode);
//		System.out.println("输入的" + loginUser.getSecCode());
		if(null != secCode && sessionCode.equalsIgnoreCase(secCode)) {
			//获取输入的账号密码
			String userName = loginUser.getUsername();
			String password = loginUser.getPassword();
			UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
			
			try {
				subject.login(token);
				return new Result(true, "登录成功，欢迎");
			} catch (AuthenticationException e) {
				e.printStackTrace();
				return new Result(false, "登录失败，用户名或密码错误");
			}
		}else {
			return new Result(false, "验证码错误");
		}
		
	}
	
}
