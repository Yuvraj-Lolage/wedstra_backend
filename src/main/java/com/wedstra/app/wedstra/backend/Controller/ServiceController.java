package com.wedstra.app.wedstra.backend.Controller;

import com.wedstra.app.wedstra.backend.Entity.Service;
import com.wedstra.app.wedstra.backend.Services.ServiceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service")

public class ServiceController {

    @Autowired
    private ServiceServices serviceServices;

    @GetMapping("/getAll")
    public ResponseEntity<List<Service>> handleGetAllServices(){
        return new ResponseEntity<List<Service>>(serviceServices.getAllServices(), HttpStatus.OK);
    }

    @GetMapping("/getByVendor/{vendor_id}")
    public ResponseEntity<List<Service>> handleGetServicesByVendor(@PathVariable String vendor_id){
        return new ResponseEntity<>(serviceServices.getServicesByVendor(vendor_id), HttpStatus.OK);
    }

    @PostMapping("/createService")
    public ResponseEntity<Service> handleCreateService(@RequestBody Service service){
        return new ResponseEntity<>(serviceServices.createService(service), HttpStatus.CREATED);
    }
}
