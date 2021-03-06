 //控制层 
app.controller('employeeController' ,function($scope,$controller   ,employeeService, departmentService){	
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		employeeService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//分页
	$scope.findPage=function(page,rows){			
		employeeService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//查询实体 
	$scope.findOne=function(id){				
		employeeService.findOne(id).success(
			function(response){
				$scope.entity= response;					
			}
		);				
	}
	
	//保存 
	$scope.save=function(){				
		var serviceObject;//服务层对象  				
		if($scope.entity.id!=null){//如果有ID
			serviceObject=employeeService.update( $scope.entity ); //修改  
		}else{
			serviceObject=employeeService.add( $scope.entity  );//增加 
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
		employeeService.dele( $scope.selectIds ).success(
			function(response){
				if(response.success){
					$scope.reloadList();//刷新列表
					$scope.selectIds=[];
				}						
			}		
		);				
	}
	
	$scope.searchEntity={};//定义搜索对象 
	
	$scope.status=['未激活','激活']; //状态列表
	
	//搜索
	$scope.search=function(page,rows){			
		employeeService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
    
	//部门列表
	$scope.departmentList=[];
	
	//部门显示departmentService
	$scope.findDepartmentList = function(){
		departmentService.findAll().success(
				function(response){
					for(var i = 0; i < response.length; i++){
						$scope.selectDepartmentList = response;
						$scope.departmentList[response[i].id] = response[i].name;
					}
				}
		)
	}
	
	//日期选择
	   var datepicker1 = $('#datetimepicker1').datetimepicker({
	        format: 'YYYY-MM-DD',
	        locale: moment.locale('zh-cn')
	    }).on('dp.change', function (e) {
	        var result = new moment(e.date).format('YYYY-MM-DD');
	        $scope.entity.hireDate = result;
	        $scope.$apply();
	    });

	   
	   //状态更新
	   $scope.updateStatus=function(status){
		   employeeService.updateStatus($scope.selectIds, status).success(
				   function(response){
						   $scope.reloadList();//刷新列表
						   $scope.selectIds=[];
				   }
		   )
	   }
});	
