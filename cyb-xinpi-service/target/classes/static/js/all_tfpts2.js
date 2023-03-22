//该方法用于给日期、时间补零
addZero = function (num) {
    if (parseInt(num) < 10) {
        num = "0" + num
    }
    return num
};
//该方法把毫秒数转化成具体日期
//参数 毫秒值
function formatMsToDate2 () {

        var oDate = new Date(),
            oYear = oDate.getFullYear(),
            oMonth = oDate.getMonth() + 1,
            oDay = oDate.getDate(),
            oHour = oDate.getHours(),
            oMin = oDate.getMinutes(),
            oSen = oDate.getSeconds(),
            oTime =  addZero(oMonth) + '月' + addZero(oDay) + '日'
        return oTime;

};
function formatMsToDate1 (ms) {
    if (ms) {
        var oDate = new Date(ms),
            oYear = oDate.getFullYear(),
            oMonth = oDate.getMonth() + 1,
            oDay = oDate.getDate(),
            oHour = oDate.getHours(),
            oMin = oDate.getMinutes(),
            oSen = oDate.getSeconds(),
            oTime = addZero(oMonth) + '月' + addZero(oDay)+'日';
        // ' + addZero(oHour) + ':' +
        // addZero(oMin) + ':' + addZero(oSen);
        return oTime;
    } else {
        return ""
    }
};
function formatMsToDate3 (ms) {
    if (ms) {
        var oDate = new Date(ms),
            oYear = oDate.getFullYear(),
            oMonth = oDate.getMonth() + 1,
            oDay = oDate.getDate(),
            oHour = oDate.getHours(),
            oMin = oDate.getMinutes(),
            oSen = oDate.getSeconds(),
            oTime =   addZero(oHour) + ':' +
                addZero(oMin) ;
        return oTime;
    } else {
        return ""
    }
};




//最新公告
$(function gettfpbulltein() {
    var url = "/cyb/v1/all_F004V"
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
            if (json != null && json.data.length > 0) {
                console.log(json)
                var productData = json.data;
                var trs = "";
                trs+=' <h3><span class="blueline">停复牌提示</span><span class="grey hide" id="tfpdate">最后更新时间：'+formatMsToDate2()+'</span></h3>'
                trs+="<table>";
                trs+='<tr>'
                trs+='<th>代码</th>'
                trs+='<th>简称</th>'
                trs+='<th>停牌日期</th>'
                trs+='<th>停牌时间</th>'
                trs+='<th>复牌时间</th>'
                trs+='<th>停复牌原因</th>'
                trs+='</tr>'
                $.each(productData, function (i, row) {
                    trs+= "<tr>";
                    trs+='<td><a href="">'+row.seccode+'</a></td>';
                    trs+='<td class="name"><a href=http://stockdata.cs.com.cn/qcenter/new/stock-exponent.html?en_prod_code='+row.seccode+'.SZ>'+row.secname+'</a></td>';
                        trs+='<td>'+formatMsToDate1(row.tdata)+'</td>';
                        trs+='<td>'+formatMsToDate3(row.tdata)+'</td>';
                        trs+='<td>'+formatMsToDate1(row.fdata)+'</td>';
                        trs+='<td>'+row.cause+'</td>';
                    trs+= "</tr>";
                });
                trs+="</table>";
                $("#tfp_ul").html(trs);
            } else {
                var trs = "<tr><td colspan='10'>暂无数据</td></tr>";
                $("#tfp_ul").html(trs);
            }
        }
    });
});