package modelo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Compra {

    private int idCompra, idUsuario;
    private String fecha, medioPago, productos;

    public Compra() {

    }

    public Compra(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Compra(int idUsuario, String fecha, String medioPago, String productos) {
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.medioPago = medioPago;
        this.productos = productos;
    }

    public Compra(int idCompra, int idUsuario, String fecha, String medioPago, String productos) {
        this.idCompra = idCompra;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.medioPago = medioPago;
        this.productos = productos;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public boolean insertar(){
        String INSERT = "INSERT INTO `compra`( `idUsuario`, `fecha`, `medioPago`, `productos`) VALUES ("+idUsuario+",'"+fecha+"','"+medioPago+"','"+productos+"')";
        Conexion oConexion = new Conexion();
        oConexion.setSQL(INSERT);
        return oConexion.ejecutarSentencia();
    }

    public List<Compra> consultarAll(){
        String SELECT = "SELECT * FROM `compra` WHERE idUsuario="+idUsuario;
        Conexion oConexion = new Conexion();
        oConexion.setSQL(SELECT);
        if (oConexion.ejecutarConsulta()){
            try{
                ResultSet rs = oConexion.getoResultSet();
                List<Compra> compras = new ArrayList<Compra>();
                while (rs.next()){
                    Compra compra = new Compra(rs.getInt(1),this.idUsuario,rs.getString(3),rs.getString(4), rs.getString(5));
                    compras.add(compra);
                }
                oConexion.desconectar();
                return compras;
            }catch(Exception e){
                oConexion.desconectar();
                System.out.println("ERROR " + e.getMessage());
                return null;
            }
        }
        return null;
    }

    public boolean consultar(){
        String SELECT = "SELECT * FROM `compra` WHERE idUsuario="+idUsuario;
        Conexion oConexion = new Conexion();
        oConexion.setSQL(SELECT);
        if (oConexion.ejecutarConsulta()){
            try{
                ResultSet rs = oConexion.getoResultSet();
                if (rs.next()){
                    this.idCompra = Integer.parseInt(rs.getString(1));
                    this.fecha = rs.getString(3);
                    this.medioPago = rs.getString(4);
                    this.productos = rs.getString(5);
                    oConexion.desconectar();
                    return true;
                }else{
                    oConexion.desconectar();
                    return false;
                }
            }catch(Exception e){
                oConexion.desconectar();
                System.out.println("ERROR " + e.getMessage());
                return false;
            }
        }
        return false;
    }

}
