var IntentPlugin = {
    foo: function () {
        console.log('foo!');
    },
    test: function (cb, ecb) {
        cordova.exec(cb, ecb, 'IntentPlugin', 'getIntentData', []);
    },
    get: function () {
        var d = $.Deferred();
        cordova.exec(function (data) {
            d.resolve(data);
        }, function (error) {
            d.reject('error', error);
        }, 'IntentPlugin', 'getIntentData', []);
        return d;
    }
};

module.exports = IntentPlugin;

