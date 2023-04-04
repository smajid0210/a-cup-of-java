package com.example.entity;

public class Blog {

    int blogid;
    String title;
    String description;
    int authorid;
    String createdat;

    public Blog()
    {
        this.blogid = 0;
        this.title = "";
        this.description = "";
        this.authorid = 0;
        this.createdat = "";
    }
    public Blog(int blogid, String title, int authorid)
    {
        this.blogid = blogid;
        this.title = title;
        this.authorid = authorid;
    }

    Blog(int blogid, String title, String description, int authorid, String createdat)
    {
        this.blogid = blogid;
        this.title = title;
        this.description = description;
        this.authorid = authorid;
        this.createdat = createdat;
    }

    public void setBlogid(int blogid)
    {
        this.blogid = blogid;
    }
    public int getBlogid()
    {
        return blogid;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }
    public String getTitle()
    {
        return title;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
    public String getDescription()
    {
        return description;
    }

    public void setAuthorid(int authorid)
    {
        this.authorid = authorid;
    }
    public int getAuthorid()
    {
        return authorid;
    }


}
