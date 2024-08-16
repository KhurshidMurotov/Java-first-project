package com.company.firstprojectspring.repository;

import com.company.firstprojectspring.module.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    // todo: select c from cards where c.card_id = ?
    Card findByCardId(Integer cardId);


}
