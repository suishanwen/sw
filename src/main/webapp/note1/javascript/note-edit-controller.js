var NoteEditController = ["$scope", "$http", "$timeout", "$location", "noteService", "$debounce", "progress", function ($scope, $http, $timeout, $location, noteService, $debounce, progress) {
    var oldVal = "";
    var tags = [];
    var Note = function () {
        this.id = null;
        this.poster = "";
        this.title = "";
        this.summary = "";
        this.tag = "";
        this.recommend = 0;
        this.content = "";
        this.ip = "";
        this.postTime = null;
        this.editTime = null;
    };

    function initNote() {
        $scope.selectedNote = noteService.note;
        if (!$scope.selectedNote) {
            $scope.selectedNote = new Note();
        }
    }

    function changeTag() {
        var newVal = $scope.selectedNote.tag;
        if (newVal !== tags.join("|")) {
            var tag = newVal.substring(oldVal.length);
            tags = oldVal.split("|");
            tags.push(tag);
            if (!isRepeat(tags)) {
                $scope.selectedNote.tag = tags.join("|");
            } else {
                $scope.selectedNote.tag = oldVal;
            }
        }
    }

    $scope.saveNote = function () {
        var url;
        if ($scope.selectedNote.id) {
            url = noteService.server + "api/note/edit"
        } else {
            url = noteService.server + "api/note/add/";
        }
        progress.open();
        $http.post(url, $scope.selectedNote).success(function (data) {
            if (angular.equals(data, "") || angular.equals(data, null)) {
                notify("系统提示", "当前IP不允许编辑此贴！");
                return;
            }
            sessionStorage.setItem("id", data.id);
            progress.close();
            $location.path("/note");
        }).error(function (data) {
            progress.close();
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


    (function () {
        initNote();

        content.onblur = function () {
            $timeout(function () {
                $scope.selectedNote.content = $("#content").val();
            });
        };

        /*输入内容改变后触发*/
        $scope.$watch('selectedNote.tag', function (newValue, oldValue) {
            if (newValue && oldValue && newValue.length > oldValue.length) {
                oldVal = oldValue;
                $debounce(changeTag, 1000);
            }
        });
    })();

}];