package uz.pdp.ussdapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.ussdapp.entity.EntertainmentService;
import uz.pdp.ussdapp.payload.ApiResponse;
import uz.pdp.ussdapp.payload.EntertainmentServiceDto;
import uz.pdp.ussdapp.repository.EntertainmentServiceRepository;
import uz.pdp.ussdapp.service.EntertainmentServiceService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/service")
public class EntertainmentServiceController {

    @Autowired
    EntertainmentServiceService entertainmentServiceService;
    @Autowired
    EntertainmentServiceRepository entertainmentServiceRepository;

    @PostMapping
    public HttpEntity<?>add(@RequestBody EntertainmentServiceDto entertainmentServiceDto){
        ApiResponse add = entertainmentServiceService.add(entertainmentServiceDto);
        return ResponseEntity.ok().body(add);


    }

    //findById
    @GetMapping("/{id}")
public Optional<EntertainmentService> get(@PathVariable Integer id){
        if (!entertainmentServiceRepository.existsById(id)) {
            return Optional.empty();
        }else {
            return entertainmentServiceRepository.findById(id);
        }
    }//get all
    @GetMapping
    public List<EntertainmentService>services(){
        return entertainmentServiceRepository.findAll();
    }

    @PutMapping("/{id}")
    public HttpEntity<?> put(@PathVariable Integer id,@RequestBody EntertainmentServiceDto  entertainmentServiceDto){
        ApiResponse edit = entertainmentServiceService.edit(id, entertainmentServiceDto);
        return ResponseEntity.status(edit.isSuccess()?201:409).body(edit);


    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteById(@PathVariable Integer id){
        if(entertainmentServiceRepository.existsById(id)) return ResponseEntity.status(409).body("not found ");
        entertainmentServiceRepository.deleteById(id);
        return ResponseEntity.ok().body("deleted");

    }

}
