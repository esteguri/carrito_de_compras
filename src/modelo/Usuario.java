package modelo;

import java.sql.ResultSet;

public class Usuario {


    private int idUsuario;
    private String usuario;
    private String password;

    public Usuario(){}

    public Usuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword(String s) {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean insertar(){
        String INSERT = "INSERT INTO usuario(usuario, password) VALUES ('"+this.usuario+"','"+this.password+"')";
        Conexion oConexion = new Conexion();
        oConexion.setSQL(INSERT);
        return oConexion.ejecutarSentencia();
    }

    public boolean consultar(){
        String SELECT = "SELECT * FROM `usuario` WHERE `usuario` = '"+this.usuario+"' AND `password` = '"+this.password+"'";
        Conexion oConexion = new Conexion();
        oConexion.setSQL(SELECT);
        if (oConexion.ejecutarConsulta()){
            try{
                ResultSet rs = oConexion.getoResultSet();
                if (rs.next()){
                    this.idUsuario = Integer.parseInt(rs.getString(1));
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
