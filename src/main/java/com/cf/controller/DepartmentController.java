package com.cf.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cf.pojo.TbDepartment;
import com.cf.pojo.result.PageResult;
import com.cf.pojo.result.Result;
import com.cf.service.DepartmentService;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbDepartment> findAll(){			
		return departmentService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return departmentService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param department
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody TbDepartment department){
		try {
			departmentService.add(department);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param department
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody TbDepartment department){
		try {
			departmentService.update(department);
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
	public TbDepartment findOne(Long id){
		return departmentService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Long [] ids){
		try {
			departmentService.delete(ids);
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
	public PageResult search(@RequestBody TbDepartment department, int page, int rows  ){
		return departmentService.findPage(department, page, rows);		
	}
	
}
