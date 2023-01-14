package com.vti.login;

import lombok.Data;

@Data
public class ResponseLogin {
    private String token;
    private String username;

    private String role;

}
