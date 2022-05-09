/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validardados() {
    console.log("Entrei na função ValidarDados");
    
    var erro = 0;
    var extratoErros = "\n";
    descricao = document.formCategoria.descricao.value;
    situacao = document.formCategoria.situacao.value;
    
    console.log("Tam descrição: " + descricao.length);
    
    if (descricao.length < 5)
    {
        document.formCategoria.descricao.style.backgroundColor = "yellow";
        document.formCategoria.descricao.focus();
        extratoErros = extratoErros + "Descrição\n";
        console.log("Deu erro na validação da Descrição!");
        erro++;
    } else {
        document.formCategoria.descricao.style.backgroundColor = "white";
    }
    
    if (situacao.length < 1 || situacao.length > 1)
    {
        document.formCategoria.situacao.style.backgroundColor = "yellow";
        extratoErros = extratoErros + "Situação\n";
        erro++;
    } else {
        document.formCategoria.situacao.style.backgroundColor = "white";
    }
    
    if (erro > 0) {
        window.alert("Preencha os campos corretamente!\n\
        " + extratoErros);
        return false;
    } else {
        return true;
    }
}

