package embeddedDB;
/* EmbeddedDatabaseBuilder funktioniert mit H2, aber leider nicht mit Derby.
 *(Doku sagt: sollte funktionieren)
 */




import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;


public class EmbeddedH2 {

	public static void main(String[] args) {

		DataSource dbEmbedded = new EmbeddedDatabaseBuilder()
		        .setName("KarrerDB")
		        .setType(EmbeddedDatabaseType.H2)
		        .addScript("classpath:create.sql")
		        .addScript("classpath:populate.sql")
		        .build();
		
		JdbcTemplate template = new JdbcTemplate(dbEmbedded);
		final String sql = "select job_title from jobs where job_id = ?";
		String job = template.query(sql, new Object[] { "AD_PRES" }, rs -> {
			if (!rs.next())
				return null;
			return rs.getString(1);
		});
		System.out.println(job);
		
		((EmbeddedDatabase) dbEmbedded).shutdown();
		
		

	}

}
