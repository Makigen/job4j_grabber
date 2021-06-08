package ru.job4j.grabber.utils;

import org.junit.Test;
import ru.job4j.grabber.Post;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PostDetailsLoaderTest {

    @Test
    public void getTextTest() {
        Post post = PostDetailsLoader.load("https://www.sql.ru/forum/1336341/java-razrabotchik-v-finteh-kompaniu");
        assertThat(post.getText(), is("Вакансия: Java разработчик (Middle/Senior) Город: вся РФ Компания: Центр Орбита Формат работы: возможна полная или частичная удаленка Занятость: полная З/п: от оклад от 200 000 до 350 000 руб. на руки + годовая премия Соцпакет: ДМС со стоматологией Ищу Java разработчиков (Middle/Senior) для работы над приложениями известного банка. Вакансий много – есть из чего выбрать;) Можно поработать над приложениями, которые позволяют розничным и СМБ клиентам совершать платежи и переводы, оформлять бизнесовые и ипотечные сделки, брать дистанционно кредиты, производить платежи через QR коды - это лишь малая часть, приложений много =) Стек: Java 8-11, Kotlin, Spring/Springboot, PostgreSQL/Oracle, Kafka, Redis, Maven/Gradle, Docker/Kubernetes(или OpenShift). Методология разработки Scrum, СI/CD, микросервисы. Задачи: • Участие в проектировании новых сервисов; • Проведение код-ревью; • Документирование кода; • Интеграция со сторонними сервисами Откликнуться можно @it_fairy в Telegram:)"));
    }

    @Test
    public void getCreatedTest() {
        Post post = PostDetailsLoader.load("https://www.sql.ru/forum/1336341/java-razrabotchik-v-finteh-kompaniu");
        SqlRuDateTimeParser sqlRuDateTimeParser = new SqlRuDateTimeParser();
        LocalDateTime rsl = sqlRuDateTimeParser.parse("25 май 21, 15:10");
        assertThat(post.getCreated(), is(rsl));
    }
}
