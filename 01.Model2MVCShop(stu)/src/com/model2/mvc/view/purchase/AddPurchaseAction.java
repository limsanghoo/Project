package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;


public class AddPurchaseAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		
		String buyerId = request.getParameter("buyerId");
		System.out.println("buyerId : " + buyerId);
		int prodNo = Integer.parseInt(request.getParameter("prodNo") );
		System.out.println("prodNo : " +prodNo);
		
		UserVO userVO = new UserVO();
		
		UserService userService = new UserServiceImpl();
		userVO = userService.getUser(buyerId);
		
		ProductService productService = new ProductServiceImpl();
		
		ProductVO productVO = new ProductVO();
		productVO = productService.getProduct(prodNo);
		
		
		
		PurchaseVO purchaseVO=new PurchaseVO();
		
		


		
		
		
		
	
		
		//System.out.println("purchaseVO : "+purchaseVO);
		//System.out.println("productVO : " +productVO);
		//System.out.println("============±¸ºÐÀÚ=================");
		
		purchaseVO.setPurchaseProd(productVO);
		purchaseVO.setBuyer(userVO);
		purchaseVO.setPaymentOption(request.getParameter("paymentOption"));
		purchaseVO.setReceiverName(request.getParameter("receiverName"));
		purchaseVO.setReceiverPhone(request.getParameter("receiverPhone"));
		purchaseVO.setDivyAddr(request.getParameter("receiverAddr"));
		purchaseVO.setDivyRequest(request.getParameter("receiverRequest"));
		purchaseVO.setDivyDate(request.getParameter("receiverDate"));
		purchaseVO.setTranCode("1");
		
		
		System.out.println("===========================");
		System.out.println("purchaseVO : "+purchaseVO);
		
		
		PurchaseService service=new PurchaseServiceImpl();
		service.addPurchase(purchaseVO);
		request.setAttribute("productVO", productVO);
		request.setAttribute("userVO", userVO);
		request.setAttribute("purchaseVO", purchaseVO);
		
		//System.out.println("purchaseVO : "+purchaseVO);
		return "forward:/purchase/addPurchase.jsp";
	}
}