package com.wedstra.app.wedstra.backend.Services;

import com.wedstra.app.wedstra.backend.Repo.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceServices {

    @Autowired
    private ServiceRepository serviceRepository;

    public List<com.wedstra.app.wedstra.backend.Entity.Service> getAllServices(){
        return serviceRepository.findAll();
    }

    public com.wedstra.app.wedstra.backend.Entity.Service createService(com.wedstra.app.wedstra.backend.Entity.Service service) {
        return serviceRepository.save(service);
    }

    public List<com.wedstra.app.wedstra.backend.Entity.Service> getServicesByVendor(String vendor_id){
        return serviceRepository.getServicesById(vendor_id);
    }
}
