/**
 * @author Administrator
 */
$(function() {
	$("#log").click(function() {
		$.postAjax("/login/readUserLog.do", {}, function(json) {
		});
	});
	$.postAjax("/login/getSession.do", {},
		function(json) {
			if (json.code) {
				window.location = "login.html";
				return;
			}
			$("#userName").text(json.rows.userName);
			$("#loginTime").text(json.rows.loginTime);
			var menus = json.rows.menus || [];
			$("#leftMenus").empty();

			var accorations = {};
			if (menus.length) {
				for ( var index = 0; index < menus.length; index++) {
					var menu = menus[index];
					var parentId = menu.parentId;
					var id = menu.id;
					if (!parentId) {
						accorations[id] = {
							"title" : menu.name,
							"backUrl" : menu.backUrl,
							"parentId" : menu.parentId
						};
						for ( var j = 0; j < menus.length; j++) {
							var menutmp = menus[j];
							if (menutmp.parentId === id) {
								accorations[id].childMenus = accorations[id].childMenus || [];
								accorations[id].childMenus.push({
									"title" : menutmp.name,
									"backUrl" : menutmp.backUrl,
									"url" : menutmp.url,
									"parentId" : menutmp.id
								});
							}
						}
					}
				}
			}

			var startIndex = 0;
			$.each(accorations, function(index, value) {
				if (value.childMenus && value.childMenus.length) {
					var content = '<ul>'.format(value.title);
					for ( var index = 0; index < value.childMenus.length; index++) {
						content = content.append('<li style="background:url(../img/?) no-repeat left center"><a href="?" target="manager">?</a></li>'
							.format(
									value.childMenus[index].backUrl,
									value.childMenus[index].url,
									value.childMenus[index].title));
					}
					content = content.append("</ul>");
					$('#leftMenus').accordion('add',
					{
						title : value.title
								|| "",
						content : content,
						selected : false
					});
					$('#leftMenus').find(".panel .accordion-body").addClass("classification");
				}

				else if (!value.parentId) {
					$('#leftMenus').accordion('add',
					{
						title : value.title || "",
						selected : false
					});
				} else {
					$('#leftMenus').append(
					"<div class='panel' style='width: 198px;'><div  class='panel-header accordion-header' style='height: 25px; width: 188px;'>"
							+ "<div class='classification panel-title'><a href='?' target='manager' style='display:block;width:100%;height:100%;'>?</a></div></div></div>"
									.format(value.url, value.title));
				}

				if (value.backUrl) {
					$("#leftMenus .panel:last .panel-title").css(
						{
							"background" : "url(../img/?) no-repeat left center".format(value.backUrl)
						});
				}
				$(".accordion-header").click(
					function() {
						$(".accordion-header").removeClass("accordion-header-selected");
						$(this).addClass("accordion-header-selected");
					});
			});
		});
});
