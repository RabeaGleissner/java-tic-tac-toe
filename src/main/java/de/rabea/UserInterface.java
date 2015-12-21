package de.rabea;

public class UserInterface {
    private final Console console;

    public UserInterface(Console console) {
        this.console = console;
    }

    private String askUserForPosition = "Please select a position for your mark.";

    public String displayBoard(Cell[] cells) {
        String boardPrinter = "";
        int i = 0;
        for (Enum cell : cells) {
            i ++;

            if (cell == Cell.EMPTY) {
                boardPrinter += i;
            } else {
                boardPrinter += cell.toString();
            }
            
            boardPrinter += " ";

            if (i%3 == 0) {
                boardPrinter += "\n";
            }
        }
        return boardPrinter;
    }


    public void askForPosition() {
        console.print(askUserForPosition);
    }

    public Integer formattedUserChoice(String userChoiceForPosition) {
        try {
            Integer position = Integer.parseInt(userChoiceForPosition);
            if (position >= 1 && position <= 8) {
                position --;
                return position;
            }
        } catch (NumberFormatException e) {
            System.out.println("e = " + e);
            return null;
        }
        return null;
    }
}
