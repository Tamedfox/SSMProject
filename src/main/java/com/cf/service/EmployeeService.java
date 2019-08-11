package com.cf.service;
import java.util.List;

import com.cf.pojo.TbEmployee;
import com.cf.pojo.result.PageResult;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface EmployeeService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbEmployee> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum,int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(TbEmployee employee);
	
	
	/**
	 * 修改
	 */
	public void update(TbEmployee employee);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public TbEmployee findOne(Long id);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Long [] ids);

	/**
	 * 分页
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageResult findPage(TbEmployee employee, int pageNum,int pageSize);
	
	/**
	 * 根据传入的id修改员工状态
	 * @param ids 传入id数组
	 * @param status 目标状态
	 */
	public void updateStatus(Long[] ids, String status);
}
