package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SeatMap {
    int[][] seatMap = new int [5][8];

    void available() {
        // 가로 번호
        System.out.print("  ");
        for (int j = 0; j < seatMap[0].length; j++) {
            System.out.print((j + 1) + " ");
        }
        System.out.println();

        int count = 0;   // 사용가능 좌석 수

        // 세로 알파벳 + 좌석
        for (int i = 0; i < seatMap.length; i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < seatMap[i].length; j++) {
                System.out.print(seatMap[i][j] == 1 ? "X " : "O ");
                if (seatMap[i][j] == 0) count++;
            }
            System.out.println();
        }

        System.out.println("사용가능 좌석 : " + count);
    }

    String findSeats(int n) {
        for (int i = 0; i < seatMap.length; i++) {
            int streak = 0;
            for (int j = 0; j < seatMap[i].length; j++) {
                if (seatMap[i][j] == 0) streak++;
                else streak = 0;

                if (streak == n) {
                    return "" + (char) ('A' + i) + (j - n + 2);   // 시작 번호(1부터)
                }
            }
        }
        return null;
    }

    List<String> resevation(int userCount, Scanner sc) {   // void → List<String>
        List<String> reserved = new ArrayList<>();
        System.out.println("인원수에 맞게 좌석을 입력해주세요 EX) A1");
        System.out.println("사용가능 열 : A~E / 사용가능 번호: 1~8");

        int inputcount = 0;

        while (inputcount < userCount) {
            System.out.println("좌석을 선택해주세요");
            String seat = sc.nextLine().trim().toUpperCase();
            if(!seat.matches("[A-E][1-8]")){
                System.out.println("해당좌석은 없습니다!");
                continue;
            };

            int col = seat.charAt(0) - 'A';
            int row = seat.charAt(1)- '1';

            if(seatMap[col][row] == 1) {
                System.out.println("이미 예약된 좌석입니다!");
                continue;
            }
            seatMap[col][row] = 1;
            reserved.add(seat);
            System.out.println("좌석이 예약됐습니다!");
            inputcount += 1;
            System.out.printf("현재 %d명을 예약하였습니다\n",inputcount);
        }
        return reserved;
    }
}
