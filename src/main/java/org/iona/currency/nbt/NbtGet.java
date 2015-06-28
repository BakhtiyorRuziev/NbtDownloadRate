package org.iona.currency.nbt;

import org.iona.currency.models.Rate;
import org.iona.currency.nbt.models.Currency;
import org.iona.currency.repository.RateRepository;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bakhtiyor Ruziev on 21.06.15.
 * <p>
 * Download data from National Bank database
 */
@Component
public class NbtGet {

    private final Logger logger = LoggerFactory.getLogger(NbtGet.class);

    private int startDate = 2001;

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    @Autowired
    private RateRepository rateRepository;

    private Currency get(String date) {

        String url = String.format("http://nbt.tj/ru/kurs/?c=4&id=28&lg=en&d=%s&export=xmlout", date);
        RestTemplate restTemplate = new RestTemplate();
        String currency = restTemplate.getForObject(url, String.class);
        StringReader stringReader = new StringReader(currency);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Currency.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (Currency) unmarshaller.unmarshal(stringReader);
        } catch (Exception e) {
            logger.warn("Date:{} error:{}", date, e.getMessage());
        }
        return null;

    }

    public List<String> dateList() {
        List<String> strings = new ArrayList<>();
        DateTime startDate = new DateTime(this.startDate, 1, 1, 0, 0, 0);
        DateTime nowDate = DateTime.now();
        Interval interval = new Interval(startDate, nowDate);
        int intervalDays = interval.toDuration().toStandardDays().getDays();

        for (int i = 0; i < intervalDays; i++) {
            strings.add(startDate.plusDays(i).toString("dd-MM-yyyy"));
        }

        return strings;
    }

    private Rate toRate(final Currency currency) {
        List<org.iona.currency.models.Currency> currencies = new ArrayList<>();
        currency.getRate().stream().forEach(e -> {
            org.iona.currency.models.Currency currency1 = new org.iona.currency.models.Currency();
            currency1.setCharCode(e.getCharCode());
            currency1.setCode(e.getCode());
            currency1.setRate(new BigDecimal(e.getRate()).
                            divide(new BigDecimal(e.getNominal())).
                            setScale(4, BigDecimal.ROUND_DOWN)
            );
            currencies.add(currency1);
        });
        Rate rate = new Rate(currency.getDate(), currencies);

        return rate;
    }

    public void run() {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "10");
        List<Rate> rates = new ArrayList<>();
        dateList().parallelStream().forEach(e -> {
            Currency currency = get(e);
            if (currency != null) {
                rates.add(toRate(currency));
            }


        });

        rates.parallelStream().forEach(rate -> rateRepository.save(rate));
    }


}
