package com.springBoot.blogApp;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Controller
public class BlogController {
    @RequestMapping("/blogs")
    private ModelAndView getBlogs(Model model) {
        String uri = "https://jsonplaceholder.typicode.com/posts/" ;
        RestTemplate restTemplate = new RestTemplate();
        Blogs[] blogsArray = restTemplate.getForObject(uri, Blogs[].class);
        List<Blogs> blogs = Arrays.asList(blogsArray);
        /* Collections.sort(blogs, new Comparator<Blogs>() {
            @Override
            public int compare(Blogs o1, Blogs o2) {
                return o2.getTitle().compareTo(o1.getTitle());
            }
        }); */


        ModelAndView modelAndView = new ModelAndView("blogs");
        modelAndView.addObject("blogs", blogs);

        return modelAndView;

    }

    @GetMapping("/home")
    public String homePage(Model model){
        return "homePage";
    }

}
