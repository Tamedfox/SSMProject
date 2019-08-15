package com.cf.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cf.mapper.TbRoleMapper;
import com.cf.pojo.TbRole;
import com.cf.pojo.TbRoleExample;
import com.cf.pojo.TbRoleExample.Criteria;
import com.cf.pojo.result.PageResult;
import com.cf.service.RoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private TbRoleMapper roleMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbRole> findAll() {
		return roleMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbRole> page=   (Page<TbRole>) roleMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(TbRole role) {
		roleMapper.insert(role);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(TbRole role){
		roleMapper.updateByPrimaryKey(role);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TbRole findOne(Long id){
		return roleMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			roleMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(TbRole role, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbRoleExample example=new TbRoleExample();
		Criteria criteria = example.createCriteria();
		
		if(role!=null){			
						if(role.getName()!=null && role.getName().length()>0){
				criteria.andNameLike("%"+role.getName()+"%");
			}
			if(role.getDescription()!=null && role.getDescription().length()>0){
				criteria.andDescriptionLike("%"+role.getDescription()+"%");
			}
	
		}
		
		Page<TbRole> page= (Page<TbRole>)roleMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}
	
}
