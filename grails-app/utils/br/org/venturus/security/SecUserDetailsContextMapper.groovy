/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.venturus.security

import java.util.Collection;

import grails.plugin.springsecurity.SpringSecurityUtils;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.org.venturus.helper.ProjectHelper;
import br.org.venturus.sorat.Project;
import br.org.venturus.sorat.security.SecUser;

public class SecUserDetailsContextMapper implements UserDetailsContextMapper {
	
  def dataSource;

  @Override
  public UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<? extends GrantedAuthority> authorities) {
    def secUser = null;
    def secUserAuthorityList = [new SimpleGrantedAuthority(SpringSecurityUtils.NO_ROLE)];
    SecUser.withTransaction { status ->
      secUser = SecUser.findByUsername(username);
      secUserAuthorityList = secUser.authorities.collect{it -> new SimpleGrantedAuthority(it.authority)};
    }
    if (!secUser){
      throw new UsernameNotFoundException("User not Found...");
    }
    return new SecUserDetails(username, secUser.enabled, secUser.accountExpired, secUser.passwordExpired, 
      secUser.accountLocked, secUserAuthorityList, secUser.name, secUser.email, secUser.photo);
  }

  @Override
  public void mapUserToContext(UserDetails user, DirContextAdapter ctx) {
    // TODO Auto-generated method stub
  }
}

