package com.wedstra.app.wedstra.backend.Controller;

import com.mongodb.internal.VisibleForTesting;
import com.wedstra.app.wedstra.backend.Entity.Vendor;
import com.wedstra.app.wedstra.backend.Services.VendorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/vendor")

public class VendorController {

    @Autowired
    private VendorServices vendorServices;



    @GetMapping("/getVendors")
    public ResponseEntity<List<Vendor>> handleGetAllVendors(){
        return new ResponseEntity<>(vendorServices.getAllVendors(), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('USER')")
    @GetMapping("/admin")
    public ResponseEntity<String> handleAdminEndpoint(){
        return new ResponseEntity<>("this is Admin endpoint", HttpStatus.OK);
    }


    @GetMapping("/getVendorById/{id}")
    public ResponseEntity<String> handleGetVendorById(@PathVariable String id){
        return new ResponseEntity<String>("Get Vendor by Id", HttpStatus.OK);
    }

    @PostMapping("/createVendor")
    public ResponseEntity<String> handleCreateVendor(@RequestBody Vendor vendor){
        return new ResponseEntity<String>(vendorServices.createVendor(vendor), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteVendor/{id}")
    public ResponseEntity<String> handleDeleteVendor(@PathVariable String id){
        String message = vendorServices.deleteVendor(id);
        if(message.contains("not found")){
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }


    @PutMapping("/{id}/update")
    public ResponseEntity<String> handleUpdateVendor(@PathVariable String id, @RequestBody Vendor vendor){
        String message = vendorServices.updateVendor(id, vendor);
        if(message.contains("not found")){
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }

}
