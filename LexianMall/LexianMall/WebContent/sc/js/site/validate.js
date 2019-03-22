var validateUtil = null;
function getValidateUtil() {
	return {
		/**
		 * 校验是否为空
		 */
		"validateEmpty" : function(str) {
			if (str == '') {
				return false;
			}
             return true;
		},
		/**
		 * 校验是否是整数
		 */
		"validateNumber" : function(str) {
			var number = Number(str);
			if (isNaN(number)) {
				return false;
			}
			return true;
		},
		"validateArea" : function(num,min,max) {
			var number = Number(num);
			if (number<min || number>max) {
				return false;
			}
			return true;
		},
		"validateInt":function(str){
			var number = Number(str);
			if(parseInt(number) != number){
				return false;
			}
			return true;
		},
		/**
		 * 校验手机号
		 */
		"validatePhone" : function(str) {
			if (!str)
				return false;
			for (var index = 0; index < str.length; index++) {
				var charCode = str.charCodeAt(index);
				if (charCode < 48 || charCode > 57)
				   return false;
			}
			return true;
		},
		/**
		 * 校验密码
		 */
		"validatePassword" : function(str) {
			if (!str) {
				return false;
			}
			for (var index = 0; index < str.length; index++) {
				var charCode = str.charCodeAt(index);
				if (charCode < 48 || charCode > 57 && charCode < 65
						|| charCode > 122 )
				return false;
			}
			return true;
		},
		/**
		 * 地址校验（汉字英文数字）
		 */
		"validateAddress" : function(str) {
			if (!str) {
				return false;
			}
			// for (var index = 0; index < str.length; index++) {
				// var charCode = str.charCodeAt(index);
				// if ( charCode < 48 || charCode > 57 && charCode < 97
						// || charCode > 122 && charCode < 65 || charCode > 90 && charCode < 19968 || charCode > 40869)
				// return false;
			// }
			return true;
		},
		/**
		 * 长度校验
		 */
		"validateMinLength":function(str,minLength){
			if(util.getStrLength(str)<minLength){
				return false;
			}
			return true;
		},
		"validateMaxLength":function(str,maxLength){
			if(util.getStrLength(str)>maxLength){
				return false;
			}
			return true;
		},
		"validateLength":function(str,minLength,maxLength){
			if(util.getStrLength(str)<minLength||util.getStrLength(str)>maxLength){
				return false;
			}
			return true;
		},
		/**
		 * 校验输入的钱数
         * @param {Object} str
		 */
		"validateMoney":function(str){
			if (!str) {
				return false;
			}
		    var strArray = str.split(".");
		    if(strArray.length==1 && !isNaN(strArray[0]) && Number(strArray[0])>0){
		        return true;
		    }
		    if(strArray.length==2){
		        var nu = strArray[1];
		        if(!isNaN(strArray[0]) && Number(strArray[0])>0 && !isNaN(nu) && nu.length<=2 && nu.length>0){ 
		            return true;}
		      return false; 
		    }
		    return false;
		},
		"validateCard" : function(str) {
			if (!str)
				return false;
			for (var index = 0; index < str.length; index++) {
				var charCode = str.charCodeAt(index);
				if (charCode < 48 || charCode > 57)
				   return false;
			}
			return true;
		},
		/**
		 *校验验证码 
		 */
		"validateCode":function(str,codeLength){
		    var len= util.getStrLength(str);
		    if(len==codeLength && !isNaN(str)){
		        return true;
		    }
		    return false;
		},
		/**
		 *校验number大小
         * @param {Object} str
         * @param {Object} min最小数字(包含)
         * @param {Object} max最大（包含）
		 */
		  "validateNum":function(str,min,max){
		    if(isNaN(str) || !str ||isNaN(min) ||isNaN(min)){
		        return false;
		    }
		    if(str<min ||str>max){
		        return false;
		    }
		    return true;
		}
	};
}
validateUtil = getValidateUtil();