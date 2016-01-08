var mediaRecLive = {
    createEvent: function(maxSize,successCallback, errorCallback) {
        cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'MediaSizeCut', // mapped to our native Java class called "MediaSizeCut"
            'onClick1', // with this action name
            [maxSize],
        ); 
    }
}
module.exports = mediaRecLive;