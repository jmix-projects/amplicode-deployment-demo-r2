package io.jmix2mvp.petclinic.dto;

import java.util.List;

public class UserDTO {
    private String username;

    private List<String> authorities;

    private boolean enabled;

    public String getUsername() {return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
