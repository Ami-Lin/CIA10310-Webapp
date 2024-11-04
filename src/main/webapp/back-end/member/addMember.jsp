<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<% //��com.emp.controller.EmpServlet.java��238��s�Jreq��empVO���� (������J�榡�����~�ɪ�empVO����)
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>
--<%= memberVO==null %>--${memberVO.city}--<!-- line 100���n�� -->
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�|����Ʒs�W - addMember.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>���u��Ʒs�W - addMember.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��Ʒs�W:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="member.do" name="form1">
<table>
	
	<tr>
		<td>�|���b��:</td>
		<td><input type="TEXT" name="ac" value="<%= (memberVO==null)? "he01314905" : memberVO.getAc()%>" size="45"/></td>
	</tr>
	<tr>
		<td>�|���K�X:</td>
		<td><input type="TEXT" name="pw" value="<%= (memberVO==null)? "wasibo1" : memberVO.getPw()%>" size="45"/></td>
	</tr>
	<tr>
		<td>�q�l�l��:</td>
		<td><input type="EMAIL" name="email" value="<%= (memberVO==null)? "he01314906@gmail.com" : memberVO.getEmail()%>" size="45"/></td>
	</tr>
	<tr>
		<td>�|�����A:</td>
		<td><input type="text" name="status" value="<%= (memberVO==null)? "0" : memberVO.getStatus()%>" size="45" disabled/></td>
	</tr>
	<tr>
		<td>���U�ɶ�:</td>
		<td><input name="registertime" id="f_date1" type="date" size="45" value="2024-11-03" disabled /></td>
	</tr>
<%-- 	value="<%= memberVO.getRegistertime()%>" --%>
	<tr>
		<td>�m�W:</td>
		<td><input type="TEXT" name="name" value="<%= (memberVO==null)? "���f��" : memberVO.getName()%>" size="45"/></td>
	</tr>
	
	<tr>
		<td>�ͤ�:</td>
		<td><input name="birth" id="f_date1" type="date" size="45" value="1999-02-23" /></td>
	</tr>
<%-- 	 value="<%= memberVO.getBirth()%>"  --%>
	<tr>
		<td>�ʧO:</td>
		<td><input name="sex" type="text" value="<%= (memberVO==null)? "�k" : "�k" %>" size="45"></td>
	</tr>
	<tr>
		<td>�q��:</td>
		<td><input type="TEXT" name="phone" value="<%= (memberVO==null)? "0905787040" : memberVO.getPhone()%>" size="45"/></td>
	</tr>
	<tr>
		<td>��:</td>
		<td><input type="TEXT" name="city" value="<%= (memberVO==null)? "�s�_��" : memberVO.getCity()%>" size="45"/></td>
	</tr>
	<tr>
		<td>��:</td>
		<td><input type="TEXT" name="disc" value="<%= (memberVO==null)? "�g����" : memberVO.getDisc()%>" size="45"/></td>
	</tr>
	<tr>
		<td>�a�}:</td>
		<td><input type="TEXT" name="address" value="<%= (memberVO==null)? "�ǩ����@�q" : memberVO.getAddress()%>" size="45"/></td>
	</tr>
	<tr>
		<td>�|���Ӥ�:</td>
		<td><input type="FILE" name="img"></td>
	</tr>

<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>����:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)? 'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>

</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<% 
  java.sql.Date birth = null;
  try {
	    birth = memberVO.getBirth();
   } catch (Exception e) {
	    birth = new java.sql.Date(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=birth%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
           //startDate:	            '2017/07/10',  // �_�l��
           //minDate:               '-1970-01-01', // �h������(���t)���e
           //maxDate:               '+1970-01-01'  // �h������(���t)����
        });
        
        
   
        // ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

        //      1.�H�U���Y�@�Ѥ��e������L�k���
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.�H�U���Y�@�Ѥ��᪺����L�k���
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>