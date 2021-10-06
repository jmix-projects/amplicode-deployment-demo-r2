package io.jmix2mvp.petclinic;

import org.springframework.security.core.Authentication;

public interface Authorities {
    String ADMIN = "ROLE_ADMIN";
    String VETERINARIAN = "ROLE_VETERINARIAN";
    String OWNER = "ROLE_OWNER";

    static boolean isOwner(Authentication authentication) {
        return hasRole(authentication, OWNER);
    }

    static boolean isVeterinarian(Authentication authentication) {
        return hasRole(authentication, VETERINARIAN);
    }

    static boolean hasRole(Authentication authentication, String role) {
        return authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(role));
    }
}
