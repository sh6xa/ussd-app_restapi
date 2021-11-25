package uz.pdp.ussdapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.ussdapp.entity.Staff;
import uz.pdp.ussdapp.payload.ApiResponse;
import uz.pdp.ussdapp.payload.StaffDTO;
import uz.pdp.ussdapp.repository.FilialRepository;
import uz.pdp.ussdapp.repository.StaffRepository;
import uz.pdp.ussdapp.service.StaffService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/stuff")
public class StaffController {
    // Bu controller kompaniya uchun staff o'zini ko'rish va o'zgartirish uchun alohida controller yoziladi

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    FilialRepository filialRepository;

    @Autowired
    StaffService staffService;

    @PostMapping
    public HttpEntity<?> addStaffToFilial(@RequestBody StaffDTO staffDTO) {
        ApiResponse apiResponse = staffService.add(staffDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @GetMapping
    private HttpEntity<?> getList() {
        List<Staff> all = staffRepository.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    private HttpEntity<?> getOne(@PathVariable UUID id) {
        ApiResponse apiResponse = staffService.getOne(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    private HttpEntity<?> editStaff(@PathVariable UUID id, @RequestBody StaffDTO staffDTO) {
        ApiResponse apiResponse = staffService.edit(id, staffDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteStaff(@PathVariable UUID id) {
        ApiResponse apiResponse = staffService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }
}
