package model.testmodel;

import model.characters.GameCharacter;
import model.characters.TypeEnum;

/**
 * Created by mwrobel on 29.01.16.
 */
public class TestCharacter extends GameCharacter {

    private TestCharacter() {
        super(TypeEnum.TEST_CHARACTER, true);
    }

    public static TestCharacter createTestCharacter() {
        return new TestCharacter();
    }

}
