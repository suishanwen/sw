var mineApp = angular.module('mineApp', []);


var mimeController = ["$scope", "$http", "$timeout", function ($scope, $http, $timeout) {
    $scope.now = {};
    $scope.upToDate = false;
    $scope.recommends = [];
    function getRecommend() {
        $http.get(Path.getUri("api/note/recommend")).success(function (data) {
            $scope.recommends = data;
            $scope.recommends.forEach(function (recommend) {
                recommend.tags = recommend.tag.split("|");
            });
            console.log($scope.recommends);
            $scope.upToDate = true;
            var now = new Date();
            $scope.now = {
                year: now.getFullYear(),
                month: now.getMonth() + 1,
                day: now.getDate(),
                hours: now.getHours(),
                minutes: now.getMinutes(),
                seconds: now.getSeconds()
            };
            $timeout(function () {
                $(window).resize();
            });
        });
    }

    function getPostTime(postTime) {
        var pDate = new Date(postTime);
        var year = $scope.now.year - pDate.getFullYear();
        var month = $scope.now.month - (pDate.getMonth() + 1 );
        var day = $scope.now.day - pDate.getDate();
        var hours = $scope.now.hours - pDate.getHours();
        var minutes = $scope.now.minutes - pDate.getMinutes();
        var seconds = $scope.now.seconds - pDate.getSeconds();
        if (year === 1 && month < 0) {
            return 12 + month + " month";
        } else if (year > 0) {
            return year + " year";
        } else if (month === 1 && day < 0) {
            return parseInt(((new Date().getTime() - pDate.getTime()) / 1000 / 60 / 60 / 24)) + " days";
        } else if (month > 0) {
            return month + " month";
        } else if (day === 1 && hours < 0) {
            return 24 + hours + " hours";
        } else if (day > 0) {
            return day + " day";
        } else if (hours === 1 && minutes < 0) {
            return 60 + minutes + " minutes";
        } else if (hours > 0) {
            return hours + " hours";
        } else if (minutes === 1 && seconds < 0) {
            return 60 + seconds + " seconds";
        } else if (minutes > 0) {
            return minutes + " minutes";
        } else {
            return seconds + " seconds";
        }
    }

    function sendEmail() {
        alert("感谢！");
    }

    function openNote(id) {
        window.open(uri +"/note/index.html#/note?source=sw&&thread=" + id);
    }

    $scope.getPostTime = getPostTime;
    $scope.openNote = openNote;
    $scope.sendEmail = sendEmail;
    (function () {
        getRecommend();
    })()
}];

mineApp
    .controller('MineController', mimeController);

