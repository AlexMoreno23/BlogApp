package by.morunov.controller;

import by.morunov.model.Post;
import by.morunov.model.Role;
import by.morunov.model.User;
import by.morunov.service.PostService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Alex Morunov
 */
@Controller

public class PostController {


    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }


    @PostMapping("/posts")
    public String addPost(@AuthenticationPrincipal User user,
                          @RequestParam String title, @RequestParam String text, Map<String, Object> model) {
        Post post = new Post(title, text, user);
        postService.savePost(post);

        Iterable<Post> posts = postService.getAll();
        model.put("posts", posts);

        return "posts";
    }

    @GetMapping("/userPosts/{user}")
    public String getPostByAuthor(@PathVariable User user, Model model) {
        model.addAttribute("userName", user.getUsername());
        model.addAttribute("user", user.getId());
        model.addAttribute("posts" , postService.getByAuthor(user.getId()));

       return "/userPosts";
    }

    @GetMapping("/posts")
    public String filter(@RequestParam(required = false, defaultValue = "") String title, Model model) {
        Iterable<Post> posts;

        if (title != null && !title.isEmpty()) {
            posts = postService.getByTitle(title);
        } else {
            posts = postService.getAll();
        }
        model.addAttribute("title", title);
        model.addAttribute("posts", posts);

        return "/posts";
    }

}
