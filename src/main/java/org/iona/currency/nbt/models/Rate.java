package org.iona.currency.nbt.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Bakhtiyor Ruziev on 21.06.15.
 * <p>
 * Rate model
 */
@XmlRootElement(name = "Valute")
public class Rate {


    private String code;
    private String charCode;
    private String nominal;
    private String name;
    private String rate;

    @XmlAttribute(name = "ID")
    public String getCode() {
        return code;
    }

    @XmlElement(name = "CharCode")
    public String getCharCode() {
        return charCode;
    }

    @XmlElement(name = "Nominal")
    public String getNominal() {
        return nominal;
    }

    @XmlElement(name = "Name")
    public String getName() {
        return name;
    }


    @XmlElement(name = "Value")
    public String getRate() {
        return rate;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setRate(String rate) {
        this.rate = rate.replace(",", ".");
    }

    @Override
    public String toString() {
        return "Rate{" +
                "code='" + code + '\'' +
                ", charCode='" + charCode + '\'' +
                ", nominal='" + nominal + '\'' +
                ", name='" + name + '\'' +
                ", rate=" + rate +
                '}';
    }
}
