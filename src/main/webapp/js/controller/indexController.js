 //控制层 
app.controller('indexController' ,function($scope,indexService){	
	
    //登录
	$scope.showName=function(){
		indexService.showUsername().success(
			function(response){
				$scope.loginName = response.username;
			}			
		);
	}    
    
});	
