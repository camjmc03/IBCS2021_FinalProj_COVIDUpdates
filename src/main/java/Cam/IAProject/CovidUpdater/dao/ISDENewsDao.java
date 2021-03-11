package Cam.IAProject.CovidUpdater.dao;

import Cam.IAProject.CovidUpdater.model.ISDENews;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface ISDENewsDao {
    int insertISDENews(UUID id, ISDENews isdENews);

    //default method to add id when no id is given
    default int insertISDENews(ISDENews isdENews){
        UUID id = UUID.randomUUID();
        return insertISDENews(id, isdENews);
    }

    List<ISDENews> selectAllISDENews();

    Optional<ISDENews> selectISDENewsByID(UUID id);

    List <ISDENews> selectISDENewsRange(int r1, int r2);
}
