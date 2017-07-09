var noteApp = angular.module('noteApp', [ 'ngRoute']);

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

