package com.bumble.bee.app.rest.controller;

import com.bumble.bee.app.models.dao.User;
import com.bumble.bee.app.models.dto.CredentialsDto;
import com.bumble.bee.app.models.dto.FullUserDto;
import com.bumble.bee.app.models.dto.UserDto;
import com.bumble.bee.app.rest.AbstractRestService;
import com.bumble.bee.app.rest.service.UsersRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = {"http://localhost:4200","https://proud-coast-0c53fa610.3.azurestaticapps.net"})
@RestController
@RequestMapping("/users")
public class UserRestController extends AbstractEntityRestController<Long, FullUserDto, User> {
    UsersRestService service;
    @Autowired
    public UserRestController(UsersRestService restService) {
        super(restService);
        this.service=restService;
    }

    @GetMapping(path="all",produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<UserDto>> findAllUsers() {
        return ResponseEntity.ok(this.service.getALlUserDto());
    }
}
