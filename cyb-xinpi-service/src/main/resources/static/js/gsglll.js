$(function () {
    getgsgl(1); //加载交易所公告数据
});

//最新公告

function getgsgl(pageindex) {
    var url = "/cyb/v1/gsgl/" + pageindex + ".json"
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
            if (gs != null && gs.data.data.length > 0) {
                var data = gs.data.data;
                var trs = "";
                trs += "<table>";
                trs += "<tr>";
                var b = false
                for (var i = 0; i < data.length; i++) {
                    trs += '<td><a href="http://stockdata.cs.com.cn/qcenter/new/stock-exponent.html?en_prod_code=' + data[i].seccode + '.SZ">' + data[i].secname + '</a><br>[ ' + data[i].seccode + ' ]</td>';

                    // trs += '<td><a href="http://stockdata.cs.com.cn/qcenter/new/stock-exponent.html?en_prod_code=' + row.seccode + '.SZ">' + row.secname + '</a><br>[ ' + row.seccode + ' ]</td>';
                    if ((i + 1) % 6 == 0) {
                        trs += "</tr>";
                        b = true
                    } else {
                        b = false;
                    }
                }
                if (!b) trs += "</tr>";
                trs += "</table>";
                trs += p_i_gsgl(pageindex, gs.data.total)
                $("#tb_gsgl").html(trs);
                init_gsgl(pageindex, gs.total)
            } else {
                var trs = "<tr><td colspan='10'>暂无数据</td></tr>";
                $("#tb_gsgl").html(trs);
            }
        }
    });
}

/*function getgsgl(pageindex) {
    var url = "http://localhost:8080/cyb/v1/gsgl/"+pageindex+".json"
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
                var data=gs.data;
                var trs="<table>";
                var trs="<tr>";var b=false;
                for(var i=0;i<data.length;i++){
                    trs+='<td><a href="http://stockdata.cs.com.cn/qcenter/new/stock-exponent.html?en_prod_code='+data[i].seccode+'.SZ">'+data[i].secname+'</a><br>[ '+data[i].seccode+' ]</td>';
                    if((i+1)%6==0){
                        trs+="</tr>";b=true;
                    }else{b=false;}
                }
                if(!b)trs+="</tr>";
              //  var pages=pager(data.currentPage,data.size-1);
              //  trs+='<tr><th colspan="3" class="td_page"><div class="pagebox"><span><a href="'+'+gsgl.html+'+'?p='+(data.currentPage-1)+'" target="_self">&lt;</a></span>';
              //  for(var i=0;i<pages.length;i++){
               //     trs+='<span'+pages[i].active+'>'+(pages[i].active==''?'<a href="'+'+gsgl.html+'+'?p='+pages[i].pageno+'" target="_self">'+pages[i].pageDisp+'</a>':pages[i].pageDisp)+'</span>';
             //   }
             //   trs+='<span><a href="'+'+gsgl.html+'+'?p='+(data.currentPage>=data.size-1?data.size-1:data.currentPage-0+1)+'" target="_self">&gt;</a></span></div></th></tr>';
                trs+="</table>";
               trs+= p_i_gsgl(pageindex,gs.total)
                $("#tb_gsgl").html(trs);
                init_gsgl(pageindex,gs.total)
            } else {
                var trs = "<tr><td colspan='10'>暂无数据</td></tr>";
                $("#tb_gsgl").html(trs);
            }
        }
    });
}*/
function init_gsgl() {
    $("a[name='f_p']").each(function () {
        $(this).click(function () {
            var m = $(this).attr('lay');
            getgsgl(m);
            return false;
        });
    })
    $("#go_1").click(function () {
        var index = parseInt($("#text_page_num").val());
        var total = parseInt($(this).attr("lay"));
        if (index > total || index < 1) {
            return false;
        }
        getgsgl(index);
        return false;
    });
}

function p_i_gsgl(page, total) {
    var retunt = "";
    retunt += "<tr><th  colspan=\"3\" class=\"td_page\"><div class=\"pagebox\"><div class=\"11111\"";
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
        if ((page + i) < total / 90) {
            retunt += "<span><a name='f_p' lay='" + (parseFloat(page) + i) + "' href=\"#\">" + (parseFloat(page) + i) + "</a></span>";
        }
    }
    retunt += "...";
    retunt += "<span><a name='f_p' lay='" + Math.ceil(total / 90) + "' href=\"#\">" + Math.ceil(total / 90) + "</a></span>";
    if (page < total) {
        retunt += "<span><a name='f_p' lay='" + (parseFloat(page) + 1) + "' href=\"#\">&gt;</a></span>";
    }
    retunt += "<span><a name='f_p' lay='" + total + "' href=\"#\">&gt;&gt;</a></span>";
    retunt += "转到：<input  type=\"text\"  id='text_page_num'/>";



    retunt += "<span class=\"search\"><a id='go_1' lay='" + total + "' href=\"#\">Go</a></span>";
    retunt += "</div></div></th>";
    retunt += "</tr>";
    return retunt;
}
