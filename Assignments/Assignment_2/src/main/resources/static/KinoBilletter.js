let utskrift;

function kjopBillett() {
    const bestilling = {
        fornavn: $("#fornavn").val(),
        etternavn: $("#etternavn").val(),
        telefonnr: $("#telefonnr").val(),
        epost: $("#epost").val(),
        film: $("#film").val(),
        antall: $("#antall").val()
    };
    let epostLengde = $("#epost").html = bestilling.epost.split("@");
    let feilmelding = false;
    /**
     * Film
     */
    if (bestilling.film === "tomFilm") {
        $("#filmFeil").html("Du må velge en film");
        feilmelding = true;
    } else {
        $("#filmFeil").html("") ;
    }
    /**
     * Fornavn
     */
    if (!isNaN(bestilling.fornavn) || bestilling.fornavn === "") {
        $("#fornavnFeil").html("Du må skrive inn fornavnet");
        feilmelding = true;
    } else if (bestilling.fornavn.length < 2) {
        $("#fornavnFeil").html("Fornavnet er for kort");
        feilmelding = true;
    } else {
        $("#fornavnFeil").html("");
    }
    /**
     * Etternavn
     */
    if (!isNaN(bestilling.etternavn) || bestilling.etternavn === "") {
        $("#etternavnFeil").html("Du må skrive inn etternavnet");
        feilmelding = true;
    } else if (bestilling.etternavn.length < 2) {
        $("#etternavnFeil").html("Etternavnet er for kort");
        feilmelding = true;
    } else {
        $("#etternavnFeil").html("");
    }
    /**
     * Telefonnummer
     */
    if (isNaN(bestilling.telefonnr) || bestilling.telefonnr === "") {
        $("#telefonnrFeil").html("Du må skrive tall inn i telefonnummer");
        feilmelding = true;
    } else if (bestilling.telefonnr.length < 8 || bestilling.telefonnr.length > 8) {
        $("#telefonnrFeil").html("Telefonnummeret må være 8 tegn");
        feilmelding = true;
    } else {
        $("#telefonnrFeil").html("");
    }
    /**
     * Epost
     */
    if (!isNaN(bestilling.epost) || bestilling.epost === "") {
        $("#epostFeil").html("Du må skrive inn epost");
        feilmelding = true;
    } else if (bestilling.epost.length < 6) {
        $("#epostFeil").html("Eposten er for kort");
        feilmelding = true;
    } else if (epostLengde.length != 2) {
        $("#epostFeil").html("Eposten må inneholde @");
        feilmelding = true;
    } else {
        $("#epostFeil").html("");
    }
    /**
     * Antall
     */
    if (isNaN(bestilling.antall) || bestilling.antall === null || bestilling.antall <= 0) {
        $("#antallFeil").html("Du må skrive tall større enn 0 inn i antall");
        feilmelding = true;
    } else {
        $("#antallFeil").html(null);
    }

    if(feilmelding === false){
        $("#fornavn").val("");
        $("#etternavn").val("");
        $("#telefonnr").val("");
        $("#epost").val("");
        $("#film").val("");
        $("#antall").val("");
    } else {
        return;
    }
    if(bestilling.fornavn === "" && bestilling.etternavn === "" && bestilling.telefonnr === ""
        && bestilling.epost === "" && bestilling.film === "tomFilm" && bestilling.antall === ""){
        return;
    } else {
        $.post("/lagre", bestilling, function () {
            hentAlle();
        });
    }
}
function formaterData(bestillinger) {
    utskrift = "<table class=\"table table-borderless\"><tr>" +
        "<th>Fornavn</th>" +
        "<th>Etternavn</th" +
        "><th>Telefonnummer</th" +
        "><th>Epost</th>" +
        "<th>Film</th>" +
        "<th>Antall</th></tr>";
    for (let b of bestillinger) {
        utskrift += "<tr>" +
            "<td>" + b.fornavn + "</td>" +
            "<td>" + b.etternavn + "</td>" +
            "<td>" + b.telefonnr + "</td>" +
            "<td>" + b.epost + "</td>" +
            "<td>" + b.film + "</td>" +
            "<td>" + b.antall + "</td>" +
            "</tr>";
    }
    utskrift += "</table>";
    $("#alleBilletter").html(utskrift);
}
function hentAlle() {
    $.get("/hentAlle", function (data) {
        formaterData(data);
    });
}
function slettBilletter() {
    $.post("/slettAlle", function () {
        hentAlle();
    });
}