package jdbcTemplate.main;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CustomerMapper implements RowMapper<Customer> {

	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return new Customer(rs.getInt("id"), 
				rs.getString("name"), 
				rs.getDouble("salary"), 
				rs.getString("city"));
	}

}
