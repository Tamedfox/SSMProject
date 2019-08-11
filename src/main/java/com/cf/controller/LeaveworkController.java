package com.cf.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cf.pojo.TbLeavework;
import com.cf.pojo.result.PageResult;
import com.cf.pojo.result.Result;
import com.cf.service.LeaveworkService;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/leavework")
public class LeaveworkController {

	@Autowired
	private LeaveworkService leaveworkService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbLeavework> findAll(){			
		return leaveworkService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return leaveworkService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param leavework
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody TbLeavework leavework){
		try {
			leaveworkService.add(leavework);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param leavework
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody TbLeavework leavework){
		try {
			leaveworkService.update(leavework);
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
	public TbLeavework findOne(Long id){
		return leaveworkService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Long [] ids){
		try {
			leaveworkService.delete(ids);
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
	public PageResult search(@RequestBody TbLeavework leavework, int page, int rows  ){
		return leaveworkService.findPage(leavework, page, rows);		
	}
	
	/**
	 * 更新员工状态
	 * @param ids 员工群组
	 * @param status 目标状态 
	 * @return
	 */
	@RequestMapping("/updateStatus")
	public Result updateStatus(Long[] ids, String status) {
		try {
			leaveworkService.updateStatus(ids, status);
			return new Result(false, "更新成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "更新失败");
		}
	}
	
	@RequestMapping("/findByStatus")
	public List<TbLeavework> findByStatus(String status){
		return leaveworkService.findByStatus(status);
	}
}
