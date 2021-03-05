package embeddedDB;
/* Datenbank-Initialisierung per Hand
 * mit Nutzung von Spring-Utility-Klassen
 */



import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public class DatabaseInitializer {

	public static void main(String[] args) throws IOException {

		//DB-Properties lesen
		final Properties properties = new Properties();
		properties.load(ClassLoader.getSystemResourceAsStream("db.properties"));
		String driver = properties.getProperty("db.driver");
		String url = properties.getProperty("db.url");
		String user = properties.getProperty("db.user");
		String pass = properties.getProperty("db.password");
		
		//System.out.println(driver);
		
		
		//Datasource erzeugen
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(pass);
		
		//Datasource initialisieren
		DataSourceInitializer initializer = new DataSourceInitializer();
	    initializer.setDataSource(dataSource);
	    initializer.setDatabasePopulator(databasePopulator());
	    initializer.afterPropertiesSet();  //wird typischerweise von Spring aufgerufen
	    
	    //Nutzung der initalisierten Datasource
	    JdbcTemplate template = new JdbcTemplate(dataSource);
		final String sql = "select job_title from jobs where job_id = ?";
		String job = template.query(sql, new Object[] { "AD_PRES" }, rs -> {
			if (!rs.next())
				return null;
			return rs.getString(1);
		});
		System.out.println(job);
		
		//Aufr�umen
		initializer.setDatabaseCleaner(databaseCleaner());
		initializer.destroy();
		
		}

	private static DatabasePopulator databasePopulator() {
	    final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
	    Resource schemaScript = new ClassPathResource("create.sql");
	    Resource dataScript = new ClassPathResource("populate.sql");
	    populator.addScript(schemaScript);
	    populator.addScript(dataScript);
	    return populator;
	}
	
	private static DatabasePopulator databaseCleaner() {
	    final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
	    Resource schemaScript = new ClassPathResource("drop.sql");
	    populator.addScript(schemaScript);
	    return populator;
	}
	
	

}
