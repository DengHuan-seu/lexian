var dataAray = {};
var picData;
var picId;
var lock = false;
var imgCount = 6;

$(function() {
	findShopCarCount(); // 用户购物车数量显示
	changeImg(); // 轮播图片
	getCategory(); // 分类填充
	categoryEffect(); // 分类栏鼠标效果设置 

	specialfun("1", "4"); // 限时抢购
	specialfun("2", "4"); // 品牌精选
	specialfun("3", "4"); // 热卖推荐
	specialfun("4", "4"); // 新品上市
	specialfun("5", "2"); // 特色产品
	specialfun("6", "6"); // 秒杀商品
	
	getStarCommodity(); // 明星商品
	userLike(); // 猜你喜欢的商品
	
	setWidthStyle();
});

// 通过用户id查看购物车商品数量
function findShopCarCount() {
	$.postAjax("/commodity/getTrolleyCount.do", {}, function(json) {
		if (json.code) {
			return;
		}
		var count = eval("(" + json.data + ")");
		$(".shopCart>span[class='tips']").text(count);
	}, function() {
	});
}

// 获取主类别信息
function getCategory() {
	//一级分类的填充
	$.postAjax("/category/getCategories.do", {
		type : 1
	}, function(json) {
		if (json.code) {
			asyncbox.tips(json.message, asyncbox.Level.error);
			return;
		}
		$(".pullDownList").empty();
		var data = json.data || [];
		var div1 = "<li class='menulihover'>";
		var div2 = "<li>";
		var div = "<i class='?'></i>";
		div += "<a target='_blank' data='?'>?</a>";
		if (data.length) {
			for ( var index in data) {
				if (index == 0) {
					$(".pullDownList").append((div1 + div).format("list" + (Number(index) + Number(1)),
						data[index].categoryId, data[index].categoryName));
				} else {
					$(".pullDownList").append((div2 + div).format("list" + (Number(index) + Number(1)),
						data[index].categoryId, data[index].categoryName));
				}
			}
		}
	}, function() {
	});
	
	// 二级和三级分类的填充
	$(".pullDownList>li").live("mouseenter",
		function() {
			var firstId = $(this).children("a").attr("data");
			$(".yMenuListConin").empty();
			var div = "<div class='yMenuLCinList'>";
			div += "<h3><a class='yListName'>?</a></h3>";
			div += "<p>?</p></div>";
			if (dataAray[firstId] == null) {
				$.postAjax("/category/getSubCategories.do", {parentId : firstId},
					function(json) {
						if (json.code) {
							asyncbox.tips(json.message, asyncbox.Level.error);
							return;
						}
						var data1 = json.data || [];
						dataAray[firstId] = data1;
						for ( var index1 in data1) {
							var sencondObj = data1[index1];
							var data2 = data1[index1].subCategories || [];
							var content = "";
							for ( var index2 in data2) {
								content = content.append("<a onclick='showCommodityList(this);' data='?'>?</a>"
									.format(data2[index2].categoryId, data2[index2].categoryName));
							}
							$(".yMenuListConin").append(div.format(sencondObj.categoryName, content));
						}
					}
				);
			} else {
				var data1 = dataAray[firstId];
				for ( var index1 in data1) {
					var sencondObj = data1[index1];
					var data2 = sencondObj.subCategories || [];
					var content = "";
					for ( var index2 in data2) {
						content = content.append("<a onclick='showCommodityList(this);' data='?'>?</a>"
							.format(data2[index2].categoryId, data2[index2].categoryName));
					}
					$(".yMenuListConin").append(div.format(sencondObj.categoryName, content));
				}
			}
		}
	);
}

// 鼠标放置在类别上时
function categoryEffect() {
	$(".pullDownList li").live("mouseenter",
		function() {
			$(".yMenuListCon").fadeIn();
			var index = $(this).index(".pullDownList li");
			if (!($(this).hasClass("menulihover") || $(this).hasClass("menuliselected"))) {
				$($(".yBannerList")[index]).css("display", "block").siblings().css("display", "none");
				$($(".yBannerList")[index]).removeClass("ybannerExposure");
				setTimeout(function() {
					$($(".yBannerList")[index]).addClass("ybannerExposure");
				}, 60);
			}
			$(this).addClass("menulihover").siblings().removeClass("menulihover");
			$(this).addClass("menuliselected").siblings().removeClass("menuliselected");
			$($(".yMenuListConin")[index]).fadeIn().siblings().fadeOut();
		}
	);

	$(".pullDown").live("mouseleave", function() {
		$(".yMenuListCon").fadeOut();
		$(".yMenuListConin").fadeOut();
		$(".pullDownList li").removeClass("menulihover");
	});
}

function changefour(obj) {
	var indexs = $(obj).attr("data");
	$(".bottom").each(function() {
		var inde = $(this).attr("data");
		if ($.trim(indexs) == $.trim(inde)) {
			$(this).css("display", "block");
		} else {
			$(this).css("display", "none");
		}
	});
}

// 图片伦宝
function changeImg() {
	imgCount--;
	if (imgCount == 0) {
		imgCount = 5;
	}
	switch (imgCount) {
	case 1:
		$("#changeImg").attr("src", "images/banner1.jpg");
		$(".yBannerList").css("background", "#F899B7");
		break;
	case 2:
		$("#changeImg").attr("src","images/banner2.jpg");
		$(".yBannerList").css("background","#5E0FCC");
		break;
	case 3:
		$("#changeImg").attr("src", "images/banner3.jpg");
		$(".yBannerList").css("background", "#FF7C00");
		break;
	case 4:
		$("#changeImg").attr("src", "images/banner4.jpg");
		$(".yBannerList").css("background", "#D32830");
		break;
	case 5:
		$("#changeImg").attr("src","images/banner5.jpg");
		$(".yBannerList").css("background","#FEC900");
		break;
	}
	setTimeout(function() {
		changeImg();
	}, 2000);
}

// 商品板块
function specialfun(specialId, count) {
	$.postAjax("/special/findSpecialCommodityBySpecialId.do", {
		specialId : specialId,
		count : count
	}, function(json) {
		if (json.code) {
			asyncbox.tips(json.message, asyncbox.Level.error);
			return;
		}
		var data = json.data || [];
		if (data) {
			append(data);
		}
	});
}

function append(data) {
	var specialId = data[0].specialId;
	var str = "";
	$("#special" + specialId).empty();
	if (specialId == 1) {
		str = "限时抢购";
	}
	if (specialId == 2) {
		str = "品牌精选";
	}
	if (specialId == 3) {
		str = "热卖推荐";
	}
	if (specialId == 4) {
		str = "新品上市";
	}
	if (specialId == 5) {
		$("#special" + specialId).append("<div class='title-T'>特色产品</div>");
	}
	div = "<div class='innerWrap'>";
	div += "<div class='content-k'>";
	div += "<p>?</p>";
	div += "<p class='secondP'><a href='javascript:void(0)' data='?' onclick='showCommodityInfo(this)'>" + str + "</a></p>";
	div += "<P style='font-size:16px;color:#ff4200;font-weight:bold'>?元</P>";
	div += "<div style='margin-top:-20px'><a href='javascript:void(0)' data='?' onclick='showCommodityInfo(this)'><img src='?' width='115' height='83' alt=''/></a></div> ";
	div += "</div></div>";
	
	div1 = "<div href='javascript:void(0)' data='?' onclick='showCommodityInfo(this)' class='firstTT'>";
	div1 += "<dl><dt style='cursor:pointer'><img src='?' width='80' height='60'/></dt>";
	div1 += "<dd><p style='font-size:16px;color:#ff4200;font-weight:bold;text-align:left'>?元</p><p style='text-overflow:ellipsis;overflow:hidden;white-space:nowrap;line-height:25px;text-align:left'>?</p>";
	div1 += "</dd></dl></div>";

	div2 = "<div class='margin_left_z list_product_com'>";
	div2 += "<a onclick='showCommodityInfo(this);' data='?'><img src='?' alt='小图片' style='width:150px;height:150px'/></a>";
	div2 += "<p style='color:#ff4200;font-size:16px;font-weight:bold'>?元</p>";
	div2 += "<p class='commName1'>?</p>";
	if (specialId <= 4) {
		for ( var index in data) {
			$("#special" + specialId).append(div.format(data[index].commodity_name,  data[index].commodity_no, 
				data[index].commodity_price, data[index].commodity_no, data[index].fullPictureurl));
		}
	} else if (specialId == 5) {
		for ( var index in data) {
			$("#special" + specialId).append(div1.format(data[index].commodity_no,  data[index].fullPictureurl,
				data[index].commodity_name,  data[index].commodity_price));
		}
	} else if (specialId == 6) {
		for ( var index in data) {
			$("#special" + specialId).append(div2.format(data[index].commodity_no, data[index].fullPictureurl,
				data[index].commodity_price, data[index].commodity_name));
		}
		$("#special" + specialId).append("<div class='clr'></div>");
	}
}

function quChange() {
	specialfun("6", "6");
}

// 明星商品
function getStarCommodity() {
	$.postAjax("/order/findTopCommodities.do", {}, 
		function(json) {
			if (json.code) {
				asyncbox.tips(json.message, asyncbox.Level.error);
				return;
			}
			div1 = "<div class='margin_left_z list_product_com index1'>";
			div2 = "<div class='margin_left_z list_product_com index2' style='display:none'>";
			div = "<a onclick='showCommodityInfo(this);' data='?'><img src='?' alt='小图片' class='likeCommodity1' style='width:150px;height:150px'/></a>";
			div += "<p style='color:#ff4200;font-size:16px;font-weight:bold'>?元</p>";
			div += "<p class='commName1'>?</p>";
			var starCommodity = json.data || [];
			$("#starCommodity").empty();
			if (starCommodity.length) {
				for ( var index1 in starCommodity) {
					var com = starCommodity[index1];
					if (index1 < 6) {
						$("#starCommodity").append((div1 + div).format(
							com.commodityNo,
							com.fullPictureurl,
							com.commodityPrice,
							com.commodityName));
					} else {
						$("#starCommodity").append((div2 + div).format(
							com.commodityNo,
							com.fullPictureurl,
							com.commodityPrice,
							com.commodityName));
					}
				}
				$("#starCommodity").append("<div class='clr'></div>");
			}
		}
	);
}

// 明星商品切换
function starChange() {
	if ($("#starCommodity>.index2").css("display") == "none") {
		$("#starCommodity>.index2").css("display", "block");
		$("#starCommodity>.index1").css("display", "none");
	} else {
		$("#starCommodity>.index2").css("display", "none");
		$("#starCommodity>.index1").css("display", "block");
	}
}

// 猜你喜欢
function userLike() {
	$.postAjax("/browse/findBrowse.do", {maxnum : 12},
		function(json) {
			if (json.code) {
				asyncbox.tips(json.message, asyncbox.Level.error);
				return;
			}
			div1 = "<div class='margin_left_z list_product_com index1'>";
			div2 = "<div class='margin_left_z list_product_com index2' style='display:none'>";
			div = "<a onclick='showCommodityInfo(this);' data='?'><img src='?' alt='小图片' class='likeCommodity1' style='width:150px;height:150px'/></a>";
			div += "<p style='color:#ff4200;font-size:16px;font-weight:bold'>?元</p>";
			div += "<p class='commName1'>?</p>";
			var browseCommodity = json.data || [];
			$("#browseCommodity").empty();
			if (browseCommodity.length) {
				for ( var index in browseCommodity) {
					if (index < 6) {
						$("#browseCommodity").append((div1 + div).format(
							browseCommodity[index].commodity_no,
							browseCommodity[index].fullPictureurl,
							browseCommodity[index].commodity_price,
							browseCommodity[index].commodity_name));
					} else {
						$("#browseCommodity").append((div2 + div).format(
							browseCommodity[index].commodity_no,
							browseCommodity[index].fullPictureurl,
							browseCommodity[index].commodity_price,
							browseCommodity[index].commodity_name));
					}
				}
				$("#browseCommodity").append("<div class='clr'></div>");
			}
		}
	);
}

// 猜你喜欢切换
function changeLike() {
	if ($("#browseCommodity>.index2").css("display") == "none") {
		$("#browseCommodity>.index2").css("display", "block");
		$("#browseCommodity>.index1").css("display", "none");
	} else {
		$("#browseCommodity>.index2").css("display", "none");
		$("#browseCommodity>.index1").css("display", "block");
	}
}

// 宽度样式设置
function setWidthStyle() {
	// 猜你喜欢宽度加载
	var idiv = document.getElementById('www_zzjs_net');
	var ibox = document.getElementById('zzjs');
	var timer = null;
	timer = setInterval(function() {
		var iWidth = idiv.offsetWidth / ibox.offsetWidth * 100;
		idiv.style.width = idiv.offsetWidth + 1 + 'px';
		idiv.innerHTML = "";
		if (iWidth == 100) {
			clearInterval(timer);
		}
	}, 1);
	
	// 明星商品宽度加载
	var idiv_z = document.getElementById('www_zzjs_net_z');
	var ibox_z = document.getElementById('zzjs_z');
	var timer_z = null;
	timer_z = setInterval(function() {
		var iWidth_z = idiv_z.offsetWidth / ibox_z.offsetWidth * 100;
		idiv_z.style.width = idiv_z.offsetWidth + 1 + 'px';
		idiv_z.innerHTML = "";
		if (iWidth_z == 100) {
			clearInterval(timer_z);
		}
	}, 1);
	
	// 秒杀商品宽度加载
	var idiv_zx = document.getElementById('www_zzjs_net_zx');
	var ibox_zx = document.getElementById('zzjs_zx');
	var timer_zx = null;
	timer_zx = setInterval(function() {
		var iWidth_zx = idiv_zx.offsetWidth / ibox_zx.offsetWidth * 100;
		idiv_zx.style.width = idiv_zx.offsetWidth + 1 + 'px';
		idiv_zx.innerHTML = "";
		if (iWidth_zx == 100) {
			clearInterval(timer_zx);
		}
	}, 1);
}

// 查看商品的详情
function showCommodityInfo(obj) {
	var commodityNo = $.trim($(obj).attr("data"));
	window.location = "commodity.html?commodityNo=" + commodityNo;
}

function showCommodityList(obj) {
	window.location = "commoditylist.html?categoryId=" + $.trim($(obj).attr("data"));
}
