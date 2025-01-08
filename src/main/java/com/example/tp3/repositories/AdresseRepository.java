package com.example.tp3.repositories;

import com.example.tp3.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresseRepository extends CrudRepository<Address, Long> {
}
