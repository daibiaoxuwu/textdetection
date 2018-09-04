import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


class NewsItem{
    private String title;
    private         String link ;
    private String  author ;
    private         String pubdate ;
    private String category ;
    private             String comments;
    private     String description;

    public String getChannel() {
        return channel;
    }

    private     String channel;

    @Override
    public String toString() {
        return "NewsItem{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", author='" + author + '\'' +
                ", pubdate='" + pubdate + '\'' +
                ", category='" + category + '\'' +
                ", comments='" + comments + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public void setTitle(String title) {
        if(TestEncoding.getEncoding(title).equals("GB2312"))
            this.title = title;
    }
    public void setLink(String link) {
        if(this.link == null || this.link.equals(""))
            this.link = link;
    }
    public void setAuthor(String author) {
        if(this.author == null || this.author.equals(""))
            this.author = author;
    }
    public void setPubdate(String pubdate) {
        if(this.pubdate == null || this.pubdate.equals(""))
            this.pubdate = pubdate;
    }
    public void setComments(String comments) {
        if(this.comments == null || this.comments.equals(""))
            this.comments = comments;
    }
    public void setCategory(String category) {
        if(this.category == null || this.category.equals(""))
            this.category = category;
    }
    public void setDescription(String description) {
        if(TestEncoding.getEncoding(description).equals("GB2312"))
            this.description = description;
    }
    public void setChannel(String channel) {
        if(this.channel == null || this.channel.equals(""))
            this.channel = channel;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getAuthor() {
        return author;
    }

    public String getPubdate() {
        return pubdate;
    }

    public String getCategory() {
        return category;
    }

    public String getComments() {
        return comments;
    }

    public String getDescription() {
        return description;
    }

    public String dbString(){
        return "VALUES ('" + title + "', '" + link + "', '"+ author + "', '"+ pubdate + "', '"+ category + "', '"+ comments + "', '"+ description+"', '"+channel+ "');";
    }
}

public class JsoupFull {
    static ArrayList<NewsItem> arrayList = new ArrayList<>();


    static void readDoc3(Document doc, String channel) throws Exception {
        for (Element element : doc.select("item")) {
                NewsItem newsItem = new NewsItem();
                newsItem.setTitle(element.getElementsByTag("title").text());
                newsItem.setLink(element.getElementsByTag("link").text());
                newsItem.setAuthor(element.getElementsByTag("author").text());
                newsItem.setPubdate(element.getElementsByTag("pubdate").text());
                newsItem.setCategory(element.getElementsByTag("category").text());
                newsItem.setComments(element.getElementsByTag("comments").text());
                newsItem.setDescription(element.getElementsByTag("description").text());
                newsItem.setChannel(channel);
                if(newsItem.getTitle()!=null
                        &&newsItem.getLink()!=null
                        &&newsItem.getAuthor()!=null
                        &&newsItem.getPubdate()!=null
                        &&newsItem.getCategory()!=null
                        &&newsItem.getComments()!=null
                        &&newsItem.getDescription()!=null
                        &&newsItem.getChannel()!=null) {
                    arrayList.add(newsItem);
                }
        }
    }

    static void readDoc(Document doc, String channel) throws Exception {

        for (Element element : doc.select("item")) {
            boolean flag=false;
            for(NewsItem newsItem : arrayList){
                if(newsItem.getPubdate() .equals( element.getElementsByTag("pubdate").text())){
                    flag=true;
                    newsItem.setTitle(element.getElementsByTag("title").text());
                    newsItem.setLink(element.getElementsByTag("link").text());
                    newsItem.setAuthor(element.getElementsByTag("author").text());
                    newsItem.setCategory(element.getElementsByTag("category").text());
                    newsItem.setComments(element.getElementsByTag("comments").text());
                    newsItem.setDescription(element.getElementsByTag("description").text());
                    newsItem.setChannel(channel);
                }
            }
            if(!flag){
                NewsItem newsItem = new NewsItem();
                newsItem.setTitle(element.getElementsByTag("title").text());
                System.out.println(element.getElementsByTag("title").text());
                newsItem.setLink(element.getElementsByTag("link").text());
                newsItem.setLink(element.getElementsByTag("link").text());
                newsItem.setAuthor(element.getElementsByTag("author").text());
                newsItem.setPubdate(element.getElementsByTag("pubdate").text());
                newsItem.setCategory(element.getElementsByTag("category").text());
                newsItem.setComments(element.getElementsByTag("comments").text());
                newsItem.setDescription(element.getElementsByTag("description").text());
                newsItem.setChannel(channel);
                System.out.println(element.getElementsByTag("description").text());
                System.out.println(newsItem);
                arrayList.add(newsItem);
            }
        }
    }
    static void readDoc2(Document doc, String channel) throws Exception {
        Map<String, NewsItem> map = new HashMap<>();

        for (Element element : doc.select("item")) {
            NewsItem newsItem = new NewsItem();
//            newsItem.setTitle(element.getElementsByTag("title").text());
//            System.out.println(element.getElementsByTag("title").text());
            newsItem.setLink(element.getElementsByTag("link").text());
            newsItem.setLink(element.getElementsByTag("link").text());
            newsItem.setAuthor(element.getElementsByTag("author").text());
            newsItem.setPubdate(element.getElementsByTag("pubdate").text());
            newsItem.setCategory(element.getElementsByTag("category").text());
            newsItem.setComments(element.getElementsByTag("comments").text());
//            System.out.println(element.getElementsByTag("description").text());
//            System.out.println(newsItem);
            newsItem.setChannel(channel);
            arrayList.add(newsItem);
        }
    }

    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.connect("http://ent.qq.com/movie/rss_movie.xml").get();
//        readDoc2(doc);

        String strURL = "http://ent.qq.com/movie/rss_movie.xml";
        InputStream inStream = new URL(strURL).openStream();
        doc = Jsoup.parse(inStream, "UTF-8", strURL);
//        System.out.println(doc);
//        readDoc(doc);

        for (NewsItem newsItem : arrayList) {
            System.out.println(newsItem);
        }
        System.out.println(arrayList.size());

    }
}


