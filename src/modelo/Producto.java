package modelo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Producto {

    private int idProducto;
    private double valorProducto;
    private String nombreProducto;
    private int cantidad=1;

    public Producto() {
    }

    public Producto(int idProducto, int cantidad) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public Producto(double valorProducto, String nombreProducto) {
        this.valorProducto = valorProducto;
        this.nombreProducto = nombreProducto;
    }

    public Producto(int idProducto, double valorProducto, String nombreProducto) {
        this.idProducto = idProducto;
        this.valorProducto = valorProducto;
        this.nombreProducto = nombreProducto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto, int cantidad) {
        this.idProducto = idProducto;
    }

    public double getValorProducto() {
        return valorProducto;
    }

    public void setValorProducto(double valorProducto) {
        this.valorProducto = valorProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void aumentarCantidad(int num){
        this.cantidad+=num;
    }

    public List<Producto> consultarAll(){
        String SELECT = "SELECT * FROM `producto`";
        Conexion oConexion = new Conexion();
        oConexion.setSQL(SELECT);
        if (oConexion.ejecutarConsulta()){
            try{
                ResultSet rs = oConexion.getoResultSet();
                List<Producto> productos = new ArrayList<Producto>();
                while (rs.next()){
                    Producto producto = new Producto(rs.getInt(1), rs.getDouble(3), rs.getString(2));
                    productos.add(producto);
                }
                oConexion.desconectar();
                return productos;
            }catch(Exception e){
                oConexion.desconectar();
                System.out.println("ERROR " + e.getMessage());
                return null;
            }
        }
        return null;
    }

    public boolean consultar(){
        String SELECT = "SELECT * FROM `producto` WHERE `idProducto`= "+this.idProducto;
        Conexion oConexion = new Conexion();
        oConexion.setSQL(SELECT);
        if (oConexion.ejecutarConsulta()){
            try{
                ResultSet rs = oConexion.getoResultSet();
                if (rs.next()){
                    this.nombreProducto = rs.getString(2);
                    this.valorProducto = rs.getDouble(3);
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
