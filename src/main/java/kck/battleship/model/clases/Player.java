package kck.battleship.model.clases;

import kck.battleship.controller.GameException;
import kck.battleship.controller.ViewController;
import kck.battleship.model.clases.State.DefendState;
import kck.battleship.model.clases.State.PlayerState;
import kck.battleship.model.clases.Strategy.Difficulty;
import kck.battleship.model.clases.Strategy.Easy;
import kck.battleship.model.clases.Strategy.Hard;
import kck.battleship.model.clases.Strategy.Normal;
import kck.battleship.model.types.TypesDirection;
import kck.battleship.model.types.TypesShips;
import kck.battleship.view.View;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Player {
    private final String name;
    private final boolean isAI;
    public boolean hasAirCrafter = false;
    private int durabilityForceField = 0;
    private Date lastShootTime;
    private final BattleField battleField = new BattleField();
    private final ArrayList<Position> shoots = new ArrayList<>();
    public final ArrayList<Position> allShoots = new ArrayList<>();
    private final ArrayList<Ship> ships = createShips();
    private static final View view = ViewController.getInstance();
    private PlayerState state;
    private Difficulty difficulty;

    public Player(String name) {
        this.name = name;
        isAI = false;
        lastShootTime = new Date();
        this.state = new DefendState();
    }

    public Player(String name, boolean isAI, String diffi) {
        this.name = name;
        this.isAI = isAI;
        if(diffi == "Easy")
        difficulty = new Easy();
        if(diffi == "Normal")
            difficulty = new Normal();
        if(diffi == "Hard")
            difficulty = new Hard();
        this.state = new DefendState();
    }

    public void changeState(PlayerState state) {
        this.state = state;
    }

    public boolean defend() {
        return state.defend(this);
    }

    public Position shot(Player defender) {
        return state.shot(this, defender);
    }

    public String getName() {
        return name;
    }

    public BattleField getBattleField() {
        return battleField;
    }

    public boolean isAI() {
        return isAI;
    }

    public Date getLastShootTime() {
        return lastShootTime;
    }

    public void setLastShootTime(Date lastShootTime) {
        this.lastShootTime = lastShootTime;
    }

    public int getDurabilityForceField() {
        return durabilityForceField;
    }

    public void setDurabilityForceField(int durabilityForceField) {
        this.durabilityForceField = durabilityForceField;
    }

    public void addShips() throws IOException {
        if (!isAI) {
            if (hasAirCrafter)
                ships.add(new Ship(TypesShips.getNameExtraShip(), 5));

            addShipManually();
        } else randAddShips();
    }

    private void addShipManually() throws IOException {
        view.showOptionToManuallyAddShip();
        view.addShipsVisually(battleField, ships.get(0), ships);
    }

    public void randAddShips() {
        Random random = new Random();
        if (hasAirCrafter)
            ships.add(new Ship(TypesShips.getNameExtraShip(), 5));

        for (Ship ship : ships)
            addShipRandomly(random, ship);
    }

    private void addShipRandomly(Random random, Ship ship) {
        boolean addedSuccessfully = false;
        int failedAttempts = 0;
        int limit = 10000;

        while (failedAttempts <= limit) {
            try {
                ship.setPosition(Position.randPosition());
                ship.setDirection(random.nextBoolean() ? TypesDirection.VERTICAL : TypesDirection.HORIZONTAL);
                addedSuccessfully = battleField.addShip(ship);
            } catch (GameException ignored) {
            }

            if (addedSuccessfully)
                break;

            failedAttempts++;
        }

        if (failedAttempts > limit) {
            reset();
            randAddShips();
        }
    }

    private ArrayList<Ship> createShips() {
        ArrayList<Ship> ships = new ArrayList<>();
        for (TypesShips type : TypesShips.values())
            for (int i = 0; i < type.getNumberShips(); i++)
                ships.add(new Ship(TypesShips.getShipName(type), type.getShipLength()));
        return ships;
    }

    public boolean areShipsStillSailing() {
        return battleField.getNumberShips() > 0;
    }

    public int shipsLeft() {
        return battleField.getNumberShips();
    }

    public boolean addShoot(Position shoot) throws GameException {
        return battleField.addHit(shoot);
    }

    public void addAllShoot(Position shoot){
        allShoots.add(shoot);
    }

    public Position ComputerShoot(BattleField defenderBattleField, ArrayList<Ship> ships) throws GameException {
        Position target = difficulty.shootAtRandom();
        ArrayList<Ship> notsunkedships = new ArrayList<>();
        difficulty.get_result(defenderBattleField.at(target));
        int ship_number = 0;
        for (Ship s : ships) {
            if (!defenderBattleField.isShipSunk(s)) {
                ship_number = ship_number + 1;
                notsunkedships.add(s);
            }
        }
        difficulty.get_ships_and_change_smallest_ship(notsunkedships);
        difficulty.enemy_ships_comprasion(ship_number);
        return target;
    }


    public Position shoot(BattleField defenderBattleField, ArrayList<Ship> ships) throws GameException {

        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                Position pos = new Position(i, j);
                //  System.out.println(defenderBattleField.at(pos));
            }
        }
        return ComputerShoot(defenderBattleField, ships);
    }

    public void registerShoot(Position position) {
        shoots.add(position);
    }

    public Position getLastShoot() {
        if (shoots.isEmpty()) return null;
        return shoots.get(shoots.size() - 1);
    }

    private void reset() {
        battleField.reset();
    }

    public void getShop() {
        String fileName = "src/main/java/kck/battleship/model/data/shop.txt";

        try {
            File plik = new File(fileName);
            FileReader fileReader = new FileReader(plik);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String linia;
            while ((linia = bufferedReader.readLine()) != null) {
                String[] parts = linia.split(" ");
                if (parts.length == 2) {
                    try {
                        String playerName = parts[0];
                        int shopOption = Integer.parseInt(parts[1]);

                        if (playerName.equals(name))
                            if (shopOption == 0)
                                hasAirCrafter = true;
                            else if (shopOption == 1) {
                                durabilityForceField = 5;
                            }
                    } catch (NumberFormatException e) {
                        System.err.println("Błąd parsowania punktów w linii: " + linia);
                    }
                }
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        removeShopEntries();
    }

    private void removeShopEntries() {
        String fileName = "src/main/java/kck/battleship/model/data/shop.txt";

        try {
            File inputFile = new File(fileName);
            File tempFile = new File(fileName + "_temp");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String linia;
            while ((linia = reader.readLine()) != null) {
                String[] parts = linia.split(" ");
                if (parts.length == 2) {
                    try {
                        String playerName = parts[0];
                        int shopOption = Integer.parseInt(parts[1]);

                        if (!(playerName.equals(name) && (shopOption == 0 || shopOption == 1))) {
                            writer.write(linia + System.lineSeparator());
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Błąd parsowania punktów w linii: " + linia);
                    }
                }
            }

            reader.close();
            writer.close();

            if (inputFile.delete() && tempFile.renameTo(inputFile)) {
                System.out.println("Usunięto wiersze spełniające warunek.");
            } else {
                System.err.println("Błąd usuwania wierszy.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }
}