package com.scushiposhi.mssecurity.repositories.security;

import com.scushiposhi.mssecurity.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Long> {
}
