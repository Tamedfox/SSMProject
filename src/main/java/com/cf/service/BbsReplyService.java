package com.cf.service;
import java.util.List;

import com.cf.pojo.TbBbsReply;
import com.cf.pojo.result.PageResult;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface BbsReplyService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbBbsReply> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum,int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(TbBbsReply bbsReply,String topicUuid);
	
	
	/**
	 * 修改
	 */
	public void update(TbBbsReply bbsReply);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public TbBbsReply findOne(String id);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(String [] ids);

	/**
	 * 分页
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageResult findPage(TbBbsReply bbsReply, int pageNum,int pageSize);
	
	/**
	 * 根据主题帖id查找下面的所有回复
	 * @param uuid
	 * @return
	 */
	public List<TbBbsReply> findByBbs(String uuid);
}
