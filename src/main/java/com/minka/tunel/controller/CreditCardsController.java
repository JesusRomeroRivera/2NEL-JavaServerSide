package com.minka.tunel.controller;

import com.minka.tunel.domain.model.CreditCard;
import com.minka.tunel.domain.service.CreditCardService;
import com.minka.tunel.resource.CreditCardResource;
import com.minka.tunel.resource.SaveCreditCardResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CreditCardsController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CreditCardService creditCardService;

    private CreditCard convertToEntity(SaveCreditCardResource resource) {
        return mapper.map(resource, CreditCard.class);
    }

    private CreditCardResource convertToResource(CreditCard entity) {
        return mapper.map(entity, CreditCardResource.class);
    }
}
