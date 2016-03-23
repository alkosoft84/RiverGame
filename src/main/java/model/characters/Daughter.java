package model.characters;

/**
 * Created by mwrobel on 29.01.16.
 */
public class Daughter extends GameCharacter {

    private Daughter() {
        super(TypeEnum.DAUGHTER, false);
    }

    public static Daughter createDaughter() {
        return new Daughter();
    }

}
