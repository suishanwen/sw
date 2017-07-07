var NoteListController = function ($scope, $http, $location,noteService) {
    $scope.notes=[];
    var getNotes=function(){
        var url = noteService.server+"api/note/";
        $http.get(url).success(function (data) {
            $scope.notes=data;
        }).error(function (data) {
            notify("系统提示", "获取数据失败！");
        });
    };
    getNotes();

    $scope.showNote=function(id){
        sessionStorage.setItem("id",id);
        $location.path("/note");
    }
};