package dao;

import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;


public class UsersDAOImpl implements UsersDAO {
	private JdbcTemplate jdbcTemplate;
	 
    public UsersDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

	@Override
	public String getPassword(String username) {
		return jdbcTemplate.queryForObject("SELECT password FROM users WHERE username = ?", new Object[] {
				username
		}, String.class);
	}
	
	@Override
	public Long allUsersCount() {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Long.class);
	}

}
