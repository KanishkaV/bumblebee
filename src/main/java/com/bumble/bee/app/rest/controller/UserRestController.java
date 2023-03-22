package com.bumble.bee.app.rest.controller;

import com.bumble.bee.app.models.dao.User;
import com.bumble.bee.app.models.dto.UserDto;
import com.bumble.bee.app.rest.AbstractRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UserRestController extends AbstractEntityRestController<Long, UserDto, User> {

    @Autowired
    public UserRestController(AbstractRestService<Long, UserDto, User> restService) {
        super(restService);
    }
}
