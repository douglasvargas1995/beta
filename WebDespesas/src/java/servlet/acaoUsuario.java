package servlet;

import dao.daoLogin;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Douglas
 */
@WebServlet(name = "acaoUsuario", urlPatterns = {"/acaoUsuario"})
public class acaoUsuario extends HttpServlet {

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet acaoUsuario</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet acaoUsuario at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("utf-8");

        System.out.println("estou no POST do login");

        String parametro = request.getParameter("parametro");
        
        if (parametro.equals("cadUsuario")) {

            request.setAttribute("origem", "cadastroLogin.jsp");

            if (new daoLogin().cadastrarLogin(request, response)) {
//                
                
                System.out.println("entrou cadastrou");
                out.println("ok");

            } else {
//                encaminharPagina("erro.jsp", request, response);
                out.println("erro");
            }
        } else if (parametro.equals("login")) {
                          
            response.sendRedirect("menu.jsp");
         
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
