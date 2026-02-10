package com.BuzzRx.Drug.Management.interfaces;

import com.BuzzRx.Drug.Management.request.DrugRequest;
import com.BuzzRx.Drug.Management.response.DrugResponse;

public interface DrugInterface {
    DrugResponse saveDrug(DrugRequest drugRequest);
}
