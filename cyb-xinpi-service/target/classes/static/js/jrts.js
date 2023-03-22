function processData(){
	$(document).ready(process);
	
}
var jrtssel=["JRTS","MRTS","BZTS","XZTS","SZBW"]
var jrtscodes=["XGFX","XGSS","ZFG","GDDH","CQCX","GQDJ","SPG","YH","ZQL"];
var jrtsitems={"XGFX":"新股发行日","XGSS":"新股上市日","ZFG":"增发股上市日","GDDH":"股东大会召开日","CQCX":"除权除息日",
"GQDJ":"股权登记日","SPG":"送配股上市日","YH":"中签号公告日","ZQL":"中签率公告日"};

//modified by jjf
//date: 2019-08-03
function process(){
	var strPubDate="";	//每日提示日期
	if(jrts&&jrts.data){
		var data=jrts.data;
		
		for(var i=0;i<data.length;i++){
			var seq=data[i]["SEQ"];
			strPubDate=data[i]["PUB_DATE"];	//取得每日提示日期
			var itemname=data[i]["T_ID"]+"_"+seq;
			var html="";
			for(var ii=0;ii<9;ii++){
				html+="<h4>"+jrtsitems[jrtscodes[ii]]+"</h4><ul>";
				var itemlist=data[i][itemname+"_"+ii];
				for(var j=0;j<itemlist.length;j++){
					html+='<li><a href="http://stockdata.cs.com.cn/qcenter/new/stock-exponent.html?en_prod_code='+itemlist[j]["CODE"]+'.SZ">'+itemlist[j]["JC"]+'</a></li>';
				}
				html+='<div class="blank10"></div></ul>';
			}
			$().html(html);
		}
		$("#jrts_div_JRTS").removeClass("hide");
		
		//设置提示日期
		var strTitle = "每日交易提示(" +strPubDate +")";
		$("#id_mrjyts_title").html(strTitle);
	}
	addchange();
}
function addchange(){
	$("#jrts_sel").change(function(){
		var sel=$("#jrts_sel").val();
		for(var i=0;i<jrtssel.length;i++){
			$("#jrts_div_"+jrtssel[i]).removeClass("hide");
			$("#jrts_div_"+jrtssel[i]).addClass("hide");
		}
		$("#jrts_div_"+sel).removeClass("hide");
	});
}