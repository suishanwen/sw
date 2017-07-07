var NavController = function ($scope, $location, noteService) {
    setBgHeight();
    $(window).resize(setBgHeight);
    $scope.toDoSomething = function (loc) {
        $(window).unbind('resize',setBgHeight);
        noteService.note=null;
        $location.path('/' + loc)
    };
    function setBgHeight(){
        $(".nav-main")[0].style.height = window.innerHeight+'px';
    }
};