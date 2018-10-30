package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class UpdateTranCodeByProdAction extends Action {
	
	public String execute(HttpServletRequest request, 
																HttpServletResponse response) throws Exception {
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		System.out.println("prodNo : "+prodNo);
		
		String tranCode = request.getParameter("tranCode");
		System.out.println("trancode : "+tranCode);
		
		PurchaseService pService = new PurchaseServiceImpl();
		PurchaseVO purchaseVO = pService.getPurchase2(prodNo);
		
		System.out.println("trancode2 : " +tranCode);
		System.out.println("purchaseVO : "+purchaseVO);
		
		purchaseVO.setTranCode(tranCode);
		request.setAttribute("vo",purchaseVO);
		
		System.out.println("purchaseVO : " +purchaseVO);
		pService.updateTranCode(purchaseVO);
		
		return "forward:/updateTranCode.do";
		
		
	}

}
