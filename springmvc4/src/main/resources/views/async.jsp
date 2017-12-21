<%--
  Created by IntelliJ IDEA.
  User: yaohongwu
  Date: 2017/12/21
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<script type="text/javascript" src="assets/js/jquery.js"></script>
<script>
    deferred();  //页面一打开就向后台发送请求
    function deferred() {
        $.get('defer',function (data) {
            console.log(data);
            deferred();  //再控制台输出数据 完成后再次发送请求
        })
    }
    /*
        jquery的ajax请求 无浏览器兼容问题

     */
</script>
</body>
</html>
