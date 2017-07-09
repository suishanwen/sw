function getQueryString(queryString, name) {
    var reg = new RegExp("(^|&?)" + name + "=([^&]*)(&|$)", "i");
    var r = queryString.substr(1).match(reg);
    if (r != null) {
        return decodeURI(r[2])
    }
    return null;
}

function notify(title, body) {
    if (Notification.permission === "granted") {
        return new Notification(title, {
            body: body,
            icon: "http://121.42.239.141/note/assets/img/icon.png"
        });
    }
}

function IsPC() {
    var userAgentInfo = navigator.userAgent;
    var Agents = ["Android", "iPhone",
        "SymbianOS", "Windows Phone",
        "iPad", "iPod"];
    var flag = true;
    for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) {
            flag = false;
            break;
        }
    }
    return flag;
}


var Path = function () {
    var reg = /^\//;
    return {
        getUri: function (url) {
            if (reg.test(url)) {
                return encodeURI(url);
            } else {
                var pathName = window.document.location.pathname;
                return encodeURI(pathName.substring(0, pathName.substr(1).indexOf('/') + 1) + "/" + url);
            }
        },
        refresh: function () {
            window.document.location.reload();
        }
    };
}();