package library.management.books.Controller;

import library.management.books.Dto.RegisterRequestDto;
import library.management.books.Entity.UserEntity;
import library.management.books.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterRequestDto dto) {

        var user = new UserEntity();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(
                passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());

        userRepo.save(user);
        return ResponseEntity.ok(
                dto.getRole() + " registered successfully!");
    }
}