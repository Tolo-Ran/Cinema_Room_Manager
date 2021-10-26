package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        System.out.print("> ");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        System.out.print("> ");
        int seats = scanner.nextInt();

        char[][] cinema = new char[rows][seats];
        for (char[] chars : cinema) {
            Arrays.fill(chars, 'S');
        }
        boolean controlFlag = true;
        int currentIncome = 0;
        while (controlFlag) {
            showMenu();
            System.out.print("> ");
            int choice = scanner.nextInt();
            if (choice == 0) {
               break;
            }
            switch (choice) {
                case 1: printCinema(cinema, seats);
                    break;
                case 2:
                    currentIncome =+ buySeat(cinema, rows, seats,currentIncome);
                    break;
                case 3:
                    calculatePurchasedTickets(cinema, rows, seats);
                    System.out.printf("Current income: $%d%n", currentIncome);
                    calculateCurrentIncome(rows, seats);
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }

    public static void calculatePurchasedTickets(char[][] cinema, int rows, int seats) {
        int purchasedTickets = 0;
        for (char[] chars : cinema) {
            for (char aChar : chars) {
                if (aChar == 'B') {
                    purchasedTickets++;
                }
            }
        }
        System.out.printf("Number of purchased tickets: %d%n", purchasedTickets);
        double totalSeats = rows * seats;
        double percentSeats = purchasedTickets * 100 / totalSeats;
        System.out.printf("Percentage: %3.2f", percentSeats);
        System.out.println("%");
    }

    public static void showMenu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    public static int buySeat(char[][] cinema, int rows, int seats, int currentIncome) {
        Scanner scanner = new Scanner(System.in);
        boolean controlFlag = true;
        int seat = 0;
        int row = 0;
        while (controlFlag) {
            System.out.println("Enter a row number:");
            System.out.print("> ");
            row = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            System.out.print("> ");
            seat = scanner.nextInt();
            if (row > rows || seat > seats) {
                System.out.println("Wrong input!");
                continue;
            }
            if (cinema[row - 1][seat - 1] == 'B') {
                System.out.println("That ticket has already been purchased!");
                continue;
            }
            controlFlag = false;
        }
            System.out.print("Ticket price: ");
            int totalSeats = rows * seats;
            int seatPrice;
            if (totalSeats <= 60) {
                seatPrice = 10;
            } else {
                int front;
                if (totalSeats % 2 == 0) {
                    front = rows / 2;
                } else {
                    front = (rows - 1) / 2;
                }
                if (row <= front) {
                    seatPrice = 10;
                } else {
                    seatPrice = 8;
                }
            }
        currentIncome = currentIncome + seatPrice;
        System.out.printf("$%d", seatPrice);
        System.out.println();
        cinema[row -1][seat - 1] = 'B';
        return currentIncome;
    }

    public static void calculateCurrentIncome(int rows, int seats) {
        int totalSeats = rows * seats;
        int totalPrice;
        if (totalSeats <= 60) {
            totalPrice = totalSeats * 10;
        } else {
            int front;
            int back;
            if (totalSeats % 2 == 0) {
                front = rows / 2;
                back = rows / 2;
            } else {
                front = (rows - 1) / 2;
                back = (rows + 1) / 2;
            }
            int frontPrice = front * seats * 10;
            int backPrice = back * seats * 8;
            totalPrice = frontPrice + backPrice;
        }
        System.out.printf("Total income: $%d%n", totalPrice);
     }

    public static void printCinema(char[][] cinema, int seats) {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= seats; i++) {
            System.out.print( i + " ");
        }
        System.out.println();
        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length; j++) {
                if (j == 0) {
                    System.out.print(i + 1 + " ");
                }
                System.out.print(cinema[i][j] +" ");
            }
            System.out.println();
        }
    }
}