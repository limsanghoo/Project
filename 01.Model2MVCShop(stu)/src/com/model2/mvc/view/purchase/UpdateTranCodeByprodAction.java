package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class UpdateTranCodeByprodAction extends Action {
	
	public String execute(HttpServletRequest request, 
																HttpServletResponse response) throws Exception {
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		String tranCode = request.getParameter("tranCode");
		
		PurchaseService pService = new PurchaseServiceImpl();
		PurchaseVO purchaseVO = pService.getPurchase2(prodNo);
		
		purchaseVO.setTranCode(tranCode);
		pService.updateTranCode(purchaseVO);
		
		return "redirect:/listProduct.do?menu=manage";
		
		
	}

}
