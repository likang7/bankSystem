package bankSystem.persistence.dao.sqlimpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper {
	public Object mapRow(ResultSet rs) throws SQLException;
}
