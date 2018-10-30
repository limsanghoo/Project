package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.user.vo.UserVO;


public class ProductDAO {
	
	public ProductDAO() {		
	}
	
	public void insertProduct(ProductVO productVO) throws Exception { 
		
		Connection con = DBUtil.getConnection();
		
		String sql = "insert into PRODUCT values (seq_product_prod_no.nextval,?,?,TO_CHAR(TO_DATE(?,'YYYY-MM-DD'),'YYYYMMDD'),?,?,sysdate)";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setString(1, productVO.getProdName());
		stmt.setString(2, productVO.getProdDetail());
		stmt.setString(3, productVO.getManuDate());
		stmt.setInt(4, productVO.getPrice());
		stmt.setString(5, productVO.getFileName());
	

		stmt.executeUpdate();
		
		con.close();
		
	}
	
	public ProductVO findProduct(int prodNo) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "select*from PRODUCT where PROD_NO = ?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, prodNo);
		
		ResultSet rs = stmt.executeQuery();
		
		ProductVO productVO = null;
		
		while(rs.next()) {
		productVO = new ProductVO();
		productVO.setFileName(rs.getString("IMAGE_FILE"));
		productVO.setManuDate(rs.getString("MANUFACTURE_DAY"));
		productVO.setPrice(rs.getInt("PRICE"));
		productVO.setProdDetail(rs.getString("PROD_DETAIL"));
		productVO.setProdName(rs.getString("PROD_NAME"));
		productVO.setProdNo(rs.getInt("PROD_NO"));
		productVO.setRegDate(rs.getDate("REG_DATE"));
		
		}
		con.close();
		
		return productVO;
	}
	public HashMap<String, Object> getProductList(SearchVO searchVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT p.prod_no,p.prod_name,p.prod_detail,p.manufacture_day,p.price,p.image_file,p.reg_date,NVL(t.tran_status_code, '0') tranCode"
				+ " FROM product p,transaction t"
				+ " WHERE p.prod_no=t.prod_no(+)";
		
		if (searchVO.getSearchCondition() != null) {
			if (searchVO.getSearchCondition().equals("0")) {
				sql += " where P.PROD_NO LIKE '%" + searchVO.getSearchKeyword()
						+ "%'";
			} else if (searchVO.getSearchCondition().equals("1")) {
				sql += " where PROD_NAME LIKE '%" + searchVO.getSearchKeyword()
						+ "%'";
			} else if (searchVO.getSearchCondition().equals("2")) {
				sql += " where PRICE LIKE '%" + searchVO.getSearchKeyword()
				+ "%'";
			}
		}
		sql += " order by PROD_NAME";
		System.out.println("sql확인 :"+sql);
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
			
			ArrayList<ProductVO> list = new ArrayList<ProductVO>();
			if (total > 0) {
				for (int i = 0; i < searchVO.getPageUnit(); i++) {
					ProductVO vo = new ProductVO();
					vo.setFileName(rs.getString("IMAGE_FILE"));
					vo.setManuDate(rs.getString("MANUFACTURE_DAY"));
					vo.setPrice(rs.getInt("PRICE"));
					vo.setProdDetail(rs.getString("PROD_DETAIL"));
					vo.setProdName(rs.getString("PROD_NAME"));
					vo.setProdNo(rs.getInt("PROD_NO"));
					vo.setRegDate(rs.getDate("REG_DATE"));
					
					System.out.println("========"+vo);
					vo.setProTranCode(rs.getString("tranCode"));
					
					

					list.add(vo);
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
	
			public void updateProduct(ProductVO productVO) throws Exception {
				
			Connection con = DBUtil.getConnection();
			
			String sql = "update PRODUCT set PROD_NAME=?,PROD_DETAIL=?,MANUFACTURE_DAY=?,PRICE=?,IMAGE_FILE=? where PROD_NO=?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, productVO.getProdName());
			stmt.setString(2, productVO.getProdDetail());
			stmt.setString(3, productVO.getManuDate());
			stmt.setInt(4, productVO.getPrice());
			stmt.setString(5, productVO.getFileName());
			stmt.setInt(6, productVO.getProdNo());
			stmt.executeUpdate();
			
			con.close();
			}
}