package com.scushiposhi.mssecurity.repositories.security;

import com.scushiposhi.mssecurity.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
