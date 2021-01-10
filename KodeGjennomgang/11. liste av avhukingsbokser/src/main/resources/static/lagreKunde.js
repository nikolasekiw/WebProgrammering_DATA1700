$(function(){  // kjøres når dokumentet er ferdig lastet
    hentAlleBiler();
});

function hentAlleBiler() {
    $.get( "/hentBiler", function(biler) {
        formaterBiler(biler);
    });
}

function formaterBiler(biler){
    let ut = "<table class='table table-striped'>";
    for(const bil of biler ){
        ut+="<tr>" +
                "<td>"+bil.merke+" "+bil.modell+"</td>"+
                "<td><input type='checkbox' name='valgteBiler[]' value='"+bil.merke+"'/></td>"+
            "</tr>";
    }
    ut+="<table/>";
    $("#bilene").html(ut);
}

function lagreKunde() {
    // legg de valgte bilene inn i en streng
    let bilene="";
    const bilArray = $("input[name='valgteBiler[]']:checked");
    for (let valgtBil of bilArray){
        bilene+=valgtBil.defaultValue+" ";
    }

    const kunde = {
        fornavn : $("#fornavn").val(),
        etternavn : $("#etternavn").val(),
        eierBil : bilene
    };
    const url = "/lagreKunde";
    $.post(url,kunde,function(){
        window.location.href = '/';
    });
}

