$(function (){
    hentAlleBiler();
});

function hentAlleBiler() {
    $.get( "/hentBiler", function( biler ) {
        formaterBiler(biler);
    })
        .fail(function(){
            $("#feil").html("Feil i DB - prøv igjen senere!");
        });
}

function formaterBiler(biler){
    let ut = "<select id='valgtMerke' onchange='finnTyper()'>";
    let forrigeMerke = "";
    ut+="<option>Velg merke</option>";
    for (const bil of biler){
        if(bil.merke !== forrigeMerke){
            ut+="<option>"+bil.merke+"</option>";
        }
        forrigeMerke = bil.merke;
    }
    ut+="</select>";
    $("#merke").html(ut);
}

function finnTyper(){
    const valgtMerke = $("#valgtMerke").val();
    $.get( "/hentBiler", function( biler ) {
        formaterTyper(biler,valgtMerke);
    })
        .fail(function(){
            $("#feil").html("Feil i DB - prøv igjen senere!");
        });
}
function formaterTyper(biler,valgtMerke){
    let ut = "<select id='valgtType'>";
    for(const bil of biler ){
        if(bil.merke === valgtMerke){
            ut+="<option>"+bil.type+"</option>";
        }
    }
    ut+="</select>";
    $("#type").html(ut);
}

/**
 * Dette er en ready funksjon som kjøres med en gang siden laster inn.
 * Denne trenger vi for at siden skal laste inn de data som opprinnelig sto i input-feltene slik at
 * de ikke skal være tomme når vi trykker på "endre".
 */
$(function() {
    // hent kunden med kunde-personnr fra url og vis denne i skjemaet
    const id = window.location.search.substring(1);
    const url = "/hentEnMotorvogn?"+id;
    $.get(url, function (motorvogn) {
        $("#id").val(motorvogn.id);
        $("#personnr").val(motorvogn.personnr);
        $("#navn").val(motorvogn.navn);
        $("#adresse").val(motorvogn.adresse);
        $("#kjennetegn").val(motorvogn.kjennetegn);
        $("#valgtMerke").val(motorvogn.merke);
        $("#valgtType").val(motorvogn.type)
    })
        .fail(function (jgXHR) {
            const json = $.parseJSON(jgXHR.responseText);
            $("#feil").html(json.message);
        });
});

/**
 * Her valideres det om input i feltene er riktig. Hvis det er riktig, så kaller den på metoden "endreEnMotorvogn"
 */
function validerOgEndre(){
    const personnrOK = validerPersonnr($("#personnr").val());
    const navnOK = validerNavn($("#navn").val());
    const adresseOK = validerAdresse($("#adresse").val());
    const kjennetegnOK = validerKjennetegn($("#kjennetegn").val());
    const merkeOK = validerMerke($("#merke").val());
    if (personnrOK && navnOK && adresseOK && kjennetegnOK && merkeOK){
        endreEnMotorvogn();
    }
}

/**
 * Det er her den faktisk endringen skjer. Her hentes den nye inputen fra feltene og lagres
 */
function endreEnMotorvogn() {
    const motorvogn = {
        id: $("#id").val(),
        personnr: $("#personnr").val(),
        navn: $("#navn").val(),
        adresse: $("#adresse").val(),
        kjennetegn: $("#kjennetegn").val(),
        merke: $("#valgtMerke").val(),
        type: $("#valgtType").val()
    };
    $.post("/endreEnMotorvogn", motorvogn, function(){
        window.location.href = "liste.html";
    })
        .fail(function(status) {
            if(status.status==422){
                $("#feil").html("Feil i db - prøv igjen senere");
            }
            else{
                $("#feil").html("Valideringsfeil - prøv igjen senere");
            }
        });
}