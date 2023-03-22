//去重
function unique(arr) {
    const res = new Map();
    return arr.filter((a) => !res.has(a.f004v) && res.set(a.f004v, 1))
}

$(function getsjyts_ybulltein() {
    $.ajax({
        url: "/cyb/v1/all_Yesterday",
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
                var data1 = json.data//昨日
                var productData = unique(json.data).splice(0, 3)//昨日
                var  trs=""
                trs += "<ul>"
                trs += "<table>";
                $.each(productData, function (i, row1) {
                    var a =0;
                    var b = 0;//计数器
                    if (a<2) {
                        trs += "<tr>"
                        trs += "<th>" + row1.f004v + "</th>";
                        trs += "<td>"
                        $.each(data1, function (i, row) {
                            if (row.f004v == row1.f004v) {
                                b++;
                                if (b <= 6) {
                                    trs += '<a href="">' + row.secname + '</a>';
                                }
                            }
                        });
                        trs += "</td>"
                        a++;
                    }else {
                        trs += "<tr class=\"noborder\">"
                        trs += "<th>" + row1.f004v + "</th>";
                        trs += "<td>"
                        $.each(data1, function (i, row) {
                            if (row.f004v == row1.f004v) {
                                b++;
                                if (b <= 6) {
                                    trs += '<a href="">' + row.secname + '</a>';
                                }
                            }
                        });

                    }

                    trs += "</tr>"
                });
                trs += "</table>";
                trs += "</ul>"
                $("#jrtse_y_div").html(trs);

            } else {
                var trs = "<tr><td colspan='10'>暂无数据</td></tr>";
                $("#jrtse_y_div").html(trs);
            }
        }
    });
});