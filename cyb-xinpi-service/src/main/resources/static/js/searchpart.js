var issy = "true";
function get3MonthBefor() {
    var resultDate, year, month, date, hms;
    var currDate = new Date();
currDate.setDate(currDate.getDate() -90);
    year = currDate.getFullYear();
    month = currDate.getMonth() + 1;
    date = currDate.getDate();
    hms = currDate.getHours() + ':' + currDate.getMinutes() + ':' + (currDate.getSeconds() < 10 ? '0' + currDate.getSeconds() : currDate.getSeconds());

    month = (month < 10) ? ('0' + month) : month;

    resultDate = year + '-' + month + '-' + date + ' ' + hms;
    return resultDate;
}

function today() {
    var strDate = '';
    var today_sel = new Date();
    strDate += today_sel.getFullYear();
    strDate += "-" + ((today_sel.getMonth() + 1) < 10 ? '0' + (today_sel.getMonth() + 1) : (today_sel.getMonth() + 1));
    strDate += "-" + (today_sel.getDate() < 10 ? '0' + today_sel.getDate() : today_sel.getDate());
    return (strDate);
}

function checkDate(ds) {
    var reg = /^ *(\d{4})-(\d{1,2})-(\d{1,2}) *$/;
    return reg.test(ds);
}

function parseDate(str) {
    if (typeof str == 'string') {
        var results = str.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) *$/);
        if (results && results.length > 3) {
            return new Date(parseInt(results[1], 10), parseInt(results[2], 10) - 1, parseInt(results[3], 10));
        }
        results = str.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) +(\d{1,2}):(\d{1,2}):(\d{1,2}) *$/);
        if (results && results.length > 6)
            return new Date(parseInt(results[1], 10), parseInt(results[2], 10) - 1, parseInt(results[3], 10), parseInt(results[4], 10), parseInt(results[5], 10), parseInt(results[6], 10));
        results = str.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) +(\d{1,2}):(\d{1,2}):(\d{1,2})\.(\d{1,9}) *$/);
        if (results && results.length > 7)
            return new Date(parseInt(results[1], 10), parseInt(results[2], 10) - 1, parseInt(results[3], 10), parseInt(results[4], 10), parseInt(results[5], 10), parseInt(results[6], 10), parseInt(results[7], 10));
    }
    return null;
}

function mashup(src) {
    if (src.value == "") {
        src.value = today();
        return;
    }
    var results = src.value.match(/^\D*(\d{4})\D*(\d{1,2})\D*(\d{1,2})\S*/);

    if (!results || results.length < 3) {
        src.value = today();
        src.focus();
        return;
    }
    else {
        var date = new Date(parseInt(results[1], 10), parseInt(results[2], 10) - 1, parseInt(results[3], 10));
        var strDate = date.getFullYear();
        strDate += "-" + ((date.getMonth() + 1) < 10 ? '0' + (date.getMonth() + 1) : (date.getMonth() + 1));
        strDate += "-" + (date.getDate() < 10 ? '0' + date.getDate() : date.getDate());
        src.value = strDate;
    }
}

function sub1() {
    var startTime = document.getElementById("d1").value;
    var endTime = document.getElementById("d2").value;
    if (startTime != "" && !checkDate(startTime)) {
        alert("开始日期格式不正确");
        return;
    }
    if (endTime != "" && !checkDate(endTime)) {
        alert("结束日期格式不正确");
        return;
    }
    if (startTime == "" && endTime != "") {
        alert("请输入开始日期");
        return;
    }
    if (endTime == "" && startTime != "") {
        alert("请输入结束日期");
        return;
    }
    if (startTime == "" || endTime == "") {
        alert("请输入日期");
        return;
    }

    var sd = parseDate(startTime);
    var ed = parseDate(endTime);
    if (sd > ed) {
        alert("开始日期不能晚于结束日期！");
        return;
    }

    var c = $("#kw1").val();
    if (c == "代码/简称/拼音") {
        c = "";
    }
    var q = $("#kw2").val();
    if (q == "请输入关键字") {
        q = "";
    }

    var market = $('#tj1').val();
    var sort = $("#tj2").val();
    var url = "/new/search.html";

    var noSecCode = false;
    var searchtype = 'gsgg';
    if (market == "012001" || market == "012015"
         || market == "012002" || market == "012003"
         || market == "") {
    }
    else if (market == "01010509") {
        if (sort == "")
            sort = market;
        market = "";
        noSecCode = true;
        searchtype = 'fundingcorp';
    }
    else if (market == '0110') {
        sort = market;
        market = "";
        noSecCode = true;
        searchtype = 'zqcorp';
    }
    else {
        sort = market;
        market = "";
        noSecCode = true;
    }
    if (c == "") {
        if (market == "" && sort == "") {
            alert("在没有输入证券代码时市场类型和公告类型至少选择一个");
            return;
        }
        else if ((ed - sd) / (1000 * 24 * 3600) > 1000) {
            alert("在没有输入证券代码时查询时间跨度不能超过3个月");
            return;
        }
    }
    if (market == "" && c == "" && (sort == "" || sort == "0110" || noSecCode)) {
        url = "/new/search2.html";
    }
    if (issy == "true") {
        window.open(url + "?t=b&st=" + startTime + "&et=" + endTime + "&c=" + c + "&q=" + q + "&m=" + market + "&s=" + sort);
    }
    else {
        window.open(url + "?t=b&st=" + startTime + "&et=" + endTime + "&c=" + c + "&q=" + q + "&m=" + market + "&s=" + sort);
    }
    return false;
}
function sub2() {
    var m = $("#kw3").attr('extid');
    if (!m)
        return false;
    var c = $("#kw3").val();
    if (!c || c == '代码/简称/拼音')
        return false;
    var url = '';
    if (m == '012001') {
        url = '/new/gs.html?t=xp_main&c=sh' + c;
    }
    else if (m == '012002' || m == '012003' || m == '012015') {
        url = '/new/gs.html?t=xp_main&c=sz' + c;
    }
    else {
        url = '/new/jjgs.html?t=xp_main&c=' + c;
    }
    window.open(url);
    return false;
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

    $("#kw3").attr('extid', extid);
}
var kw2value = "";
var jjvalue = "<option value=''>-请选择公告类型-</option><option value='0133'>招募及设立</option><option value='013501'>年度报告</option><option value='013503'>半年度报告</option><option value='013505'>季度报告</option><option value='0137'>临时报告</option>";
function tj1changed() {
    var sel = $("#tj1").val();
    if (sel == "01010509") {
        $("#tj2").html(jjvalue);
        $("#tj2").attr("jj", "1");
        $("#kw1").attr("url", "/new/search.ashx?t=s&s=fund");
    }
    else if (sel == "0139") {
        $("#kw1").attr("url", "/new/search.ashx?t=s&s=zq");
    }
    else {
        if ($("#tj2").attr("jj") == "1") {
            $("#tj2").html(kw2value);
            $("#tj2").attr("jj", "0");
        }
        $("#kw1").attr("url", "/new/search.ashx?t=s");
    }
    $("#kw1").trigger("flushCache");
    if (sel == "012001" || sel == "012015"
         || sel == "012002" || sel == "012003"
         || sel == "01010509" || sel == "") {
        $("#tj2").removeAttr("disabled");
    }
    else {
        $("#tj2").html(kw2value);
        $("#tj2").attr("disabled", "disabled");
    }

}
$(document).ready(function () {
    $("#kw1").attr("url", "/new/search.ashx?t=s");
    kw2value = $("#tj2").html();

    $("#tj1").change(function () {
        $("#kw1").flushCache;
        tj1changed();
    });
    $("#kw1").focus(function () {
        if ($("#kw1").val() == '代码/简称/拼音') {
            $("#kw1").val("");
            $("#kw1").css("color", "black");
        }
        return;
    });
    $("#kw1").blur(function () {
        if ($('#kw1').val() == '') {
            $('#kw1').val('代码/简称/拼音');
            $("#kw1").css("color", "#9f9f9f");
        }
    });

    $("#kw1").autocomplete(
        "/new/search.ashx?t=s",
        {
            delay: 10,
            minChars: 1,
            matchSubset: 0,
            cacheLength: 10,
            matchContains: false,
            scrollHeight: 100,
            width: 200,
            formatItem: formatItem,
            autoFill: false,
            selectFirst: true
        });
    $("#kw2").focus(function () {
        if ($("#kw2").val() == '请输入关键字') {
            $("#kw2").val("");
            $("#kw2").css("color", "black");
        }
        return;
    });
    $("#kw2").blur(function () {
        if ($("#kw2").val() == "") {
            $("#kw2").val("请输入关键字");
            $("#kw2").css("color", "#9f9f9f");
        }
    });
    $("#kw2").keydown(function (e) {
        var curKey = e.which;
        if (curKey == 13) { return sub1(); }
    });
    if ($("#kw3").length > 0) {
        $("#kw3").blur(function () {
            if ($("#kw3").val() == '') {
                $("#kw3").val('代码/简称/拼音');
                $("#kw3").css("color", "#9f9f9f");
            }
        });
        $("#kw3").focus(function () {
            if ($("#kw3").val() == '代码/简称/拼音') {
                $("#kw3").val("");
                $("#kw3").css("color", "black");
            }
            return;
        });
        $("#kw3").attr('url', "search.ashx?t=s&s=fs");
        $("#kw3").autocomplete(
            "search.ashx?t=s&s=fs",
            {
                delay: 10,
                minChars: 1,
                matchSubset: 0,
                cacheLength: 10,
                matchContains: false,
                scrollHeight: 100,
                width: 200,
                formatItem: formatItem,
                autoFill: false,
                selectFirst: true,
                onItemSelect: ItemSelected
            });
        $('#kw3').keyup(function (e) {
            if (e.keyCode == 13) {
                sub2();
                return false;
            }
        });
    }
    $("#d1").val(get3MonthBefor());
    $("#d1").blur(function () {
        mashup(this);
    });


    $("#d1").datepicker({ dateFormat: 'yy-mm-dd' });

    // add by zhaodian @ 20130125
    var nextDate = new Date();
    nextDate.setDate(nextDate.getDate() + 1);

    $("#d2").val(nextDate.getFullYear() + '-' + (nextDate.getMonth()+1) + '-' + nextDate.getDate());
    //end
    $("#d2").blur(function () {
        mashup(this);
    });
    $("#d2").datepicker({ dateFormat: 'yy-mm-dd' });

    mashup(document.getElementById("d1"));
    mashup(document.getElementById("d2"));

    //zhaodian begin
    try {
        var pars = $.query.load(parent.location.href);
        var m = pars.get("m");
        var s = pars.get("s");
        if (m && m == true) {
            m = s;
            if (s == '01010509' || jjvalue.indexOf(s) > 0)
                m = '01010509';
        }
        $("#tj1 option[value='" + m + "']").attr("selected", true);
        tj1changed();
        m = pars.get("st");

        if (m && m != true)
            $("#d1").val(m);
        m = pars.get("et");
        if (m && m != true)
            $("#d2").val(m);
        m = pars.get("c");
        if (m && m != true) {
            $("#kw1").val(m);
            $("#kw1").css("color", "black");
        }
        m = pars.get("q");
        if (m && m != true) {
            $("#kw2").val(m);
            $("#kw2").css("color", "black");
        }
        if (s && s != true)
            $("#tj2 option[value='" + s + "']").attr("selected", true);
        //zhaodian end

        mashup(document.getElementById("d1"));
        mashup(document.getElementById("d2"));
    }
    catch (err) {
    }

});
