let utskrift;
let bestillinger = [];

function kjopBillett() {
    const bestilling = {
        fornavn: document.getElementById("fornavn").value,
        etternavn: document.getElementById("etternavn").value,
        telefonnr: document.getElementById("telefonnr").value,
        epost: document.getElementById("epost").value,
        film: document.getElementById("film").value,
        antall: document.getElementById("antall").value
    };

    let epostLengde = document.getElementById("epost").innerHTML = bestilling.epost.split("@");
    let feilmelding = false;

    /**
     * Film
     */
    if (bestilling.film === "feil") {
        document.getElementById("filmFeil").innerHTML = "Du må velge en film";
        feilmelding = true;
    } else {
        document.getElementById("filmFeil").innerHTML = "";
    }

    /**
     * Fornavn
     */
    if (!isNaN(bestilling.fornavn) || bestilling.fornavn === "") {
        document.getElementById("fornavnFeil").innerHTML = "Du må skrive inn fornavnet";
        feilmelding = true;
    } else if (bestilling.fornavn.length < 2) {
        document.getElementById("fornavnFeil").innerHTML = "Fornavnet er for kort";
        feilmelding = true;
    } else {
        document.getElementById("fornavnFeil").innerHTML = "";
    }

    /**
     * Etternavn
     */
    if (!isNaN(bestilling.etternavn) || bestilling.etternavn === "") {
        document.getElementById("etternavnFeil").innerHTML = "Du må skrive inn etternavnet";
        feilmelding = true;
    } else if (bestilling.etternavn.length < 2) {
        document.getElementById("etternavnFeil").innerHTML = "Etternavnet er for kort";
        feilmelding = true;
    } else {
        document.getElementById("etternavnFeil").innerHTML = "";
    }

    /**
     * Telefonnummer
     */
    if (isNaN(bestilling.telefonnr) || bestilling.telefonnr === "") {
        document.getElementById("telefonnrFeil").innerHTML = "Du må skrive tall inn i telefonnummer";
        feilmelding = true;
    } else if (bestilling.telefonnr.length < 8 || bestilling.telefonnr.length > 8) {
        document.getElementById("telefonnrFeil").innerHTML = "Telefonnummeret må være 8 tegn";
        feilmelding = true;
    } else {
        document.getElementById("telefonnrFeil").innerHTML = "";
    }

    /**
     * Epost
     */
    if (!isNaN(bestilling.epost) || bestilling.epost === "") {
        document.getElementById("epostFeil").innerHTML = "Du må skrive inn epost";
        feilmelding = true;

    } else if (bestilling.epost.length < 6) {
        document.getElementById("epostFeil").innerHTML = "Eposten er for kort";
        feilmelding = true;

    } else if (epostLengde.length != 2) {
        document.getElementById("epostFeil").innerHTML = "Eposten må inneholde @";
        feilmelding = true;

    } else {
        document.getElementById("epostFeil").innerHTML = "";
    }

    /**
     * Antall
     */
    if (isNaN(bestilling.antall) || bestilling.antall === null || bestilling.antall <= 0) {
        document.getElementById("antallFeil").innerHTML = "Du må skrive tall større enn 0 inn i antall";
        feilmelding = true;
    } else {
        document.getElementById("antallFeil").innerHTML = "";
    }

    //Hvis feilmeldingen ikke slår inn (er true) så skal alt som var i input nullstilles
    if (feilmelding === false) {
        document.getElementById("reset").reset();
    } else {
        return;
    }

    if (bestilling.fornavn === "" && bestilling.etternavn === "" && bestilling.telefonnr === ""
        && bestilling.epost === "" && bestilling.film === "feil" && bestilling.antall === "") {
        return;
    } else {
        bestillinger.push(bestilling);
    }

    utskrift = "<table><tr><th>Fornavn</th>" +
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
    document.getElementById("kjøpteBilletter").innerHTML = utskrift;
    utskrift += "</table>";
}

function slettBilletter() {
    document.getElementById("kjøpteBilletter").innerHTML = "";
}