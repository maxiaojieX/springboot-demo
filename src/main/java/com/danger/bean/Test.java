package com.danger.bean;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;

/**
 * Created by xiaojie.Ma on 2018/8/23.
 */
public class Test {

    private int hh;

    public enum TEST {
        SDK(0);
        private int code;
        TEST(int code){
            this.code = code;
        }
        public int code(){
            return this.code;
        }
    }
    public enum SeasonEnum {
        SPRING("春天"),SUMMER("夏天"),FALL("秋天"),WINTER("冬天");

        private final String name;

        private SeasonEnum(String name)
        {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }


    public static void main(String[] args) {
//        System.out.println(TEST.SDK.code);
//        String s = SeasonEnum.FALL.getName();
//        System.out.println(SeasonEnum.FALL.getName());
        DecimalFormat df = new DecimalFormat("######0.00");
        Double a = 3.000;
        Double a1 = 2.50;
        Double b = 10.00;
        System.out.println(b/a);
        System.out.println(df.format(11111111111.155));
        System.out.println(BigDecimal.valueOf(11111111111.155).setScale(2, BigDecimal.ROUND_HALF_UP));


    }

}
