package cinema;
import java.util.*;

public class Cinema {

    private static char[][] cinemaHall;
    private static final int frontPrice = 10;
    private static final int backPrice = 8;

    private static final Scanner scanner = new Scanner(System.in);

    private static int currentIncome;
    private static int numberOfPurchased;
    private static int totalIncome;

    public static void main(String[] args) {

        createCinemaHall();

        int command;
        do {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            command = scanner.nextInt();
            switch (command) {
                case 1:
                    printCinemaHall(cinemaHall);
                    break;
                case 2:
                    buyTicket(cinemaHall);
                    break;
                case 3:
                    showStatistics(cinemaHall);
                    break;
                case 0:
                    break;
            }
        } while (command != 0);
    }

    public static void createCinemaHall() {
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();

        if (rows <= 9 && seats <= 9) {
            cinemaHall = new char[rows][seats];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < seats; j++) {
                    cinemaHall[i][j] = 'S';
                }
            }
        } else {
            System.out.println("Error! Numbers grater than 9!");
            System.exit(-1);
        }

        int totalSeats = cinemaHall.length * cinemaHall[0].length;
        if (totalSeats <= 60) {
            totalIncome = totalSeats * 10;
        } else {
            int frontHalf = cinemaHall.length / 2;
            int totalFrontHalf = frontHalf * cinemaHall[0].length * frontPrice;
            int totalBackHalf = (cinemaHall.length - frontHalf) * cinemaHall[0].length * backPrice;
            totalIncome = totalFrontHalf + totalBackHalf;
        }
    }

    public static void printCinemaHall(char[][] cinemaHall) {
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 1; i <= cinemaHall[0].length; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 0; i < cinemaHall.length; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < cinemaHall[0].length; j++) {
                System.out.print(" " + cinemaHall[i][j]);
            }
            System.out.println();
        }
    }

    public static void buyTicket(char[][] cinemaHall) {

        int totalSeats = cinemaHall.length * cinemaHall[0].length;

        int getRow;
        int getSeat;

        while (true) {
            System.out.println("Enter a row number:");
            getRow = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            getSeat = scanner.nextInt();

            if (getRow > cinemaHall.length || getSeat > cinemaHall[0].length) {
                System.out.println("Wrong input!");
            } else {
                if (cinemaHall[getRow - 1][getSeat - 1] == 'B') {
                    System.out.println("That ticket has already been purchased!");
                } else {
                    break;
                }
            }
        }

        int ticketPrice;

        if (totalSeats <= 60) {
            ticketPrice = 10;
        } else {
            int frontHalf = cinemaHall.length / 2;
            if (getRow <= frontHalf) {
                ticketPrice = frontPrice;
            } else {
                ticketPrice = backPrice;
            }
        }

        currentIncome += ticketPrice;
        numberOfPurchased++;

        System.out.println("Ticket price: $" + ticketPrice);
        cinemaHall[getRow - 1][getSeat - 1] = 'B';
    }

    public static void showStatistics(char[][] cinemaHall) {
        System.out.println("Number of purchased tickets: " + numberOfPurchased);

        float totalSeats = cinemaHall.length * cinemaHall[0].length;
        float percentageOfPurchased = 100 / totalSeats * numberOfPurchased;
        System.out.printf("Percentage: %.2f%s%n", percentageOfPurchased, "%");

        System.out.println("Current income: $" + currentIncome);

        System.out.println("Total income: $" + totalIncome);
    }
}