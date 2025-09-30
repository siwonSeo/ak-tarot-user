package com.tarot.repository;

import com.tarot.entity.tarot.TarotCardInterpretation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarotCardInterpretationRepository extends JpaRepository<TarotCardInterpretation, Integer>{
}
