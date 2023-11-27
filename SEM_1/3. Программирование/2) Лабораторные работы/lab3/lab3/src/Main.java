package org.example;

import org.example.enums.Place;
import org.example.models.charackter.Misa;
import org.example.models.charackter.Snork;
import org.example.models.charackter.UnknownWoman;
import org.example.models.scene.Scene;
import org.example.models.scene.Sentence;
import org.example.models.scene.Story;

/**
 * Она взяла карманный фонарик и вышла в коридор.
 * Там никого не было.
 * Но Миса не отвечала.
 * Фрекен Снорк увидела узкую полоску света, пробивающуюся сквозь полуоткрытую дверь, и на цыпочках вошла туда.
 * Там сидела Миса, и лоси у нее на голове были совсем другие.
 */
public class Main {
    public static void main(String[] args) {
        var story = new Story();

        var scene = new Scene();

        scene.addCharacter(new UnknownWoman());
        scene.addCharacter(new Misa());
        scene.addCharacter(new Snork());

        UnknownWoman unknownWoman = ((UnknownWoman) scene.getCharacter(UnknownWoman.stcName));
        Misa misa = ((Misa) scene.getCharacter(Misa.stcName));
        Snork snork = ((Snork) scene.getCharacter(Snork.stcName));

        story.addSentence(new Sentence(unknownWoman.action.perform()).and(unknownWoman.move.moveTo(Place.CORRIDOR)));
        story.addSentence(new Sentence(unknownWoman.observeCorridorAction.observe()));
        story.addSentence(new Sentence(misa.respond.perform()));
        story.addSentence(new Sentence(snork.notice.observe()).comma(snork.comeCloser.perform()).and(snork.getIntoSilently.perform()));
        story.addSentence(new Sentence(snork.observeCharacter.observe()).and(snork.describeHead.perform()));

        scene.setStory(story);
        scene.play();
    }
}