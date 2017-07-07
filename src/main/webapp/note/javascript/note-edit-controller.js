var NoteEditController = function ($scope, $http, $timeout, $location, noteService) {
    var newNote = function () {
        $scope.selectedNote = {
            id: null,
            poster: "",
            title: "",
            content: "",
            ip:"",
            postTime: null,
            editTime: null
        }
    };

    var initNote = function () {
        $scope.selectedNote = noteService.note;
        if (!$scope.selectedNote) {
            newNote()
        }
    };

    initNote();

    $scope.saveNote = function () {
        var url;
        if ($scope.selectedNote.id) {
            url = noteService.server + "api/note/edit"
        } else {
            url = noteService.server + "api/note/add/";
        }
        $scope.$apply(function(){
            $scope.selectedNote.content = $("#content").val();
        });
        $http.post(url, $scope.selectedNote).success(function (data) {
            if(angular.equals(data,"")||angular.equals(data,null)){
                notify("系统提示", "当前IP不允许编辑此贴！");
                return;
            }
            sessionStorage.setItem("id", data.id);
            $location.path("/note");
        }).error(function (data) {
            notify("系统提示", "提交失败！");
        });
    };

    $scope.backNote = function () {
        if ($scope.selectedNote.id) {
            $location.path("/note");
        } else {
            $location.path("/list");
        }

    };

    $scope.newEditor = function () {
        new TQEditor('content');
        $("#TQEditorContainer_content")[0].style.width = "808px";
        var dom = $("#content")[0];
        $timeout(function () {
            dom.focus();
        }, 100);
    };


    content.onblur = function () {
        $timeout(function(){
            $scope.selectedNote.content = $("#content").val();
        });
    };
};