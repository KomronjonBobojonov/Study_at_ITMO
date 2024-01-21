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

        story.addSentence(new Sentence(Sizes.BIG.getName()).gap(MeanTime.Seem.getName()).gap(Pronouns.Us.getName()).exclamatory(""));
        story.addSentence(new Sentence(tigra.back(MeanTime.TimeT, Condition.Happy, Verb.Jump, Direction.Ahead, MeanTime.TimeM, Circumstsnces.Comeing)).because(tigra.back2(Verb.Ask, Things.Here, Verb.Go)));
        story.addSentence(new Sentence(kengi.seem(MeanTime.Finally, Verb.Appeared, Things.House)).and(cristoferRobin.Was(Circumstsnces.Therewas)));
        story.addSentence(new Sentence(tigra.rushed(Verb.Rushed,Circumstsnces.AllFootsToHim, Pronouns.Him)).dot(""));
        scene.setStory(story);
        scene.play();

    }
}
