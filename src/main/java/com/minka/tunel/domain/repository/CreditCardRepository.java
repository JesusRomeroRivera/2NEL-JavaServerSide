package com.minka.tunel.domain.repository;

import com.minka.tunel.domain.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    @Query(value = "SELECT * FROM credit_cards cc WHERE cc.user = :user",nativeQuery = true)
    Optional<Enterprise> getCreditCardByUserID(@Param("user") User user);
}
