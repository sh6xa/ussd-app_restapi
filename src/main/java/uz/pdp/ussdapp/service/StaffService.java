package uz.pdp.ussdapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.ussdapp.entity.Filial;
import uz.pdp.ussdapp.entity.Staff;
import uz.pdp.ussdapp.payload.ApiResponse;
import uz.pdp.ussdapp.payload.StaffDTO;
import uz.pdp.ussdapp.repository.FilialRepository;
import uz.pdp.ussdapp.repository.StaffRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class StaffService {
    @Autowired
    StaffRepository staffRepository;
    @Autowired
    FilialRepository filialRepository;

    // staff qo'shish
    public ApiResponse add(StaffDTO staffDTO) {
        Staff staff = new Staff();
        Optional<Filial> optionalFilial = filialRepository.findById(staffDTO.getFilialId());
        if (!optionalFilial.isPresent()) {
            return new ApiResponse("Filial not found", false);
        }
        staff.setFilial(optionalFilial.get());
        staff.setPassword(staffDTO.getPassword());
        staff.setFullName(staffDTO.getFullName());
        staff.setUserName(staffDTO.getUserName());
        staff.setPosition(staffDTO.getPosition());
        staffRepository.save(staff);
        return new ApiResponse("Saved", true);
    }

    // id bo'yicha olish
    public ApiResponse getOne(UUID id) {
        Optional<Staff> optionalStaff = staffRepository.findById(id);
        return optionalStaff.map(staff -> new ApiResponse("Found", true, staff)).orElseGet(() -> new ApiResponse("Not found", false));
    }

    // bu kompaniya uchun har ishni qiladi
    public ApiResponse edit(UUID id, StaffDTO staffDTO) {
        Optional<Staff> optionalStaff = staffRepository.findById(id);
        if (!optionalStaff.isPresent()) {
            return new ApiResponse("Not found", false);
        }
        Staff staff = optionalStaff.get();
        Optional<Filial> optionalFilial = filialRepository.findById(staffDTO.getFilialId());
        if (!optionalFilial.isPresent()) {
            return new ApiResponse("Filial not found", false);
        }
        staff.setFilial(optionalFilial.get());
        staff.setPassword(staffDTO.getPassword());
        staff.setFullName(staffDTO.getFullName());
        staff.setUserName(staffDTO.getUserName());
        staff.setPosition(staffDTO.getPosition());
        staffRepository.save(staff);
        return new ApiResponse("Saved", true);
    }

    // bu staffni o'zi uchun imkonyatlar cheklangan
    public ApiResponse editForOnlyStaff(UUID id, StaffDTO staffDTO) {
        Optional<Staff> optionalStaff = staffRepository.findById(id);
        if (!optionalStaff.isPresent()) {
            return new ApiResponse("Not found", false);
        }
        Staff staff = optionalStaff.get();
        staff.setPassword(staffDTO.getPassword());
        staff.setFullName(staffDTO.getFullName());
        staff.setUserName(staffDTO.getUserName());
        staffRepository.save(staff);
        return new ApiResponse("Saved", true);
    }

    // o'chirish
    public ApiResponse delete(UUID id) {
        if (staffRepository.existsById(id)) {
            staffRepository.deleteById(id);
        }
        return new ApiResponse("Deleted", true);
    }
}
