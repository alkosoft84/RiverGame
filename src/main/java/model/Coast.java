package model;

import java.util.ArrayList;
import java.util.List;

import model.characters.GameCharacter;
import model.characters.TypeEnum;

/**
 * Created by mwrobel on 01.03.16.
 */
public class Coast {

    private List<GameCharacter> characters = new ArrayList<>();

    private Coast() {
    }

    public static Coast createCoast() {
        return new Coast();
    }

    public void addGameCharacter(GameCharacter character) {
        characters.add(character);
    }

    public void removeGameCharacter(GameCharacter character) {
        characters.remove(character);
    }

    public boolean checkConstraintsOnCoast() {
        final int charactersOnCoast = characters.size();
        if (hasAtLeastTwoCharacters(charactersOnCoast)) {
            if (checkIfCharacterIsOnTheCoast(TypeEnum.THIEF) && !checkIfCharacterIsOnTheCoast(TypeEnum.POLICEMAN)) {
                return false;
            }
            if (checkIfCharacterIsOnTheCoast(TypeEnum.DAUGHTER) && !checkIfCharacterIsOnTheCoast(TypeEnum.WOMAN) && checkIfCharacterIsOnTheCoast(TypeEnum.MAN)) {
                return false;
            }
            if (checkIfCharacterIsOnTheCoast(TypeEnum.SON) && !checkIfCharacterIsOnTheCoast(TypeEnum.MAN) && checkIfCharacterIsOnTheCoast(TypeEnum.WOMAN)) {
                return false;
            }
        }
        return true;
    }

    private boolean hasAtLeastTwoCharacters(final int charactersOnCoast) {
        return charactersOnCoast > 1;
    }

    private boolean checkIfCharacterIsOnTheCoast(TypeEnum characterType) {
        return characters.stream()
                .anyMatch(x -> x.getType() == characterType);
    }

    public List<GameCharacter> getCharacters() {
        return characters;
    }
}
