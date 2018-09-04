import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DownloadPage{
    public static void main(String[]args) throws InterruptedException {
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
            URL cs = null;
            try {
                cs = new URL(string);
            } catch (MalformedURLException e){
                e.printStackTrace();
            }
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(cs.openConnection().getInputStream(), StandardCharsets.UTF_8));
                String inputLine;
                String path = map.get(string) + "_UTF8_" + new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                System.out.println(path);
                File file = new File(path);
                file.createNewFile();
                FileWriter fileWriter = new FileWriter(file);
//            FileOutputStream fileOutputStream = new FileOutputStream(file);
                while ((inputLine = in.readLine()) != null) {
                    fileWriter.write(inputLine);
                }
                in.close();

                in = new BufferedReader(new InputStreamReader(cs.openConnection().getInputStream(), "GB2312"));
                path = map.get(string) + "_GB2312_" + new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                System.out.println(path);
                file = new File(path);
                file.createNewFile();
                fileWriter = new FileWriter(file);
//            FileOutputStream fileOutputStream = new FileOutputStream(file);
                while ((inputLine = in.readLine()) != null) {
                    fileWriter.write(inputLine);
                }
                in.close();
                in = new BufferedReader(new InputStreamReader(cs.openConnection().getInputStream()));
                path = map.get(string) + "_NULL_" + new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                System.out.println(path);
                file = new File(path);
                file.createNewFile();
                fileWriter = new FileWriter(file);
//            FileOutputStream fileOutputStream = new FileOutputStream(file);
                while ((inputLine = in.readLine()) != null) {
                    fileWriter.write(inputLine);
                }
                in.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        Thread.sleep(1000 * 60 * 60);
    }
}
