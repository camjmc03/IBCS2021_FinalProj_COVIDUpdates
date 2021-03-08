package Cam.IAProject.CovidUpdater.model;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ISSENews {
    int insertISSENews(UUID id, ISSENews issENews);

    default int insertISSENews(ISSENews issENews){
        UUID id = UUID.randomUUID();
        return insertISSENews(id, issENews);
    }

    List<ISSENews> selectAllISSENews();

    Optional<ISSENews> selectISSENewsByID(UUID id);

    List <ISSENews> selectISSENewsRange();
}
