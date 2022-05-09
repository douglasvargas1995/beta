<%-- 
    Document   : listarUsuario
    Created on : 08/04/2021, 21:08:46
    Author     : Douglas
--%>

<%@page import="dao.daoLogin"%>
<%@page import="entidade.Login"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <%
            ArrayList<Login> lo = new LoginDAO().consultarTodos();
        %>
        <div class="table-responsive">
            <table class="table table-striped table-sm">
                <th>#</th>
                <th>Nome</th>
                <th>Email</th>
                <th>Estado</th>
                <th>   Ação</th>

                <%
                    for (int i = 0; i < lo.size(); i++) {
                        Login lg = lo.get(i);
                %>
                <tr>
                    <td><%= lg.getId()%></td>
                    <td><%= lg.getNome()%></td>
                    <td><%= lg.getEmail()%></td>
                    <td><%= lg.getStatus()%></td>
                    <td><a href='/WebDespesas/acao?param=edLogin&id=<%= lg.getId()%>'<input type="button" class="btn-sm btn-primary" value="Editar">Editar</a>
                        <a href='/WebDespesas/acao?param=exLogin&id=<%= lg.getId()%>'<input type="button" class="btn-sm btn-danger" value="Remover">Remover</a>
                    <td>
                    
                </tr>

                <%
                    }
                %>

            </table>
        </div>
        <br>
        <br>
        <a href='menu.jsp'>Voltar</a>
    </body>
</html>