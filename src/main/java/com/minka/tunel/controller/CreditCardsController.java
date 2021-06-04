package com.minka.tunel.controller;

import com.minka.tunel.domain.model.CreditCard;
import com.minka.tunel.domain.service.CreditCardService;
import com.minka.tunel.resource.CreditCardResource;
import com.minka.tunel.resource.SaveCreditCardResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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

    @Operation(summary = "Get Enterprises", description = "Get All Enterprises", tags = {"enterprises"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Enterprises returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/credit-cards")
    public Page<CreditCardResource> getAllCreditCards(Pageable pageable) {
        List<CreditCardResource> creditCards = creditCardService.getAllCreditCards(pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int creditCardsCount = creditCards.size();
        return new PageImpl<>(creditCards, pageable, creditCardsCount);
    }

    @Operation(summary = "Get Enterprise by ID", description = "Get a specific Enterprise by its ID", tags = {"enterprises"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enterprise returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/credit-cards/{creditCardId}")
    public CreditCardResource getCreditCardById(
            @PathVariable Long creditCardId) {
        return convertToResource(creditCardService.getCreditCardByUserId(creditCardId));
    }

    @Operation(summary = "Create an Enterprise", description = "Create an Enterprise", tags = {"enterprises"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enterprise created", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/credit-cards")
    public CreditCardResource createCreditCard(
            Long userId,
            @Valid @RequestBody SaveCreditCardResource resource){
        return convertToResource(creditCardService.createCreditCard(userId, convertToEntity(resource)));
    }

    @Operation(summary = "Update an Enterprise", description = "Update an Farmland", tags = {"enterprises"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enterprise updated", content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/credit-cards/{creditCardId}")
    public CreditCardResource updateCreditCard(
            @PathVariable Long creditCardId,
            @Valid @RequestBody SaveCreditCardResource resource) {
        return convertToResource(creditCardService.updateCreditCard(creditCardId, convertToEntity(resource)));
    }

    @Operation(summary = "Delete an Enterprise", description = "Delete an Enterprise", tags = {"enterprises"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enterprise deleted", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/credit-cards/{creditCardId}")
    public ResponseEntity<?> deleteCreditCard(@PathVariable Long creditCardId) {
        return creditCardService.deleteCreditCard(creditCardId);
    }

    private CreditCard convertToEntity(SaveCreditCardResource resource) {
        return mapper.map(resource, CreditCard.class);
    }

    private CreditCardResource convertToResource(CreditCard entity) {
        return mapper.map(entity, CreditCardResource.class);
    }
}
