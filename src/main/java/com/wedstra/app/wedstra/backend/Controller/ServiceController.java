package com.wedstra.app.wedstra.backend.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")

public class ServiceController {
    @GetMapping("/getAll")
    public ResponseEntity<String> handleGetAllServices(){
        return new ResponseEntity<String>("Get all Services", HttpStatus.OK);
    }

    @GetMapping("/getByVendor/{vendorId}")
    public ResponseEntity<String> handleGetServicesByVendor(@PathVariable String vendorId){
        return new ResponseEntity<String>("Get Services by vendor "+vendorId, HttpStatus.OK);
    }



}
