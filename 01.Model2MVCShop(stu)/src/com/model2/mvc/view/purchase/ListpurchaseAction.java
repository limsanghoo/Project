package com.model2.mvc.view.purchase;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;

	public class ListpurchaseAction extends Action {
		
		public String execute(	HttpServletRequest request,
				HttpServletResponse response) throws Exception {
    
		SearchVO searchVO=new SearchVO();
		
		int page=1;
		if(request.getParameter("page") != null)
			page=Integer.parseInt(request.getParameter("page"));
		
		searchVO.setPage(page);
	
		String pageUnit=getServletContext().getInitParameter("pageSize");
		searchVO.setPageUnit(Integer.parseInt(pageUnit));
		
		String buyerId = request.getParameter("buyerId");
		System.out.println("buyerId : " + buyerId);
		UserVO userVO = new UserVO();
		
		UserService userService = new UserServiceImpl();
		userVO = userService.getUser(buyerId);
		
		PurchaseService service=new PurchaseServiceImpl();
		HashMap<String,Object> map=service.getPurchaseList(searchVO, buyerId);
	
		request.setAttribute("map", map);
		request.setAttribute("searchVO", searchVO);
		
		return  "forward:/purchase/listPurchasejsp";
	}
}