package com.minka.tunel.controller;

import com.minka.tunel.domain.service.EnterpriseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EnterprisesController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private EnterpriseService enterpriseService;
}
