var NavController = function ($scope, $location, noteService) {
    $scope.toDoSomething = function (loc) {
        noteService.note = null;
        $location.path('/' + loc)
    };

    (function () {
        var height = ($(window).height() - 300) / 2;
        $(".nav-index .add").css("margin", height + "px 150px");
        $(".nav-index .find").css("margin", height + "px 150px");
        RENDERER.init();
    })()
};