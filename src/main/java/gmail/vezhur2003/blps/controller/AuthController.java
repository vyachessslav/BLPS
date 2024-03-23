package gmail.vezhur2003.blps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import gmail.vezhur2003.blps.DTO.UserLoginContext;
import gmail.vezhur2003.blps.entity.UserEntity;
import gmail.vezhur2003.blps.service.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserLoginContext> login(@RequestParam String login, @RequestParam String password) {
        try {
            UserLoginContext user = userService.login(login, password);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new UserLoginContext(e.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserLoginContext> register(@RequestBody UserEntity user) {
        try {
            UserLoginContext registeredUser = userService.register(user);
            return ResponseEntity.ok(registeredUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new UserLoginContext(e.getMessage()));
        }
    }
}
