package com.example.demo;

import com.example.entity.SetGlobals;
import com.example.entity.Blog;
import com.example.entity.BlogDbRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.sql.SQLException;

@Controller
public class CreateNewBlogController {

    @Autowired
    @Qualifier(SetGlobals.dbName)
    public BlogDbRepository blogDbRepository;

    @GetMapping("/createnewblog")
    public ModelAndView loadBlogUploadForm(ModelAndView model) {
        model.setViewName("createblog.html");
        model.addObject("blogForm", new Blog());
        return model;
    }

    @PostMapping("/createnewblog")
    public ModelAndView submitNewBlogForm( ModelAndView model, @ModelAttribute Blog blog) throws SQLException
    {

        if(blog.getAuthorid() > 0) {
            Blog newblog = blog;
            blogDbRepository.createnewblog(newblog);
            model.setViewName("redirect:/viewallblogs");
            return model;
        }
        else {
            model.setViewName("redirect:/createnewblog");
            model.addObject("texterror",true);
            return model;
        }
    }


}
