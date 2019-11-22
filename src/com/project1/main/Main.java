package com.project1.main;

import java.util.Arrays;

public final class Main {
    private Main() {
        // just to trick checkstyle
    }
    public static void main(final String[] args) {
    GameInputLoader gameInputLoader = new GameInputLoader(args[0], args[1]);
    GameInput gameInput = gameInputLoader.load();

    System.out.println(gameInput.getRows());
    System.out.println(gameInput.getColumns());
    System.out.println(Arrays.deepToString(gameInput.getArena()));
    System.out.println(gameInput.getNoPlayers());
    for (int i = 0; i < gameInput.getNoPlayers(); i++) {
        System.out.println("x position: " + gameInput.getHeroList().get(i).getxPosition());
        System.out.println("y position: " + gameInput.getHeroList().get(i).getyPosition());
    }
    System.out.println(gameInput.getNoRounds());
    for (int i = 0; i < gameInput.getNoRounds(); i++) {
        System.out.println(gameInput.getMovesList().get(i));
    }

    }
}
