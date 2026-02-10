package com.BuzzRx.Drug.Management.controller;

import com.BuzzRx.Drug.Management.service.DrugService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/drug")
public class DrugController {
    private static final Logger log= LoggerFactory.getLogger(DrugController.class);

    @Autowired
    private DrugService drugService;

}
