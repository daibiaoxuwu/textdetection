
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import java.sql.*;

public class Xmldb2 {

    private static Connection c;
    private static Statement stmt;

    public static void work(String url, String channel) {
        System.out.println(url);
        System.out.println(channel);
        try {
//            Document doc = Jsoup.connect(url).get();
//            JsoupFull.readDoc2(doc, channel);
//
//            InputStream inStream = new URL(url).openStream();
//            doc = Jsoup.parse(inStream, "UTF-8", url);
//        System.out.println(doc);
//            JsoupFull.readDoc(doc, channel);
            Document doc = Jsoup.connect(url).get();
            JsoupFull.readDoc3(doc, channel);

            InputStream inStream = new URL(url).openStream();
            doc = Jsoup.parse(inStream, "UTF-8", url);
            JsoupFull.readDoc3(doc, channel);

            System.out.println("\n\n\n\n\n");

            for (NewsItem newsItem : JsoupFull.arrayList) {
                System.out.println(newsItem);
            }
            System.out.println(JsoupFull.arrayList.size());

            stmt = c.createStatement();
            for (NewsItem newsItem : JsoupFull.arrayList) {
                try {
                    String sql = "INSERT INTO NEWS (TITLE,LINK,AUTHOR,PUBDATE,CATEGORY,COMMENTS,DESCRIPTION, CHANNEL)" +
                            newsItem.dbString();
                    stmt.executeUpdate(sql);
                    System.out.println("ADD: " + newsItem.getTitle());
                } catch (org.sqlite.SQLiteException e) {
                    System.out.println("OLD: " + newsItem.getTitle());
                }
            }
        } catch (org.jsoup.HttpStatusException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

    public static void main(String args[]) {
        c = null;
        stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test3.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            Map<String, String> map = new HashMap<>();
            map.put("http://news.qq.com/newsgn/rss_newsgn.xml","新闻频道");
            map.put("http://ent.qq.com/movie/rss_movie.xml","娱乐频道");
            map.put("http://stock1.finance.qq.com/stock/dpfx/rss_dpfx.xml", "证券频道");
            map.put("http://finance.qq.com/scroll/rss_scroll.xml", "财经频道");
            map.put("http://tech.qq.com/web/rss_web.xml", "科技频道");
            map.put("http://auto.qq.com/comment/zjpc/rss_zjpc.xml", "汽车频道");
            map.put("http://sports.qq.com/rss_newssports.xml", "体育频道");
            map.put("http://games.qq.com/ntgame/rss_ntgame.xml", "游戏频道");
            map.put("http://edu.qq.com/gaokao/rss_gaokao.xml", "教育频道");
            map.put("http://bb.qq.com/original/rss_original.xml", "视频频道");
            map.put("http://book.qq.com/origin/rss_origin.xml", "读书频道");
            map.put("http://lady.qq.com/qqstar/rss_qqstart.xml", "女性频道");
            map.put("http://baby.qq.com/bbs/rss_babybbs.xml", "育儿频道");
            map.put("http://astro.qq.com/12star/rss_12star.xml", "星座频道");
            map.put("http://xian.qq.com/xanews/rss_news.xml", "西安地方站");
            map.put("http://digi.qq.com/mobile/manufacturer/rss_manufacturer.xml", "手机频道");
            map.put("http://comic.qq.com/cosplay/rss_cosplay.xml", "动漫频道");
            map.put("http://luxury.qq.com/staff/rss_staff.xml", "时尚频道");
            map.put("http://joke.qq.com/jokeflash/rss_flash.xml", "笑话频道");
            map.put("http://kid.qq.com/youxi/rss_game.xml", "儿童频道");
            map.put("http://weather.qq.com/zixun/rss_fyzx.xml", "天气频道");

            for (String string : map.keySet()) {
                work(string, map.get(string));
            }
            c.commit();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

}
