package com.example.demo;

import com.example.entity.SetGlobals;
import com.example.entity.Blog;
import com.example.entity.BlogDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
public class EditBlogController {

    @Autowired
    @Qualifier(SetGlobals.dbName)
    public BlogDbRepository blogDbRepository;

    @GetMapping("/editblog/{id}")
    public ModelAndView previewBlog(ModelAndView model,@PathVariable int id) throws SQLException
    {
        Blog editableBlog = blogDbRepository.viewablogbyid(id);

        model.addObject("blog",editableBlog);
        model.setViewName("editblog.html");
        return model;
    }

    @PostMapping("/editblog")
    public ModelAndView editBlog(ModelAndView model, @ModelAttribute Blog blog) throws SQLException {

        if(blog.getAuthorid() > 0) {
            Blog newblog = blog;
            blogDbRepository.editblog(newblog);
            model.setViewName("redirect:/viewallblogs");
            return model;
        }

        else {
             model.setViewName("redirect:/editblog/" + blog.getBlogid());
             model.addObject("editerror",true);
             return model;
            }
        }

}
