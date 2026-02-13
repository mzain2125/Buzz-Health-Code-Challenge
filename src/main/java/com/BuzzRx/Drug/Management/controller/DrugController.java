package com.BuzzRx.Drug.Management.controller;

import com.BuzzRx.Drug.Management.repo.DrugRepo;
import com.BuzzRx.Drug.Management.request.DrugRequest;
import com.BuzzRx.Drug.Management.response.DrugResponse;
import com.BuzzRx.Drug.Management.service.DrugService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/drug")
public class DrugController {
    private static final Logger log= LoggerFactory.getLogger(DrugController.class);

    @Autowired
    private DrugService drugService;
    @Autowired
    private DrugRepo drugRepo;

    @PostMapping
    public ResponseEntity<?> saveDrug(@RequestBody DrugRequest drugRequest){

        log.info("Drug is created with ths Name={}",drugRequest.getName());
        DrugResponse drugResponse=drugService.saveDrug(drugRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(drugResponse);

    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?>findByName(@PathVariable String name){
        List<DrugResponse> drugResponseList=drugService.findByName(name);
        return ResponseEntity.ok(drugResponseList);
    }

    @GetMapping("/ndc/{ndc}")
    public ResponseEntity<?>findByNdc(@PathVariable String ndc){
        DrugResponse drugResponse=drugService.findByNdc(ndc);
        return ResponseEntity.ok(drugResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>PutDrugById(@PathVariable UUID id,@RequestBody DrugRequest drugRequest){
        DrugResponse drugResponse=drugService.putDrugById(id,drugRequest);
        return ResponseEntity.ok(drugResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?>patchDrugById(@PathVariable UUID id,@RequestBody DrugRequest drugRequest){
        DrugResponse drugResponse=drugService.patchById(id,drugRequest);
        return ResponseEntity.ok(drugResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>dltById(@PathVariable UUID id){
        drugService.dltById(id);
       return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<?> dltAll(){
        drugService.dltAll();
        return ResponseEntity.ok("All Drugs Successfully Deleted");
    }

}
