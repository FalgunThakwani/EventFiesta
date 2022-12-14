package group11.EventFiesta.controller;

import group11.EventFiesta.db.MySQLDBPersistence;
import group11.EventFiesta.event.BlogHandler;
import group11.EventFiesta.event.UserMyEventFeedback;
import group11.EventFiesta.model.Reviews;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BlogController
{
    @GetMapping("/blog")
    public String showBlog(Model model) throws Exception
    {
        BlogHandler blogHandler = new BlogHandler();
        List<Reviews> reviewsList = blogHandler.getReviewList(new MySQLDBPersistence());
        model.addAttribute("userReviews", reviewsList);
        return "Blog";
    }
}
