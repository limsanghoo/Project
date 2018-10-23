package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;


public class UpdateProductAction extends Action {
	
	public String execute(	HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ProductVO productVO=new ProductVO();
		
		int prodNo= Integer.parseInt(request.getParameter("prodNo"));
		
		productVO.setProdNo(prodNo);
		productVO.setProdName(request.getParameter("prodName"));
		productVO.setProdDetail(request.getParameter("prodDetail"));
		productVO.setManuDate(request.getParameter("manaDate"));
		productVO.setPrice(Integer.parseInt(request.getParameter("price")));
		productVO.setFileName(request.getParameter("fileName"));
		
		
		
		ProductService service=new ProductServiceImpl();
		service.updateProduct(productVO);
		
		
		
		return "redirect:/getProduct.do?prodNo="+prodNo+"&menu="+request.getParameter("menu");
		}
	}
