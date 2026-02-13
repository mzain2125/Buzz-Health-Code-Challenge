package com.BuzzRx.Drug.Management.service;

import com.BuzzRx.Drug.Management.interfaces.PharmacyInterface;
import com.BuzzRx.Drug.Management.model.Drug;
import com.BuzzRx.Drug.Management.model.Pharmacy;
import com.BuzzRx.Drug.Management.repo.PharmacyRepo;
import com.BuzzRx.Drug.Management.request.DrugRequest;
import com.BuzzRx.Drug.Management.request.PharmacyRequest;
import com.BuzzRx.Drug.Management.response.DrugResponse;
import com.BuzzRx.Drug.Management.response.PharmacyResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PharmacyService implements PharmacyInterface {
    @Autowired
    PharmacyRepo pharmacyRepo;

    public PharmacyResponse savePharmacy(PharmacyRequest pharmacyRequest){
        if(pharmacyRepo.existsByNpi(pharmacyRequest.getNpi())){
            throw new RuntimeException("npi is already exist");
        }
        Pharmacy pharmacy=new Pharmacy();
        BeanUtils.copyProperties(pharmacyRequest,pharmacy);
        pharmacyRepo.save(pharmacy);
        PharmacyResponse pharmacyResponse=new PharmacyResponse();
        BeanUtils.copyProperties(pharmacy,pharmacyResponse);
        return pharmacyResponse;
    }

    public PharmacyResponse getPharmacyById(UUID id){
        Pharmacy pharmacy=pharmacyRepo.findById(id).orElseThrow(()->new RuntimeException("Pharmacy not found with id: " + id));
        PharmacyResponse pharmacyResponse=new PharmacyResponse();
        BeanUtils.copyProperties(pharmacy,pharmacyResponse);
        return pharmacyResponse;
    }

    public List<PharmacyResponse> getPharmacyByName(String name){
        List<Pharmacy> pharmacies=pharmacyRepo.findByName(name);
        if(pharmacies.isEmpty()){
            throw new RuntimeException("Pharmacy not found");
        }
        List<PharmacyResponse> responseList=new ArrayList<>();
        for(Pharmacy pharmacy:pharmacies){
            PharmacyResponse pharmacyResponse=new PharmacyResponse();
            BeanUtils.copyProperties(pharmacy,pharmacyResponse);
            responseList.add(pharmacyResponse);
        }
        return responseList;
    }

    public List<PharmacyResponse> getPharmacyByAddress(String address){
        List<Pharmacy> pharmacies=pharmacyRepo.findByAddress(address);
        if(pharmacies.isEmpty()){
            throw new RuntimeException("Pharmacy not found");
        }
        List<PharmacyResponse> responseList=new ArrayList<>();
        for(Pharmacy pharmacy:pharmacies){
            PharmacyResponse pharmacyResponse=new PharmacyResponse();
            BeanUtils.copyProperties(pharmacy,pharmacyResponse);
            responseList.add(pharmacyResponse);
        }
        return responseList;
    }

    public List<PharmacyResponse> getPharmacyByCity(String city){
        List<Pharmacy> pharmacies=pharmacyRepo.findByCity(city);
        if(pharmacies.isEmpty()){
            throw new RuntimeException("Pharmacy not found");
        }
        List<PharmacyResponse> responseList=new ArrayList<>();
        for(Pharmacy pharmacy:pharmacies){
            PharmacyResponse pharmacyResponse=new PharmacyResponse();
            BeanUtils.copyProperties(pharmacy,pharmacyResponse);
            responseList.add(pharmacyResponse);
        }
        return responseList;
    }

    public PharmacyResponse putPharmacyById(UUID id, PharmacyRequest pharmacyRequest){
        Pharmacy pharmacy=pharmacyRepo.findById(id).orElseThrow(()->new RuntimeException("Pharmacy not found with this id: "+id));
        BeanUtils.copyProperties(pharmacyRequest,pharmacy);
        Pharmacy updatePharmacy=pharmacyRepo.save(pharmacy);
        PharmacyResponse pharmacyResponse=new PharmacyResponse();
        BeanUtils.copyProperties(updatePharmacy,pharmacyResponse);
        return pharmacyResponse;
    }

    public PharmacyResponse patchById(UUID id,PharmacyRequest pharmacyRequest){
        Pharmacy pharmacy=pharmacyRepo.findById(id).orElseThrow(()->new RuntimeException("Pharmacy not found with this id:"+id));
        if(pharmacyRequest.getName()!=null){
            pharmacy.setName(pharmacyRequest.getName());
        }
        if(pharmacyRequest.getNpi()!=null){
            if (pharmacyRepo.existsByNpi(pharmacyRequest.getNpi())
                    && !pharmacy.getNpi().equals(pharmacyRequest.getNpi())) {
                throw new RuntimeException("NPI already exists");
            }
            pharmacy.setNpi(pharmacyRequest.getNpi());
        }
        if(pharmacyRequest.getCity()!=null){
            pharmacy.setCity(pharmacyRequest.getCity());
        }
        if(pharmacyRequest.getAddress()!=null){
            pharmacy.setAddress(pharmacyRequest.getAddress());
        }
        if(pharmacyRequest.getState()!=null){
            pharmacy.setState(pharmacyRequest.getState());
        }
        if(pharmacyRequest.getZip()!=null){
            pharmacy.setZip(pharmacyRequest.getZip());
        }
        Pharmacy updatePharmacy=pharmacyRepo.save(pharmacy);
        PharmacyResponse pharmacyResponse=new PharmacyResponse();
        BeanUtils.copyProperties(updatePharmacy,pharmacyResponse);
        return pharmacyResponse;
    }

    public void dltById(UUID id){
        Pharmacy pharmacy=pharmacyRepo.findById(id).orElseThrow(()->new RuntimeException("Drug don't exist"));
        pharmacyRepo.delete(pharmacy);
    }

    public void dltAll(){
        pharmacyRepo.deleteAll();
    }
}
