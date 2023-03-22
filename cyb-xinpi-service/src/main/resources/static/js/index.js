function processData(){
	$(document).ready(process);
}
function process(){
	if(index&&index.indexJjgg){
		var data=index.indexJjgg;
		var table="<table>";
		var trs="";
		for(var i=0;i<data.length&&i<8;i++){
			var url=data[i].pUrl;
			if(data[i].PUB_TYPE=='SJSGG'&&data[i].pUrl2nd&&data[i].pUrl2nd.indexOf(".js")!=-1){
				var pos=(typeof(mainindexpos)!="undefined"&&mainindexpos)?mainindexpos:"";
				url=pos+"gsgg_detail.html?p=json/gsggdtl"+data[i].pUrl2nd;
			}
//			trs+='<tr><td class="td_code"><a href="'+url+'">'+data[i].CODE+'</a></td>';
			trs+='<td class="td_title"><a href="'+url+'">'+data[i].Title+'</a></td>';
			trs+='<td class="td_date">'+data[i].time+'</td></tr>';
		}
		table+=trs+"</table>";
		$("#jjggdiv").html(table);$("#jjggdiv").removeClass("hide");
	}
	if(index&&index.index_gsgg_qt){
		var data=index.index_gsgg_qt;
		var table="<table>";
		var trs="";
		for(var i=0;i<data.length&&i<8;i++){
var title = data[i].Title;
title = title.length>30? title.substring(0,25)+"......": title;
			var u="http://stockdata.cs.com.cn/qcenter/new/stock-exponent.html?en_prod_code="+data[i].CODE+".SZ";
			trs+='<tr><td class="td_name"><a href="'+u+'">'+data[i].JC+'</a></td>';
			trs+='<td class="td_title"><a href="'+data[i].pUrl+'" alt="'+data[i].Title+'">'+title+'</a></td>';
			trs+='<td class="td_date">'+data[i].time+'</td></tr>';
		}
		table+=trs+"</table>";
		$("#gsggdiv").html(table);$("#gsggdiv").removeClass("hide");
	}
	if(index&&index.index_nssgsgg_ypl){
		var data=index.index_nssgsgg_ypl;
		var table="<table>";
		var trs="";
		for(var i=0;i<data.length&&i<8;i++){
			trs+='<tr><td class="td_title"><a href="'+data[i].pUrl+'">'+data[i].Title+'</a></td>';
			trs+='<td class="td_date">'+data[i].time+'</td></tr>';
		}
		table+=trs+"</table>";
		$("#nssgsggdiv").html(table);$("#nssgsggdiv").removeClass("hide");
	}
	if(index&&index.index_sjsgg){
		var data=index.index_sjsgg;
		var table="<table>";
		var trs="";
		for(var i=0;i<data.length&&i<8;i++){
			var url=data[i].pUrl;
			if(data[i].PUB_TYPE=='SJSGG'&&data[i].pUrl2nd&&data[i].pUrl2nd.indexOf(".js")!=-1){
				var pos=(typeof(mainindexpos)!="undefined"&&mainindexpos)?mainindexpos:"";
				url=pos+"gsgg_detail.html?p=json/gsggdtl"+data[i].pUrl2nd;
			}
			trs+='<tr><td class="td_title"><a href="'+url+'">'+data[i].Title+'</a></td>';
			trs+='<td class="td_date">'+data[i].time+'</td></tr>';
		}
		table+=trs+"</table>";
		$("#sjsggdiv").html(table);$("#sjsggdiv").removeClass("hide");
	}
	if(index&&index.index_zjhgg){
		var data=index.index_zjhgg;
		var table="<table>";
		var trs="";
		for(var i=0;i<data.length&&i<8;i++){
			trs+='<tr><td class="td_title"><a href="'+data[i].pUrl+'">'+data[i].Title+'</a></td>';
			trs+='<td class="td_date">'+data[i].time+'</td></tr>';
		}
		table+=trs+"</table>";
		$("#zjhggdiv").html(table);$("#zjhggdiv").removeClass("hide");
	}
	if(index&&index.index_fsgg){
		var data=index.index_fsgg;
		var table="<table>";
		var trs="";
		for(var i=0;i<data.length&&i<8;i++){
			trs+='<tr><td class="td_title"><a href="'+data[i].pUrl+'">'+data[i].Title+'</a></td>';
			trs+='<td class="td_date">'+data[i].time+'</td></tr>';
		}
		table+=trs+"</table>";
		$("#fsggdiv").html(table);$("#fsggdiv").removeClass("hide");
	}	
	if(index&&index.indexTfp){
		var data=index.indexTfp;
		var table="<table>";
		var trs='<tr><th>代码</th><th class="name">简称</th><th>停牌时间</th><th>复牌时间</th><th>停牌日期</th></tr>';
		for(var i=0;i<data.length&&i<8;i++){
			trs+='<tr><td>'+data[i].code+'</td><td class="name"><a href="http://stockdata.cs.com.cn/qcenter/new/stock-exponent.html?en_prod_code='+data[i].code+'.SZ">'+data[i].jc+'</a></td>';
			trs+='<td>'+data[i].tpsj+'</td><td>'+data[i].fpsj+'</td><td>'+data[i].rq+'</td></tr>';
		}
		table+=trs+"</table>";
		$("#tfpdiv").html(table);$("#tfpdiv").removeClass("hide");
	}
}