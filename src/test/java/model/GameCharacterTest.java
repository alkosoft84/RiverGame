package model;

import static model.characters.Daughter.createDaughter;
import static model.characters.Man.createMan;
import static model.characters.PoliceMan.createPoliceMan;
import static model.characters.Son.createSon;
import static model.characters.Thief.createThief;
import static model.characters.Woman.createWoman;
import static model.testmodel.TestCharacter.createTestCharacter;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import main.RiverGameImpl;
import model.characters.GameCharacter;
import model.characters.TypeEnum;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by mwrobel on 29.01.16.
 */
public class GameCharacterTest {

    private RiverGameImpl tut;

    @Before
    public void setUp() throws Exception {
        tut = new RiverGameImpl();
        tut.createGame();
    }

    @Test
    public void man_should_be_able_to_steer_raft_when_is_created() {
        // given
        // when
        final GameCharacter man = createMan();
        // then
        final boolean canSteerTheRaft = man.isCanSteerTheRaft();
        assertThat(canSteerTheRaft).isEqualTo(true);
    }

    @Test
    public void woman_should_be_able_to_steer_raft_when_is_created() {
        // given
        // when
        final GameCharacter woman = createWoman();
        // then
        final boolean canSteerTheRaft = woman.isCanSteerTheRaft();
        assertThat(canSteerTheRaft).isEqualTo(true);
    }

    @Test
    public void policeMan_should_be_able_to_steer_raft_when_is_created() {
        // given
        // when
        final GameCharacter policeMan = createPoliceMan();
        // then
        final boolean canSteerTheRaft = policeMan.isCanSteerTheRaft();
        assertThat(canSteerTheRaft).isEqualTo(true);
    }

    @Test
    public void son_should_NOT_be_able_to_steer_raft_when_is_created() {
        // given
        // when
        final GameCharacter son = createSon();
        // then
        final boolean canSteerTheRaft = son.isCanSteerTheRaft();
        assertThat(canSteerTheRaft).isEqualTo(false);
    }

    @Test
    public void daughter_should_NOT_be_able_to_steer_raft_when_is_created() {
        // given
        // when
        final GameCharacter daughter = createDaughter();
        // then
        final boolean canSteerTheRaft = daughter.isCanSteerTheRaft();
        assertThat(canSteerTheRaft).isEqualTo(false);
    }

    @Test
    public void thief_should_NOT_be_able_to_steer_raft_when_is_created() {
        // given
        // when
        final GameCharacter thief = createThief();
        // then
        final boolean canSteerTheRaft = thief.isCanSteerTheRaft();
        assertThat(canSteerTheRaft).isEqualTo(false);
    }

    @Test
    public void any_character_should_be_on_RIGHT_COAST_after_odd_rafting() {
        // given
        GameCharacter testCharacter = createTestCharacter();
        tut.addCharacterToCoast(testCharacter);
        // when
        movePassengerToOtherSide(testCharacter);
        // then
        final List<GameCharacter> rightCoast = tut.getRightCoast().getCharacters();
        assertThat(rightCoast)
                .extracting(GameCharacter::getType).contains(TypeEnum.TEST_CHARACTER);
    }

    @Test
    public void any_character_should_be_on_LEFT_COAST_after_even_rafting() {
        // given
        GameCharacter testCharacter = createTestCharacter();
        tut.addCharacterToCoast(testCharacter);
        // when
        movePassengerToOtherSide(testCharacter);
        movePassengerToOtherSide(testCharacter);
        // then
        final List<GameCharacter> leftCoast = tut.getLeftCoast().getCharacters();
        assertThat(leftCoast)
                .extracting(GameCharacter::getType).contains(TypeEnum.TEST_CHARACTER);

    }

    private void movePassengerToOtherSide(GameCharacter character) {
        tut.addToRaft(character);
        tut.moveRaft();
        tut.removeFromRaft(character);
    }
}
