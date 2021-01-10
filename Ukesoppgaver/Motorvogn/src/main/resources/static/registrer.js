$(function (){
    hentAlleBiler();
});

function hentAlleBiler() {
    $.get( "/hentBiler", function( biler ) {
        formaterBiler(biler);
    })
        .fail(function(jqXHR) {
            const json = $.parseJSON(jqXHR.responseText);
            $("#feil").html(json.message);
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
    $("#feilMerke").html("");
    $.get("/hentBiler", function( biler ) {
        formaterTyper(biler,valgtMerke);
    })
        .fail(function(jqXHR) {
            const json = $.parseJSON(jqXHR.responseText);
            $("#feil").html(json.message);
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
 * Dette er klientvalidering. Gir feilmelding i span som har blitt lagt til under hver input i HTML-filen.
 * Bruker span slik at feilmeldingen skal komme bak input og ikke under.
 */
function validerOgLagreMotorvogn(){
    const personnrOK = validerPersonnr($("#personnr").val());
    const navnOK = validerNavn($("#navn").val());
    const adresseOK = validerAdresse($("#adresse").val());
    const kjennetegnOK = validerKjennetegn($("#kjennetegn").val());
    const merkeOK = validerMerke($("#merke").val());
    if (personnrOK && navnOK && adresseOK && kjennetegnOK && merkeOK){
        regMotorvogn();
    }
}
function regMotorvogn() {
    const motorvogn = {
        personnr: $("#personnr").val(),
        navn: $("#navn").val(),
        adresse: $("#adresse").val(),
        kjennetegn: $("#kjennetegn").val(),
        merke: $("#valgtMerke").val(),
        type: $("#valgtType").val()
    };
    if (ingenValideringsFeil()) {
        $.post("/lagre", motorvogn, function() {
            hentAlle();
        });
        window.location.href = "liste.html";
    }
}