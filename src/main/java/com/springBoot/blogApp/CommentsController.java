package com.springBoot.blogApp;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Controller
public class CommentsController {
    private final String jsonPlaceholderUrl = "https://jsonplaceholder.typicode.com/comments?postId={postId}";

    @GetMapping("/comments/{postId}")
    public String getCommentsByPostId(@PathVariable int postId, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        Comments[] comments = restTemplate.getForObject(jsonPlaceholderUrl, Comments[].class, postId);
        model.addAttribute("comments", comments);
        model.addAttribute("postId", postId);
        return "Comments";
    }
}
