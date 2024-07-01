package com.tarot.repository;

import com.tarot.entity.tarot.TarotCardReadingMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarotCardReadingMethodRepository extends JpaRepository<TarotCardReadingMethod, Integer>{
}
