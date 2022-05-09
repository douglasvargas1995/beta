package dao;

import apoio.ConexaoBD;
import apoio.IDAO;
import entidade.Login;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DOUGLAS
 */
public class daoLogin implements IDAO<Login>{
    
    ResultSet resultadoQ;

    public boolean salvarInicial(Login login) {
        try {
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "Insert into login values "
                    + "(default,"
                    + " '" + login.getNome() + "',"
                    + " '" + login.getEmail() + "',"
                    + " '" + login.getSenha() + "',"
                    + " '" + login.getStatus()+ "')";

            System.out.println("SQL: " + sql);

            int resultado = stm.executeUpdate(sql);

            return true;
        } catch (Exception e) {
            System.out.println("Erro ao salvar login: " + e);
            return false;
        }
    }

    @Override
    public String salvar(Login objeto) {
        try {
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "Insert into login values "
                    + "(default,"
                    + " '" + objeto.getNome() + "',"
                    + " '" + objeto.getEmail() + "',"
                    + " '" + objeto.getSenha() + "',"
                    + " 'A')";

            System.out.println("SQL: " + sql);

            int resultado = stm.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Erro ao salvar login: " + e);
            return e.toString();
        }

    }

    @Override
    public String atualizar(Login o) {
        String saida = null;

        try {
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "update login "
                    + "set nome = '" + o.getNome() + "', "
                    + "email = '" + o.getEmail() + "', "
                    + "senha = '" + o.getSenha() + "', "
                    + "estado = 'A' "
                    + "where id = " + o.getId();

            int retorno = stm.executeUpdate(sql);

            if (retorno != 0) {
                saida = null;
            } else {
                saida = "Erro";
            }

        } catch (Exception e) {
            System.out.println("Erro ao atualizar login: " + e);
            saida = e.toString();
        }

        return saida;
    }

    @Override
    public String excluir(int id) {

        String saida = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "Update login "
                    + "set estado = 'I' "
                    + "WHERE id = " + id;

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

            if (resultado != 0) {
                saida = "ok";
            } else {
                saida = "Erro";
            }

        } catch (Exception e) {
            System.out.println("Erro excluir login = " + e);
            return e.toString();
        }
        return saida;
    }

    @Override
    public ArrayList<Login> consultarTodos() {
        ArrayList<Login> logins = new ArrayList();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * "
                    + "from "
                    + "login "
                    + "WHERE estado = 'A' "
                    + "order by id";

            ResultSet resultado = st.executeQuery(sql);

            while (resultado.next()) {
                Login l = new Login();

                l.setId(resultado.getInt("id"));
                l.setNome(resultado.getString("nome"));
                l.setEmail(resultado.getString("email"));
                l.setSenha(resultado.getString("senha"));
                l.setStatus(resultado.getString("status"));

                logins.add(l);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar login: " + e);
        }

        return logins;
    }

    @Override
    public boolean registroUnico(Login o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Login> consultar(String criterio) {

        ArrayList<Login> usuarios;
        usuarios = new ArrayList<>();

        try {
            String sql = "SELECT * "
                    + "FROM login "
                    + "WHERE nome ilike '%" + criterio + "%' "
                    + "AND estado = 'A' "
                    + "ORDER BY nome";

            ResultSet retorno = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (retorno.next()) {
                Login u = new Login();

                u.setId(retorno.getInt("id"));
                u.setNome(retorno.getString("nome"));
                u.setEmail(retorno.getString("email"));
                u.setStatus(retorno.getString("status"));

                usuarios.add(u);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar login: " + e);
            return null;
        }

        return usuarios;
    }
    
    public String consultarLogin(String usuario, String senha) {
      
        String estado = "A";

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "select * from login "
                    + "where  "
                    + "email = '" + usuario + "' AND "
                    + "senha = '" + senha + "' AND "
                    + "status = '" + estado + "'";

            System.out.println("sql: " + sql);

            resultadoQ = st.executeQuery(sql);

            if (resultadoQ.next()) {
                return "ok";
            } else {
                return "n";
            }

        } catch (Exception e) {
            System.out.println("Erro consultar Usuario = " + e);
            return "n";
        }
    }

    @Override
    public Login consultarId(int id) {
        Login c = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * "
                    + "from "
                    + "login "
                    + "where "
                    + "id = " + id;

            ResultSet resultado = st.executeQuery(sql);

            while (resultado.next()) {
                c = new Login();

                c.setId(resultado.getInt("id"));
                c.setNome(resultado.getString("nome"));
                c.setEmail(resultado.getString("email"));
                c.setSenha(resultado.getString("senha"));
                c.setStatus(resultado.getString("status"));
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar login por ID: " + e);
        }

        return c;
    }

    @Override
    public boolean consultar(Login o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public boolean cadastrarLogin(HttpServletRequest request, HttpServletResponse response) {
        Login u = new Login();

        boolean ok = false;

        int id = Integer.parseInt(request.getParameter("id"));

        u.setId(id);
        u.setNome(request.getParameter("nome"));
        u.setEmail(request.getParameter("email"));
        u.setSenha(request.getParameter("senha"));
        u.setStatus(request.getParameter("status"));

        String retorno = "";

        if (!"".equals(u.getNome()) && !"".equals(u.getEmail()) && !"".equals(u.getSenha())) {
            if (id == 0) {

                retorno = new daoLogin().salvar(u);

            } else {
                retorno = new daoLogin().atualizar(u);
            }
        }

        if (retorno == null) {
            ok = true;
        } else {
            ok = false;
        }

        return ok;
    }
    
}

