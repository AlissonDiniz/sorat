/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.venturus.security

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class SecUserDetails extends User {
	
  private String name;
  private String email;
  private String photo;

  public SecUserDetails(String username, boolean enabled, boolean accountExpired, boolean passwordExpired, 
    boolean accountLocked, Collection<? extends GrantedAuthority> authorities, String name, String email, String photo) {
    
    super(username, username, enabled, accountExpired, passwordExpired, accountLocked, authorities);
    this.name = name;
    this.email = email;
    this.photo = photo;
  }
	
  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }
  
  public String getPhoto() {
    return photo;
  }
  
}

