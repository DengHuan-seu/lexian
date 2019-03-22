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

String.prototype.append = function(){
   var cur =""+ this;
    if(arguments){
      for(var index=0;index<arguments.length;index++){
          cur = cur + arguments[index];
      }
    }
    return cur;
};
