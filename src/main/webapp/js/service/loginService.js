//服务层
app.service('loginService',function($http){
	    	
	//登录
	this.login=function(entity){
		return $http.post('../login.do',entity);	
	}
});
