package com.example.ideaface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private Facebook facebook;

    @Autowired
    private ConnectionRepository connectionRepository;

    @GetMapping
    public String helloFacebook(Model model) {
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return "redirect:/connect/facebook";
        }

        String[] fields = {"id", "first_name", "last_name"};
        User userProfile = facebook.fetchObject("me", User.class, fields);
        model.addAttribute("facebookProfile", userProfile);

        // model.addAttribute("facebookProfile",
        // facebook.userOperations().getUserProfile());
        // PagedList<Post> feed = facebook.feedOperations().getFeed();
        // model.addAttribute("feed", feed);

        return "hello";
    }

}