angular.module('noteApp').directive("selfWidth",function(){
    return{
        restrict: 'A',
        link: function(scope,element,attrs){
            element[0].style.width=attrs.selfWidth+"px";
        }
    }
});

/**
 *dom编译期设置元素高度，可以接受数字或者表达式
 */
angular.module('noteApp').directive('selfHeight', ['$timeout', function ($timeout) {
    function _resizeElement(element, selfHeight) {
        element[0].style.height = ((typeof selfHeight === "number") ? selfHeight : eval(selfHeight)) + "px";
    }

    return {
        priority: 1000,
        link: function (scope, element, attrs) {
            var selfHeight = attrs["selfHeight"];
            var on = attrs["on"];
            if (on) {
                $(window).resize(function () {
                    _resizeElement(element, scope.$eval(selfHeight));
                });
                scope.$watch(on, function () {
                    $timeout(function () {
                        _resizeElement(element, scope.$eval(selfHeight));
                    }, 100);
                }, true);
            } else {
                $(window).resize(function () {
                    _resizeElement(element, selfHeight);
                });
                _resizeElement(element, selfHeight);
            }
        }
    };
}]);

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