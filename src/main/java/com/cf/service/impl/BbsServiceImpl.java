package com.cf.service.impl;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cf.mapper.TbBbsMapper;
import com.cf.pojo.TbBbs;
import com.cf.pojo.TbBbsExample;
import com.cf.pojo.TbBbsExample.Criteria;
import com.cf.pojo.TbBbsReply;
import com.cf.pojo.group.BlogReplys;
import com.cf.pojo.result.PageResult;
import com.cf.service.BbsReplyService;
import com.cf.service.BbsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.util.UuidUtil;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class BbsServiceImpl implements BbsService {

	@Autowired
	private TbBbsMapper bbsMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbBbs> findAll() {
		return bbsMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbBbs> page=   (Page<TbBbs>) bbsMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(TbBbs bbs) {
		String uuid = UuidUtil.get32UUID();
		bbs.setUuid(uuid);
		bbs.setPublishTime(new Date());
		bbs.setUsername("chen");
		bbsMapper.insert(bbs);
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(TbBbs bbs){
		bbsMapper.updateByPrimaryKey(bbs);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TbBbs findOne(String id){
		return bbsMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(String[] ids) {
		for(String id:ids){
			bbsMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(TbBbs bbs, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbBbsExample example=new TbBbsExample();
		Criteria criteria = example.createCriteria();
		
		if(bbs!=null){			
						if(bbs.getUuid()!=null && bbs.getUuid().length()>0){
				criteria.andUuidLike("%"+bbs.getUuid()+"%");
			}
			if(bbs.getContent()!=null && bbs.getContent().length()>0){
				criteria.andContentLike("%"+bbs.getContent()+"%");
			}
			if(bbs.getTitle()!=null && bbs.getTitle().length()>0){
				criteria.andTitleLike("%"+bbs.getTitle()+"%");
			}
			if(bbs.getUsername()!=null && bbs.getUsername().length()>0){
				criteria.andUsernameLike("%"+bbs.getUsername()+"%");
			}
			if(bbs.getRemark()!=null && bbs.getRemark().length()>0){
				criteria.andRemarkLike("%"+bbs.getRemark()+"%");
			}
	
		}
		
		Page<TbBbs> page= (Page<TbBbs>)bbsMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}
		
		@Autowired
		private BbsReplyService bbsReplyService;

		@Override
		public BlogReplys findBlogReplys(String uuid) {
			//查找主题帖进行封装
			BlogReplys blogReplys = new BlogReplys();
			TbBbs bbs = bbsMapper.selectByPrimaryKey(uuid);
			blogReplys.setBbs(bbs);
			//查找所有回复进行封装
			List<TbBbsReply> replyList = bbsReplyService.findByBbs(uuid);
			blogReplys.setReplyList(replyList);
			return blogReplys;
		}
	
}
