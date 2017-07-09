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