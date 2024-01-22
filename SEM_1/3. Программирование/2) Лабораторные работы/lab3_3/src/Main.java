import enums.*;
import models.charackter.CristoferRobin;
import models.charackter.Kengi;
import models.charackter.Tigra;
import models.scene.Scene;
import models.scene.Sentence;
import models.scene.Story;

public class Main {

    /**
     * ОГРОМНЫМИ кажутся нам!
     * Тигра тем временем весело прыгал впереди, поминутно возвращаясь, чтобы спросить: Сюда идти?
     * И вот наконец показался домик Кенги, и там был Кристофер Робин.
     * Тигра бросился со всех ног к нему.
     */
    public static void main(String[] args) {
        var story = new Story();
        var scene = new Scene();

        scene.addCharacter(new Tigra());
        scene.addCharacter(new Kengi());
        scene.addCharacter(new CristoferRobin());

        Tigra tigra = ((Tigra) scene.getCharacter(Tigra.stcName));
        Kengi kengi = ((Kengi) scene.getCharacter((Kengi.stcName)));
        CristoferRobin cristoferRobin = ((CristoferRobin) scene.getCharacter(CristoferRobin.stcName));

        story.addSentence(new Sentence(Sizes.BIG.getName()).gap(MeanTime.SEEM.getName()).gap(Pronouns.US.getName()).exclamatory(""));
        story.addSentence(new Sentence(tigra.jump(MeanTime.TIME_T, Condition.HAPPY, Verb.JUMP, Direction.AHEAD, MeanTime.TIME_M, Circumstsnces.COMEING)).because(tigra.back2(Verb.ASK, Things.HERE, Verb.GO)));
        story.addSentence(new Sentence(kengi.seem(MeanTime.FINALLY, Verb.APPEARED, Things.HOUSE)).and(cristoferRobin.Was(Circumstsnces.THEREWAS)));
        story.addSentence(new Sentence(tigra.rushed(Verb.RUSHED,Circumstsnces.ALL_FOOTS_TO_HIM, Pronouns.HIM)).dot(""));
        scene.setStory(story);
        scene.play();

    }
}

