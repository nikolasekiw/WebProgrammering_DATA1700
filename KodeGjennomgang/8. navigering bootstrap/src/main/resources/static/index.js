$(function(){
    hentAlle();
});

function hentAlle() {
    $.get( "/hentAlle", function(data) {
        formaterData(data);
    });
}

function formaterData(kunder){
    let ut = "<table class='table table-striped'>" +
        "<tr>" +
        "<th>Navn</th><th>Adresse</th>" +
        "</tr>";
    for(const kunde of kunder ){
        ut+="<tr><td>"+kunde.navn+"</td><td>"+kunde.adresse+"</td></tr>"
    }
    $("#kundene").html(ut);
}

function slettKundene() {
    const ok=confirm("Sikker på å slette alle?");
    if(ok){
        $.get( "/slettAlle", function() {
            hentAlle();
        });
    }
}