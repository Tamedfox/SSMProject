package com.cf.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cf.pojo.TbBbsReply;
import com.cf.pojo.group.BlogReplys;
import com.cf.pojo.result.PageResult;
import com.cf.pojo.result.Result;
import com.cf.service.BbsReplyService;
import com.cf.service.BbsService;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/bbsReply")
public class BbsReplyController {

	@Autowired
	private BbsReplyService bbsReplyService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbBbsReply> findAll(){			
		return bbsReplyService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return bbsReplyService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param bbsReply
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody TbBbsReply bbsReply,String topicUuid){
		try {
			bbsReplyService.add(bbsReply,topicUuid);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param bbsReply
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody TbBbsReply bbsReply){
		try {
			bbsReplyService.update(bbsReply);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}	
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	public TbBbsReply findOne(String id){
		return bbsReplyService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(String [] ids){
		try {
			bbsReplyService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
		/**
	 * 查询+分页
	 * @param brand
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/search")
	public PageResult search(@RequestBody TbBbsReply bbsReply, int page, int rows  ){
		return bbsReplyService.findPage(bbsReply, page, rows);		
	}
	
	@Autowired
	private BbsService bbsService;
	
	/**
	 * 根据blog帖子主键查找主题及所有回复
	 * @param uuid
	 * @return
	 */
	@RequestMapping("/findTopAndReplys")
	public BlogReplys findTopAndReplys(String uuid) {
		return bbsService.findBlogReplys(uuid);
	}
}
