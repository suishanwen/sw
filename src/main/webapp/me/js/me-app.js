var mineApp = angular.module('mineApp', []);


var mimeController = ["$scope", "$http", "$timeout", "$filter", function ($scope, $http, $timeout, $filter) {
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

    function getPostTime(postTime, islast) {
        if (islast) {
            return $filter('date')(postTime, 'MM-dd yyyy');
        }
        var pDate = new Date(postTime);
        var year = $scope.now.year - pDate.getFullYear();
        var month = $scope.now.month - (pDate.getMonth() + 1 );
        var days = $scope.now.day - pDate.getDate();
        var weeks = parseInt(days / 7);
        var hours = $scope.now.hours - pDate.getHours();
        var minutes = $scope.now.minutes - pDate.getMinutes();
        var seconds = $scope.now.seconds - pDate.getSeconds();
        if (year === 1 && month < 0) {
            return 12 + month + " month ago";
        } else if (year > 0) {
            return year + " year ago";
        } else if (month === 1 && days < 0) {
            days = parseInt(((new Date().getTime() - pDate.getTime()) / 1000 / 60 / 60 / 24));
            weeks = parseInt(days / 7);
            if (weeks > 0) {
                return weeks + " weeks ago";
            }
            return days + " days ago";
        } else if (month > 0) {
            return month + " month ago";
        } else if (weeks > 0) {
            return weeks + " weeks ago";
        } else if (days === 1 && hours < 0) {
            return 24 + hours + " hours ago";
        } else if (days > 0) {
            return days + " days ago";
        } else if (hours === 1 && minutes < 0) {
            return 60 + minutes + " minutes ago";
        } else if (hours > 0) {
            return hours + " hours ago";
        } else if (minutes === 1 && seconds < 0) {
            return 60 + seconds + " seconds ago";
        } else if (minutes > 0) {
            return minutes + " minutes ago";
        } else {
            return seconds + " seconds ago";
        }
    }

    function sendEmail() {
        alert("感谢！");
    }

    function openNote(id) {
        var uri = window.location.href.substring(0, window.location.href.indexOf("/me"));
        window.open(uri + "/note/index.html#/note?source=sw&&thread=" + id);
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
//test pull
