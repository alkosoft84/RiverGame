package model.characters;

/**
 * Created by mwrobel on 29.01.16.
 */
public class Son extends GameCharacter {

    private Son() {
        super(TypeEnum.SON, false);
    }

    public static Son createSon() {
        return new Son();
    }

}
