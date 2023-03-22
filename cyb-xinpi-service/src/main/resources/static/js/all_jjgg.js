function processData(){
    $(document).ready(process);
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
$(function getjjggbulltein() {
   var pageindex=1
    var url = "/cyb/v1/jjgg/"+pageindex+".json"
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
                var productData = json.data.data.splice(0,8);
                var trs = "";
                trs+="<table>";
                $.each(productData, function (i, row) {
                    trs+= "<tr>";
                   // trs+='<td class="td_name" ><a href="http://stockdata.cs.com.cn/qcenter/new/stock-exponent.html?en_prod_code='+row.seccode+'.SZ">'+row.seccode+'</a></td>';
                    trs+='<td class="td_title" style="white-space: nowrap;overflow: hidden;text-overflow: ellipsis"><a href="'+row.f003v+'">'+row.f002v+'</a></td>';
                    trs+='<td class="td_date">'+formatMsToDate(row.f001d) + '</td>';
                    trs+= "</tr>";
                });
                trs+="</table>";
                $("#tb_newBulletin").html(trs);
            } else {
                var trs = "<tr><td colspan='10'>暂无数据</td></tr>";
                $("#tb_newBulletin").html(trs);
            }
        }
    });
});