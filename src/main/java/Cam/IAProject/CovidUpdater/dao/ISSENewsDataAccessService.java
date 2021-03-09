package Cam.IAProject.CovidUpdater.dao;

import Cam.IAProject.CovidUpdater.model.ISSENews;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ISSENewsDataAccessService implements ISSENewsDao{
    private static List<ISSENews> DB = new ArrayList<>();
    @Override
    public int insertISSENews(UUID id, ISSENews eNews) {
        DB.add(new ISSENews(id,eNews.getDate(), eNews.getName(), eNews.getLink()));
        return 1;
    }

    @Override
    public List<ISSENews> selectAllISSENews() {
        return DB;
    }

    @Override
    public Optional<ISSENews> selectISSENewsByID(UUID id) {
        return DB.stream().filter(person -> person.getId().equals(id)).findFirst();
    }

    @Override
    public List<ISSENews> selectISSENewsRange(int r1, int r2) {
        return DB.subList(r1,r2);
    }
}
