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

    @GetMapping("/{vendor_id}/all")
    public ResponseEntity<List<Service>> handleGetServicesByVendor(@PathVariable String vendor_id){
        return new ResponseEntity<>(serviceServices.getServicesByVendor(vendor_id), HttpStatus.OK);
    }

    @GetMapping("/{category}")
    public ResponseEntity<List<Service>> handleGetServicesByCategory(@PathVariable String category){
        return new ResponseEntity<>(serviceServices.getServicesBycategory(category), HttpStatus.OK);
    }

    @PostMapping("/{vendor_id}/create-service")
    public ResponseEntity<Service> handleCreateService(@RequestBody Service service, @PathVariable String vendor_id){
        return new ResponseEntity<>(serviceServices.createService(service, vendor_id), HttpStatus.CREATED);
    }

    @DeleteMapping("{service_id}/delete")
    public ResponseEntity<?> handleServiceDelete(@PathVariable String service_id){
        if(serviceServices.deleteService(service_id)){
            return new ResponseEntity<>("Service deleted", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("service not found", HttpStatus.NOT_FOUND);
        }
    }
}
