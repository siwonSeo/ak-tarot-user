package com.tarot.repository;

import com.tarot.entity.user.UserBase;
import com.tarot.entity.user.UserBaseInterpretation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserBaseInterpretationRepository extends JpaRepository<UserBaseInterpretation, Integer> {
}
