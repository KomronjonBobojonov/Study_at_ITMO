import models.charackter.CristoferRobin;
import models.charackter.Kengi;
import models.charackter.Tigra;
import models.charackter.Us;
import models.scene.Scene;
import models.scene.Sentence;
import models.scene.Story;

public class Main {

    /**
     * ОГРОМНЫМИ кажутся нам!
     * Тигра тем временем весело прыгал впереди, поминутно возвращаясь, чтобы спросить: "Сюда идти?"
     * И вот наконец показался домик Кенги, и там был Кристофер Робин.
     * Тигра бросился со всех ног к нему.
    */

    public static void main(String[] args) {
        var story = new Story();
        var scene = new Scene();

        scene.addCharacter(new Us());
        scene.addCharacter(new Tigra());
        scene.addCharacter(new Kengi());
        scene.addCharacter(new CristoferRobin());

        Us us = ((Us) scene.getCharacter(Us.stcName));
        Tigra tigra = ((Tigra) scene.getCharacter(Tigra.stcName));
        Kengi kengi = ((Kengi) scene.getCharacter((Kengi.stcName)));
        CristoferRobin cristoferRobin = ((CristoferRobin) scene.getCharacter(CristoferRobin.stcName));

        story.addSentence(new Sentence(us.action.perform()));
        story.addSentence(new Sentence(tigra.happyjump.observe()).comma(tigra.comeback.perform()).because(tigra.asking.perform()).doubledot(tigra.herego.perform()));
        story.addSentence(new Sentence("").and(kengi.appearedhouse.perform()).comma("").but(cristoferRobin.was.perform()));
        story.addSentence(new Sentence(tigra.rushed.perform()).with(tigra.allfoot.observe()));

//        scene.setStory(story);
//        scene.play();
        System.out.println(408281%36);
    }
}