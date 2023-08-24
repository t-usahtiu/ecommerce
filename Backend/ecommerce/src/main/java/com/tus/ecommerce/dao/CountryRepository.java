package com.tus.ecommerce.dao;

import com.tus.ecommerce.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface   CountryRepository extends JpaRepository<Country, Integer> {
}
