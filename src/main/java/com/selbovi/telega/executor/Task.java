package com.selbovi.telega.executor;

import java.time.ZonedDateTime;
import java.util.List;
import com.selbovi.telega.entity.ProductPage;
import com.selbovi.telega.entity.Result;
import com.selbovi.telega.http.HtmlProvider;
import com.selbovi.telega.parser.Parser;
import com.selbovi.telega.repository.ProductPageRepository;
import com.selbovi.telega.repository.ProductRepository;
import com.selbovi.telega.repository.ResultRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Task {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductPageRepository productPageRepository;

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private List<Parser> parsers;

    @Autowired
    private HtmlProvider htmlProvider;

    //TODO REPORT
    public void run() {
        productRepository.findAll().forEach(product -> {
            var productLinks = productPageRepository.findProductPagesByProductName(product.getName());
            productLinks.forEach(link -> {
                var linkVisitResult = visitLink(link);

                resultRepository.save(linkVisitResult);
            });
        });
    }

    public Result visitLink(ProductPage link) {
        var linkVisitResult = new Result();
        linkVisitResult.setProductPage(link);
        linkVisitResult.setCheckTime(ZonedDateTime.now());
        String html = null;
        try {
            var url = link.getUrl();

            final String htmlStr = html = htmlProvider.getHtml(url);

            var parser = parsers
                    .stream()
                    .filter(htmlParser -> htmlParser.canProcess(url, htmlStr))
                    .findFirst()
                    .orElseThrow();//FIXME

            var price = parser.extractPrice(html);

            linkVisitResult.setPrice(price);
        } catch (Exception e) {
            linkVisitResult.setErrorHtml(html); //fixme
            linkVisitResult.setError(
                    e.getMessage() +
                            System.lineSeparator() +
                            ExceptionUtils.getStackTrace(e)
            );
            log.error("bad", e);
        }
        return linkVisitResult;
    }

}
