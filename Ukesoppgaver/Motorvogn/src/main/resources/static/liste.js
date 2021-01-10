$(function(){
    hentAlle();
});

/**
 * Denne metoden blir kalt på via ready funksjonen som kjører med en gang dokumentet er ferdig lastet.
 * Da kalles hentAlle metoden for å hente alle motorvogner fra databasen som allerede er registrert.
 * Det er vanlig å gi navnet jqXHR, men det er ikke nødvendig. Det kan hete hva som helst annet.
 */
function hentAlle() {
    $.get( "/hentAlle", function(biler) {
        formaterData(biler);
    })
        .fail(function(jqXHR) {
            const json = $.parseJSON(jqXHR.responseText);
            $("#feil").html(json.message);
        });
}

function formaterData(motorvogner) {
    let ut = "<table class='table table-striped'><tr>" +
        "<th>Personnr</th>" +
        "<th>Navn</th>" +
        "<th>Adresse</th>" +
        "<th>Kjennetegn</th>" +
        "<th>Merke</th>" +
        "<th>Type</th>" +
        "<th></th>" +
        "<th></th>" +
        "<th></th>" +
        "</tr>";
    for (const motorvogn of motorvogner) {
        ut += "<tr>" +
            "<td>" + motorvogn.personnr + "</td>" +
            "<td>" + motorvogn.navn + "</td>" +
            "<td>" + motorvogn.adresse + "</td>" +
            "<td>" + motorvogn.kjennetegn + "</td>" +
            "<td>" + motorvogn.merke + "</td>" +
            "<td>" + motorvogn.type + "</td>" +
            "<td> <a class='btn btn-primary' href='endre.html?id="+motorvogn.id+"'>Endre</a></td>"+
            "<td> <button class='btn btn-danger' onclick='slettEnMotorvogn("+motorvogn.personnr+")'>Slett</button></td>"+
            "</tr>";
    }
    ut += "</table>";
    $("#bilene").html(ut);
}

function slettEnMotorvogn(personnr){
    $.get("/slettEnMotorvogn?personnr="+personnr, function() {
        window.location.href = "/liste.html";
    });
}

function slettAlle(){
    $.get( "/slettAlle", function() {
        window.location.href = "/liste.html";
    })
        .fail(function(){
            $("#feil").html("Feil i DB - prøv igjen senere!");
        });
}

/**
 * Denne gjør ajax-kall til serveren og etter det er gjort sender den til loggInn siden igjen (index)
 */
function loggUt(){
    $.get("/loggUt", function(){
        window.location.href = "index.html";
    });
}