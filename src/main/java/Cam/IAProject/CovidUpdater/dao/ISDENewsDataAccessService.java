package Cam.IAProject.CovidUpdater.dao;

import Cam.IAProject.CovidUpdater.model.ISDENews;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//Repository annotation sets up the tag to be used in the @Qualifier annotation in the corresponding service class
@Repository("ISDENewsDao")

public class ISDENewsDataAccessService implements ISDENewsDao {

    private static List<ISDENews> DB = new ArrayList<>();
    //TODO: connect to database
    @Override
    //id is needed here but if none given then default method from implemented interface generates a random one
    public int insertISDENews(UUID id, ISDENews eNews) {
        DB.add(new ISDENews(id,eNews.getDate(), eNews.getName(), eNews.getLink()));
        return 1;
    }

    @Override
    public List<ISDENews> selectAllISDENews() {
        return DB;
    }

    @Override
    public Optional<ISDENews> selectISDENewsByID(UUID id) {
        return DB.stream()
                .filter(ISDENews -> ISDENews.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<ISDENews> selectISDENewsRange(int r1, int r2) {
        return DB.subList(r1,r2);
    }
}
