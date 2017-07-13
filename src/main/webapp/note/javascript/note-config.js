angular.module('noteApp').run(["$q","$templateCache",function($q,$templateCache){
    var checkPermission=function(){
        var deferred=$q.defer();
        if (Notification.permission !== "granted") {
            Notification.requestPermission(function(status){
                if(status==="granted"){
                    deferred.resolve(true)
                }else{
                    deferred.resolve(false)
                }
            });
        }else{
            deferred.resolve(true)
        }
        return deferred.promise;
    };
    if(IsPC()){
        checkPermission().then(function(result){
            if(result){
                notify("隔壁老王群", "隔壁老王 475692491，群主叫逗逗\n一个很牛逼的人");
            }
        });
    }
    $templateCache.put('progress.html', '<div class="alert-level">\n    <i style="position: absolute;top: 50%;left: 50%;" class=\'fa fa-spinner fa-pulse\'>\n</div>');
}]);