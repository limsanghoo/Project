package com.model2.mvc.service.purchase.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;

import junit.framework.Assert;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/commonservice.xml"})
public class PurchaseServiceTest {
	
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	
	@Test
	public void testAddPurchase() throws Exception {
		
	Purchase purchase = new Purchase();	
	User user = new User();
	Product product = new Product();
	
	product.setProdName("휴대용선풍기");
	user.setUserId("user02");
	
	//purchase.setTranNo(10050);

	purchase.setBuyer(user);
	purchase.setPaymentOption("2");
	purchase.setPurchaseProd(product);
	purchase.setReceiverName("임상후");
	purchase.setReceiverPhone("010-1234-5678");
	purchase.setDivyAddr("서울숲");
	purchase.setDivyRequest("문앞에");
	purchase.setTranCode("0");
	purchase.setDivyDate("2018-11-11");
	
	purchaseService.addPurchase(purchase);
	
	Assert.assertEquals("2", purchase.getPaymentOption());
	Assert.assertEquals("임상후", purchase.getReceiverName());
	Assert.assertEquals("010-1234-5678", purchase.getReceiverPhone());
	Assert.assertEquals("서울숲", purchase.getDivyAddr());
	Assert.assertEquals("문앞에", purchase.getDivyRequest());
	Assert.assertEquals("0", purchase.getTranCode());
	Assert.assertEquals("2018-11-11", purchase.getDivyDate());
	}
}
