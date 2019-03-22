var baseUrl = "/LexianMall/";
var userName = '';
var role;
var phone = '';
var storeNo;
var storeName = '';
var userShow='';

//将值存入本地方法
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

//获取用户信息
$(function(){
	if (window.location.href.indexOf("location.html") < 0){
		var storeNo = cache.getItem('storeNo');
		if(storeNo==null || storeNo=='') {
	        showBox('请先定位最近的门店', 'location.html');
	    }
	}
	
    $('#backArrow').click(function(){
        history.back();
    });
});
function getUser(callback){
    $.post(
        baseUrl+'user/getUserInfo.do',
        function(json){
            if(json.code==0){
                callback(json.data);
            }else{
            	callback(null);
            }
        },'json'
    )
}
function forwardLogin(returnUrl){
	$.post(
        baseUrl+'user/getUserInfo.do',
        function(json){
            if(json.code != 0){
            	showBox('请先登录', 'login.html?backUrl=' + returnUrl);
            }
        },'json'
    );
}

function showBox(str,url){
    redirect = function(obj){
        var url = null;
        if(obj.getAttribute){
            url = obj.getAttribute("url");
        }
        else
        {
            url = obj.url;
        }
        if(!url||url=="undefined"){
            $('.box_bg').remove();
        }else if(url=='back'){
            history.back();
        }else if(url=='refresh') {
            location.reload();
        }else{
            obj.href = url;
        }
    };
    $('body').prepend('<div class="box_bg"><div class="msg_box"><p class="msg_title">提示</><p class="msg_text">'+str+'</p><p class="msg_btn_div"><a id="msg_btn"  url = "'+url+'" onClick="redirect(this);" href="javascript:void(0);">确定</a></p></div></div>');
}
//yes or no选择框
function selectBox(str,x){
    $('body').prepend('<div class="box_bg"><div class="msg_box"><p class="msg_title">提示</><p class="msg_text">'+str+'</p><p class="msg_btn_div"><span id="'+x+'" class="msg_min_btn btn_active">确定</span><span id="" class="msg_min_btn">取消</span></p></div></div>');
}
$('.msg_min_btn').live('click',function(){
    var id = $(this).attr('id');
    if(id!=''){
       confirmReceipt(id);
    }
    $('.box_bg').remove();
});
$('.header-fh').click(function(){
    history.go(-1);
});


//加载特效
function loadingShow(){
    $('body').prepend('<div id="box_ts" class="box_bg"><div class="loading"><p><img src="images/loading.jpg" width="32"></p></div></div>');
}
//删除加载特效
function loadingHide(){
    $('#box_ts').remove();
}
//退出登陆
function exitLogin(){
    $.post(
        baseUrl+'user/logout.do',
        function(json){
            document.cookie="";
            window.location.href='index.html';
        },'json'
    );
}

