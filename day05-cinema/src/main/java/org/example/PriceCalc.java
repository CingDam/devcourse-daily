package org.example;

import java.util.List;

public class PriceCalc {
    String[] audienceType = {"어린이","청소년","성인","경로"};
    int[] priceArr = {8000,12000,15000,7000};
    long sales(long price,boolean isMoringScreen) {
        if(isMoringScreen) return (long) (price * 0.8);
        else return price;
    }

    long priceCalculator(int age,boolean isMorningScreening) {
        long price = 0;
        if(age<=12) {
            price = sales(priceArr[0],isMorningScreening);
            System.out.println(audienceType[0]+" : "+price);
        } else if (age<=18) {
            price = sales(priceArr[1],isMorningScreening);
            System.out.println(audienceType[1]+" : "+price);
        } else if (age<=64) {
            price = sales(priceArr[2],isMorningScreening);
            System.out.println(audienceType[2]+" : "+price);
        } else {
            price = sales(priceArr[3],isMorningScreening);
            System.out.println(audienceType[3]+" : "+price);
        }

        return price;
    }


    long calculateTotalPrice(List<Integer> ageArr, char moringSet) {
        long sum = 0;
        boolean isMorningScreening = false;

        for (int age : ageArr) {
            if (moringSet == 'Y') isMorningScreening = true;
            sum += priceCalculator(age, isMorningScreening);
            return sum;
        }

        return sum;
    }
}
