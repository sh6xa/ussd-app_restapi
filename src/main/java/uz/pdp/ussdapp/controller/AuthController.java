package uz.pdp.ussdapp.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.ussdapp.entity.User;
import uz.pdp.ussdapp.payload.ApiResponse;
import uz.pdp.ussdapp.payload.AuthRegisterDTO;
import uz.pdp.ussdapp.payload.LoginDto;
import uz.pdp.ussdapp.repository.UserRepository;
import uz.pdp.ussdapp.service.AuthService;

import java.util.List;

@RequestMapping("/api/auth")
@RestController
public class AuthController {

    @Autowired
    AuthService authService;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public HttpEntity<?> register(@RequestBody AuthRegisterDTO authRegisterDTO) throws NotFoundException {
        ApiResponse apiResponse = authService.registerUser(authRegisterDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDto loginDto) {
        ApiResponse apiResponse = authService.loginUser(loginDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }
    @GetMapping
    public HttpEntity<?> all(@RequestBody LoginDto loginDto) {
        List<User> all = userRepository.findAll();
        return ResponseEntity.ok(all);
    }
}
