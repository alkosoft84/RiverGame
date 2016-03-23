package model;

import java.util.ArrayList;
import java.util.List;

import model.characters.GameCharacter;
import model.characters.TypeEnum;

/**
 * Created by mwrobel on 29.01.16.
 */
public class Raft {
    private CoastsEnum position = CoastsEnum.LEFT;
    private List<GameCharacter> passengers = new ArrayList<>();

    private Raft() {
    }

    public static Raft createRaft() {
        return new Raft();
    }

    public void addPassenger(GameCharacter passenger) {
        passengers.add(passenger);
    }

    public void removePassenger(GameCharacter passenger) {
        passengers.remove(passenger);
    }

    public void assignRaftPosition() {
        if (this.isOnTheLeftCoast()) {
            this.position = CoastsEnum.RIGHT;
        } else {
            this.position = CoastsEnum.LEFT;
        }
    }

    public boolean checkConstraintsOnRaft() {
        if (checkIfCharacterIsOnTheRaft(TypeEnum.THIEF) && !checkIfCharacterIsOnTheRaft(TypeEnum.POLICEMAN)) {
            return false;
        }
        if (checkIfCharacterIsOnTheRaft(TypeEnum.DAUGHTER) && checkIfCharacterIsOnTheRaft(TypeEnum.MAN)) {
            return false;
        }
        if (checkIfCharacterIsOnTheRaft(TypeEnum.WOMAN) && checkIfCharacterIsOnTheRaft(TypeEnum.SON)) {
            return false;
        }
        return true;
    }

    private boolean checkIfCharacterIsOnTheRaft(TypeEnum characterType) {
        return passengers.stream()
                .anyMatch(x -> x.getType() == characterType);
    }

    private boolean isOnTheLeftCoast() {
        return position.equals(CoastsEnum.LEFT);
    }

    // GETTERS
    public CoastsEnum getPosition() {
        return position;
    }

    public List<GameCharacter> getPassengers() {
        return passengers;
    }
}
