import java.util.*;

public class TextAdventure {
  FancyConsole console;
  Scanner inScanner;
  Player ourHero;
  String whitespace;
  String input;

  public TextAdventure() {
    console = new FancyConsole("Great Text Adventure!", 600, 600);
    inScanner = new Scanner(System.in);

    // feel free to change the player's starting values
    ourHero = new Player("Bob", 300, 150, 350, 10, "DAGGER");
  }

  public void play() {

    // start of adventure. You can change this if you like
    console.setImage("distantcity.jpg");

    // ask the user for their name.
    System.out.println("What is your name?\n");
    input = inScanner.nextLine();

    // Change ourHero's name
    ourHero.changeName(input);

    System.out
        .println("Hello, thank you for playing my game. There are some things you need to know \n   before starting.");
    System.out.println(
        "Whenever a choice is presented to you, type out exactly what is in brackets \n   to make your selection.");
    System.out.println(
        "Whenever the game seems to stop and you're not prompted with anything, just \n   enter nothing for the input. It's there to give you time to read the text \n   without overloading you.");
    System.out.println("");
    whitespace = inScanner.nextLine();

    // Adding starting cantrip and skills
    ourHero.addCantrip("SHARPEN BLADE");
    ourHero.addSkill("MULTI-ATTACK");
    ourHero.addSkill("MINOR HEAL");
    ourHero.addSkill("RECALL");
    ourHero.addSkill("DEFEND");

    // describe the starting situation. Feel free to change this
    System.out.println(
        "You wake up to find yourself on the edge of a shadowy forest with the sun \n   nearly set. You know monsters are about to come out of hiding.");
    System.out.println("You check your inventory: a " + ourHero.getWeapon() + ", camping supples, \n   some rope, and "
        + ourHero.getGold()
        + " gold. You see what looks like an abandoned city in \n   the distance. It's foreign land to you, but maybe you can take shelter for the night there. \n   There's also the option of going back into the forest. You could get attacked by monsters, but \n   at least you're familiar with the terrain. \n");
    System.out.println(
        "What would you like to do? \n[1]: Go back to sleep \n[2]: Turn around and re-enter the forest \n[3]: Go towards the city \n");

    // get user input and go to the appropriate zone based on their input
    String zoneInput = inScanner.nextLine();
    while (true) {
      if (zoneInput.equals("1")) {
        System.out.println(
            "\nYou decide to rest near the edge of the forest. The forest itself is \n   hostile with monsters, and the city is too unfamiliar to you.");
        enterZone1();
        break;
      } else if (zoneInput.equals("2")) {
        System.out.println(
            "\nYou decide to take your chances with the forest again. The city being \n   unfamiliar makes it more dangerous than your already charted terrain.");
        enterZone3();
        break;
      } else if (zoneInput.equals("3")) {
        System.out.println(
            "\nYou decide to venture into the city to take shelter for the night. \n   It seems better than going back through the forest again with all of its monsters.");
        enterZone6();
        break;

        // Used to test enemy combat
      } else if (zoneInput.equals("Pantheon")) {
        System.out.println("\nYou obtained all available CANTRIPS and SKILLS");
        ourHero.addCantrip("COURAGE");
        ourHero.addCantrip("RUNIC STRENGTH");
        ourHero.addCantrip("MINOR SHIELD");
        ourHero.addCantrip("SECOND WIND");
        ourHero.addCantrip("MINOR RECALL");
        ourHero.addCantrip("HOPE");
        ourHero.addCantrip("CALM DOWN");
        ourHero.addCantrip("QUICK THINKING");

        ourHero.addSkill("FALCON SLASH");
        ourHero.addSkill("WHIRLWIND");
        ourHero.addSkill("HEMOKENESIS");
        ourHero.addSkill("LIGHT FLASH");
        ourHero.addSkill("BLACK STAR");
        ourHero.addSkill("ENERGY SURGE");
        ourHero.addSkill("GOLDEN SHIELDS");
        ourHero.addSkill("ABSORPTION");
        ourHero.addSkill("REAPER");
        ourHero.addSkill("DEMON FORM");

        System.out.println("\nType who you want to kill.\n");
        input = inScanner.nextLine();

        if (ourHero.defeatMonster(input)) {
          System.out.println("Congratulations, you killed them.");
        } else {
          System.out.println("Oof, better luck next time.");
        }

        break;
      } else {
        System.out.println("Invalid option, please try again\n");
        zoneInput = inScanner.nextLine();
      }
    }

  }

  // Most of the Zone code is just story stuff. Don't worry too much about it.

  private void enterZone1() {
    /*
     * Rest
     */

    /*
     * Look man, I'm not a writer Don't expect my "story" to be any good haha
     * 
     * It's just a nightmare where you confront manifestations of your fears Heights
     * (Phantom), the Unknown (Nightwalker), Spiders (The Collective), Dolls/Puppets
     * (The Marionette), and yourself
     * 
     * You're prone to anxiety attacks when things go awry, as you start to go into
     * one upon arriving
     * 
     * There's also some vague story with you killing a man in the real world who
     * was running towards you to ask for help You killing the Nightwalker reminds
     * you of that, and you go into an anxiety attack.
     */

    // change image
    console.setImage("Rest.jpg");

    // describe the area/situation to the user.
    // Give them options for choices.

    whitespace = inScanner.nextLine();
    System.out.println("\n. . .");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou awake up. You're not in the same place. Everything is shrouded in a hazy mist.");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou're freaking out. You don't know where you are. Everything is foreign.");
    System.out.println("You start hyperventilating.");
    whitespace = inScanner.nextLine();
    System.out.println("\nIs this area hostile? Are you safe?");
    System.out.println("Your bpm increases.");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou try to collect yourself. To calm down.");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou tell yourself everything will be okay.");
    System.out.println("Your breathing steadies.");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou can do this.");
    System.out.println("Everything will be okay.");
    System.out.println("Your heart beats steadily.");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou learnt how to CALM DOWN (CANTRIP)");
    ourHero.addCantrip("CALM DOWN");
    whitespace = inScanner.nextLine();
    System.out.println("\nJust as you start to calm down, you're attacked.");
    System.out.println("You prepare to fight.");
    whitespace = inScanner.nextLine();

    // Combat initiates. If the boolean returns false, the death seqeunce initiates.
    if (ourHero.defeatMonster("Phantom")) {
      System.out.println("\nYou killed the Phantom, but you still feel uneasy.");
    } else {
      System.out.println("\nYou died to the Phantom.");
      enterZone0();
    }

    whitespace = inScanner.nextLine();

    // Gives the player a choice. They get a differnet skill depending on the choice
    System.out.println("\nYou see a silhouette of a man in the distance. He seems to be pointing somewhere.");
    System.out.println("Maybe they can help you.");
    System.out.println("Do you approach him?");
    System.out.println("[1]: Yes \n[2]: No\n");
    input = inScanner.nextLine();

    // Continue running the code until the player inputs a correct repsonse
    while (true) {
      if (input.equals("1")) {
        System.out.println("\nYou approach the stranger.");
        whitespace = inScanner.nextLine();
        System.out.println("\n. . .");
        whitespace = inScanner.nextLine();
        System.out.println("\nHe don't seem to take notice of you. He continues pointing.");
        System.out.println("You look in the direction that they're pointing to.");
        System.out.println("You can make out something shining in the distance.");
        whitespace = inScanner.nextLine();
        System.out.println("\nYou look back to the man. He's not there anymore.");
        whitespace = inScanner.nextLine();
        System.out.println("\nYou go to the shining object.");
        whitespace = inScanner.nextLine();
        System.out.println("\nIt's a glowing, white orb.");
        System.out.println("As you reach out to pick it up, the orb shatters and it's light is absorbed into you.");
        whitespace = inScanner.nextLine();
        System.out.println("\nYou learnt LIGHT FLASH (SKILL)");
        ourHero.addSkill("LIGHT FLASH");
        break;

      } else if (input.equals("2")) {
        System.out.println("\nIt's best to play it safe. You don't know if he's friendly.");
        System.out.println("You continue to wander the darkness without any companionship");
        whitespace = inScanner.nextLine();
        System.out.println("\nAs you turn away from the man, a black vine seems grabs your hand.");
        System.out.println("You look back to the man suddendly. He hasn't moved. He hasn't done anything.");
        whitespace = inScanner.nextLine();
        System.out.println("\nYou look down. You see the vines coming out of the ground.");
        System.out.println("More of the vines come out and drag you down into the ground.");
        System.out.println("Before you can react, you sink.");
        whitespace = inScanner.nextLine();
        System.out.println("\n. . .");
        whitespace = inScanner.nextLine();
        System.out.println("\nYou open your eyes.");
        System.out.println("You notice you are breathing rapidly and a sweat drop falls to the ground");
        whitespace = inScanner.nextLine();
        System.out.println("\nYou seem to be in the same place, unharmed");
        System.out.println("The man is gone.");
        whitespace = inScanner.nextLine();
        System.out.println("\nBeside you lies a strange orb. You can barely make it out in the darkness.");
        System.out.println("You instinctively reach out to it. It shatters, and its power is absorbed into you.");
        whitespace = inScanner.nextLine();
        System.out.println("\nYou learnt BLACK STAR (SKILL)");
        ourHero.addSkill("BLACK STAR");
        break;

      } else {
        System.out.println("Invalid Option. Please try again.");
        input = inScanner.nextLine();
      }
    }

    whitespace = inScanner.nextLine();
    System.out.println("\nYou look around again, and see something approaching you.");
    System.out.println("It looks human, but definitely not the man you saw before.");
    System.out.println("It's covered with some black mist, and you can't make out its definitive shape.");
    whitespace = inScanner.nextLine();
    System.out.println("\nIt starts to break into a sprint, headed straight towards you.");
    System.out.println("You prepare to fight.");
    whitespace = inScanner.nextLine();

    // Combat again
    if (ourHero.defeatMonster("Nightwalker")) {
      System.out.println("\nYou killed the Nightwalker. It's blood stains your hands and DAGGER.");
    } else {
      System.out.println("\nYou died to the Nightwalker.");
      enterZone0();
    }

    whitespace = inScanner.nextLine();
    System.out.println("\nWhat if you killed an innocent?");
    System.out.println("What if you killed someone who was trying to help you?");
    System.out.println("What if he was running towards you to warn you or to ask for help?");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou start hearing voices out of the corner of your ear.");
    System.out.println("'Murderer, murderer, murderer...'");
    System.out.println("You don't know where these voices are coming from.");
    whitespace = inScanner.nextLine();
    System.out.println("\nThe chanting keeps getting louder and louder");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou start hyperventilating.");
    whitespace = inScanner.nextLine();
    System.out.println("\n'Murderer, murderer, murderer...'");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou shut your eyes.");
    System.out.println("Y̵o̵u̶ ̵w̵a̵n̵t̸ ̷t̷o̶ ̶l̷e̷a̴v̷e̸.");
    whitespace = inScanner.nextLine();
    System.out.println("\n'Murderer, murderer...'");
    whitespace = inScanner.nextLine();
    System.out.println("\nYour heart is heating rapidly.");
    whitespace = inScanner.nextLine();
    System.out.println("\n'Murderer, murderer...'");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou tell yourself everything will be okay.");
    System.out.println("Everything will be okay.");
    whitespace = inScanner.nextLine();
    System.out.println("\n'Murderer, murderer...'");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou try to CALM DOWN.");
    System.out.println("You try to steady your breathing.");
    whitespace = inScanner.nextLine();
    System.out.println("\n. . .");
    whitespace = inScanner.nextLine();
    System.out.println("It doesn't work.");
    whitespace = inScanner.nextLine();
    System.out.println("\n'Murderer, murderer...'");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou're stressed out.");
    whitespace = inScanner.nextLine();
    System.out.println("\nyou want to leave");
    whitespace = inScanner.nextLine();
    System.out.println("You want to leave");
    whitespace = inScanner.nextLine();
    System.out.println("YOU WANT TO LEAVE");
    whitespace = inScanner.nextLine();
    System.out.println("\n. . .");
    whitespace = inScanner.nextLine();
    System.out.println("\nEverything suddenly goes quiet. There's a moment of calm.");
    System.out.println("You're restored to max hp.");
    whitespace = inScanner.nextLine();

    enterZone2();

  }

  private void enterZone2() {
    /*
     * Nightmare
     */

    // change image
    console.setImage("Nightmare.jpg");

    // describe the area/situation to the user.
    // Give them options for choices.
    ourHero.setHealth(300);
    System.out.println("\nAs you open your eyes, you see that you're not in the same place again.");
    System.out.println("Before you can take in your surroundings, a large creature approaches you.");
    whitespace = inScanner.nextLine();
    System.out.println("\nYour mind freezes in fear.");
    System.out.println("Your body instinctively draws your DAGGER.");
    whitespace = inScanner.nextLine();
    System.out.println("\n. . .");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou prepare to fight.");
    whitespace = inScanner.nextLine();

    // Combat
    if (ourHero.defeatMonster("The Collective")) {
      System.out.println("\nThe monster hisses and screams before falling to the ground.");
    } else {
      System.out.println("\nYou died to The Collective.");
      enterZone0();
    }

    whitespace = inScanner.nextLine();
    System.out.println("\nYou don't know what you just killed, but you know that it's not human.");
    System.out.println("Its body slowly disappates into the dark surroundings");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou take a moment to view your surroundings.");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou're in a clearing in the woods.");
    System.out.println("You can clearly see the black sky and the stars.");
    System.out.println("The wind blows gently across your face");
    whitespace = inScanner.nextLine();
    System.out.println("\nIt's a moment of calm in your adventure.");
    whitespace = inScanner.nextLine();
    System.out.println("\nLooking at the moon and stars gives you a sense of hope.");
    System.out.println("You know you can get through all of this.");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou were given HOPE (CANTRIP)");
    ourHero.addCantrip("HOPE");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou decide to stand idly for a while.");
    System.out.println("It's been a while since you've been able to experience tranquility.");
    whitespace = inScanner.nextLine();
    System.out.println("\nSome time passes...");
    whitespace = inScanner.nextLine();
    System.out.println("\nThe silence is broken the sound of water splashing behind you.");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou turn to face the noise.");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou're faced with another humanoid creature.");
    System.out.println("It stands 7 ft tall, much taller than you.");
    System.out.println("You're unsure if you can beat it.");
    whitespace = inScanner.nextLine();
    System.out.println("\nIt charges at you.");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou prepare to fight.");
    whitespace = inScanner.nextLine();

    // Combat
    if (ourHero.defeatMonster("The Marionette")) {
      System.out.println("\nThe Marionette screams in agony before it falls to the ground, motionless.");
    } else {
      System.out.println("\nYou died to The Marionette.");
      enterZone0();
    }

    whitespace = inScanner.nextLine();
    System.out.println("\n. . .");
    whitespace = inScanner.nextLine();
    System.out.println("\nThere was no blood that spewed out from that creature.");
    System.out.println("Instead, the creature seemed to be made from wood and string.");
    whitespace = inScanner.nextLine();
    System.out.println("\nLike The Collective, it's body slowly disappates into the ground.");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou hear a voice behind you.");
    System.out.println("???: 'Still off slaughtering everything you meet, it seems.'");
    System.out.println("???: 'Didn't even need my help this time.'");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou recognise that voice.");
    System.out.println("You turn to face it.");
    whitespace = inScanner.nextLine();
    System.out.println("\nIt's you, covered in blood.");
    System.out.println("They're smiling.");
    whitespace = inScanner.nextLine();
    System.out.println("\n" + ourHero.getName() + "?: 'You never know when to hold back.'");
    System.out.println(ourHero.getName() + "?: 'I love that about you.'");
    whitespace = inScanner.nextLine();
    System.out.println("\nShut up.");
    whitespace = inScanner.nextLine();
    System.out.println("You draw your DAGGER.");
    whitespace = inScanner.nextLine();
    System.out.println("\n" + ourHero.getName() + "?: 'Come on now, don't be like that.'");
    System.out.println(ourHero.getName() + "?: 'You know that I'm right.'");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou ignore his retort and begin to approach them.");
    whitespace = inScanner.nextLine();
    System.out.println("\n" + ourHero.getName() + "?: 'Approaching me for once. Aren't you scared?'");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou say nothing and continue walking towards them.");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou feel stronger, more confident.");
    whitespace = inScanner.nextLine();
    System.out.println("You learnt COURAGE (CANTRIP)");
    ourHero.addCantrip("COURAGE");
    System.out.println("You're restored to max hp.");
    ourHero.setHealth(300);
    whitespace = inScanner.nextLine();
    System.out.println("\n" + ourHero.getName() + "?: 'I see you've gotten bolder. Let's see where that takes you.'");
    whitespace = inScanner.nextLine();
    System.out.println("\n" + ourHero.getName() + "? draws their DAGGER");
    System.out.println(ourHero.getName() + "?: 'Come, let us dance this bloody dance beneath the stars!'");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou prepare to fight.");
    whitespace = inScanner.nextLine();

    // Final boss of this route, whoop.
    if (ourHero.defeatMonster(ourHero.getName())) {
      System.out.println("\nYour doppleganger bleeds, but doesn't cry out in pain.");
    } else {
      System.out.println("\nYou died to yourself.");
      enterZone0();
    }

    whitespace = inScanner.nextLine();
    System.out.println("\n" + ourHero.getName() + ": 'Ha... ha...'");
    whitespace = inScanner.nextLine();
    System.out.println(ourHero.getName() + ": 'You... can't escape me...'");
    whitespace = inScanner.nextLine();
    System.out.println(ourHero.getName() + ": 'forever...'");
    whitespace = inScanner.nextLine();
    System.out.println("\nWith that, your doppleganger fades away.");
    whitespace = inScanner.nextLine();
    System.out.println("\n. . .");
    whitespace = inScanner.nextLine();
    console.setImage("distantcity.jpg");
    System.out.println("\nYou suddenly open your eyes to see yourself at the forest edge.");
    whitespace = inScanner.nextLine();
    System.out.println("\nJust another nightmare.");
    whitespace = inScanner.nextLine();
    System.out.println("\n. . .");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou don't feel like adventuring anymore.");
    gameEnd();

  }

  private void enterZone3() {
    /*
     * Forest
     */

    // change image
    console.setImage("animals.jpg");

    // describe the area/situation to the user.
    whitespace = inScanner.nextLine();
    System.out.println("\n. . .");
    whitespace = inScanner.nextLine();
    System.out.println("\nWhile walking, you notice a pack of animals.");
    System.out.println("Do you decide to hunt them?");
    System.out.println("[1]: Yes");
    System.out.println("[2]: No");

    while (true) {
      input = inScanner.nextLine();
      if (input == "1") {
        whitespace = inScanner.nextLine();
        System.out.println("\nYou wait patiently for them to pass by you.");
        System.out.println("And then you strike the moose with your DAGGER.");
        whitespace = inScanner.nextLine();
        System.out.println("\nYou prepare to fight.");
        whitespace = inScanner.nextLine();

        if (ourHero.defeatMonster("Moose")) {
          System.out.println("\nThe moose falls down to the ground, dead from your attacks.");
          System.out.println("It didn't stand a chance to be honest.");
        } else {
          System.out.println("\nYou died to the moose.");
          System.out.println("...how the hell did you die to a moose?");
          System.out.println("Sending you to blackspace.");
          enterZone0();
        }

        whitespace = inScanner.nextLine();
        System.out.println("\nYou build a fire near the moose's corpse, out of sight from other monsters.");
        whitespace = inScanner.nextLine();
        System.out.println("\n. . .");
        whitespace = inScanner.nextLine();
        console.setImage("forest.jpg");
        System.out.println("\nNight falls upon you, and you feast upon your recent kill.");
        whitespace = inScanner.nextLine();
        System.out.println("\nYou recovered 50hp.");
        ourHero.addHealth(50);

        whitespace = inScanner.nextLine();
        System.out.println("\n. . .");
        whitespace = inScanner.nextLine();
        System.out.println("\nYou have the feeling that you're being watched.");
        System.out.println("Maybe the scent of meat attracted something.");
        whitespace = inScanner.nextLine();
        System.out.println("\nSuddenly, a hound jumps out at you.");
        whitespace = inScanner.nextLine();
        System.out.println("\nYou prepare to fight.");
        whitespace = inScanner.nextLine();

        if (ourHero.defeatMonster("Rogue Hound")) {
          System.out.println("\nThe meat theif dies to your DAGGER.");
        } else {
          System.out.println("\nYou died to the rabid dog.");
          System.out.println("C'mon, that enemy isn't that hard to kill.");
          System.out.println("Sigh. Sending you to blackspace.");
          enterZone0();
        }

        whitespace = inScanner.nextLine();
        System.out.println("\nYou weren't prepared for that.");
        System.out.println("Luckily, you're not tired all that much.");
        whitespace = inScanner.nextLine();
        System.out.println("\nYou learnt SECOND WIND (CANTRIP).");
        ourHero.addCantrip("SECOND WIND");
        whitespace = inScanner.nextLine();
        System.out.println("\nYou think that maybe you should move locations.");
        System.out.println("You kick out your fire, and set up camp somewhere else.");
        whitespace = inScanner.nextLine();
        System.out.println("\nYou setup a camp elsewhere and rest for a bit.");
        whitespace = inScanner.nextLine();
        System.out.println("\nYou recovered 25hp.");
        ourHero.addHealth(25);

        whitespace = inScanner.nextLine();
        System.out.println("\n. . .");
        whitespace = inScanner.nextLine();
        System.out.println("\nAs you wake up, you notice something.");
        System.out.println("You're very close to an abandonded camp that you couldn't make out in the dark.");
        whitespace = inScanner.nextLine();
        System.out.println("\nYou decide to ransack the camp to see if there's anything useful.");
        whitespace = inScanner.nextLine();

        System.out.println("\nYou find a weird-looking short sword.");
        System.out.println("It has strange runes on it.");
        whitespace = inScanner.nextLine();
        System.out.println("\nYou obtained the FALCON BLADE.");
        System.out.println("You equipped the FALCON BLADE");
        ourHero.changeWeapon("FALCON BLADE");

        whitespace = inScanner.nextLine();
        System.out.println("\nSuddenly, the runes begin to glow a blue light.");
        System.out.println("You feel more powerful.");
        whitespace = inScanner.nextLine();
        System.out.println("\nYou learnt RUNIC STRENGTH (CANTIRP)");
        ourHero.addCantrip("RUNIC STRENGTH");
        whitespace = inScanner.nextLine();
        System.out.println("You learnt FALCON SLASH (SKILL)");
        ourHero.addSkill("FALCON SLASH");

        whitespace = inScanner.nextLine();
        System.out.println("\nAfter checking the rest of the camp, you find nothing else useful.");
        whitespace = inScanner.nextLine();
        System.out.println("\nYou continue walking through the forest.");

        enterZone4();
        break;

      } else if (input == "2") {
        System.out.println("\nYou decide that it's not worth the trouble.");
        whitespace = inScanner.nextLine();
        console.setImage("forest.jpg");
        System.out.println("\nYou make a camp and rest for a bit.");
        whitespace = inScanner.nextLine();
        System.out.println("\nYou recovered 25hp.");
        ourHero.addHealth(25);

        whitespace = inScanner.nextLine();
        System.out.println("\nWhen you awaken, you hear a really annoying and high-pitched voice.");
        System.out.println("???: 'Thank GAIA you're awake! I thought you were dead!'");
        whitespace = inScanner.nextLine();
        System.out.println("\nYou look in the voice's direction.");
        System.out.println("It's coming from a small fairy.");
        whitespace = inScanner.nextLine();
        System.out.println("\nYou're not in the mood for this.");
        System.out.println("You get up and grab your DAGGER.");
        whitespace = inScanner.nextLine();
        System.out.println("\nFairy: 'W-w-what are you doing?'");
        System.out.println("Fairy: 'You're s-scaring me...");
        whitespace = inScanner.nextLine();
        System.out.println("\nYou prepare to fight.");
        whitespace = inScanner.nextLine();

        if (ourHero.defeatMonster("Fairy")) {
          System.out.println("\nThe Fairy pleads and begs for mercy.");
          System.out.println("Fairy: 'W-w-wait! I-I can offer you some m-magic perhaps?'");
          System.out.println("Fairy: 'You like s-SKILLS, right?'");
        } else {
          System.out.println("\nPFFT, YOU DIED TO A FAIRY??? HOW??");
          System.out.println("HAHAHAHA. Whatever. Sending you to blackspace.");
          enterZone0();
        }

        whitespace = inScanner.nextLine();
        System.out.println("\nYou hesitate and raise an eyebrow.");
        System.out.println("She takes this as you accepting her offer.");
        whitespace = inScanner.nextLine();
        System.out.println("\nFairy: 'A-alright, I can't t-teach you this one directly...");
        whitespace = inScanner.nextLine();
        System.out.println("\nYou raise your DAGGER.");
        whitespace = inScanner.nextLine();
        System.out.println("\nFairy: 'J-JUST HEAR ME OUT OKAY?'");
        System.out.println(
            "Fairy: 'There's an a-artifact that I know the location of, a-and you can learn the SKILL on it.'");
        whitespace = inScanner.nextLine();
        System.out.println("\nYou put down your DAGGER.");
        whitespace = inScanner.nextLine();
        System.out.println("\nFairy: 'You w-want to learn the SKILL?'");
        System.out.println("Fairy: 'G-great! We can set off now! My name is Eso by the way!'");
        System.out.println("She cheerfully flies forward.");
        System.out.println("She thinks she made a friend, forgetting what happened literally less than a minute ago.");
        System.out.println("You follow her.");
        enterZone5();
        break;
      }
    }
  }

  private void enterZone4() {
    /*
     * Graveyard
     */

    // change image
    console.setImage("graveyard.jpg");

    // describe the area/situation to the user.
    whitespace = inScanner.nextLine();
    System.out.println("\n...this is a very spooky place.");
    System.out.println("How the hell did you wander into a graveyard of all places?");
    whitespace = inScanner.nextLine();
    System.out.println("\nSuddenly, a skeleton pops out of nowhere and attacks!");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou prepare to fight.");
    whitespace = inScanner.nextLine();

    if (ourHero.defeatMonster("Skeleton")) {
      System.out.println("\nThe skeleton was reduced to a pile of bones.");
    } else {
      System.out.println("\nYou couldn't handle the spook.");
      System.out.println("Sending you to blackspace.");
      enterZone0();
    }

    whitespace = inScanner.nextLine();
    System.out.println("\nAfter killing the Skeleton, your FALCON BLADE's runes begin to glow.");
    System.out.println("You learnt WHIRLWIND (SKILL)");
    ourHero.addSkill("WHIRLWIND");
    whitespace = inScanner.nextLine();
    System.out.println("\nWell, that certainly was a strange encounter.");
    System.out.println("Skeletons are supposed to be dead.");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou wonder if someone's responsible for this.");
    whitespace = inScanner.nextLine();
    System.out.println("\nAnd just like that, you hear cackling behind you.");
    System.out.println("Of f*cking course it's a witch");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou turn to face the potion hag.");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou prepare to fight");
    whitespace = inScanner.nextLine();

    if (ourHero.defeatMonster("Witch")) {
      System.out.println("\nThe witch's potions were no match for the raw power of stabbing.");
    } else {
      System.out.println("\nYou died to a witch.");
      System.out.println("That was the last encounter of this route, and the easiest 'final boss.'");
      System.out.println("You've gotta be kidding me, how'd you lose?");
      System.out.println("Sending you to blackspace.");
      enterZone0();
    }

    whitespace = inScanner.nextLine();
    System.out.println("\nSigh. It's been a long day.");
    System.out.println("That witch corroded some of your armor and even some of your clothes.");
    System.out.println("You don't feel like adventuring anymore.");
    whitespace = inScanner.nextLine();
    System.out.println("You go back into the forest to your campsite and rest.");
    console.setImage("forest.jpg");
    gameEnd();

  }

  private void enterZone5() {
    /*
     * Mountain
     */

    // change image
    console.setImage("mountains.png");

    // describe the area/situation to the user
    whitespace = inScanner.nextLine();
    System.out.println("\nThe fairy leads you to a location in the forest where a mountain can be seen.");
    System.out.println("Eso: It's just up ahead!");
    System.out.println("She darts off.");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou have the feeling that she's going to be a liability.");
    System.out.println("But hey, magic is magic. Can't really turn that down.");
    System.out.println("It can be difficult for humans to get magic nowadays.");
    System.out.println("They can only get it through ancient artifacts, scrolls, or offerings to gods.");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou hear a scream in the distance.");
    System.out.println("oh ffs");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou calmly walk ahead to where the fairy was headed.");
    whitespace = inScanner.nextLine();
    System.out.println("\nBefore you lies a giant snake, and also Eso who is frozen in fear.");
    System.out.println("She looks back at you.");
    whitespace = inScanner.nextLine();
    System.out.println("\nEso: 'H-help. P-p-please.'");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou prepare to fight.");
    whitespace = inScanner.nextLine();

    if (ourHero.defeatMonster("Giant Snake")) {
      System.out.println("\nThe snake falls before the might of a small human with a knife.");
      System.out.println("Seriously, how did you win that?");
    } else {
      System.out.println("\nYou died to the snake.");
      System.out.println("I mean, it's a big snake. Did you expect to win?");
      System.out.println("Sending you to blackspace.");
      enterZone0();
    }

    whitespace = inScanner.nextLine();
    System.out.println("\nEso peers from behind the rock she ducked behind.");
    System.out.println("Eso: 'Wow, you actually managed to beat it.'");
    System.out.println("Eso: 'You sure are strong, do you even need the SKILL?'");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou point your DAGGER towards Eso.");
    whitespace = inScanner.nextLine();
    System.out.println("\nEso: 'Ah, f-fair enough...'");
    whitespace = inScanner.nextLine();
    System.out.println("\nEso continues leading you until you both stumble upon a ruin.");
    System.out.println("The ruin's walls are still intact, but just barely.");
    System.out.println("There's nothing left but tattered cloth, broken concrete, spider webs, and vegetation.");
    whitespace = inScanner.nextLine();
    System.out.println("\nEso: 'It's just in here'");
    System.out.println("She flies in.");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou can't help but wonder why a scroll would still be left in a ruin.");
    System.out.println("The place looks barren, and nature has already taken its toll on the structure.");
    System.out.println("You can't help but feel like the fairy is leading you into a trap.");
    whitespace = inScanner.nextLine();
    System.out.println("\n...or maybe she's just that clueless.");
    whitespace = inScanner.nextLine();
    System.out.println("\nEso: 'It's just over here!'");
    System.out.println("Eso turns away from you and stares at a wall.");
    whitespace = inScanner.nextLine();
    System.out.println("\n...she's lost it.");
    whitespace = inScanner.nextLine();
    System.out.println("\nEso starts to chant something in another language.");
    System.out.println("You guess that it has to be elven since she's a fairy.");
    whitespace = inScanner.nextLine();
    System.out.println("\nSuddenly, the wall in front of you disappears.");
    whitespace = inScanner.nextLine();
    System.out.println("\nIn front of you lies a room with multiple elven runes around a podium that begin to glow.");
    System.out.println("The runes illuminate the room and the golden sword that lies stuck in the podium");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou look at Eso.");
    System.out.println("Eso looks proud and gestures towards the sword.");
    System.out.println("You came all this way, so might as well.");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou walk up to the sword. You can feel it's magic without even touching it.");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou place both hands on the grip of the sword.");
    whitespace = inScanner.nextLine();
    System.out.println("\nWith one motion, you pull the sword out.");
    System.out.println("The runes around the podium flicker and fade.");
    whitespace = inScanner.nextLine();
    System.out.println("\nThe sword glows brighter and brighter until its blade become too bright to look at.");
    System.out.println("The sword's power flows into you suddenly.");
    whitespace = inScanner.nextLine();
    System.out.println("You feel... powerful.");

    whitespace = inScanner.nextLine();
    System.out.println("\nYou obtained the GILDED SWORD");
    ourHero.changeWeapon("GILDED SWORD");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou learnt MINOR SHIELD (CANTRIP)");
    ourHero.addCantrip("MINOR SHIELD");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou learnt GOLDEN SHIELDS (SKILL)");
    ourHero.addSkill("GOLDEN SHIELDS");

    whitespace = inScanner.nextLine();
    System.out.println("\nYou look back at Eso, who just stares.");
    whitespace = inScanner.nextLine();
    System.out.println("\nEso: 'There's no way, how did you do that?'");
    System.out.println("Eso: 'Every single human before you wasn't even able to pull out the sword.'");
    whitespace = inScanner.nextLine();
    System.out.println("\n...you weren't even expected to get anything.");
    whitespace = inScanner.nextLine();
    System.out.println("Your thoughts are interupted by a loud noise from outside the room.");
    whitespace = inScanner.nextLine();
    System.out.println("\nEso turns around and sees a giant spider staring her from 20 feet away.");
    whitespace = inScanner.nextLine();
    System.out.println("\nEso: 'Ah, it's just a spider.'");
    System.out.println("Eso: 'It's not even attacking us.'");
    System.out.println("Eso: 'It probably just noticed the mana overflow from you pulling out that sword.'");
    whitespace = inScanner.nextLine();
    System.out.println("\nEso turns to face you.");
    System.out.println("Eso: 'Hey! We're totally fine! You don't have to-'");
    whitespace = inScanner.nextLine();
    System.out.println("\nBefore she can finish her sentence, you run past her and face the spider.");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou feel like you're not in control of your body as you point your sword at the spider.");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou prepare to fight.");
    whitespace = inScanner.nextLine();

    if (ourHero.defeatMonster("Giant Spider")) {
      System.out.println("\nThe spider that was just defending itself is now dead.");
      System.out.println("When will you get over that violent fear of yours.");
    } else {
      System.out.println("\nYou died to the spider.");
      System.out.println("I mean, it's your fault for provoking it.");
      System.out.println("Sending you to blackspace.");
      enterZone0();
    }

    whitespace = inScanner.nextLine();
    System.out.println("\nWhen you come back to your senses, you notice Eso staring at you.");
    whitespace = inScanner.nextLine();
    System.out.println("\nMore accurately, she's looking at the sword in your hand which has begun glowing again.");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou feel a surge of power, stronger than the one before.");

    whitespace = inScanner.nextLine();
    System.out.println("\nYou learnt MINOR RECALL (CANTRIP)");
    ourHero.addCantrip("MINOR RECALL");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou learnt ABSORPTION (CANTIRP)");
    ourHero.addSkill("ABSORPTION");

    whitespace = inScanner.nextLine();
    System.out.println("\nDoes the wielder grow stronger the more enemies it defeats?");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou suddenly hear a loud roar, accompanied by the sound of flapping wings.");
    whitespace = inScanner.nextLine();
    System.out.println("\nA Dragon has appeared.");
    System.out.println("Eso starts freaking out.");
    whitespace = inScanner.nextLine();
    System.out.println("\nEso: 'WHY IS THERE A DRAGON HERE?'");
    System.out.println("She looks over to you.");
    System.out.println("Eso: 'Uhh, can you even beat it?'");
    whitespace = inScanner.nextLine();
    System.out.println("\nEso: 'No offense to your strength, but it's A FRICKING DRAGON.'");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou shrug and advance towards the dragon.");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou prepare to fight.");
    whitespace = inScanner.nextLine();

    if (ourHero.defeatMonster("Dragon")) {
      System.out.println("\nThe Dragon screams and hisses before its head is swiftly chopped off.");
      System.out.println("Its head topples to the floor, expressionless.");
    } else {
      System.out.println("\nYou died to the dargon.");
      System.out.println("No, I didn't misspell that.");
      System.out.println("But really, what could you do? It's a damn dragon.");
      System.out.println("Sending you to blackspace.");
      enterZone0();
    }

    whitespace = inScanner.nextLine();
    System.out.println("\nEso: 'Woah, what the heck?'");
    System.out.println("Eso: 'You... you actually managed to kill it.'");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou look back at the fairy, who is just staring in awe.");
    System.out.println("You've grown strangely attached to her.");
    whitespace = inScanner.nextLine();
    System.out.println("\nEso: 'Well, what now?");
    whitespace = inScanner.nextLine();
    System.out.println("\nThere's still that mountain that you've yet to climb.");
    System.out.println("That's where the dragon came from. Maybe it has a lair.");
    System.out.println("You start to walk towards the mountain.");
    whitespace = inScanner.nextLine();
    System.out.println("\nEso: 'Hey, wait up!'");
    System.out.println("Eso follows you to the base of the mountian before you begin to climb it.");
    System.out.println("\n[To be continued]");
    whitespace = inScanner.nextLine();
    System.out.println("Kris (shitty writer): Okay look, I'm bad at making stories haha");
    System.out.println("Kris: Maybe I'll continue this story, but it's Saturday and I have to do the next route");
    System.out.println("Kris: Don't expect too much though");
    gameEnd();

  }

  private void enterZone6() {

    /*
     * City
     */

    // change image
    console.setImage("city.png");

    whitespace = inScanner.nextLine();
    System.out.println("\nYou're on your way to the city when a slime suddenly hops out in front of you.");
    whitespace = inScanner.nextLine();
    System.out.println("\nIt seems like it wants to fight.");
    System.out.println("You draw your DAGGER.");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou prepare to fight.");
    whitespace = inScanner.nextLine();

    if (ourHero.defeatMonster("Slime")) {
      System.out.println("\nThe gelatinous blob just kinda melts?");
      System.out.println("It's hard to describe.");
    } else {
      System.out.println("\nYOU DIED TO A SLIME.");
      System.out.println("HAHAHA OH MY GOD.");
      System.out.println("I'm just going to ignore how ridiculous that is.");
      System.out.println("Sending you to blackspace.");
      enterZone0();
    }

    whitespace = inScanner.nextLine();
    System.out.println("\nAfter you kill the slime, you still feel uneasy.");
    whitespace = inScanner.nextLine();
    System.out.println("\nAlmost as if you're being watched...");
    whitespace = inScanner.nextLine();
    System.out.println("\nTHWACK! You were knocked unconscious.");

    whitespace = inScanner.nextLine();
    System.out.println("\nYou lost your DAGGER.");
    ourHero.changeWeapon("FISTS");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou lost 350 gold.");
    ourHero.setGold(0);
    whitespace = inScanner.nextLine();

    enterZone7();

    whitespace = inScanner.nextLine();
    console.setImage("city.png");
    System.out.println("\nWhen you walk out, you see a pair of two people walking towards the building.");
    System.out.println("One appears to be a swordsman and the other, a cleric");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou then realise that you're entirely soaked in blood.");
    whitespace = inScanner.nextLine();
    System.out
        .println("\nThe cleric has a worried look on her face starts to run towards you, but the swordsman stops her.");
    System.out.println("Swordsman: 'Who are you? You're not dressed like the bandits, but you're covered in blood...'");
    whitespace = inScanner.nextLine();
    System.out.println("\n. . .");
    whitespace = inScanner.nextLine();
    System.out.println("\nSwordsman: 'You mute? My apologies. Gloria, go heal him up.");
    System.out.println("Gloria: 'S-sure thing, Ace.'");
    whitespace = inScanner.nextLine();
    System.out.println("\nGloria casts a minor healing spell.");
    System.out.println("You restored 55 hp");
    ourHero.addHealth(55);
    whitespace = inScanner.nextLine();
    System.out.println("\nAce: 'There we go, is that better?'");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou certainly feel better than before.");
    System.out.println("Much better, actually.");

    whitespace = inScanner.nextLine();
    System.out.println("\nA dagger is suddenly shot your way, but you manage to dodge it.");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou learnt QUICK THINKING (CANTRIP)");
    ourHero.addCantrip("QUICK THINKING");

    whitespace = inScanner.nextLine();
    System.out.println("\nAce: 'Get down Gloria!");
    whitespace = inScanner.nextLine();
    System.out.println("\nAce commands Gloria to get behind him.");
    System.out.println("It seems as if you're surrounded by bandits.");
    whitespace = inScanner.nextLine();
    System.out.println("\nAce: 'I hate to ask you this after just healing you, but can you help?'");
    System.out.println("You nod.");
    whitespace = inScanner.nextLine();
    System.out.println("\nAce: 'Alright, I can focus on the leader while yo-");
    System.out.println("Ace's sentence is cut off by two bandits rushing him with knives.");
    whitespace = inScanner.nextLine();
    System.out.println("\nAce: 'D-dammit! Hey, change of plans. Go for the leader while I hold off the others.");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou look around for the leader and notice one bandit looking more equipped than the others.");
    System.out
        .println("It's actually to an absurd degree. You can see mulitple smoke bombs, caltrops, and daggers on her.");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou rush straight for her.");
    whitespace = inScanner.nextLine();
    System.out.println("\nBandit Leader: 'Tsk, you want to fight? I'll give you a fight!'");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou prepare to fight.");
    whitespace = inScanner.nextLine();

    if (ourHero.defeatMonster("Bandit Leader")) {
      System.out.println("\nOnce the leader falls, the rest of the bandits become uneasy.");
      System.out.println("They all flee combat.");
    } else {
      System.out.println("\nThe multiple knives were too much for your feeble body.");
      System.out.println("C'mon, I know you can do better.");
      System.out.println("Sending you to blackspace.");
      enterZone0();
    }

    whitespace = inScanner.nextLine();
    System.out.println("\nAce walks over to congratulate you.");
    whitespace = inScanner.nextLine();
    System.out.println("\nAce: 'Haha, you did splendidly! Gloria, can you heal him again?'");
    System.out.println("Gloria nods and casts a minor healing spell.");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou recovered 55 hp");
    ourHero.addHealth(55);
    whitespace = inScanner.nextLine();
    System.out.println("\nSuddenly, Ace has a very serious look on his face.");
    System.out.println("The kind you only see once or twice in a lifetime.");
    whitespace = inScanner.nextLine();
    System.out.println("\nAce commands Gloria back away.");
    whitespace = inScanner.nextLine();
    System.out.println("\nAce: 'Tell me. Where'd you get that dagger?'");
    whitespace = inScanner.nextLine();
    System.out.println("\nAce: 'Those demonic runes... those are a demon's handiwork.");
    whitespace = inScanner.nextLine();
    System.out.println("\nAce: 'As GAIA's paladin, you will go no further.'");
    System.out.println("Ace: 'Gloria, don't interfere.'");
    whitespace = inScanner.nextLine();
    System.out.println("Ace points his sword at you.");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou prepare to fight.");

    if (ourHero.defeatMonster("Ace")) {
      System.out.println("\nAce: 'Agh! A-am I... not... strong e-enough...?'");
    } else {
      System.out.println("\nYou died to Paladin Ace.");
      System.out.println("Syke would be upset you know.");
      System.out.println("Sending you to blackspace.");
      enterZone0();
    }

    whitespace = inScanner.nextLine();
    System.out.println("\nAce falls to one knee.");
    System.out.println("Ace: 'May GAIA... smite thee...'");
    whitespace = inScanner.nextLine();
    System.out.println("Ace falls to the ground, dead.");
    whitespace = inScanner.nextLine();
    System.out.println("\nGloria watched the entire thing unfold.");
    System.out.println("She falls to her knees, with tears streaming down her eyes.");
    whitespace = inScanner.nextLine();
    System.out.println("\nGloria: 'B-brother? You didn't win?'");
    whitespace = inScanner.nextLine();
    System.out.println("\nShe looks you dead in the eye.");
    whitespace = inScanner.nextLine();
    System.out.println("\nGloria: 'You... killed him...'");
    whitespace = inScanner.nextLine();
    System.out.println("\nGloria: 'I-I'll never forgive you!'");
    whitespace = inScanner.nextLine();
    System.out.println("\nShe gets up and begins to run at you.");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou prepare to fight.");

    if (ourHero.defeatMonster("Gloria")) {
      System.out.println("\nGloria coughs and chokes on her own blood. She's bleeding profusely");
    } else {
      System.out.println("\nYou died to Cleric Gloria");
      System.out.println("You let her win, didn't you? Such a hollow victory for her.");
      System.out.println("Should've ended her pain when you could.");
      System.out.println("Sigh, sending you to blackspace.");
      enterZone0();
    }

    whitespace = inScanner.nextLine();
    System.out.println("\nGloria: 'i-im sorry... brother...'");
    System.out.println("Gloria: 'i wasn't... s-strong enough...'");
    System.out.println("Gloria: 'im n-never... strong... enough...'");
    whitespace = inScanner.nextLine();
    System.out.println("\nA single tear falls from Gloria's eyes before she falls to the ground.");

    whitespace = inScanner.nextLine();
    System.out.println("\n. . .");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou hear Syke suddenly burst out laughing.");
    whitespace = inScanner.nextLine();
    System.out.println("\nSyke: 'Oh man, you just killed TWO of GAIA's devouts! Without me even asking!");
    whitespace = inScanner.nextLine();
    System.out.println("\nSyke: 'You know, this deserves a special kind of reward.'");
    System.out.println("Syke: 'How about... the very essense of demons?'");
    whitespace = inScanner.nextLine();
    System.out.println("\nBefore you can respond, you feel an overwhelmingly strong force surge through your body.");
    whitespace = inScanner.nextLine();
    System.out.println("\nEvery cell in your body feels like it's on the verge of exploding.");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou're in massive pain, and yet you don't fall to the ground.");
    whitespace = inScanner.nextLine();
    System.out.println("\nThe pain slowly subsides, and you feel VERY powerful.");
    System.out.println("Syke: 'Very powerful doesn't even begin to describe the amount of power you now have, AHAHA.'");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou learnt DEMON FORM (SKILL)");
    ourHero.addSkill("DEMON FORM");
    whitespace = inScanner.nextLine();
    System.out.println("\nSuddenly the ground shakes violently.");
    System.out.println("The ground cracks and beams of light burts through.");
    whitespace = inScanner.nextLine();
    System.out.println("\nSyke: 'HAHAHA, we managed to pull out GAIA from her idleness!'");
    System.out.println("Syke: 'I KNEW you were a good choice.");
    whitespace = inScanner.nextLine();
    System.out.println("\nGAIA rises from the ground and appears before you.");
    whitespace = inScanner.nextLine();
    System.out.println("\nGAIA: 'So you are the one who harnessed the power of demons and killed two of my devouts.'");
    System.out.println("GAIA: 'I am sorry, but your life ends here.'");
    whitespace = inScanner.nextLine();
    System.out.println("\nSyke: 'Ah crap, you're gonna need some help for this one. GAIA's crazy strong.'");
    System.out.println("You recovered 300 hp");
    ourHero.addHealth(300);
    whitespace = inScanner.nextLine();
    System.out.println("\nSyke: 'A little overheal never hurt anyone. Now go get 'em!'");

    if (ourHero.defeatMonster("GAIA")) {
      System.out.println("\nGAIA shrieks in pain. The ground fissures and it seems as if the world shakes.");
    } else {
      System.out.println("\nYou died to Lesser Goddess GAIA.");
      System.out.println("Let's be honest, you were never supposed to win that.");
      System.out.println("To blackspace you go~");
      enterZone0();
    }

    whitespace = inScanner.nextLine();
    System.out.println("\nGAIA retreats back into the ground where she rose from.");
    whitespace = inScanner.nextLine();
    System.out.println("\nSyke is silent for a while.");
    whitespace = inScanner.nextLine();
    System.out.println("\nSyke: 'Holy shit, man... You just fucking defeated GAIA.'");
    whitespace = inScanner.nextLine();
    System.out.println("\nSyke: 'ahaHAHAHAHA, YOU'RE AMAZING MAN!'");
    System.out.println("Syke: 'I CAN TELL THIS IS THE START OF SOMETHING AMAZING!'");
    System.out.println("[To be continued]");

  }

  private void enterZone7() {

    /*
     * Theif Hideout
     */

    console.setImage("bandit.jpg");

    System.out.println("\nYou wake up to find your hands are tied behind your back.");
    System.out.println("You're alive at least.");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou struggle to no avail.");
    System.out.println("The rope cuts into your wrists but doesn't budge.");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou try again.");
    whitespace = inScanner.nextLine();
    System.out.println("\nNo dice.");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou sigh, is this how you die?");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou suddenly hear a voice.");
    System.out.println("???: 'Having trouble breaking free are we?'");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou look around.");
    System.out.println("You can't locate the source of the noise.");
    whitespace = inScanner.nextLine();
    System.out.println("\n???: 'Haha, that's natural for mortals like you to not understand.'");
    System.out.println("???: 'Let me help you with that.'");

    whitespace = inScanner.nextLine();
    System.out.println("\nYou feel a surge of power flow through you.");
    System.out.println("You're able to break free from the restraints.");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou learnt POWER SURGE (SKILL)");
    ourHero.addSkill("POWER SURGE");

    whitespace = inScanner.nextLine();
    System.out.println("\n???: 'Ahaha, there we go.'");
    System.out.println("???: 'Now, let's make a deal.'");
    whitespace = inScanner.nextLine();
    System.out.println(
        "\n???: 'There's a bandit on their way to check up on you. There's also a bunch of others waiting outside.'");
    ;
    System.out
        .println("???: 'There's no way your winning that fight if you can't even break free from some rope, haha.'");
    whitespace = inScanner.nextLine();
    System.out.println("\nWhat is this voice getting at?");
    whitespace = inScanner.nextLine();
    System.out.println("\n???: 'I'm getting to that.'");
    whitespace = inScanner.nextLine();
    System.out.println("\nHe can hear that?");
    whitespace = inScanner.nextLine();
    System.out.println("\n???: 'Yeah yeah, I hear ya. Let me finish will ya?'");
    System.out.println("???: 'Anyways, here's the deal.'");
    whitespace = inScanner.nextLine();
    System.out.println("\n???: 'Name's Syke. I'm a demon. I can offer you my power in exchange for some blood.'");
    System.out.println("Syke: 'You get the gist of it, yeah?'");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou nod your head.");
    whitespace = inScanner.nextLine();
    System.out.println("\nSyke: 'Great! To prove your worth, kill the bandit that comes through to check up on ya.'");
    System.out.println("Syke: 'Not getting any more help from me until you do.'");
    whitespace = inScanner.nextLine();
    System.out.println("\nThe bandit walks into the room.");
    whitespace = inScanner.nextLine();
    System.out.println("\nBandit: 'How ya been buddy? Feelin we-'");
    System.out.println("Bandit: 'What the- How'd you break free?'");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou prepare to fight.");
    whitespace = inScanner.nextLine();

    if (ourHero.defeatMonster("Bandit")) {
      System.out.println("\nThe bandit's blood was spilt");
      System.out.println("Syke laughs in joy.");
    } else {
      System.out.println("\nSkye: 'Sigh, You disappoint me kid.'");
      System.out.println("He can talk through in a death state too.");
      System.out.println("Whatever, sending you to blackspace.");
      enterZone0();
    }

    whitespace = inScanner.nextLine();
    System.out.println("\nSyke: 'Oh man, that was fun to watch.'");
    System.out.println("Syke: 'He had a dagger, and you still beat him!'");
    System.out.println("Syke: 'Alright, a deal's a deal.'");
    whitespace = inScanner.nextLine();
    System.out.println("\nSyke: 'Here, a gift from me to you.'");
    System.out.println("A twisted dagger appears in front of you.");
    System.out.println("It has demonic runes etched into the blade.");

    whitespace = inScanner.nextLine();
    System.out.println("\nYou obtained the TWISTED DAGGER");
    ourHero.changeWeapon("TWISTED DAGGER");

    whitespace = inScanner.nextLine();

    System.out.println("\nSyke: 'The more kills you get, the stronger I'll make ya.'");
    System.out.println("Syke: 'It's a win-win!'");
    whitespace = inScanner.nextLine();
    System.out.println("\nAnother bandit walks into the room.");
    whitespace = inScanner.nextLine();
    System.out.println("\nBandit: 'Hey Grant, what's taking so lo-'");
    System.out.println("Bandit: 'HOLY SHIT'");
    whitespace = inScanner.nextLine();
    System.out.println("\nThe bandit notices the now dead Grant on the floor.");
    whitespace = inScanner.nextLine();
    System.out.println("\nBandit: 'What the fuck?? You'll die for this!'");
    whitespace = inScanner.nextLine();
    System.out.println("\nSyke: 'Ahaha, a free kill for you. You got this.'");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou prepare to fight.");
    whitespace = inScanner.nextLine();

    if (ourHero.defeatMonster("Bandit")) {
      System.out.println("\nThe bandit dies, and his blood was absorbed by the dagger.");
    } else {
      System.out.println("\nSkye: 'C'mon you handled one, you can handle two.'");
      System.out.println("Syke has a point, you know.");
      System.out.println("Whatever, sending you to blackspace.");
      enterZone0();
    }

    whitespace = inScanner.nextLine();
    System.out.println("\nThe runes on the blade glow an eerie red.");
    System.out.println("You feel power surge through you.");

    whitespace = inScanner.nextLine();
    System.out.println("\nYou learnt REAPER (SKILL)");
    ourHero.addSkill("REAPER");

    whitespace = inScanner.nextLine();
    System.out.println("\nSyke: 'How you like that? Feels good?'");
    System.out.println("Syke: 'I've got to go now. Got some other contract holders waiting for me.'");
    System.out.println("Syke: 'Just keep on killing!'");
    whitespace = inScanner.nextLine();
    System.out.println("\nAnother bandit walks in on the massacre before you.");
    whitespace = inScanner.nextLine();
    System.out.println("\nBandit: 'Tsk, you'll die for that.'");
    whitespace = inScanner.nextLine();
    System.out.println("\nBefore you can start to attack, she throws down a smoke bomb.");
    System.out.println("You run through the smoke to find that she's not on the other side.");

    whitespace = inScanner.nextLine();
    System.out.println("\nShe then stabs you in the back.");
    whitespace = inScanner.nextLine();
    System.out.println("You took 50 damage.");
    ourHero.reduceHealth(50);
    if (ourHero.getHealth() <= 0) {
      System.out.println("You were stabbed in the back and that was your demise.");
      System.out.println("It was a cheap shot anyways.");
      System.out.println("Sending you to blackspace.");
    }

    whitespace = inScanner.nextLine();
    System.out.println("\nBandit: 'For Grant and Ebel! I'll kill you!'");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou prepare to fight.");
    whitespace = inScanner.nextLine();

    if (ourHero.defeatMonster("Bandit")) {
      System.out.println("\nThe bandit bleeds.");
      System.out.println("Bandit: 'You... bastard...'");
    } else {
      System.out.println("\nWas she too much for you? Or were you holding back?");
      System.out.println("Either way, you died.");
      System.out.println("Sending you to blackspace.");
      enterZone0();
    }

    whitespace = inScanner.nextLine();
    System.out.println("\nAfter killing her, the runes on the dagger glow again.");
    whitespace = inScanner.nextLine();
    System.out.println("\nYou learned HEMOKENESIS (SKILL)");
    ourHero.addSkill("HEMOKENESIS");
    whitespace = inScanner.nextLine();
    System.out.println("\nAfterwards, you walk out of the building you were held captive in.");

  }

  private void enterZone0() {
    /*
     * Blackspace
     */

    // This is the death state, dubbed "Blackspace" by the narrator
    whitespace = inScanner.nextLine();
    System.out.println("\nBefore you lies an endless void and two buttons that hover in front of you.");
    System.out.println("They read QUIT and CONTINUE");
    whitespace = inScanner.nextLine();
    System.out.println("\nWhat will you do?");
    System.out.println("QUIT: Exit the Program");
    System.out.println("CONTINUE: Run the Program to try again");
    gameEnd();
    System.exit(0);
  }

  private void gameEnd() {
    inScanner.close();
  }
}