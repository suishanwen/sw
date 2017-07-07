var UploadController = function ($scope, $http, progress, noteService) {
    $scope.file = {};

    $scope.readFile = function () {
        console.log($scope.file);
    };

    $scope.upload = function () {
        if (!angular.equals($scope.file, {})) {
            var fd = new FormData();
            fd.append("file", $scope.file);
            if (progress.isProcessing) {
                return false;
            } else {
                progress.open();
            }
            $http.post(noteService.server+"api/file/upload", fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': "multipart/form-data"}
            }).success(function (data, status) {
                notify("系统提示", "上传成功！");
                progress.close();
            }).error(function (data) {
                progress.close();
                notify("上传失败", data.message);
            });
        }
    }
};