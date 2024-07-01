package com.tarot.repository;

import com.tarot.entity.tarot.TarotCardCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarotCardCategoryRepository extends JpaRepository<TarotCardCategory, Character>{
}
