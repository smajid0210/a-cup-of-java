package com.example.entity;

import org.springframework.stereotype.Repository;
import java.sql.SQLException;
import java.util.List;

@Repository
public interface BlogDbRepository {

    public void createnewblog(Blog blog) throws SQLException;
    public void deleteblog(int id) throws SQLException;
    public void editblog(Blog blog) throws SQLException;
    public List<Blog> viewallblogs() throws SQLException;
    public Blog viewablogbyid(int id) throws SQLException;
}
