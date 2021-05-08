package com.minka.tunel.service;

import com.minka.tunel.domain.model.CreditCard;
import com.minka.tunel.domain.repository.CreditCardRepository;
import com.minka.tunel.domain.service.CreditCardService;
import com.minka.tunel.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Override
    public Page<CreditCard> getAllCreditCards(Pageable pageable) {
        return creditCardRepository.findAll(pageable);
    }

    @Override
    public CreditCard getCreditCardByUserId(Long userId) {
        return creditCardRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Tag", "Id", userId));
    }

    @Override
    public CreditCard createCreditCard(CreditCard creditCard) {
        return creditCardRepository.save(creditCard);
    }

    @Override
    public CreditCard updateCreditCard(Long userId, CreditCard creditCard) {
        return creditCardRepository.findById(userId)
                .map(creditCard1 -> {
                    creditCard1.setCardNumber(creditCard.getCardNumber());
                    creditCard1.setCvv(creditCard.getCvv());
                    creditCard1.setExpMonth(creditCard.getExpMonth());
                    creditCard1.setExpYear(creditCard.getExpYear());
                    return creditCardRepository.save(creditCard1);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Credit Card", "Id", userId));
    }

    @Override
    public ResponseEntity<?> deleteCreditCard(Long userId) {
        return creditCardRepository.findById(userId)
                .map(creditCard -> {
                    creditCardRepository.delete(creditCard);
                    return (ResponseEntity.ok().build());
                })
                .orElseThrow(() -> new ResourceNotFoundException("Credit Card", "Id", userId));    }
}
