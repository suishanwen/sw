angular.module('noteApp').service('noteService', function () {
    return {
        server: Path.getUri("")
    }
});

angular.module('noteApp').factory('progress', ['$templateCache', function ($templateCache) {
    var isProcessing = false;
    var progress = null;
    var open = function () {
        if (isProcessing) {
            console.warn("progress is open");
            return false;
        } else {
            isProcessing = true;
            progress = new Progress().open();
        }
    };

    var close = function () {
        if (isProcessing) {
            isProcessing = false;
            progress.close();
        } else {
            console.warn("progress is close");
        }
    };

    function Progress() {
        this._element = angular.element($templateCache.get('progress.html'));
    }

    Progress.prototype.open = function () {
        $(window.top.document).find('body').append(this._element);
        return this;
    };

    Progress.prototype.close = function () {
        this._element.remove();
    };

    return {
        "isProcessing": isProcessing,
        "open": open,
        "close": close
    };
}]);


angular.module('noteApp').factory('$debounce', ['$rootScope', '$browser', '$q', '$exceptionHandler',
    function ($rootScope, $browser, $q, $exceptionHandler) {
        var deferreds = {},
            methods = {},
            uuid = 0;

        function debounce(fn, delay, invokeApply) {
            var deferred = $q.defer(),
                promise = deferred.promise,
                skipApply = (angular.isDefined(invokeApply) && !invokeApply),
                timeoutId, cleanup,
                methodId, bouncing = false;

            // check we dont have this method already registered
            angular.forEach(methods, function (value, key) {
                if (angular.equals(methods[key].fn, fn)) {
                    bouncing = true;
                    methodId = key;
                }
            });

            // not bouncing, then register new instance
            if (!bouncing) {
                methodId = uuid++;
                methods[methodId] = {fn: fn};
            } else {
                // clear the old timeout
                deferreds[methods[methodId].timeoutId].reject('bounced');
                $browser.defer.cancel(methods[methodId].timeoutId);
            }

            var debounced = function () {
                // actually executing? clean method bank
                delete methods[methodId];

                try {
                    deferred.resolve(fn());
                } catch (e) {
                    deferred.reject(e);
                    $exceptionHandler(e);
                }

                if (!skipApply) $rootScope.$apply();
            };

            timeoutId = $browser.defer(debounced, delay);

            // track id with method
            methods[methodId].timeoutId = timeoutId;

            cleanup = function (reason) {
                delete deferreds[promise.$$timeoutId];
            };

            promise.$$timeoutId = timeoutId;
            deferreds[timeoutId] = deferred;
            promise.then(cleanup, cleanup);

            return promise;
        }


        // similar to angular's $timeout cancel
        debounce.cancel = function (promise) {
            if (promise && promise.$$timeoutId in deferreds) {
                deferreds[promise.$$timeoutId].reject('canceled');
                return $browser.defer.cancel(promise.$$timeoutId);
            }
            return false;
        };

        return debounce;
    }]);