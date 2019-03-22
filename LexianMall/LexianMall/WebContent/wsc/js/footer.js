/**
 * Created by l1ang on 2015/10/27.
 */
function userShow(userName){
    $('#userName').text(userName);
    $('#userName').attr('href','home.html');
    $('#cancellation').text('注销');
    $('#cancellation').attr('href','javascript:void(0)');
    $('#cancellation').click(exitLogin);
    var str = '学生';
    switch(role)
    {
        case 1:str = '老师';break;
        case 2:str = '学生';break;
        case 3:str = '家长';break;
        case 4:str = '其它';break;
        case 5:str = '商家';break;
    }
    $('#uName').text(userName);
    $('#role').text(str);
}
$('#download').click(function(){
    $.post(
        baseUrl+'version/getNewVersion.do',
        {},
        function(json){
            if(json.code==0){
                location.href=json.data.appUrl;
            }else{
                showBox('网络错误')
            }
        },'json'
    )
});
