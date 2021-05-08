package com.minka.tunel.controller;

import com.minka.tunel.domain.model.Entrepreneur;
import com.minka.tunel.domain.model.Investor;
import com.minka.tunel.domain.service.InvestorService;
import com.minka.tunel.resource.EntrepreneurResource;
import com.minka.tunel.resource.InvestorResource;
import com.minka.tunel.resource.SaveEntrepreneurResource;
import com.minka.tunel.resource.SaveInvestorResource;
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
public class InvestorsController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private InvestorService investorService;

    @GetMapping("/investors")
    public Page<InvestorResource> getAllInvestors(Pageable pageable) {
        List<InvestorResource> investors = investorService.getAllInvestors(pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int investorCount = investors.size();
        return new PageImpl<>(investors, pageable, investorCount);
    }

    @GetMapping("/investors/{userId}")
    public InvestorResource getInvestorById(
            @PathVariable Long userId) {
        return convertToResource(investorService.getInvestorById(userId));
    }

    @PostMapping("/investors")
    public InvestorResource createInvestor(
            @Valid @RequestBody SaveInvestorResource resource){
        return convertToResource(investorService.createInvestor(convertToEntity(resource)));
    }

    @PutMapping("/investors/{userId}")
    public InvestorResource updateInvestor(
            @PathVariable Long userId,
            @Valid @RequestBody SaveInvestorResource resource) {
        return convertToResource(investorService.updateInvestor(userId, convertToEntity(resource)));
    }

    private Investor convertToEntity(SaveInvestorResource resource) {
        return mapper.map(resource, Investor.class);
    }

    private InvestorResource convertToResource(Investor entity) {
        return mapper.map(entity, InvestorResource.class);
    }
}
