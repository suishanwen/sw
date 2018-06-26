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
            icon: "http://bitcoinrobot.cn/file/img/note/back.jpg"
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

function isRepeat(arr) {
    var hash = {};
    for (var i in arr) {
        if (hash[arr[i]])
            return true;
        hash[arr[i]] = true;
    }
    return false;
}

function getUnit(count, unit) {
    if (count === 1 || unit === "month") {
        return count + " " + unit + " ago";
    } else {
        return count + " " + unit + "s ago";

    }
}

function getTimeInfo(t, n) {
    var pDate = new Date(t);
    var year = n.year - pDate.getFullYear();
    var month = n.month - (pDate.getMonth() + 1 );
    var days = n.day - pDate.getDate();
    var weeks = parseInt(days / 7);
    var hours = n.hours - pDate.getHours();
    var minutes = n.minutes - pDate.getMinutes();
    var seconds = n.seconds - pDate.getSeconds();
    if (year === 1 && month < 0) {
        return getUnit(12 + month, "month");
    } else if (year > 0) {
        return getUnit(year, "year");
    } else if (month === 1 && days < 0) {
        days = parseInt(((new Date().getTime() - pDate.getTime()) / 1000 / 60 / 60 / 24));
        weeks = parseInt(days / 7);
        if (weeks > 0) {
            return getUnit(weeks, "week");
        }
        return getUnit(days, "day");
    } else if (month > 0) {
        return getUnit(month, "month");
    } else if (weeks > 0) {
        return getUnit(weeks, "week");
    } else if (days === 1 && hours < 0) {
        return getUnit(24 + hours, "hour");
    } else if (days > 0) {
        return getUnit(days, "day");
    } else if (hours === 1 && minutes < 0) {
        return getUnit(60 + minutes, "minute");
    } else if (hours > 0) {
        return getUnit(hours, "hour");
    } else if (minutes === 1 && seconds < 0) {
        return getUnit(60 + seconds, "second");
    } else if (minutes > 0) {
        return getUnit(minutes, "minute");
    } else {
        return getUnit(seconds, " second");
    }
}


var Path = function () {
    var reg = /^\//;
    return {
        getUri: function (url) {
            var pathName = window.document.location.pathname;
            if (reg.test(url)) {
                return encodeURI(url);
            } else {
                return encodeURI(pathName.substring(0, pathName.substr(1).indexOf('/') + 1) + "/" + url);
            }
        },
        refresh: function () {
            window.document.location.reload();
        }
    };
}();