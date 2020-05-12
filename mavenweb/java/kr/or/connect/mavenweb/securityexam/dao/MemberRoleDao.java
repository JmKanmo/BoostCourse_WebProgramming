package kr.or.connect.mavenweb.securityexam.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.mavenweb.securityexam.dto.MemberRole;

@Repository
public class MemberRoleDao {
	private NamedParameterJdbcTemplate jdbc;
	// BeanPropertyRowMapper는 Role클래스의 프로퍼티를 보고 자동으로 칼럼과 맵핑해주는 RowMapper객체를 생성한다.
	// roleId 프로퍼티는 role_id 칼럼과 맵핑이 된다.
	// 만약 프로퍼티와 칼럼의 규칙이 맞아 떨어지지 않는다면 직접 RowMapper객체를 생성해야 한다.
	// 생성하는 방법은 아래의 rowMapper2를 참고한다.
	private RowMapper<MemberRole> rowMapper = BeanPropertyRowMapper.newInstance(MemberRole.class);
	private SimpleJdbcInsert insertAction;
	private DataSource dataSoruce;

	public MemberRoleDao(DataSource dataSource) {
		this.dataSoruce = dataSource;
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<MemberRole> getRolesByEmail(String email) {
		Map<String, Object> map = new HashMap<>();
		map.put("email", email);
		return jdbc.query(MemberRoleDaoSqls.SELECT_ALL_BY_EMAIL, map, rowMapper);
	}

	public Long insertMemberRole(Long memberId, String admin, String user) {
		Long ret = 0L;
		SqlParameterSource params = new BeanPropertySqlParameterSource(
				new MemberRole(memberId, getUserState(admin, user)));
		this.insertAction = new SimpleJdbcInsert(this.dataSoruce).withTableName("member_role")
				.usingGeneratedKeyColumns("id");
		ret = this.insertAction.executeAndReturnKey(params).longValue();
		return ret;
	}

	private String getUserState(String admin, String user) {
		if (admin != null) {
			return "ROLE_ADMIN";
		} else if (user != null) {
			return "ROLE_USER";
		} else {
			return "ROLE_USER";
		}
	}
}