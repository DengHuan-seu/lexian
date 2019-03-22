var baseUrl = "/LexianMall";
var userinfo;

// AJAX请求辅助函数
$.postAjax = function (url, param, fnCallback, fnErrorCallback) {
    url = baseUrl + url;
    var fixParam = getFixParam();
    param = param || {};
    param = $.extend(param, fixParam);
    if (fnCallback && typeof fnCallback == "function") {
        $.post(url, param, function (json, status) {
            if (status == "success") {
                fnCallback(json);
            }
            else if (fnErrorCallback && typeof fnErrorCallback == 'function') {
                fnErrorCallback();
            }
        }, "json");
    }
};

$.getJSONAjax = function (url, param, fnCallback, fnErrorCallback) {
    url = baseUrl + url;
    var fixParam = getFixParam();
    param = param || {};
    param = $.extend(param, fixParam);
    if (fnCallback && typeof fnCallback == "function") {
        $.getJSON(url, param, function (json, status) {
            if (status == "success") {
                fnCallback(json);
            }
            else if (fnErrorCallback && typeof fnErrorCallback == 'function') {
				fnErrorCallback();
            }
        });
    }

};

// 辅助工具对象
var util = {
    "getStrLength": function (str) {
        if (!str)
            return 0;
        var count = 0;
        for (var index = 0; index < str.length; index++) {
            var code = str.charCodeAt(index);
            if (code >= 913 && code <= 65509) {
                count += 2;
            }
            else {
                count += 1;
            }
        }
        return count;
    },
    
    "isLogin":function(){
        var cookiesStr  =  document.cookie;
        if(!cookiesStr&&!cookiesStr.length)
       	 return false;
        if(cookiesStr.indexOf("lexianKey")==-1)
       	 return false;
        return true;
   },

    "getParams":function(obj){
        var search = (window||obj).location.search;
        if (!search)
            return {};
        var object = {};
        var params = search.substring(1).split("&");
        for (var index in params) {
            var param = params[index].split("=");
            object[param[0]] = param[1];
        }
        return object;
    }, 
    
    "getParam": function (key) {
        var search = window.location.search;
        if (!search)
            return "";
        var params = search.substring(1).split("&");
        for (var index in params) {
            var param = params[index].split("=");
            if (param[0] == key)
                return param[1];
        }
        return "";
    },
    
    "parseJSON": function (str) {
    	if(!str)
    	{
    		return null;
    	}
        try {
            if (JSON && JSON.parse && typeof  JSON.parse == "function") {
            return JSON.parse(str);
            }
            if ($ && $.parseJSON) {
                return $.parseJSON(str);
            }
            return eval("(" + str + ")");
        } catch (e) {
            return null;
        }
    },
    
    "stringify":function(obj){
    	if(!obj||typeof obj=='string'){
    		return {};
    	}else
    	{
    		return JSON.stringify(obj);
    	}
    },
    
    "innerTable": function (table, json, keys) {
        table.empty();
        if (table&&table.append&&json && json.length) {
            for(var index in json){
                var tr = json[index];
                var trstr = "<tr>";
                for(var key in keys){
                    var object = keys[key];
                    if(object instanceof  Array){
                        if(object.length>1){ 
                        	trstr = trstr + "<td>"+object[1](tr[object[0]],tr)+"</td>";
                        }
                        else{
                            trstr = trstr+"<td>"+(tr[object[0]]||'')+"</td>";
                        }
                    }
                    else
                    {
                        trstr = trstr+"<td>"+(tr[object]||'')+"</td>";
                    }
                }
                trstr = trstr+"</tr>";
                table.append(trstr);
            }

        }
    }
};

function getFixParam() {
    return {"os": "pc", "platformCode": 2,"unixtime":new Date().getTime()};
}

// HTML本地缓存操作对象
function initCache(){
	 cache = window.localStorage ||{
	 	"setItem":function(key,value){
	 		this[key]= value;
	 	},
	 	"getItem":function(key){
	 		return this[key];
	 	},
	 	"removeItem":function(key){
	 		this[key] = null;
	 		delete this[key];
	 	}
	 	,"clear":function(){
	 		for(var key in this){
	 			if(key!='setItem'&&key!='getItem'&&key!='removeItem'&&key!='clear'&&key!='setObjectItem'&&key!='getObjectItem'){
	 				this[key] = null;
	 				delete this[key];
	 			}
	 		}
	 	}
	 
	 };
	
}(initCache());

$.fn.extend({
	"enter":function(fnCallBack){
		 $(this).keypress(function(event){
			 event = event || window.event;
			 var keyCode = event.keyCode || event.which;
			 if(keyCode==13){
				 fnCallBack();
			 }
		 });
	}
});

function iterateChildren(cur,child){
	if(child&&$(child).length>0){
      		var child_data_option = $(child).attr("data-option");
            var child_option_json = util.parseJSON(child_data_option);	
            if(!child_option_json){
            	return;
       		}
			var parentKey = child_option_json.parentKey;
            var url = child_option_json.url;          	
            var valueField= child_option_json.valueField;
            var textField = child_option_json.textField;
         	var code = child_option_json.code;
			var key = child_option_json.key;
			var child1 = child_option_json.child;
			var keys = key.split(".");
            var keyStr = ".";
        	for(var index in keys){
           		keyStr = keyStr+ keys[index];
        	}      
        	       
		    cur.on("change",function(){
		    var value  =  cur.val(); 
			var param = {};
			param[parentKey] =  value;
			var optionstoStr  = $(child).html();
			var firstOptionIndex  = -1;
			if((firstOptionIndex = optionstoStr.indexOf("</OPTION>"))!=-1||((firstOptionIndex = optionstoStr.indexOf("</option>"))!=-1))
			{
				
			    $(child).html(optionstoStr.substring(0,firstOptionIndex+9));
			}
   		 	$.getJSONAjax(url,param,function(json){
  		 		var data = eval("json"+keyStr);
       			for(var index in data){
            		$(child).append("<option code='"+data[index][code]+"' value='"+data[index][valueField]+"'>"+data[index][textField]+"</option>");
        		}
     		},function(){
     			
     		});	
		});
		iterateChildren($(child),child1);
	}
	else
	     return;
};

// 页面加载完成后的通用操作 
$(function () { 
    var selected = $(".select>*");
    selected.live("click",function(){
    	$(this).addClass("selected");
    	$(this).siblings().removeClass("selected");
    	$(this).trigger('select');
    });
    
    var commoCheck = $(".commoCheck");
    if(commoCheck.length){
        commoCheck.each(function(){
            var cur = $(this);
            var data_option = cur.attr("data-option");
            var  json_option =  util.parseJSON(data_option);
            if(!json_option)
               return;
            var href = json_option.href;
            $("input:checkbox[name='"+href+"']").live("click",function(){
            	var length = $("input:checkbox[name='"+href+"']:checked").size();
           	 	if(length==$("input:checkbox[name='"+href+"']").length){
                    cur.text(json_option.checked||cur.text());
           	 	}
           	 	else
                    cur.text(json_option.unchecked||cur.text());
            	}
            );
            if(data_option){
            	cur.click(function(){
	                if(href){
	                	if(!cur.attr("checked")){
	                		$("input:checkbox[name='"+href+"']").attr("checked",true);
	                		cur.attr("checked","checked");
	                		cur.text(json_option.checked||cur.text());
	                	}
	                	else
	                	{
	                		$("input:checkbox[name='"+href+"']").attr("checked",false);
	                		cur.text(json_option.unchecked||cur.text());
	                		cur.removeAttr("checked");
	                	}
	                }
            	});
            }
        });
    }

    // commoBox下拉自动获取数据
    var commoBox = $("select.commoBox");
    if(commoBox.length){
    	commoBox.each(function(){	
    		var cur = $(this);
    		var data_option  =null;
          	if((data_option=cur.attr("data-option"))!=null){
                var data_json = util.parseJSON(data_option);
                if(!data_json)
                	return;
                var url = data_json.url;
                var textField = data_json.textField;
                var valueField = data_json.valueField;
                var code = data_json.code;
                var child = data_json.child;
                var key = data_json.key;
                if(url&&textField&&valueField&&key){
                    $.getJSONAjax(url,{},function(json){
                        var data = eval("json."+key);
                        for(var index in data){
							cur.append("<option code='"+data[index][code]+"' value='"+data[index][valueField]+"'>"+data[index][textField]+"</option>");
                        }
					},function(){

                    });
                }
				iterateChildren(cur,child);
            }
        });
    }
    
    // 装载页头和页尾
    if($("#header")){
    	$.get("header.html", function(data){
        	$("#header").html(data);
            // 页面头部用户信息
            $.postAjax("/user/getUserInfo.do",{},
            	function(json){
        	    	if(json.code){
        	    		return;
        	    	}
        	    	var changelogin = $("#userInfo>span>a");
        	        changelogin.text(json.data.username);
        	        changelogin.attr("href","usersetting.html");
        	        var exitlogin = $("#userInfo>a");
        	        $("#loginA").text(json.data.username);
        	        $("#timeA").text(json.data.lastlogintime);
        	        $("#imageA").attr("src",json.data.portrait);
        	        exitlogin.text("退出");
        	        exitlogin.removeAttr("href");
            	}
            );
            
            // 点击注销
            $("#userInfo>a").click(function(){
            	$.postAjax("/user/logout.do",{},
            		function(json){
        	    		if(json.code){
        	        		return;
        	        	}
        	    		window.location = "login.html";
            		}
            	);
            }); 
        });
    }
    if($("#footer")){
    	$.get("footer.html", function(data){
	    	$("#footer").html(data);
	    });
    }
    if($("#search")){
    	$.get("search.html", function(data){
    		$("#search").html(data);
    		$("form>button").click(function(){
    	    	var str = $.trim($(this).prev("input").val());
    	    	if(str){
    	    		window.location.href = "searchcommodity.html?keyword=" + encodeURIComponent(str);
    	        	return false;
    	    	}
    	    	asyncbox.tips("你还没有输入任何东西哦~",asyncbox.Level.success);
    	    	return false;
    	    });
    	});
    }
    
    // 访问计数
    function visitCount(){
        var visiterCount = $(".visiterCount");
        if(visiterCount.length>0){
        $.post(baseUrl+"/user/visitCount.do",{},function(count){
            visiterCount.text(count); 
        },"text");
       }
    }
    visitCount();
    
    // 点击搜索
    $("form>button").click(function(){
    	var str = $.trim($(this).prev("input").val());
    	if(str){
    		window.location.href = "searchcommodity.html?keyword=" + encodeURIComponent(str);
        	return false;
    	}
    	asyncbox.tips("你还没有输入任何东西哦~",asyncbox.Level.success);
    	return false;
    });
});