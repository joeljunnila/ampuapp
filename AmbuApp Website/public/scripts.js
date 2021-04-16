window.onload = function leadFirst() {
    changeContent("kotisivu");
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
            break;
        case "erikoistilanteetSivu":
            document.getElementById("menuLayout").style.display = "block";
            document.getElementById("imageTextLayout").style.display = "none";
            document.getElementById("button4").style.visibility = "hidden";
            document.getElementById("leftArrow").style.display = "block";

            document.getElementById("button1").textContent = "Perätila";
            document.getElementById("button2").textContent = "Hartiadystokia";
            document.getElementById("button3").textContent = "Napanuoran esiinluiskahdus";

            document.getElementById("button1").onclick = function () { changeContent("perätilaSivu1"); }
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
            break;
        case "synnytysvaiheetSivu1":
            document.getElementById("menuLayout").style.display = "none";
            document.getElementById("imageTextLayout").style.display = "block";
            document.getElementById("leftArrow").style.display = "block";
            document.getElementById("rightArrow").style.display = "block";
            document.getElementById("leftArrow").onclick = function () { changeContent("kotisivu"); }
            break;
        case "synnytyksenJalkeenSivu1":
            document.getElementById("menuLayout").style.display = "none";
            document.getElementById("imageTextLayout").style.display = "block";
            document.getElementById("leftArrow").style.display = "block";
            document.getElementById("rightArrow").style.display = "block";
            document.getElementById("leftArrow").onclick = function () { changeContent("kotisivu"); }
            break;
    }
}