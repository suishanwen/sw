var noteApp = angular.module('noteApp', [ 'ngRoute']);

noteApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
        when('/nav', {
            templateUrl: '../note1/nav.html',
            controller: 'NavController'
        }).
        when('/list', {
            templateUrl: '../note1/note-list.html',
            controller: 'NoteListController'
        }).
        when('/note', {
            templateUrl: '../note1/note.html',
            controller: 'NoteController'
        }).
        when('/note-edit', {
            templateUrl: '../note1/note-edit.html',
            controller: 'NoteEditController'
        }).
        when('/upload', {
            templateUrl: '../note1/upload.html',
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

