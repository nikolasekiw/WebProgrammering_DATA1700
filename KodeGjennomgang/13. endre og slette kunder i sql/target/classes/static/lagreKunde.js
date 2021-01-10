function lagreKunde() {
    const kunde = {
        navn : $("#navn").val(),
        adresse : $("#adresse").val(),
    }
    const url = "/lagreKunde";
    $.post( url, kunde, function() {
        window.location.href = 'index.html';
    });
};

