package controlador;


import modelo.Compra;
import modelo.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "comprar", urlPatterns = {"/comprar"})
public class comprar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String metodoPago = request.getParameter("pago");
        String carrito = (String) session.getAttribute("carrito");
        Usuario user = null;
        try{
            user = (Usuario) session.getAttribute("credenciales");
        }catch(Exception e){
            e.printStackTrace();
        }
        Date myDate = new Date();
        String fecha = new SimpleDateFormat("dd-MM-yyyy").format(myDate);
        Compra compra = new Compra(user.getIdUsuario(),fecha, metodoPago,carrito);
        if (compra.insertar()){
            session.setAttribute("mensaje","Realizo compra correctamente");
            session.setAttribute("carrito",null);
        }else{
            session.setAttribute("mensaje","No se puedo realizar la compra");
        }
        response.sendRedirect(request.getContextPath());
    }
}