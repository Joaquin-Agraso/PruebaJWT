package com.example.demo.loginjwt.Service;

import java.util.List;

import com.example.demo.loginjwt.User.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    String username;
    String password;
    String email;
    List<Role> role;
}
