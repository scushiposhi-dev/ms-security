package com.scushiposhi.mssecurity.repositories;

import com.scushiposhi.mssecurity.entities.Wine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WineRepository extends JpaRepository<Wine,Long> {
}
