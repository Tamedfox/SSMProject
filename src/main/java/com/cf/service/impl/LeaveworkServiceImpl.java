package com.cf.service.impl;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cf.mapper.TbLeaveworkMapper;
import com.cf.pojo.TbEmployee;
import com.cf.pojo.TbLeavework;
import com.cf.pojo.TbLeaveworkExample;
import com.cf.pojo.TbLeaveworkExample.Criteria;
import com.cf.pojo.result.PageResult;
import com.cf.pojo.result.Result;
import com.cf.service.LeaveworkService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
@Transactional
public class LeaveworkServiceImpl implements LeaveworkService {

	@Autowired
	private TbLeaveworkMapper leaveworkMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbLeavework> findAll() {
		return leaveworkMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbLeavework> page=   (Page<TbLeavework>) leaveworkMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(TbLeavework leavework) {
		leavework.setCreateTime(new Date());
		leaveworkMapper.insert(leavework);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(TbLeavework leavework){
		leavework.setUpdateTime(new Date());
		leaveworkMapper.updateByPrimaryKey(leavework);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TbLeavework findOne(Long id){
		return leaveworkMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			leaveworkMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(TbLeavework leavework, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbLeaveworkExample example=new TbLeaveworkExample();
		Criteria criteria = example.createCriteria();
		
		if(leavework!=null){			
						if(leavework.getUsername()!=null && leavework.getUsername().length()>0){
				criteria.andUsernameLike("%"+leavework.getUsername()+"%");
			}
			if(leavework.getType()!=null && leavework.getType().length()>0){
				criteria.andTypeLike("%"+leavework.getType()+"%");
			}
			if(leavework.getNode1()!=null && leavework.getNode1().length()>0){
				criteria.andNode1Like("%"+leavework.getNode1()+"%");
			}
			if(leavework.getNode2()!=null && leavework.getNode2().length()>0){
				criteria.andNode2Like("%"+leavework.getNode2()+"%");
			}
			if(leavework.getNode3()!=null && leavework.getNode3().length()>0){
				criteria.andNode3Like("%"+leavework.getNode3()+"%");
			}
			if(leavework.getRemark()!=null && leavework.getRemark().length()>0){
				criteria.andRemarkLike("%"+leavework.getRemark()+"%");
			}
			if(leavework.getStatus()!=null && leavework.getStatus().length()>0){
				criteria.andStatusLike("%"+leavework.getStatus()+"%");
			}
	
		}
		
		Page<TbLeavework> page= (Page<TbLeavework>)leaveworkMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}
	
		/**
		 * 更新请假单状态
		 * @param ids 员工群组
		 * @param status 目标状态 
		 * @return
		 */
		@Override
		public void updateStatus(Long[] ids, String status) {
			for (Long id : ids) {
				TbLeavework leaveWork = leaveworkMapper.selectByPrimaryKey(id);
				leaveWork.setStatus(status);
				leaveworkMapper.updateByPrimaryKey(leaveWork);
			}
		}

		/**
		 * 根据状态查找
		 */
		@Override
		public List<TbLeavework> findByStatus(String status) {
			TbLeaveworkExample example = new TbLeaveworkExample();
			Criteria criteria = example.createCriteria();
			criteria.andStatusEqualTo(status);
			return leaveworkMapper.selectByExample(example );
		}
		
		
}
