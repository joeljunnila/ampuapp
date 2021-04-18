let imageRef = [
    "kuvat/ohje.jpg",
    "kuvat/ohje3.jpg",
    "kuvat/ohje4.jpg",
    "kuvat/ohje5.jpg",
    "kuvat/ohje7.jpg"
];

let textRef = [
    "tekstit/valmistautuminenSivu1.txt",
    "tekstit/valmistautuminenSivu2.txt",
    "tekstit/valmistautuminenSivu3.txt",
    "tekstit/valmistautuminenSivu4.txt",
    "tekstit/valmistautuminenSivu5.txt",
    "tekstit/synnytyksenAikanaSivu1.txt",
    "tekstit/synnytyksenAikanaSivu2.txt",
    "tekstit/synnytyksenAikanaSivu3.txt",
    "tekstit/synnytyksenAikanaSivu4.txt",
    "tekstit/synnytyksenAikanaSivu5.txt",
    "tekstit/synnytyksenAikanaSivu6.txt",
    "tekstit/synnytyksenJalkeenSivu1.txt",
    "tekstit/synnytyksenJalkeenSivu2.txt",
    "tekstit/synnytyksenJalkeenSivu3.txt",
    "tekstit/synnytyksenJalkeenSivu4.txt",
    "tekstit/peratilaSivu1.txt",
    "tekstit/peratilaSivu2.txt",
    "tekstit/peratilaSivu3.txt",
    "tekstit/peratilaSivu4.txt",
    "tekstit/peratilaSivu5.txt",
    "tekstit/hartiadystokiaSivu1.txt",
    "tekstit/hartiadystokiaSivu2.txt",
    "tekstit/hartiadystokiaSivu3.txt",
    "tekstit/napanuoraSivu1.txt",
    "tekstit/napanuoraSivu2.txt",
    "tekstit/napanuoraSivu3.txt",
    "tekstit/laakeohjeetSivu.txt",
];

var savedImage = {
    ohje: "ohje",
    ohje3: "ohje3",
    ohje4: "ohje4",
    ohje5: "ohje5",
    ohje7: "ohje7"
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

window.onload = function leadFirst() {
    changeContent("kotisivu");

    var firebaseConfig = {
        apiKey: "AIzaSyDUbaZX8mHLAKMiX5Kd1i6xuViu0kDEGEg",
        authDomain: "ambuapp-2691e.firebaseapp.com",
        projectId: "ambuapp-2691e",
        storageBucket: "ambuapp-2691e.appspot.com",
        messagingSenderId: "812625720469",
        appId: "1:812625720469:web:851a28a3eefbac8374d79c"
    };
    firebase.initializeApp(firebaseConfig);

    for (var key in savedImage) getImage(key);
    for (var key in savedText) getText(key);
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

function changeContent(page) {
    switch (page) {
        case "kotisivu":
            document.getElementById("menuLayout").style.display = "block";
            document.getElementById("imageTextLayout").style.display = "none";
            document.getElementById("button4").style.visibility = "visible";
            document.getElementById("button5").style.visibility = "visible";
            document.getElementById("leftArrow").style.display = "none";
            document.getElementById("rightArrow").style.display = "none";

            document.getElementById("button1").textContent = "Valmistautuminen";
            document.getElementById("button2").textContent = "Synnytysvaiheet";
            document.getElementById("button3").textContent = "Synnytyksen jälkeen";
            document.getElementById("button4").textContent = "Erikoistilanteet";
            document.getElementById("button5").textContent = "Lääkeohjeet";

            document.getElementById("button1").onclick = function () { changeContent("valmistautuminenSivu1"); }
            document.getElementById("button2").onclick = function () { changeContent("synnytyksenAikanaSivu1"); }
            document.getElementById("button3").onclick = function () { changeContent("synnytyksenJalkeenSivu1"); }
            document.getElementById("button4").onclick = function () { changeContent("erikoistilanteetSivu"); }
            document.getElementById("button5").onclick = function () { changeContent("laakeohjeetSivu"); }
            break;
        case "erikoistilanteetSivu":
            document.getElementById("menuLayout").style.display = "block";
            document.getElementById("imageTextLayout").style.display = "none";
            document.getElementById("button4").style.visibility = "hidden";
            document.getElementById("button5").style.visibility = "hidden";
            document.getElementById("leftArrow").style.display = "block";
            document.getElementById("rightArrow").style.display = "hidden";

            document.getElementById("button1").textContent = "Perätila";
            document.getElementById("button2").textContent = "Hartiadystokia";
            document.getElementById("button3").textContent = "Napanuoran esiinluiskahdus";

            document.getElementById("button1").onclick = function () { changeContent("peratilaSivu1"); }
            document.getElementById("button2").onclick = function () { changeContent("hartiadystokiaSivu1"); }
            document.getElementById("button3").onclick = function () { changeContent("napanuoraSivu1"); }

            document.getElementById("leftArrow").onclick = function () { changeContent("kotisivu"); }
            break;
        case "valmistautuminenSivu1":
            document.getElementById("menuLayout").style.display = "none";
            document.getElementById("imageTextLayout").style.display = "block";
            document.getElementById("image").style.display = "none";
            document.getElementById("leftArrow").style.display = "block";
            document.getElementById("rightArrow").style.display = "block";
            document.getElementById("leftArrow").onclick = function () { changeContent("kotisivu"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("valmistautuminenSivu2"); }
            document.getElementById("text").textContent = savedText.valmistautuminenSivu1;
            break;
        case "valmistautuminenSivu2":
            document.getElementById("leftArrow").onclick = function () { changeContent("valmistautuminenSivu1"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("valmistautuminenSivu3"); }
            document.getElementById("text").textContent = savedText.valmistautuminenSivu2;
            break;
        case "valmistautuminenSivu3":
            document.getElementById("leftArrow").onclick = function () { changeContent("valmistautuminenSivu2"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("valmistautuminenSivu4"); }
            document.getElementById("text").textContent = savedText.valmistautuminenSivu3;
            break;
        case "valmistautuminenSivu4":
            document.getElementById("leftArrow").onclick = function () { changeContent("valmistautuminenSivu3"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("valmistautuminenSivu5"); }
            document.getElementById("text").textContent = savedText.valmistautuminenSivu4;
            break;
        case "valmistautuminenSivu5":
            document.getElementById("leftArrow").onclick = function () { changeContent("valmistautuminenSivu4"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("kotisivu"); }
            document.getElementById("text").textContent = savedText.valmistautuminenSivu5;
            break;
        case "synnytyksenAikanaSivu1":
            document.getElementById("menuLayout").style.display = "none";
            document.getElementById("imageTextLayout").style.display = "block";
            document.getElementById("image").style.display = "block";
            document.getElementById("leftArrow").style.display = "block";
            document.getElementById("rightArrow").style.display = "block";
            document.getElementById("leftArrow").onclick = function () { changeContent("kotisivu"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("synnytyksenAikanaSivu2"); }
            document.getElementById("image").src = savedImage.ohje;
            document.getElementById("text").textContent = savedText.synnytyksenAikanaSivu1;
            break;
        case "synnytyksenAikanaSivu2":
            document.getElementById("leftArrow").onclick = function () { changeContent("synnytyksenAikanaSivu1"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("synnytyksenAikanaSivu3"); }
            document.getElementById("image").src = savedImage.ohje3;
            document.getElementById("text").textContent = savedText.synnytyksenAikanaSivu2;
            break;
        case "synnytyksenAikanaSivu3":
            document.getElementById("leftArrow").onclick = function () { changeContent("synnytyksenAikanaSivu2"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("synnytyksenAikanaSivu4"); }
            document.getElementById("image").src = savedImage.ohje4;
            document.getElementById("text").textContent = savedText.synnytyksenAikanaSivu3;
            break;
        case "synnytyksenAikanaSivu4":
            document.getElementById("leftArrow").onclick = function () { changeContent("synnytyksenAikanaSivu3"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("synnytyksenAikanaSivu5"); }
            document.getElementById("image").src = savedImage.ohje5;
            document.getElementById("text").textContent = savedText.synnytyksenAikanaSivu4;
            break;
        case "synnytyksenAikanaSivu5":
            document.getElementById("leftArrow").onclick = function () { changeContent("synnytyksenAikanaSivu4"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("synnytyksenAikanaSivu6"); }
            document.getElementById("image").src = savedImage.ohje7;
            document.getElementById("text").textContent = savedText.synnytyksenAikanaSivu5;
            break;
        case "synnytyksenAikanaSivu6":
            document.getElementById("leftArrow").onclick = function () { changeContent("synnytyksenAikanaSivu5"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("kotisivu"); }
            document.getElementById("image").src = savedImage.ohje;
            document.getElementById("text").textContent = savedText.synnytyksenAikanaSivu6;
            break;
        case "synnytyksenJalkeenSivu1":
            document.getElementById("menuLayout").style.display = "none";
            document.getElementById("imageTextLayout").style.display = "block";
            document.getElementById("image").style.display = "none";
            document.getElementById("leftArrow").style.display = "block";
            document.getElementById("rightArrow").style.display = "block";
            document.getElementById("leftArrow").onclick = function () { changeContent("kotisivu"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("synnytyksenJalkeenSivu2"); }
            document.getElementById("text").textContent = savedText.synnytyksenJalkeenSivu1;
            break;
        case "synnytyksenJalkeenSivu2":
            document.getElementById("leftArrow").onclick = function () { changeContent("synnytyksenJalkeenSivu1"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("synnytyksenJalkeenSivu3"); }
            document.getElementById("text").textContent = savedText.synnytyksenJalkeenSivu2;
            break;
        case "synnytyksenJalkeenSivu3":
            document.getElementById("leftArrow").onclick = function () { changeContent("synnytyksenJalkeenSivu2"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("synnytyksenJalkeenSivu4"); }
            document.getElementById("text").textContent = savedText.synnytyksenJalkeenSivu3;
            break;
        case "synnytyksenJalkeenSivu4":
            document.getElementById("leftArrow").onclick = function () { changeContent("synnytyksenJalkeenSivu3"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("kotisivu"); }
            document.getElementById("text").textContent = savedText.synnytyksenJalkeenSivu4;
            break;
        case "peratilaSivu1":
            document.getElementById("menuLayout").style.display = "none";
            document.getElementById("imageTextLayout").style.display = "block";
            document.getElementById("image").style.display = "block";
            document.getElementById("leftArrow").style.display = "block";
            document.getElementById("rightArrow").style.display = "block";
            document.getElementById("leftArrow").onclick = function () { changeContent("erikoistilanteetSivu"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("peratilaSivu2"); }
            document.getElementById("image").src = savedImage.ohje;
            document.getElementById("text").textContent = savedText.peratilaSivu1;
            break;
        case "peratilaSivu2":
            document.getElementById("leftArrow").onclick = function () { changeContent("peratilaSivu1"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("peratilaSivu3"); }
            document.getElementById("image").src = savedImage.ohje;
            document.getElementById("text").textContent = savedText.peratilaSivu2;
            break;
        case "peratilaSivu3":
            document.getElementById("leftArrow").onclick = function () { changeContent("peratilaSivu2"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("peratilaSivu4"); }
            document.getElementById("image").src = savedImage.ohje;
            document.getElementById("text").textContent = savedText.peratilaSivu3;
            break;
        case "peratilaSivu4":
            document.getElementById("leftArrow").onclick = function () { changeContent("peratilaSivu3"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("peratilaSivu5"); }
            document.getElementById("image").src = savedImage.ohje;
            document.getElementById("text").textContent = savedText.peratilaSivu4;
            break;
        case "peratilaSivu5":
            document.getElementById("leftArrow").onclick = function () { changeContent("peratilaSivu4"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("erikoistilanteetSivu"); }
            document.getElementById("image").src = savedImage.ohje;
            document.getElementById("text").textContent = savedText.peratilaSivu5;
            break;
        case "hartiadystokiaSivu1":
            document.getElementById("menuLayout").style.display = "none";
            document.getElementById("imageTextLayout").style.display = "block";
            document.getElementById("image").style.display = "block";
            document.getElementById("leftArrow").style.display = "block";
            document.getElementById("rightArrow").style.display = "block";
            document.getElementById("leftArrow").onclick = function () { changeContent("erikoistilanteetSivu"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("hartiadystokiaSivu2"); }
            document.getElementById("image").src = savedImage.ohje;
            document.getElementById("text").textContent = savedText.hartiadystokiaSivu1;
            break;
        case "hartiadystokiaSivu2":
            document.getElementById("leftArrow").onclick = function () { changeContent("hartiadystokiaSivu1"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("hartiadystokiaSivu3"); }
            document.getElementById("image").src = savedImage.ohje;
            document.getElementById("text").textContent = savedText.hartiadystokiaSivu2;
            break;
        case "hartiadystokiaSivu3":
            document.getElementById("leftArrow").onclick = function () { changeContent("hartiadystokiaSivu2"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("erikoistilanteetSivu"); }
            document.getElementById("image").src = savedImage.ohje;
            document.getElementById("text").textContent = savedText.hartiadystokiaSivu3;
            break;
        case "napanuoraSivu1":
            document.getElementById("menuLayout").style.display = "none";
            document.getElementById("imageTextLayout").style.display = "block";
            document.getElementById("image").style.display = "block";
            document.getElementById("leftArrow").style.display = "block";
            document.getElementById("rightArrow").style.display = "block";
            document.getElementById("leftArrow").onclick = function () { changeContent("erikoistilanteetSivu"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("napanuoraSivu2"); }
            document.getElementById("image").src = savedImage.ohje;
            document.getElementById("text").textContent = savedText.napanuoraSivu1;
            break;
        case "napanuoraSivu2":
            document.getElementById("leftArrow").onclick = function () { changeContent("napanuoraSivu1"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("napanuoraSivu3"); }
            document.getElementById("image").src = savedImage.ohje;
            document.getElementById("text").textContent = savedText.napanuoraSivu2;
            break;
        case "napanuoraSivu3":
            document.getElementById("leftArrow").onclick = function () { changeContent("napanuoraSivu2"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("erikoistilanteetSivu"); }
            document.getElementById("image").src = savedImage.ohje;
            document.getElementById("text").textContent = savedText.napanuoraSivu3;
            break;
        case "laakeohjeetSivu":
            document.getElementById("menuLayout").style.display = "none";
            document.getElementById("imageTextLayout").style.display = "block";
            document.getElementById("image").style.display = "none";
            document.getElementById("leftArrow").style.display = "block";
            document.getElementById("leftArrow").onclick = function () { changeContent("kotisivu"); }
            document.getElementById("text").textContent = savedText.laakeohjeetSivu;
            break;
    }
}