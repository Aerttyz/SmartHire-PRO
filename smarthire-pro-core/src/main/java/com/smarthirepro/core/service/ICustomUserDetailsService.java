package com.smarthirepro.core.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface ICustomUserDetailsService {
  public UserDetails loadUserByUsername(String username);
}
