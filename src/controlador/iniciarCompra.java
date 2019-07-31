package controlador;

import modelo.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "iniciarCompra", urlPatterns = {"/iniciarCompra"})
public class iniciarCompra extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String json = null;
        try{
            json = (String) session.getAttribute("carrito");
        }catch(Exception e){
            e.printStackTrace();
        }
        if (json != null && !json.equals("[]")){

            Usuario user = null;
            try{
                user = (Usuario) session.getAttribute("credenciales");
            }catch(Exception e){
                e.printStackTrace();
            }

            if (user!=null){
                request.getRequestDispatcher("/WEB-INF/vista/comprar.jsp").forward(request,response);
            }else{
                session.setAttribute("mensaje", "Debe de iniciar sesion para seguir con la compra");
            }
        }else{
            session.setAttribute("mensaje", "El carrito esta vacio");
        }
        response.sendRedirect(request.getContextPath());


    }

}
