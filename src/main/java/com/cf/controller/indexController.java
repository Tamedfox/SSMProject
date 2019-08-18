package com.cf.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author Administrator
 *此controller显示首页用户名
 */

@RestController
public class indexController {

	/**
	 * 展示用户名
	 * @return
	 */
	@RequestMapping("/showUsername")
	public Map<String, String> showUsername() {
		Map<String, String> map = new HashMap<String, String>();
		Subject subject = SecurityUtils.getSubject();
		String username = (String) subject.getPrincipal();
		map.put("username", username);
		return map;
	}
	
}
