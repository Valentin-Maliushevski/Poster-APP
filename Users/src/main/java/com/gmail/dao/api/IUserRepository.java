package com.gmail.dao.api;

import com.gmail.dao.entity.User;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, UUID> {

  User findByUsername(String username);

  User findByNick(String nick);

  User findByUuid(UUID uuid);

  Page<User> findAll(Pageable pageable);

}