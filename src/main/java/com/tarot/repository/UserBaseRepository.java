package com.tarot.repository;

import com.tarot.entity.tarot.TarotCardCategory;
import com.tarot.entity.user.UserBase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserBaseRepository extends JpaRepository<UserBase, Integer> {
    Optional<UserBase> findByEmail(String email);
}
