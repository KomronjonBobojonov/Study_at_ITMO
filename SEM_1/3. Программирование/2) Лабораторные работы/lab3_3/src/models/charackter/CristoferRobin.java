package models.charackter;

import enums.Circumstsnces;

public class CristoferRobin extends AbsCharacter{
    public static String stcName = "Кристофер Робин";

    public CristoferRobin() {
        super("Кристофер Робин");
    }

    public String Was(Circumstsnces was){
        return was.getName()+" "+stcName;
    }
}
