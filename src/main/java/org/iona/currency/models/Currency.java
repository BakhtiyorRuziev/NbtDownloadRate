package org.iona.currency.models;

import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

/**
 * Created by Bakhtiyor Ruziev on 21.06.15.
 * <p>
 * Currency model for mongo
 */
public class Currency {


    private String code;

    private String charCode;

    private BigDecimal rate;

    public Currency(String code, String charCode, BigDecimal rate) {
        this.code = code;
        this.charCode = charCode;
        this.rate = rate;
    }

    public Currency() {
    }

    @XmlElement(name = "code")
    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }

    @XmlElement(name = "rate")
    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @XmlElement(name = "charCode")
    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "code='" + code + '\'' +
                ", charCode='" + charCode + '\'' +
                ", rate=" + rate +
                '}';
    }
}
