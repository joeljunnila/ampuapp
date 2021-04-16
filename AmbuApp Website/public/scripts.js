window.onload = function loadFirst() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("content").innerHTML = this.responseText;
        }
    };
    xhttp.open("GET", "imageTextLayout.html", true);
    xhttp.send();
}

function changeContent(page) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("content").innerHTML = this.responseText;
        }
    };

    switch (page) {
        case "kotisivu":
            xhttp.open("GET", "menuLayout.html", true);
            break;
        case "imageTextLayout":
            xhttp.open("GET", "imageTextLayout.html", true);
            break;
    }

    xhttp.send();
}