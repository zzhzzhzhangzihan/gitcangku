$(function a() {
    getsjytsbulltein1("all_Today")
});
function unique(arr) {
    const res = new Map();
    return arr.filter((a) => !res.has(a.f004v) && res.set(a.f004v, 1))
}
//最新公告
function getsjytsbulltein1(url) {

    $.ajax({
        url: "/cyb/v1/"+url,
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
                var productData = unique(json.data)//今日
                var trs = "";;
                $.each(productData, function (i, row1) {
                    var b = 0;//计数器
                    trs += "<h4>" + row1.f004v + "</h4>";
                    trs +="<ul>"
                    $.each(data, function (i, row) {
                        if (row.f004v == row1.f004v) {
                            b++;
                            if (b <= 100) {
                                trs += '<li><a href="http://stockdata.cs.com.cn/qcenter/new/stock-exponent.html?en_prod_code='+row.seccode+'.SZ">' + row.secname + '</a></li>';
                            }
                        }
                    });
                    trs +="</ul>"
                });

                    $("#jrts_div_JRTS").html(trs);
            } else {
                var trs = "<tr><td colspan='10'>暂无数据</td></tr>";
                $("#jrts_div_JRTS").html(trs);
            }
        }
    });
}


