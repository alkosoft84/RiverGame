package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import main.RiverGameImpl;
import model.characters.GameCharacter;
import model.characters.TypeEnum;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by mwrobel on 27.01.16.
 */
public class RiverGameImplTest {

    private RiverGameImpl tut;

    @Before
    public void setUp() throws Exception {
        tut = new RiverGameImpl();
    }

    @Test
    public void game_should_have_raft_with_No_passengers_when_game_is_created() {
        // given
        // when
        tut.createGame();
        // then
        final List<GameCharacter> raftPassengers = tut.getRaft().getPassengers();
        final int countPassengers = raftPassengers.size();
        assertThat(countPassengers).isEqualTo(0);
    }

    @Test
    public void game_should_have_eight_characters_on_the_left_coast_when_game_is_created() {
        // given
        // when
        tut.createGame();
        // then
        final int characterCount = tut.getLeftCoast().getCharacters().size();
        assertThat(characterCount).isEqualTo(8);
    }

    @Test
    public void game_should_Have_All_Types_Of_Character_When_Game_Is_Created() {
        // given
        // when
        tut.createGame();
        // then
        final List<GameCharacter> gameCharacters = tut.getLeftCoast().getCharacters();
        assertThat(gameCharacters)
                .extracting(GameCharacter::getType)
                .contains(TypeEnum.DAUGHTER, TypeEnum.DAUGHTER,
                        TypeEnum.SON, TypeEnum.SON, TypeEnum.WOMAN, TypeEnum.MAN,
                        TypeEnum.THIEF, TypeEnum.POLICEMAN);
    }

    @Test
    public void game_should_return_character_from_actual_coast_by_type() {
        // given
        tut.createGame();
        // when
        final GameCharacter gameCharacter = tut.pickCharacter(TypeEnum.THIEF);
        // then
        assertThat(gameCharacter).extracting(GameCharacter::getType).contains(TypeEnum.THIEF);
    }

}
