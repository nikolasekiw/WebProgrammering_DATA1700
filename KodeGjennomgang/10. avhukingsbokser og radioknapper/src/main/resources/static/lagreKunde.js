function lagreKunde() {
    const kunde = {
        fornavn : $("#fornavn").val(),
        etternavn : $("#etternavn").val(),
        eierBil : $('input[name=valgtBil]:checked').val(),
        forerkort : $('input[name=forerkort]:checked').val()
    };
    const url = "/lagreKunde";
    $.post(url,kunde,function(){
        window.location.href = '/';
    });
}

