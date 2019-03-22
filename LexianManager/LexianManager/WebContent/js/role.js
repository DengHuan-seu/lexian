/**
 * @author Administrator
 */
$(function(){
    var data;
    $.postAjax("http://192.168.0.105:8080/LexianManager/management/readRole.do",{},
    function (json) {
      data=json.rows;
      window.console.info(data);
    });
   $("#roleInfoTable").datagrid(
       {
       url:data,
       fit:true,
       fitColumns:true,
       singleSelect:true,
       pagination:true,
       columns:[[
    {field:'roleId',title:'角色名称'},    
    {field:'description',title:'描述'},    
    {field:'createTime',title:'创建时间'},
    {field:'updateTime',title:'修改时间'},
    {field:'operate',title:'操作'}
]]
   });
});
