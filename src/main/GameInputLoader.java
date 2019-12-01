package main;

import heros.Hero;
import heros.Knight;
import heros.Pyromancer;
import heros.Rogue;
import heros.Wizard;
import fileio.FileSystem;

import java.util.LinkedList;


public class GameInputLoader {
    private final String inputPath;
    private final String outputPath;

    GameInputLoader(final String input, final String output) {
        inputPath = input;
        outputPath = output;
    }

    /**
     * Incarca efectiv jocul, citind din fisier.
     * @return un nou input pentru joc.
     */
    public GameInput load() {
        int rows = 0;
        int columns = 0;
        char[][] arena = new char[rows][columns];
        int noPlayers = 0;
        LinkedList<Hero> heroList = new LinkedList<Hero>();
        LinkedList<String> moves = new LinkedList<String>();
        int noRounds = 0;
        try {
            FileSystem fs = new FileSystem(inputPath, outputPath);
            rows = fs.nextInt();
            columns = fs.nextInt();
            arena = new char[rows][columns];
            for (int i = 0; i < rows; i++) {
                String rowLandTypes = fs.nextWord();
                char[] landType = rowLandTypes.toCharArray();
                for (int j = 0; j < columns; j++) {
                    arena[i][j] = landType[j];
                }
            }
            noPlayers = fs.nextInt();
            for (int i = 0; i < noPlayers; i++) {

                char playerType = fs.nextWord().charAt(0);
                int positionX = fs.nextInt();
                int positionY = fs.nextInt();

                Hero myHero = new Hero();

                if (playerType == 'W') {
                    myHero = new Wizard(positionX, positionY, arena[positionX][positionY]);
                } else if (playerType == 'P') {
                    myHero = new Pyromancer(positionX, positionY, arena[positionX][positionY]);
                } else if (playerType == 'R') {
                    myHero = new Rogue(positionX, positionY, arena[positionX][positionY]);
                } else if (playerType == 'K') {
                    myHero = new Knight(positionX, positionY, arena[positionX][positionY]);
                }

                heroList.add(myHero);
            }

            noRounds = fs.nextInt();

            for (int i = 0; i < noRounds; i++) {
                String listOfMoves = fs.nextWord();
                moves.add(listOfMoves);
            }

            fs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new GameInput(rows, columns, arena, noPlayers, heroList, noRounds, moves);
        /*aici modifici contructorul din gameinput dupa ce adaugi pozitiile si ce
        plm mai trebuie sa citesc din fisierul de intrare */
    }


}
