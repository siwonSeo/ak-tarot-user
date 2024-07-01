package com.tarot.repository;

import com.tarot.entity.tarot.TarotCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarotCardRepository extends JpaRepository<TarotCard, Integer>, TarotCardRepositoryCustom{
}
