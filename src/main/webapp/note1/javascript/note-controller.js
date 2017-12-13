var NoteController = ["$scope", "$http", "$timeout", "$location", "noteService", "progress", function ($scope, $http, $timeout, $location, noteService, progress) {
    $scope.source = "";
    function getNote() {
        var source = getQueryString(window.location.hash, "source");
        var id = getQueryString(window.location.hash, "thread");
        if (!id) {
            id = parseInt(sessionStorage.getItem("id"));
            $scope.source = sessionStorage.getItem("source");
            if (isNaN(id)) {
                $location.path("/list");
                return;
            }
        } else {
            sessionStorage.setItem("id", id);
            sessionStorage.setItem("source", source);
            window.location.hash = "#/note";
            return;
        }
        var url = noteService.server + "api/note/get?id=" + id;
        progress.open();
        $http.get(url).success(function (data) {
            $scope.note = data;
            $scope.buttonShow = false;
            $("#content")[0].innerHTML = $scope.note.content;
            progress.close();
        }).error(function (data) {
            notify("查询失败", data.message);
            progress.close();
        });
    }

    getNote();

    $scope.editNote = function (note) {
        noteService.note = note;
        $location.path("/note-edit");
    };

    $scope.backList = function () {
        $location.path("/list");
    };

    $scope.buttonDelayHide = function () {
        $timeout(function () {
            $scope.buttonShow = false;
        }, 8000)
    }
}];