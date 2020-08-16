package com.hqyj.cyj.moudls.test.repository;

import com.hqyj.cyj.moudls.test.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {

}
