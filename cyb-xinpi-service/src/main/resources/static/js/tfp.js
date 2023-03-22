function processData(){
	$(document).ready(process);
}
function process(){
	if(tfp&&tfp.list){
		var data=tfp.list;
		var table="<table>";
		var trs="<tr><th>代码</th><th>简称</th><th>停牌日期</th><th>停牌时间</th><th>复牌时间</th><th>停复牌原因</th></tr>";
		for(var i=0;i<data.length;i++){
			trs+='<tr><td><a href="'+data[i].JC_URL+'">'+data[i].CODE+'</a></td>';
			trs+='<td><a href="'+data[i].JC_URL+'">'+data[i].JC+'</a></td>';
			trs+='<td>'+data[i].RQ+'</td><td>'+data[i].TPSJ+'</td>';
			trs+='<td>'+data[i].FPSJ+'</td><td>'+data[i].REASON+'</td></tr>';
		}
		table+=trs+"</table>";
		$("#tfp_ul").html(table);$("#tfp_ul").removeClass("hide");
		$("#tfpdate").html("最后更新: "+tfp.tfpdate);
		$("#tfpdate").removeClass("hide");
	}
}