var exec = require('cordova/exec');

var find = function (success, error) {
    console.warn('FinderXYZ: ', success, error);
    exec(success, error, 'Finder', 'find', []);
};
module.exports = {
    find
};

