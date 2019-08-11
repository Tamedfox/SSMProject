package com.cf.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cf.pojo.TbBbs;
import com.cf.pojo.group.BlogReplys;
import com.cf.pojo.result.PageResult;
import com.cf.pojo.result.Result;
import com.cf.service.BbsService;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/bbs")
public class BbsController {

	@Autowired
	private BbsService bbsService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbBbs> findAll(){			
		return bbsService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return bbsService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param bbs
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody TbBbs bbs){
		try {
			bbsService.add(bbs);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param bbs
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody TbBbs bbs){
		try {
			bbsService.update(bbs);
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
	public TbBbs findOne(String id){
		return bbsService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(String [] ids){
		try {
			bbsService.delete(ids);
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
	public PageResult search(@RequestBody TbBbs bbs, int page, int rows  ){
		return bbsService.findPage(bbs, page, rows);		
	}

}
