<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.member.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
MemberVO memberVO = (MemberVO) request.getAttribute("memberVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>會員資料 - listOneMember.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>會員資料 - listOneMember.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>會員編號</th>
		<th>帳號</th>
		<th>密碼</th>
		<th>電子郵件</th>
		<th>會員狀態</th>
		<th>註冊時間</th>
		<th>名字</th>
		<th>生日</th>
		<th>性別</th>
		<th>手機號碼</th>
		<th>市</th>
		<th>區</th>
		<th>地址</th>
		<th>會員照片</th>
	</tr>
	<tr>
		<td><%=memberVO.getMemid()%></td>
		<td><%=memberVO.getAc()%></td>
		<td><%=memberVO.getPw()%></td>
		<td><%=memberVO.getEmail()%></td>
		<td><%=memberVO.getStatus()%></td>
		<td><%=memberVO.getRegistertime()%></td>
		<td><%=memberVO.getName()%></td>
		<td><%=memberVO.getBirth()%></td>
		<td><%=memberVO.getSex()%></td>
		<td><%=memberVO.getPhone()%></td>
		<td><%=memberVO.getCity()%></td>
		<td><%=memberVO.getDisc()%></td>
		<td><%=memberVO.getAddress()%></td>
		<td><%=memberVO.getImg()%></td>
	</tr>
</table>

</body>
</html>