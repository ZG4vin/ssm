<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link type="text/css" rel="styleSheet" href="${basePath}/css/bootstrap.css" />
    <link type="text/css" rel="stylesheet" href="${basePath}/css/zTreeStyle.css"/>
    <link type="text/css" rel="stylesheet" href="${basePath}/css/rmenu.css"/>
    <link type="text/css" rel="stylesheet" href="${basePath}/css/menu.css"/>
    <link type="text/css" rel="stylesheet" href="${basePath}/css/demo.css">
    <link rel="shortcut icon" href="${basePath}/favicon.ico"/>
    <script src="${basePath}/js/common/jquery-3.3.1.js"></script>
    <script src="${basePath}/js/common/bootstrap.js"></script>
    <script src="${basePath}/js/common/echarts.js"></script>
    <script src="${basePath}/js/common/jquery.ztree.all.js"></script>
    <script src="${basePath}/js/contant/menu.js"></script>
    <script>
        $(document).ready(function(){
            $("#btnEcharts").click(function(){

                var cityName=$("#cityNameByWeather").val();
                var myChart = echarts.init(document.getElementById('reportDiv'));
                $.ajax({
                    url: "/index/showEcharts/"+cityName,
                    success: function (result) {
                        myChart.clear();
                        var option = {
                            xAxis: {
                                type: 'category'
                            },
                            yAxis: {
                                type: 'value'
                            }
                        };
                        $.extend(true,option,result);
                        myChart.setOption(option);
                    }
                });
            });

            $("#btnTree").click(function () {
                initTree();
            });
            
            $("#btnImg").click(function () {
                $("#ImgModel").modal("toggle");
            });

            $("#btnRemoveImg").click(function () {
                $.ajax({
                    url:"/index/removeCache",
                    type:"POST",
                    data:{
                      "_method":"DELETE"
                    },
                    success:function (result) {
                        if (result){
                            alert("清理成功");
                        }
                    }
                })
            });
        });


        function initTree() {
            $.ajax({
                url : "/index/initTree",
                success : function(data) {
                    var setting = {
                        view : {
                            dblClickExpand : true,// 定义双击展开
                            showLine : true,
                            selectedMulti : false
                        },
                        edit : {
                            enable : true,
                            showRemoveBtn : false,
                            showRenameBtn : false,
                            drag : {//拖拽时的操作
                                isCopy : false //不是复制，那就是移动
                            }
                        },
                        data : {
                            simpleData : {
                                enable : true
                            },
                            key : {
                                name : "cityName",
                                idKey: "id",
                                pIdKey: "pId",
                                rootPId: 0 //说明根结点的id是0
                            }
                        },
                        callback : {
                            beforeDrop : beforeDrop, //拖拽 拖拽结束时发生的函数
                            onRightClick : cityRightClick
                        }
                    };
                    //加一个虚拟节点的方法 id为0就是根结点
                    data.push({id:0,cityName:"城市",open:true});
                    /**
                     * 参数1：容器
                     * 参数2：配置值
                     * 参数3：数据
                     */
                    $.fn.zTree.init($("#city"), setting, data);
                }
            });
        }
    </script>
</head>
<body>
    <input type="hidden" id="user_group_id" value="${User.groupId}">
    <div class="container">
        <div class="row">
            <div class="col-md-3">
                <input type="text" class="form-control" id="cityNameByWeather" placeholder="请输入市名" value="淄博">
            </div>
            <div class="col-md-3">
                <button id="btnEcharts" class="btn btn-primary" type="button">报表</button>
            </div>
            <div class="col-md-3">
                <button id="btnTree" class="btn btn-primary" type="button">树</button>
            </div>
            <div class="col-md-3">
                <button id="btnImg" class="btn btn-primary" type="button">Restful图片</button>
                <button id="btnRemoveImg" class="btn btn-primary" type="button">清空图片缓存</button>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
                <div id="reportDiv" style="width: 600px;height:400px;"></div>
            </div>
            <div class="col-md-2">
                <div id="citytree">
                    <table border="0" style="height:550px" align="left" width="100%">
                        <tr>
                            <td width="30%" align="left" valign="top">
                                <ul id="city" class="ztree" style="width:260px; overflow:auto;"></ul>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="col-md-4">
                <img class="img-rounded" src="/TM_Img/${imgURL}"/>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4">
                <div class="zTreeDemoBackground left">
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
            </div>
            <div class="col-md-8">
                <iframe width="600px" height="400px" id="iframeA"></iframe>
            </div>
        </div>
    </div>

    <%--添加城市的模态框--%>
    <div class="modal fade" id="cityAddModel" data-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title">添加城市</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="addForm">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">城市名称:</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="AddCityName" name="AddCityName">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default exitBtn" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="addForm()">添加</button>
                </div>
            </div>
        </div>
    </div>

    <%--修改城市的模态框--%>
    <div class="modal fade" id="cityModiModel" data-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title">修改城市</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal">
                        <input type="hidden" name="cityId" id="cityId">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">城市名称:</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="ModiCityName" name="ModiCityName">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default exitBtn" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="ModiForm()">修改</button>
                </div>
            </div>
        </div>
    </div>

    <%--删除城市的模态框--%>
    <div class="modal fade" id="cityDeleteModel" data-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title">删除城市</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal">
                        <input type="hidden" name="cityId" id="delCityById">
                        确定要删除<label id="delCityByName"></label>吗？
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default exitBtn" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="DeleteForm()">删除</button>
                </div>
            </div>
        </div>
    </div>

    <%--上传照片的模态框--%>
    <div class="modal fade" id="ImgModel" data-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title">上传照片</h4>
                </div>
                <div class="modal-body">
                    <form action="/index/img" class="form-horizontal" enctype="multipart/form-data" id="imgform" method="post">
                        上传的文件：<input type="file" name="imgFile" id="imgFile">
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default exitBtn" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="AddImg()">上传</button>
                </div>
            </div>
        </div>
    </div>



    <!-- city树右键菜单 -->
    <div id="rMenu" class="rMenu" onmouseout="divOut();" onmouseover="divOver()">
        <ul class="rMenuUi">
            <li class="rMenuLi" onmousemove="move(this);" data-toggle="modal" data-target="#cityAddModel" onclick="initAddCity()">新增</li>
            <li class="rMenuLi disabled" onmousemove="move(this);" data-toggle="modal" data-target="#cityModiModel"  onclick="initModiCity()">修改</li>
            <li class="rMenuLi disabled" onmousemove="move(this);" data-toggle="modal" data-target="#cityDeleteModel" onclick="initDelCity()">删除</li>
        </ul>
    </div>

    <script>
        /**
         * 在用户树上右击显示右键菜单同时选中节点
         */
        function cityRightClick(event, treeId, treeNode) {
            if(!treeNode) {
                return;
            }

            $.fn.zTree.getZTreeObj(treeId).selectNode(treeNode);
            rightClick(event,"rMenu");

        }

        /**
         * 右击时定位右键菜单展示的位置并显示
         */
        function rightClick(event,rMenuId) {
            $("#" + rMenuId).css({
                "top" : event.clientY + "px",
                "left" : event.clientX + "px",
                "visibility" : "visible"
            });
        }

        /**
         * 鼠标划出右键菜单层时，去除“鼠标经过菜单时”的样式。
         */
        function divOut() {
            $("body").bind("mousedown", mousedown);

            $(".rMenuLiMove").addClass("rMenuLi");
            $(".rMenuLiMove").removeClass("rMenuLiMove");
        }

        /**
         * 鼠标在弹出层上方时，解除鼠标按下的事件
         */
        function divOver() {
            $("body").unbind("mousedown", mousedown);
        }

        /**
         * 鼠标在菜单间移动时样式的切换
         */
        function move(element) {
            $(".rMenuLiMove").addClass("rMenuLi");
            $(".rMenuLiMove").removeClass("rMenuLiMove");

            $(element).addClass("rMenuLiMove");
            $(element).removeClass("rMenuLi");
        }

        /**
         * 单击鼠标事件：
         * 在页面任意地方单击鼠标，关闭右键弹出的菜单
         */
        function mousedown() {
            $("#rMenu").css({
                "visibility" : "hidden"
            });
            $("#groupMenu").css({
                "visibility" : "hidden"
            });
            $("#menuMenu").css({
                "visibility" : "hidden"
            });
        }
        /**
         * 初始化新增用户界面
         */
        function initAddCity() {
            $("#AddCityName").val("");
            mousedown();
        }

        /**
         * 初始化修改用户界面
         */
        function initModiCity() {
            mousedown();
            var nodes = $.fn.zTree.getZTreeObj("city").getSelectedNodes();
            $("#ModiCityName").val(nodes[0].cityName);
            $("#cityId").val(nodes[0].id);
        }
        /**
         * 初始化修改用户界面
         */
        function initDelCity() {
            mousedown();
            var nodes = $.fn.zTree.getZTreeObj("city").getSelectedNodes();
            $("#delCityByName").html(nodes[0].cityName);
            $("#delCityById").val(nodes[0].id);
        }

        /**
         * 提交添加的表单
         */
        function addForm(){
            var nodes = $.fn.zTree.getZTreeObj("city").getSelectedNodes();
            $.ajax({
                url : "/index/addTreeNode/"+ $("#AddCityName").val(),
                type : "POST",
                data : {
                    "_method" : "PUT",
                    "selectId": nodes[0].id
                },
                success : function(data) {
                    if (data=="ok"){
                        $(".exitBtn").click();
                        initTree();
                    }
                }
            });
        }
        /**
         * 提交修改的表单
         */
        function ModiForm() {
            $.ajax({
                url : "/index/modifyTreeNode/"+ $("#cityId").val(),
                type : "POST",
                data : {
                    "_method" : "PUT",
                    "cityName": $("#ModiCityName").val()
                },
                success : function(data) {
                    if (data=="ok"){
                        $(".exitBtn").click();
                        initTree();
                    }
                }
            });
        }
        /**
         * 提交删除的表单
         */
        function DeleteForm() {
            $.ajax({
                url : "/index/deleteTreeNode/"+ $("#delCityById").val(),
                type : "POST",
                data : {
                    "_method" : "DELETE"
                },
                success : function(data) {
                    if (data=="ok"){
                        $(".exitBtn").click();
                        initTree();
                    }
                }
            });
        }


        /**
         * 节点拖拽操作结束之前事件：
         * 将拖拽后的顺序提交，修改数据库中的顺序
         */
        function beforeDrop(treeId, treeNodes, targetNode, moveType, isCopy) {
            $.ajax({
                /**
                 * treeNodes[0].id：被拖拽的节点id
                 * targetNode.id：目标节点id
                 * moveType：指定移动到目标节点的相对位置
                 * 			"inner"：成为子节点，"prev"：成为同级前一个节点，"next"：成为同级后一个节点
                 */
                url : "/index/" + treeNodes[0].id + "/" + targetNode.id + "/" + moveType,
                type : "POST",
                data : {
                    "_method" : "PUT"
                },
                success : function(data) {
                    if (data=="ok"){
                        $(".exitBtn").click();
                        initTree();
                    }
                }
            });
        }


        /**
         * 上传文件
         */
        function AddImg() {
            $("#imgform").submit();
        }
    </script>
</body>
</html>
