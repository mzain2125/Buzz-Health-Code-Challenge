package com.BuzzRx.Drug.Management.controller;

import com.BuzzRx.Drug.Management.model.Drug;
import com.BuzzRx.Drug.Management.repo.DrugRepo;
import com.BuzzRx.Drug.Management.request.DrugRequest;
import com.BuzzRx.Drug.Management.response.BaseResponse;
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

    @Autowired
    private DrugService drugService;
    @Autowired
    private DrugRepo drugRepo;

    @PostMapping
    public ResponseEntity<?> saveDrug(@RequestBody DrugRequest drugRequest){
        DrugResponse drugResponse=drugService.saveDrug(drugRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponse.successResponse(true,"Successfully Created",HttpStatus.CREATED.value(),drugResponse));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?>findByName(@PathVariable String name){
        List<DrugResponse> drugResponseList=drugService.findByName(name);
        return ResponseEntity.ok(BaseResponse.successResponse(true,"get successfully",HttpStatus.OK.value(),drugResponseList));
    }

    @GetMapping("/ndc/{ndc}")
    public ResponseEntity<?>findByNdc(@PathVariable String ndc){
        DrugResponse drugResponse=drugService.findByNdc(ndc);
        return ResponseEntity.ok(BaseResponse.successResponse(true,"Get successfully",HttpStatus.OK.value(),drugResponse));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>PutDrugById(@PathVariable UUID id,@RequestBody DrugRequest drugRequest){
        DrugResponse drugResponse=drugService.putDrugById(id,drugRequest);
        return ResponseEntity.ok(BaseResponse.successResponse(true,"Changes done Successfully",HttpStatus.OK.value(), drugResponse));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?>patchDrugById(@PathVariable UUID id,@RequestBody DrugRequest drugRequest){
        DrugResponse drugResponse=drugService.patchById(id,drugRequest);
        return ResponseEntity.ok(BaseResponse.successResponse(true,"Changes done Successfully",HttpStatus.OK.value(), drugResponse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>dltById(@PathVariable UUID id){
       drugService.dltById(id);
       return ResponseEntity.ok(BaseResponse.successResponse(true,"Successfully Deleted",HttpStatus.OK.value(), null));
    }

    @DeleteMapping
    public ResponseEntity<?> dltAll(){
        drugService.dltAll();
        return ResponseEntity.ok(BaseResponse.successResponse(true,"Successfully Deleted",HttpStatus.OK.value(), null));
    }

}
