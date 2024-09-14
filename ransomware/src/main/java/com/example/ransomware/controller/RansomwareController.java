package com.example.ransomware.controller;

import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ransomware.service.RansomwareService;


@RestController
@RequestMapping("/api/ransomware")
@CrossOrigin(origins = "http://localhost:4200") 
public class RansomwareController {    
    @Autowired
    private RansomwareService ransomwareService;    
    
    @PostMapping
    public ResponseEntity<String> createRansomware(@RequestBody Map<String, Object> ransomware) {        
        String id = ransomwareService.createRansomware(ransomware);        
        return ResponseEntity.ok(id);
    }    
    
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getRansomwareById(@PathVariable String id) {         Map<String, Object> ransomware = ransomwareService.getRansomwareById(id);       
        return ResponseEntity.ok(ransomware);
    }    
    
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllRansomware(
        @RequestParam int page,
        @RequestParam int size) {
        List<Map<String, Object>> ransomwareList = ransomwareService.getAllRansomware(page, size);       
        return ResponseEntity.ok(ransomwareList);
    }    

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRansomware(
        @PathVariable String id, 
        @RequestBody Map<String, Object> ransomware) {
        ransomwareService.updateRansomware(id, ransomware);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRansomware(@PathVariable String id) { 
        ransomwareService.deleteRansomware(id);
        return ResponseEntity.ok().build();
    }
}