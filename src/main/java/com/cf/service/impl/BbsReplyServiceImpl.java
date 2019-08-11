package com.cf.service.impl;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cf.mapper.TbBbsReplyMapper;
import com.cf.pojo.TbBbsReply;
import com.cf.pojo.TbBbsReplyExample;
import com.cf.pojo.TbBbsReplyExample.Criteria;
import com.cf.pojo.result.PageResult;
import com.cf.service.BbsReplyService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.util.UuidUtil;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class BbsReplyServiceImpl implements BbsReplyService {

	@Autowired
	private TbBbsReplyMapper bbsReplyMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbBbsReply> findAll() {
		return bbsReplyMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbBbsReply> page=   (Page<TbBbsReply>) bbsReplyMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(TbBbsReply bbsReply,String topicUuid) {
		String uuid = UuidUtil.get32UUID();
		bbsReply.setUuid(uuid);
		bbsReply.setPublishTime(new Date());
		bbsReply.setTopicUuid(topicUuid);
		bbsReply.setUsername("testuser1");
		bbsReplyMapper.insert(bbsReply);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(TbBbsReply bbsReply){
		bbsReplyMapper.updateByPrimaryKey(bbsReply);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TbBbsReply findOne(String id){
		return bbsReplyMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(String[] ids) {
		for(String id:ids){
			bbsReplyMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(TbBbsReply bbsReply, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbBbsReplyExample example=new TbBbsReplyExample();
		Criteria criteria = example.createCriteria();
		
		if(bbsReply!=null){			
						if(bbsReply.getUuid()!=null && bbsReply.getUuid().length()>0){
				criteria.andUuidLike("%"+bbsReply.getUuid()+"%");
			}
			if(bbsReply.getContent()!=null && bbsReply.getContent().length()>0){
				criteria.andContentLike("%"+bbsReply.getContent()+"%");
			}
			if(bbsReply.getTopicUuid()!=null && bbsReply.getTopicUuid().length()>0){
				criteria.andTopicUuidEqualTo("%"+bbsReply.getTopicUuid()+"%");
			}
			if(bbsReply.getUsername()!=null && bbsReply.getUsername().length()>0){
				criteria.andUsernameLike("%"+bbsReply.getUsername()+"%");
			}
			if(bbsReply.getRemark()!=null && bbsReply.getRemark().length()>0){
				criteria.andRemarkLike("%"+bbsReply.getRemark()+"%");
			}
	
		}
		
		Page<TbBbsReply> page= (Page<TbBbsReply>)bbsReplyMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}

		@Override
		public List<TbBbsReply> findByBbs(String uuid) {
			TbBbsReplyExample example = new TbBbsReplyExample();
			Criteria criteria = example.createCriteria();
			criteria.andTopicUuidEqualTo(uuid);
			return bbsReplyMapper.selectByExample(example );
		}
	
}
