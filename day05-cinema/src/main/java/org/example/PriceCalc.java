package org.example;

import java.util.List;

public class PriceCalc {
    String[] audienceType = {"어린이","청소년","성인","경로"};
    int[] priceArr = {8000,12000,15000,7000};

    int moringPriceCalculator(int age) {
        int price = 0;
        if(age<=12) {
            price = (int) (priceArr[0]*0.8);
            System.out.println(audienceType[0]+" : "+price);
        } else if (age<=18) {
            price = (int) (priceArr[1]*0.8);
            System.out.println(audienceType[1]+" : "+price);
        } else if (age<=64) {
            price = (int) (priceArr[2]*0.8);
            System.out.println(audienceType[2]+" : "+price);
        } else {
            price = (int) (priceArr[3]*0.8);
            System.out.println(audienceType[3]+" : "+price);
        }

        return price;
    }

    int priceCalculator(int age) {
        int price = 0;
        if(age<=12) {
            price = priceArr[0];
            System.out.println(audienceType[0]+" : "+price);
        } else if (age<=18) {
            price = priceArr[1];
            System.out.println(audienceType[1]+" : "+price);
        } else if (age<=64) {
            price = priceArr[2];
            System.out.println(audienceType[2]+" : "+price);
        } else {
            price = priceArr[3];
            System.out.println(audienceType[3]+" : "+price);
        }

        return price;
    }


    int calculateTotalPrice(List<Integer> ageArr, char moringSet) {
        long sum=0;
        boolean isMorningScreening = false;

        for(int age: ageArr) {
            if(moringSet == 'Y') isMorningScreening = true;

            if(isMorningScreening) {
                sum+= moringPriceCalculator(age);
            } else {
                sum+= priceCalculator(age);
            }
        }

        return sum;
    }
}
