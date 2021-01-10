function validerPersonnr(){
    const personnr = $("#personnr").val();
    const regexp = /^[0-9]{11}$/;
    const ok = regexp.test(personnr);
    if(!ok){
        $("#feilPersonnummer").html("Personnummeret må bestå av 11 siffer");
        return false;
    }
    else{
        $("#feilPersonnummer").html("");
        return true;
    }
}

function validerNavn(){
    const navn = $("#navn").val(); //her hentes navnet fra input i HTML
    const regexp = /^[a-zA-ZæøåÆØÅ. \-]{2,20}$/; //her lages det regex for navn
    const ok = regexp.test(navn); //her testes navnet mot regex og testes om det er ok
    if(!ok){ //hvis ikke ok, så returneres en passende melding
        $("#feilNavn").html("Navnet må bestå av 2 til 20 bokstaver");
        return false;
    }
    else{ //hvis ok, så blir feltet "feilNavn" i html stående tomt
        $("#feilNavn").html("");
        return true;
    }
}

function validerAdresse(){
    const adresse = $("#adresse").val();
    const regexp = /^[0-9a-zA-ZæøåÆØÅ. \-]{2,30}$/;
    const ok = regexp.test(adresse);
    if(!ok){
        $("#feilAdresse").html("Adressen må bestå av 2 til 40 bokstaver og tall");
        return false;
    }
    else{
        $("#feilAdresse").html("");
        return true;}
}

function validerKjennetegn() {
    const kjennetegn = $("#kjennetegn").val();
    const regexp = /^[A-Z][A-Z][0-9]{5}$/;
    const ok = regexp.test(kjennetegn);
    if (!ok) {
        $("#feilKjennetegn").html("Kjennetegnet må ha to store bokstaver og 5 tall");
        return false;
    } else {
        $("#feilKjennetegn").html("");
        return true;
    }
}

function validerMerke(){
    const merke = $("#valgtMerke").val();
    if(merke==="Velg merke"){
        $("#feilMerke").html("Velg et merke!");
        return false;
    }
    else{
        $("#feilMerke").html("");
        return true;
    }
}

function ingenValideringsFeil(){
    return ( validerPersonnr() && validerNavn() && validerAdresse() && validerKjennetegn() && validerMerke() );
}

function validerBrukernavn(){
    const brukernavn = $("#brukernavn").val();
    const regexp = /^[a-zA-ZæøåÆØÅ. \-]{2,20}$/;
    const ok = regexp.test(brukernavn);
    if(!ok){
        $("#feilBrukernavn").html("Navnet må bestå av 2 til 20 bokstaver");
        return false;
    }
    else{
        $("#feilBrukernavn").html("");
        return true;}
}

function validerPassord(){
    const passord = $("#passord").val();
    const regexp = /^(?=.*[A-ZÆØÅa-zøæå])(?=.*\d)[A-ZØÆÅa-zøæå\d]{8,}$/;
    const ok = regexp.test(passord);
    if(!ok){
        $("#feilPassord").html("Passordet må være minimum 8 tegn, minst en bokstav og et tall");
        return false;
    }
    else{
        $("#feilPassord").html("");
        return true;}
}

//Det samme her som i IngenValideringsFeil, hvis validering av brukernavn og passord er OK, så kan brukeren logge inn
function loggInnValideringOK() {
    return (validerBrukernavn() && validerPassord());
}


