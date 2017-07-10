var NavController = function ($scope, $location, noteService) {
    $scope.toDoSomething = function (loc) {
        noteService.note = null;
        $location.path('/' + loc)
    };

    (function () {
        RENDERER.init();
    })()
};