package com.cf.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cf.mapper.TbEmployeeMapper;
import com.cf.pojo.TbEmployee;
import com.cf.pojo.TbEmployeeExample;
import com.cf.pojo.TbEmployeeExample.Criteria;
import com.cf.pojo.result.PageResult;
import com.cf.service.EmployeeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private TbEmployeeMapper employeeMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbEmployee> findAll() {
		return employeeMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbEmployee> page=   (Page<TbEmployee>) employeeMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(TbEmployee employee) {
		employee.setPassword("123456");
		employee.setStatus("0");
		employeeMapper.insert(employee);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(TbEmployee employee){
		employeeMapper.updateByPrimaryKey(employee);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TbEmployee findOne(Long id){
		return employeeMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			employeeMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(TbEmployee employee, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbEmployeeExample example=new TbEmployeeExample();
		Criteria criteria = example.createCriteria();
		
		if(employee!=null){			
						if(employee.getName()!=null && employee.getName().length()>0){
				criteria.andNameLike("%"+employee.getName()+"%");
			}
			if(employee.getUsername()!=null && employee.getUsername().length()>0){
				criteria.andUsernameLike("%"+employee.getUsername()+"%");
			}
			if(employee.getPassword()!=null && employee.getPassword().length()>0){
				criteria.andPasswordLike("%"+employee.getPassword()+"%");
			}
			if(employee.getTelephone()!=null && employee.getTelephone().length()>0){
				criteria.andTelephoneLike("%"+employee.getTelephone()+"%");
			}
			if(employee.getDepartment()!= null ) {
				criteria.andDepartmentEqualTo(employee.getDepartment());
			}
			if(employee.getHeadPic()!=null && employee.getHeadPic().length()>0){
				criteria.andHeadPicLike("%"+employee.getHeadPic()+"%");
			}
			if(employee.geteMail()!=null && employee.geteMail().length()>0){
				criteria.andEMailLike("%"+employee.geteMail()+"%");
			}
			if(employee.getStatus()!=null && employee.getStatus().length()>0){
				criteria.andStatusLike("%"+employee.getStatus()+"%");
			}
	
		}
		
		Page<TbEmployee> page= (Page<TbEmployee>)employeeMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}

		/**
		 * 根据传入的id修改员工状态
		 * @param ids 传入id数组
		 * @param status 目标状态
		 */
		@Override
		public void updateStatus(Long[] ids, String status) {
			for (Long id : ids) {
				TbEmployee employee = employeeMapper.selectByPrimaryKey(id);
				employee.setStatus(status);
				employeeMapper.updateByPrimaryKey(employee);
			}
		}
	
		
}
