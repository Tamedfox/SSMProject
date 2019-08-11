 //控制层 
app.controller('bbsReplyController' ,function($scope,$controller   ,bbsReplyService){	
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		bbsReplyService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//分页
	$scope.findPage=function(page,rows){			
		bbsReplyService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//查询实体 
	$scope.findOne=function(id){				
		bbsReplyService.findOne(id).success(
			function(response){
				$scope.entity= response;					
			}
		);				
	}
	
	//保存 
	$scope.save=function(topicUuid){
		bbsReplyService.add($scope.entity, topicUuid).success(
				function(response){
					if(response.success){
						location.href="bbs_replys.html?uuid="+topicUuid;//刷新列表
					}
				}
		)
		
//		var serviceObject;//服务层对象  				
//		if($scope.entity.id!=null){//如果有ID
//			serviceObject=bbsReplyService.update( $scope.entity ); //修改  
//		}else{
//			serviceObject=bbsReplyService.add( $scope.entity  );//增加 
//		}				
//		serviceObject.success(
//			function(response){
//				if(response.success){
//					//重新查询 
//		        	$scope.reloadList();//重新加载
//				}else{
//					alert(response.message);
//				}
//			}		
//		);				
	}
	
	 
	//批量删除 
	$scope.dele=function(){			
		//获取选中的复选框			
		bbsReplyService.dele( $scope.selectIds ).success(
			function(response){
				if(response.success){
					$scope.reloadList();//刷新列表
					$scope.selectIds=[];
				}						
			}		
		);				
	}
	
	$scope.searchEntity={};//定义搜索对象 
	
	//搜索
	$scope.search=function(page,rows){			
		bbsReplyService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
    
	
	//查找主题帖和回复
	$scope.findTopicAndReplys=function(){
		var str = location.href; //取得整个地址栏
		var uuid = str.substr(str.indexOf("=")+1);
		bbsReplyService.findTopAndReplys(uuid).success(
				function(response){
					$scope.topicAndReplys=response;
				}
		)
	}
	
	//跳转主页
	$scope.goBbs=function(){
		location.href="bbs.html";
	}
	
	//增加留言
	
});	
