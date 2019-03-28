package com.tstore.controller.itemcategory;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tstore.service.itemcategory.IItemCategoryService;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * todo:用户实体Controller控制器类 ItemCategoryController<br/>
 * 作者:xiaoteng<br/>
 * 创建时间：2019年01月27日 20:52:19 <br/>
 * 
 * @version 1.0.0<br/>
 *
 */
@RestController
public class ItemCategoryController {
	@Autowired
	private IItemCategoryService itemCategory;

	/**
	 * (查询分类列表信息)<br/>
	 * 方法名：findItemgorys<br/>
	 * 创建人：xiaoteng <br/>
	 * 时间：2019年1月28日-上午1:30:02 <br/>
	 * 手机:18820148139<br/>
	 * @return ResponseEntity<List<Map<String,Object>>><br/>
	 * @exception <br/>
	 * @since 1.0.0<br/>
	 */
	@GetMapping("/item/findItemgorys")
	@CrossOrigin
	@ApiOperation(value = "查询一级商品", notes = "查询一级商")
	// @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType =
	// "long", paramType = "path")
	public ResponseEntity<List<Map<String, Object>>> findItemgorys() {
		List<Map<String, Object>> roots = itemCategory.findItemgorys();
		return ResponseEntity.status(HttpStatus.OK).body(roots);
	}


}
