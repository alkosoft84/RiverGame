package model.characters;

/**
 * Created by mwrobel on 29.01.16.
 */
public class Thief extends GameCharacter {

    private Thief() {
        super(TypeEnum.THIEF, false);
    }

    public static Thief createThief() {
        return new Thief();
    }

}
