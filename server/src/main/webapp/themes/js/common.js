/**
 * Created by wt on 2017/3/23.
 */
function dateTimeFormatter(value, row, index) {
    return getLocalTime(value, 4);
}

//时间戳转换
var getLocalTime = function (value, type) {
    if (value == null || value == '') {
        return '--';
    }
    var dt;
    if (value instanceof Date) {
        dt = value;
    }
    else {
        dt = new Date(value);
        if (isNaN(dt)) {
            value = value.replace(/\/Date\((-?\d+)\)\//, '$1'); //将那个长字符串的日期值转换成正常的JS日期格式
            dt = new Date();
            dt.setTime(value);
        }
    }
    switch (type) {
        case 1:
            return dt.format("yyyy年MM月dd日");   //这里用到一个javascript的Date类型的拓展方法
            break;
        case 2:
            return dt.format("yyyy年MM月dd日 hh:mm:ss");   //这里用到一个javascript的Date类型的拓展方法
            break;
        case 3:
            return dt.format("yyyy-MM-dd");   //这里用到一个javascript的Date类型的拓展方法
            break;
        case 4:
            return dt.format("yyyy-MM-dd hh:mm:ss");   //这里用到一个javascript的Date类型的拓展方法
            break;
    }
};

//日期显示的格式化
Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1, //month
        "d+": this.getDate(),    //day
        "h+": this.getHours(),   //hour
        "m+": this.getMinutes(), //minute
        "s+": this.getSeconds(), //second
        "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter
        "S": this.getMilliseconds() //millisecond
    };
    if (/(y+)/.test(format)) format = format.replace(RegExp.$1,
        (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o) if (new RegExp("(" + k + ")").test(format))
        format = format.replace(RegExp.$1,
            RegExp.$1.length == 1 ? o[k] :
                ("00" + o[k]).substr(("" + o[k]).length));
    return format;
};


function responseHandlerPage(res) {
    // console.log(res);
    if (res.result) {
        var rsp = {
            rows: res.data.data,
            total: res.data.total
        };
        return rsp
    }
    return null;
}

function responseHandlerList(res) {
    // console.log(res);
    if (res.result) {
        var rsp = {
            rows: res.data,
            total: res.data.length
        };
        return rsp
    }
    return null;
}

function queryParams(params) {
    // console.log(params);
    var offset = parseInt(params.offset) / parseInt(params.limit);
    offset++;
    var temp = {
        pageSize: params.limit,
        pageNum: offset,
        search: params.search
    };
    return temp
}