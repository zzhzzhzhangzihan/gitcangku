//该方法用于给日期、时间补零
addZero = function (num) {
    if (parseInt(num) < 10) {
        num = "0" + num
    }
    return num
};
//该方法把毫秒数转化成具体日期
//参数 毫秒值
function formatMsToDate4(ms) {
    if (ms) {
        var oDate = new Date(ms),
            oYear = oDate.getFullYear(),
            oMonth = oDate.getMonth() + 1,
            oDay = oDate.getDate(),
            oHour = oDate.getHours(),
            oMin = oDate.getMinutes(),
            oSen = oDate.getSeconds(),
            oTime = oYear + '年' + addZero(oMonth) + '月' + addZero(oDay) + '日';
        // ' + addZero(oHour) + ':' +
        // addZero(oMin) + ':' + addZero(oSen);
        return oTime;
    } else {
        return ""
    }
}


function unique(arr) {
    const res = new Map();
    return arr.filter((a) => !res.has(a.f004v) && res.set(a.f004v, 1))
}

//最新公告
$(function getsjytsbulltein() {
    $.ajax({
        url: "/cyb/v1/all_Today",
        type: "GET",
        dataType: "json",
        error: function (json) {
            //  $("#loading").html("没有数据");
        },
        beforeSend: function () {
            // $("#loading").html(loadingIMG);
        },
        success: function (json) {
            if (json != null && json.data.length > 0) {
                var data = json.data//今日
                var productData = unique(json.data).splice(0, 3)//今日
                var trs = "";
                trs += "<ul>"
                trs += "<table>";
                $.each(productData, function (i, row1) {
                    var b = 0;//计数器
                    trs += "<tr>"
                    trs += "<th>" + row1.f004v + "</th>";
                    trs += "<td>"
                    $.each(data, function (i, row) {
                        if (row.f004v == row1.f004v) {
                            b++;
                            if (b <= 6) {
                                trs += '<a href="http://stockdata.cs.com.cn/qcenter/new/stock-exponent.html?en_prod_code='+row.seccode+'.SZ">' + row.secname + '</a>';
                            }
                        }
                    });
                    trs += "</td>"
                    trs += "</tr>"
                });
                trs += "</table>";
                trs += "</ul>"

                $("#jrtse_div").html(trs);
            } else {
                var trs = "<tr><td colspan='10'>暂无数据</td></tr>";
                $("#jrtse_div").html(trs);
            }
        }
    });
});