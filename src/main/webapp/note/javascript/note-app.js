var noteApp = angular.module('noteApp', [ 'ngRoute']);
noteApp.run(["$q","$templateCache",function($q,$templateCache){
    var checkPermission=function(){
        var deferred=$q.defer();
        if (Notification.permission !== "granted") {
            Notification.requestPermission(function(status){
                if(status==="granted"){
                    deferred.resolve(true)
                }else{
                    deferred.resolve(false)
                }
            });
        }else{
            deferred.resolve(true)
        }
        return deferred.promise;
    };
    if(IsPC()){
        checkPermission().then(function(result){
            if(result){
                notify("隔壁老王群", "隔壁老王 475692491，群主叫逗逗\n一个很牛逼的人");
            }
        });
    }
    $templateCache.put('progress.html', '<div class="alert-level">\n    <i style="position: absolute;top: 40%;left: 50%;right: 50%;" class=\'progress spin icon-loading\'>\n</div>');
}]);
noteApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
        when('/nav', {
            templateUrl: '../note/nav.html',
            controller: 'NavController'
        }).
        when('/list', {
            templateUrl: '../note/note-list.html',
            controller: 'NoteListController'
        }).
        when('/note', {
            templateUrl: '../note/note.html',
            controller: 'NoteController'
        }).
        when('/note-edit', {
            templateUrl: '../note/note-edit.html',
            controller: 'NoteEditController'
        }).
        when('/upload', {
            templateUrl: '../note/upload.html',
            controller: 'UploadController'
        }).otherwise({
            redirectTo: '/nav'
        });
    }]);

noteApp
    .controller('NavController', NavController)
    .controller('NoteListController', NoteListController)
    .controller('NoteController', NoteController)
    .controller('NoteEditController', NoteEditController)
    .controller('UploadController', UploadController);

noteApp.service('noteService', function () {
    return {
        server: 'http://121.42.239.141:8051/sw/'
    }
});

noteApp.factory('progress', ['$templateCache', function ($templateCache) {
    var isProcessing = false;
    var progress = null;
    var open = function () {
        if (isProcessing) {
            console.warn("progress is open");
            return false;
        } else {
            isProcessing = true;
            progress = new Progress().open();
        }
    };

    var close = function () {
        if (isProcessing) {
            isProcessing = false;
            progress.close();
        } else {
            console.warn("progress is close");
        }
    };

    function Progress() {
        this._element = angular.element($templateCache.get('progress.html'));
    }

    Progress.prototype.open = function () {
        $(window.top.document).find('body').append(this._element);
        return this;
    };

    Progress.prototype.close = function () {
        this._element.remove();
    };

    return {
        "isProcessing": isProcessing,
        "open": open,
        "close": close
    };
}]);

noteApp.directive("selfWidth",function(){
    return{
        restrict: 'A',
        link: function(scope,element,attrs){
            element[0].style.width=attrs.selfWidth+"px";
        }
    }
});

noteApp.directive('fileInput', ['$parse', function ($parse) {
    return {
        restrict: "EA",
        template: "<input type='file'  />",
        replace: true,
        link: function (scope, element, attrs) {
            var accept = attrs.fileAccept;
            if (accept !== undefined) {
                element[0].setAttribute("accept", accept);
            }

            var modelGet = $parse(attrs.fileInput);
            var modelSet = modelGet.assign;
            var onChange = $parse(attrs.onChange);

            var updateModel = function () {
                scope.$apply(function () {
                    modelSet(scope, element[0].files[0]);
                    onChange(scope);
                });
            };
            element.bind('change', updateModel);
        }
    };
}]);