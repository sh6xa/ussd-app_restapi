package uz.pdp.ussdapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.ussdapp.entity.Category;
import uz.pdp.ussdapp.entity.EntertainmentService;
import uz.pdp.ussdapp.payload.ApiResponse;
import uz.pdp.ussdapp.payload.EntertainmentServiceDto;
import uz.pdp.ussdapp.repository.CategoryRepository;
import uz.pdp.ussdapp.repository.EntertainmentServiceRepository;

import java.util.Optional;

@Service
public class EntertainmentServiceService {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    EntertainmentServiceRepository entertainmentServiceRepository;

    public ApiResponse add(EntertainmentServiceDto entertainmentServiceDto) {

        EntertainmentService entertainmentService = new EntertainmentService();
        Optional<Category> category = categoryRepository.findById(entertainmentServiceDto.getCategoryId());
        if (!category.isPresent()) return null;
        entertainmentService.setCategory(category.get());
        entertainmentService.setPrice(entertainmentServiceDto.getPrice());
        entertainmentServiceDto.setName(entertainmentServiceDto.getName());
        entertainmentService.setDueDate(entertainmentServiceDto.getDueDate());
        entertainmentServiceRepository.save(entertainmentService);
        return new ApiResponse("success",true);

    }
    public ApiResponse edit(Integer id,EntertainmentServiceDto entertainmentServiceDto){
        if (!entertainmentServiceRepository.existsById(id)) {
            return new ApiResponse("object not found",false);
        }else {
            Optional<EntertainmentService> byId = entertainmentServiceRepository.findById(id);

           if(!categoryRepository.existsById(entertainmentServiceDto.getCategoryId())) return new ApiResponse("category not found",false);

if (!byId.isPresent()) return null;
           EntertainmentService entertainmentService = byId.get();
            entertainmentService.setDueDate(entertainmentServiceDto.getDueDate());
            entertainmentService.setName(entertainmentServiceDto.getName());
            Optional<Category> category = categoryRepository.findById(entertainmentServiceDto.getCategoryId());
            if (!category.isPresent()) return null;

            entertainmentService.setCategory(category.get());
            entertainmentService.setPrice(entertainmentServiceDto.getPrice());
            return new ApiResponse("success",true);
        }
    }
}
