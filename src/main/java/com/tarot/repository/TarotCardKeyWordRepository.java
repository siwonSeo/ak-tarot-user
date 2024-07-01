package com.tarot.repository;

import com.tarot.entity.tarot.TarotCardKeyWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarotCardKeyWordRepository extends JpaRepository<TarotCardKeyWord, Integer>{
}
