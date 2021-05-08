package com.minka.tunel.controller;

import com.minka.tunel.domain.model.CreditCard;
import com.minka.tunel.domain.model.Tag;
import com.minka.tunel.domain.service.CreditCardService;
import com.minka.tunel.domain.service.TagService;
import com.minka.tunel.resource.CreditCardResource;
import com.minka.tunel.resource.SaveCreditCardResource;
import com.minka.tunel.resource.SaveTagResource;
import com.minka.tunel.resource.TagResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CreditCardsController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CreditCardService creditCardService;

    @GetMapping("/creditcards")
    public Page<CreditCardResource> getAllCreditCards(Pageable pageable) {
        List<CreditCardResource> creditCards = creditCardService.getAllCreditCards(pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int creditCardsCount = creditCards.size();
        return new PageImpl<>(creditCards, pageable, creditCardsCount);
    }

    @GetMapping("/creditcards/{creditCardId}")
    public CreditCardResource getCreditCardByUserId(
            @PathVariable Long creditCardId) {
        return convertToResource(creditCardService.getCreditCardByUserId(creditCardId));
    }

    @PostMapping("/creditcards")
    public CreditCardResource createCreditCard(
            @Valid @RequestBody SaveCreditCardResource resource){
        return convertToResource(creditCardService.createCreditCard(convertToEntity(resource)));
    }

    @PutMapping("/creditcards/{creditCardId}")
    public CreditCardResource updateCreditCard(
            @PathVariable Long creditCardId,
            @Valid @RequestBody SaveCreditCardResource resource) {
        return convertToResource(creditCardService.updateCreditCard(creditCardId, convertToEntity(resource)));
    }

    private CreditCard convertToEntity(SaveCreditCardResource resource) {
        return mapper.map(resource, CreditCard.class);
    }

    private CreditCardResource convertToResource(CreditCard entity) {
        return mapper.map(entity, CreditCardResource.class);
    }
}
