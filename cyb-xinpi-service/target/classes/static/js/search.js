
function search_bjs() {
    $('#txtStartDate').datepicker("hide");
    $('#txtEndDate').datepicker("hide");
    $("#txtStartDate").datepicker({ dateFormat: 'yy-mm-dd' });
    $("#txtEndDate").datepicker({ dateFormat: 'yy-mm-dd' });
    var date = new Date();
    $('#txtStartDate').val(formatDate(date.setFullYear(date.getFullYear() - 1)));
    $('#txtEndDate').val(formatDate(new Date()));

    $("#txtkw").attr("url", "/api/v1/all_search_zhanshi&q={q}");
    $("#txtkw").autocomplete(
        "/api/v1/all_search_zhanshi&q={q}",
        {
            delay: 10,
            minChars: 1,
            matchSubset: 0,
            cacheLength: 8,
            matchContains: false,
            scrollHeight: 100,
            width: 200,
            formatItem: formatItem,
            autoFill: false,
            selectFirst: true
        });
    $('#txtSecCode').attr("url", "/api/v1/all_search_zhanshi&q={q}");
    $('#txtSecCode').autocomplete(
        "/api/v1/all_search_zhanshi&q={q}",
        {
            delay: 10,
            minChars: 1,
            matchSubset: 0,
            cacheLength: 8,
            matchContains: false,
            scrollHeight: 100,
            width: 200,
            formatItem: formatItem,
            autoFill: false,
            selectFirst: true
        });
    $('#span_search').bind('click', function () {
        Search_senior();
    });
    $('#txtsubmit').bind('click', function () {
        Search_default();
    });
    document.onkeydown = function (e) {
        var ev = document.all ? window.event : e;
        if (ev.keyCode == 13) {
            Search_default(); //搜索事件
        }
    }
    $("#span_search").bind('click', function () {
        Search_senior();
    });
}

//默认搜索
function Search_default() {
    var v = $('#txtkw').val();
    if (v && v != "请输入代码/简称/拼音...") {

        var p = "t=b&c=" + encodeURI(v);
        var url = "/page/L4_result.html?" + p;
        window.open(url);
    }
}
//高级搜索
function Search_senior() {
    var m = $('#select_category option:selected').val();
    var start = $('#txtStartDate').val();
    var end = $('#txtEndDate').val();
    var st, en;
    if (start) {
        st = new Date(start);
    }
    if (end) {

        en = new Date(end);
    }
    if (start && end) {
        if (st > end) {
            alert("开始日期不能晚于截止日期");
            return false;
        }
        var currt = new Date();
        if (st > currt) {
            alert("开始日期不能晚于当天日期");
            return false;
        }
    }
    var code = $('#txtSecCode').val();
    var key = $('#txtKeys').val();
    var param = "t=b";

    if (code && code != "公司代码/简称/拼音") {
        param += "&c=" + code;
    }
    if (key && key != "关键字") {
        param += "&q=" + key;
    }
    if (m) {
        if (m != "all") {
            param += "&m=" + m;
        }
    }
    if (st) {
        param += "&st=" +start;
    }
    if (en) {
        param += "&et=" + end;
    }
    window.open("/page/L4_result.html?" + param);

}

//格式化提示信息
function formatItem(row) {
    return "<td>" + row[0] + "</td><td>" + row[1] + "</td><td>" + row[2] + "</td>";
}
function ItemSelected(li) {
    var extra = li.attr("extra");
    var extid = '';
    var e = extra.split(',');
    if (e.length < 3)
        extid = null;
    else
        extid = e[2]

    $("#txtkw").attr('extid', extid);
}
function hide_datepicker() {
    $('#txtStartDate').datepicker("hide");
    $('#txtStartDate').datepicker("hide");
}