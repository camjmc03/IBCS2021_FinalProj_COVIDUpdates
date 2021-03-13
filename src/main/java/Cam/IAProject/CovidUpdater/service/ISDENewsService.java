package Cam.IAProject.CovidUpdater.service;

import Cam.IAProject.CovidUpdater.dao.ISDENewsDao;
import Cam.IAProject.CovidUpdater.model.ISDENews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//service class that holds a data access object in order to communicate between it and the api
@Service
public class ISDENewsService {

    private final ISDENewsDao isdENewsDao;

    @Autowired
    public ISDENewsService(@Qualifier("ISDENewsDao") ISDENewsDao isdENewsDao){
        this.isdENewsDao = isdENewsDao;
    }

    public int addISDENews(ISDENews isdENews){
        return isdENewsDao.insertISDENews(isdENews);
    }

    public List<ISDENews> getAllISDENews(){
        return isdENewsDao.selectAllISDENews();
    }

    public Optional<ISDENews> getISDENewsByID(UUID id){
        return isdENewsDao.selectISDENewsByID(id);
    }

    public void updateISDENewsDB() {
        isdENewsDao.scrapeISDENews();
    }
}
