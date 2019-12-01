package main;

import heros.Hero;

import java.util.LinkedList;
import java.util.List;

public class GameInput {
    private int mRows;
    private int mColumns;
    private char[][] mArena;
    private int mNoPlayers;
    private List<Hero> heroList = new LinkedList<Hero>();
    private int mNoRounds;
    private List<String> movesList = new LinkedList<String>();

    public GameInput() {
        mRows = -1;
        mColumns = -1;
        mArena = new char[0][];
        mNoPlayers = -1;
        mNoRounds = -1;
    }

    public GameInput(final int rows, final int columns,
                     final char[][] arena, final int noPlayers,
                     final LinkedList<Hero> playerList, final int rounds,
                     final LinkedList<String> moves) {
        mRows = rows;
        mColumns = columns;
        mArena = arena;
        mNoPlayers = noPlayers;
        heroList = playerList;
        mNoRounds = rounds;
        movesList = moves;

    }

    /**
     *
     * @return randurile din Arena
     */
    public int getRows() {
        return mRows;
    }

    /**
     *
     * @return coloanele din Arena
     */
    public int getColumns() {
        return mColumns;
    }

    /**
     *
     * @return matricea de caractere Arena care contine tipurile
     * de terrain
     */
    public char[][] getArena() {
        return mArena;
    }

    /**
     *
     * @return numarul de jucatori
     */
    public int getNoPlayers() {
        return mNoPlayers;
    }

    /**
     *
     * @return lista de eroi
     */
    public List<Hero> getHeroList() {
        return heroList;
    }

    /**
     * @return numarul de runde
     */
    public int getNoRounds() {
        return mNoRounds;
    }

    /**
     *
     * @return lista cu mutarile din fiecare runda
     */
    public List<String> getMovesList() {
        return movesList;
    }

    //o functie boolean care verifica daca inputul este valid
    //-> vezi tema cealalta
}
