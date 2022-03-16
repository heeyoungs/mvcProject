<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="weather.vo.Member"%>
<jsp:useBean id="member"
             scope="session"
             class="weather.vo.Member"/>
<div style="background-color: #00008b; color: #ffffff; height: 20px; padding: 5px;">
    MyTodays_MiniProject
    <span style="float: right;">
    <a style="color: white;"
       href="<%=request.getContextPath()%>/member/logout.do">로그아웃</a>
    </span>
</div>