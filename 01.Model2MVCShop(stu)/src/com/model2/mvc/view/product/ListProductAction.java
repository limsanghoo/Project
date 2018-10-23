package com.model2.mvc.view.product;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class ListProductAction extends Action{

	public String execute(HttpServletRequest req,
												HttpServletResponse res) throws Exception {
		SearchVO searchVO = new SearchVO();
		
		int page=1;
		if(req.getParameter("page") != null)
			page=Integer.parseInt(req.getParameter("page"));
		
			searchVO.setPage(page);
			searchVO.setSearchCondition(req.getParameter("searchCondition"));
			searchVO.setSearchKeyword(req.getParameter("searchKeyword"));
			
			String pageUnit = getServletContext().getInitParameter("pageSize");
			searchVO.setPageUnit(Integer.parseInt(pageUnit));
			
			ProductService service = new ProductServiceImpl();
			HashMap<String,Object> map=service.getProductList(searchVO);
			
			req.setAttribute("map", map);
			req.setAttribute("searchVO", searchVO);
			
			return "forward:/product/listProduct.jsp";
	}

	
}
