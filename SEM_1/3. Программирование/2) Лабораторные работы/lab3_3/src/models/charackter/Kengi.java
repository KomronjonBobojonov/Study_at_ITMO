package models.charackter;

import enums.*;

public class Kengi extends AbsCharacter {
    public static String stcName = "Кенги";

    public Kengi() {
        super("Кенги");
    }
    public String seem(MeanTime finaly, Verb show, Things house){
        return finaly.getName()+" "+show.getName()+" "+house.getName()+" "+stcName;
    }
}
