package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.Parse;
import ru.job4j.grabber.Post;
import ru.job4j.grabber.utils.SqlRuDateTimeParser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SqlRuParse implements Parse {

    @Override
    public List<Post> list(String link) {
        List<Post> list = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(link).get();
            Elements row = doc.select(".postslisttopic");
            for (Element td : row) {
                Element href = td.child(0);
                list.add(detail(href.attr("href")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Post detail(String link) {
        Post post = new Post();
        try {
            Document doc = Jsoup.connect(link).get();
            String text = doc.select(".msgBody").get(1).text();
            String footer = doc.select(".msgFooter").get(0).text();
            String name = doc.select(".messageHeader").get(0).text().split(" \\[")[0];
            int index = footer.indexOf(":");
            SqlRuDateTimeParser sqlRuDateTimeParser = new SqlRuDateTimeParser();
            LocalDateTime created = sqlRuDateTimeParser.parse(footer.substring(0, index + 3));
            post.setLink(link);
            post.setName(name);
            post.setText(text);
            post.setCreated(created);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return post;
    }
}