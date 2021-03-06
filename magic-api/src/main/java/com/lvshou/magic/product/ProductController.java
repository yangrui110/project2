package com.lvshou.magic.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lvshou.magic.exception.ResultEnum;
import com.lvshou.magic.product.service.ProductService;
import com.lvshou.magic.product.vo.ProductVo;
import com.lvshou.magic.result.Result;

@Controller
@RequestMapping("product")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@ResponseBody
	@GetMapping("findAll")
	public Result findAll(@RequestParam(value="page",defaultValue="1",required=false) int page,
			@RequestParam(value="rows",defaultValue="1000000",required=false) int rows) {
		
		return new Result<>(ResultEnum.OK, productService.findAll(page-1, rows));
	}
	
	@ResponseBody
	@PostMapping("insert")
	public Result insert(ProductVo productVo) {
		return new Result<>(ResultEnum.OK, productService.insert(productVo));
	}
	@ResponseBody
	@PutMapping("update")
	public Result update(ProductVo productVo) {
		return new Result<>(ResultEnum.OK, productService.update(productVo));
	}
	
	@ResponseBody
	@DeleteMapping("delete/{id}")
	public Result delete(@PathVariable("id") String id) {
		productService.delete(id);
		return new Result<>(ResultEnum.OK);
	}
	

	@ResponseBody
	@GetMapping("findById")
	public Result findById(@RequestParam("id")String id) {
		List list=new ArrayList<>();
				list.add(productService.findById(id));
		return new Result<>(ResultEnum.OK,list);
	}
}
