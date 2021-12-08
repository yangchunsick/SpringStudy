package com.koreait.ex08.repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.koreait.ex08.domain.Contact;

public class ContactRepository {

		// Spring jdbc 이용을 위해서 JdbcTemplate 클래스 사용
		// JdbcTemplate 내부에서 Connection, PreparedStatement, ResultSet 사용
		
		@Autowired
		private JdbcTemplate template;
		private String sql;
		
		/* 연락처 목록 보여주기 */
		public List<Contact> selectContactList() {
			sql = "SELECT NO, NAME, TEL, ADDRESS, BIRTHDAY FROM CONTACT";
			return template.query(sql, new BeanPropertyRowMapper<Contact>(Contact.class));
		}
		
		/* 연락처 추가 */
		public int insertContact(Contact contact) {
			return template.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					// 매개변수 Connection con을 이용해서 PreparedStatement ps 만들어서 반환함.
					sql = "INSERT INTO CONTACT VALUES (CONTACT_SEQ.NEXTVAL, ?, ?, ?, ?)";
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, contact.getName());
					ps.setString(2, contact.getTel());
					ps.setString(3, contact.getAddress());
					ps.setString(4, contact.getBirthday());
					return ps;
				}
			});
		}
		
		/* 연락처 상세 보기 */
		public Contact selectContactByNo(Contact contact) {
			sql = "SELECT NO, NAME, TEL, ADDRESS, BIRTHDAY FROM CONTACT WHERE NO = ?";
			//template.queryForObject(sql, requiredType, args)
			//						  sql문에 rowMappmer안에 전달 받을 "?"
			return template.queryForObject(sql, new BeanPropertyRowMapper<Contact>(Contact.class), contact.getNo());
			
		}
		
		/* 수정 */
		public int updateContact(Contact contact) {
			sql = "UPDATE CONTACT SET TEL = ?, ADDRESS = ?, BIRTHDAY = ? WHERE NO = ?";
			return template.update(sql, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					// 매개변수 PreparedStatement ps에 ? 값 채우기
					ps.setString(1, contact.getTel());
					ps.setString(2, contact.getAddress());
					ps.setString(3, contact.getBirthday());
					ps.setInt(4, contact.getNo());
				}
			});
		}
		
		/* 삭제 */
		public int deleteContact(Contact contact) {
			sql = "DELETE FROM CONTACT WHERE NO = ?";
			return template.update(sql, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, contact.getNo());					
				}
			});
		}
		
	
	
	
}
