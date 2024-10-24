package com.selbovi.telega.http;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HtmlProvider {

    public String getHtml(String url) {
        Document document = Jsoup.parse(url);
        return document.html();
    }

}
