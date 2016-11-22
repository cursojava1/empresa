package application.basededatos;
import java.net.URL;

public class Ruta {
    private final String dbOracle="/EmpresaFX/src/application/basededatos/dboracle.properties";
    private final String dbMysql="/EmpresaFX/src/application/basededatos/dbmysql.properties";
    private final String dbPostgres="/EmpresaFX/src/application/basededatos/dbpostgres.properties";
    private final String dbSqlServer="/EmpresaFX/src/application/basededatos/dbsqlserver.properties";
    
    public URL getFileDbOracle(){
        return getClass().getResource(dbOracle);
    }
    
    public URL getFileDbMysql(){
        return getClass().getResource(dbMysql);
    }
    
    public URL getFileDbPostgres(){
        return getClass().getResource(dbPostgres);
    }
    
    public URL getFileDbSqlServer(){
        return getClass().getResource(dbSqlServer);
    }
}