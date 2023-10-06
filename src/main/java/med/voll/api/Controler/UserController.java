package med.voll.api.Controler;

import jakarta.validation.Valid;
import lombok.experimental.Delegate;
import med.voll.api.Domain.Users.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/login")
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/hello")
    public String hello(){
        return "Hello not secure here";
    }

    @GetMapping("/helloSecured")
    public String helloSecure(){
        return "Hello secure here";
    }

    @PostMapping("/createdUser")
    public ResponseEntity<?> createUser(@RequestBody @Valid CreatedUser createdUser){

        Set<RoleEntity> roles= createdUser.getRoles().stream()
                .map(role -> RoleEntity.builder()
                        .name(ERole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        UserEntity userEntity = UserEntity.builder()
                .username(createdUser.getUsername())
                .password(passwordEncoder.encode(createdUser.getPassword()))
                .email(createdUser.getEmail())
                .roles(roles)
                .build();

        userRepository.save(userEntity);

        return ResponseEntity.ok(userEntity);
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam String id){
        userRepository.deleteById(Long.parseLong(id));
        return "Se ha eliminado el usuarios con id: ".concat(id);

    }


}
