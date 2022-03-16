<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원 등록</title>
</head>
<body>
<h1>회원 등록</h1>
<form action="/member/add.do" method="post">
    아이디: <input type="text" name="id"><br>
    암호: <input type="password" name="password"><br>
    이메일: <input type="text" name="email"><br>
    이름: <input type="text" name="name"><br>
    <input type="submit" value="추가">
    <input type="button" value="취소" onclick="history.go(-1)">
</form>
</body>
</html>