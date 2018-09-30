cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
  {
    "id": "yxplugin.CustomPlugin",
    "file": "plugins/yxplugin/www/customPlugin.js",
    "pluginId": "yxplugin",
    "clobbers": [
      "navigator.custom"
    ]
  }
];
module.exports.metadata = 
// TOP OF METADATA
{
  "cordova-plugin-whitelist": "1.3.3",
  "yxplugin": "0.0.1"
};
// BOTTOM OF METADATA
});