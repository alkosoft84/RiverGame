package model.characters;

/**
 * Created by mwrobel on 29.01.16.
 */
public class Woman extends GameCharacter {

    private Woman() {
        super(TypeEnum.WOMAN, true);
    }

    public static Woman createWoman() {
        return new Woman();
    }

}
