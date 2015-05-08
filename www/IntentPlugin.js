var IntentPlugin = {

    get: function (cb, ecb) {
        cordova.exec(cb, ecb, 'IntentPlugin', 'getIntentData', []);
    }
};

module.exports = IntentPlugin;

