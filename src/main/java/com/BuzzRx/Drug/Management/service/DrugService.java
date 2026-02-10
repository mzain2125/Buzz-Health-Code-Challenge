package com.BuzzRx.Drug.Management.service;

import com.BuzzRx.Drug.Management.interfaces.DrugInterface;
import com.BuzzRx.Drug.Management.model.Drug;
import com.BuzzRx.Drug.Management.repo.DrugRepo;
import com.BuzzRx.Drug.Management.request.DrugRequest;
import com.BuzzRx.Drug.Management.response.DrugResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrugService implements DrugInterface {
    private static final Logger log= LoggerFactory.getLogger(DrugService.class);

    @Autowired
    private DrugRepo drugRepo;

    public DrugResponse saveDrug(DrugRequest drugRequest){
        Drug drug=new Drug();
        log.info("Drug save request receive from controller");
        BeanUtils.copyProperties(drugRequest,drug);
        Drug savedDrug = drugRepo.save(drug);
        DrugResponse drugResponse=new DrugResponse();
        BeanUtils.copyProperties(savedDrug,drugResponse);
        log.info("drug response send to controller");
        return drugResponse;
    }


}
