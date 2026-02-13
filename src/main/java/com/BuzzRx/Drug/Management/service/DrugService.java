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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DrugService implements DrugInterface {
    private static final Logger log= LoggerFactory.getLogger(DrugService.class);

    @Autowired
    private DrugRepo drugRepo;

    public DrugResponse saveDrug(DrugRequest drugRequest){
        if(drugRepo.existsByNdc(drugRequest.getNdc())){
            throw new RuntimeException("Ndc must be Unique");
        }
        Drug drug=new Drug();
        log.info("Drug save request receive from controller");
        BeanUtils.copyProperties(drugRequest,drug);
        Drug savedDrug = drugRepo.save(drug);
        DrugResponse drugResponse=new DrugResponse();
        BeanUtils.copyProperties(savedDrug,drugResponse);
        log.info("drug response send to controller");
        return drugResponse;
    }

    public List<DrugResponse> findByName(String name){
        List<Drug> drugList=drugRepo.findByNameContainingIgnoreCase(name);
        if(drugList.isEmpty()){
            throw new RuntimeException("Drug not available");
        }
        List<DrugResponse> drugResponseList=new ArrayList<>();
        for(Drug drug:drugList){
            DrugResponse drugResponse=new DrugResponse();
            BeanUtils.copyProperties(drug,drugResponse);
            drugResponseList.add(drugResponse);
        }
        return drugResponseList;
    }

    public DrugResponse findByNdc(String ndc){
        Drug drug=drugRepo.findByNdc(ndc);
        if(drug==null){
           throw new RuntimeException("Drug not Found with this ndc: "+ ndc);
        }
        DrugResponse drugResponse=new DrugResponse();
        BeanUtils.copyProperties(drug,drugResponse);
        return drugResponse;
    }

    public DrugResponse putDrugById(UUID id,DrugRequest drugRequest){
        Drug drug=drugRepo.findById(id).orElseThrow(()->new RuntimeException("Drug not found with this id: "+id));
        BeanUtils.copyProperties(drugRequest,drug);
        Drug updateDrug=drugRepo.save(drug);
        DrugResponse drugResponse=new DrugResponse();
        BeanUtils.copyProperties(updateDrug,drugResponse);
        return drugResponse;
    }

    public DrugResponse patchById(UUID id,DrugRequest drugRequest){
        Drug drug=drugRepo.findById(id).orElseThrow(()->new RuntimeException("Drug not found with this id:"+id));
        if(drugRequest.getName()!=null){
            drug.setName(drugRequest.getName());
        }
        if(drugRequest.getNdc()!=null){
            if (drugRepo.existsByNdc(drugRequest.getNdc())
                    && !drug.getNdc().equals(drugRequest.getNdc())) {
                throw new RuntimeException("NDC already exists");
            }
            drug.setNdc(drugRequest.getNdc());
        }
        if(drugRequest.getForm()!=null){
            drug.setForm(drugRequest.getForm());
        }
        if(drugRequest.getStrength()!=null){
            drug.setStrength(drugRequest.getStrength());
        }
        Drug updatedDrug=drugRepo.save(drug);
        DrugResponse drugResponse=new DrugResponse();
        BeanUtils.copyProperties(updatedDrug,drugResponse);
        return drugResponse;
    }

    public void dltById(UUID id){
        Drug drug=drugRepo.findById(id).orElseThrow(()->new RuntimeException("Drug don't exist"));
        drugRepo.delete(drug);
    }

    public void dltAll(){
        drugRepo.deleteAll();
    }

}
