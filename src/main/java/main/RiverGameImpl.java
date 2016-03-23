package main;

import static model.Coast.createCoast;
import static model.Raft.createRaft;
import static model.characters.Daughter.createDaughter;
import static model.characters.GameCharacter.findGameCharacterByType;
import static model.characters.GameCharacter.hasSomeoneWithSteeringAbilities;
import static model.characters.Man.createMan;
import static model.characters.PoliceMan.createPoliceMan;
import static model.characters.Son.createSon;
import static model.characters.Thief.createThief;
import static model.characters.Woman.createWoman;

import java.util.Iterator;
import java.util.List;

import model.Coast;
import model.CoastsEnum;
import model.Raft;
import model.characters.GameCharacter;
import model.characters.TypeEnum;

/**
 * Created by mwrobel on 27.01.16.
 */
public class RiverGameImpl implements RiverGame {

    private Raft raft;
    private Coast leftCoast;
    private Coast rightCoast;

    public static void main(String[] args) {
        // JUST CLICK RUN ALL TESTS ON "test" folder
    }

    @Override
    public void createGame() {
        raft = createRaft();
        leftCoast = createCoast();
        rightCoast = createCoast();
        createGameCharactersAndAddThemToLeftCoast();
    }

    @Override
    public GameCharacter pickCharacter(final TypeEnum type) {
        final CoastsEnum coast = raft.getPosition();
        if (coast.equals(CoastsEnum.LEFT)) {
            return findGameCharacterByType(leftCoast.getCharacters(), type);
        } else if (coast.equals(CoastsEnum.RIGHT)) {
            return findGameCharacterByType(rightCoast.getCharacters(), type);
        } else {
            throw new RuntimeException("You can add only on left and right coast");
        }
    }

    @Override
    public void addToRaft(final GameCharacter character) {
        removeCharacterFromCoast(character);
        raft.addPassenger(character);
    }

    @Override
    public void removeFromRaft(final GameCharacter character) {
        addCharacterToCoast(character);
        raft.removePassenger(character);
    }

    @Override
    public void moveRaft() {
        if (checkConstraints()) {
            if (hasSomeoneWithSteeringAbilities(raft.getPassengers())) {
                raft.assignRaftPosition();
                tryToRemoveAllPassengersFromRaft();
            } else {
                throw new RuntimeException("You can't move the raft, you don't have steering abilities");
            }
        } else {
            throw new RuntimeException("Constraints violation during adding passenger");
        }
    }

    @Override
    public void addCharacterToCoast(final GameCharacter character) {
        final CoastsEnum coast = raft.getPosition();
        if (coast.equals(CoastsEnum.LEFT)) {
            leftCoast.addGameCharacter(character);
        } else if (coast.equals(CoastsEnum.RIGHT)) {
            rightCoast.addGameCharacter(character);
        } else {
            throw new RuntimeException("You can add only on left and right coast");
        }
    }

    @Override
    public void removeCharacterFromCoast(final GameCharacter character) {
        final CoastsEnum coast = raft.getPosition();
        if (coast.equals(CoastsEnum.LEFT)) {
            final GameCharacter gameCharacterByType = findGameCharacterByType(leftCoast.getCharacters(), character.getType());
            leftCoast.removeGameCharacter(gameCharacterByType);
        } else if (coast.equals(CoastsEnum.RIGHT)) {
            final GameCharacter gameCharacterByType = findGameCharacterByType(rightCoast.getCharacters(), character.getType());
            rightCoast.removeGameCharacter(gameCharacterByType);
        } else {
            throw new RuntimeException("You can add only on left and right coast");
        }
    }

    private void createGameCharactersAndAddThemToLeftCoast() {
        leftCoast.addGameCharacter(createDaughter());
        leftCoast.addGameCharacter(createDaughter());
        leftCoast.addGameCharacter(createSon());
        leftCoast.addGameCharacter(createSon());
        leftCoast.addGameCharacter(createMan());
        leftCoast.addGameCharacter(createWoman());
        leftCoast.addGameCharacter(createPoliceMan());
        leftCoast.addGameCharacter(createThief());
    }

    private boolean checkConstraints() {
        return raft.checkConstraintsOnRaft() && leftCoast.checkConstraintsOnCoast() && rightCoast.checkConstraintsOnCoast();
    }

    private void tryToRemoveAllPassengersFromRaft() {
        temporaryAddPassengerToCoast();

        if (checkConstraints()) {
            removePermanentlyPassengersFromRaft();
        } else {
            rollBackCharactersToRaft();
            raft.assignRaftPosition();
            throw new RuntimeException("Constraints violation during adding passenger");
        }

    }

    private void temporaryAddPassengerToCoast() {
        raft.getPassengers().forEach(this::addCharacterToCoast);
    }

    private void rollBackCharactersToRaft() {
        raft.getPassengers().forEach(this::addToRaft);
    }

    private void removePermanentlyPassengersFromRaft() {
        final List<GameCharacter> passengers = raft.getPassengers();
        Iterator<GameCharacter> iterator = passengers.iterator();
        while (iterator.hasNext()) {
            GameCharacter character = iterator.next();
            iterator.remove();
        }
    }

    // GETTERS

    public Raft getRaft() {
        return raft;
    }

    public Coast getLeftCoast() {
        return leftCoast;
    }

    public Coast getRightCoast() {
        return rightCoast;
    }
}
