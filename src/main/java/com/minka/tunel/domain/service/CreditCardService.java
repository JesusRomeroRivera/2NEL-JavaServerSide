package com.minka.tunel.domain.service;

import com.minka.tunel.domain.model.CreditCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CreditCardService {
    Page<CreditCard> getAllCreditCards(Pageable pageable);
    CreditCard getCreditCardByUserId(Long userId);
    CreditCard createCreditCard(CreditCard creditCard);
    CreditCard updateCreditCard(Long userId, CreditCard creditCard);
    ResponseEntity<?> deleteCreditCard(Long userId);
}
