/**
 * Created by wt on 2017/3/24.
 */
function initGoodsTable() {
    $('#goodsTable').bootstrapTable({
        toolbar: '#toolbar',
        url: '/goods/goodsList.html', //请求后台的URL（*）
        queryParams: queryParams,
//        queryParamsType: 'limit',
        method: 'get', //请求方式（*）
//        toolbar: '#toolbar', //工具按钮用哪个容器
        striped: true, //是否显示行间隔色
        cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true, //是否显示分页（*）
//        sortable: false, //是否启用排序
        sortOrder: "desc", //排序方式
        sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1, //初始化加载第一页，默认第一页
        pageSize: 10, //每页的记录行数（*）
        pageList: [10, 25, 50], //可供选择的每页的行数（*）
        search: true, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        searchOnEnterKey: true,
//        strictSearch: true,
//        showColumns: true, //是否显示所有的列
        showRefresh: true, //是否显示刷新按钮
        responseHandler: responseHandlerPage,
        columns: [{
            field: 'goodsName',
            title: '商品名',
            width: '30%'
        }, {
            field: 'typeName',
            title: '商品类型',
            width: '30%'
        }, {
            field: 'price',
            title: '售价',
            width: '30%'
        }, {
            title: '操作',
            field: 'uuid',
            formatter: function (value, row, index) {
                var e = '<a class="btn btn-primary btn-xs" role="button" href="/goods/editPage.html?uuid=' + row.uuid + '" >编辑</a>&nbsp;';
                var d = '<button type="button" class="btn btn-warning btn-xs" data-toggle="modal" data-target="#msgModal" data-uuid="' + row.uuid + '" data-name="' + row.goodsName + '">删除</button>';
                return e+d;
            },
            width: '10%'
        }]
    });
}

function editGoods() {
    $.ajax({
        type: "post",
        url: "/goods/goodsEdit.html",
        dataType: 'json',
        data: $('#goodsForm').serialize(),
        success: function (data) {
            if (data.result) {
                $("#msgTip").removeClass('alert-danger').addClass('alert-success').show().html(data.msg);
                setTimeout("window.location.href='/goods/listPage.html'", 1000)
            } else {
                $("#msgTip").removeClass('alert-success').addClass('alert-danger').show().html(data.msg);
            }
        }
    });
}

function addGoods() {
    $.ajax({
        type: "post",
        url: "/goods/goodsAdd.html",
        dataType: 'json',
        data: $('#goodsForm').serialize(),
        success: function (data) {
            if (data.result) {
                $("#msgTip").removeClass('alert-danger').addClass('alert-success').show().html(data.msg);
                setTimeout("window.location.href='/goods/listPage.html'", 1000)
            } else {
                $("#msgTip").removeClass('alert-success').addClass('alert-danger').show().html(data.msg);
            }
        }
    });
}

function initSelectTypeTable() {
    $('#selectTypeTable').bootstrapTable({
        url: '/goods/goodsTypeList.html', //请求后台的URL（*）
        queryParams: queryParams,
        method: 'get', //请求方式（*）
        striped: true, //是否显示行间隔色
        cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true, //是否显示分页（*）
        sortOrder: "desc", //排序方式
        sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1, //初始化加载第一页，默认第一页
        pageSize: 10, //每页的记录行数（*）
        pageList: [10, 25, 50], //可供选择的每页的行数（*）
        search: true, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        // searchOnEnterKey: true,
        strictSearch: true,
//        showColumns: true, //是否显示所有的列
        showRefresh: true, //是否显示刷新按钮
        responseHandler: responseHandlerList,
        columns: [{
            field: 'typeName',
            title: '类型',
            width: '80%'
        }, {
            title: '操作',
            field: 'uuid',
            formatter: function (value, row, index) {
                var e = '<button type="button" class="btn btn-primary btn-xs" onclick="setType(\'' + row.typeName + '\',\'' + row.uuid + '\')">选择</button> ';
//                var d = '<a href="#" onclick="del(\'' + row.uuid + '\')">删除</a> ';
                return e;
            },
            width: '20%'
        }]
    });

}

function setType(type, uuid) {
    $('#typeName').val(type);
    $('#typeUuid').val(uuid);
    $('#typeModal').modal('hide')
}

function deleteGoods() {
    $.ajax({
        type: "post",
        url: "/goods/goodsDelete.html",
        dataType: 'json',
        data: "uuid=" + $('#deleteUuid').val(),
        success: function (data) {
            if (data.result) {
                $("#msgTip").removeClass('alert-danger').addClass('alert-success').show().html(data.msg);
                setTimeout("window.location.href='/goods/listPage.html'", 1000)
            } else {
                $("#msgTip").removeClass('alert-success').addClass('alert-danger').show().html(data.msg);
            }
        }
    });
}