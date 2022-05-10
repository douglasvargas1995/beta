<%-- 
    Document   : cadastroLogin
    Created on : 29/03/2021, 20:57:22
    Author     : Douglas
--%>

<%@page import="entidade.Login"%>
<!DOCTYPE html>
<html lang="en">
    <%@include file="menu.jsp" %>

    <script src="js/jquery-3.4.1.js" type="text/javascript"></script>
    <script type="text/javascript">

        // Via JQuery
        $(document).ready(function () {

            $('#salvar').click(function ()

            {
                $.ajax({
                    type: "POST",
                    url: "/WebDespesas/acaoUsuario?parametro=cadUsuario",
                    data: $('form').serialize()
                }).done(function (retorno) {
                    var resultado = $.trim(retorno);
                    if (resultado == "erro") {
                        swal("Erro ao salvar registro", "Erro!", "error");
                    } else {
                        //alert("Deu certo: " + retorno);

                        $('#nome').focus();
                        $('#cadUsuario').each(function () {
                            this.reset();
                            $(this).closest('form').find("input[type=text], input[type=password], input[type=email], textarea").val("");
                        });
//                            swal("Registro salvo!");
                        swal("Registro salvo", "Salvo!", "success");
                        $(document).ready(function () {
                            $(':button').click(function () {
                                window.location = "/WebDespesas/cadastroLogin.jsp";
                            });
                        });
                    }
                });
                return false;
            }
            );
        });

    </script>

    <title>Cadastro de login</title>
    <link rel="stylesheet" href="css/style.css"> 
    <%            Login login = (Login) request.getAttribute("objLogin");

        if (login == null) {
            login = new Login();

            login.setId(0);
            login.setNome("");
            login.setEmail("");
            login.setSenha("");
            login.setStatus("");
        }
    %>
    <body> 
        <div id="main-container">
            <h1>Cadastro de login</h1>
            <!--<form name='formLogin' method='post' action='/WebChamados/acao?param=salvarLogin'>-->
            <form name='formLogin' method='post' action=''>
                <input type="hidden" name="id" value="<%= login.getId()%>">
                <div class="full-box">
                    <label for="email">E-mail</label>
                    <input required="required" type="email" name="email" value="<%= login.getEmail()%>" placeholder="Digite seu e-mail" data-min-length="2" data-email-validate>
                </div>
                <div class="half-box spacing">
                    <label for="name">Nome</label>
                    <input required="required" type="text" name="nome" pattern="[a-zA-Z\s]+$" value="<%= login.getNome()%>" placeholder="Digite seu nome" data-required data-min-length="3" data-max-length="16">
                </div>
                <div class="half-box spacing">
                    <label for="pass">Senha</label>
                    <input required="required"type="password" name="senha" value="<%= login.getSenha()%>" placeholder="Digite sua senha" data-password-validate data-required>
                </div>
                <div>
                    <input type="checkbox" name="agreement" id="agreement" required>
                    <label for="agreement" id="agreement-label">Eu li e aceito os <a href="#">termos de uso</a></label>
                </div>
                <div class="full-box">
                    <input name="enviar" id="salvar" type="submit" value="Registrar">
                </div>
            </form>
        </div>
        <p class="error-validation template"></p>
        
    </body>
</html>
