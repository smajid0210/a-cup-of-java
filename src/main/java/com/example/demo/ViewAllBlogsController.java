package com.example.demo;
import com.example.entity.SetGlobals;
import com.example.entity.Blog;
import com.example.entity.BlogDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;

@Controller
public class ViewAllBlogsController {

    @Autowired
    @Qualifier(SetGlobals.dbName)
    public BlogDbRepository blogDbRepository;
    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/viewallblogs")
    public ModelAndView sayHello(ModelAndView model) throws SQLException {
        model.setViewName("home.html");
        model.addObject("appName",appName);
        List<Blog> bloglist = blogDbRepository.viewallblogs();
        model.addObject("bloglist",bloglist);

        return model;
    }
}