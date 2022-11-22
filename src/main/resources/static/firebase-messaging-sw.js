import { initializeApp } from "https://www.gstatic.com/firebasejs/9.14.0/firebase-app.js";
import { getAnalytics } from "https://www.gstatic.com/firebasejs/9.14.0/firebase-analytics.js";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
        apiKey: "AIzaSyAs9JiWQrk3rzPU_m83mwWQqmMtuWPj2Nc",
        authDomain: "code-community-364713.firebaseapp.com",
        projectId: "code-community-364713",
        storageBucket: "code-community-364713.appspot.com",
        messagingSenderId: "1075812926594",
        appId: "1:1075812926594:web:44545d014285b4ffe0209b",
        measurementId: "G-0NKGRVFBKY"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);