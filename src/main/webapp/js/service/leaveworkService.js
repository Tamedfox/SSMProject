//服务层
app.service('leaveworkService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('../leavework/findAll.do');		
	}
	//分页 
	this.findPage=function(page,rows){
		return $http.get('../leavework/findPage.do?page='+page+'&rows='+rows);
	}
	//查询实体
	this.findOne=function(id){
		return $http.get('../leavework/findOne.do?id='+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post('../leavework/add.do',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.post('../leavework/update.do',entity );
	}
	//删除
	this.dele=function(ids){
		return $http.get('../leavework/delete.do?ids='+ids);
	}
	//搜索
	this.search=function(page,rows,searchEntity){
		return $http.post('../leavework/search.do?page='+page+"&rows="+rows, searchEntity);
	}    
	
	//状态更新
	this.updateStatus=function(ids,status){
		return $http.get('../leavework/updateStatus.do?ids='+ids+'&status='+status);
	}
	
	//状态更新
	this.findByStatus=function(status){
		return $http.get('../leavework/findByStatus.do?status='+status);
	}
});
