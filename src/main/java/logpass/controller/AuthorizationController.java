package logpass.controller;

import logpass.service.AuthorizationService;
import logpass.model.Authorities;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorizationController {
    private final AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@RequestParam("user") String user, 
                                          @RequestParam("password") String password) {
        return service.getAuthorities(user, password);
    }
}
