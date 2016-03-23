package model.characters;

import java.util.List;

/**
 * Created by mwrobel on 27.01.16.
 */
public abstract class GameCharacter {
    private static final int MAX_CHARACTERS = 8;
    private static int counter = 0;
    private int id;
    private TypeEnum type;
    private boolean canSteerTheRaft;

    protected GameCharacter(final TypeEnum type, final boolean canSteerTheRaft) {
        this.type = type;
        this.id = generateCounter();
        this.canSteerTheRaft = canSteerTheRaft;
    }

    public static GameCharacter findGameCharacterByType(final List<GameCharacter> characters, final TypeEnum type) {
        GameCharacter character = characters.stream()
                .filter(x -> x.getType() == type)
                .findAny()
                .get();
        return character;
    }

    public static boolean hasSomeoneWithSteeringAbilities(final List<GameCharacter> characters) {
        return characters
                .stream()
                .anyMatch(GameCharacter::isCanSteerTheRaft);
    }

    private int generateCounter() {
        if (counter == MAX_CHARACTERS) {
            counter = 1;
        } else {
            counter++;
        }
        return counter;
    }

    // GETTERS
    public int getId() {
        return id;
    }

    public TypeEnum getType() {
        return type;
    }

    public boolean isCanSteerTheRaft() {
        return canSteerTheRaft;
    }

    @Override
    public String toString() {
        return "GameCharacter{" +
                "id=" + id +
                ", type=" + type +
                ", canSteerTheRaft=" + canSteerTheRaft +
                "}\n";
    }
}
