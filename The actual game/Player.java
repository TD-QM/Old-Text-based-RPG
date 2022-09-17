import java.util.*;

public class Player {
	// Basic variables for the Player class (name, hp, etc.)
	private String name;
	private int hp;
	private int mp;
	private int def;
	private double gold;
	private String weapon;
	private int monstersDefeated = 0;

	// Used to give players some breathing room for walls of text. Used in
	// conjunction with
	// input.nextLine(). It's explained to the player to just hit enter when text
	// seems to stop.
	private String nothing = "";

	// Variables for combat.
	private Enemy enemy;
	private boolean check;
	private int dmg;
	private int extradmg;
	private int extrapermadmg;
	private int extradef;
	private int extrapermadef;
	private int turn = 0;
	private String primary;
	private String bonus;
	private String cantrip;
	private int cantripNumber = 0;
	private String skill;
	private int skillNumber = 0;
	private int multi1;
	private int multi2;
	private int multi3;
	private int multi4;
	private int multi5;

	// Late game skills
	private boolean absorption;
	private boolean demonform;

	// Setting up dictionaries for the player's abilities (Cantrips/Skills)
	Hashtable<String, String> cantripList = new Hashtable<String, String>();
	Hashtable<String, String> skillList = new Hashtable<String, String>();

	// User input aaaaaaaaa
	Scanner input = new Scanner(System.in);

	// Player object with constructors
	public Player(String playerName, int startingHealth, int startingMana, double startingGold, int startingDef,
			String startingWeapon) {
		name = playerName;
		hp = startingHealth;
		mp = startingMana;
		def = startingDef;
		gold = startingGold;
		weapon = startingWeapon;
	}

	// Returns the player's name
	public String getName() {
		return name;
	}

	// Changes the players name. Only executes at the beginning of the game
	public void changeName(String newName) {
		name = newName;
	}

	// Returns player hp
	public int getHealth() {
		return hp;
	}

	public void addHealth(int morehp) {
		hp += morehp;
	}

	public void reduceHealth(int lesshp) {
		hp -= lesshp;
	}

	// Returns player def
	public int getDef() {
		return def;
	}

	// Returns player weapon
	public String getWeapon() {
		return weapon;
	}

	// Changes the player's weapon
	public void changeWeapon(String newWeapon) {
		weapon = newWeapon;
	}

	// Sets the new hp
	public void setHealth(int newHealth) {
		hp = newHealth;
	}

	// Returns player gold
	public double getGold() {
		return gold;
	}

	// Sets the new gold amount
	public void setGold(double newAmount) {
		gold = newAmount;
	}

	// Returns the number of monsters defeated
	public int getMonstersDefeated() {
		return monstersDefeated;
	}

	public boolean defeatMonster(String en) {
		/*
		 * The start of it all :) I don't know why I do this haha
		 */

		// Literally here just so VSCode stops bugging me about not actually using the
		// nothing String
		if (nothing.equals("")) {
		}

		// Plays the tutorial is it's the player's first time fighting an enemy
		if (monstersDefeated == 0) {
			tutorial();
		}

		// Making the enemy class using the enemy name
		enemy = new Enemy(en, def, name);

		// Resetting turn number before combat initiates.
		turn = 1;

		// Resetting permanant damage/def bonuses given by skills
		extrapermadmg = 0;
		demonform = false;
		extrapermadef = 0;

		// Resetting any debuffs set by the enemy
		enemy.resetPlayerEffect();

		// Resetting mana to full (hp doesn't restore to full at the end of combat)
		mp = 150;

		// Resets the check boolean to false
		check = false;

		// If the player dies, this breaks and returns a false statement, which triggers
		// death and a reset
		while (hp > 0) {
			// Resets player damage and extra damage/def bonuses to 0
			dmg = 0;
			extradmg = 0;
			extradef = 0;

			// Resets the absorption bonus back to false
			absorption = false;

			// Resetting enemy intent and damage
			enemy.resetIntent();
			enemy.resetDamage();

			// Enemy declares its intent before the player goes.
			enemy.declareIntent();

			// Telling the player the turn number and the hp/mp of both them and the enemy
			System.out.println("}-|Stats|-{");
			System.out.println("Turn " + turn);
			System.out.println();
			System.out.println(name + " (You)");
			System.out.println("HP: " + hp);
			System.out.println("MP: " + mp);
			System.out.println("Str: " + extrapermadmg);
			System.out.println("Def: " + extrapermadef);
			System.out.println();

			if (check) {
				System.out.println(en);
				System.out.println("HP: " + enemy.getEnHp());
				System.out.println("MP: " + enemy.getEnMp());
			} else if (!check) {
				System.out.println(en);
				System.out.println("HP: ???");
				System.out.println("MP: ???");
			}

			nothing = input.nextLine();

			// Giving the player the enemy's intent this turn
			System.out.println("\nThe enemy intends to use " + enemy.getIntent() + "\n");
			nothing = input.nextLine();

			bonusAction(); // See bonusAction() method
			nothing = input.nextLine();

			primaryAction(); // See primaryAction() method
			nothing = input.nextLine();

			// If the enemy dies, they don't use their intent and the method returns true
			enemy.takeDamage(dmg);
			if (enemy.getEnHp() <= 0) {
				monstersDefeated++;
				return true;
			}

			// Sets the player's def in the enemy function
			enemy.setPlayerDef(def, extradef, extrapermadef);

			// Formatting
			System.out.println();

			// Enemy excecutes their intent
			enemy.doIntent();

			// Enemy does damage if they do an attack
			hp -= enemy.enDamage();

			// Enemy reduces player def if they reduced def
			def -= enemy.reduceDef();

			// Checks the death status
			// If the player's hp is at or below 0, the method returns false and combat ends
			if (hp <= 0) {
				return false;
			}

			// Late game skill ABSORPTION heals the player for whatever they were
			// hit by. If the attack kills them, this doesn't take effect and they die
			if (absorption) {
				System.out.println("\nDue to ABSOPTION, you recover " + enemy.enDamage() + "hp.\n");
				hp += enemy.enDamage();
			}

			// Checks for statuses
			if (enemy.poisonCheck()) {
				System.out.println("\nYou lost 15hp from poison\n");
				hp -= 15;
			}
			if (enemy.burnCheck()) {
				System.out.println("\nYou lost 10hp and 5mp from burning\n");
				hp -= 10;
				mp -= 5;
			}
			if (enemy.bleedCheck()) {
				System.out.println("\nYou lost 30hp from bleeding\n");
				hp -= 30;
			}
			if (enemy.mpdrainCheck()) {
				System.out.println("\nYou lost 15mp from mana drain\n");
				mp -= 10; // It says you lose 15 mp but only reduces it by 10 because of the passive mana
									// regen
			} else {
				mp += 5; // Passive mana regen
			}

			// Checks to see if there's an active effect timer
			// If there is one, the timer gets reset
			// If there isn't all effects are reset
			if (enemy.timerCheck()) {
				enemy.advanceTimer();
			} else {
				enemy.resetPlayerEffect();
				enemy.setPlayerDef(def, extradef, extrapermadef);
			}

			// Another death status check. Used to see if they die to statuses
			if (hp <= 0) {
				return false;
			}

			// Checks for the late game skill demon form
			if (demonform) {
				// Breathing room lol
				nothing = input.nextLine();
				System.out.println("\nThe demons give you strength");
				System.out.println("+7 extra permanent damage\n");
				extrapermadmg += 7;
			}

			// Advances the turn number
			turn++;
			nothing = input.nextLine();

		}
		return false;
	}

	private void tutorial() {
		/*
		 * Basically just telling the player how to play the game for their first time
		 * fighting an enemy.
		 * 
		 * This system is WAY more complex than my old one with enemy intents and
		 * multi-actions being a thing.
		 * 
		 * The terms BONUS ACTION and MARTIAL CHECK were taked from DnD. The enemy
		 * intents was inspired by Slay the Spire's enemy intent system, but that one
		 * doesn't require a separate action and is instead just given to the player.
		 * 
		 * I'm overloading the player with information, so it'll be wise to split it
		 * into chunks that they can handle. As they play, they'll get more of an
		 * understanding of how combat works and what to do.
		 * 
		 * I'll add in cantrips/skills as they progress depending on their route (Ex:
		 * they encounter an NPC who teaches them how to use X skill).
		 * 
		 * All of this was typed out before actually implementing the code so this is
		 * basically setting the standard for what I intend to do. [UPDATE TO THIS] I
		 * ACTUALLY FUCKING DID IT HAHAHA
		 */
		System.out.println("It seems as if this is your first time entering combat with an enemy. Let me guide you.");
		nothing = input.nextLine();
		System.out.println("You have two resources you have to manage: HP and MP.");
		System.out.println(
				"HP is your health. When enemies attack you, you lose HP. If your HP drops to or below 0, \n   you die and have to restart from the beginning of the game. Such is the penalty for death.");
		System.out.println(
				"MP is your mana. It regenerates over time (+5 every turn). MP is used to cast SKILLS, \n   and SKILLS can't be cast without the proper amount of MP.");
		System.out.println("HP carries over between different fights while MP resets to full every fight.");
		nothing = input.nextLine();
		System.out.println("\nAt the beginning of each turn, you're given some information.");
		System.out.println("You see your current hp and mp but not the enemy's.");
		System.out.println("And you also see what the enemy intends to do.");
		System.out.println("Looking at enemy intents can help you determine what to do in that turn.");
		System.out.println("Keep in mind that it only tells you the name of the intent, not what it does.");
		nothing = input.nextLine();
		System.out.println("\nYou have two things that can be done in combat, a BONUS ACTION and a PRIMARY ACTION.");
		nothing = input.nextLine();
		System.out.println(
				"\nYour BONUS ACTION is done before you do your PRIMARY ACTION. This can be either a \n   an ENEMY CHECK or a lesser SKILL called a CANTRIP");
		System.out.println(
				"An ENEMY CHECK checks the enemy and tells you what they look like, the base stats \n   such as hp and mp, and their potential ACTIONS.");
		System.out.println(
				"A CANTRIP is a lesser SKILL that costs no mana to use. They are weaker than SKILLS \n   but are still useful");
		nothing = input.nextLine();
		System.out.println("\nYour PRIMARY ACTION can be a BASIC ATTACK or a SKILL.");
		System.out.println(
				"BASIC ATTACKS scale their damage with your current weapon. These are automatically \n   equipped to the strongest one if you either pick a new one up or your old one breaks.");
		System.out.println(
				"SKILLS are special abilities that require MP to use. They cannot be used without MP. \n   They have a variety of effects depending on the SKILL.\n");
		nothing = input.nextLine();

	}

	// Methods for bonus/primary actions. It makes the defeatMonster method a bit
	// cleaner.
	private void bonusAction() {
		// Formatting
		System.out.println();

		// Prints the user's possible choices
		System.out.println("What would you like to do for your BONUS ACTION?: ");
		System.out.println("[1] ENEMY CHECK: Check enemy stats and possible actions");
		System.out.println("[2] CANTRIP: Use a lesser SKILL");

		// While loop to make sure the player puts in the correct response
		while (true) {
			// Formatting
			System.out.println("");
			// Asks the user for input
			bonus = input.nextLine();
			if (bonus.equals("1")) {
				// Gives the player the enemy description
				System.out.println();
				System.out.println(enemy.enemyCheck());
				check = true;
				break;

			} else if (bonus.equals("2")) {
				// See useCantrip method
				useCantrip();
				break;

			} else {
				// Prompts the user to put in a new response if the current one is invalid
				System.out.println("Invalid Option, please try again: ");
			}
		}
	}

	// Adds a new cantrip
	public void addCantrip(String newCantrip) {
		cantripNumber++;
		cantripList.put(String.valueOf(cantripNumber), newCantrip);
	}

	private void useCantrip() {
		// Checks to see if they player has any cantrips. If they don't, return to the
		// bonusAction method
		// [UPDATE] This is also obsolete now haha. the player starts with a cantrip now
		if (!cantripList.isEmpty()) {
			// If the player has cantrips, the game gives them their available cantrips from
			// the cantripList
			System.out.println("\nWhich CANTRIP would you like to use?: ");

			// Gives the player the option to go back to the bonus action selections
			System.out.println("[0]: Return to BONUS ACTION selection");

			// Checks each entry of the cantripList until there are no more and returns what
			// they do.
			for (int i = 1; i < (cantripList.size() + 1); i++) {
				// Prints the available cantrips using .get, i, and .valueOf()
				System.out.print("[" + (i) + "]  " + cantripList.get(String.valueOf(i)) + ": ");
				if (cantripList.get(String.valueOf(i)).equals("SHARPEN BLADE")) {
					System.out.println("+3 dmg for the rest of combat");
				} else if (cantripList.get(String.valueOf(i)).equals("CALM DOWN")) {
					System.out.println("+10hp & 45mp");
				} else if (cantripList.get(String.valueOf(i)).equals("HOPE")) {
					System.out.println("+30hp & 15mp");
				} else if (cantripList.get(String.valueOf(i)).equals("COURAGE")) {
					System.out.println("+7 dmg this turn");
				} else if (cantripList.get(String.valueOf(i)).equals("SECOND WIND")) {
					System.out.println("+40 hp");
				} else if (cantripList.get(String.valueOf(i)).equals("RUNIC STRENGTH")) {
					System.out.println("+4 dmg this turn | +2 dmg for the rest of combat");
				} else if (cantripList.get(String.valueOf(i)).equals("MINOR SHIELD")) {
					System.out.println("+5 def this turn");
				} else if (cantripList.get(String.valueOf(i)).equals("MINOR RECALL")) {
					System.out.println("+60 mp");
				} else if (cantripList.get(String.valueOf(i)).equals("QUICK THINKING")) {
					System.out.println("+20 hp & +40 mp");
				}
			}

			// While loop is to keep asking the player for input until they input a valid
			// response
			while (true) {
				// Formatting
				System.out.println("");
				// Asks the player for input
				cantrip = input.nextLine();

				// Returns user to primary action selection
				if (cantrip.equals("0")) {
					bonusAction();
					break;

					// Executes a cantrip. If the cantrip function returns ture, then the while loop
					// breaks
					// Otherwise, it keeps going
				} else {
					if (doCantrip(cantrip)) {
						break;
					}
				}

			}
		}
	}

	private boolean doCantrip(String cantripString) {
		if (cantripList.get(cantripString).equals("SHARPEN BLADE")) {
			sharpenBlade();
			return true;

		} else if (cantripList.get(cantripString).equals("CALM DOWN")) {
			calmDown();
			return true;

		} else if (cantripList.get(cantripString).equals("HOPE")) {
			hope();
			return true;

		} else if (cantripList.get(cantripString).equals("COURAGE")) {
			courage();
			return true;

		} else if (cantripList.get(cantripString).equals("SECOND WIND")) {
			secondWind();
			return true;

		} else if (cantripList.get(cantripString).equals("RUNIC STRENGTH")) {
			runicStrength();
			return true;

		} else if (cantripList.get(cantripString).equals("MINOR SHIELD")) {
			minorShield();
			return true;

		} else if (cantripList.get(cantripString).equals("MINOR RECALL")) {
			minorRecall();
			return true;

		} else if (cantripList.get(cantripString).equals("QUICK THINKING")) {
			quickThinking();
			return true;

		} else {
			System.out.println("\nThere is no CANTRIP assigned to this slot. Please try again: ");
			return false;
		}

	}

	// I made the cantrips and skills in separate methods to make the code look
	// cleaner.
	private void calmDown() {
		System.out.println("\nYou take a deep breath to calm down");
		System.out.println("You restored 10hp and 45mp");
		hp += 10;
		mp += 45;
	}

	private void hope() {
		System.out.println("\nYou know that you can get through this place and survive");
		System.out.println("You restored 30hp and 15mp");
		hp += 30;
		mp += 15;
	}

	private void courage() {
		System.out.println("\nYou gather all of your courage");
		System.out.println("You deal 7 more damage this turn");
		extradmg = 7;
	}

	private void secondWind() {
		System.out.println("\nYou have a small surge of energy");
		System.out.println("You recovered 40 hp");
		hp += 40;
	}

	private void runicStrength() {
		System.out.println("\nYou quickly channel the runes in your blade");
		System.out.println("You deal 4 more damage this turn");
		System.out.println("You deal 2 more damage for the rest of combat");
		extradmg += 4;
		extrapermadmg += 2;
	}

	private void minorShield() {
		System.out.println("\nYou comjure a small golden shield");
		System.out.println("You deal have 5 more def this turn");
		extradef += 5;
	}

	private void minorRecall() {
		System.out.println("\nYou take a small amount of time to focus");
		System.out.println("You recovered 60mp");
		mp += 60;
	}

	private void quickThinking() {
		System.out.println("\nYou gather your wits and focus");
		System.out.println("You recovered 20 hp & 30 mp");
		hp += 20;
		mp += 30;
	}

	private void sharpenBlade() {
		System.out.println("\nYou sharpen and polish your blade");
		System.out.println("You deal 3 more damage for the rest of combat");
		extrapermadmg += 3;
	}

	// Same deal as the bonusAction() method, but with different options
	private void primaryAction() {
		// Formatting
		System.out.println();

		// Prints the user's options
		System.out.println("\nWhat would you like to do for your PRIMARY ACTION?: ");
		System.out.println("[1] BASIC ATTACK: Attack the enemy with your " + weapon);
		System.out.println("[2] SKILL: Use a SKILL");

		// While loop to ensure a correct response is used
		while (true) {

			// Formatting
			System.out.println("");
			// Asks the user's input
			primary = input.nextLine();

			// This is the basic attack function
			if (primary.equals("1")) {
				// Checks the user's current weapon and changes damage values accordingly
				// This and all damage done by the player gets supported by temp extra damage
				// given by a cantrip
				// or permanent damage given by a skill
				if (weapon.equals("DAGGER")) {
					dmg = (int) ((Math.random() * 6) + 25) + extradmg + extrapermadmg - enemy.getEnDef();
				} else if (weapon.equals("FALCON BLADE")) {
					dmg = (int) ((Math.random() * 26) + 30) + extradmg + extrapermadmg - enemy.getEnDef();
				} else if (weapon.equals("GILDED SWORD")) {
					dmg = (int) ((Math.random() * 6) + 40) + extradmg + extrapermadmg - enemy.getEnDef();
				} else if (weapon.equals("FISTS")) {
					dmg = (int) ((Math.random() * 6) + 15) + extradmg + extrapermadmg - enemy.getEnDef();
				} else if (weapon.equals("TWISTED DAGGER")) {
					dmg = (int) ((Math.random() * 6) + 35) + extradmg + extrapermadmg - enemy.getEnDef();
				}

				// Prints out damage
				System.out.println("\nYou attack with your " + weapon);
				System.out.println("You dealt " + dmg + " dmg");
				break;

				// Skill function. See useSkill() method
			} else if (primary.equals("2")) {
				useSkill();
				break;

				// A little cheating function to help testing
			} else if (primary.equals("cheat")) {
				System.out.println("\nBetter hope you're an admin");
				dmg = 9999;
				break;

				// Prompts the user for another input if the current one is invalid
			} else {
				System.out.println("\nInvalid Option, please try again:");
			}
		}
	}

	// Adds a new skill
	public void addSkill(String newSkill) {
		skillNumber++;
		skillList.put(String.valueOf(skillNumber), newSkill);
	}

	// This is the exact same as the useCantrip method above, but with skills and
	// mana drain on the options
	private void useSkill() {
		// Asks the user what they want to use
		System.out.println("\nWhich SKILL would you like to use?: ");

		// Gives the user the option to return to the primary action selection
		System.out.println("[0]: Return to PRIMARY ACTION selection");

		// Prints the available skills (and also their effects) using .get, i, and
		// .valueOf()
		for (int i = 1; i < (skillList.size() + 1); i++) {
			System.out.print("[" + (i) + "] " + skillList.get(String.valueOf(i)) + ": ");
			if (skillList.get(String.valueOf(i)).equals("MULTI-ATTACK")) {
				System.out.println("Attack three times with your " + weapon + " | Cost: 30 mp");
			} else if (skillList.get(String.valueOf(i)).equals("MINOR HEAL")) {
				System.out.println("+55 hp | Cost: 20 mp");
			} else if (skillList.get(String.valueOf(i)).equals("RECALL")) {
				System.out.println("+100 mp | Cost: 0 mp");
			} else if (skillList.get(String.valueOf(i)).equals("DEFEND")) {
				System.out.println("+20 def this turn | Cost: 0 mp");
			} else if (skillList.get(String.valueOf(i)).equals("LIGHT FLASH")) {
				System.out.println("40-50 dmg | +45hp | +3 def for the rest of combat | Cost: 35 mp");
			} else if (skillList.get(String.valueOf(i)).equals("BLACK STAR")) {
				System.out.println("50-70 dmg | +7 dmg for the rest of combat | Cost: 45 mp");
			} else if (skillList.get(String.valueOf(i)).equals("FALCON SLASH")) {
				System.out.println("60-90 dmg | Cost: 35 mp");
			} else if (skillList.get(String.valueOf(i)).equals("WHIRLWIND")) {
				System.out.println("25-35 dmg x 5 | Min/Max dmg increases by 5 every hit | Cost: 50 mp");
			} else if (skillList.get(String.valueOf(i)).equals("GOLDEN SHIELDS")) {
				System.out.println("+20 def for this turn | +3 def for the rest of combat | Cost: 45 mp");
			} else if (skillList.get(String.valueOf(i)).equals("ABSORPTION")) {
				System.out.println("Heal for damage done to you this turn | Cost: 70 mp");
			} else if (skillList.get(String.valueOf(i)).equals("ENERGY SURGE")) {
				System.out.println("+10 dmg for the rest of combat | Cost: 40 mp");
			} else if (skillList.get(String.valueOf(i)).equals("REAPER")) {
				System.out.println("60-65 dmg | Heal for dmg dealt | Cost: 85 mp");
			} else if (skillList.get(String.valueOf(i)).equals("HEMOKENESIS")) {
				System.out.println("50-55 dmg x 2 | Cost: 25hp");
			} else if (skillList.get(String.valueOf(i)).equals("DEMON FORM") && (!demonform)) {
				System.out
						.println("+7 permament dmg after every turn | Can only be used once per combat | Cost: 30 hp + 50 mp");
			} else if (skillList.get(String.valueOf(i)).equals("DEMON FORM") && (demonform)) {
				System.out.println("Already Used");
			}
		}

		while (true) {
			// Formatting
			System.out.println("");
			// Asks the player for input
			skill = input.nextLine();

			// Returns user to primary action selection
			if (skill.equals("0")) {
				primaryAction();
				break;

				// Executes a skill. If the skill function returns true, then the while loop
				// breaks
				// Otherwise, it keeps going
			} else {
				if (doSkill(skill)) {
					break;
				}
			}

		}
	}

	private boolean doSkill(String skillString) {
		if (skillList.get(skillString).equals("MULTI-ATTACK")) {
			if (mp >= 30) {
				multiAttack();
				return true;
				// Code prompts the player to use another skill if they don't have enough mp
			} else {
				System.out.println("\nNot enough MP. Please try a different SKILL or go back to primary action selection:");
				return false;
			}

		} else if (skillList.get(skillString).equals("MINOR HEAL")) {
			if (mp >= 20) {
				minorHeal();
				return true;
			} else {
				System.out.println("\nNot enough MP. Please try a different SKILL or go back to primary action selection:");
				return false;
			}

		} else if (skillList.get(skillString).equals("RECALL")) {
			if (mp >= 0) {
				recall();
				return true;
			} else {
				System.out.println("\nNot enough MP. Please try a different SKILL or go back to primary action selection:");
				return false;
			}

		} else if (skillList.get(skillString).equals("DEFEND")) {
			if (mp >= 0) {
				defend();
				return true;
			} else {
				System.out.println("\nNot enough MP. Please try a different SKILL or go back to primary action selection:");
				return false;
			}

		} else if (skillList.get(skillString).equals("LIGHT FLASH")) {
			if (mp >= 35) {
				lightFlash();
				return true;
			} else {
				System.out.println("\nNot enough MP. Please try a different SKILL or go back to primary action selection:");
				return false;
			}

		} else if (skillList.get(skillString).equals("BLACK STAR")) {
			if (mp >= 45) {
				blackStar();
				return true;
			} else {
				System.out.println("\nNot enough MP. Please try a different SKILL or go back to primary action selection:");
				return false;
			}

		} else if (skillList.get(skillString).equals("FALCON SLASH")) {
			if (mp >= 35) {
				falconSlash();
				return true;
			} else {
				System.out.println("\nNot enough MP. Please try a different SKILL or go back to primary action selection:");
				return false;
			}

		} else if (skillList.get(skillString).equals("WHIRLWIND")) {
			if (mp >= 50) {
				whirlwind();
				return true;
			} else {
				System.out.println("\nNot enough MP. Please try a different SKILL or go back to primary action selection:");
				return false;
			}

		} else if (skillList.get(skillString).equals("GOLDEN SHIELDS")) {
			if (mp >= 45) {
				goldenShields();
				return true;
			} else {
				System.out.println("\nNot enough MP. Please try a different SKILL or go back to primary action selection:");
				return false;
			}

		} else if (skillList.get(skillString).equals("ABSORPTION")) {
			if (mp >= 70) {
				absorption();
				return true;
			} else {
				System.out.println("\nNot enough MP. Please try a different SKILL or go back to primary action selection:");
				return false;
			}

		} else if (skillList.get(skillString).equals("ENERGY SURGE")) {
			if (mp >= 40) {
				energySurge();
				return true;
			} else {
				System.out.println("\nNot enough MP. Please try a different SKILL or go back to primary action selection:");
				return false;
			}

		} else if (skillList.get(skillString).equals("REAPER")) {
			if (mp >= 85) {
				reaper();
				return true;
			} else {
				System.out.println("\nNot enough MP. Please try a different SKILL or go back to primary action selection:");
				return false;
			}

		} else if (skillList.get(skillString).equals("HEMOKENESIS")) {
			if (hp >= 25) {
				hemokenesis();
				return true;
			} else {
				System.out.println("\nNot enough MP. Please try a different SKILL or go back to primary action selection:");
				return false;
			}

		} else if (skillList.get(skillString).equals("DEMON FORM")) {
			if ((hp > 30) && (mp >= 50)) {
				demonForm();
				return true;
			} else {
				System.out.println("\nNot enough MP. Please try a different SKILL or go back to primary action selection:");
				return false;
			}

		} else {
			System.out.println("\nThere is no SKILL in this slot, please try again:");
			return false;
		}
	}

	private void multiAttack() {
		// Checks the player's weapon and changes damage values accordingly
		// This is also the only skill that changes depending on the user's weapon
		if (weapon.equals("DAGGER")) {
			multi1 = (int) ((Math.random() * 6) + 25) + extradmg + extrapermadmg - enemy.getEnDef();
			multi2 = (int) ((Math.random() * 6) + 25) + extradmg + extrapermadmg - enemy.getEnDef();
			multi3 = (int) ((Math.random() * 6) + 25) + extradmg + extrapermadmg - enemy.getEnDef();
		} else if (weapon.equals("FALCON BLADE")) {
			multi1 = (int) ((Math.random() * 26) + 30) + extradmg + extrapermadmg - enemy.getEnDef();
			multi2 = (int) ((Math.random() * 26) + 30) + extradmg + extrapermadmg - enemy.getEnDef();
			multi3 = (int) ((Math.random() * 26) + 30) + extradmg + extrapermadmg - enemy.getEnDef();
		} else if (weapon.equals("GILDED SWORD")) {
			multi1 = (int) ((Math.random() * 6) + 35) + extradmg + extrapermadmg - enemy.getEnDef();
			multi2 = (int) ((Math.random() * 6) + 35) + extradmg + extrapermadmg - enemy.getEnDef();
			multi3 = (int) ((Math.random() * 6) + 35) + extradmg + extrapermadmg - enemy.getEnDef();
		} else if (weapon.equals("FISTS")) {
			multi1 = (int) ((Math.random() * 6) + 15) + extradmg + extrapermadmg - enemy.getEnDef();
			multi2 = (int) ((Math.random() * 6) + 15) + extradmg + extrapermadmg - enemy.getEnDef();
			multi3 = (int) ((Math.random() * 6) + 15) + extradmg + extrapermadmg - enemy.getEnDef();
		} else if (weapon.equals("TWISTED DAGGER")) {
			multi1 = (int) ((Math.random() * 6) + 35) + extradmg + extrapermadmg - enemy.getEnDef();
			multi2 = (int) ((Math.random() * 6) + 35) + extradmg + extrapermadmg - enemy.getEnDef();
			multi3 = (int) ((Math.random() * 6) + 35) + extradmg + extrapermadmg - enemy.getEnDef();
		}

		System.out.println("\nYou strike three times with your " + weapon);
		// Prints the user's damage individually
		System.out.println("You dealt " + multi1 + " dmg");
		System.out.println("You dealt " + multi2 + " dmg");
		System.out.println("You dealt " + multi3 + " dmg");

		dmg = multi1 + multi2 + multi3;
		mp -= 30;
	}

	private void minorHeal() {
		System.out.println("\nYou cast a minor healing spell");
		System.out.println("You recovered 55hp");
		hp += 55;
		mp -= 20;
	}

	private void recall() {
		System.out.println("\nYou take a moment to collect yourself");
		System.out.println("You recovered 100mp");
		mp += 100;
	}

	private void defend() {
		System.out.println("\nYou brace for impact");
		System.out.println("You have +20 def this turn");
		extradef += 20;
	}

	private void lightFlash() {
		dmg = (int) ((Math.random() * 11) + 40) + extradmg + extrapermadmg - enemy.getEnDef();
		System.out.println("\nYou channel a flash of light");
		System.out.println("Its energy calms you and blinds nearby enemies");
		System.out.println("You dealt " + dmg + " dmg");
		System.out.println("You recovered 45hp");
		System.out.println("You gained 3 def for the rest of combat");
		hp += 45;
		extrapermadef += 3;
		mp -= 40;
	}

	private void blackStar() {
		dmg = (int) ((Math.random() * 21) + 50) + extradmg + extrapermadmg - enemy.getEnDef();
		System.out.println("\nYou channel a black star");
		System.out.println("Its energy empowers you and lashes out at nearby enemies");
		System.out.println("You dealt " + dmg + " dmg");
		System.out.println("You do 7 more dmg for the rest of combat");
		extrapermadmg += 7;
		mp -= 45;
	}

	private void falconSlash() {
		dmg = (int) ((Math.random() * 31) + 60) + extradmg + extrapermadmg - enemy.getEnDef();
		System.out.println("\nYou channel your blade with energy and unleash a downward strike");
		System.out.println("You dealt " + dmg + " dmg");
		mp -= 35;
	}

	private void whirlwind() {
		multi1 = (int) ((Math.random() * 11) + 25) + extradmg + extrapermadmg - enemy.getEnDef();
		multi2 = (int) ((Math.random() * 11) + 30) + extradmg + extrapermadmg - enemy.getEnDef();
		multi3 = (int) ((Math.random() * 11) + 35) + extradmg + extrapermadmg - enemy.getEnDef();
		multi4 = (int) ((Math.random() * 11) + 40) + extradmg + extrapermadmg - enemy.getEnDef();
		multi5 = (int) ((Math.random() * 11) + 45) + extradmg + extrapermadmg - enemy.getEnDef();
		System.out.println("\nYou channel your blade with wind energy and release a devastating whirlwind");
		System.out.println("You dealt " + multi1 + " dmg");
		System.out.println("You dealt " + multi2 + " dmg");
		System.out.println("You dealt " + multi3 + " dmg");
		System.out.println("You dealt " + multi4 + " dmg");
		System.out.println("You dealt " + multi5 + " dmg");
		dmg = multi1 + multi2 + multi3 + multi4 + multi5;
		mp -= 50;
	}

	private void goldenShields() {
		System.out.println("\nYou conjure multiple golden shields that surround you");
		System.out.println("You gained 20 def this turn");
		System.out.println("You gained 3 def for the rest of combat");
		extradef += 20;
		extrapermadef += 3;
		mp -= 45;
	}

	private void absorption() {
		System.out.println("\nYou conjure a magical shield that heals relative to damage done to you");
		absorption = true;
		mp -= 70;
	}

	private void energySurge() {
		System.out.println("\nYou suddenly channel a burst of energy");
		System.out.println("You do 10 more damage for the rest of combat");
		extrapermadmg += 10;
	}

	private void reaper() {
		dmg = (int) ((Math.random() * 6) + 60) + extradmg + extrapermadmg - enemy.getEnDef();
		System.out.println("\nYou channel dark energy to steal your enemy's lifeforce");
		System.out.println("You dealt " + dmg + " dmg");
		System.out.println("You recovered " + dmg + " hp");
		hp += dmg;
		mp -= 85;
	}

	private void hemokenesis() {
		multi1 = (int) ((Math.random() * 6) + 50) + extradmg + extrapermadmg - enemy.getEnDef();
		multi2 = (int) ((Math.random() * 6) + 50) + extradmg + extrapermadmg - enemy.getEnDef();
		System.out.println("\nYou slice open your hand to control your blood and use it to attack");
		System.out.println("You lost 25 hp");
		System.out.println("You dealt " + multi1 + " dmg");
		System.out.println("You dealt " + multi2 + " dmg");
		dmg = multi1 + multi2;
	}

	private void demonForm() {
		System.out.println("\nYou speak to demons and ask for their strength in return for blood");
		System.out.println("You activated DEMON FORM");
		demonform = true;
		hp -= 30;
		mp -= 50;
	}

}