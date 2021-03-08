package Cam.IAProject.CovidUpdater.dao;

import Cam.IAProject.CovidUpdater.model.SHSENews;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SHSEnewsDao {
    int insertSHSENews(UUID id, SHSENews SHSENews);

    default int insertSHSENews(SHSENews SHSENews){
        UUID id = UUID.randomUUID();
        return insertSHSENews(id, SHSENews);
    }

    List<SHSENews> selectAllSHSENews();

    Optional<SHSENews> selectSHSENewsByID(UUID id);

    List <SHSENews> selectSHSENewsRange();
}
