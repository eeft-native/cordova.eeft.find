var exec = require('cordova/exec');

var find = function (success, error) {
    console.warn('root detection!', success, error);
    exec(success, error, 'Finder', 'find', []);
};
module.exports = {
    find
};

