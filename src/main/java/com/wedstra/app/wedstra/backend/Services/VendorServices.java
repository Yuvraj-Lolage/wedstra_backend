package com.wedstra.app.wedstra.backend.Services;

import com.wedstra.app.wedstra.backend.Entity.Vendor;
import com.wedstra.app.wedstra.backend.Repo.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServices {

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Vendor> getAllVendors(){
        return vendorRepository.findAll();
    }


    public String createVendor(Vendor vendor) {
        vendorRepository.save(vendor);
        return "vendor created in services class";
    }


    public String deleteVendor(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        Vendor existingVendor = mongoTemplate.findOne(query, Vendor.class);

        if(existingVendor == null){
            return "Vendor not found.";
        }
        else {
            vendorRepository.deleteById(id);
            return "vendor deleted with ID"+id;
        }
    }

    public String updateVendor(String id, Vendor vendor) {
        Query query = new Query(Criteria.where("id").is(id));
        Vendor existingVendor = mongoTemplate.findOne(query, Vendor.class);

        if(existingVendor == null){
            return "Vendor not found.";
        }
        else {
            // Build the update object with the new values
            Update update = new Update();
            if (vendor.getVendor_name() != null) update.set("vendor_name", vendor.getVendor_name());
            if (vendor.getBusiness_name() != null) update.set("business_name", vendor.getBusiness_name());
            if (vendor.getBusiness_category() != null) update.set("business_category", vendor.getBusiness_category());
            if (vendor.getVendor_aadharCard() != null) update.set("vendor_aadharCard", vendor.getVendor_aadharCard());
            if (vendor.getVendor_PAN() != null) update.set("vendor_PAN", vendor.getVendor_PAN());
            if (vendor.getBusiness_PAN() != null) update.set("business_PAN", vendor.getBusiness_PAN());
            if (vendor.getElectricity_bill() != null) update.set("electricity_bill", vendor.getElectricity_bill());
            if (vendor.getBusiness_photos() != null) update.set("business_photos", vendor.getBusiness_photos());
            if (vendor.getLiscence() != null) update.set("liscence", vendor.getLiscence());
            if (vendor.getGst_number() != null) update.set("gst_number", vendor.getGst_number());
            if (vendor.getTerms_and_conditions() != null) update.set("terms_and_conditions", vendor.getTerms_and_conditions());
            if (vendor.getEmail() != null) update.set("email", vendor.getEmail());
            if (vendor.getPhone_no() != null) update.set("phone_no", vendor.getPhone_no());
            if (vendor.getPassword() != null) update.set("password", vendor.getPassword());
            if (vendor.getCity() != null) update.set("city", vendor.getCity());

            // Perform the update
            mongoTemplate.updateFirst(query, update, Vendor.class);
            return "vendor found.";
        }
    }
}
