package ru.job4j.grabber.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.job4j.grabber.Post;

import java.io.IOException;
import java.time.LocalDateTime;

public class PostDetailsLoader {

    public static Post load(String url) {
        Post post = new Post();
        try {
            Document doc = Jsoup.connect(url).get();
            String text = doc.select(".msgBody").get(1).text();
            String footer = doc.select(".msgFooter").get(0).text();
            int index = footer.indexOf(":");
            SqlRuDateTimeParser sqlRuDateTimeParser = new SqlRuDateTimeParser();
            LocalDateTime created = sqlRuDateTimeParser.parse(footer.substring(0, index + 3));
            post.setText(text);
            post.setCreated(created);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return post;
    }

    public static void main(String[] args) {
        Post post = load("https://www.sql.ru/forum/1336341/java-razrabotchik-v-finteh-kompaniu");
        System.out.println(post.getText());
        System.out.println(post.getCreated());
    }
}
