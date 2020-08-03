package com.techelevator.dao;

import com.techelevator.model.Family;

public interface UserDAO {

    Family findByFamilyName(String username);

    boolean familyNameExists(String username);
    
    boolean create(String username, String password);
}
