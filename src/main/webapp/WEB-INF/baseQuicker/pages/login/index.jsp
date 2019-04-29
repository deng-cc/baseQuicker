<%@ page pageEncoding="UTF-8" %>
<html>
<head>
    <title>请登录</title>
</head>
<body>
    <h1>请先登录</h1>
    <form action="/login/login" method="post">
        账户：<input type="text" name="username" />
        密码：<input type="password" name="password" />
        <input type="submit" value="submit">
    </form>
</body>
</html>