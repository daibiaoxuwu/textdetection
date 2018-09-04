import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.util.List;

public class Jdom {
    public static void main(String[] args) {
        SAXBuilder sbBuilder = new SAXBuilder();
        Document doc = null;
        try {
            //找到Document
            doc = sbBuilder.build("C:\\Users\\d\\Desktop\\1.xht");
            //读取根元素
            Element stu = doc.getRootElement();
            //得到全部linkman子元素
            System.out.println(stu.getChildren().get(1).getChildren());
            List list = stu.getChildren("body");

            for (int i = 0; i < list.size(); i++) {
                Element element = (Element) list.get(i);
                String name = element.getChildText("name");
                String index = element.getChild("name").getAttributeValue("index");
                String email = element.getChildText("email");
                System.out.print("<name" + " index=" + index + ">" + name + "</name>");
                System.out.println("<email>" + email + "</email>");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}