package com.selbovi.telega.config;

import com.selbovi.telega.executor.Task;
import com.selbovi.telega.http.HtmlProvider;
import com.selbovi.telega.parser.Parser;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
        basePackageClasses = {
                Task.class,
                Parser.class,
                HtmlProvider.class
        }
)
public class HtmlProcessConfig {
}
