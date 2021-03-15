package Cam.IAProject.CovidUpdater.api;

import Cam.IAProject.CovidUpdater.model.ISDENews;
import Cam.IAProject.CovidUpdater.service.ISDENewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//creates the mapping used in the api URL
@RequestMapping("api/ISDENews")
//tells spring to treat this as a controller when api calls are made
@RestController
public class ISDENewsController {
    private final ISDENewsService isdENewsService;

    @Autowired
    public ISDENewsController(ISDENewsService isdENewsService){
        this.isdENewsService = isdENewsService;
    }

    //method that is mapped as a post method using spring annotation, takes input from api call and adds it to DB.
    //this method is not used from the front end, mainly for testing and future proofing.
    @PostMapping
    public void addISDENews(@RequestBody ISDENews isdENews){
        isdENewsService.addISDENews(isdENews);
    }
    //TODO: add a password function here so that only I can make post requests

    //method mapped as a get method by spring annotation, api calls it and ISDENews objects are returned
    @CrossOrigin
    @GetMapping
    public List<ISDENews> getALLISDENews(){
        return isdENewsService.getAllISDENews();
    }

}
