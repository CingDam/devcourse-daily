package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SeatMap {
    boolean[][] seatMap = new boolean[5][8];   // true: 예매

    void available() {
        print(seatMap);
        System.out.println("사용가능 좌석 : " + countEmpty(seatMap));
    }

    List<String> resevation(int userCount, Scanner sc) {
        List<String> reserved = new ArrayList<>();

        System.out.println("인원수에 맞게 좌석을 입력해주세요 EX) A1");
        System.out.println("사용가능 열 : A~E / 사용가능 번호: 1~8");

        int inputcount = 0;

        while (inputcount < userCount) {
            System.out.println("좌석을 선택해주세요");
            int[] pos = parse(sc.nextLine());

            if (pos == null) {
                System.out.println("해당좌석은 없습니다!");
                continue;
            }
            if (seatMap[pos[0]][pos[1]]) {
                System.out.println("이미 예약된 좌석입니다!");
                continue;
            }

            seatMap[pos[0]][pos[1]] = true;
            reserved.add(name(pos[0], pos[1]));
            System.out.println("좌석이 예약됐습니다!");
            inputcount += 1;
            System.out.printf("현재 %d명을 예약하였습니다\n", inputcount);
        }

        return reserved;
    }

    // 좌석 이름 → {행, 열}, 없거나 형식이 틀리면 null
    int[] parse(String seat) {
        seat = seat.trim().toUpperCase();
        if (!seat.matches("[A-E][1-8]")) return null;
        return new int[]{ seat.charAt(0) - 'A', seat.charAt(1) - '1' };
    }

    // {행, 열} → 좌석 이름
    String name(int r, int c) {
        return "" + (char) ('A' + r) + (c + 1);
    }

    // 배치도 출력 (O: 빈 좌석, X: 예매)
    void print(boolean[][] seats) {
        System.out.print("  ");
        for (int j = 0; j < seats[0].length; j++) {
            System.out.print((j + 1) + " ");
        }
        System.out.println();

        for (int i = 0; i < seats.length; i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < seats[i].length; j++) {
                System.out.print(seats[i][j] ? "X " : "O ");
            }
            System.out.println();
        }
    }

    // 빈 좌석 수
    int countEmpty(boolean[][] seats) {
        int count = 0;
        for (boolean[] row : seats) {
            for (boolean isReserved : row) {
                if (!isReserved) count++;
            }
        }
        return count;
    }

    // 같은 행에서 n칸 연속으로 빈 첫 좌석, 없으면 null
    String findConsecutive(boolean[][] seats, int n) {
        for (int i = 0; i < seats.length; i++) {
            int streak = 0;
            for (int j = 0; j < seats[i].length; j++) {
                streak = seats[i][j] ? 0 : streak + 1;
                if (streak == n) return name(i, j - n + 1);
            }
        }
        return null;
    }
}