/*var mediaRecLive = {
    createEvent: function(mediaSize, successCallback, errorCallback) {
        cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'Capture', // mapped to our native Java class called "CalendarPlugin"
            'onClick1', // with this action name
            [{                  // and this array of custom arguments to create our entry
                "mediaSize": 20
            }]
        ); 
    }
}*/
var mediaRecLive = {
    createEvent: function(successCallback, errorCallback) {
        cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'MediaSizeCut', // mapped to our native Java class called "MediaSizeCut"
            'onClick1', // with this action name
            []
        ); 
    }
}
module.exports = mediaRecLive;