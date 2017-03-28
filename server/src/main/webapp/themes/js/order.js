/**
 * Created by wt on 2017/3/24.
 */
function initOrderTable() {
    $('#orderTable').bootstrapTable({
        toolbar: '#toolbar',
        url: '/order/orderList.html', //请求后台的URL（*）
        queryParams: queryParams,
        // queryParamsType: 'limit',
        method: 'get', //请求方式（*）
        // toolbar: '#toolbar', //工具按钮用哪个容器
        striped: true, //是否显示行间隔色
        cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true, //是否显示分页（*）
        // sortable: false, //是否启用排序
        sortOrder: "desc", //排序方式
        sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1, //初始化加载第一页，默认第一页
        pageSize: 10, //每页的记录行数（*）
        pageList: [10, 25, 50], //可供选择的每页的行数（*）
        search: true, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        // searchOnEnterKey: true,
        strictSearch: true,
        // showColumns: true, //是否显示所有的列
        showRefresh: true, //是否显示刷新按钮
        responseHandler: responseHandlerPage,
        columns: [{
            field: 'userName',
            title: '客户',
            width: '30%'
        }, {
            field: 'account',
            title: '订单总价',
            width: '30%'
        }, {
            field: 'doneTime',
            title: '完成时间',
            formatter: dateTimeFormatter,
            width: '30%'
        }, {
            title: '操作',
            field: 'uuid',
            formatter: function (value, row, index) {
                var e = '<a class="btn btn-primary" role="button" href="/order/editPage.html?uuid=' + row.uuid + '" >编辑</a> ';
//                var d = '<a href="#" onclick="del(\'' + row.uuid + '\')">删除</a> ';
                return e;
            },
            width: '10%'
        }]
    });
}

function editOrder() {
    $.ajax({
        type: "post",
        url: "/order/orderEdit.html",
        dataType: 'json',
        data: $('#orderForm').serialize(),
        success: function (data) {
            if (data.result) {
                $("#msgTip").removeClass('alert-danger').addClass('alert-success').show().html(data.msg);
            } else {
                $("#msgTip").removeClass('alert-success').addClass('alert-danger').show().html(data.msg);
            }
        }
    });
}

function initEditOrderDetailTable() {
    var uuid = $('#uuid').val();
    $('#editOrderDetailTable').bootstrapTable({
        url: '/order/orderDetailList.html?uuid=' + uuid, //请求后台的URL（*）
        method: 'get', //请求方式（*）
        striped: true, //是否显示行间隔色
        cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: false, //是否显示分页（*）
        responseHandler: responseHandlerList,
        sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1, //初始化加载第一页，默认第一页
        pageSize: 100, //每页的记录行数（*）
        columns: [{
            field: 'goodsName',
            title: '商品名称',
            width: '40%'
        }, {
            field: 'quantity',
            title: '数量',
            width: '30%'
        }, {
            field: 'account',
            title: '价格',
            width: '30%'
        }]
    });
}

function addOrder() {
    $.ajax({
        type: "post",
        url: "/order/orderAdd.html",
        dataType: 'json',
        data: $('#orderForm').serialize(),
        success: function (data) {
            if (data.result) {
                $("#msgTip").removeClass('alert-danger').addClass('alert-success').show().html(data.msg);
                setTimeout("window.location.href='/order/listPage.html'", 1000)
            } else {
                $("#msgTip").removeClass('alert-success').addClass('alert-danger').show().html(data.msg);
            }
        }
    });
}

function initSelectUserTable() {
    $('#selectUserTable').bootstrapTable({
        url: '/user/userList.html', //请求后台的URL（*）
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
        responseHandler: responseHandlerPage,
        columns: [{
            field: 'userName',
            title: '微信号',
            width: '30%'
        }, {
            field: 'realName',
            title: '真实姓名',
            width: '30%'
        }, {
            field: 'mobile',
            title: '手机号',
            width: '30%'
        }, {
            title: '操作',
            field: 'uuid',
            formatter: function (value, row, index) {
                var e = '<button type="button" class="btn btn-primary" onclick="setUser(\'' + row.userName + '\',\'' + row.uuid + '\')">选择</button> ';
//                var d = '<a href="#" onclick="del(\'' + row.uuid + '\')">删除</a> ';
                return e;
            },
            width: '10%'
        }]
    });

}

function setUser(userName, userUUid) {
    $('#userName').val(userName);
    $('#userUUid').val(userUUid);
    $('#userModal').modal('hide')
}

function calculateAccount() {
    var data = $('#orderDetailTable').bootstrapTable('getData');
    var sum = 0.00;
    if (data.length > 0) {
        for (var i in data) {
            sum += data[i].account
        }
    }
    $('#account').val(sum)
}

function initSelectGoodsTable() {
    $('#selectGoodsTable').bootstrapTable({
        url: '/goods/goodsList.html', //请求后台的URL（*）
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
                var e = "<button type=\"button\" class=\"btn btn-primary\" onclick=\'addSelectGoods(\"" + JSON.stringify(row) + "\")\'>选择</button>";
//                var d = '<a href="#" onclick="del(\'' + row.uuid + '\')">删除</a> ';
                return e;
            },
            width: '10%'
        }]
    });

}

function addSelectGoods(row) {
    console.log(row)
}

function initAddOrderDetailTable() {
    var uuid = $('#uuid').val();
    $('#addOrderDetailTable').bootstrapTable({
        striped: true, //是否显示行间隔色
        cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: false, //是否显示分页（*）
        pageNumber: 1, //初始化加载第一页，默认第一页
        pageSize: 100, //每页的记录行数（*）
        columns: [{
            field: 'goodsName',
            title: '商品名称',
            width: '40%'
        }, {
            field: 'quantity',
            title: '数量',
            width: '30%'
        }, {
            field: 'account',
            title: '价格',
            width: '30%'
        }]
    });
}