function carregaPagina(pagina) {

  //  document.getElementById('insere_aqui').style.display = "inline"; // mostra div    

    var requisicaoAjax = iniciaAjax();

    if (requisicaoAjax) {
        requisicaoAjax.onreadystatechange = function () {	

            if (requisicaoAjax.readyState === 4) {
                if (requisicaoAjax.status === 200 || requisicaoAjax.status === 304) {
                    document.getElementById("retornoAjax").innerHTML = requisicaoAjax.responseText;
                }
            }
        };

        requisicaoAjax.open("POST", pagina, true);
        requisicaoAjax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')

        var form = document.forms['formulario'];
        var param1 = form.campoDeBusca.value;
//        var param2 = form.param2.value;
        qstr = 'campoBusca=' + escape(param1);// + "&param2=" + escape(param2);

        requisicaoAjax.send(qstr);
        return true;
    } else {
        return false;
    }
}

// Funcao que cria objeto Ajax XMLHttpRequest
function iniciaAjax() {
    var objetoAjax = false;
    
    if (window.XMLHttpRequest) { // tenta carregar componente Mozilla/safari
        objetoAjax = new XMLHttpRequest();
    } else if (window.ActiveXObject) { // se falhar, tenta carregar o ActiveX do IE :-(
        try {
            objetoAjax = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            try {
                objetoAjax = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (ex) {
                objetoAjax = false;
            }
        }
    }
    return objetoAjax;
}
