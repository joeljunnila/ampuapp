var savedImage = {
    ohje1: "ohje1",
    ohje2: "ohje2",
    ohje2B: "ohje2b",
    ohje3: "ohje3",
    ohje4: "ohje4",
    ohje5: "ohje5", 
    ohje5B: "ohje5b",
    ohje6: "ohje6",
    ohje6B: "ohje6b",
    ohje7: "ohje7",
    ohje8: "ohje8",
    ohje8B: "ohje8b",
    ohje9: "ohje9",
    ohje9B: "ohje9b",
    ohje10: "ohje10"
}

var savedText = {
    valmistautuminenSivu1: "valmistautuminenSivu1",
    valmistautuminenSivu2: "valmistautuminenSivu2",
    valmistautuminenSivu3: "valmistautuminenSivu3",
    valmistautuminenSivu4: "valmistautuminenSivu4",
    valmistautuminenSivu5: "valmistautuminenSivu5",

    synnytyksenAikanaSivu1: "synnytyksenAikanaSivu1",
    synnytyksenAikanaSivu2: "synnytyksenAikanaSivu2",
    synnytyksenAikanaSivu3: "synnytyksenAikanaSivu3",
    synnytyksenAikanaSivu4: "synnytyksenAikanaSivu4",
    synnytyksenAikanaSivu5: "synnytyksenAikanaSivu5",
    synnytyksenAikanaSivu6: "synnytyksenAikanaSivu6",

    synnytyksenJalkeenSivu1: "synnytyksenJalkeenSivu1",
    synnytyksenJalkeenSivu2: "synnytyksenJalkeenSivu2",
    synnytyksenJalkeenSivu3: "synnytyksenJalkeenSivu3",
    synnytyksenJalkeenSivu4: "synnytyksenJalkeenSivu4",

    peratilaSivu1: "peratilaSivu1",
    peratilaSivu2: "peratilaSivu2",
    peratilaSivu3: "peratilaSivu3",
    peratilaSivu4: "peratilaSivu4",
    peratilaSivu5: "peratilaSivu5",

    hartiadystokiaSivu1: "hartiadystokiaSivu1",
    hartiadystokiaSivu2: "hartiadystokiaSivu2",
    hartiadystokiaSivu3: "hartiadystokiaSivu3",

    napanuoraSivu1: "napanuoraSivu1",
    napanuoraSivu2: "napanuoraSivu2",
    napanuoraSivu3: "napanuoraSivu3",

    laakeohjeetSivu: "laakeohjeetSivu",
};

var usedImage = {
    synnytyksenAikanaSivu2: "ohje1",
    synnytyksenAikanaSivu3: "ohje2",
    synnytyksenAikanaSivu4: "ohje2B",
    synnytyksenAikanaSivu6: "ohje3",

    peratilaSivu1: "ohje4",
    peratilaSivu2: "ohje5",
    peratilaSivu3: "ohje5B",
    peratilaSivu4: "ohje6",
    peratilaSivu5: "ohje6B",

    hartiadystokiaSivu1: "ohje7",
    hartiadystokiaSivu2: "ohje8",
    hartiadystokiaSivu3: "ohje8B",

    napanuoraSivu1: "ohje9",
    napanuoraSivu2: "ohje9B",
    napanuoraSivu3: "ohje10",
};

var firebaseConfig = {
    apiKey: "AIzaSyDUbaZX8mHLAKMiX5Kd1i6xuViu0kDEGEg",
    authDomain: "ambuapp-2691e.firebaseapp.com",
    projectId: "ambuapp-2691e",
    storageBucket: "ambuapp-2691e.appspot.com",
    messagingSenderId: "812625720469",
    appId: "1:812625720469:web:851a28a3eefbac8374d79c"
};

var activityName = "kotisivu";
var user = null;

window.onload = function loadFirst() {
    document.getElementById("phone").style.display = "none";
    changeContent("kotisivu");

    firebase.initializeApp(firebaseConfig);

    document.getElementById("newImageCustomButton").onclick = function () {
        document.getElementById("newImage").click();
    }

    document.getElementById("changeImage").onclick = function () {
        document.getElementById("homeIcon").onclick = function () { }
        document.getElementById("leftArrow").onclick = function () { }
        document.getElementById("rightArrow").onclick = function () { }
        document.getElementById("saveButton").onclick = function () { }
        document.getElementById("saveButton").style.display = "none";
        document.getElementById("newImage").value = "";

        document.getElementById("updateViewMenu").style.display = "none";
        document.getElementById("updateViewButtons").style.display = "block";
        document.getElementById("newImageCustomButton").style.display = "block";

        document.getElementById("saveButton").onclick = function () {
            putImage(activityName);
            document.getElementById("cancelButton").click();
        }
    }

    document.getElementById("changeText").onclick = function () {
        document.getElementById("homeIcon").onclick = function () { }
        document.getElementById("leftArrow").onclick = function () { }
        document.getElementById("rightArrow").onclick = function () { }

        document.getElementById("updateViewMenu").style.display = "none";
        document.getElementById("updateViewButtons").style.display = "block";
        document.getElementById("newImageCustomButton").style.display = "none";
        document.getElementById("saveButton").style.display = "block";
        document.getElementById("text").disabled = false;
        document.getElementById("text").focus();

        document.getElementById("saveButton").onclick = function () {
            var newText = document.getElementById("text").value;
            putText(activityName);
            document.getElementById("cancelButton").click();
            document.getElementById("text").value = newText;
        }
    }

    document.getElementById("cancelButton").onclick = function () {
        document.getElementById("updateViewMenu").style.display = "block";
        document.getElementById("updateViewButtons").style.display = "none";
        document.getElementById("homeIcon").onclick = function () { changeContent("kotisivu"); }
        document.getElementById("text").disabled = true;
        changeContent(activityName);
    }
}

function userLogin() {
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value

    firebase.auth().signInWithEmailAndPassword(email, password)
        .then((userCredentials) => {
            user = userCredentials.user;
            for (var key in savedText) getText(key);
            for (var key in savedImage) getImage(key);
            document.getElementById("phone").style.display = "block";
            document.getElementById("login").style.display = "none";
            document.getElementById("virheilmoitus").style.display = "none"
            document.getElementById("email").value = "";
            document.getElementById("password").value = "";
            document.getElementById("userEmail").textContent = String(email);
            document.getElementById("userEmail").style.display = "block";
            document.getElementById("logoutBtn").style.display = "block";
        })
        .catch(e => {
            console.log(e);
            document.getElementById("virheilmoitus").style.display = "block"
        })
}

function logout() {
    console.log("logout");
    firebase.auth().signOut().then(() => {
        document.getElementById("cancelButton").click();
        document.getElementById("phone").style.display = "none";
        changeContent("kotisivu");
        document.getElementById("login").style.display = "block";
        document.getElementById("logoutBtn").style.display = "none";
        document.getElementById("userEmail").textContent = "";
        document.getElementById("userEmail").style.display = "none";
    }).catch(e => {
        console.log(e);
    })
    user = null;
}

function getImage(key) {
    var ref = firebase.storage().ref("kuvat/" + key + ".jpg");
    ref.getDownloadURL()
        .then((url) => {
            savedImage[key] = url;
        })
        .catch((error) => {
            switch (error.code) {
                case 'storage/object-not-found':
                    console.log("File doesn't exist")
                    break;
                case 'storage/unauthorized':
                    console.log("User doesn't have permission to access the object")
                    break;
                case 'storage/canceled':
                    console.log("User canceled the upload")
                    break;
                case 'storage/unknown':
                    console.log("Unknown error occurred, inspect the server response")
                    break;
            }
        });
}

function getText(key) {
    var ref = firebase.storage().ref("tekstit/" + key + ".txt");
    ref.getDownloadURL()
        .then((url) => {
            fetch(url).then(function (response) {
                response.text().then(function (text) {
                    savedText[key] = text;
                });
            });
        })
        .catch((error) => {
            switch (error.code) {
                case 'storage/object-not-found':
                    console.log("File doesn't exist")
                    break;
                case 'storage/unauthorized':
                    console.log("User doesn't have permission to access the object")
                    break;
                case 'storage/canceled':
                    console.log("User canceled the upload")
                    break;
                case 'storage/unknown':
                    console.log("Unknown error occurred, inspect the server response")
                    break;
            }
        });
}

function putText(key) {
    var newText = document.getElementById("text").value;
    var ref = firebase.storage().ref("tekstit/" + key + ".txt");
    ref.putString(newText).then((snapshot) => {
        savedText[key] = newText;
    });
}

function putImage(key) {
    var newImage = document.getElementById("newImage").files[0];
    var ref = firebase.storage().ref("kuvat/" + usedImage[key] + ".jpg");
    var uploadTask = ref.put(newImage);
    uploadTask.on("state_changed", function progress(snapshot) {
        //var percent = (snapshot.bytesTranferred / snapshot.totalBytes) * 100;
        //document.getElementById("progressbar").value = percent;
    }, function error(err) {
        console.log("upload image error");
    }, function complete() {
        uploadTask.snapshot.ref.getDownloadURL().then(function (url) {
            savedImage[usedImage[key]] = url;
            document.getElementById("image").src = url;
        })
    });
}

function onNewImageSelected() {
    var newImage = document.getElementById("newImage").files[0];
    document.getElementById("image").src = window.URL.createObjectURL(newImage);
    document.getElementById("saveButton").style.display = "block";
}

function changeContent(page) {
    switch (page) {
        case "kotisivu":
            activityName = "kotisivu";
            document.getElementById("menuLayout").style.display = "block";
            document.getElementById("imageTextLayout").style.display = "none";
            document.getElementById("button4").style.visibility = "visible";
            document.getElementById("button5").style.visibility = "visible";
            document.getElementById("leftArrow").style.visibility = "hidden";
            document.getElementById("rightArrow").style.visibility = "hidden";
            document.getElementById("stepView").style.display = "none";

            document.getElementById("button1").textContent = "Valmistautuminen";
            document.getElementById("button2").textContent = "Synnytysvaiheet";
            document.getElementById("button3").textContent = "Jälkeisvaiheet";
            document.getElementById("button4").textContent = "Erikoistilanteet";
            document.getElementById("button5").textContent = "Lääkeohjeet";

            document.getElementById("button1").onclick = function () { changeContent("valmistautuminenSivu1"); }
            document.getElementById("button2").onclick = function () { changeContent("synnytyksenAikanaSivu1"); }
            document.getElementById("button3").onclick = function () { changeContent("synnytyksenJalkeenSivu1"); }
            document.getElementById("button4").onclick = function () { changeContent("erikoistilanteetSivu"); }
            document.getElementById("button5").onclick = function () { changeContent("laakeohjeetSivu"); }

            document.getElementById("changeImage").style.visibility = "hidden";
            document.getElementById("changeText").style.visibility = "hidden";
            break;
        case "erikoistilanteetSivu":
            activityName = "erikoistilanteetSivu";
            document.getElementById("menuLayout").style.display = "block";
            document.getElementById("imageTextLayout").style.display = "none";
            document.getElementById("button4").style.visibility = "hidden";
            document.getElementById("button5").style.visibility = "hidden";
            document.getElementById("leftArrow").style.visibility = "visible";
            document.getElementById("rightArrow").style.visibility = "hidden";
            document.getElementById("stepView").style.display = "none";

            document.getElementById("button1").textContent = "Perätila";
            document.getElementById("button2").textContent = "Hartiadystokia";
            document.getElementById("button3").textContent = "Napanuoran esiinluiskahdus";

            document.getElementById("button1").onclick = function () { changeContent("peratilaSivu1"); }
            document.getElementById("button2").onclick = function () { changeContent("hartiadystokiaSivu1"); }
            document.getElementById("button3").onclick = function () { changeContent("napanuoraSivu1"); }

            document.getElementById("leftArrow").onclick = function () { changeContent("kotisivu"); }

            document.getElementById("changeImage").style.visibility = "hidden";
            document.getElementById("changeText").style.visibility = "hidden";
            break;
        case "valmistautuminenSivu1":
            activityName = "valmistautuminenSivu1";
            document.getElementById("menuLayout").style.display = "none";
            document.getElementById("imageTextLayout").style.display = "block";
            document.getElementById("image").style.display = "none";
            document.getElementById("leftArrow").style.visibility = "visible";
            document.getElementById("rightArrow").style.visibility = "visible";
            document.getElementById("leftArrow").onclick = function () { changeContent("kotisivu"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("valmistautuminenSivu2"); }
            document.getElementById("text").value = savedText[activityName];
            document.getElementById("changeImage").style.visibility = "hidden";
            document.getElementById("changeText").style.visibility = "visible";

            document.getElementById("stepView").style.display = "block";
            document.getElementById("stepView4").style.display = "inline-block";
            document.getElementById("stepView5").style.display = "inline-block";
            document.getElementById("stepView6").style.display = "none";

            document.getElementById("stepView1").style.color = "#AF0505";
            document.getElementById("stepView1").style.background = "#FFD4E3";
            document.getElementById("stepView2").style.color = "#808080";
            document.getElementById("stepView2").style.background = "#BDBDBD";
            document.getElementById("stepView3").style.color = "#808080";
            document.getElementById("stepView3").style.background = "#BDBDBD";
            document.getElementById("stepView4").style.color = "#808080";
            document.getElementById("stepView4").style.background = "#BDBDBD";
            document.getElementById("stepView5").style.color = "#808080";
            document.getElementById("stepView5").style.background = "#BDBDBD";
            break;
        case "valmistautuminenSivu2":
            activityName = "valmistautuminenSivu2";
            document.getElementById("leftArrow").onclick = function () { changeContent("valmistautuminenSivu1"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("valmistautuminenSivu3"); }
            document.getElementById("text").value = savedText[activityName];

            document.getElementById("stepView1").style.color = "#808080";
            document.getElementById("stepView1").style.background = "#BDBDBD";
            document.getElementById("stepView2").style.color = "#AF0505";
            document.getElementById("stepView2").style.background = "#FFD4E3";
            document.getElementById("stepView3").style.color = "#808080";
            document.getElementById("stepView3").style.background = "#BDBDBD";
            break;
        case "valmistautuminenSivu3":
            activityName = "valmistautuminenSivu3";
            document.getElementById("leftArrow").onclick = function () { changeContent("valmistautuminenSivu2"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("valmistautuminenSivu4"); }
            document.getElementById("text").value = savedText[activityName];

            document.getElementById("stepView2").style.color = "#808080";
            document.getElementById("stepView2").style.background = "#BDBDBD";
            document.getElementById("stepView3").style.color = "#AF0505";
            document.getElementById("stepView3").style.background = "#FFD4E3";
            document.getElementById("stepView4").style.color = "#808080";
            document.getElementById("stepView4").style.background = "#BDBDBD";
            break;
        case "valmistautuminenSivu4":
            activityName = "valmistautuminenSivu4";
            document.getElementById("leftArrow").onclick = function () { changeContent("valmistautuminenSivu3"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("valmistautuminenSivu5"); }
            document.getElementById("text").value = savedText[activityName];

            document.getElementById("stepView3").style.color = "#808080";
            document.getElementById("stepView3").style.background = "#BDBDBD";
            document.getElementById("stepView4").style.color = "#AF0505";
            document.getElementById("stepView4").style.background = "#FFD4E3";
            document.getElementById("stepView5").style.color = "#808080";
            document.getElementById("stepView5").style.background = "#BDBDBD";
            break;
        case "valmistautuminenSivu5":
            activityName = "valmistautuminenSivu5";
            document.getElementById("leftArrow").onclick = function () { changeContent("valmistautuminenSivu4"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("kotisivu"); }
            document.getElementById("text").value = savedText[activityName];

            document.getElementById("stepView4").style.color = "#808080";
            document.getElementById("stepView4").style.background = "#BDBDBD";
            document.getElementById("stepView5").style.color = "#AF0505";
            document.getElementById("stepView5").style.background = "#FFD4E3";
            break;
        case "synnytyksenAikanaSivu1":
            activityName = "synnytyksenAikanaSivu1";
            document.getElementById("menuLayout").style.display = "none";
            document.getElementById("imageTextLayout").style.display = "block";
            document.getElementById("image").style.display = "none";
            document.getElementById("leftArrow").style.visibility = "visible";
            document.getElementById("rightArrow").style.visibility = "visible";
            document.getElementById("leftArrow").onclick = function () { changeContent("kotisivu"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("synnytyksenAikanaSivu2"); }
            document.getElementById("text").value = savedText[activityName];
            document.getElementById("changeImage").style.visibility = "hidden";
            document.getElementById("changeText").style.visibility = "visible";

            document.getElementById("stepView").style.display = "block";
            document.getElementById("stepView4").style.display = "inline-block";
            document.getElementById("stepView5").style.display = "inline-block";
            document.getElementById("stepView6").style.display = "inline-block";

            document.getElementById("stepView1").style.color = "#AF0505";
            document.getElementById("stepView1").style.background = "#FFD4E3";
            document.getElementById("stepView2").style.color = "#808080";
            document.getElementById("stepView2").style.background = "#BDBDBD";
            document.getElementById("stepView3").style.color = "#808080";
            document.getElementById("stepView3").style.background = "#BDBDBD";
            document.getElementById("stepView4").style.color = "#808080";
            document.getElementById("stepView4").style.background = "#BDBDBD";
            document.getElementById("stepView5").style.color = "#808080";
            document.getElementById("stepView5").style.background = "#BDBDBD";
            document.getElementById("stepView6").style.color = "#808080";
            document.getElementById("stepView6").style.background = "#BDBDBD";
            break;
        case "synnytyksenAikanaSivu2":
            activityName = "synnytyksenAikanaSivu2";
            document.getElementById("image").style.display = "block";
            document.getElementById("leftArrow").onclick = function () { changeContent("synnytyksenAikanaSivu1"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("synnytyksenAikanaSivu3"); }
            document.getElementById("image").src = savedImage[usedImage[activityName]];
            document.getElementById("text").value = savedText[activityName];
            document.getElementById("changeImage").style.visibility = "visible";

            document.getElementById("stepView1").style.color = "#808080";
            document.getElementById("stepView1").style.background = "#BDBDBD";
            document.getElementById("stepView2").style.color = "#AF0505";
            document.getElementById("stepView2").style.background = "#FFD4E3";
            document.getElementById("stepView3").style.color = "#808080";
            document.getElementById("stepView3").style.background = "#BDBDBD";
            break;
        case "synnytyksenAikanaSivu3":
            activityName = "synnytyksenAikanaSivu3";
            document.getElementById("image").style.display = "block";
            document.getElementById("leftArrow").onclick = function () { changeContent("synnytyksenAikanaSivu2"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("synnytyksenAikanaSivu4"); }
            document.getElementById("image").src = savedImage[usedImage[activityName]];
            document.getElementById("text").value = savedText[activityName];
            document.getElementById("changeImage").style.visibility = "visible";

            document.getElementById("stepView2").style.color = "#808080";
            document.getElementById("stepView2").style.background = "#BDBDBD";
            document.getElementById("stepView3").style.color = "#AF0505";
            document.getElementById("stepView3").style.background = "#FFD4E3";
            document.getElementById("stepView4").style.color = "#808080";
            document.getElementById("stepView4").style.background = "#BDBDBD";
            break;
        case "synnytyksenAikanaSivu4":
            activityName = "synnytyksenAikanaSivu4";
            document.getElementById("image").style.display = "block";
            document.getElementById("leftArrow").onclick = function () { changeContent("synnytyksenAikanaSivu3"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("synnytyksenAikanaSivu5"); }
            document.getElementById("image").src = savedImage[usedImage[activityName]];
            document.getElementById("text").value = savedText[activityName];
            document.getElementById("changeImage").style.visibility = "visible";

            document.getElementById("stepView3").style.color = "#808080";
            document.getElementById("stepView3").style.background = "#BDBDBD";
            document.getElementById("stepView4").style.color = "#AF0505";
            document.getElementById("stepView4").style.background = "#FFD4E3";
            document.getElementById("stepView5").style.color = "#808080";
            document.getElementById("stepView5").style.background = "#BDBDBD";
            break;
        case "synnytyksenAikanaSivu5":
            activityName = "synnytyksenAikanaSivu5";
            document.getElementById("image").style.display = "none";
            document.getElementById("leftArrow").onclick = function () { changeContent("synnytyksenAikanaSivu4"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("synnytyksenAikanaSivu6"); }
            document.getElementById("text").value = savedText[activityName];
            document.getElementById("changeImage").style.visibility = "hidden";

            document.getElementById("stepView4").style.color = "#808080";
            document.getElementById("stepView4").style.background = "#BDBDBD";
            document.getElementById("stepView5").style.color = "#AF0505";
            document.getElementById("stepView5").style.background = "#FFD4E3";
            document.getElementById("stepView6").style.color = "#808080";
            document.getElementById("stepView6").style.background = "#BDBDBD";
            break;
        case "synnytyksenAikanaSivu6":
            activityName = "synnytyksenAikanaSivu6";
            document.getElementById("image").style.display = "block";
            document.getElementById("leftArrow").onclick = function () { changeContent("synnytyksenAikanaSivu5"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("kotisivu"); }
            document.getElementById("image").src = savedImage[usedImage[activityName]];
            document.getElementById("text").value = savedText[activityName];
            document.getElementById("changeImage").style.visibility = "visible";

            document.getElementById("stepView5").style.color = "#808080";
            document.getElementById("stepView5").style.background = "#BDBDBD";
            document.getElementById("stepView6").style.color = "#AF0505";
            document.getElementById("stepView6").style.background = "#FFD4E3";
            break;
        case "synnytyksenJalkeenSivu1":
            activityName = "synnytyksenJalkeenSivu1";
            document.getElementById("menuLayout").style.display = "none";
            document.getElementById("imageTextLayout").style.display = "block";
            document.getElementById("image").style.display = "none";
            document.getElementById("leftArrow").style.visibility = "visible";
            document.getElementById("rightArrow").style.visibility = "visible";
            document.getElementById("leftArrow").onclick = function () { changeContent("kotisivu"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("synnytyksenJalkeenSivu2"); }
            document.getElementById("text").value = savedText.synnytyksenJalkeenSivu1;
            document.getElementById("changeImage").style.visibility = "hidden";
            document.getElementById("changeText").style.visibility = "visible";

            document.getElementById("stepView").style.display = "block";
            document.getElementById("stepView4").style.display = "inline-block";
            document.getElementById("stepView5").style.display = "none";
            document.getElementById("stepView6").style.display = "none";

            document.getElementById("stepView1").style.color = "#AF0505";
            document.getElementById("stepView1").style.background = "#FFD4E3";
            document.getElementById("stepView2").style.color = "#808080";
            document.getElementById("stepView2").style.background = "#BDBDBD";
            document.getElementById("stepView3").style.color = "#808080";
            document.getElementById("stepView3").style.background = "#BDBDBD";
            document.getElementById("stepView4").style.color = "#808080";
            document.getElementById("stepView4").style.background = "#BDBDBD";
            break;
        case "synnytyksenJalkeenSivu2":
            activityName = "synnytyksenJalkeenSivu2";
            document.getElementById("leftArrow").onclick = function () { changeContent("synnytyksenJalkeenSivu1"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("synnytyksenJalkeenSivu3"); }
            document.getElementById("text").value = savedText[activityName];

            document.getElementById("stepView1").style.color = "#808080";
            document.getElementById("stepView1").style.background = "#BDBDBD";
            document.getElementById("stepView2").style.color = "#AF0505";
            document.getElementById("stepView2").style.background = "#FFD4E3";
            document.getElementById("stepView3").style.color = "#808080";
            document.getElementById("stepView3").style.background = "#BDBDBD";
            break;
        case "synnytyksenJalkeenSivu3":
            activityName = "synnytyksenJalkeenSivu3";
            document.getElementById("leftArrow").onclick = function () { changeContent("synnytyksenJalkeenSivu2"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("synnytyksenJalkeenSivu4"); }
            document.getElementById("text").value = savedText[activityName];

            document.getElementById("stepView2").style.color = "#808080";
            document.getElementById("stepView2").style.background = "#BDBDBD";
            document.getElementById("stepView3").style.color = "#AF0505";
            document.getElementById("stepView3").style.background = "#FFD4E3";
            document.getElementById("stepView4").style.color = "#808080";
            document.getElementById("stepView4").style.background = "#BDBDBD";
            break;
        case "synnytyksenJalkeenSivu4":
            activityName = "synnytyksenJalkeenSivu4";
            document.getElementById("leftArrow").onclick = function () { changeContent("synnytyksenJalkeenSivu3"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("kotisivu"); }
            document.getElementById("text").value = savedText[activityName];

            document.getElementById("stepView3").style.color = "#808080";
            document.getElementById("stepView3").style.background = "#BDBDBD";
            document.getElementById("stepView4").style.color = "#AF0505";
            document.getElementById("stepView4").style.background = "#FFD4E3";
            break;
        case "peratilaSivu1":
            activityName = "peratilaSivu1";
            document.getElementById("menuLayout").style.display = "none";
            document.getElementById("imageTextLayout").style.display = "block";
            document.getElementById("image").style.display = "block";
            document.getElementById("leftArrow").style.visibility = "visible";
            document.getElementById("rightArrow").style.visibility = "visible";
            document.getElementById("leftArrow").onclick = function () { changeContent("erikoistilanteetSivu"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("peratilaSivu2"); }
            document.getElementById("image").src = savedImage[usedImage[activityName]];
            document.getElementById("text").value = savedText[activityName];
            document.getElementById("changeImage").style.visibility = "visible";
            document.getElementById("changeText").style.visibility = "visible";

            document.getElementById("stepView").style.display = "block";
            document.getElementById("stepView4").style.display = "inline-block";
            document.getElementById("stepView5").style.display = "inline-block";
            document.getElementById("stepView6").style.display = "none";

            document.getElementById("stepView1").style.color = "#AF0505";
            document.getElementById("stepView1").style.background = "#FFD4E3";
            document.getElementById("stepView2").style.color = "#808080";
            document.getElementById("stepView2").style.background = "#BDBDBD";
            document.getElementById("stepView3").style.color = "#808080";
            document.getElementById("stepView3").style.background = "#BDBDBD";
            document.getElementById("stepView4").style.color = "#808080";
            document.getElementById("stepView4").style.background = "#BDBDBD";
            document.getElementById("stepView5").style.color = "#808080";
            document.getElementById("stepView5").style.background = "#BDBDBD";
            break;
        case "peratilaSivu2":
            activityName = "peratilaSivu2";
            document.getElementById("leftArrow").onclick = function () { changeContent("peratilaSivu1"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("peratilaSivu3"); }
            document.getElementById("image").src = savedImage[usedImage[activityName]];
            document.getElementById("text").value = savedText[activityName];

            document.getElementById("stepView1").style.color = "#808080";
            document.getElementById("stepView1").style.background = "#BDBDBD";
            document.getElementById("stepView2").style.color = "#AF0505";
            document.getElementById("stepView2").style.background = "#FFD4E3";
            document.getElementById("stepView3").style.color = "#808080";
            document.getElementById("stepView3").style.background = "#BDBDBD";
            break;
        case "peratilaSivu3":
            activityName = "peratilaSivu3";
            document.getElementById("leftArrow").onclick = function () { changeContent("peratilaSivu2"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("peratilaSivu4"); }
            document.getElementById("image").src = savedImage[usedImage[activityName]];
            document.getElementById("text").value = savedText[activityName];

            document.getElementById("stepView2").style.color = "#808080";
            document.getElementById("stepView2").style.background = "#BDBDBD";
            document.getElementById("stepView3").style.color = "#AF0505";
            document.getElementById("stepView3").style.background = "#FFD4E3";
            document.getElementById("stepView4").style.color = "#808080";
            document.getElementById("stepView4").style.background = "#BDBDBD";
            break;
        case "peratilaSivu4":
            activityName = "peratilaSivu4";
            document.getElementById("leftArrow").onclick = function () { changeContent("peratilaSivu3"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("peratilaSivu5"); }
            document.getElementById("image").src = savedImage[usedImage[activityName]];
            document.getElementById("text").value = savedText[activityName];

            document.getElementById("stepView3").style.color = "#808080";
            document.getElementById("stepView3").style.background = "#BDBDBD";
            document.getElementById("stepView4").style.color = "#AF0505";
            document.getElementById("stepView4").style.background = "#FFD4E3";
            document.getElementById("stepView5").style.color = "#808080";
            document.getElementById("stepView5").style.background = "#BDBDBD";
            break;
        case "peratilaSivu5":
            activityName = "peratilaSivu5";
            document.getElementById("leftArrow").onclick = function () { changeContent("peratilaSivu4"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("erikoistilanteetSivu"); }
            document.getElementById("image").src = savedImage[usedImage[activityName]];
            document.getElementById("text").value = savedText[activityName];

            document.getElementById("stepView4").style.color = "#808080";
            document.getElementById("stepView4").style.background = "#BDBDBD";
            document.getElementById("stepView5").style.color = "#AF0505";
            document.getElementById("stepView5").style.background = "#FFD4E3";
            break;
        case "hartiadystokiaSivu1":
            activityName = "hartiadystokiaSivu1";
            document.getElementById("menuLayout").style.display = "none";
            document.getElementById("imageTextLayout").style.display = "block";
            document.getElementById("image").style.display = "block";
            document.getElementById("leftArrow").style.visibility = "visible";
            document.getElementById("rightArrow").style.visibility = "visible";
            document.getElementById("leftArrow").onclick = function () { changeContent("erikoistilanteetSivu"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("hartiadystokiaSivu2"); }
            document.getElementById("image").src = savedImage[usedImage[activityName]];
            document.getElementById("text").value = savedText[activityName];
            document.getElementById("changeImage").style.visibility = "visible";
            document.getElementById("changeText").style.visibility = "visible";

            document.getElementById("stepView").style.display = "block";
            document.getElementById("stepView4").style.display = "none";
            document.getElementById("stepView5").style.display = "none";
            document.getElementById("stepView6").style.display = "none";

            document.getElementById("stepView1").style.color = "#AF0505";
            document.getElementById("stepView1").style.background = "#FFD4E3";
            document.getElementById("stepView2").style.color = "#808080";
            document.getElementById("stepView2").style.background = "#BDBDBD";
            document.getElementById("stepView3").style.color = "#808080";
            document.getElementById("stepView3").style.background = "#BDBDBD";
            break;
        case "hartiadystokiaSivu2":
            activityName = "hartiadystokiaSivu2";
            document.getElementById("leftArrow").onclick = function () { changeContent("hartiadystokiaSivu1"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("hartiadystokiaSivu3"); }
            document.getElementById("image").src = savedImage[usedImage[activityName]];
            document.getElementById("text").value = savedText[activityName];

            document.getElementById("stepView1").style.color = "#808080";
            document.getElementById("stepView1").style.background = "#BDBDBD";
            document.getElementById("stepView2").style.color = "#AF0505";
            document.getElementById("stepView2").style.background = "#FFD4E3";
            document.getElementById("stepView3").style.color = "#808080";
            document.getElementById("stepView3").style.background = "#BDBDBD";
            break;
        case "hartiadystokiaSivu3":
            activityName = "hartiadystokiaSivu3";
            document.getElementById("leftArrow").onclick = function () { changeContent("hartiadystokiaSivu2"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("erikoistilanteetSivu"); }
            document.getElementById("image").src = savedImage[usedImage[activityName]];
            document.getElementById("text").value = savedText[activityName];

            document.getElementById("stepView2").style.color = "#808080";
            document.getElementById("stepView2").style.background = "#BDBDBD";
            document.getElementById("stepView3").style.color = "#AF0505";
            document.getElementById("stepView3").style.background = "#FFD4E3";
            break;
        case "napanuoraSivu1":
            activityName = "napanuoraSivu1";
            document.getElementById("menuLayout").style.display = "none";
            document.getElementById("imageTextLayout").style.display = "block";
            document.getElementById("image").style.display = "block";
            document.getElementById("leftArrow").style.visibility = "visible";
            document.getElementById("rightArrow").style.visibility = "visible";
            document.getElementById("leftArrow").onclick = function () { changeContent("erikoistilanteetSivu"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("napanuoraSivu2"); }
            document.getElementById("image").src = savedImage[usedImage[activityName]];
            document.getElementById("text").value = savedText[activityName];
            document.getElementById("changeImage").style.visibility = "visible";
            document.getElementById("changeText").style.visibility = "visible";

            document.getElementById("stepView").style.display = "block";
            document.getElementById("stepView4").style.display = "none";
            document.getElementById("stepView5").style.display = "none";
            document.getElementById("stepView6").style.display = "none";

            document.getElementById("stepView1").style.color = "#AF0505";
            document.getElementById("stepView1").style.background = "#FFD4E3";
            document.getElementById("stepView2").style.color = "#808080";
            document.getElementById("stepView2").style.background = "#BDBDBD";
            document.getElementById("stepView3").style.color = "#808080";
            document.getElementById("stepView3").style.background = "#BDBDBD";
            break;
        case "napanuoraSivu2":
            activityName = "napanuoraSivu2";
            document.getElementById("leftArrow").onclick = function () { changeContent("napanuoraSivu1"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("napanuoraSivu3"); }
            document.getElementById("image").src = savedImage[usedImage[activityName]];
            document.getElementById("text").value = savedText[activityName];

            document.getElementById("stepView1").style.color = "#808080";
            document.getElementById("stepView1").style.background = "#BDBDBD";
            document.getElementById("stepView2").style.color = "#AF0505";
            document.getElementById("stepView2").style.background = "#FFD4E3";
            document.getElementById("stepView3").style.color = "#808080";
            document.getElementById("stepView3").style.background = "#BDBDBD";
            break;
        case "napanuoraSivu3":
            activityName = "napanuoraSivu3";
            document.getElementById("leftArrow").onclick = function () { changeContent("napanuoraSivu2"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("erikoistilanteetSivu"); }
            document.getElementById("image").src = savedImage[usedImage[activityName]];
            document.getElementById("text").value = savedText[activityName];

            document.getElementById("stepView2").style.color = "#808080";
            document.getElementById("stepView2").style.background = "#BDBDBD";
            document.getElementById("stepView3").style.color = "#AF0505";
            document.getElementById("stepView3").style.background = "#FFD4E3";
            break;
        case "laakeohjeetSivu":
            activityName = "laakeohjeetSivu";
            document.getElementById("menuLayout").style.display = "none";
            document.getElementById("imageTextLayout").style.display = "block";
            document.getElementById("image").style.display = "none";
            document.getElementById("leftArrow").style.visibility = "visible";
            document.getElementById("leftArrow").onclick = function () { changeContent("kotisivu"); }
            document.getElementById("text").value = savedText[activityName];
            document.getElementById("changeImage").style.visibility = "hidden";
            document.getElementById("changeText").style.visibility = "visible";
            break;
    }
}