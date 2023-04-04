package com.example.demo;

import com.example.entity.SetGlobals;
import com.example.entity.BlogDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
public class DeleteBlogController {

    @Autowired
    @Qualifier(SetGlobals.dbName)
    public BlogDbRepository blogDbRepository;

    @GetMapping("/deleteblog/{id}")
    public ModelAndView previewBlog(ModelAndView model, @PathVariable int id) throws SQLException
    {

        blogDbRepository.deleteblog(id);
        model.setViewName("redirect:/viewallblogs");
        return model;
    }
}
