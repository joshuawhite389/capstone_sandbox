package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Family {

   private Long familyId;
   private String familyName;
   @JsonIgnore
   private String password;
   @JsonIgnore
   private boolean activated;
   private Set<Authority> authorities = new HashSet<>();
   
   public Family() { }

   public Family(Long id, String username, String password, String authorities) {
      this.familyId = id;
      this.familyName = username;
      this.password = password;
      this.activated = true;
   }

   

   public Set<Authority> getAuthorities() {
      return authorities;
   }

   public void setAuthorities(Set<Authority> authorities) {
      this.authorities = authorities;
   }

   public void setAuthorities(String authorities) {
      String[] roles = authorities.split(",");
      for(String role : roles) {
         String authority = role.contains("ROLE_") ? role : "ROLE_" + role;
         this.authorities.add(new Authority(authority));
      }
   }
 
   public Long getFamilyId() {
	return familyId;
}

public void setFamilyId(Long familyId) {
	this.familyId = familyId;
}

public String getFamilyName() {
	return familyName;
}

public void setFamilyName(String familyName) {
	this.familyName = familyName;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public boolean isActivated() {
	return activated;
}

public void setActivated(boolean activated) {
	this.activated = activated;
}

@Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Family user = (Family) o;
      return familyId == user.familyId &&
              activated == user.activated &&
              Objects.equals(familyName, user.familyName) &&
              Objects.equals(password, user.password)
              && Objects.equals(authorities, user.authorities);
   }

   @Override
   public int hashCode() {
      return Objects.hash(familyId, familyName, password, activated, authorities); 
   }

   @Override
   public String toString() {
      return "User{" +
              "id=" + familyId +
              ", username='" + familyName + '\'' +
              ", activated=" + activated +
              ", authorities=" + authorities +
              '}';

   }
}
