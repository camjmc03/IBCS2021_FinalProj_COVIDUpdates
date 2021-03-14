package Cam.IAProject.CovidUpdater.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.*;

public class ISDENewsScraper {

    private final URL url;
    private final String urlString;


    public ISDENewsScraper(URL url){
        this.url = url;
        this.urlString = url.toString();
    }

    public ISDENewsScraper(String urlString) throws MalformedURLException {
        this.url = new URL(urlString);
        this.urlString = urlString;
    }

    private String getWeb() throws IOException {

        //getting input stream
        URLConnection con = url.openConnection();
        InputStream is = con.getInputStream();

        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String rawHTML = "";
        while ((line = br.readLine()) != null) {
            rawHTML += line + "\n";

        }
        return rawHTML;
    }

    public ArrayList<ISDENews> getObjects() throws IOException {
        //create an ArrayList to return later of ENews Articles
        ArrayList<ISDENews> arr = new ArrayList<>();
        //create a string that holds the html of the ENews Archive page
        String docHtml = getWeb();

        //regex to find the json object that holds all the ENews Articles information
        Pattern pattern = Pattern.compile("(?<=var model = \\{\"TotalCount\":20,\"SortFacets\":\\[],\"FacetKeyApplied\":false,\"Items\":).*([^\\n\\r]*)");
        Matcher matcher = pattern.matcher(docHtml);
        if(matcher.find()){
            //create a string that holds the raw json
            String objectsStr = matcher.group();

            //more regexs?... regexi?... more regular expressions that gather title and date of each article and add them to ENews objects in the ArrayList above
            //find and add all titles, creates objects to be returned and adds the titles to them
            pattern = Pattern.compile("(?<=\"Title\":\")(.*?)(?=\",)");
            matcher = pattern.matcher(objectsStr);
            while(matcher.find()) {
                arr.add(new ISDENews(UUID.randomUUID(),"", matcher.group(), "https://www.issaquah.wednet.edu/news/ENews/archive"));
            }
            //find and add all dates to the objects created above
            pattern = Pattern.compile("(?<=\"SendDate\":\")(.*?)(?=T)");
            matcher = pattern.matcher(objectsStr);
            int i = 0;
            while(matcher.find()){
                arr.get(i).setDate(matcher.group());
                i++;
            }
        }else{
            System.out.println("issue with parsing ENews article information from ISD Archives");
        }
        return arr;
    }
}