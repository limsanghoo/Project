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

������ ���� ���Ű� �Ǿ����ϴ�.

<table border=1>
	<tr>
		<td>��ǰ��ȣ</td>
		<td><%=productVO.getProdNo() %></td>
		<td></td>
	</tr>
	<tr>
		<td>�����ھ��̵�</td>
		<td><%=userVO.getUserId() %></td>
		<td></td>
	</tr>
	<tr>
		<td>���Ź��</td>
		<td>
		


		
		���ݱ���
		
		
		
				
		</td>
		<td></td>
	</tr>
	<tr>
		<td>�������̸�</td>
		<td><%=userVO.getUserName() %></td>
		<td></td>
	</tr>
	<tr>
		<td>�����ڿ���ó</td>
		<td><%=userVO.getPhone() %></td>
		<td></td>
	</tr>
	<tr>
		<td>�������ּ�</td>
		<td><%=userVO.getAddr() %></td>
		<td></td>
	</tr>
		<tr>
		<td>���ſ�û����</td>
		<td><%=productVO.getProdDetail() %></td>
		<td></td>
	</tr>
	<tr>
		<td>����������</td>
		<td><%=purchaseVO.getOrderDate() %></td>
		<td></td>
	</tr>
</table>
</form>

</body>
</html>