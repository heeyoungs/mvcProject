<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>로그인</title>
</head>
<body>
<h2>사용자 로그인</h2>
<form action="/member/login.do" method="post">
    아이디: <input type="text" name="id"><br>
    암호: <input type="password" name="password"><br>
    <input type="submit" value="로그인">
</form>
<p><a href="/member/add.do">신규 회원</a></p>
</form>
</body>
</html>
