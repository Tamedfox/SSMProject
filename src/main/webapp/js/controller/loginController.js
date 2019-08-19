 //控制层 
app.controller('loginController' ,function($scope,loginService){	
	
    //登录
	$scope.login=function(){
		loginService.login($scope.entity).success(
			function(response){
				if(response.success){
					location.href="index.html";
				}else{
					$scope.message = response.message;
				}		
			}			
		);
	}    
    
	//获取验证码
	$scope.getSecCode=function(){
		var time = new Date().getTime(); //将时间戳转换为日期类型
		loginService.getSecCode(time).success(
				function(response){
					alert(response);
					$scope.secCodeUrl=response;
				}
		)
	}
});	
