$(function(){  // kjøres når dokumentet er ferdig lastet
    hentAlleBiler();
    henteEnMotorvogn();
});

function hentAlleBiler() {
    $.get( "/hentBiler", function( biler ) {
        formaterBiler(biler);
    });
}

function formaterBiler(biler){
    let ut = "<select id='valgtMerke' onchange='finnTyper()'>";
    let i = 0;
    let forrigeMerke = "";
    ut+="<option>Velg merke</option>";
    for (const bil of biler){
        if(bil.merke != forrigeMerke){
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

function henteEnMotorvogn(){
    const id = window.location.search.substring(1); // kommer fra kallet i index.js
    const url = "/henteEnMotorvogn?id="+id;
    $.get( url, function(enMotorVogn) {
        // overfør til input-feltene i skjemaet
        $("#id").val(enMotorVogn.id); // må ha med denne for å vite hvilken id
        $("#personnr").val(enMotorVogn.personnr);
        $("#navn").val(enMotorVogn.navn);
        $("#adresse").val(enMotorVogn.adresse);
        $("#kjennetegn").val(enMotorVogn.kjennetegn);
        $("#merke").val(enMotorVogn.merke);
        $("#type").val(enMotorVogn.type);
    });
}

function endreMotorvogn() {
    const motorvogn = {
        id : $("#id").val(),
        personnr : $("#personnr").val(),
        navn : $("#navn").val(),
        adresse : $("#adresse").val(),
        kjennetegn : $("#kjennetegn").val(),
        merke : $("#valgtMerke").val(),
        type : $("#valgtType").val(),
    };
    $.post("/endre", motorvogn, function(){
    });

    window.location.href="index.html";
}


