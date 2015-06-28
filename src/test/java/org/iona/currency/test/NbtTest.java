package org.iona.currency.test;

import org.iona.currency.nbt.NbtGet;
import org.iona.currency.nbt.models.Currency;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bakhtiyor Ruziev on 21.06.15.
 */


public class NbtTest {

    @Test
    public void checkDate() throws Exception {
        String dt = "12/12/2012";
        LocalDate localDate = LocalDate.parse(dt, DateTimeFormat.forPattern("dd/MM/yyyy"));
        Assert.assertEquals(localDate.toString("yyyyMMdd"), "20121212");
    }

    @Test
    public void checkDuration() throws Exception {
        DateTime startDate = new DateTime(2009, 1, 1, 0, 0, 0);
        DateTime nowDate = DateTime.now();
        Interval interval = new Interval(startDate, nowDate);
        int intervalDays = interval.toDuration().toStandardDays().getDays();
        Assert.assertEquals(intervalDays, 2363);


    }


    @Test
    public void getData() throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(Currency.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("test/nbt.xml").getFile());
        Currency currency = (Currency) unmarshaller.unmarshal(file);

    }


    @Test
    public void checkString() throws Exception {
        String d = "5,9950";
        BigDecimal bigDecimal = new BigDecimal(d.replace(",", "."));
        Assert.assertEquals("5.9950", bigDecimal.toString());
    }

    @Test
    public void checkList() throws Exception {
        List<String> strings = new ArrayList<>();
        DateTime startDate = new DateTime(2009, 1, 1, 0, 0, 0);
        DateTime nowDate = DateTime.now();
        Interval interval = new Interval(startDate, nowDate);
        int intervalDays = interval.toDuration().toStandardDays().getDays();

        for (int i = 0; i < intervalDays; i++) {
            strings.add(startDate.plusDays(i).toString("yyyyMMdd"));
        }

    }

    @Test
    public void stringFormat() throws Exception {
        String date = "2012-12-12";
        String url = String.format("http://nbt.tj/ru/kurs/?c=4&id=28&lg=en&d=%s&export=xmlout", date);
        Assert.assertEquals(url, "http://nbt.tj/ru/kurs/?c=4&id=28&lg=en&d=2012-12-12&export=xmlout");
    }

    @Test
    public void nbtParallel() throws Exception {

        NbtGet nbtGet = new NbtGet();

        nbtGet.run();

    }


}
