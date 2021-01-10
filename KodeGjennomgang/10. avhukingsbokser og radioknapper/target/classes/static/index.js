$(function(){  // kjøres når dokumentet er ferdig lastet
    hentAlleKunder();
});

function hentAlleKunder() {
    $.get( "/hentKunder", function( kunder ) {
        formaterKunder(kunder);
    });
}

function formaterKunder(kunder){
    let ut = "<table class='table table-striped'>" +
        "<tr>" +
            "<th>Fornavn</th><th>Etternavn</th><th>Bilmerke</th><th>Førerkort</th>" +
        "</tr>";
    for(const kunde of kunder ){
        let forerkort = "";
        if (kunde.forerkort){
            forerkort = "Ja";
        }
        else{
            forerkort = "Nei";
        }
        ut+="<tr>" +
                "<td>"+kunde.fornavn+"</td>"+
                "<td>"+kunde.etternavn+"</td>"+
                "<td>"+kunde.eierBil+"</td>"+
                "<td>"+forerkort+"</td>"+
            "</tr>";
    }
    $("#kundene").html(ut);
}

function slettKundene() {
    $.get( "/slettAlleKunder", function() {
        window.location.href = '/';
    });
}