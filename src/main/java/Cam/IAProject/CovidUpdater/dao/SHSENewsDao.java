package Cam.IAProject.CovidUpdater.dao;

import Cam.IAProject.CovidUpdater.model.SHSENews;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface SHSENewsDao {
    int insertSHSENews(UUID id, SHSENews isdENews);

    //default method to add id when no id is given
    default int insertSHSENews(SHSENews isdENews){
        UUID id = UUID.randomUUID();
        return insertSHSENews(id, isdENews);
    }

    List<SHSENews> selectAllSHSENews();

    Optional<SHSENews> selectSHSENewsByID(UUID id);

    List <SHSENews> selectSHSENewsRange(int r1, int r2);

}
