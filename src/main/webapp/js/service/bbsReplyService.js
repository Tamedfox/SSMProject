//服务层
app.service('bbsReplyService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('../bbsReply/findAll.do');		
	}
	//分页 
	this.findPage=function(page,rows){
		return $http.get('../bbsReply/findPage.do?page='+page+'&rows='+rows);
	}
	//查询实体
	this.findOne=function(id){
		return $http.get('../bbsReply/findOne.do?id='+id);
	}
	//增加 
	this.add=function(entity,topicUuid){
		return  $http.post('../bbsReply/add.do?topicUuid='+topicUuid,entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.post('../bbsReply/update.do',entity );
	}
	//删除
	this.dele=function(ids){
		return $http.get('../bbsReply/delete.do?ids='+ids);
	}
	//搜索
	this.search=function(page,rows,searchEntity){
		return $http.post('../bbsReply/search.do?page='+page+"&rows="+rows, searchEntity);
	}
	//查找主题帖及回复
	this.findTopAndReplys=function(uuid){
		return $http.get('../bbsReply/findTopAndReplys.do?uuid='+uuid);
	}   
});
