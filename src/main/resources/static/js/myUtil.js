var IS_DEVELOP = true;//是否生产环境
var uri;
var url;
var appId = "wx960de9db7158a03b";
if (IS_DEVELOP) {//生产环境
    uri = "f.pyblkj.cn";
    url = "http://" + uri + "/";
} else {
    appId = "wx2a1951f46acc4371";
    uri = "9f44zu.natappfree.cc/";
    url = "http://" + uri + "/";
}

function getLoginUrl(state) {
    if (IS_DEVELOP) {
        //重定向地址
        return "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId + "&redirect_uri=http%3A%2F%2F" + uri + "%2Fcommunity%2Flogin&response_type=code&scope=snsapi_userinfo&state=" + state + "#wechat_redirect";
    } else {
        return "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId + "&redirect_uri=http%3A%2F%2F" + uri + "%2Fcommunity%2Flogin&response_type=code&scope=snsapi_userinfo&state=" + state + "#wechat_redirect";
    }
}

//朋悦比邻
var suffix = "community/";

var areaInforUrl = url + suffix + "areaInfor";
var secondRecordUrl = url + suffix + "secondrecord";

var authUrl = url + suffix + "auth";
var auth2Url = url + suffix + "auth2";
var qrcodeUrl = url + suffix + "qrcode";
var verifyFamily1Url = url + suffix + "verifyFamily1";
var verifyFamily2Url = url + suffix + "verifyFamily2";
var joinFamily1Url = url + suffix + "joinFamily1";
var joinFamily2Url = url + suffix + "joinFamily2";
var familyInforUrl = url + suffix + "familyInfor";
var addPersonUrl = url + suffix + "addPerson";
var houseInforUrl = url + suffix + "houseInfor";
var familyIndexUrl = url + suffix + "familyIndex";
var addAdminUrl = url + suffix + "addAdmin";
var adminInforUrl = url + suffix + "adminInfor";
var downloadUrl = url + suffix + "download";
var reportUrl=url+suffix+"area/reports";

function setCookie(c_name, value, expiredays) {
    var exdate = new Date()
    exdate.setDate(exdate.getDate() + expiredays)
    document.cookie = c_name + "=" + escape(value) +
        ((expiredays == null) ? "" : ";expires=" + exdate.toGMTString())
}

//取回cookie
function getCookie(c_name) {
    if (document.cookie.length > 0) {
        c_start = document.cookie.indexOf(c_name + "=")
        if (c_start != -1) {
            c_start = c_start + c_name.length + 1
            c_end = document.cookie.indexOf(";", c_start)
            if (c_end == -1) c_end = document.cookie.length
            return unescape(document.cookie.substring(c_start, c_end))
        }
    }
    return ""
}

function isLogin(state) {
    console.log(getCookie('openId'));
    console.log(state);
    if (getCookie('openId') == "") {
        window.location.href = getLoginUrl(state);
        return true;
    }
    return false;
}


function isEmpty(v) {
    switch (typeof v) {
        case 'undefined':
            return true;
        case 'string':
            if (v.replace(/(^[ \t\n\r]*)|([ \t\n\r]*$)/g, '').length == 0) return true;
            break;
        case 'boolean':
            if (!v) return true;
            break;
        case 'number':
            if (0 === v || isNaN(v)) return true;
            break;
        case 'object':
            if (null === v || v.length === 0) return true;
            for (var i in v) {
                return false;
            }
            return true;
    }
    return false;
}

function checkPhone() {
    var tel = $('#phone').val();
    if(isEmpty(tel)){
        $.toptip('请输入手机号');
        return false;
    }
    if(tel.length!=11){
        $.toptip('手机号输入位数错误，请检查');
        return false;
    }
    if (!tel || !/1[3|4|5|7|8]\d{9}/.test(tel)) {
        $.toptip('手机号输入错误，请检查');
        return false;
    } else {
        return true;
    }
}

/*function checkIdNo() {
    var regIdNo = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
    var idNo = $("#idNo").val();
    if(!regIdNo.test(idNo)){
        $.toptip('身份证输入错误，请检查');
        return false;
    }else{
        return true;
    }
}*/
function checkIdNo() {
    var id = $("#idNo").val();
    // 1 "验证通过!", 0 //校验不通过
    if(isEmpty(id)){
        $.toptip('请输入身份证');
        return false;
    }
    if(id.length!=18&&id.length!=15){
        $.toptip('身份证输入位数错误，请检查');
        return false;
    }
    var format = /^(([1][1-5])|([2][1-3])|([3][1-7])|([4][1-6])|([5][0-4])|([6][1-5])|([7][1])|([8][1-2]))\d{4}(([1][9]\d{2})|([2]\d{3}))(([0][1-9])|([1][0-2]))(([0][1-9])|([1-2][0-9])|([3][0-1]))\d{3}[0-9xX]$/;
    //号码规则校验
    if (!format.test(id)) {
        $.toptip('身份证输入错误，请检查');
        return false;
    }
    //区位码校验
    //出生年月日校验   前正则限制起始年份为1900;
    var year = id.substr(6, 4),//身份证年
        month = id.substr(10, 2),//身份证月
        date = id.substr(12, 2),//身份证日
        time = Date.parse(month + '-' + date + '-' + year),//身份证日期时间戳date
        now_time = Date.parse(new Date()),//当前时间戳
        dates = (new Date(year, month, 0)).getDate();//身份证当月天数
    if (time > now_time || date > dates) {
        $.toptip('身份证出生日期不合法，请检查');
        return false;
    }
    //校验码判断
    var c = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);   //系数
    var b = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');  //校验码对照表
    var id_array = id.split("");
    var sum = 0;
    for (var k = 0; k < 17; k++) {
        sum += parseInt(id_array[k]) * parseInt(c[k]);
    }
    if (id_array[17].toUpperCase() != b[sum % 11].toUpperCase()) {
        $.toptip('身份证校验码不合法，请检查');
        return false;
    }
    return true;
}