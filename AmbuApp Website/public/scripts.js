function changeContent(page) {
    if(page == "kotisivu") {
        document.getElementById("menuLayout").style.display = "block";
        document.getElementById("imageTextLayout").style.display = "none";
    } else {
        document.getElementById("menuLayout").style.display = "none";
        document.getElementById("imageTextLayout").style.display = "block";
    }
}