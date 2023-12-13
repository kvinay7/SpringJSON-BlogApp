package com.springBoot.blogApp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class UsersController {
    private final String jsonPlaceholderUrl = "https://jsonplaceholder.typicode.com/users";

    @GetMapping("/users")
    public String getUsers(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        Users[] users = restTemplate.getForObject(jsonPlaceholderUrl, Users[].class);
        model.addAttribute("users", users);
        return "Users";
    }


    @GetMapping("/user/{id}")
    public String getUserDetails(@PathVariable Long id, Model model) {
        String UserUrl = "https://jsonplaceholder.typicode.com/users/" + id;
        RestTemplate restTemplate = new RestTemplate();
        Users user = restTemplate.getForObject(UserUrl, Users.class);
        model.addAttribute("user", user);
        return "UserPage";
    }

    @GetMapping("/user/{uid}/posts")
    public String getUserPosts(@PathVariable int uid, Model model) {
        String baseUrl = "https://jsonplaceholder.typicode.com/users/{id}/posts";
        String userUrl = UriComponentsBuilder.fromUriString(baseUrl)
                .buildAndExpand(uid)
                .toUriString();

        RestTemplate restTemplate = new RestTemplate();
        UserPosts[] userPosts = restTemplate.getForObject(userUrl, UserPosts[].class);

        model.addAttribute("userPosts", userPosts);
        model.addAttribute("userId", uid);
        return "UserPosts";
    }

    @GetMapping("/user/{uid}/comments")
    public String getUserComments(@PathVariable int uid, Model model) {
        String baseUrl = "https://jsonplaceholder.typicode.com/users/{id}/comments";
        String userUrl = UriComponentsBuilder.fromUriString(baseUrl)
                .buildAndExpand(uid)
                .toUriString();

        RestTemplate restTemplate = new RestTemplate();
        Comments[] userComments = restTemplate.getForObject(userUrl, Comments[].class);

        model.addAttribute("userComments", userComments);
        model.addAttribute("userId", uid);
        return "UserComments";
    }

}
