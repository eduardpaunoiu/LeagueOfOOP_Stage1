package main;

import heros.Hero;
import heros.Rogue;
import fileio.FileSystem;

import java.io.IOException;
import java.util.List;

public final class Main {
    private Main() {
        // just to trick checkstyle
    }
    public static void main(final String[] args) throws IOException {
    GameInputLoader gameInputLoader = new GameInputLoader(args[0], args[1]);
    GameInput gameInput = gameInputLoader.load();

    int noRows = gameInput.getRows();
    int noColums = gameInput.getColumns();
    char[][] arena = gameInput.getArena();
    int noPlayers = gameInput.getNoPlayers();
    int noRounds = gameInput.getNoRounds();
    List<Hero> heroList = gameInput.getHeroList();
    List<String> movesList = gameInput.getMovesList();

    for (int i = 0; i < noRounds; i++) {
        for (int j = 0; j < noPlayers; j++) {
            if (!heroList.get(j).isKilled()) {
                if (movesList.get(i).charAt(j) == 'L') {
                    if (!heroList.get(j).isImmobilized()) {
                        heroList.get(j).moveLeft();
                    }
                } else if (movesList.get(i).charAt(j) == 'R') {
                        if (!heroList.get(j).isImmobilized()) {
                            heroList.get(j).moveRight();

                        }
                } else if (movesList.get(i).charAt(j) == 'U') {
                        if (!heroList.get(j).isImmobilized()) {
                            heroList.get(j).moveUp();
                        }
                } else if (movesList.get(i).charAt(j) == 'D') {
                        if (!heroList.get(j).isImmobilized()) {
                            heroList.get(j).moveDown();
                        }
                }
            }
        }

        if (i != 0) {
            for (int k = 0; k < noPlayers; k++) {
                heroList.get(k).getPassiveDamage();
                if (heroList.get(k).isImmobilized()) {
                    heroList.get(k).setImmobilized(heroList.get(k).getImmobilized() - 1);
                }
            }
        }

        for (int m = 0; m <  noPlayers - 1; m++) {
            for (int n = m + 1; n < noPlayers; n++) {
                if ((!heroList.get(m).isKilled())
                && (!heroList.get(n).isKilled())) {
                    if (heroList.get(m).getxPosition() == heroList.get(n).getxPosition()
                    && heroList.get(m).getyPosition() == heroList.get(n).getyPosition()) {
                        if (heroList.get(n) instanceof Rogue) {
                            heroList.get(n).battles(heroList.get(m));
                            heroList.get(m).battles(heroList.get(n));
                        } else {
                            heroList.get(m).battles(heroList.get(n));
                            heroList.get(n).battles(heroList.get(m));
                        }
                      }
                    if (!heroList.get(m).isKilled() && heroList.get(n).isKilled()) {
                        heroList.get(m).experienceAndLevelUp(heroList.get(n));
                    } else if (heroList.get(m).isKilled() && !heroList.get(n).isKilled()) {
                        heroList.get(n).experienceAndLevelUp(heroList.get(m));
                    }
                }
            }
        }
    }

        FileSystem writer = new FileSystem(args[0], args[1]);

        for (int i = 0; i < noPlayers; i++) {
          writer.writeWord(heroList.get(i).output());
          writer.writeNewLine();
        }

        writer.close();

    }
}

