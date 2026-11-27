package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int userCount = 0;
        List<Integer> ageArr = new ArrayList<>();
        PriceCalc priceCalc = new PriceCalc();
        SeatMap seatMap = new SeatMap();

        System.out.println("인원수를 적어주세요");
        while (true) {
            userCount = sc.nextInt();
            if(userCount > 0) break;
            System.out.println("0은 입력이 불가능합니다!");
        }
        sc.nextLine();

        System.out.println("각 인원의 나이를 적어주세요(띄어쓰기로 구분해주세요)");
        String[] ageInputs;
        while (true) {
            ageInputs = sc.nextLine().split(" ");
            if(ageInputs.length == userCount) break;
            System.out.printf("%d명에 맞게 다시 입력해주세요! 현재 입력 수: %d\n",userCount,ageInputs.length);
        }
        for(String age : ageInputs) {
            ageArr.add(Integer.parseInt(age));
        }

        System.out.println("조조 여부를 적어주세요 (Y 혹은 N으로)");
        String moringInput;
        while (true) {
            moringInput = sc.nextLine();
            if(moringInput.toUpperCase().equals("Y") || moringInput.toUpperCase().equals("N")) break;
            System.out.println("Y 혹은 N을 입력해주세요!");
        }

        long total = priceCalc.calculateTotalPrice(ageArr,moringInput.toUpperCase().charAt(0));
        System.out.println("총합 : " + total);

        // 좌석 현황과 추천
        System.out.println("사용가능 좌석");
        seatMap.available();

        String recommended = seatMap.findConsecutive(seatMap.seatMap, userCount);
        System.out.println(recommended == null
                ? "연속된 좌석이 없습니다"
                : "추천 시작 좌석: " + recommended);

        // 예약
        List<String> reserved = seatMap.resevation(userCount, sc);

        // 최종 결과
        System.out.println("\n===== 최종 결과 =====");
        System.out.println("인원: " + userCount + "명");
        System.out.println("예약 좌석: " + String.join(", ", reserved));
        System.out.println("총 금액: " + total + "원");
        seatMap.available();
    }
}