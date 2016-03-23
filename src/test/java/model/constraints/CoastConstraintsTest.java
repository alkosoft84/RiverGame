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
public class CoastConstraintsTest {

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
        clearLeftCoast();
    }

    // For 0 and 1 character
    @Test
    public void game_should_be_able_to_have_empty_coast() {
        // given
        // when
        final boolean constraintsAreOk = tut.getLeftCoast().checkConstraintsOnCoast();
        // then
        assertThat(constraintsAreOk).isEqualTo(true);
    }

    @Test
    public void coast_should_be_able_to_have_any_onlyOne_character() {
        // given
        tut.addCharacterToCoast(thief);
        // when
        final boolean constraintsAreOk = tut.getLeftCoast().checkConstraintsOnCoast();
        // then
        assertThat(constraintsAreOk).isEqualTo(true);
    }

    // For two character
    @Test
    public void noOne_should_be_able_to_be_with_thief() {
        // given
        tut.addCharacterToCoast(man);
        tut.addCharacterToCoast(thief);
        // when
        final boolean constraintsAreOk = tut.getLeftCoast().checkConstraintsOnCoast();
        // then
        assertThat(constraintsAreOk).isEqualTo(false);
    }

    @Test
    public void policeMan_should_be_able_to_be_on_coast_with_thief() {
        // given
        tut.addCharacterToCoast(thief);
        tut.addCharacterToCoast(policeMan);
        // when
        final boolean constraintsAreOk = tut.getLeftCoast().checkConstraintsOnCoast();
        // then
        assertThat(constraintsAreOk).isEqualTo(true);
    }

    @Test
    public void man_should_NOT_be_able_to_be_with_daughter() {
        // given
        tut.addCharacterToCoast(man);
        tut.addCharacterToCoast(daughter);
        // when
        final boolean constraintsAreOk = tut.getLeftCoast().checkConstraintsOnCoast();
        // then
        assertThat(constraintsAreOk).isEqualTo(false);
    }

    @Test
    public void woman_should_NOT_be_able_to_be_with_son() {
        // given
        tut.addCharacterToCoast(woman);
        tut.addCharacterToCoast(son);
        // when
        final boolean constraintsAreOk = tut.getLeftCoast().checkConstraintsOnCoast();
        // then
        assertThat(constraintsAreOk).isEqualTo(false);
    }

    // for more than two characters
    @Test
    public void daughter_should_NOT_be_able_to_be_with_man_without_woman() {
        // given
        tut.addCharacterToCoast(daughter);
        tut.addCharacterToCoast(daughter);
        tut.addCharacterToCoast(man);
        // when
        final boolean constraintsAreOk = tut.getLeftCoast().checkConstraintsOnCoast();
        // then
        assertThat(constraintsAreOk).isEqualTo(false);
    }

    @Test
    public void daughter_should_be_able_to_be_with_man_and_woman() {
        // given
        tut.addCharacterToCoast(daughter);
        tut.addCharacterToCoast(man);
        tut.addCharacterToCoast(woman);
        // when
        final boolean constraintsAreOk = tut.getLeftCoast().checkConstraintsOnCoast();
        // then
        assertThat(constraintsAreOk).isEqualTo(true);
    }

    @Test
    public void daughter_should_be_able_to_be_with_policeMan_and_thief() {
        // given
        tut.addCharacterToCoast(daughter);
        tut.addCharacterToCoast(thief);
        tut.addCharacterToCoast(policeMan);
        // when
        final boolean constraintsAreOk = tut.getLeftCoast().checkConstraintsOnCoast();
        // then
        assertThat(constraintsAreOk).isEqualTo(true);
    }

    @Test
    public void daughter_should_be_able_to_be_with_another_daughter_without_woman_with_thief_and_policeMan() {
        // given
        tut.addCharacterToCoast(daughter);
        tut.addCharacterToCoast(daughter);
        tut.addCharacterToCoast(thief);
        tut.addCharacterToCoast(policeMan);
        // when
        final boolean constraintsAreOk = tut.getLeftCoast().checkConstraintsOnCoast();
        // then
        assertThat(constraintsAreOk).isEqualTo(true);
    }

    @Test
    public void son_should_NOT_be_able_to_be_with_woman_without_man() {
        // given
        tut.addCharacterToCoast(son);
        tut.addCharacterToCoast(son);
        tut.addCharacterToCoast(woman);
        // when
        final boolean constraintsAreOk = tut.getLeftCoast().checkConstraintsOnCoast();
        // then
        assertThat(constraintsAreOk).isEqualTo(false);
    }

    @Test
    public void son_should_be_able_to_be_with_woman_and_man() {
        // given
        tut.addCharacterToCoast(son);
        tut.addCharacterToCoast(man);
        tut.addCharacterToCoast(woman);
        // when
        final boolean constraintsAreOk = tut.getLeftCoast().checkConstraintsOnCoast();
        // then
        assertThat(constraintsAreOk).isEqualTo(true);
    }

    @Test
    public void thief_should_be_able_to_be_with_anyone_with_policeMan() {
        // given
        tut.addCharacterToCoast(thief);
        tut.addCharacterToCoast(policeMan);
        tut.addCharacterToCoast(woman);
        // when
        final boolean constraintsAreOk = tut.getLeftCoast().checkConstraintsOnCoast();
        // then
        assertThat(constraintsAreOk).isEqualTo(true);
    }

    @Test
    public void thief_should_NOT_be_able_to_be_with_anyone_without_policeMan() {
        // given
        tut.addCharacterToCoast(thief);
        tut.addCharacterToCoast(man);
        tut.addCharacterToCoast(woman);
        // when
        final boolean constraintsAreOk = tut.getLeftCoast().checkConstraintsOnCoast();
        // then
        assertThat(constraintsAreOk).isEqualTo(false);
    }

    private void clearLeftCoast() {
        tut.getLeftCoast().getCharacters().clear();
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
