package sk.train;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import domain.Job;



public class Application {
	public static void main(String[] args) {
		try (final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class)) {
			final JdbcTemplate template = ctx.getBean(JdbcTemplate.class);
			try {
				demo(template);
			}
			catch (final DataRetrievalFailureException e) {
				System.out.println(e);
			}
			catch (final DataIntegrityViolationException e) {
				System.out.println(e);
			}
			catch (final Exception e) {
				System.out.println(e);
			}
		}
	}

	private static void demo(final JdbcTemplate template) {
		Job j = new Job();
		j.setJobid("Kars_Job");
		j.setJobtitle("Dozent");
		j.setMaxsal(10000);
		j.setMinsal(1000);
		insertJob(template, j);
 
		final Job job1 = findJob(template, "ST_CLERK");
		System.out.println(job1);

		final Job job2 = findJob(template, "AD_PRES");
		System.out.println(job2);
		
		System.out.println("Jetzt alle: *********************************************************");
		for (final Job job : findAllJobs(template))
			System.out.println(job);
 
	}

	private static void insertJob(JdbcTemplate template, Job job) {
		final String sql = "insert into jobs values (?, ?, ?, ?)";
		final Object result = template.update(sql, job.getJobid(), job.getJobtitle(),
				                                   job.getMinsal(), job.getMaxsal());
		if ((Integer) result != 1)
			throw new DataRetrievalFailureException(job.toString());
	}

	

	private static RowMapper<Job> mapper = (rs, rowNumber) -> {
		final Job a = new Job();
		a.setJobid(rs.getString(1));
		a.setJobtitle(rs.getString(2));
		a.setMaxsal(rs.getInt(4));
		a.setMinsal(rs.getInt(3));
		return a;
	};

	private static Job findJob(JdbcTemplate template, String jobid) {
		final String sql = "select * from jobs where job_id = ?";
		return template.queryForObject(sql, mapper, jobid);
	}

	private static List<Job> findAllJobs(JdbcTemplate template) {
		final String sql = "select * from jobs";
		return template.query(sql, mapper);
	}
}

