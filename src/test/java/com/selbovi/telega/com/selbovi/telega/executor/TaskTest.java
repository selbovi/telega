package com.selbovi.telega.com.selbovi.telega.executor;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import com.selbovi.telega.entity.ProductPage;
import com.selbovi.telega.entity.Result;
import com.selbovi.telega.executor.Task;
import com.selbovi.telega.http.HtmlProvider;
import com.selbovi.telega.repository.ProductPageRepository;
import com.selbovi.telega.repository.ProductRepository;
import com.selbovi.telega.repository.ResultRepository;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(properties =
        {
                "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration,org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration",
        })
@SpringBootTest(classes = TestConfiguration.class)
public class TaskTest {

    @Autowired
    private Task task;
    @MockBean
    private HtmlProvider htmlProvider;
    @MockBean
    private ProductRepository productRepository;
    @MockBean
    private ProductPageRepository productPageRepository;
    @MockBean
    private ResultRepository resultRepository;

    @Test
    void getPrice_MegaMarker_ProductPage_Single_Test() throws IOException {
        String rawHtml = getHtml("onyx-galileo-single.html");

        String tidyHtml = Jsoup.parse(rawHtml).html();
        when(htmlProvider.getHtml(any())).thenReturn(tidyHtml);

        ProductPage productPage = new ProductPage();
        productPage.setUrl(
                "https://megamarket.ru/catalog/details/elektronnaya-kniga-onyx-boox-galileo-chernyy-onyx-galileo-black-600011888656_52/#?related_search=onyx%20boox%20galileo");

        Result result = task.visitLink(productPage);
        assertEquals(new BigDecimal(26490), result.getPrice());
    }

    @Test
    @Disabled
    void getPrice_MegaMarker_ProductPage_Aggregator_Test() throws IOException {
        String rawHtml = getHtml("onyx-galileo-aggregator.html");

        String tidyHtml = Jsoup.parse(rawHtml).html();
        when(htmlProvider.getHtml(any())).thenReturn(tidyHtml);

        ProductPage productPage = new ProductPage();
        productPage.setUrl("https://megamarket.ru/catalog/?q=onyx%20boox%20galileo");

        Result result = task.visitLink(productPage);
        assertEquals(new BigDecimal(26490), result.getPrice());
    }

    private String getHtml(String fileName) throws IOException {
        String folder = "/market/megamarket/";
        return IOUtils.resourceToString(folder + fileName, StandardCharsets.UTF_8);
    }
}