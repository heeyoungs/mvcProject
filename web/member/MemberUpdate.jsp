<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원정보</title>
</head>
<body>
<h1>회원정보</h1>
<form action="/member/update.do" method="post">
    회원 번호:<td><input type="text" name="no" value="${sessionScope.member.no}" readonly><br>
    이름: <input type="text" name="name" value="${sessionScope.member.name}"><br>
    아이디: <input type="text" name="id" value="${sessionScope.member.id}"><br>
    비밀번호: <input type="password" name="password"><br>

    <input type="submit" value="저장">
    <input type="button" value="삭제" onclick="location.href='/member/delete.do?no=${sessionScope.member.no}'">
    <input type="button" value="취소" onclick="history.go(-1)">
</form>
</body>
</html>
