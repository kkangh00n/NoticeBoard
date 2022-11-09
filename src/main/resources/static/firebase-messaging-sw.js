importScripts('https://www.gstatic.com/firebasejs/5.9.2/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/5.9.2/firebase-messaging.js');

// Initialize Firebase
let firebaseConfig = {
        apiKey: "AIzaSyAs9JiWQrk3rzPU_m83mwWQqmMtuWPj2Nc",
        authDomain: "code-community-364713.firebaseapp.com",
        projectId: "code-community-364713",
        storageBucket: "code-community-364713.appspot.com",
        messagingSenderId: "1075812926594",
        appId: "1:1075812926594:web:44545d014285b4ffe0209b",
        measurementId: "G-0NKGRVFBKY"
};
firebase.initializeApp(firebaseConfig);
const messaging = firebase.messaging();