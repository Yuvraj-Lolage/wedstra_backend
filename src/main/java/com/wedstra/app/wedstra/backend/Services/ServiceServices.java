package com.wedstra.app.wedstra.backend.Services;

import com.wedstra.app.wedstra.backend.Repo.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceServices {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<com.wedstra.app.wedstra.backend.Entity.Service> getAllServices(){
        return serviceRepository.findAll();
    }

    public com.wedstra.app.wedstra.backend.Entity.Service createService(com.wedstra.app.wedstra.backend.Entity.Service service, String vendor_id) {

        service.setVendor_id(vendor_id);
        return serviceRepository.save(service);
    }

    public List<com.wedstra.app.wedstra.backend.Entity.Service> getServicesByVendor(String vendor_id){
        Query query = new Query(Criteria.where("vendor_id").is(vendor_id));
        return mongoTemplate.find(query, com.wedstra.app.wedstra.backend.Entity.Service.class);
    }

    public boolean deleteService(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        com.wedstra.app.wedstra.backend.Entity.Service existingService = mongoTemplate.findOne(query, com.wedstra.app.wedstra.backend.Entity.Service.class);

        if(existingService != null){
            serviceRepository.deleteById(id);
            return true;
        }else{
            return false;
        }

    }

    public List<com.wedstra.app.wedstra.backend.Entity.Service> getServicesBycategory(String category) {
        Query query = new Query(Criteria.where("category").is(category));
        return mongoTemplate.find(query, com.wedstra.app.wedstra.backend.Entity.Service.class);
    }
}
