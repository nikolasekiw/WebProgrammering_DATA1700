function regOrdre() {
    const ordre = {
        fornavn : $("#fornavn").val(),
        etternavn : $("#etternavn").val(),
        adresse : $("#adresse").val(),
        varenavn : $("#varenavn").val(),
        pris : $("#pris").val()
    };
    $.post("/lagre", ordre, function(){

    });
}

