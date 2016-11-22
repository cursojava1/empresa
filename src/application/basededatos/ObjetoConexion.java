package application.basededatos;
import java.sql.SQLException;

public class ObjetoConexion {
    private Conexion oracle;
    private Conexion mysql;
    private Conexion postgres;
    private Conexion sqlServer;
    
    public boolean conectarOracle() throws ClassNotFoundException, InstantiationException, InstantiationException, IllegalAccessException, SQLException{
        oracle=new Conexion(
                "192.168.1.124",
                "1521",
                "orcl",
                "sysferronor",
                "sysferronor",
                "ORACLE"
                );
        return oracle.conectarBD();
    }
    
    public boolean conectarMysql() throws ClassNotFoundException, InstantiationException, InstantiationException, IllegalAccessException, SQLException{
        mysql=new Conexion(
                "192.168.1.124",
                "3306",
                "celltower",
                "root",
                "toor",
                "MYSQL"
                );
        return mysql.conectarBD();
    }
    
    public boolean conectarPostgres() throws ClassNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        postgres=new Conexion(
                "192.168.1.124",
                "5432",
                "tumi",
                "postgres",
                "toor",
                "POSTGRES"
                );
        return postgres.conectarBD();
    }
    
    public boolean conectarSQLServer() throws ClassNotFoundException, ClassNotFoundException, InstantiationException, InstantiationException, IllegalAccessException, IllegalAccessException, SQLException{
        sqlServer=new Conexion(
                "192.168.1.70",
                "1433",
                "appdevel",
                "sa",
                "",
                "SQLSERVER"
                );
        return sqlServer.conectarBD();
    }
    
    public Conexion getOracle() {
        return oracle;
    }
    public void setOracle(Conexion oracle) {
        this.oracle = oracle;
    }
    public Conexion getMysql() {
        return mysql;
    }
    public void setMysql(Conexion mysql) {
        this.mysql = mysql;
    }
    public Conexion getPostgres() {
        return postgres;
    }
    public void setPostgres(Conexion postgres) {
        this.postgres = postgres;
    }
    
}


/* ObjetoConexion objCnx=new ObjetoConexion();
System.out.println("Postgres: "+objCnx.conectarPostgres());
System.out.println("Mysql: "+objCnx.conectarMysql());
System.out.println("Oracle: "+objCnx.conectarOracle());
System.out.println("SqlServer: "+objCnx.conectarSQLServer());        
}*/