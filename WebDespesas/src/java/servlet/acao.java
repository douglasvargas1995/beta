
package servlet;

import dao.daoLogin;
import entidade.Login;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author douglas
 */

@WebServlet(name = "acao", urlPatterns = {"/acao"})
public class acao extends HttpServlet {
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Esse é o título do Servlet ACAO</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>voçe fez logout " + request.getContextPath() + "</h1>");
            out.println("<h6>Data:" + new Date() + "</h6>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        String param = request.getParameter("param");
        
        System.out.println("ESTOU no GET");

        // =================== LOGIN ===================================
        if (param.equals("logout")) {
            HttpSession sessao = request.getSession();
            sessao.invalidate();
            response.sendRedirect("login.jsp");
        }

        ///EXCLUIR LOGIN
        if (param.equals("exLogin")) {
            String id = request.getParameter("id");
            String log = new daoLogin().excluir(Integer.parseInt(id));

            if (log != null) {

                //request.setAttribute("objLogin", log);
                encaminharPagina("cadastroLogin.jsp", request, response);
            } else {
                encaminharPagina("erro.jsp", request, response);
            }

        }

        ///EDITAR LOGIN
        if (param.equals("edLogin")) {
            String id = request.getParameter("id");

            Login log = new daoLogin().consultarId(Integer.parseInt(id));

            if (log != null) {

                request.setAttribute("objLogin", log);

                encaminharPagina("cadastroLogin.jsp", request, response);
            } else {
                encaminharPagina("erro.jsp", request, response);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        System.out.println("ESTOU no POST");

        String param = request.getParameter("param");

        ///SALVAR LOGIN
        if (param.equals("salvarLogin")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            Login l = new Login();
            l.setId(id);
            l.setNome(nome);
            l.setEmail(email);
            l.setSenha(senha);
            l.setStatus("A");

            String retorno = null;
            if (id == 0) {
                retorno = new daoLogin().salvar(l);
            } else {
                retorno = new daoLogin().atualizar(l);
            }

            if (retorno == null) {
                // deu certo
                request.setAttribute("tipoCadastro", "Login");
                request.setAttribute("paginaRetorno", "cadastroLogin.jsp");

                encaminharPagina("sucesso.jsp", request, response);
            } else {
                // deu errado
                encaminharPagina("erro.jsp", request, response);
            }
        }


        ///AUTENTICAÇÃO DE LOGIN COM CONSULTA NO BANCO DE DADOS
        if (param.equals("logar")) {
            System.out.println("chegou em logar");
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");

            String loginOk = new daoLogin().consultarLogin(email, senha);

            if ("ok".equals(loginOk)) {
                // deu certo
                HttpSession sessao = ((HttpServletRequest) request).getSession();

                sessao.setAttribute("usuarioLogado", email);
                encaminharPagina("inicial.jsp", request, response);

                System.out.println("entreii");
            } else if ("n".equals(loginOk)) {
                // senha errada
                encaminharPagina("erroSenha.jsp", request, response);
                System.out.println("senha errada ou usuario inexistente");
            } else {
                System.out.println("usuario inativo");
            }
        }

    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void encaminharPagina(String pagina, HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher rd = request.getRequestDispatcher(pagina);
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println("Erro ao encaminhar: " + e);
        }
    }
}
