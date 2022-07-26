package com.itacademy.service.converters;

import com.itacademy.dao.entity.Role;
import com.itacademy.dao.entity.User;
import com.itacademy.dto.RoleName;
import com.itacademy.dto.UserRegistrationByAdmin;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationByAdminToUserConverter implements Converter<UserRegistrationByAdmin, User> {

  @Autowired
  PasswordEncoder encoder;

  @Override
  public User convert(UserRegistrationByAdmin userRegistrationByAdmin) {
    Set<Role> roles = new HashSet<>();

    for (RoleName roleName : userRegistrationByAdmin.getRole()) {
      if(roleName.getName().equals("ADMIN")) {
        roles.add(new Role(2L, "ADMIN"));
      }
      if(roleName.getName().equals("USER")) {
        roles.add(new Role(1L, "USER"));
      }
    }
    User user = new User();
    user.setUuid(UUID.randomUUID());
    user.setDtCreate(OffsetDateTime.now());
    user.setDtUpdate(user.getDtCreate());
    user.setUsername(userRegistrationByAdmin.getMail());
    user.setNick(userRegistrationByAdmin.getNick());
    user.setRoles(roles);
    user.setUserStatus(userRegistrationByAdmin.getUserStatus());
    user.setPassword(encoder.encode(userRegistrationByAdmin.getPassword()));
    user.setCredentialsNonExpired(true);
    user.setAccountNonExpired(true);
    user.setAccountNonLocked(true);
    user.setEnabled(true);

    return user;
  }
}
