package Cam.IAProject.CovidUpdater.service;

import Cam.IAProject.CovidUpdater.dao.SHSENewsDataAccessService;
import Cam.IAProject.CovidUpdater.model.SHSENews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


//service class that holds a data access object in order to communicate between it and the api
@Service
public class SHSENewsService {

    private final SHSENewsDataAccessService isdENewsDao;

    @Autowired
    public SHSENewsService(@Qualifier("SHSENewsDao") SHSENewsDataAccessService isdENewsDao){
        this.isdENewsDao = isdENewsDao;
    }

    public List<SHSENews> getSHSENewsByRange(int r1, int r2){
        return isdENewsDao.selectSHSENewsRange(r1, r2);
    }

    public int addSHSENews(SHSENews isdENews){
        return isdENewsDao.insertSHSENews(isdENews);
    }

    public List<SHSENews> getAllSHSENews(){
        return isdENewsDao.selectAllSHSENews();
    }

    public Optional<SHSENews> getSHSENewsByID(UUID id){
        return isdENewsDao.selectSHSENewsByID(id);
    }

}

