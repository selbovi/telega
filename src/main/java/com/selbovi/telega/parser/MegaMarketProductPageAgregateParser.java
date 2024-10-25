package com.selbovi.telega.parser;

import java.math.BigDecimal;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.jayway.jsonpath.JsonPath;
import org.springframework.stereotype.Component;

@Component
public class MegaMarketProductPageAgregateParser implements Parser {
    @Override
    public boolean canProcess(String url, String html) {
        return url.contains("megamarket") && !url.contains("/details/");
    }

    @Override
    public BigDecimal extractPrice(String html) {
        //FIXME
        Document document = Jsoup.parse(html);

        Elements scriptElements = document.getElementsByTag("script");

        String text = null;
        for (Element element : scriptElements) {
            for (DataNode node : element.dataNodes()) {
                if (node.getWholeData().contains("window.__APP__")) {
                    text = node.getWholeData();
                    break;
                }
            }
            System.out.println("-------------------");
        }

        List<Object> goods = JsonPath.read(text, "$..goods");

        Elements elements = document.selectXpath("//script");



        String price = elements.attr("content");
        return new BigDecimal(price);
    }
}
