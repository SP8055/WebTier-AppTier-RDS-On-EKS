package com.cricketshop.repository;

import com.cricketshop.model.CricketProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CricketProductRepository extends JpaRepository<CricketProduct, Long> {
}
