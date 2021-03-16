package Cam.IAProject.CovidUpdater.dao;

import Cam.IAProject.CovidUpdater.model.SHSENews;
import Cam.IAProject.CovidUpdater.model.SHSENewsScraper;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import java.sql.*;

//Repository annotation sets up the tag to be used in the @Qualifier annotation in the corresponding service class
@Repository("SHSENewsDao")

public class SHSENewsDataAccessService implements SHSENewsDao {

    private static List<SHSENews> DB = new ArrayList<>();
    @Override
    //id is needed here but if none given then default method from implemented interface generates a random one
    public int insertSHSENews(UUID id, SHSENews eNews) {
        DB.add(new SHSENews(id,eNews.getDate(), eNews.getName(), eNews.getLink()));
        return 1;
    }

    @Override
    public List<SHSENews> selectAllSHSENews() {
        try {
            updateDB();
        }catch(MalformedURLException e){
            System.out.println("Unable to update SHS ENews Articles. Could not connect to the SHS ENews Archive: " + e);
        }catch(IOException e){
            e.printStackTrace();
        }
        return DB;
    }

    @Override
    public Optional<SHSENews> selectSHSENewsByID(UUID id) {
        try {
            updateDB();
        }catch(MalformedURLException e){
            System.out.println("Unable to update SHS ENews Articles. Could not connect to the SHS ENews Archive: " + e);
        }catch(IOException e){
            e.printStackTrace();
        }
        return DB.stream()
                .filter(SHSENews -> SHSENews.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<SHSENews> selectSHSENewsRange(int r1, int r2) {
        try {
            updateDB();
        }catch(MalformedURLException e){
            System.out.println("Unable to update SHS ENews Articles. Could not connect to the SHS ENews Archive: " + e);
        }catch(IOException e){
            e.printStackTrace();
        }
        return DB.subList(r1,r2);
    }


    private void updateDB() throws IOException {
        SHSENewsScraper scraper = new SHSENewsScraper("https://apps.issaquah.wednet.edu/apidev/enews/lists/18/posts");
        ArrayList<SHSENews> articles = scraper.getObjects();
        ArrayList<SHSENews> articlesToAdd = new ArrayList<>();
        for (int i = 0; i < articles.size(); i++){
            if (DB.size() > 0) {
                for (SHSENews enews: DB) {
                    if (!enews.getDate().equals(articles.get(i).getDate()) || !enews.getName().equals(articles.get(i).getName())){
                        articlesToAdd.add(articles.get(i));
                        DBInsertSHSENews(articles.get(i));
                    }
                }
            }else{
                DB.add(articles.get(i));
                DBInsertSHSENews(articles.get(i));
            }

        }
        DB.addAll(articlesToAdd);
    }

    private void DBInsertSHSENews(SHSENews enews) {
        Connection c;
        Statement stmt;
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\camjm\\OneDrive\\Desktop\\Documents\\school stuff\\CS Projects\\IA\\Product\\Backend\\CovidUpdater\\src\\main\\java\\Cam\\IAProject\\CovidUpdater\\database.db");

            stmt = c.createStatement();
            String sql = "INSERT INTO SHSENews (ID, date, name, link) " +
                    "VALUES (\'" + enews.getId().toString() + "\', \'" +
                    enews.getDate() + "\', \'" +
                    enews.getName() + "\', \'" +
                    enews.getLink() + "\');";
            stmt.executeUpdate(sql);
            c.close();
        } catch (ClassNotFoundException e) {
            System.out.println("could not load JDBC: " + e);
        } catch( SQLException e){
            System.out.println("SQL error when inserting SHS ENews into Database: " + e);
        }
    }
}
