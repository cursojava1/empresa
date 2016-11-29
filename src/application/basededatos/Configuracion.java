package application.basededatos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Configuracion {

 	private static Properties p = new Properties();
	
	private static Properties getInstance() {
		try {
			p.load(Configuracion.class.getClassLoader().getResourceAsStream("config.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	public static String getAppSetting(String appSetting) {
		Properties prop = getInstance();
		return prop.getProperty(appSetting);
	}
}