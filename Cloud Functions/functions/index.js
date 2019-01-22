// The Cloud Functions for Firebase SDK to create Cloud Functions and setup triggers.
const functions = require('firebase-functions');

// The Firebase Admin SDK to access the Firebase Realtime Database.
const admin = require('firebase-admin');
admin.initializeApp();

exports.addNotificationMessage = functions.https.onRequest((req, res) => {

    const title = req.query.title;
    const body = req.query.body;

    console.log("Title", title, "Body:", body);

    var refNotification = admin.database().ref('/Notification/Message');
    refNotification.set
    ({
        "Body": body,
        "Title": title
    });

    res.status(200).send("OK");
});