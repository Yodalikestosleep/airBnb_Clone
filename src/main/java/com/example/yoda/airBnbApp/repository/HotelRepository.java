package com.example.yoda.airBnbApp.repository;

import com.example.yoda.airBnbApp.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //This annotation is optional since we are already extending JPArepository
//But it is a good practice to use it
public interface HotelRepository extends JpaRepository<Hotel,Long> {
}
