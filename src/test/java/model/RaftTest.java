package model;

import static model.characters.Son.createSon;
import static model.testmodel.TestCharacter.createTestCharacter;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.util.List;

import main.RiverGameImpl;
import model.characters.GameCharacter;
import model.characters.TypeEnum;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by mwrobel on 29.01.16.
 */
public class RaftTest {

    private RiverGameImpl tut;
    private GameCharacter testCharacter;

    @Before
    public void setUp() throws Exception {
        tut = new RiverGameImpl();
        tut.createGame();
        addTestCharacterToGameWhoCanSteerTheRaftAndDontHaveAnyConstrainst();
    }

    @Test
    public void raft_should_have_LEFT_state_after_game_creation() {
        // given
        // when
        final CoastsEnum raftState = tut.getRaft().getPosition();
        // then
        assertThat(raftState).isEqualTo(CoastsEnum.LEFT);
    }

    @Test
    public void raft_should_have_one_passenger_after_add_to_raft() {
        // given
        // when
        tut.addToRaft(testCharacter);
        // then
        final List<GameCharacter> passengers = tut.getRaft().getPassengers();
        assertThat(passengers.size()).isEqualTo(1);
    }

    @Test
    public void should_throw_exception_while_raft_without_passengers() {
        // given
        // when
        try {
            tut.moveRaft();
            fail("You can't raft without someone who can steer on board.");
        } catch (Exception ex) {
            // then
        }
    }

    @Test
    public void should_throw_exception_while_raft_without_someone_who_can_steer_on_board() {
        // given
        final GameCharacter son = createSon();
        tut.addToRaft(son);
        // when
        try {
            tut.moveRaft();
            fail("You can't raft without someone who can steer on board.");
        } catch (Exception ex) {
            // then
        }
    }

    @Test
    public void should_be_able_to_raft_when_have_someone_who_can_steer_on_board() {
        // given
        tut.addToRaft(testCharacter);
        // when
        tut.moveRaft();
        // then
        final boolean canSteerTheRaft = testCharacter.isCanSteerTheRaft();
        final CoastsEnum raftState = tut.getRaft().getPosition();
        assertThat(canSteerTheRaft).isEqualTo(true);
        assertThat(raftState).isEqualTo(CoastsEnum.RIGHT);
    }

    @Test
    public void raft_should_have_RIGHT_State_after_odd_rafting() {
        // given
        tut.addToRaft(testCharacter);
        // when
        tut.moveRaft();
        // then
        final CoastsEnum raftState = tut.getRaft().getPosition();
        assertThat(raftState).isEqualTo(CoastsEnum.RIGHT);
    }

    @Test
    public void raft_should_have_LEFT_state_after_even_rafting() {
        // given
        tut.addToRaft(testCharacter);
        // when
        tut.moveRaft();
        tut.addToRaft(testCharacter);
        tut.moveRaft();
        // then
        final Raft raft = tut.getRaft();
        assertThat(raft.getPosition()).isEqualTo(CoastsEnum.LEFT);
    }

    @Test
    public void left_coast_should_not_have_character_after_adding_to_raft() {
        // given
        // when
        tut.addToRaft(testCharacter);
        // then
        final List<GameCharacter> leftCoast = tut.getLeftCoast().getCharacters();
        assertThat(leftCoast)
                .extracting(GameCharacter::getType).doesNotContain(TypeEnum.TEST_CHARACTER);
    }

    @Test
    public void raft_should_have_no_passengers_after_remove_passenger() {
        // given
        // when
        addAndRemoveCharacter(testCharacter);
        // then
        final List<GameCharacter> passengers = tut.getRaft().getPassengers();
        assertThat(passengers.size()).isEqualTo(0);
    }

    @Test
    public void left_coast_should_have_character_after_add_and_remove_from_the_raft_without_raft_movement() {
        // given
        // when
        addAndRemoveCharacter(testCharacter);
        // then
        final List<GameCharacter> leftCoast = tut.getLeftCoast().getCharacters();
        assertThat(leftCoast)
                .extracting(GameCharacter::getType).contains(TypeEnum.TEST_CHARACTER);
    }

    @Test
    public void right_coast_should_have_character_after_raft_move_and_removing_from_the_raft() {
        // given
        // when
        addMoveAndRemoveCharacter(testCharacter);
        // then
        final List<GameCharacter> rightCoast = tut.getRightCoast().getCharacters();
        assertThat(rightCoast)
                .extracting(GameCharacter::getType).contains(TypeEnum.TEST_CHARACTER);
    }

    @Test
    public void right_coast_should_have_character_after_add_and_remove_from_the_raft_without_raft_movement() {
        // given
        addMoveAndRemoveCharacter(testCharacter);
        // when
        addAndRemoveCharacter(testCharacter);
        // then
        final List<GameCharacter> rightCoast = tut.getRightCoast().getCharacters();
        assertThat(rightCoast)
                .extracting(GameCharacter::getType).contains(TypeEnum.TEST_CHARACTER);
    }

    private void addTestCharacterToGameWhoCanSteerTheRaftAndDontHaveAnyConstrainst() {
        testCharacter = createTestCharacter();
        tut.addCharacterToCoast(testCharacter);
    }

    private void addAndRemoveCharacter(final GameCharacter character) {
        tut.addToRaft(character);
        tut.removeFromRaft(character);
    }

    private void addMoveAndRemoveCharacter(final GameCharacter character) {
        tut.addToRaft(character);
        tut.moveRaft();
        tut.removeFromRaft(character);
    }

}
