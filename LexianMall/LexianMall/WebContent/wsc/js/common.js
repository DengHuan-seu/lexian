/**
 * baseurl公共请求域
 */
//var baseUrl = "/LexianMall";
var cBaseUrl = "/LexianMall";
var userinfo;
/**
 * url              http url地址
 * param            请求的参数
 * fnCallback       成功回调json
 * fnErrorCallback  网络500回调
 *
 */
$.postAjax = function (url, param, fnCallback, fnErrorCallback) {
    url = cBaseUrl + url;
    var fixParam = getFixParam();
    param = param || {};
    param = $.extend(param, fixParam);
    window.console.info("url:"+url);
    window.console.info("param:"+JSON.stringify(param));
    if (fnCallback && typeof fnCallback == "function") {
        $.post(url, param, function (json, status) {
            if (status == "success") {
                window.console.info("result:"+JSON.stringify(json));
                fnCallback(json);
            }
            else if (fnErrorCallback && typeof fnErrorCallback == 'function') {
                window.console.error("result:失败");
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
    window.console.info("url:"+url);
    window.console.info("param:"+JSON.stringify(param));
    if (fnCallback && typeof fnCallback == "function") {
        $.getJSON(url, param, function (json, status) {
            if (status == "success") {
                window.console.info("result:"+JSON.stringify(json));
                fnCallback(json);
            }
            else if (fnErrorCallback && typeof fnErrorCallback == 'function') {
                window.console.error("result:失败");
				fnErrorCallback();
            }
        });
    }

};




/**
 * 工具包  调用方式util.
 * @type {{getStrLength: Function, getParam: Function, parseJSON: Function, innerTable: Function}}
 */
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

    /**
     * 将convert get params to object if param is null return the object for current window
     * @param obj
     * @returns {{}}
     */
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
    /**
     * 获取
     */
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
            window.console.error(e);
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
    /**
     *
     * @param table  需要填充的表格对象
     * @param json   json数据
     * @param keys   需要加载的key [[key,columncallback],[],[]]
     */
    "innerTable": function (table, json, keys) {
        table.empty();
        if (table&&table.append&&json && json.length) {
            //to do here
            for(var index in json){
                var tr = json[index];
                var trstr = "<tr>";
                /**
                 * 遍历所有需要加载的key
                 */
                for(var key in keys){
                    var object = keys[key];
                    //有回调
                    if(object instanceof  Array){
                        if(object.length>1){ 
                        	trstr = trstr + "<td>"+object[1](tr[object[0]],tr)+"</td>";
                        }
                        else{
                            trstr = trstr+"<td>"+(tr[object[0]]||'')+"</td>";
                        }
                    }
                    //没有回调
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
/*
生产
 *初始化全局缓存 初始化   初始化此书initialize  the global cache of navigator
 *
 *  初始化
 */

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
/**
 * jquery plugin  document init  页面加载完成后的动作  主要是控件数据初始化  数据绑定
 */
$(function () {
    /**
     * placehold  插件
     * @type {*|jQuery|HTMLElement}
     */
   /* var placeholder = $("input:text[placehold!=''],input:password[placehold!='']");
    if (placeholder && placeholder.length) {
        placeholder.each(function() {
            var cur = $(this);
            cur.val(cur.attr("placehold"));
            if(cur.attr("type")=="password"){
                /!**
                 * 可见域
                 * @type {*|jQuery|HTMLElement}
                 *!/
                var passwd = $("<input type='text' value='?'/>".format(cur.attr("placehold")));
                cur. before(passwd);
                passwd.on("focus",function () {
                    if ($.trim(passwd.val()) == cur.attr("placehold")) {
                        passwd.hide();
                        cur.val("");
                        cur.show();
                    }
                }).on("blur",function () {
                    if ($.trim(passwd.val()) == "") {
                        passwd.val(cur.attr("placehold"));
                        passwd.show();
                        cur.hide();
                    }
                });
                /!**
                 * 隐藏域
                 *!/
                cur.hide();
            }
        });
    }
    placeholder.on("focus",function () {
        var cur = $(this);
        if ($.trim(cur.val()) == cur.attr("placehold")) {
            cur.val("");
        }
    }).on("blur",function () {
        var cur = $(this);
        if ($.trim(cur.val()) == "") {
            cur.val(cur.attr("placehold"));
        }
    });*/
    
    var selected = $(".select>*");
    selected.live("click",function(){
    	$(this).addClass("selected");
    	$(this).siblings().removeClass("selected");
    	$(this).trigger('select');
    });
    

    /**
     * commoCheck  全选 取消插件   data-option  href 指向需要选择的checkbox的name  unchecked 取消状态时的文本  全选时的文本
     */
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

            });
            if(data_option){
            cur.click(function(){
                
                    
                     if(href)
                     {
                         
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

                  
                }
             );
            }
        });
    }
    /**
     * commoBox 下拉自动获取数据
     * @type {*|jQuery|HTMLElement}
     */
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
    
    function visitCount(){
        var visiterCount = $(".visiterCount");
        if(visiterCount.length>0){
        $.post(baseUrl+"/user/visitCount.do",{},function(count){
            visiterCount.text(count); 
        },"text");
       }
       console.info("统计中");
       window.setInterval(visitCount,60000);
    }
    //visitCount();
    
    
    /**
     * 首页的头部
     */
    $.postAjax("/user/getUserInfo.do",{},function(json){
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
        exitlogin.removeAttr("href")
    },function(){});
    
    
    /**
     *点击注销  
     */
    $("#userInfo>a").click(function(){
    	$.postAjax("/user/logout.do",{},function(json){
    		if(json.code){
        		return;
        	}
    		window.location = "login.html";
    	},function(){});
    }); 
    
    //点击搜索
    $("form>button").click(function(){
    	var str = $.trim($(this).prev("input").val());
    	if(str){
    		window.location.href = "searchcommodity.html?name="+encodeURIComponent(str);
        	return false;
    	}
    	asyncbox.tips("你还没有输入任何东西哦~",asyncbox.Level.success);
    	return false;
    });
    
    
    /**
     * 热搜
     */
    $.postAjax("/heatWord/findHeatWord.do",{},function(json){
    	if(json.code){
			//asyncbox.tips(json.message,asyncbox.Level.error);
			return;
		}
    	$(".hotList").empty();
    	var data = json.data ||[];
    	if(data.length){
    		for(var index in data){
    			$(".hotList").append("<a href='searchcommodity.html?name="+data[index].words+"'>"+data[index].words+"</a><span>|</span>");
    		}
    		$(".hotList>span:last").hide();
    	}
    },function(){});
    
    //隐藏查看详情
    $(".map_z").find("a").hide();
});