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

@WebServlet(name = "dashboard", urlPatterns = {"/dashboard"})
public class ingresarDashboard extends HttpServlet  {

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
            if (usuario.consultar()){
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/vista/dashboard.jsp");
                dispatcher.forward(request,response);
            }else{
                session.setAttribute("mensaje","No tiene permisos, inicie sesion");
                response.sendRedirect(request.getContextPath());
            }
        }else{
            session.setAttribute("mensaje","No tiene permisos, inicie sesion");
            response.sendRedirect(request.getContextPath());
        }

    }

}
