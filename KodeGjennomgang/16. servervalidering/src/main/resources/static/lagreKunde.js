function validerOgLagreKunde(){
    const navnOK = validerNavn($("#navn").val());
    const adresseOK = validerAdresse($("#adresse").val());
    if (navnOK && adresseOK){
        lagreKunde();
     }
}

function lagreKunde() {
    const kunde = {
        navn : $("#navn").val(),
        adresse : $("#adresse").val(),
    };
    const url = "/lagreKunde";
    $.post( url, kunde, function() {
        window.location.href = 'index.html';
    })
    .fail(function(jqXHR) {
        const json = $.parseJSON(jqXHR.responseText);
        $("#feil").html(json.message);
    });
}
