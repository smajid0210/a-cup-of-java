package com.example.demo;

import com.example.entity.Blog;
import com.example.entity.BlogDbRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class UploadBulkBlogService {

    ExecutorService executorService;
    BlogDbRepository blogDbRepository;

    List<String[] >blogrows;

    public UploadBulkBlogService(List<String[]>rows, BlogDbRepository blogDbRepository)
    {
        blogrows = rows;
        this.blogDbRepository = blogDbRepository;
        executorService = Executors.newFixedThreadPool(3);
    }

    public void startuploadbulkblog()
    {

        int counter = 1;
        for(String[] val: blogrows)
        {
            executorService.submit(new Bloguploader(val,counter));
            counter++;
        }

        executorService.shutdown();

        try {
            executorService.awaitTermination(120, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            // Handle the exception
            e.printStackTrace();
        }


    }

    private class Bloguploader implements Runnable
    {
           private String[] singleblog;
           int counter;
           int sleepinterval;

           Bloguploader(String[] val, int counter)
           {
               singleblog = val;
               this.counter = counter;

               Random rand = new Random();
               sleepinterval = rand.nextInt(2) + 3;
           }
           @Override
           public void run()
           {
               Blog blog = new Blog();
               String idthread = Thread.currentThread().getName();
               System.out.println("Blog " + counter + " is running on Thread " + idthread);
               blog.setAuthorid(Integer.valueOf(singleblog[0]));
               blog.setTitle(singleblog[1]);
               blog.setDescription(singleblog[2]);

               try {
                   blogDbRepository.createnewblog(blog);
               }
               catch(SQLException e)
               {
                   e.printStackTrace();
               }
               try
               {
                   Thread.sleep(sleepinterval*100);
               }
               catch(InterruptedException i)
               {
                   i.printStackTrace();
               }

           }

    }
}
