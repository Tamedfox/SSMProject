package com.cf.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cf.pojo.TbEmployee;
import com.cf.pojo.result.Result;

/**
 * 登录控制
 * @author Administrator
 *
 */

@RestController
public class LoginController {

	@RequestMapping(value="/login",method=RequestMethod.POST)
	public Result login(@RequestBody TbEmployee employee) {
		//获取subject主题
		Subject subject = SecurityUtils.getSubject();
		//获取输入的账号密码
		String userName = employee.getUsername();
		String password = employee.getPassword();
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
		
		try {
			subject.login(token);
			return new Result(true, "登录成功，欢迎");
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return new Result(false, "登录失败");
		}
	}
	
}
