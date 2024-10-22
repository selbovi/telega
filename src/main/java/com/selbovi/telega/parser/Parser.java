package com.selbovi.telega.parser;

import java.math.BigDecimal;

public interface Parser {

    boolean canProcess(String url);

    BigDecimal extractPrice(String html);

}
