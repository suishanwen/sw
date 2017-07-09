angular.module('noteApp').directive("selfWidth",function(){
    return{
        restrict: 'A',
        link: function(scope,element,attrs){
            element[0].style.width=attrs.selfWidth+"px";
        }
    }
});

angular.module('noteApp').directive('fileInput', ['$parse', function ($parse) {
    return {
        restrict: "EA",
        template: "<input type='file'  />",
        replace: true,
        link: function (scope, element, attrs) {
            var accept = attrs.fileAccept;
            if (accept !== undefined) {
                element[0].setAttribute("accept", accept);
            }

            var modelGet = $parse(attrs.fileInput);
            var modelSet = modelGet.assign;
            var onChange = $parse(attrs.onChange);

            var updateModel = function () {
                scope.$apply(function () {
                    modelSet(scope, element[0].files[0]);
                    onChange(scope);
                });
            };
            element.bind('change', updateModel);
        }
    };
}]);