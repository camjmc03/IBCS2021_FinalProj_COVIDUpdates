package Cam.IAProject.CovidUpdater.dao;

import Cam.IAProject.CovidUpdater.model.ISSENews;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface ISSENewsDao {
    int insertISSENews(UUID id, ISSENews issENews);

    default int insertISSENews(ISSENews issENews){
        UUID id = UUID.randomUUID();
        return insertISSENews(id, issENews);
    }

    List<ISSENews> selectAllISSENews();

    Optional<ISSENews> selectISSENewsByID(UUID id);

    List <ISSENews> selectISSENewsRange(int r1, int r2);
}
