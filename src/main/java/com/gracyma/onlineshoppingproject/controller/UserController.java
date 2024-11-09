package com.gracyma.onlineshoppingproject.controller;

import com.gracyma.onlineshoppingproject.model.OnlineShoppingUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class UserController {
    HashMap<String, OnlineShoppingUser> userMaps = new HashMap<>();

    @PostMapping("/users")
    public String createUser(@RequestParam("id") String id,
                             @RequestParam("name") String name,
                             @RequestParam("email") String email,
                             Map<String, Object> resultMap) {// use hash map t put values in C to V

        OnlineShoppingUser user = OnlineShoppingUser.builder()
                .id(id)
                .name(name)
                .email(email)
                .build();
        userMaps.put(id, user); // use hash map to put values in database
        resultMap.put("user", user);
        return "user_detail"; // now return the name of View file

    }

    @GetMapping("/users/{id}")
    public String getUser(@PathVariable("id") String id,
                          Map<String, Object> resultMap) {
        OnlineShoppingUser user = userMaps.get(id);
//        OnlineShoppingUser user = userMaps.getOrDefault(id, defaultUser);
        resultMap.put("user", user);
        return "user_detail";
    }
}
