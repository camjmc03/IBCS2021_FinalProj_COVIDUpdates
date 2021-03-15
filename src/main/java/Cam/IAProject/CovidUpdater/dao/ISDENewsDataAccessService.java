package Cam.IAProject.CovidUpdater.dao;

import Cam.IAProject.CovidUpdater.model.ISDENews;
import Cam.IAProject.CovidUpdater.model.ISDENewsScraper;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import java.sql.*;

//Repository annotation sets up the tag to be used in the @Qualifier annotation in the corresponding service class
@Repository("ISDENewsDao")

public class ISDENewsDataAccessService implements ISDENewsDao {

    private static List<ISDENews> DB = new ArrayList<>();
    @Override
    //id is needed here but if none given then default method from implemented interface generates a random one
    public int insertISDENews(UUID id, ISDENews eNews) {
        DB.add(new ISDENews(id,eNews.getDate(), eNews.getName(), eNews.getLink()));
        return 1;
    }

    @Override
    public List<ISDENews> selectAllISDENews() {
        try {
            updateDB();
        }catch(MalformedURLException e){
            System.out.println("Unable to update ISD ENews Articles. Could not connect to the ISD ENews Archive: " + e);
        }catch(IOException e){
            e.printStackTrace();
        }
        return DB;
    }

    @Override
    public Optional<ISDENews> selectISDENewsByID(UUID id) {
        try {
            updateDB();
        }catch(MalformedURLException e){
            System.out.println("Unable to update ISD ENews Articles. Could not connect to the ISD ENews Archive: " + e);
        }catch(IOException e){
            e.printStackTrace();
        }
        return DB.stream()
                .filter(ISDENews -> ISDENews.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<ISDENews> selectISDENewsRange(int r1, int r2) {
        try {
            updateDB();
        }catch(MalformedURLException e){
            System.out.println("Unable to update ISD ENews Articles. Could not connect to the ISD ENews Archive: " + e);
        }catch(IOException e){
            e.printStackTrace();
        }
        return DB.subList(r1,r2);
    }


    private void updateDB() throws IOException {
        ISDENewsScraper scraper = new ISDENewsScraper("https://apps.issaquah.wednet.edu/apidev/enews/lists/1/posts");
        ArrayList<ISDENews> articles = scraper.getObjects();
        System.out.println(articles);//todo:delete
        for (int i = 0; i < articles.size(); i++){
            if (DB.size() == 0) {
                for (ISDENews enews: DB) {
                    if (!enews.getDate().equals(articles.get(i).getDate()) || !enews.getName().equals(articles.get(i).getName())){
                        DB.add(articles.get(i));
                        DBInsertISDENews(articles.get(i));
                    }
                }
            }else{
                DB.add(articles.get(i));
                DBInsertISDENews(articles.get(i));
            }

        }
        System.out.println(DB);//todo:delete
    }

    private void DBInsertISDENews(ISDENews enews) {
        Connection c;
        Statement stmt;
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\camjm\\OneDrive\\Desktop\\Documents\\school stuff\\CS Projects\\IA\\Product\\Backend\\CovidUpdater\\src\\main\\java\\Cam\\IAProject\\CovidUpdater\\database.db");

            stmt = c.createStatement();
            String sql = "INSERT INTO ISDENews (ID, date, name, link) " +
                    "VALUES (\'" + enews.getId().toString() + "\', \'" +
                    enews.getDate() + "\', \'" +
                    enews.getName() + "\', \'" +
                    enews.getLink() + "\');";
            stmt.executeUpdate(sql);
            c.close();
        } catch (ClassNotFoundException e) {
            System.out.println("could not load JDBC: " + e);
        } catch( SQLException e){
            System.out.println("SQL error when inserting ISD ENews into Database: " + e);
        }
    }
}
