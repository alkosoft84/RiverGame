package gameScenarios;

import java.util.List;

import main.RiverGameImpl;
import model.characters.GameCharacter;
import model.characters.TypeEnum;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by mwrobel on 22.03.16.
 * 
 * This class is my idea how to simulate game scenarios in certain steps. Just
 * run it and You will see how to move every passengers to other side
 * 
 */
public class GameScenariosTest {

    private RiverGameImpl tut;

    @Before
    public void setUp() throws Exception {
        tut = new RiverGameImpl();
        tut.createGame();
    }

    @Test
    public void move_man_and_woman_to_another_coast() {
        System.out.println(" ");
        System.out.println("SCENARIO: move_man_and_woman_to_another_coast");
        System.out.println("-------------------------------------------------------------------");
        // STEP 1: Pick first character
        final GameCharacter man = tut.pickCharacter(TypeEnum.MAN);
        // STEP 2: Add first character to the raft
        tut.addToRaft(man);
        // STEP 3: Pick second character
        final GameCharacter woman = tut.pickCharacter(TypeEnum.WOMAN);
        // STEP 4: Add second character to the raft
        tut.addToRaft(woman);
        // STEP 5: Move the raft and check constraints
        tut.moveRaft();
        // STEP 7: Check raft, coasts
        showRaftAndCoastsCharacters();
    }

    @Test
    public void move_man_and_woman_and_policeMan_and_thief_in_another_step_to_another_coast() {
        System.out.println(" ");
        System.out.println("SCENARIO: move_man_and_woman_and_policeMan_and_thief_in_another_step_to_another_coast");
        System.out.println("-------------------------------------------------------------------");
        // STEP 1: Pick first character
        GameCharacter man = tut.pickCharacter(TypeEnum.MAN);
        // STEP 2: Add first character to the raft
        tut.addToRaft(man);
        // STEP 3: Pick second character
        GameCharacter woman = tut.pickCharacter(TypeEnum.WOMAN);
        // STEP 4: Add second character to the raft
        tut.addToRaft(woman);
        // STEP 5: Move the raft and check constraints
        tut.moveRaft();
        // STEP 6: Pick first character from right coast
        man = tut.pickCharacter(TypeEnum.MAN);
        // STEP 7: Add first character to the raft
        tut.addToRaft(man);
        // STEP 8: Pick first character from right coast
        woman = tut.pickCharacter(TypeEnum.WOMAN);
        // STEP 9: Add first character to the raft
        tut.addToRaft(woman);
        // STEP 10: Move the raft and check constraints
        tut.moveRaft();
        // STEP 11: Pick first character
        GameCharacter policeMan = tut.pickCharacter(TypeEnum.POLICEMAN);
        // STEP 12: Add first character to the raft
        tut.addToRaft(policeMan);
        // STEP 13: Pick second character
        final GameCharacter thief = tut.pickCharacter(TypeEnum.THIEF);
        // STEP 14: Add second character to the raft
        tut.addToRaft(thief);
        // STEP 15: Move the raft and check constraints
        tut.moveRaft();
        // STEP 16: Check raft, coasts
        showRaftAndCoastsCharacters();
    }

    @Test
    public void winning_Game() {
        System.out.println(" ");
        System.out.println("SCENARIO: winning_Game");
        System.out.println("-------------------------------------------------------------------");
        // Move 1:
        GameCharacter policeman = tut.pickCharacter(TypeEnum.POLICEMAN);
        GameCharacter thief = tut.pickCharacter(TypeEnum.THIEF);
        tut.addToRaft(policeman);
        tut.addToRaft(thief);
        tut.moveRaft();
        // Move 2:
        policeman = tut.pickCharacter(TypeEnum.POLICEMAN);
        tut.addToRaft(policeman);
        tut.moveRaft();
        // Move 3:
        policeman = tut.pickCharacter(TypeEnum.POLICEMAN);
        GameCharacter son = tut.pickCharacter(TypeEnum.SON);
        tut.addToRaft(policeman);
        tut.addToRaft(son);
        tut.moveRaft();
        // Move 4:
        policeman = tut.pickCharacter(TypeEnum.POLICEMAN);
        thief = tut.pickCharacter(TypeEnum.THIEF);
        tut.addToRaft(policeman);
        tut.addToRaft(thief);
        tut.moveRaft();
        // Move 5:
        GameCharacter man = tut.pickCharacter(TypeEnum.MAN);
        son = tut.pickCharacter(TypeEnum.SON);
        tut.addToRaft(man);
        tut.addToRaft(son);
        tut.moveRaft();
        // Move 6:
        man = tut.pickCharacter(TypeEnum.MAN);
        tut.addToRaft(man);
        tut.moveRaft();
        // Move 7:
        man = tut.pickCharacter(TypeEnum.MAN);
        GameCharacter woman = tut.pickCharacter(TypeEnum.WOMAN);
        tut.addToRaft(man);
        tut.addToRaft(woman);
        tut.moveRaft();
        // Move 8:
        woman = tut.pickCharacter(TypeEnum.WOMAN);
        tut.addToRaft(woman);
        tut.moveRaft();
        // Move 9:
        policeman = tut.pickCharacter(TypeEnum.POLICEMAN);
        thief = tut.pickCharacter(TypeEnum.THIEF);
        tut.addToRaft(policeman);
        tut.addToRaft(thief);
        tut.moveRaft();
        // Move 10:
        man = tut.pickCharacter(TypeEnum.MAN);
        tut.addToRaft(man);
        tut.moveRaft();
        // Move 11:
        man = tut.pickCharacter(TypeEnum.MAN);
        woman = tut.pickCharacter(TypeEnum.WOMAN);
        tut.addToRaft(man);
        tut.addToRaft(woman);
        tut.moveRaft();
        // Move 12:
        woman = tut.pickCharacter(TypeEnum.WOMAN);
        tut.addToRaft(woman);
        tut.moveRaft();
        // Move 13
        woman = tut.pickCharacter(TypeEnum.WOMAN);
        GameCharacter daughter = tut.pickCharacter(TypeEnum.DAUGHTER);
        tut.addToRaft(woman);
        tut.addToRaft(daughter);
        tut.moveRaft();
        // Move 14:
        policeman = tut.pickCharacter(TypeEnum.POLICEMAN);
        thief = tut.pickCharacter(TypeEnum.THIEF);
        tut.addToRaft(policeman);
        tut.addToRaft(thief);
        tut.moveRaft();
        // Move 15:
        policeman = tut.pickCharacter(TypeEnum.POLICEMAN);
        daughter = tut.pickCharacter(TypeEnum.DAUGHTER);
        tut.addToRaft(policeman);
        tut.addToRaft(daughter);
        tut.moveRaft();
        // Move 16:
        policeman = tut.pickCharacter(TypeEnum.POLICEMAN);
        tut.addToRaft(policeman);
        tut.moveRaft();
        // Move 17:
        policeman = tut.pickCharacter(TypeEnum.POLICEMAN);
        thief = tut.pickCharacter(TypeEnum.THIEF);
        tut.addToRaft(policeman);
        tut.addToRaft(thief);
        tut.moveRaft();

        // Show Result
        showRaftAndCoastsCharacters();
    }

    private void showRaftAndCoastsCharacters() {
        final List<GameCharacter> rightCoastCharacters = tut.getRightCoast().getCharacters();
        final List<GameCharacter> leftCoastCharacters = tut.getLeftCoast().getCharacters();
        final List<GameCharacter> raftPassengers = tut.getRaft().getPassengers();

        System.out.println("LEFT COAST: \n" + leftCoastCharacters);
        System.out.println("-------------------------------------------------------------------");
        System.out.println("RAFT: \n" + raftPassengers);
        System.out.println("-------------------------------------------------------------------");
        System.out.println("RIGHT COAST: \n" + rightCoastCharacters);
        System.out.println("-------------------------------------------------------------------");
    }
}
