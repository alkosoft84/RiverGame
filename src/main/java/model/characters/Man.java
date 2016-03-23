package model.characters;

/**
 * Created by mwrobel on 29.01.16.
 */
public class Man extends GameCharacter {

    private Man() {
        super(TypeEnum.MAN, true);
    }

    public static Man createMan() {
        return new Man();
    }

}
