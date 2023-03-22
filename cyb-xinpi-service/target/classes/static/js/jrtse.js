function processjrtse(){
	var jrtscodes=["XGFX","XGSS","ZFG","GDDH","CQCX","GQDJ","SPG","YH","ZQL"];
	var jrtsitems={"XGFX":"新股发行","XGSS":"新股上市","ZFG":"增发股上市日","GDDH":"股东大会召开日","CQCX":"除权除息",
	"GQDJ":"股权登记","SPG":"送配股上市日","YH":"中签号公告日","ZQL":"中签率公告日"};
	if(jrtse&&jrtse.data){
		var data=jrtse.data;
		var html="";
		var pubdate=jrtse.pubdate;
		for(var i=0;i<data.length;i++){
			var seq=data[i]["SEQ"];
			if("JRTS"==seq||"MRTS"==seq){
				var itemname=data[i]["T_ID"]+"_"+seq;
				var hasCompany=false;
				var countt=0;
				html+='<ul style="display:'+("JRTS"==seq?'block':'none')+';"><table>';
				for(var ii=0;ii<9&&countt<3;ii++){
					var itemlist=data[i][itemname+"_"+ii];
					if(itemlist&&itemlist.length>0){
						html+="<tr><th>"+jrtsitems[jrtscodes[ii]]+"</th><td>";
						for(var j=0;itemlist&&j<itemlist.length&&j<6;j++){
							html+='<a href="http://stockdata.cs.com.cn/qcenter/new/stock-exponent.html?en_prod_code='+itemlist[j]["CODE"]+'.SZ">'+itemlist[j]["JC"]+'</a>';
							hasCompany=true;
							if(j==5){
								html+="...";
							}
						}
						html+='</td></tr>';
						countt++;
					}
				}
				if(!hasCompany){
					for(var ii=0;ii<3;ii++){
						html+="<tr><th>"+jrtsitems[jrtscodes[ii]]+"</th><td></td></tr>";
					}
				}
				html+="</table></ul>";
			}
			
		}
		$("#jrtse_div .hd ul>li:eq(0)").html(pubdate);
		$("#jrtse_div .bd").html(html);$("#jrtse_div div.bd").removeClass("hide");
	}
}