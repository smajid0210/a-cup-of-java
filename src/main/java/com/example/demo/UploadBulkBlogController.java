package com.example.demo;

import com.example.entity.SetGlobals;
import com.example.entity.BlogDbRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UploadBulkBlogController {

    @Autowired
    @Qualifier(SetGlobals.dbName)
    public BlogDbRepository blogDbRepository;

    @GetMapping("/bulkuploadblog")
    public ModelAndView loadbulkuploadform(ModelAndView model) {
        model.setViewName("bulkuploadblog.html");
        return model;
    }

    @PostMapping("/bulkuploadblog")
    public ModelAndView parseBlogcsv(@RequestParam("blogFile") MultipartFile file, ModelAndView model)
    {
        List<String[]>rows = new ArrayList<>();
         CSVReader csvReader = null;
        try {
            InputStreamReader reader = new InputStreamReader(file.getInputStream());
            csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            rows = csvReader.readAll();

        }
        catch(IOException e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
        catch(CsvException c)
        {
            System.out.println(c);
            c.printStackTrace();
        }
        finally
        {
            if(csvReader!=null)
            {
                try {
                    csvReader.close();
                }
                catch(IOException ex)
                {
                    System.out.println(ex);
                    ex.printStackTrace();
                }
            }
        }
        int counter = 1;
        for(String[] values: rows)
        {
            if(Integer.valueOf(values[0]) <=0 || values[1].isEmpty() || values[2].isEmpty())
            {
                model.setViewName("redirect:/bulkuploadblog");
                model.addObject("bulkerror",true);
                return model;
            }
        }
        UploadBulkBlogService uploadBulkBlogService = new UploadBulkBlogService(rows, blogDbRepository);
        uploadBulkBlogService.startuploadbulkblog();

        model.setViewName("redirect:/viewallblogs");
        return model;
    }
}
