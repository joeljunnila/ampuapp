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
}

function changeContent(page) {
    switch (page) {
        case "kotisivu":
            document.getElementById("menuLayout").style.display = "block";
            document.getElementById("imageTextLayout").style.display = "none";
            document.getElementById("button4").style.visibility = "visible";
            document.getElementById("leftArrow").style.display = "none";
            document.getElementById("rightArrow").style.display = "none";

            document.getElementById("button1").textContent = "Valmistautuminen";
            document.getElementById("button2").textContent = "Synnytysvaiheet";
            document.getElementById("button3").textContent = "Synnytyksen jälkeen";
            document.getElementById("button4").textContent = "Erikoistilanteet";

            document.getElementById("button1").onclick = function () { changeContent("valmistautuminenSivu1"); }
            document.getElementById("button2").onclick = function () { changeContent("synnytysvaiheetSivu1"); }
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
            document.getElementById("leftArrow").style.display = "block";
            document.getElementById("rightArrow").style.display = "block";
            document.getElementById("leftArrow").onclick = function () { changeContent("kotisivu"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("valmistautuminenSivu2"); }

            var storageRef = firebase.storage().ref("kuvat/" + "ohje.jpg");
            var storageRef2 = firebase.storage().ref("tekstit/" + "valmistautuminenSivu1.txt");

            storageRef.getDownloadURL()
                .then((url) => {
                    //document.getElementById("image").src = url;

                    url = "https://cors-anywhere.herokuapp.com/" + url;

                    var request = new XMLHttpRequest();
                    request.open('GET', url, true);
                    request.send(null);
                    request.onreadystatechange = function () {
                        if (request.readyState === 4 && request.status === 200) {
                            var type = request.getResponseHeader('Content-Type');
                            if (type.indexOf("text") !== 1) {
                                document.getElementById("text").textContent = request.responseText;
                            }
                        }
                    }
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
            break;
        case "valmistautuminenSivu2":
            document.getElementById("leftArrow").onclick = function () { changeContent("valmistautuminenSivu1"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("valmistautuminenSivu3"); }
            document.getElementById("text").textContent = "valmistautuminenSivu2";
            break;
        case "valmistautuminenSivu3":
            document.getElementById("leftArrow").onclick = function () { changeContent("valmistautuminenSivu2"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("valmistautuminenSivu4"); }
            document.getElementById("text").textContent = "valmistautuminenSivu3";
            break;
        case "valmistautuminenSivu4":
            document.getElementById("leftArrow").onclick = function () { changeContent("valmistautuminenSivu3"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("valmistautuminenSivu5"); }
            document.getElementById("text").textContent = "valmistautuminenSivu4";
            break;
        case "valmistautuminenSivu5":
            document.getElementById("leftArrow").onclick = function () { changeContent("valmistautuminenSivu4"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("kotisivu"); }
            document.getElementById("text").textContent = "valmistautuminenSivu5";
            break;
        case "synnytysvaiheetSivu1":
            document.getElementById("menuLayout").style.display = "none";
            document.getElementById("imageTextLayout").style.display = "block";
            document.getElementById("image").style.display = "block";
            document.getElementById("leftArrow").style.display = "block";
            document.getElementById("rightArrow").style.display = "block";
            document.getElementById("leftArrow").onclick = function () { changeContent("kotisivu"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("synnytysvaiheetSivu2"); }
            document.getElementById("text").textContent = "synnytysvaiheetSivu1";
            break;
        case "synnytysvaiheetSivu2":
            document.getElementById("leftArrow").onclick = function () { changeContent("synnytysvaiheetSivu1"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("synnytysvaiheetSivu3"); }
            document.getElementById("text").textContent = "synnytysvaiheetSivu2";
            break;
        case "synnytysvaiheetSivu3":
            document.getElementById("leftArrow").onclick = function () { changeContent("synnytysvaiheetSivu2"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("synnytysvaiheetSivu4"); }
            document.getElementById("text").textContent = "synnytysvaiheetSivu3";
            break;
        case "synnytysvaiheetSivu4":
            document.getElementById("leftArrow").onclick = function () { changeContent("synnytysvaiheetSivu3"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("synnytysvaiheetSivu5"); }
            document.getElementById("text").textContent = "synnytysvaiheetSivu4";
            break;
        case "synnytysvaiheetSivu5":
            document.getElementById("leftArrow").onclick = function () { changeContent("synnytysvaiheetSivu4"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("synnytysvaiheetSivu6"); }
            document.getElementById("text").textContent = "synnytysvaiheetSivu5";
            break;
        case "synnytysvaiheetSivu6":
            document.getElementById("leftArrow").onclick = function () { changeContent("synnytysvaiheetSivu5"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("kotisivu"); }
            document.getElementById("text").textContent = "synnytysvaiheetSivu6";
            break;
        case "synnytyksenJalkeenSivu1":
            document.getElementById("menuLayout").style.display = "none";
            document.getElementById("imageTextLayout").style.display = "block";
            document.getElementById("image").style.display = "none";
            document.getElementById("leftArrow").style.display = "block";
            document.getElementById("rightArrow").style.display = "block";
            document.getElementById("leftArrow").onclick = function () { changeContent("kotisivu"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("synnytyksenJalkeenSivu2"); }
            document.getElementById("text").textContent = "synnytyksenJalkeenSivu1";
            break;
        case "synnytyksenJalkeenSivu2":
            document.getElementById("leftArrow").onclick = function () { changeContent("synnytyksenJalkeenSivu1"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("synnytyksenJalkeenSivu3"); }
            document.getElementById("text").textContent = "synnytyksenJalkeenSivu2";
            break;
        case "synnytyksenJalkeenSivu3":
            document.getElementById("leftArrow").onclick = function () { changeContent("synnytyksenJalkeenSivu2"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("synnytyksenJalkeenSivu4"); }
            document.getElementById("text").textContent = "synnytyksenJalkeenSivu3";
            break;
        case "synnytyksenJalkeenSivu4":
            document.getElementById("leftArrow").onclick = function () { changeContent("synnytyksenJalkeenSivu3"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("kotisivu"); }
            document.getElementById("text").textContent = "synnytyksenJalkeenSivu4";
            break;
        case "peratilaSivu1":
            document.getElementById("menuLayout").style.display = "none";
            document.getElementById("imageTextLayout").style.display = "block";
            document.getElementById("image").style.display = "block";
            document.getElementById("leftArrow").style.display = "block";
            document.getElementById("rightArrow").style.display = "block";
            document.getElementById("leftArrow").onclick = function () { changeContent("erikoistilanteetSivu"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("peratilaSivu2"); }
            document.getElementById("text").textContent = "peratilaSivu1";
            break;
        case "peratilaSivu2":
            document.getElementById("leftArrow").onclick = function () { changeContent("peratilaSivu1"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("peratilaSivu3"); }
            document.getElementById("text").textContent = "peratilaSivu2";
            break;
        case "peratilaSivu3":
            document.getElementById("leftArrow").onclick = function () { changeContent("peratilaSivu2"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("peratilaSivu4"); }
            document.getElementById("text").textContent = "peratilaSivu3";
            break;
        case "peratilaSivu4":
            document.getElementById("leftArrow").onclick = function () { changeContent("peratilaSivu3"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("peratilaSivu5"); }
            document.getElementById("text").textContent = "peratilaSivu4";
            break;
        case "peratilaSivu5":
            document.getElementById("leftArrow").onclick = function () { changeContent("peratilaSivu4"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("kotisivu"); }
            document.getElementById("text").textContent = "peratilaSivu5";
            break;
        case "hartiadystokiaSivu1":
            document.getElementById("menuLayout").style.display = "none";
            document.getElementById("imageTextLayout").style.display = "block";
            document.getElementById("image").style.display = "block";
            document.getElementById("leftArrow").style.display = "block";
            document.getElementById("rightArrow").style.display = "block";
            document.getElementById("leftArrow").onclick = function () { changeContent("erikoistilanteetSivu"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("hartiadystokiaSivu2"); }
            document.getElementById("text").textContent = "hartiadystokiaSivu1";
            break;
        case "hartiadystokiaSivu2":
            document.getElementById("leftArrow").onclick = function () { changeContent("hartiadystokiaSivu1"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("hartiadystokiaSivu3"); }
            document.getElementById("text").textContent = "hartiadystokiaSivu2";
            break;
        case "hartiadystokiaSivu3":
            document.getElementById("leftArrow").onclick = function () { changeContent("hartiadystokiaSivu2"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("kotisivu"); }
            document.getElementById("text").textContent = "hartiadystokiaSivu3";
            break;
        case "napanuoraSivu1":
            document.getElementById("menuLayout").style.display = "none";
            document.getElementById("imageTextLayout").style.display = "block";
            document.getElementById("image").style.display = "block";
            document.getElementById("leftArrow").style.display = "block";
            document.getElementById("rightArrow").style.display = "block";
            document.getElementById("leftArrow").onclick = function () { changeContent("erikoistilanteetSivu"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("napanuoraSivu2"); }
            document.getElementById("text").textContent = "napanuoraSivu1";
            break;
        case "napanuoraSivu2":
            document.getElementById("leftArrow").onclick = function () { changeContent("napanuoraSivu1"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("napanuoraSivu3"); }
            document.getElementById("text").textContent = "napanuoraSivu2";
            break;
        case "napanuoraSivu3":
            document.getElementById("leftArrow").onclick = function () { changeContent("napanuoraSivu2"); }
            document.getElementById("rightArrow").onclick = function () { changeContent("kotisivu"); }
            document.getElementById("text").textContent = "napanuoraSivu3";
            break;
        case "laakeohjeetSivu":
            document.getElementById("menuLayout").style.display = "none";
            document.getElementById("imageTextLayout").style.display = "block";
            document.getElementById("image").style.display = "none";
            document.getElementById("leftArrow").style.display = "block";
            document.getElementById("leftArrow").onclick = function () { changeContent("kotisivu"); }
            document.getElementById("text").textContent = "laakeohjeetSivu";
            break;
    }
}