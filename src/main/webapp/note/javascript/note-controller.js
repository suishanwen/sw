var NoteController = function ($scope, $http, $timeout, $location,noteService) {
    var getNote=function(){
        var id=getQueryString(window.location.hash,"thread");
        if(!id){
            id=parseInt(sessionStorage.getItem("id"));
            if(isNaN(id)){
                $location.path("/list");
                return
            }
        }else{
            sessionStorage.setItem("id",id);
            window.location.hash="#/note"
        }
        var url = noteService.server+"api/note/get?id="+id;
        $http.get(url).success(function (data) {
            $scope.note=data;
            $scope.buttonShow = false;
            $("#content")[0].innerHTML=$scope.note.content;
        }).error(function (data) {
        });
    };

    getNote();
    
    $scope.editNote=function(note){
        noteService.note=note;
        $location.path("/note-edit");
    };

    $scope.backList=function(){
        $location.path("/list");
    };

    $scope.buttonDelayHide = function () {
        $timeout(function () {
            $scope.buttonShow = false;
        }, 8000)
    }
};