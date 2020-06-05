import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;

public class Third {
    private static String baseURL = "https://student.mirea.ru";

    public static void main(String[] args) {

        try {
            Document document = Jsoup.connect(baseURL + "/media/photo/").get();//С помощью get можно пользоваться try catch

            Elements elementsDiv = document.select("div.js-slide");//Ищет по тегу

            for (Element div: elementsDiv) {
                Element h3 = div.child(1).child(0);
                downloadImages(div.child(2).attr("href"), h3.text());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void downloadImages(String link, String nameDirectory){
        String basePath = "C:/Users/evg/Desktop/photos/";

        try {
            nameDirectory = nameDirectory.replaceAll("\"", "'");
            System.out.println(nameDirectory);
            new File(basePath + nameDirectory).mkdirs();

            Document document = Jsoup.connect(baseURL + link).get();

            Elements elementsImg = document.select("div.row img.img-fluid");

            for (Element img: elementsImg) {
                URL url = new URL(baseURL + img.attr("src"));

                String fileName = url.getFile();
                String destName = basePath + nameDirectory + fileName.substring(fileName.lastIndexOf("/"));

                InputStream is = url.openStream();
                OutputStream os = new FileOutputStream(destName);

                byte[] b = new byte[2048];
                int length;

                while ((length = is.read(b)) != -1) {
                    os.write(b, 0, length);
                }

                is.close();
                os.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
