$(function(){  // kjøres når dokumentet er ferdig lastet
    hentAlleBiler();
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

function regMotorvogn() {
    const motorvogn = {
        personnr : $("#personnr").val(),
        navn : $("#navn").val(),
        adresse : $("#adresse").val(),
        kjennetegn : $("#kjennetegn").val(),
        merke : $("#valgtMerke").val(),
        type : $("#valgtType").val(),
    };
    $.post("/lagre", motorvogn, function(){
        hentAlle();
    });

    window.location.href="/";
}


