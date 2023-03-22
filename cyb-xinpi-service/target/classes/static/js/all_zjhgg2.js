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
$(function () {
    getzjhgg2bulltein(1); //加载交易所公告数据
});

//证监会公告
function getzjhgg2bulltein(pageindex) {
    var url = "/cyb/v1/zjhgg/"+pageindex+".json"
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
                    // trs+='<td class="td_name" ><a href="http://stockdata.cs.com.cn/qcenter/new/stock-exponent.html?en_prod_code='+row.seccode+'.SZ">'+row.seccode+'</a></td>';
                    if (row.f004v=="HTML"){
                        trs+='<td ><a href="'+row.f003v+'">'+row.f002v+'<svg t="1666575090636" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4680" width="16" height="16"><path d="M332.799002 686.081014m-332.799002 0a332.799002 332.799002 0 1 0 665.598003 0 332.799002 332.799002 0 1 0-665.598003 0Z" fill="#FFDCEE" p-id="4681"></path><path d="M883.19735 1024h-639.99808A141.055577 141.055577 0 0 1 102.399693 883.200422v-742.397772A141.055577 141.055577 0 0 1 243.19927 0.003072h516.350451a89.087733 89.087733 0 0 1 63.231811 25.599923l189.695431 189.695431A38.399885 38.399885 0 0 1 1023.996928 243.202342v639.99808a141.055577 141.055577 0 0 1-140.799578 140.799578zM243.19927 76.802842A63.999808 63.999808 0 0 0 179.199462 140.80265v742.397772A63.999808 63.999808 0 0 0 243.19927 947.20023h639.99808a63.999808 63.999808 0 0 0 63.999808-63.999808V259.074295l-179.199462-179.199463a12.799962 12.799962 0 0 0-8.447975-3.07199z" fill="#434260" p-id="4682"></path><path d="M198.655404 399.105875h35.583893v92.159723h96.76771v-92.159723h35.583893v226.047322h-35.583893v-102.399693H234.239297v102.399693H198.655404zM451.582645 428.801786H383.998848v-29.695911h168.447495v29.695911H486.398541v196.095411h-34.815896zM577.534267 399.105875h40.959878L659.710021 512.001536c5.119985 15.103955 9.727971 30.463909 15.103955 45.823863h1.535995c5.119985-15.359954 9.471972-30.719908 14.847955-45.823863l40.959878-114.175657h40.959877v226.047321h-33.279901V512.001536c0-20.223939 2.815992-49.407852 4.607987-69.88779l-18.175946 51.199846-39.423882 107.775677h-22.015933l-39.679881-107.775677-17.919947-51.199846c1.535995 20.479939 4.351987 49.663851 4.351987 69.88779v111.871664h-34.047898zM796.66961 399.105875h35.583893v195.839412h95.487714v30.20791h-131.071607z" fill="#434260" p-id="4683"></path></svg></a></td>';
                    }else if (row.f004v=="PDF") {
                        trs += '<td ><a href="' + row.f003v + '">' + row.f002v + '<img src="images/dot_pdf.gif"></a></td>';
                    }else if (row.f004v=="DOC"||row.f004v=="DOCX") {
                        trs += '<td ><a href="' + row.f003v + '">' + row.f002v + '<svg t="1666575148079" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="5662" width="16" height="16"><path d="M354.40128 0c-87.04 0-157.44 70.55872-157.44 157.59872v275.68128H78.72c-21.6576 0-39.36256 17.69984-39.36256 39.36256v236.31872c0 21.6576 17.69984 39.35744 39.36256 39.35744h118.24128v118.08256c0 87.04 70.4 157.59872 157.44 157.59872h472.63744c87.04 0 157.59872-70.55872 157.59872-157.59872V315.0336c0-41.74848-38.9888-81.93024-107.52-149.27872l-29.11744-29.12256L818.87744 107.52C751.5392 38.9888 711.39328 0 669.59872 0H354.4064z m0 78.72h287.20128c28.35456 7.0912 27.99616 42.1376 27.99616 76.8v120.16128c0 21.6576 17.69984 39.35744 39.36256 39.35744h118.07744c39.38816 0 78.87872-0.0256 78.87872 39.36256v512c0 43.32032-35.55328 78.87872-78.87872 78.87872H354.4064c-43.32544 0-78.72-35.5584-78.72-78.87872v-118.08256h393.91744c21.66272 0 39.36256-17.69472 39.36256-39.35744V472.64256c0-21.66272-17.69984-39.36256-39.36256-39.36256H275.68128V157.59872c0-43.32032 35.39456-78.87872 78.72-78.87872z m15.67744 419.35872c23.04 0 41.28256 8.00256 54.72256 24.00256 14.08 15.36 21.12 37.43744 21.12 66.23744 0 29.44-7.04 51.84-21.12 67.2-13.44 15.36-31.68256 23.04-54.72256 23.04-23.68 0-42.24-7.35744-55.68-22.07744-13.44-15.36-20.15744-38.08256-20.15744-68.16256 0-29.44 6.71744-51.84 20.15744-67.2s32-23.04 55.68-23.04z m186.24 0.96256c17.92 0 33.28 3.2 46.08 9.6l-9.6 19.2c-12.16-6.4-24.32-9.6-36.48-9.6-16.64 0-30.39744 6.4-41.27744 19.2-10.24 12.16-15.36 29.44-15.36 51.84 0 23.04 4.79744 40.63744 14.39744 52.79744 9.6 11.52 23.68 17.28 42.24 17.28 10.24 0 23.36256-2.23744 39.36256-6.71744v19.2c-11.52 4.48-25.92256 6.71744-43.20256 6.71744-23.68 0-42.24-7.35744-55.68-22.07744-13.44-15.36-20.15744-38.08256-20.15744-68.16256 0-28.16 7.04-49.92 21.12-65.28 14.72-16 34.23744-23.99744 58.55744-23.99744z m-421.43744 1.92h48.95744c24.96 0 44.48256 7.68 58.56256 23.04 14.72 14.72 22.07744 35.84 22.07744 63.36 0 28.8-7.68 50.87744-23.04 66.23744-14.72 15.36-35.51744 23.04-62.39744 23.04h-44.16V500.96128z m235.19744 17.28c-17.28 0-30.39744 6.07744-39.35744 18.23744-8.96 11.52-13.44 28.8-13.44 51.84 0 23.68 4.48 41.6 13.44 53.76 8.96 11.52 22.07744 17.28 39.35744 17.28s30.40256-5.76 39.36256-17.28c8.96-12.16 13.44-30.08 13.44-53.76 0-23.04-4.48-40.32-13.44-51.84-8.96-12.16-22.08256-18.23744-39.36256-18.23744z m-213.12 1.92v137.27744h19.2c21.76 0 37.76-5.76 48-17.28 10.88-11.52 16.32256-28.8 16.32256-51.84s-5.12-39.99744-15.36-50.87744c-9.6-11.52-24.32-17.28-44.16-17.28h-24.00256z" p-id="5663"></path></svg></a></td>';
                    }
                    trs+='<td class="date">'+formatMsToDate(row.f001d) + '</td>';
                    trs+= "</tr>";
                });
                trs+="</table>";
                trs+=p_i_zjhgg(pageindex,json.data.total)
                $("#nssgsgg_ul").html(trs);
                init_zjhgg(pageindex,json.data.total)
            } else {
                var trs = "<tr><td colspan='10'>暂无数据</td></tr>";
                $("#nssgsgg_ul").html(trs);
            }
        }
    });
}
function init_zjhgg() {
    $("a[name='f_p']").each(function () {
        $(this).click(function () {
            var m = $(this).attr('lay');
            getzjhgg2bulltein(m);
            return false;
        });
    })
    $("#go_1").click(function () {
        var index = parseInt($("#text_page_num").val());
        var total = parseInt($(this).attr("lay"));
        if (index > total || index < 1) {
            return false;
        }
        getzjhgg2bulltein(index);
        return false;
    });
}
function p_i_zjhgg(page, total) {
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