package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;

public class PurchaseDAO {

	public PurchaseVO findPurchase(int tranNo) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "select*from TRANSACTION where TRAN_NO = ? ";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, tranNo);
		
		ResultSet rs = stmt.executeQuery();
		
		PurchaseVO purchaseVO = null;
		
		
		while(rs.next()) {
			
		purchaseVO = new PurchaseVO();	
		ProductVO purchaseProd = new ProductVO();
		purchaseProd.setProdNo(rs.getInt("PROD_NO"));
		
		UserVO buyer = new UserVO();
		buyer.setUserId(rs.getString("BUYER_ID"));
		
		
		purchaseVO.setPaymentOption(rs.getString("PAYMENT_OPTION"));
		purchaseVO.setReceiverName(rs.getString("RECEIVER_NAME"));
		purchaseVO.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
		purchaseVO.setDivyAddr(rs.getString("DEMAILADDR"));
		
		System.out.println("리시버주소 : " +purchaseVO.getDivyAddr()); 
		purchaseVO.setDivyRequest(rs.getString("DLVY_REQUEST"));
		purchaseVO.setTranCode(rs.getString("TRAN_STATUS_CODE"));
		purchaseVO.setOrderDate(rs.getDate("ORDER_DATA"));
		purchaseVO.setDivyDate(rs.getString("DLVY_DATE"));
		purchaseVO.setTranNo(rs.getInt("TRAN_NO"));
		purchaseVO.setBuyer(buyer);
		purchaseVO.setPurchaseProd(purchaseProd);
		
	
		
	}
		System.out.println("확인용 :" +purchaseVO);
		con.close();
		return purchaseVO;
	}

	
	public HashMap<String, Object> getPurchaseList(SearchVO searchVO, String buyerId) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "select*from TRANSACTION"
											+ " WHERE buyer_id ='"+buyerId+"'";
		
		PreparedStatement stmt = 
				con.prepareStatement(	sql,
															ResultSet.TYPE_SCROLL_INSENSITIVE,
															ResultSet.CONCUR_UPDATABLE);
		
			ResultSet rs = stmt.executeQuery();

			rs.last();
			int total = rs.getRow();
			System.out.println("로우의 수:" + total);

			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("count", new Integer(total));
			
			rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1);
			System.out.println("searchVO.getPage():" + searchVO.getPage());
			System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());
			
			List<PurchaseVO> list = new ArrayList<PurchaseVO>();
			if (total > 0) {
				for (int i = 0; i < searchVO.getPageUnit(); i++) {
					PurchaseVO purchaseVO = new PurchaseVO();
					UserVO buyer = new UserVO();
					buyer.setUserId(rs.getString("BUYER_ID"));
					System.out.println("buyer : " + buyer);
					
					purchaseVO.setBuyer(buyer);
					purchaseVO.setReceiverName(rs.getString("RECEIVER_NAME"));
					purchaseVO.setReceiverPhone(rs.getString("RECEIVER_PHONE"));

					purchaseVO.setDivyRequest(rs.getString("DLVY_REQUEST"));
					purchaseVO.setTranCode(rs.getString("TRAN_STATUS_CODE"));

					purchaseVO.setDivyDate(rs.getString("DLVY_DATE"));
					purchaseVO.setTranNo(rs.getInt("TRAN_NO"));
					
					list.add(purchaseVO);
					if (!rs.next())
						break;
				}
			}
			
				System.out.println("list.size() : "+ list.size());
				map.put("list", list);
				System.out.println("map().size() : "+ map.size());
	
				con.close();
					
				return map;

		
	
	}
	
	public HashMap<String, Object> getSaleList(SearchVO searchVO) throws Exception {
		
	Connection con = DBUtil.getConnection();
		
		String sql = "select*from TRANSACTION";
		
		PreparedStatement stmt = 
				con.prepareStatement(	sql,
															ResultSet.TYPE_SCROLL_INSENSITIVE,
															ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery();

			rs.last();
			int total = rs.getRow();
			System.out.println("로우의 수:" + total);

			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("count", new Integer(total));
			
			rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1);
			System.out.println("searchVO.getPage():" + searchVO.getPage());
			System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());
			
			List<PurchaseVO> list = new ArrayList<PurchaseVO>();
			if (total > 0) {
				for (int i = 0; i < searchVO.getPageUnit(); i++) {
					PurchaseVO purchaseVO = new PurchaseVO();
					
					purchaseVO.setPaymentOption(rs.getString("PAYMENT_OTPION"));
					purchaseVO.setReceiverName(rs.getString("RECEIVER_NAME"));
					purchaseVO.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
					purchaseVO.setDivyAddr(rs.getString("DEMAIL_ADDR"));
					purchaseVO.setDivyRequest(rs.getString("DLVY_REQUEST"));
					purchaseVO.setTranCode(rs.getString("TRAN_STATUS_CODE"));
					purchaseVO.setOrderDate(rs.getDate("ORDER_DATE"));
					purchaseVO.setDivyDate(rs.getString("DLVY_DATE"));
					purchaseVO.setTranNo(rs.getInt("TRAN_NO"));
					
					list.add(purchaseVO);
					if (!rs.next())
						break;
				}
			}
			
				System.out.println("list.size() : "+ list.size());
				map.put("list", list);
				System.out.println("map().size() : "+ map.size());
	
				con.close();
					
				return map;

		}
	
		public void insertPurchase(PurchaseVO purchaseVO) throws Exception {
			
			
			
			Connection con = DBUtil.getConnection();
			
			String sql = "insert into TRANSACTION values (seq_product_prod_no.nextval,?,?,?,?,?,?,?,?,sysdate,?)";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			
			stmt.setInt(1, purchaseVO.getPurchaseProd().getProdNo());
			stmt.setString(2, purchaseVO.getBuyer().getUserId());
			stmt.setString(3, purchaseVO.getPaymentOption());
			stmt.setString(4, purchaseVO.getReceiverName());
			stmt.setString(5, purchaseVO.getReceiverPhone());
			stmt.setString(6, purchaseVO.getDivyAddr());
			stmt.setString(7,  purchaseVO.getDivyRequest());
			stmt.setString(8, purchaseVO.getTranCode());
			stmt.setString(9, purchaseVO.getDivyDate());
			
			stmt.executeUpdate();
		}
		public void updatePurchase(PurchaseVO purchaseVO) throws Exception {
			System.out.println("날아왔어요");
			System.out.println("두번쨰확인 :::" +purchaseVO);
			
			Connection con = DBUtil.getConnection();
		
			
			String sql =  "UPDATE TRANSACTION  set"
					+ " payment_option=?, receiver_name=?, receiver_phone=?, demailaddr=?, dlvy_request=?,dlvy_date=?"
					+ " where tran_no=?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			System.out.println("쿼리준비완료");
			
			System.out.println("sql :"+sql);
			
			
	
			
			stmt.setString(1, purchaseVO.getPaymentOption());
			stmt.setString(2, purchaseVO.getReceiverName());
			stmt.setString(3,  purchaseVO.getReceiverPhone());
			stmt.setString(4, purchaseVO.getDivyAddr());
			stmt.setString(5,  purchaseVO.getDivyRequest());
			stmt.setString(6,  purchaseVO.getDivyDate());
			stmt.setInt(7, purchaseVO.getTranNo());
		
			System.out.println("sql 2번쨰 확인 :" +sql);
			
			stmt.executeUpdate();
		
	
			
			
			
			
			System.out.println("dao 확인 : "+sql);
			
			
			con.close();
		}
		public void updateTranCode(PurchaseVO purchaseVO) throws Exception{
			
			Connection con = DBUtil.getConnection();
			String sql = "UPDATE TRANSATION set tran_no=?";
					
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, purchaseVO.getTranNo());
			
			stmt.executeUpdate();
			con.close();
		}
}
