<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ page import="java.util.*"  %>
<%@ page import="com.model2.mvc.service.product.vo.*" %>
<%@ page import="com.model2.mvc.service.user.vo.*" %>
<%@ page import="com.model2.mvc.common.*" %>
<%@ page import="com.model2.mvc.service.purchase.vo.*" %>

    
  <%
	ProductVO productVO = (ProductVO)request.getAttribute("productVO");
	UserVO userVO = (UserVO)session.getAttribute("user");
	String menu = request.getParameter("menu");
	PurchaseVO purchaseVO =(PurchaseVO)request.getAttribute("purchaseVO");
%>	




<html>
<head>
<title>Insert title here</title>
</head>

<body>

<form name="updatePurchase" action="/updatePurchaseView.do?tranNo=0" method="post">

다음과 같이 구매가 되었습니다.

<table border=1>
	<tr>
		<td>물품번호</td>
		<td><%=productVO.getProdNo() %></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자아이디</td>
		<td><%=userVO.getUserId() %></td>
		<td></td>
	</tr>
	<tr>
		<td>구매방법</td>
		<td>
		


		
		현금구매
		
		
		
				
		</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자이름</td>
		<td><%=userVO.getUserName() %></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자연락처</td>
		<td><%=userVO.getPhone() %></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자주소</td>
		<td><%=userVO.getAddr() %></td>
		<td></td>
	</tr>
		<tr>
		<td>구매요청사항</td>
		<td><%=productVO.getProdDetail() %></td>
		<td></td>
	</tr>
	<tr>
		<td>배송희망일자</td>
		<td><%=purchaseVO.getOrderDate() %></td>
		<td></td>
	</tr>
</table>
</form>

</body>
</html>