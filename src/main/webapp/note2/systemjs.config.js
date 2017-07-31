/**
 * System configuration for Angular samples
 * Adjust as necessary for your application needs.
 */
(function (global) {
    System.config({
        paths: {
            // paths serve as alias
            'npm:': 'node_modules/'
        },
        // map tells the System loader where to look for things
        map: {
            // our app is within the app folder
            app: 'app',
            // angular bundles
            '@angular/core': 'http://121.42.239.141/file/node_modules/@angular/core/bundles/core.umd.js',
            '@angular/common': 'http://121.42.239.141/file/node_modules/@angular/common/bundles/common.umd.js',
            '@angular/compiler': 'http://121.42.239.141/file/node_modules/@angular/compiler/bundles/compiler.umd.js',
            '@angular/platform-browser': 'http://121.42.239.141/file/node_modules/@angular/platform-browser/bundles/platform-browser.umd.js',
            '@angular/platform-browser-dynamic': 'http://121.42.239.141/file/node_modules/@angular/platform-browser-dynamic/bundles/platform-browser-dynamic.umd.js',
            '@angular/http': 'http://121.42.239.141/file/node_modules/@angular/http/bundles/http.umd.js',
            '@angular/router': 'http://121.42.239.141/file/node_modules/@angular/router/bundles/router.umd.js',
            '@angular/forms': 'http://121.42.239.141/file/node_modules/@angular/forms/bundles/forms.umd.js',
            // other libraries
            'rxjs': 'http://121.42.239.141/file/node_modules/rxjs',
            'angular2-in-memory-web-api': 'http://121.42.239.141/file/node_modules/angular2-in-memory-web-api',
            'renderer': 'third-party/renderer.js',
            'common': 'third-party/common.js'
},
        // packages tells the System loader how to load when no filename and/or no extension
        packages: {
            app: {
                main: './main.js',
                defaultExtension: 'js'
            },
            rxjs: {
                defaultExtension: 'js'
            },
            'angular2-in-memory-web-api': {
                main: './index.js',
                defaultExtension: 'js'
            }
        }
    });
})(this);