function changeContent(page) {
    switch(page) {
        case "kotisivu":
            document.getElementById("menuLayout").style.display = "block";
            document.getElementById("imageTextLayout").style.display = "none";
            document.getElementById("button4").style.visibility = "visible"; // kussee

            document.getElementById("button1").onclick = changeContent("erikoistilanteetSivu"); //kussee

            document.getElementById("button1").textContent = "Valmistautuminen";
            document.getElementById("button2").textContent = "Synnytysvaiheet";
            document.getElementById("button3").textContent = "Synnytyksen jälkeen";
            document.getElementById("button4").textContent = "Erikoistilanteet";
            break;
        case "valmistautuminenSivu1":
            document.getElementById("menuLayout").style.display = "none";
            document.getElementById("imageTextLayout").style.display = "block";
            break;
        case "synnytysvaiheetSivu1":
            document.getElementById("menuLayout").style.display = "none";
            document.getElementById("imageTextLayout").style.display = "block";
            break;
        case "synnytyksenJalkeenSivu1":
            document.getElementById("menuLayout").style.display = "none";
            document.getElementById("imageTextLayout").style.display = "block";
            break;
        case "erikoistilanteetSivu":
            document.getElementById("menuLayout").style.display = "block";
            document.getElementById("imageTextLayout").style.display = "none";
            document.getElementById("button4").style.visibility = "hidden";

            document.getElementById("button1").textContent = "Perätila";
            document.getElementById("button2").textContent = "Hartiadystokia";
            document.getElementById("button3").textContent = "Napanuoran esiinluiskahdus";
            break;
    }
}