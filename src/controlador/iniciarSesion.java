package controlador;

import modelo.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "iniciarSesion", urlPatterns = {"/iniciarSesion"})
public class iniciarSesion extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        Usuario user = new Usuario(usuario, password);
        if (user.consultar()){
            session.setAttribute("credenciales", user);
            session.setAttribute("mensaje","si");
        }else{
            session.setAttribute("mensaje","Credenciales incorrectas, revise el usuario o el password");
        }
        response.sendRedirect(request.getContextPath());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Usuario usuario;
        try{
            usuario =  (Usuario) session.getAttribute("credenciales");
        }catch (Exception e){
            e.printStackTrace();
            usuario = null;
        }

        if (usuario != null){
            if (!usuario.consultar()){
                session.setAttribute("mensaje","Credeniales incorrectas, revise el usuario o el password");
            }
        }else{
            session.setAttribute("mensaje","No tiene permisos, inicie sesion");
        }
        response.sendRedirect(request.getContextPath());
    }



}
