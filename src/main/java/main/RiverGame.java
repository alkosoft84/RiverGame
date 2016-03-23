package main;

import model.characters.GameCharacter;
import model.characters.TypeEnum;

/**
 * Created by mwrobel on 29.01.16.
 */
public interface RiverGame {
    void createGame();

    GameCharacter pickCharacter(final TypeEnum type);

    void addToRaft(final GameCharacter character);

    void removeFromRaft(final GameCharacter character);

    void moveRaft();

    void addCharacterToCoast(final GameCharacter character);

    void removeCharacterFromCoast(final GameCharacter character);
}
