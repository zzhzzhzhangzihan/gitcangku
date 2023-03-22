function processData(){
	$(document).ready(process);
}
function process(){
	if(jyzldtl&&jyzldtl.list){
		$("#sczm_detail_div box_left_Title").html(jyzldtl.Title);
		$("#sczm_detail_div box_left_From span:eq(1)").html(jyzldtl.PUB_DATE);
		var data=jyzldtl.list;
		for(var i=0;data&&i<data.length;i++){
			$("#REPORTID_tab1 tr:eq("+(i+1)+") td:eq(1)").html(data[i]["T_VALUE"]);
			$("#REPORTID_tab1 tr:eq("+(i+1)+") td:eq(2)").html(data[i]["INCREASE"]);
			$("#REPORTID_tab1 tr:eq("+(i+1)+") td:eq(3)").html(data[i]["TOP_VALUE"]);
			$("#REPORTID_tab1 tr:eq("+(i+1)+") td:eq(4)").html(data[i]["TOP_DATE"]);
		}
	}
}