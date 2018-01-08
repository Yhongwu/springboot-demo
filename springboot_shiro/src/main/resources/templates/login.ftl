<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>login</title>
</head>
<body>
    <form action="submit" method="post">
        <table>
            <tr>
                <td>用户：</td>
                <td><input type="text" name="username"></td>
            </tr>
            <tr>
                <td>密码：</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr><td></td><td style="color: red">${msg!}</td></tr>
            <tr>
                <td><P><input type="checkbox" name="rememberMe" />记住我</P></td>
                <td><input type="submit" value="登录"></td>
            </tr>
        </table>
    </form>

</body>
</html>