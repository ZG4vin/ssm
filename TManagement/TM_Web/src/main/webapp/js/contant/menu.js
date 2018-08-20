var curMenu = null, zTree_Menu = null;

$(function () {
    initMenuTree();
})

function initMenuTree() {
    $.ajax({
        url:"/index/initMenuTree/"+$("#user_group_id").val(),
        success:function (zNodes) {
            var treeObj = $("#treeDemo");
            var setting = {
                view: {
                    showLine: false,
                    showIcon: false,
                    selectedMulti: false,
                    dblClickExpand: false,
                    addDiyDom: addDiyDom
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                callback: {
                    beforeClick: beforeClick
                }
            };
            $.fn.zTree.init(treeObj, setting, zNodes);
            zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");

            //默认选中节点
            //默认是这样的 curMenu = zTree_Menu.getNodes()[0].children[0].children[0];结果不行
            //改成了这样curMenu = zTree_Menu.getNodes()[0].children[0];就可以了
            //把下面屏蔽掉是为了打开时不选择任何节点
            // curMenu = zTree_Menu.getNodes()[0].children[0];
            // zTree_Menu.selectNode(curMenu);

            treeObj.hover(function () {
                if (!treeObj.hasClass("showIcon")) {
                    treeObj.addClass("showIcon");
                }
            }, function() {
                treeObj.removeClass("showIcon");
            });
        }
    })
}
function addDiyDom(treeId, treeNode) {
    var spaceWidth = 5;
    var switchObj = $("#" + treeNode.tId + "_switch"),
        icoObj = $("#" + treeNode.tId + "_ico");
    switchObj.remove();
    icoObj.before(switchObj);

    if (treeNode.level > 1) {
        var spaceStr = "<span style='display: inline-block;width:" + (spaceWidth * treeNode.level)+ "px'></span>";
        switchObj.before(spaceStr);
    }
}
function beforeClick(treeId, treeNode,clickFlag) {
    if (treeNode.level == 0 ) {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        zTree.expandNode(treeNode);
        return false;
    }
    
    if (treeNode.jumpUrl!=null){
        $("#iframeA").attr("src",treeNode.jumpUrl);
    }
    return true;
}