var mineApp = angular.module('mineApp', []);


var mimeController = ["$scope", "$http", "$timeout", "$filter", function ($scope, $http, $timeout, $filter) {
    var Enquiry = function () {
        this.name = "";
        this.email = "";
        this.subject = "";
        this.message = "";
    };
    $scope.now = {};
    $scope.upToDate = false;
    $scope.recommends = [];
    $scope.enquiry = new Enquiry();

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
        return getTimeInfo(postTime, $scope.now);
    }


    function openNote(id) {
        var uri = window.location.href.substring(0, window.location.href.indexOf("/mine"));
        window.open(uri + "/note/index.html#/note?source=sw&&thread=" + id);
    }

    function resetEnquiry() {
        $scope.enquiry = new Enquiry();
    }

    function sendEmail() {
        resetEnquiry();
        alert("感谢！");
        $http.post(Path.getUri("api/note/enquiry"), $scope.enquiry);
    }

    $scope.getPostTime = getPostTime;
    $scope.openNote = openNote;
    $scope.resetEnquiry = resetEnquiry;
    $scope.sendEmail = sendEmail;
    (function () {
        getRecommend();
    })()
}];

mineApp
    .controller('MineController', mimeController);