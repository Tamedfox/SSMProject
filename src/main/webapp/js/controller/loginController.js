 //控制层 
app.controller('loginController' ,function($scope,loginService){	
	
    //登录
	$scope.login=function(){
		loginService.login($scope.entity).success(
			function(response){
				if(response.success){
					alert("OK");
					location.href="index.html";
				}					
			}			
		);
	}    
    
});	
