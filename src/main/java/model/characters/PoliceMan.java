package model.characters;

/**
 * Created by mwrobel on 29.01.16.
 */
public class PoliceMan extends GameCharacter {

    private PoliceMan() {
        super(TypeEnum.POLICEMAN, true);
    }

    public static PoliceMan createPoliceMan() {
        return new PoliceMan();
    }
}
