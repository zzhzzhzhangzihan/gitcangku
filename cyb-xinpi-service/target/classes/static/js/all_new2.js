function processData(){
    $(document).ready(getNew2bulltein);
}
//该方法用于给日期、时间补零
addZero = function (num) {
    if (parseInt(num) < 10) {
        num = "0" + num
    }
    return num
};
//该方法把毫秒数转化成具体日期
//参数 毫秒值
function formatMsToDate (ms) {
    if (ms) {
        var oDate = new Date(ms),
            oYear = oDate.getFullYear(),
            oMonth = oDate.getMonth() + 1,
            oDay = oDate.getDate(),
            oHour = oDate.getHours(),
            oMin = oDate.getMinutes(),
            oSen = oDate.getSeconds(),
            oTime = oYear + '-' + addZero(oMonth) + '-' + addZero(oDay);
        // ' + addZero(oHour) + ':' +
        // addZero(oMin) + ':' + addZero(oSen);
        return oTime;
    } else {
        return ""
    }
};
//最新公告
$(function () {
    getNew2bulltein(1); //加载交易所公告数据
});
function getNew2bulltein(pageindex) {
      var url = "/cyb/v1/new/"+pageindex+".json"
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
        success: function (json) {
            if (json != null && json.data.data.length > 0) {
                console.log(json)
                var productData = json.data.data.splice(0,20);
                var trs = "";
                trs+="<table>";
                $.each(productData, function (i, row) {
                    trs+= "<tr>";
                    trs+='<td class="code" ><a href="http://stockdata.cs.com.cn/qcenter/new/stock-exponent.html?en_prod_code='+row.seccode+'.SZ">'+row.seccode+'</a></td>';
                    if (row.f004v=="HTML"){
                        trs+='<td class="td_title" ><a href="'+row.f003v+'">'+row.f002v+'<svg t="1666575090636" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4680" width="16" height="16"><path d="M332.799002 686.081014m-332.799002 0a332.799002 332.799002 0 1 0 665.598003 0 332.799002 332.799002 0 1 0-665.598003 0Z" fill="#FFDCEE" p-id="4681"></path><path d="M883.19735 1024h-639.99808A141.055577 141.055577 0 0 1 102.399693 883.200422v-742.397772A141.055577 141.055577 0 0 1 243.19927 0.003072h516.350451a89.087733 89.087733 0 0 1 63.231811 25.599923l189.695431 189.695431A38.399885 38.399885 0 0 1 1023.996928 243.202342v639.99808a141.055577 141.055577 0 0 1-140.799578 140.799578zM243.19927 76.802842A63.999808 63.999808 0 0 0 179.199462 140.80265v742.397772A63.999808 63.999808 0 0 0 243.19927 947.20023h639.99808a63.999808 63.999808 0 0 0 63.999808-63.999808V259.074295l-179.199462-179.199463a12.799962 12.799962 0 0 0-8.447975-3.07199z" fill="#434260" p-id="4682"></path><path d="M198.655404 399.105875h35.583893v92.159723h96.76771v-92.159723h35.583893v226.047322h-35.583893v-102.399693H234.239297v102.399693H198.655404zM451.582645 428.801786H383.998848v-29.695911h168.447495v29.695911H486.398541v196.095411h-34.815896zM577.534267 399.105875h40.959878L659.710021 512.001536c5.119985 15.103955 9.727971 30.463909 15.103955 45.823863h1.535995c5.119985-15.359954 9.471972-30.719908 14.847955-45.823863l40.959878-114.175657h40.959877v226.047321h-33.279901V512.001536c0-20.223939 2.815992-49.407852 4.607987-69.88779l-18.175946 51.199846-39.423882 107.775677h-22.015933l-39.679881-107.775677-17.919947-51.199846c1.535995 20.479939 4.351987 49.663851 4.351987 69.88779v111.871664h-34.047898zM796.66961 399.105875h35.583893v195.839412h95.487714v30.20791h-131.071607z" fill="#434260" p-id="4683"></path></svg></a></td>';
                    } else {
                        trs+='<td class="td_title" ><a href="'+row.f003v+'">'+row.f002v+'<img src="images/dot_pdf.gif"></a></td>';
                    }

                    trs+='<td class="date">'+formatMsToDate(row.f001d) + '</td>';
                    trs+= "</tr>";
                });
                trs+="</table>";
               trs+= p_i_new(pageindex,json.data.total)

                $("#gsgg_ul").html(trs);
                init_new(pageindex,json.data.total)
            } else {
                var trs = "<tr><td colspan='10'>暂无数据</td></tr>";
                $("#gsgg_ul").html(trs);
            }
        }
    });
}
function init_new() {
    $("a[name='f_p']").each(function () {
        $(this).click(function () {
            var m = $(this).attr('lay');
            getNew2bulltein(m);
            return false;
        });
    })
    $("#go_1").click(function () {
        var index = parseInt($("#text_page_num").val());
        var total = parseInt($(this).attr("lay"));
        if (index > total || index < 1) {
            return false;
        }
        getNew2bulltein(index);
        return false;
    });
}
function p_i_new(page, total) {
    var retunt = "";
    retunt += "<tr><th  colspan=\"3\" class=\"td_page\"><div class=\"pagebox\">";
    if (page > 1) {
        retunt += "<span><a name='f_p' lay='1' href='#'>&lt;&lt;</a></span>";
        retunt += "<span><a name='f_p' lay='" + (page - 1) + "' href='#'>&lt;</a></span>";
        if (page == 2) {
            retunt += "<span><a name='f_p' lay='" + (page - 1) + "' href=\"#\">" + (page - 1) + "</a></span>";
        } else {
            if (page > 2) {
                for (i = 2; i > 0; i--) {
                    if ((page - i) > 0) {
                        retunt += "<span><a name='f_p' lay='" + (page - i) + "' href=\"#\">" + (page - i) + "</a></span>";
                    }
                }
            }
        }
    }
    retunt += "<span class=\"on\">" + page + "</span>";
    for (i = 1; i < 3; i++) {
        if ((page + i) < total/20) {
            retunt += "<span><a name='f_p' lay='" + (parseFloat(page) + i) + "' href=\"#\">" + (parseFloat(page) + i) + "</a></span>";
        }
    }
    retunt += "...";
    retunt += "<span><a name='f_p' lay='" + Math.ceil(total/20) + "' href=\"#\">" + Math.ceil(total/20) + "</a></span>";
    if (page < total) {
        retunt += "<span><a name='f_p' lay='" + (parseFloat(page) + 1) + "' href=\"#\">&gt;</a></span>";
    }
    retunt += "<span><a name='f_p' lay='" + total + "' href=\"#\">&gt;&gt;</a></span>";
    retunt += "转到：<input  type=\"text\"  id='text_page_num'/>";
    retunt += "<span class=\"search\"><a id='go_1' lay='" + total + "' href=\"#\">Go</a></span>";
    retunt += "</div></th>";
    retunt += "</tr>";
    return retunt;
}