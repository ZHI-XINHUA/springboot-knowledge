function inArray(suffix, array) {
    for (var i = 0; i < array.length; i++) {
        if (suffix.indexOf(array[i]) != -1)
            return true;
    }
    return false;
}
// 通过后缀返回组件名
function getAppName(suffix) {
    var wpsSuffix = ["doc", "docx", "wps", "wpsx"];
    var etSuffix = ["xls", "xlsx", "et", "etx"];
    var wppSuffix = ["ppt", "pptx", "dps", "dpsx"];
    suffix = suffix.toLowerCase();
    if (inArray(suffix, wpsSuffix)) {
        return "wps";  //文字
    }
    else if (inArray(suffix, etSuffix)) {
        return "et";  //表格
    }
    else if (inArray(suffix, wppSuffix)) {
        return "wpp";  //演示
    }
    else {
        alert("无效后缀");
    }
}