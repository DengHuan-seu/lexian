/**
 * baseurl公共请求域
 */

var baseUrl = "/LexianManager";

/**
 * url              http url地址
 * param            请求的参数
 * fnCallback       成功回调json
 * fnErrorCallback  网络500回调
 *
 */
$.postAjax = function(url, param, fnCallback, fnErrorCallback) {
    url = baseUrl + url;
    var fixParam = getFixParam();
    param = param || {};
    param = $.extend(param, fixParam);
    if (fnCallback && typeof fnCallback == "function") {
        $.post(url, param, function(json, status) {
            if (status == "success") {
                fnCallback(json);
            } else if (fnErrorCallback && typeof fnErrorCallback == 'function') {
                fnErrorCallback();
            }
        }, "json");
    }

};

$.getJSONAjax = function(url, param, fnCallback, fnErrorCallback) {
    url = baseUrl + url;
    var fixParam = getFixParam();
    param = param || {};
    param = $.extend(param, fixParam);
    if (fnCallback && typeof fnCallback == "function") {
        $.getJSON(url, param, function(json, status) {
            if (status == "success") {
                fnCallback(json);
            } else if (fnErrorCallback && typeof fnErrorCallback == 'function') {
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
    "seriesParam":function(json){
       if(json&&typeof json=="object"){
           var index = 0;
           var str = "";
           for(var key in json){
               if(key!=undefined&&key!=null&&json[key]!=undefined&&json[key]!=null){
                   if(index>0){
                       str = str+"&";
                   }
                   str = str+key+"="+json[key];
                   index++;
               }

           }
           return str;
       }
        return "";
    },
    "getStrLength" : function(str) {
        if (!str)
            return 0;
        var count = 0;
        for (var index = 0; index < str.length; index++) {
          
            var code = str.charCodeAt(index);
            if (code >= 913 && code <= 65509) {
                count += 2;
            } else {
                count += 1;
            }
        }
        return count;
    },

    /**
     * 将convert get params to object if param is null return the object for current window
     * @param obj
     * @returns {{}}
     */
    "getParams" : function(obj) {
        var search = (window || obj).location.search;
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
    "getParam" : function(key) {
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
    "parseJSON" : function(str) {
        if (!str) {
            return null;
        }
        try {
            if (JSON && JSON.parse && typeof JSON.parse == "function") {
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
    "stringify" : function(obj) {
        if (!obj || typeof obj == 'string') {
            return {};
        } else {
            return JSON.stringify(obj);
        }
    },
    /**
     *
     * @param table  需要填充的表格对象
     * @param json   json数据
     * @param keys   需要加载的key [[key,columncallback],[],[]]
     */
    "innerTable" : function(table, json, keys) {
        table.empty();
        if (table && table.append && json && json.length) {
            //to do here
            for (var index in json) {
                var tr = json[index];
                var trstr = "<tr>";
                /**
                 * 遍历所有需要加载的key
                 */
                for (var key in keys) {
                    var object = keys[key];
                    //有回调
                    if ( object instanceof Array) {
                        if (object.length > 1) {
                            trstr = trstr + "<td>" + object[1](tr[object[0]], tr) + "</td>";
                        } else {
                            trstr = trstr + "<td>" + (tr[object[0]] || '') + "</td>";
                        }
                    }
                    //没有回调
                    else {
                        trstr = trstr + "<td>" + (tr[object] || '') + "</td>";
                    }
                }
                trstr = trstr + "</tr>";
                table.append(trstr);
            }

        }
    },
    /**
     *url 请求的url
     * data  {id:value}
     */
    "differentRequest" : function(url, data,successStr,fnCallback) {
        $.postAjax(url, data, function(json) {
            if (json.code) {
                asyncbox.tips(json.message, asyncbox.Level.error);
                return;
            }
            asyncbox.tips(successStr, asyncbox.Level.success);
            if(fnCallback&&typeof fnCallback=="function"){
            	fnCallback();
            }
            
        }, function() {
            asyncbox.tips("网络连接错误！", asyncbox.Level.error);

        });
    },
    /**
     *url 请求地址
     * param 请求参数 obj
     * 字段数据[[field : 'roleId',title : '角色id',width : getWidth('#content', 0.05)}]] 
     */
    "fillTable":function(url,param,columnArray,tableId) {
             url=baseUrl+url;
             var args = arguments;
            $("#"+(tableId||"roleInfoTable")).datagrid({
                url : url, //加载的URL
                // method : 'get',
                method : 'post',
                singleSelect : true,
                collapsible:true,
                rownumbers : true,
                pagination : true, //显示分页
                pageSize : 22, //分页大小
                pageList : [17, 22, 27], //每页的个数
                fit : false, //自动补全
                fitColumns : true,
                queryParams:param,
                columns :columnArray,
                onLoadSuccess:function(){
                	if(args&&args.length>3)
                	{
                		var fncallBack = args[4];
                        	if(fncallBack&&typeof fncallBack =="function"){
                        		fncallBack();
                        	}
                	}
                }
            })},
           "getWidth" :function (str, num) {
            return ($(str).width() * num);
        },
        /**
         *将参数拼接在url后面
         * url请求地址
         * obj对象
         * keyArray 字段数组 
         */
        "paramFollowUrl":function(url,obj,keyArray){
          url="/LexianManager/html"+url;
          if(!obj||!keyArray||!keyArray.length||!url){
              return "";
          }
        var str=url +"?";
        var param="";
        for(var i=0;i<keyArray.length;i++){
            var key=keyArray[i];
            str=str.append(key).append("=").append(obj[key]||""||0).append("&");
        }
        return str.substring(0,str.length-1);
    },
    /**
     *将选中的checkox中的value值拼接起来。值之间以逗号隔开
     *hintString 提示信息 
     */
    "box": function(hintString){
             var checkedbox = $("input:checkbox:checked");
            if (!checkedbox || !checkedbox.length) {
               // asyncbox.tips(hintString, asyncbox.Level.error);
                return false;
            }
            /**
             需要删除的id用,拼接
             */
            var ids = "";
            var index = 0;
            checkedbox.each(function () {
                if (index++ > 0) {
                    ids = ids + ",";
                }
                ids = ids + $.trim($(this).val());
            });
            return ids;
              }
};
function getFixParam() {
    return {
        "os" : "pc",
        "platformCode" : 2,
        "unixtime" : new Date().getTime()
    };
}

/*
 生产
 *初始化全局缓存 初始化   初始化此书initialize  the global cache of navigator
 *
 *  初始化
 */

function initCache() {
    cache = window.localStorage || {
        "setItem" : function(key, value) {
            this[key] = value;
        },
        "getItem" : function(key) {
            return this[key];
        },
        "removeItem" : function(key) {
            this[key] = null;
            delete this[key];
        },
        "clear" : function() {
            for (var key in this) {
                if (key != 'setItem' && key != 'getItem' && key != 'removeItem' && key != 'clear' && key != 'setObjectItem' && key != 'getObjectItem') {
                    this[key] = null;
                    delete this[key];
                }
            }
        }
    };

}(initCache());

function iterateChildren(cur, child) {
    if (child && $(child).length > 0) {

        var child_data_option = $(child).attr("data-option");
        var child_option_json = util.parseJSON(child_data_option);
        if (!child_option_json) {
            return;
        }
        var parentKey = child_option_json.parentKey;
        var url = child_option_json.url;
        var valueField = child_option_json.valueField;
        var textField = child_option_json.textField;
        var code = child_option_json.code;
        var key = child_option_json.key;
        var child1 = child_option_json.child;
        var keys = key.split(".");
        var keyStr = ".";
        for (var index in keys) {
            keyStr = keyStr + keys[index];
        }

        cur.on("change", function() {
            var value = cur.val();
            var param = {};
            param[parentKey] = value;
            var optionstoStr = $(child).html();
            var firstOptionIndex = -1;
            if (( firstOptionIndex = optionstoStr.indexOf("</OPTION>")) != -1 || (( firstOptionIndex = optionstoStr.indexOf("</option>")) != -1)) {

                $(child).html(optionstoStr.substring(0, firstOptionIndex + 9));
            }
            $.getJSONAjax(url, param, function(json) {
                var data = eval("json" + keyStr);
                for (var index in data) {
                    $(child).append("<option code='" + data[index][code] + "' value='" + data[index][valueField] + "'>" + data[index][textField] + "</option>");
                }
            }, function() {

            });
        });
        iterateChildren($(child), child1);
    } else
        return;
};
/**
 * jquery plugin  document init  页面加载完成后的动作  主要是控件数据初始化  数据绑定
 */
$(function() {
    /**
     * placehold  插件
     * @type {*|jQuery|HTMLElement}
     */
    var placeholder = $("input:text[placehold!='']");
    if (placeholder && placeholder.length) {
        placeholder.each(function() {
            var cur = $(this);
            cur.val(cur.attr("placehold"));
        });
    }
    placeholder.on("focus", function() {
        var cur = $(this);
        if ($.trim(cur.val()) == cur.attr("placehold")) {
            cur.val("");
        }
    }).on("blur", function() {
        var cur = $(this);
        if ($.trim(cur.val()) == "") {
            cur.val(cur.attr("placehold"));
        }
    });

    /**
     * commoCheck  全选 取消插件   data-option  href 指向需要选择的checkbox的name  unchecked 取消状态时的文本  全选时的文本
     */
    var commoCheck = $(".commoCheck");
    if (commoCheck.length) {
        commoCheck.each(function() {
            var cur = $(this);
            cur.click(function() {
                var data_option = cur.attr("data-option");
                if (data_option) {
                    var json_option = util.parseJSON(data_option);
                    if (!json_option)
                        return;
                    var href = json_option.href;
                    if (href) {
                        $("input:checkbox[name='" + href + "']").click();
                        if (!cur.attr("checked")) {
                            cur.attr("checked", "checked");
                            cur.text(json_option.checked || cur.text());
                        } else {
                            cur.text(json_option.unchecked || cur.text());
                            cur.removeAttr("checked");
                        }
                    }

                }
            });
        });
    }
    function box(hintString){
             var checkedbox = $("input:checkbox:checked");
            if (!checkedbox || !checkedbox.length) {
                asyncbox.tips("请选择需要赋予的权限！", asyncbox.Level.error);
                return;
            }
            /**
             需要删除的id用,拼接
             */
            var ids = "";
            var index = 0;
            checkedbox.each(function () {
                if (index++ > 0) {
                    ids = ids + ",";
                }
                ids = ids + $.trim($(this).val());
            });
            return ids;
          }
    /**
     * commoBox 下拉自动获取数据
     * @type {*|jQuery|HTMLElement}
     */
    var commoBox = $("select.commoBox");
    if (commoBox.length) {
        commoBox.each(function() {
            var cur = $(this);
            var data_option = null;
            if (( data_option = cur.attr("data-option")) != null) {
                var data_json = util.parseJSON(data_option);
                if (!data_json)
                    return;
                var url = data_json.url;
                var textField = data_json.textField;
                var valueField = data_json.valueField;
                var code = data_json.code;
                var child = data_json.child;
                var key = data_json.key;
                if (url && textField && valueField && key) {
                    var keys = key.split(".");
                    var keyStr = ".";
                    for (var index in keys) {
                        keyStr = keyStr + keys[index];
                    }
                    $.getJSONAjax(url, {}, function(json) {
                        var data = eval("json" + keyStr);
                        for (var index in data) {
                            cur.append("<option code='" + data[index][code] + "' value='" + data[index][valueField] + "'>" + data[index][textField] + "</option>");
                        }
                    }, function() {

                    });
                }
                iterateChildren(cur, child);
            }
        });
    }
    
    var expendImage = $("img.expand");
    expendImage.live("click",function(){
    	var src = $(this).attr("src");
    	var div = $("<div class='addDivPic' style='height:0px;width:0px;position:fixed;left:50%;top:50%;'><div style='height:20px;text-align:right;background-color:blue;'><a href='javascript:void(0)' onclick='removeDiv()'><img src='easyui/themes/icons/pppic.png'/></a></div><div style='width:100%;height:100%;margin-top:-1000px;padding-top:1000px;'><img src='"+src+"' style='width:100%;height:100%;'/></div></div>");
    	$(document.body,window.top).append(div);
    	div.animate({"height":"60%","width":"60%","left":"20%","top":"20%"},{speed:2000});
    });
});

function removeDiv(){
	$(".addDivPic").remove();
}

function logout(){
	$.messager.confirm('确认','您确定退出此次登录吗？',function(r){    
	    if (r){
	    	$.postAjax("/login/logout.do",{},function(json){
	    		if(json.code){
	    			asyncbox.tips(json.code,asyncbox.Level.error);
	    			return;
	    		}
	    		asyncbox.tips(json.message,asyncbox.Level.success);
	    		window.location = "login.html";
	    	},function(){});    
	    }    
	});  
}

/**
 * 回调
 * @param json
 */
function loginUser(json){
	 if (json.code) {
         asyncbox.tips(json.message, asyncbox.Level.error);
      //   window.location="login.html";
         return;
     }
	 $("#userName").text(json.rows.userName);
	 $("#loginTime").text(json.rows.loginTime);
	 var menus = json.menus||[];
	 $("#leftMenus").empty();
	 var isSelect = true;
	 if(menus.length>0){
		 for(var index in menus){
			 var menu = menus[index];
			 var parentId = menu.parentId ||0;
		     /**
		      * 没有父亲的菜单
		      */
			 if(!parentId){
		    	 $('#menu'+menu.id).append('<div style="background:url(../img/?) no-repeat left center"  class="classification" title="?" data-options="selected:?"><ul></ul></div>'.format(menu.backUrl,menu.name,String(isSelect)));
		         isSelect = false;
			  }
			 else{
				 $('#menu'+parentId+">ul").append('<li><a href="?" target="manager">?</a></li>'.format(menu.url,menu.name));
			 }
		 }
	 }
}


