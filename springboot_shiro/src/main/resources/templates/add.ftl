<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form action="" method="post">
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
            <td><input type="submit" value="新增"></td>
            <td></td>
        </tr>
    </table>
</form>
</body>
</html>