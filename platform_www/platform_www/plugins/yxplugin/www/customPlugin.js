cordova.define("yxplugin.CustomPlugin", function(require, exports, module) {
var exec = require('cordova/exec');

exports.coolMethod = function (arg0, success, error) {
    exec(success, error, 'CustomPlugin', 'coolMethod', [arg0]);
};

});
