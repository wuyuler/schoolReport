<%@page import="utils.StuDBHelper"%>
<%@page import="entity.ScoreOfStudent"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>成绩管理系统</h1>
<div>
<%ArrayList<ScoreOfStudent> list=StuDBHelper.executeSQL("select*from schoolreport;"); %>
<table border="8">

<tr>
<th>学号</th>
<th>姓名</th>
<th>班级</th>
<th>性别</th>
<%for(int i =0;i<8;i++){ %>
<%for(int j=0;j<ScoreOfStudent.eachTermSubjectNum[i];j++){ %>
<th><%=ScoreOfStudent.subject[i][j] %></th>
<%}} %>
</tr>
<%for(ScoreOfStudent stu:list){ %>
<tr>
<td><%=stu.get_id() %></td>
<td><%=stu.getName() %></td>
<td><%=stu.getClasses() %></td>
<td><%=stu.getSex() %></td>
<%for(int i=0;i<8;i++){ %>
<%for(int j=0;j<ScoreOfStudent.eachTermSubjectNum[i];j++){ %>
<td><%=stu.scores[i][j] %></td>
<%}} %>


</tr>
<%} %>
</table>

</div>
</body>
</html>