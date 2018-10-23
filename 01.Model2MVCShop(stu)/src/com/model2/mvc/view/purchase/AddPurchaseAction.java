package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;


public class AddPurchaseAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		PurchaseVO purchaseVO=new PurchaseVO();
		ProductVO productVO = new ProductVO();
		
		
		System.out.println("purchaseVO : "+purchaseVO);
		System.out.println("productVO : " +productVO);
		
		
		productVO.setProdNo(Integer.parseInt(request.getParameter("prodNo") ) );
		uVO.setUserId(request.getParameter("userId"));
		purchaseVO.setPaymentOption(request.getParameter("paymentOption"));
		purchaseVO.setReceiverName(request.getParameter("receiverName"));
		purchaseVO.setReceiverPhone(request.getParameter("receiverPhone"));
		purchaseVO.setDivyAddr(request.getParameter("divyDate"));
		purchaseVO.setDivyRequest(request.getParameter("divyRequest"));
		purchaseVO.setTranCode(request.getParameter("tranCode"));
		//purchaseVO.setOrderDate(request.getParameter("orderDate"));
		purchaseVO.setDivyDate(request.getParameter("divyDate"));
		
		
		PurchaseService service=new PurchaseServiceImpl();
		service.addPurchase(purchaseVO);
		System.out.println("purchaseVO : "+purchaseVO);
		return "forward:/purchase/addPurchase.jsp";
	}
}