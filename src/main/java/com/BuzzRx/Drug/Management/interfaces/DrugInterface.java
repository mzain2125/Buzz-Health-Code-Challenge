package com.BuzzRx.Drug.Management.interfaces;

import com.BuzzRx.Drug.Management.request.DrugRequest;
import com.BuzzRx.Drug.Management.response.DrugResponse;

import java.util.List;
import java.util.UUID;

public interface DrugInterface {
    DrugResponse saveDrug(DrugRequest drugRequest);
    List<DrugResponse> findByName(String name);
    DrugResponse findByNdc(String ndc);
    DrugResponse putDrugById(UUID id, DrugRequest drugRequest);
    DrugResponse patchById(UUID id,DrugRequest drugRequest);

}
