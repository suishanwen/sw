// SystemJS configuration file, see links for more information
// https://github.com/systemjs/systemjs
// https://github.com/systemjs/systemjs/blob/master/docs/config-api.md

/***********************************************************************************************
 * User Configuration.
 **********************************************************************************************/
const paths: any = {
    // paths serve as alias
    'npm:': 'https://bitcoinrobot.cn/file/node_modules/'
};

/** Map relative paths to URLs. */
const map: any = {
    '@angular': 'npm:@angular',
    'rxjs': 'npm:rxjs',
    'renderer': './third-party/renderer.js',
    'common': './third-party/common.js',
    'main': './main.js'
};

/** User packages configuration. */
const packages: any = {
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
    packages['@angular/' + ngBarrelName] = {main: 'bundles/' + ngBarrelName + '.umd.js', defaultExtension: 'js'};
    // Individual files (~300 requests):
    //packages['@angular/'+pkgName] = { main: 'index.js', defaultExtension: 'js' };
});


const barrels: string[] = [
    // Thirdparty barrels.

    // App specific barrels.
    'app',
    'app/edit',
    'app/list',
    'app/nav',
    'app/home/contact',
    'app/note'
];

barrels.forEach((barrelName: string) => {
    packages[barrelName] = {main: 'index', defaultExtension: 'js'};
});

/** Type declaration for ambient System. */
declare var System: any;

// Apply the user's configuration.
System.config({paths, map, packages});
