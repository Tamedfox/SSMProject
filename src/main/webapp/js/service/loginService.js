//服务层
app.service('loginService',function($http){
	    	
	//登录
	this.login=function(entity){
		return $http.post('../login.do',entity);	
	}
	
	//获取验证码
	this.getSecCode=function(time){
		return $http.get('../code.do?t='+time);
	}
});
