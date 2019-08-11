 //控制层 
app.controller('leaveworkController' ,function($scope,$controller   ,leaveworkService){	
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		leaveworkService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//分页
	$scope.findPage=function(page,rows){			
		leaveworkService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//查询实体 
	$scope.findOne=function(id){				
		leaveworkService.findOne(id).success(
			function(response){
				$scope.entity= response;					
			}
		);				
	}
	
	//保存 
	$scope.save=function(){				
		var serviceObject;//服务层对象  				
		if($scope.entity.id!=null){//如果有ID
			serviceObject=leaveworkService.update( $scope.entity ); //修改  
		}else{
			serviceObject=leaveworkService.add( $scope.entity  );//增加 
		}				
		serviceObject.success(
			function(response){
				if(response.success){
					//重新查询 
		        	$scope.reloadList();//重新加载
				}else{
					alert(response.message);
				}
			}		
		);				
	}
	
	 
	//批量删除 
	$scope.dele=function(){			
		//获取选中的复选框			
		leaveworkService.dele( $scope.selectIds ).success(
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
		leaveworkService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
    
	//定义请假单状态数组
	$scope.leaveStatus=['未提交','申请中','通过','驳回'];
	
	//日期选择
	   var datepicker1 = $('#datetimepicker1').datetimepicker({
	        format: 'YYYY-MM-DD hh:mm:ss',
	        locale: moment.locale('zh-cn')
	    }).on('dp.change', function (e) {
	        var result = new moment(e.date).format('YYYY-MM-DD hh:mm:ss');
	        $scope.entity.startTime = result;
	        $scope.$apply();
	    });
	   
		//日期选择
	   var datepicker1 = $('#datetimepicker2').datetimepicker({
	        format: 'YYYY-MM-DD hh:mm:ss',
	        locale: moment.locale('zh-cn')
	    }).on('dp.change', function (e) {
	        var result = new moment(e.date).format('YYYY-MM-DD hh:mm:ss');
	        $scope.entity.endTime = result;
	        $scope.$apply();
	    });
	   
	   //定义假条类型
	   $scope.type={病假:'病假',事假:'事假',年假:'年假',丧假:'丧假',婚假:'婚假'};
//	   $scope.leaveType=[{type:'事假',content:'事假'},{type:'病假',content:'病假'},{type:'婚假',content:'婚假'},{type:'丧假',content:'丧假'},{type:'年假',content:'年假'}];
	   //状态更新
	   $scope.updateStatus=function(status){
		   leaveworkService.updateStatus($scope.selectIds, status).success(
				   function(response){
						   $scope.reloadList();//刷新列表
						   $scope.selectIds=[];
				   }
		   )
	   }
});	
