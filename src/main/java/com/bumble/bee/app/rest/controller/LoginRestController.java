package com.bumble.bee.app.rest.controller;

import com.bumble.bee.app.exceptions.EntityNotFoundException;
import com.bumble.bee.app.models.dao.User;
import com.bumble.bee.app.models.dto.CredentialsDto;
import com.bumble.bee.app.models.dto.UserDto;
import com.bumble.bee.app.models.error.BadRequestErrorDto;
import com.bumble.bee.app.rest.service.UsersRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginRestController {
    @Autowired
    UsersRestService restService;
    public LoginRestController() {

    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("")
    public ResponseEntity<?> login(@RequestBody CredentialsDto dto) {
        UserDto user = restService.login(dto);
        if(user==null){
            return  ResponseEntity.badRequest().body("Please check username/passwor and try again.");
        }else {
            if(user.getRole().equals("admin")) {
                return ResponseEntity.ok(user);
            }else{
                return  ResponseEntity.badRequest().body("Only admins are allowed to login.");
            }
        }
    }
}
