function init(){//reporttype ggMainPage  reportdate
	var rdlist="";
	for(var i=0;i<reporttype.length;i++){
		rdlist+="<option value="+reporttype[i].Y+reporttype[i].MD+">"+reporttype[i].MDDIP+"</option>";
	}
	$("#rdlist").html(rdlist);
	$("#rdlist").change(function(){
	  asyncTab($(this).val());
	});
	asyncTab($("#rdlist").val());
}
function asyncTab(obj){
	$("#rtype").html("最后更新: "+obj.substring(0,4)+"-"+obj.substring(4,6)+"-"+obj.substring(6,8));
	var data=reportdate[obj];data=data?data:[];
	var tip="提示：点击表头可以进行排序,拖动表头边线可调整列宽。";
	var head="<tbody><tr><th title='"+tip+"'>股票代码<span></span></th><th title='"+tip+"'>股票简称<span></span></th><th title='"+tip
	+"'>首次预约日期<span></span></th><th title='"+tip+"'>第一次变更日期<span></span></th><th title='"+tip
	+"'>第二次变更日期<span></span></th><th title='"+tip+"'>第三次变更日期<span></span></th><th title='"+tip+"'>实际披露日期<span></span></th></tr></tbody>";
	var tab="<tbody>"+printTBody(data)+"</tbody>";
	tab="<table id=\"datatable\" width=\"100%\" align=\"center\">"+head+tab+"</table>"
	$("#datatable_ul").html("<div style=\"height:12px;\"></div>"+tab+"<div style=\"height:12px;\"></div>");
	initCSS();
}
function initCSS(){
	$("#datatable th").click(function(){
		$(this).parent().find("span").each(function(index, domEle){
			$(domEle).css({"display":"none"});
		});
		var span=$(this).find("span");$(span).css("display","");
		var src="";
		if($(span).find("img")&&$(span).find("img").attr("src")){
			src=$(span).find("img").attr("src");
			src=src.substring(src.lastIndexOf("/")+1);
		}
		if($(span).find("img")&&src=='desc.png'){
			$(this).find("span").html("<img src='images/asc.png' style='vertical-align:middle'>");
			tablesort(this,0);
		}else{
			$(this).find("span").html("<img src='images/desc.png' style='vertical-align:middle'>");
			tablesort(this,1);
		}
	});
	$("#datatable th").mouseover(function(){
	  $(this).css("background-color","#D0E5F5");
	});
	$("#datatable th").mouseout(function(){
	  $(this).css("background-color","#DFEFFA");
	});
}
function printTBody(data){
	var tab="";
	for(var i=0;i<data.length;i++){
		tab+="<tr><td>"+data[i]["SECUCODE"]+"</td><td><a style='color:1D5987;text-decoration:none;' target='_blank' href='http://stockdata.cs.com.cn/qcenter/new/stock-exponent.html?en_prod_code="+data[i]["SECUCODE"]+".SZ'>"+data[i]["SECUABBR"]+"</a></td><td>"
		+fmtdate(empty(data[i]["NOTICESTARTDATE"]))+"</td><td>"+fmtdate(empty(data[i]["NOTICESTARTDATE1"]))+"</td><td>"
		+fmtdate(empty(data[i]["NOTICESTARTDATE2"]))+"</td><td>"+fmtdate(empty(data[i]["NOTICESTARTDATE3"]))+"</td><td>"
		+fmtdate(empty(data[i]["ACTUALDATE"]))+"</td></tr>"
	}
	return tab;
}
function empty(obj){
	if(!obj||obj=='undefined'){
		return '';
	}
	return obj;
}
function fmtdate(obj){
	if(obj.length>=10){
		return obj.substring(0,10);
	}
	return obj;
}
function tablesort(obj,sn){
	var head=["SECUCODE","SECUABBR","NOTICESTARTDATE","NOTICESTARTDATE1","NOTICESTARTDATE2","NOTICESTARTDATE3","ACTUALDATE"];
	var data=reportdate[$("#rdlist").val()];
	data.sort(compareProp(head[obj.cellIndex],sn==0?"asc":"desc"));
	var tbodys=$("#datatable tbody");
	$(tbodys[1]).html(printTBody(data));
}
function compareProp(pName,sn){
	return function(obj1,obj2){
		var v1=empty(obj1[pName]);
		var v2=empty(obj2[pName]);
		if(sn=="asc"){
			return v1.localeCompare(v2);
		}else{
			return 0-v1.localeCompare(v2);
		}
	}
}