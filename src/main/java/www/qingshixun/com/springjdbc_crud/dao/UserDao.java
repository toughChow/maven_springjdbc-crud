package www.qingshixun.com.springjdbc_crud.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import www.qingshixun.com.springjdbc_crud.domain.User;

@Repository
public class UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 保存用户信息
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public int saveUser(String username, String password) {
		String sql = "insert into qsx_user(username,password) values(?,?);";
		return jdbcTemplate.update(sql, username, password);
	}

	/**
	 * 通过username查询一条记录
	 * 
	 * @param username
	 * @return
	 */
	public User getUser(String username) {
		String sql = "select * from qsx_user where username=?";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
	}

	/**
	 * 获取所有用户信息
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getAllUser() {
		String sql = "select * from qsx_user";
		return jdbcTemplate.queryForList(sql);
	}

	/**
	 * 根据username删除某条记录
	 * @param username
	 * @return
	 */
	public int deleteUser(String username) {
		String sql = "delete from qsx_user where username = ?";
		return jdbcTemplate.update(sql, username);
	}
}
