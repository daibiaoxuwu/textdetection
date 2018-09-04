import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Test4 {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://news.qq.com/world_index.shtml");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document data = builder.parse(inputStream);

            if (data != null) {
                ArrayList<FeedItem> feedItems = new ArrayList<>();
                Element root = data.getDocumentElement();
                Node channel = root.getChildNodes().item(1);
                NodeList items = channel.getChildNodes();
                for (int i = 0; i < items.getLength(); i++) {
                    Node cureentchild = items.item(i);
                    if (cureentchild.getNodeName().equalsIgnoreCase("item")) {
                        FeedItem item = new FeedItem();
                        NodeList itemchilds = cureentchild.getChildNodes();
                        for (int j = 0; j < itemchilds.getLength(); j++) {
                            Node cureent = itemchilds.item(j);
                            if (cureent.getNodeName().equalsIgnoreCase("title")) {
                                item.setTitle(cureent.getTextContent());
                            } else if (cureent.getNodeName().equalsIgnoreCase("description")) {
                                item.setDescription(cureent.getTextContent());
                            } else if (cureent.getNodeName().equalsIgnoreCase("pubDate")) {
                                item.setPubDate(cureent.getTextContent());
                            } else if (cureent.getNodeName().equalsIgnoreCase("link")) {
                                item.setLink(cureent.getTextContent());
                            } else if (cureent.getNodeName().equalsIgnoreCase("media:thumbnail")) {
                                //this will return us thumbnail url
                                String url2 = cureent.getAttributes().item(0).getTextContent();
                                item.setThumbnailUrl(url2);
                            }
                        }
                        feedItems.add(item);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
