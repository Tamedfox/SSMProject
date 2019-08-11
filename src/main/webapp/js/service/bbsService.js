//服务层
app.service('bbsService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('../bbs/findAll.do');		
	}
	//分页 
	this.findPage=function(page,rows){
		return $http.get('../bbs/findPage.do?page='+page+'&rows='+rows);
	}
	//查询实体
	this.findOne=function(id){
		return $http.get('../bbs/findOne.do?id='+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post('../bbs/add.do',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.post('../bbs/update.do',entity );
	}
	//删除
	this.dele=function(ids){
		return $http.get('../bbs/delete.do?ids='+ids);
	}
	//搜索
	this.search=function(page,rows,searchEntity){
		return $http.post('../bbs/search.do?page='+page+"&rows="+rows, searchEntity);
	}    
	
	//查找主题帖及回复
	this.findTopAndReplys=function(uuid){
		return $http.get('../bbs/findTopAndReplys.do?uuid='+uuid);
	}   
});
