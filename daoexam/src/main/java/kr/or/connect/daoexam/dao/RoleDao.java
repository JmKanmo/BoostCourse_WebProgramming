package kr.or.connect.daoexam.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.daoexam.dto.Role;

@Repository
public class RoleDao {
	private final NamedParameterJdbcTemplate jdbc;
	private RowMapper<Role> rowMapper = BeanPropertyRowMapper.newInstance(Role.class);
	private final SimpleJdbcInsert insertAction;

	public RoleDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("role");
	}

	public List<Role> selectAll() {
		return jdbc.query(RoleDaoSqls.SELECT_ALL, Collections.emptyMap(), rowMapper);
	}

	public int insert(Role role) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(role);
		return insertAction.execute(params);
	}

	public int update(Role role) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(role);
		return jdbc.update(RoleDaoSqls.UPDATE, params);
	}

	public Role select_by_roleId(int roleId) {
		try {
			Map<String, ?> params = Collections.singletonMap("roleId", roleId);
			return jdbc.queryForObject(RoleDaoSqls.SELECT_BY_ROLE_ID, params, rowMapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public int delete_by_roleId(int roleId) {
		Map<String, ?> params = Collections.singletonMap("roleId", roleId);
		return jdbc.update(RoleDaoSqls.DELECT_BY_ROLE_ID, params);
	}
}
