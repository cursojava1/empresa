package application.basededatos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private Connection cnx;
    private String ipHost;
    private String puerto;
    private String baseDatos;
    private String usuario;
    private String contrasena;
    private String cadenaConexion;
    private String gestorBD;
    private String driver;
    public Conexion(
            String paramIpHost,
            String paramPuerto,
            String paramBaseDatos,
            String paramUsuario,
            String paramContrasena,
            String paramGestorBD) {
        this.ipHost = paramIpHost;
        this.puerto = paramPuerto;
        this.baseDatos = paramBaseDatos;
        this.usuario = paramUsuario;
        this.contrasena = paramContrasena;
        this.gestorBD = paramGestorBD;
        if (gestorBD.equalsIgnoreCase("ORACLE")) {
            this.cadenaConexion = "jdbc:oracle:thin:@" + ipHost + ":" + puerto + ":" + baseDatos;
            this.driver = "oracle.jdbc.OracleDriver";
        } else if (gestorBD.equalsIgnoreCase("MYSQL")) {
            this.cadenaConexion = "jdbc:mysql://" + ipHost + ":" + puerto + "/" + baseDatos;
            this.driver = "com.mysql.jdbc.Driver";
        } else if(gestorBD.equalsIgnoreCase("POSTGRES")){
            this.cadenaConexion = "jdbc:postgresql://" + ipHost + ":" + puerto + "/" + baseDatos;
            this.driver = "org.postgresql.Driver";
        }else if(this.gestorBD.equalsIgnoreCase("SQLSERVER")){
            this.cadenaConexion = "jdbc:sqlserver://" + ipHost + ":" + puerto + ";DatabaseName=" + baseDatos;
            this.driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        }else {
            System.err.println("No existe implementacion para ese gestor");
        }
    }
    public boolean conectarBD() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            Class.forName(this.driver).newInstance();
            cnx = DriverManager.getConnection(cadenaConexion, usuario, contrasena);
            cnx.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            cnx.setAutoCommit(false);
            return true;
        } catch (ClassNotFoundException ex) {
            System.err.println("causa: " + ex.getCause());
            System.err.println("Mensaje: " + ex.getMessage());
            System.err.println("Localizacion: " + ex.getLocalizedMessage());
            System.err.println("Traza: " + ex.getStackTrace());
            throw ex;
        } catch (InstantiationException ex) {
            System.err.println("causa: " + ex.getCause());
            System.err.println("Mensaje: " + ex.getMessage());
            System.err.println("Localizacion: " + ex.getLocalizedMessage());
            System.err.println("Traza: " + ex.getStackTrace());
            throw ex;
        } catch (IllegalAccessException ex) {
            System.err.println("causa: " + ex.getCause());
            System.err.println("Mensaje: " + ex.getMessage());
            System.err.println("Localizacion: " + ex.getLocalizedMessage());
            System.err.println("Traza: " + ex.getStackTrace());
            throw ex;
        } catch (SQLException ex) {
            System.err.println("causa: " + ex.getCause());
            System.err.println("Mensaje: " + ex.getMessage());
            System.err.println("Localizacion: " + ex.getLocalizedMessage());
            System.err.println("Traza: " + ex.getStackTrace());
            throw ex;
        }
    }
    public boolean desconectarBD() throws SQLException {
        try {
            cnx.close();
            return true;
        } catch (SQLException ex) {
            System.err.println("causa: " + ex.getCause());
            System.err.println("Mensaje: " + ex.getMessage());
            System.err.println("Localizacion: " + ex.getLocalizedMessage());
            System.err.println("Traza: " + ex.getStackTrace());
            throw ex;
        }
    }
    public boolean confirmar() throws SQLException {
        try {
            cnx.commit();
            return true;
        } catch (SQLException ex) {
            System.err.println("causa: " + ex.getCause());
            System.err.println("Mensaje: " + ex.getMessage());
            System.err.println("Localizacion: " + ex.getLocalizedMessage());
            System.err.println("Traza: " + ex.getStackTrace());
            throw ex;
        }
    }
    public boolean deshacer() throws SQLException {
        try {
            cnx.rollback();
            return true;
        } catch (SQLException ex) {
            System.err.println("causa: " + ex.getCause());
            System.err.println("Mensaje: " + ex.getMessage());
            System.err.println("Localizacion: " + ex.getLocalizedMessage());
            System.err.println("Traza: " + ex.getStackTrace());
            throw ex;
        }
    }
    public Connection getCnx() {
        return cnx;
    }
    public void setCnx(Connection cnx) {
        this.cnx = cnx;
    }
    public String getIpHost() {
        return ipHost;
    }
    public void setIpHost(String ipHost) {
        this.ipHost = ipHost;
    }
    public String getPuerto() {
        return puerto;
    }
    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }
    public String getBaseDatos() {
        return baseDatos;
    }
    public void setBaseDatos(String baseDatos) {
        this.baseDatos = baseDatos;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public String getCadenaConexion() {
        return cadenaConexion;
    }
    public void setCadenaConexion(String cadenaConexion) {
        this.cadenaConexion = cadenaConexion;
    }
    public String getGestorBD() {
        return gestorBD;
    }
    public void setGestorBD(String gestorBD) {
        this.gestorBD = gestorBD;
    }
    public String getDriver() {
        return driver;
    }
    public void setDriver(String driver) {
        this.driver = driver;
    }
}



// llamada    Conexion.getInstance().getProperty(Conexion.DATABASE_SERVER)