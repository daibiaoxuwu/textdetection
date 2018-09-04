import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sun.activation.registries.LogSupport.log;

public class JsoupTest {
    public static void main(String[] args) throws Exception {

        Document doc = Jsoup.connect("http://news.qq.com/society_index.shtml").get();

        Elements newsHeadlines = doc.select(".Q-tpList");
        Pattern pattern = Pattern.compile("src=\"([^\"]+)\"");
        for (Element headline : newsHeadlines) {
            Elements elements = headline.select(".Q-tpWrap .text .linkto");
            if (elements == null) continue;
            if (elements.first() == null) continue;
            System.out.println(elements.first().text());
            System.out.println(elements.first().absUrl("abs:href"));

            elements = headline.select(".Q-tpWrap .pic img");
            if (elements == null) continue;
            if (elements.first() == null) continue;
            String string = elements.first().toString();
            Matcher matcher = pattern.matcher(string);
            if (matcher.find())
                System.out.println(matcher.group(1));
            else
                throw (new Exception());
//                System.out.println("E:" + string);
        }

        newsHeadlines = doc.select(".Q-pList");
        Pattern pattern2 = Pattern.compile("href=\"([^\"]+)\"");
        for (Element headline : newsHeadlines) {
            Element element = headline.getElementsByClass("content").first();
            if (element == null) continue;
            System.out.println(element.text());
            String string = element.toString();
            Matcher matcher = pattern2.matcher(string);
            if (matcher.find())
                System.out.println(matcher.group(1));
            else
                throw (new Exception());
            Elements elements = element.getElementsByTag("ul").first().getElementsByTag("li");
            for (Element element1 : elements) {
                string = element1.getElementsByTag("a").first().getElementsByTag("img").first().toString();
                matcher = pattern.matcher(string);
                if (matcher.find())
                    System.out.println(matcher.group(1));
                else
                    throw (new Exception());
            }
        }
    }
}


