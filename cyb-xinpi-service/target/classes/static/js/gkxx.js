function processData(){
	$(document).ready(process);
}
function process(){
	if(gkxx&&gkxx.list){
		var data=gkxx.list;
		var table="<table>";
		var trs="";
		for(var i=0;i<data.length;i++){
			trs+='<tr><td class="title title-dot"><a href="gkxx_detail.html?p=json/gkxxdtl/'+data[i].jsonpath+'">'+data[i].Title+'</a></td>';
			trs+='<td class="date">'+data[i].time+'</td></tr>';
		}
		var pages=pager(page_NO,gkxx.pages-1);
		trs+='<tr><th colspan="3" class="td_page"><div class="pagebox"><span><a href="'+filename+'?p='+(page_NO-1)+'" target="_self">&lt;</a></span>';
		for(var i=0;i<pages.length;i++){
			trs+='<span'+pages[i].active+'>'+(pages[i].active==''?'<a href="'+filename+'?p='+pages[i].pageno+'" target="_self">'+pages[i].pageDisp+'</a>':pages[i].pageDisp)+'</span>';
		}
		trs+='<span><a href="'+filename+'?p='+(page_NO>=gkxx.pages-1?gkxx.pages-1:page_NO-0+1)+'" target="_self">&gt;</a></span></div></th></tr>';
		table+=trs+"</table>";
		$("#gkxx_ul").html(table);$("#gkxx_ul").removeClass("hide");
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