package com.techelevator.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.techelevator.model.Family;

@Service
public class UserSqlDAO implements UserDAO {

    private JdbcTemplate jdbcTemplate;
    
    private static final String ALL_FIELDS = "family_id, family_name, password_hash";
    

    public UserSqlDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Family findByFamilyName(String familyName) throws UsernameNotFoundException {
    	String sql = "SELECT " + ALL_FIELDS + " FROM family WHERE family_name = ?";
    	SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, familyName);
    	if (rs.next()) {
    		return mapRowToUser(rs);
    	} else {
    		throw new UsernameNotFoundException("Family " + familyName + " was not found.");
    	}
    }
    
    @Override
    public boolean familyNameExists(String familyName) {
    	String sql = "SELECT 1 FROM family WHERE family_name = ?";
    	SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, familyName);
    	return rs.next();
    }

    @Override
    public boolean create(String familyName, String password) {
        boolean familyCreated = false;
        String sql = "INSERT INTO family (family_name,password_hash) VALUES (?,?)";
        String password_hash = new BCryptPasswordEncoder().encode(password);
//        String ssRole = "ROLE_" + role.toUpperCase();

        try {
        	int count = jdbcTemplate.update(sql, familyName, password_hash); //removed role
        	familyCreated = (count == 1);
        } catch (DataAccessException e) {
        	//userCreated remains false
        }
        return familyCreated;
    }

    private Family mapRowToUser(SqlRowSet rs) {
        Family user = new Family();
        user.setFamilyId(rs.getLong("family_id"));
        user.setFamilyName(rs.getString("family_name"));
        user.setPassword(rs.getString("password_hash"));
//        user.setAuthorities(rs.getString("role"));
        user.setActivated(true);
        return user;
    }
}
