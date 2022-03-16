<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>날씨정보추가</title>
</head>
<body>
<h1>날씨정보 추가</h1>
<form action="/member/weatherInfoAdd.do" method="post">
    도시: <input type="text" name="bigLocation"><br>
    구:  <input type="text" name="middleLocation"><br>
    동: <input type="text" name="smallLocation"><br>
    <input type="submit" value="추가">
    <input type="button" value="취소" onclick="history.go(-1)">
</form>
</body>
</html>
