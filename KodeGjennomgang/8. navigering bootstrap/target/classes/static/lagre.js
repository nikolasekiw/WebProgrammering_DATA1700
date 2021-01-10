function regKunde() {
    const kunde = {
        navn : $("#navn").val(),
        adresse : $("#adresse").val()
    };
    $.get("/lagre",kunde,function(){
    });
    $("#navn").val(""); //tøm input-feltene
    $("#adresse").val("");
    window.location.href = "index.html";
}

