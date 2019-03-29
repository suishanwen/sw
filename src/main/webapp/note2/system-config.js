// SystemJS configuration file, see links for more information
// https://github.com/systemjs/systemjs
// https://github.com/systemjs/systemjs/blob/master/docs/config-api.md
/***********************************************************************************************
 * User Configuration.
 **********************************************************************************************/
var paths = {
    // paths serve as alias
    'npm:': 'https://bitcoinrobot.cn/file/node_modules/'
};
/** Map relative paths to URLs. */
var map = {
    '@angular': 'npm:@angular',
    'rxjs': 'npm:rxjs',
    'renderer': './third-party/renderer.js',
    'common': './third-party/common.js',
    'main': './main.js'
};
/** User packages configuration. */
var packages = {
    rxjs: {
        defaultExtension: 'js'
    }
};
// Angular specific barrels.
var ngBarrels = [
    'core',
    'common',
    'compiler',
    'platform-browser',
    'platform-browser-dynamic',
    'router',
    'http',
    'forms'
];
// Add package entries for angular packages
ngBarrels.forEach(function (ngBarrelName) {
    // Bundled (~40 requests):
    packages['@angular/' + ngBarrelName] = { main: 'bundles/' + ngBarrelName + '.umd.js', defaultExtension: 'js' };
    // Individual files (~300 requests):
    //packages['@angular/'+pkgName] = { main: 'index.js', defaultExtension: 'js' };
});
var barrels = [
    // Thirdparty barrels.
    // App specific barrels.
    'app',
    'app/edit',
    'app/list',
    'app/nav',
    'app/home/contact',
    'app/note'
];
barrels.forEach(function (barrelName) {
    packages[barrelName] = { main: 'index', defaultExtension: 'js' };
});
// Apply the user's configuration.
System.config({ paths: paths, map: map, packages: packages });
//# sourceMappingURL=system-config.js.map