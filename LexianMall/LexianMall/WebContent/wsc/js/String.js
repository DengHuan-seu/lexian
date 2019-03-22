/**
 * Created by admin on 2015/9/9.
 */
/**
 *  使用方法 "aaa??".format("1","2") or aaa??".format("1").format("2");
 * @returns {*}
 */
String.prototype.format = function(){
    if(arguments&&arguments.length){
        var count = 0;
        var str = "";
        for(var index = 0;index<this.length;index++){
            var code = this.charAt(index);
            switch(code){
                case '?':
                    str = str+arguments[count++];
                    break;
                default:
                    str = str+code;
                    break;
            }
        }
        return str;
    }
    return this;
};

/**
 * append  使用方法 "".append(...).append(...)
 * @returns {string}
 */
String.prototype.append = function(){
    var cur =""+ this;
    if(arguments){
        for(var index=0;index<arguments.length;index++){
            cur = cur + arguments[index];
        }
    }
    windows.console.info(cur);
    return cur;
};
