<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>회원 정보</title>
</head>
<body>
<jsp:include page="/decorate/Header.jsp"/>
<h1>회원 정보</h1>
<c:out value="${sessionScope.member.name}님 안녕하세요!"/><br>
<c:choose>
    <c:when test="${sessionScope.member.locationCode eq 0}">
        <a href="/member/weatherInfoAdd.do?no=${sessionScope.member.no}">[날씨 정보추가]</a><br>
    </c:when>
    <c:otherwise>
        <h2>날씨 정보</h2>
        <c:out value="강수형태:${sessionScope.weather.PTY} <- 없음(0),비(1),비/눈(2),눈(3)"/><br>
        <c:out value="습도:${sessionScope.weather.REH}"/><br>
        <c:out value="1시간 강수량:${sessionScope.weather.RN1}"/><br>
        <c:out value="기온:${sessionScope.weather.TH1}"/><br>
        <c:out value="동서바람성분:${sessionScope.weather.UUU}"/><br>
        <c:out value="남북바람성분:${sessionScope.weather.VVV}"/><br>
        <c:out value="풍향:${sessionScope.weather.VEC}"/><br>
        <c:out value="풍속:${sessionScope.weather.WSD}"/><br>
    </c:otherwise>
</c:choose>
<a href="/member/update.do?no=${sessionScope.member.no}">[개인 정보 수정]</a>
<a href="/member/delete.do?no=${sessionScope.member.no}">[회원 탈퇴]</a>
<jsp:include page="/decorate/Tail.jsp"/>
</body>
</html>
