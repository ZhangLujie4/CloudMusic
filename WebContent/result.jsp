<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>查询结果</title>
</head>
<body>
        歌曲名：<input type="text" value="${songname}"><br />
        表演者：<input type="text" value="${artistname}"><br />
	<textarea rows="4" cols="60">${lianjie}</textarea>
	<textarea rows="100" cols="80">${result}</textarea>
</body>
</html>