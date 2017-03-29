/**
 * Created by wt on 2017/3/24.
 */
function initUserTable() {
    $('#userTable').bootstrapTable({
        toolbar: '#toolbar',
        url: '/user/userList.html', //请求后台的URL（*）
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
                var e = '<a class="btn btn-primary btn-xs" role="button" href="/user/editPage.html?uuid=' + row.uuid + '" >编辑</a> ';
//                var d = '<a href="#" onclick="del(\'' + row.uuid + '\')">删除</a> ';
                return e;
            },
            width: '10%'
        }]
    });
}

function editUser() {
    $.ajax({
        type: "post",
        url: "/user/userEdit.html",
        dataType: 'json',
        data: $('#userForm').serialize(),
        success: function (data) {
            if (data.result) {
                $("#msgTip").removeClass('alert-danger').addClass('alert-success').show().html(data.msg);
                setTimeout("window.location.href='/user/listPage.html'", 1000)
            } else {
                $("#msgTip").removeClass('alert-success').addClass('alert-danger').show().html(data.msg);
            }
        }
    });
}

function addUser() {
    $.ajax({
        type: "post",
        url: "/user/userAdd.html",
        dataType: 'json',
        data: $('#userForm').serialize(),
        success: function (data) {
            if (data.result) {
                $("#msgTip").removeClass('alert-danger').addClass('alert-success').show().html(data.msg);
                setTimeout("window.location.href='/user/listPage.html'", 1000)
            } else {
                $("#msgTip").removeClass('alert-success').addClass('alert-danger').show().html(data.msg);
            }
        }
    });
}