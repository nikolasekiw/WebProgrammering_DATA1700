/**
 * Vi får en boolean inn som sier om det gikk OK eller ikke (true eller false). Hvis det gikk OK, altså at det er
 * gyldig bruker og passord så kan vi sende videre til liste.html. Hvis det ikke gik OK (false), så gir vi
 * feilmelding om at det var feil i brukernavn eller passord i feil-diven
 */
function loggInn() {
    if (loggInnValideringOK()) {
        const url = "/loggInn?brukernavn=" + $("#brukernavn").val() + "&passord=" + $("#passord").val();
        $.get(url, function(OK) {
             if (OK) {
               window.location.href = "liste.html";
            } else {
              $("#feil").html("Feil brukernavn eller passord");
            }
        })
         .fail(function (jqXHR) {
              const json = $.parseJSON(jqXHR.responseText);
             $("#feil").html(json.message);
        });
    }
}
/**
 * Det kan også tenkes at det skjer en annen feil i programmet (generell feilhåndtering hvis det skulle
 * vært noe galt), som ikke er feil brukernavn eller passord, dette kan vi fange opp i en fail-funksjon
 **/
function krypterAllePassord(){
    $.get("/krypterAllePassord", function(OK){
        if (OK) {
            $("#feil").html("Kryptering utført!");
        } else {
            $("#feil").html("Feil i kryptering!");
        }
    })
    .fail(function(jqXHR){
        const json = $.parseJSON(jqXHR.responseText);
        $("#fail").html(json.message);
    });
}