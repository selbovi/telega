package com.selbovi.telega.parser;

import java.math.BigDecimal;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class MegaMarketProductPageParser implements Parser {
    @Override
    public boolean canProcess(String url) {
        return url.contains("megamarket");
    }

    @Override
    public BigDecimal extractPrice(String html) {
        Document document = Jsoup.parse(html);
        Elements elements = document.selectXpath("//meta[@itemprop='price']");

        String price = elements.attr("content");
        return new BigDecimal(price);
    }
}
