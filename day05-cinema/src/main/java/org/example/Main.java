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
        boolean[][] seats = seatMap.seatMap;   // 새 배열이 아니라 같은 배열

        for (String seat : new String[]{"A1", "A2", "A3", "B4"}) {
            int[] pos = SeatMap.parse(seat);
            seats[pos[0]][pos[1]] = true;
        }

        SeatMap.print(seats);
        System.out.println("빈 좌석: " + SeatMap.countEmpty(seats));
        System.out.println("5칸: " + SeatMap.findConsecutive(seats, 5));
        System.out.println("6칸: " + SeatMap.findConsecutive(seats, 6));
        System.out.println("9칸: " + SeatMap.findConsecutive(seats, 9));
        System.out.println("F1: " + SeatMap.parse("F1"));
        System.out.println("A9: " + SeatMap.parse("A9"));
    }
}