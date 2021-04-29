package bullscows;

import java.util.Scanner;

public class Main {


    Scanner scanner = new Scanner(System.in);
    StringBuilder secretCode;
    String userCode;
    int bulls;
    int cows;
    int secretNumbers;
    int possibleSymbols;


    void readCode() {
        Scanner scanner = new Scanner(System.in);
        userCode = scanner.next();
    }

    void checkCode() {
        bulls = 0;
        cows = 0;
        for (int i = 0; i < userCode.length(); i++) {
            for (int j = 0; j < secretCode.length(); j++) {
                if (userCode.charAt(i) == secretCode.charAt(j)) {
                    if (i == j) {
                        bulls++;
                    } else {
                        cows++;
                    }
                }
            }
        }
    }

    void printResult() {
        if (bulls == 0 && cows == 0) {
            System.out.println("Grade: None. ");
        } else if (bulls > 0 && cows == 0) {
            System.out.printf("Grade: %d bull(s). \n", bulls);
        } else if (bulls == 0 && cows > 0) {
            System.out.printf("Grade: %d cow(s). \n", cows);
        } else {
            System.out.printf("Grade: %d bull(s) and %d cow(s). \n", bulls, cows);
        }
        //System.out.printf("The secret code is %s.\n", secretCode);
    }

     void playGame() {
        readSecretNumbers();
        readPossibleSymbols();
        createRandomCode();
        System.out.println("Okay, let's start a game!");
        int turns = 1;
        while (bulls != secretNumbers) {
            System.out.printf("Turn %d:\n", turns);
            readCode();
            checkCode();
            printResult();
            turns++;
        }
        System.out.println("Congratulations! You guessed the secret code.");
    }

    void readSecretNumbers() {
        System.out.println("Input the length of the secret code:");
        String seNum = "";
        try {
            seNum = scanner.nextLine();
            secretNumbers = Integer.parseInt(seNum);
        } catch (Exception e) {
            System.out.printf("Error: \"%s\" isn't a valid number.", seNum);
            System.exit(0);
        }
        if (secretNumbers == 0) {
            System.out.println("Error: the minimum length of code should be one.");
            System.exit(0);
        }
    }

    void readPossibleSymbols() {
        System.out.println("Input the number of possible symbols in the code:");
        String posSym = "";
        try {
            posSym = scanner.nextLine();
            possibleSymbols = Integer.parseInt(posSym);
        } catch (Exception e ) {
            System.out.printf("Error: \"%s\" isn't a valid number.", posSym);
            System.exit(0);
        }
        if (possibleSymbols > 36) {
            System.out.println("Error: maximum number of possible symbols " +
                    "in the code is 36 (0-9, a-z).");
            System.exit(0);
        }
        if (possibleSymbols < secretNumbers) {
            System.out.printf("Error: it's not possible to generate a " +
                            "code with a length of %d with %d unique symbols.\n",
                    secretNumbers, possibleSymbols);
            System.exit(0);
        }
    }



    void createRandomCode() {
        int[] numArray = new int[secretNumbers];
        for (int i = 0; i < secretNumbers; i++) {
            boolean state = true;
            while (state) {
                int randomNumber = (int) (Math.random() * possibleSymbols);
                boolean numOk = true;
                for (int j = 0; j < i; j++) {
                    if (randomNumber == numArray[j]) {
                        numOk = false;
                    }
                }
                if (numOk) {
                    numArray[i] = randomNumber;
                    state = false;
                }
            }
        }
        secretCode = new StringBuilder();
        for (int i = 0; i < secretNumbers; i++) {
            if (numArray[i] > 9) {
                //System.out.println(numArray[i]);
                secretCode.append((char)((char) 87 + numArray[i]));
            } else {
                secretCode.append(numArray[i]);
            }
        }
        StringBuilder stars = new StringBuilder();
        stars.append("*".repeat(Math.max(0, secretNumbers))); // build String in lenght of secretNumbers
        if (possibleSymbols < 10) {
            System.out.printf("The secret is prepared: %s (0-%d)\n", stars, possibleSymbols);
            } else {
            System.out.printf("The secret is prepared: %s (0-9, a-%c)\n", stars, (char) 86 + possibleSymbols);
            }
            //System.out.println(secretCode);

    }

    public static void main(String[] args) {
        Main game = new Main();
        game.playGame();

    }
}
