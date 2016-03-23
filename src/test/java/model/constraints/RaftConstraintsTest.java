package model.constraints;

import static model.characters.Daughter.createDaughter;
import static model.characters.Man.createMan;
import static model.characters.PoliceMan.createPoliceMan;
import static model.characters.Son.createSon;
import static model.characters.Thief.createThief;
import static model.characters.Woman.createWoman;
import static org.assertj.core.api.Assertions.assertThat;

import main.RiverGameImpl;
import model.characters.GameCharacter;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by mwrobel on 08.03.16.
 */
public class RaftConstraintsTest {

    private RiverGameImpl tut;
    private GameCharacter man;
    private GameCharacter woman;
    private GameCharacter policeMan;
    private GameCharacter son;
    private GameCharacter daughter;
    private GameCharacter thief;

    @Before
    public void setUp() throws Exception {
        tut = new RiverGameImpl();
        tut.createGame();
        createCharacters();
    }

    @Test
    public void noOne_except_policeMan_should_NOT_be_able_to_raft_with_thief() {
        // given
        tut.addToRaft(man);
        tut.addToRaft(thief);
        // when
        final boolean constraintsAreOk = tut.getRaft().checkConstraintsOnRaft();
        // then
        assertThat(constraintsAreOk).isEqualTo(false);
    }

    @Test
    public void daughter_should_NOT_be_able_to_raft_with_man() {
        // given
        tut.addToRaft(daughter);
        tut.addToRaft(man);
        // when
        final boolean constraintsAreOk = tut.getRaft().checkConstraintsOnRaft();
        // then
        assertThat(constraintsAreOk).isEqualTo(false);
    }

    @Test
    public void son_should_NOT_be_able_to_raft_with_woman() {
        // given
        tut.addToRaft(son);
        tut.addToRaft(woman);
        // when
        final boolean constraintsAreOk = tut.getRaft().checkConstraintsOnRaft();
        // then
        assertThat(constraintsAreOk).isEqualTo(false);
    }

    @Test
    public void policeMan_should_be_able_to_raft_with_thief() {
        // given
        tut.addToRaft(policeMan);
        tut.addToRaft(thief);
        // when
        final boolean constraintsAreOk = tut.getRaft().checkConstraintsOnRaft();
        // then
        assertThat(constraintsAreOk).isEqualTo(true);
    }

    private void createCharacters() {
        man = createMan();
        woman = createWoman();
        policeMan = createPoliceMan();
        son = createSon();
        daughter = createDaughter();
        thief = createThief();
    }
}
