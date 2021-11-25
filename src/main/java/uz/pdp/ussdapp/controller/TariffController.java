package uz.pdp.ussdapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.ussdapp.entity.Tariff;
import uz.pdp.ussdapp.payload.ApiResponse;
import uz.pdp.ussdapp.payload.TariffDTO;
import uz.pdp.ussdapp.repository.TariffRepository;
import uz.pdp.ussdapp.service.TariffService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tariff")
@RequiredArgsConstructor
public class TariffController {

    final TariffService tariffService;
    final TariffRepository tariffRepository;

    // raqam manager
    @PostMapping
    public HttpEntity<?> save(@RequestBody TariffDTO tariffDTO) {
        ApiResponse response = tariffService.saveTariff(tariffDTO);

        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    //get all
    @GetMapping("/all")
    public List<Tariff> getTariff(){
        List<Tariff> all = tariffRepository.findAll();
        return all;

    }

    //byname
    @GetMapping("/{name}")
    public Optional<Tariff> getTarif(@PathVariable String name){
        boolean existsByName = tariffRepository.existsByName(name);
        if (!existsByName) return Optional.empty();
        return tariffRepository.findByName(name);

    }
 // path yozish qolib ketgan ekan yozib qo'ydim
    @PutMapping("/{name}")
    public HttpEntity<?>editTariff(@PathVariable String name,@RequestBody TariffDTO   tariffDTO){
        ApiResponse apiResponse = tariffService.editTariff(name, tariffDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    //delete by name
    @DeleteMapping("/{name}")
    public HttpEntity<?>deleteById(@PathVariable String name){

        if(!tariffRepository.existsByName(name)) return null;

        tariffRepository.deleteByName(name);

        return  ResponseEntity.ok().body("deleted");


    }

}
