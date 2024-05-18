package gmail.vezhur2003.blps.controller;

import gmail.vezhur2003.blps.DTO.RegistrationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import gmail.vezhur2003.blps.DTO.UserLoginContext;
import gmail.vezhur2003.blps.entity.UserEntity;
import gmail.vezhur2003.blps.service.UserService;

@RestController
@RequestMapping("${api.endpoints.base-url}")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/employees")
    public ResponseEntity<UserLoginContext> registerEmployee(@RequestBody @Validated RegistrationData registrationData) {
        try {
            UserLoginContext registeredUser = userService.registerEmployee(registrationData);
            return ResponseEntity.ok(registeredUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new UserLoginContext(e.getMessage()));
        }
    }

    @PostMapping("/employers")
    public ResponseEntity<UserLoginContext> registerEmployer(@RequestBody @Validated RegistrationData registrationData) {
        try {
            UserLoginContext registeredUser = userService.registerEmployer(registrationData);
            return ResponseEntity.ok(registeredUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new UserLoginContext(e.getMessage()));
        }
    }

    @PostMapping("/admins")
    public ResponseEntity<UserLoginContext> registerAdmin(@RequestBody @Validated RegistrationData registrationData) {
        try {
            UserLoginContext registeredAdmin = userService.registerAdmin(registrationData);
            return ResponseEntity.ok(registeredAdmin);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new UserLoginContext(e.getMessage()));
        }
    }
}
