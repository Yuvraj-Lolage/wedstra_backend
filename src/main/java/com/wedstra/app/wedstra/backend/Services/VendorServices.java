package com.wedstra.app.wedstra.backend.Services;

import com.wedstra.app.wedstra.backend.Entity.Vendor;
import com.wedstra.app.wedstra.backend.Repo.VendorRepository;
import com.wedstra.app.wedstra.backend.config.AmazonS3Config.bucket.BucketName;
import com.wedstra.app.wedstra.backend.config.AmazonS3Config.bucket.fileStore.FileStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class VendorServices {

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private JWTServices jwtServices;

    @Autowired
    private FileStore fileStore;

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

    public String authenticate(String username, String password) {
        Vendor vendor = vendorRepository.findByUsername(username);
        if(vendor != null){
            if(vendor.getPassword().equals(password)){
                return jwtServices.generateToken(username, vendor.getId());
            }
            else{
                return "bad credentials";
            }
        }
        else{
            return "vendor not found";
        }
    }

    public String uploadImage(MultipartFile file) {
        // save file to s3
        //1. check if the file is empty
        if(file.isEmpty()){
            return "file is empty";
        }
        //2. check if the file is an image
        if(!Arrays.asList("image/jpeg", "image/png", "image/gif").contains(file.getContentType())){
            return "file is not an image";
        }
        //3. Grab the file metadata
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        //4. Save the image to s3 and update the vendor image url

        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), file.getOriginalFilename());
        String fileName =  String.format("%s/%s", file.getName(), file.getOriginalFilename());
        try {
            fileStore.save(path, fileName, Optional.of(metadata), file.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "image uploaded";
    }
}
