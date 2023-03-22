function processData(){
	$(document).ready(process);
}
function process(){
	if(gg&&gg.list){
		var data=gg.list;
		var table="<table>";
		var trs="";
		for(var i=0;i<data.length;i++){
			var url=data[i].pUrl;
			if(data[i].PUB_TYPE=='SJSGG'&&data[i].pUrl2nd&&data[i].pUrl2nd.indexOf(".js")!=-1){
				url="gsgg_detail.html?p=json/gsggdtl"+data[i].pUrl2nd;
			}
			if(data[i].CODE){
				var u="http://stockdata.cs.com.cn/qcenter/new/stock-exponent.html?en_prod_code="+data[i].CODE+".SZ";
				trs+='<tr><td class="code"><a href="'+u+'">'+data[i].CODE+'</a></td>';
			}
			trs+='<td class="title"><a href="'+url+'">'+data[i].JC+data[i].MH+data[i].Title+'<img src="images/dot_pdf.gif"</a></td>';
			trs+='<td class="date">'+data[i].time+'</td></tr>';
		}
		var pages=pager(page_NO,gg.pages-1);
		trs+='<tr><th colspan="3" class="td_page"><div class="pagebox"><span><a href="'+filename+'?p='+(page_NO-1)+'" target="_self">&lt;</a></span>';
		for(var i=0;i<pages.length;i++){
			trs+='<span'+pages[i].active+'>'+(pages[i].active==''?'<a href="'+filename+'?p='+pages[i].pageno+'" target="_self">'+pages[i].pageDisp+'</a>':pages[i].pageDisp)+'</span>';
		}
		trs+='<span><a href="'+filename+'?p='+(page_NO>=gg.pages-1?gg.pages-1:page_NO-0+1)+'" target="_self">&gt;</a></span></div></th></tr>';
		table+=trs+"</table>";
		$("#gsgg_ul").html(table);$("#gsgg_ul").removeClass("hide");
	}
}
function pager(pageNo,pageCount){
	var arr=[];
	for(var i=0;i<7;i++){
		if(pageNo<3){
			arr.push({'pageno':i,'pageDisp':i+1,'active':(pageNo==i?' class="on"':'')});
		}else if(pageNo>=3&&pageNo<=pageCount-3){
			arr.push({'pageno':pageNo-3+i,'pageDisp':pageNo-3+i+1,'active':(pageNo==(pageNo-3+i)?' class="on"':'')});
		}else if(pageNo>pageCount-3){
			arr.push({'pageno':pageCount-6+i,'pageDisp':pageCount-6+i+1,'active':(pageNo==(pageCount-6+i)?' class="on"':'')});
		}
	}
	return arr;
}