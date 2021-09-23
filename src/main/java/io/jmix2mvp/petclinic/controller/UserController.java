package io.jmix2mvp.petclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserController {
    @GetMapping(produces = {"application/json"})
    public Principal retrievePrincipal(Principal principal) {
        return principal;
    }
}
