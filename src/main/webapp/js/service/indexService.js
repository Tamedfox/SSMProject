//服务层
app.service('indexService',function($http){
	    	
	//登录
	this.showUsername=function(){
		return $http.get('../showUsername.do');	
	}
});
