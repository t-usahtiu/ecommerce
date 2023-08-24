package com.tus.ecommerce.dao;

import com.tus.ecommerce.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
public interface CityRepository extends JpaRepository<City, Integer> {

    @RestResource(path = "/by-country")
    @Query("SELECT city FROM City city WHERE city.country.code=?1")
    Page<City> findByCountryCode(@Param("code") String code, Pageable pageable);

}
