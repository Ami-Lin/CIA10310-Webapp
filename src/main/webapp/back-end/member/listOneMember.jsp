<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.member.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
MemberVO memberVO = (MemberVO) request.getAttribute("memberVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>�|����� - listOneMember.jsp</title>

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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�|����� - listOneMember.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�|���s��</th>
		<th>�b��</th>
		<th>�K�X</th>
		<th>�q�l�l��</th>
		<th>�|�����A</th>
		<th>���U�ɶ�</th>
		<th>�W�r</th>
		<th>�ͤ�</th>
		<th>�ʧO</th>
		<th>������X</th>
		<th>��</th>
		<th>��</th>
		<th>�a�}</th>
		<th>�|���Ӥ�</th>
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