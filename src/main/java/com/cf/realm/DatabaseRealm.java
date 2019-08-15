package com.cf.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.cf.pojo.TbEmployee;
import com.cf.pojo.TbRole;
import com.cf.service.EmployeeService;
import com.cf.service.RoleService;

public class DatabaseRealm extends AuthorizingRealm{
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private RoleService roleService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//如果进入这里代表已经通过验证了
		String userName = (String) principals.getPrimaryPrincipal();
		//获取角色
		TbEmployee employee = employeeService.findByName(userName);
		TbRole role = roleService.findOne(employee.getRole());
		
		Set<String> roles = new HashSet<String>();
		roles.add(role.getDescription());
		//授权对象
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(roles);
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//获取账号密码
		UsernamePasswordToken t = (UsernamePasswordToken) token;
		String userName = (String) t.getPrincipal();
		String password = new String(t.getPassword());
		//后去数据库中的账号密码
		TbEmployee employee = employeeService.findByName(userName);
		String passwordInDB = employee.getPassword();
		if(null == passwordInDB || !passwordInDB.equals(password)) {
			throw new AuthenticationException();
		}
		
		//创建认证信息类
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, password, getName());
		return authenticationInfo;
	}

}
