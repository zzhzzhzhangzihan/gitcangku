function checkContentNew() {
    // 判断检索条件全为空的情况
    var content = $("#s").val();

    if (content == null || content == "请输入代码/简称/拼音/关键字..." || content == "") {
        alert("未找到该股票代码");
    } else {
        window.open("http://xinpi.cs.com.cn/page/xp/gaojiSousuoJG.html?t=b&st=&et=&c=" + content + "&q=&m=&s=");
    }
}

function checkContentNew1() {
    // 判断检索条件全为空的情况
    var content = $("#kxtkw").val();

    if (content == null || content == "请输入代码/简称/拼音/关键字..." || content == "") {
        alert("未找到该股票代码");
    } else {
        window.open("http://xinpi.cs.com.cn/page/xp/gaojiSousuoJG.html?t=b&st=&et=&c=" + content + "&q=&m=&s=");
    }
}

$(function () {
    initcode($("#s"));
    initcode_gsgl($("#ss"));
});
function initcode(obj) {
    obj.autocomplete(company, {
        multiple: false,
        dataType: "json", max: 15, width: 180, scrollHeight: 290, matchContains: true,
        parse: function (data) {
            return $.map(data, function (row) {
                return {
                    data: row,
                    value: row.code,
                    result: row.code + " | " + row.jc + " | " + row.jp
                }
            });
        }, formatMatch: function (row, i, max) {
            return row.code + row.jc + row.jp;
        }, formatItem: function (row, i, max) {
            return row.code + " | " + row.jc + " | " + row.jp;
        }, formatResult: function (row, i, max) {
            return row.code;
        },
    }).result(function (event, row) {
        window.open("http://xinpi.cs.com.cn/page/xp/gaojiSousuoJG.html?t=b&st=&et=&c=" + row.code + "&q=&m=&s=");
    });

}
//
function createTarget() {
    if (window.ActiveXObject) {
        return new ActiveXObject("Microsoft.XMLHTTP");
    } else if (window.XMLHttpRequest) {
        return new XMLHttpRequest();
    }
    return null;
}

function testURL(fobj) {//alert(url);
    var target = createTarget();
    target.onreadystatechange = function () {
        if (target.readyState == 4) {
            if (target.status == 200) {
                //alert("页面存在");
            } else {
                //alert("页面不存在");
                fobj.src = fobj.src.substr(1);
            }
        }
    }
    target.open("get", fobj.src, true);
    target.send(null);
}

function changeiframe() {
    var frs = document.getElementsByTagName("iframe");
    for (var i = 0; i < frs.length; i++) {
        testURL(frs[i]);
    }
}

//公司概览检索
function gsgl_sousuo() {
    location.replace("http://localhost:63342/xinpi-parent/xinpi-service/chinext.cs.com.cn/cyb2nd/gsgl_sousuo.html")
    var elementsByName = document.getElementsByName("ss").value;
    alert($("#ss"))
    alert(elementsByName)
}
/*$(function abc() {
    var url = "http://localhost:8080/cyb/v1/all_search_gs"
    $.ajax({
        url: url,
        type: "GET",
        dataType: "json",
        error: function (json) {
            //  $("#loading").html("没有数据");
        },
        beforeSend: function () {
            // $("#loading").html(loadingIMG);

        },
        success: function (gs) {
            if (gs != null && gs.data.length > 0) {
                var data = gs.data;
                var trs = "";
                trs +="<table>";
                trs += "<tr>";
                trs += '<td><a href="http://stockdata.cs.com.cn/qcenter/new/stock-exponent.html?en_prod_code='+data.seccode+'.SZ">'+data.secname+'</a><br>[ '+data.seccode+' ]</td>';
                trs += "</tr>";
                trs += "</table>";
                $("#tb_gsgl_sousuo").html(trs);
            } else {
                var trs = "<tr><td colspan='10'>暂无数据</td></tr>";
                $("#tb_gsgl_sousuo").html(trs);
            }
        }
    });
});*/

//公司概览下拉框
function initcode_gsgl(obj) {
    obj.autocomplete(company, {
        multiple: false,
        dataType: "json", max: 15, width: 180, scrollHeight: 290, matchContains: true,
        parse: function (data) {
            return $.map(data, function (row) {
                return {
                    data: row,
                    value: row.code,
                    result: row.code + " | " + row.jc + " | " + row.jp
                }
            });
        }, formatMatch: function (row, i, max) {
            return row.code + row.jc + row.jp;
        }, formatItem: function (row, i, max) { w
            return row.code + " | " + row.jc + " | " + row.jp;
        }, formatResult: function (row, i, max) {
            return row.code;
        },
    }).result(function (event, row) {
        location.replace("http://localhost:63342/xinpi-parent/xinpi-service/chinext.cs.com.cn/cyb2nd/gsgl_sousuo.html")

    });

}