package com.example.cryptohash.Controller;


import com.example.cryptohash.Model.HashRequest;
import com.example.cryptohash.Model.HashResponse;
import com.example.cryptohash.Service.HashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("api/hash")
public class HashController {

    @Autowired
    public HashService service;

    @PostMapping("/compare")
    public HashResponse compareHashes (@RequestBody HashRequest request){
        return service.compare(request);
    }
}
