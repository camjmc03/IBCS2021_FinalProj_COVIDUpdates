package Cam.IAProject.CovidUpdater.service;

import Cam.IAProject.CovidUpdater.dao.ISDENewsDao;
import Cam.IAProject.CovidUpdater.dao.ISDENewsDataAccessService;
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

    private final ISDENewsDataAccessService isdENewsDao;

    @Autowired
    public ISDENewsService(@Qualifier("ISDENewsDao") ISDENewsDataAccessService isdENewsDao){
        this.isdENewsDao = isdENewsDao;
    }

    public List<ISDENews> getISDENewsByRange(int r1, int r2){
        return isdENewsDao.selectISDENewsRange(r1, r2);
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

}
