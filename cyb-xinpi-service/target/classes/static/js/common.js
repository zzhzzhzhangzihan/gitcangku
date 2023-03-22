function loadScript(url,callback){
	var script = document.createElement("script");
	if(script.addEventListener){
	　　script.addEventListener("load", callback, false);
	}else if(script.attachEvent){
	　　script.attachEvent("onreadystatechange", function(){
	　　　　var target = window.event.srcElement;
	　　　　 if(target.readyState == "loaded" || target.readyState == "complete"){
	　　　　　　callback.call(target);
	　　　　}
	　　});
	}
	document.getElementsByTagName("head")[0].appendChild(script);
	script.src=url;
}
function getQueryString(nameq){
    var reg = new RegExp('(^|&)' + nameq + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}
function isENums(val){
    var regNeg =/^\d+$/; // 非负整数
    if(regNeg.test(val)){
        return true;
    }else{
        return false;
    }
}