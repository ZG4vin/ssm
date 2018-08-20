<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link type="text/css" rel="styleSheet"  href="${basePath}/css/bootstrap.css" />
    <link type="text/css" rel="stylesheet" href="${basePath}/css/error.css"/>
    <link rel="shortcut icon" href="${basePath}/favicon.ico"/>
    <script src="${basePath}/js/common/jquery-3.3.1.js"></script>
    <script src="${basePath}/js/common/bootstrap.js"></script>
    <script src="${basePath}/js/common/jquery.validate.js"></script>
    <script src="${basePath}/js/common/messages_zh.js"></script>
    <script>
        $(document).ready(function(){
            var UserError='${pageCode.msg}';
            if (UserError!=''){
                $("#myModal").modal("toggle");
            }


            $("#loginForm").validate({
                rules:{
                    username:"required",
                    password:"required"
                },
                messages:{
                    username:"请输入用户名",
                    password:"请输入密码"
                }
            });


        });
    </script>
</head>
<body>
    <form action="/login/toIndex" method="post" id="loginForm">
        用户名:<input type="text" name="username" id="username" value="1"><br/>
        密码:<input type="password" name="password" id="password" value="1"><br/>
        <input type="submit" class="btn btn-primary" value="登录"/>
    </form>



    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">错误</h4>
                </div>
                <div class="modal-body">${pageCode.msg}</div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>




</body>
</html>
