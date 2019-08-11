package com.cf.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cf.mapper.TbDepartmentMapper;
import com.cf.pojo.TbDepartment;
import com.cf.pojo.TbDepartmentExample;
import com.cf.pojo.TbDepartmentExample.Criteria;
import com.cf.pojo.result.PageResult;
import com.cf.service.DepartmentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private TbDepartmentMapper departmentMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbDepartment> findAll() {
		return departmentMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbDepartment> page=   (Page<TbDepartment>) departmentMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(TbDepartment department) {
		departmentMapper.insert(department);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(TbDepartment department){
		departmentMapper.updateByPrimaryKey(department);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TbDepartment findOne(Long id){
		return departmentMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			departmentMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(TbDepartment department, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbDepartmentExample example=new TbDepartmentExample();
		Criteria criteria = example.createCriteria();
		
		if(department!=null){			
						if(department.getName()!=null && department.getName().length()>0){
				criteria.andNameLike("%"+department.getName()+"%");
			}
	
		}
		
		Page<TbDepartment> page= (Page<TbDepartment>)departmentMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}
	
}
