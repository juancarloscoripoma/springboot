package com.soft.demo.rest.util;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Scope("Request")
@Component
@Service
public class ParseBigDecimalObject {

    public ParseBigDecimalObject() {
    }

    public BigDecimal parseObject(Object o){
        if (o == null){
            return new BigDecimal(0);
        }
        if (o instanceof BigDecimal){
            return new BigDecimal(o+"");
        }
        return new BigDecimal(0);
    }

    public BigDecimal getDivide(BigDecimal a,BigDecimal b){
        if (a.equals(0))
            return new BigDecimal(0);
        if (a.intValue()>b.intValue())
            return new BigDecimal(100);
        return a.divide(b).multiply(new BigDecimal(100));
    }

    public Double getDivideDouble(BigDecimal a,BigDecimal b){
        if (a.equals(0))
            return 0.0;
        if (a.intValue()>b.intValue())
            return 100.0;
        return (a.doubleValue()/b.doubleValue())*100.0;
    }

}
