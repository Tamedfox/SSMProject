package com.cf.service;
import java.util.List;

import com.cf.pojo.TbBbs;
import com.cf.pojo.group.BlogReplys;
import com.cf.pojo.result.PageResult;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface BbsService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbBbs> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum,int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(TbBbs bbs);
	
	
	/**
	 * 修改
	 */
	public void update(TbBbs bbs);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public TbBbs findOne(String id);
	
	
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
	public PageResult findPage(TbBbs bbs, int pageNum,int pageSize);
	
	/**
	 * 查找主题贴及回复
	 * @param uuid
	 * @return
	 */
	public BlogReplys findBlogReplys(String uuid);
}
