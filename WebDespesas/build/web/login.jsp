<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Signin Colletore - Painel</title>

        <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/sign-in/">


        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <style>
            .bd-placeholder-img {
                font-size: 1.125rem;
                text-anchor: middle;
                -webkit-user-select: none;
                -moz-user-select: none;
                user-select: none;
            }

            @media (min-width: 768px) {
                .bd-placeholder-img-lg {
                    font-size: 3.5rem;
                }
            }
        </style>


        <!-- Custom styles for this template -->
        <link href="css/signin.css" rel="stylesheet">
    </head>
    <body class="text-center">

        <main class="form-signin">
            <form method="post" action="/WebDespesas/acao?param=logar">  
                <h1 class="h3 mb-3 fw-normal">LOGIN</h1>
                
                <img class="mb-4" src="img/logo.png" alt="" width="300">

                

                               
                <label for="inputEmail" class="visually-hidden">Usuario</label>
                <input type="email" id="inputEmail" name="email" class="form-control" placeholder="E-mail" required autofocus>

                <label for="inputPassword" class="visually-hidden">Password</label>
                <input type="password" id="inputPassword" name="senha" class="form-control" placeholder="Senha" required>


                <button class="w-100 btn btn-lg btn-primary" type="submit">Acessar</button>

                <p class="mt-5 mb-3 text-muted">&copy; 2017–2022</p>
            </form>
        </main>

    </body>


</html>
