package org.iona.currency.nbt.models;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by Bakhtiyor Ruziev on 21.06.15.
 * <p>
 * Currency model
 */
@XmlRootElement(name = "ValCurs")
public class Currency {


    private String date;
    private String name;
    private List<Rate> rate;

    @XmlAttribute(name = "Date")
    public String getDate() {
        return date;
    }

    @XmlAttribute(name = "name")
    public String getName() {
        return name;
    }

    @XmlElement(name = "Valute")
    public List<Rate> getRate() {
        return rate;
    }

    public void setDate(String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/uuuu"));
        localDate.format(DateTimeFormatter.ofPattern("uuuMMdd"));
        this.date = localDate.format(DateTimeFormatter.ofPattern("uuuMMdd"));
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRate(List<Rate> rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", rate=" + rate +
                '}';
    }
}
