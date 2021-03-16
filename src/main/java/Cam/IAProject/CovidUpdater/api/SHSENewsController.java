package Cam.IAProject.CovidUpdater.api;

import Cam.IAProject.CovidUpdater.model.SHSENews;
import Cam.IAProject.CovidUpdater.service.SHSENewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//creates the mapping used in the api URL
@RequestMapping("api/SHSENews")
//tells spring to treat this as a controller when api calls are made
@RestController
public class SHSENewsController {
    private final SHSENewsService isdENewsService;

    @Autowired
    public SHSENewsController(SHSENewsService isdENewsService){
        this.isdENewsService = isdENewsService;
    }

    //method that is mapped as a post method using spring annotation, takes input from api call and adds it to DB.
    //this method is not used from the front end, mainly for testing and future proofing.
    @PostMapping
    public void addSHSENews(@RequestBody SHSENews isdENews){
        isdENewsService.addSHSENews(isdENews);
    }

    //method mapped as a get method by spring annotation, api calls it and SHSENews objects are returned
    @CrossOrigin
    @GetMapping(path = "{r1}-{r2}")
    public List<SHSENews> getALLSHSENews(@PathVariable("r1")int r1, @PathVariable("r2") int r2){
        return isdENewsService.getAllSHSENews();
    }

    @CrossOrigin
    @GetMapping
    public List<SHSENews> getSHSENewsRange(){
        return isdENewsService.getAllSHSENews();
    }

}

