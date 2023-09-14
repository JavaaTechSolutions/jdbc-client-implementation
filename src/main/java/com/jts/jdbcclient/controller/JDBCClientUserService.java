package com.jts.jdbcclient.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JDBCClientUserService {

	private final JdbcClient jdbcClient;

	public JDBCClientUserService(JdbcClient jdbcClient) {
		this.jdbcClient = jdbcClient;
	}

	public List<User> finalAll() {
		return jdbcClient.sql("select id, name, address from user_details ").query(User.class).list();
	}
	
	public List<User> finalAllByRowMapper() {
		return jdbcClient.sql("select id, name, address from user_details ")
				.query(new UserRowMapper()).list();
	}
	
	public Optional<User> finalById(String id) {
		return jdbcClient.sql("select id, name, address from user_details where id = :id ")
				.param("id", id)
				.query(User.class)
				.optional();
	}
	
	public void createdUser(User user) {
		var created = jdbcClient.sql("INSERT INTO user_details (id, name, address) values(?,?,?)")
		.params(List.of(user.id(), user.name(), user.address()))
		.update();
		
		System.out.println("No of User created" + created);
	}
	
	public void updateUser(User user, String id) {
		var created = jdbcClient.sql("update user_details  set name = ?, address = ? where id = ?")
		.params(List.of(user.name(), user.address(), id))
		.update();
		
		System.out.println("No of User updated" + created);
	}
	
	public void deleteUser(String id) {
		var created = jdbcClient.sql("delete from user_details  where id = :id")
				.param("id", id)
				.update();
		
		System.out.println("No of User deleted" + created);
	}

}

class UserRowMapper implements RowMapper<User> {
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new User(rs.getString("id"), rs.getString("name"), rs.getString("address"));
	}

}
