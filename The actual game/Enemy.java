import java.util.*;

public class Enemy {
	// Initializing variables
	private String en;
	private int enhp;
	private int enmp;
	private int endef;
	private int endmg;
	private int multi1;
	private int multi2;
	private int multi3;
	private int multi4;
	private int multi5;
	private String intent;
	private int intentNumber;
	private String enemyCheck = "";
	private Hashtable<String, String> intentList = new Hashtable<String, String>();
	private String player;
	private int playerdef;
	private int extraplayerdef;
	private int extrapermaplayerdef;
	private int timer;

	// Combat effects that cna be inflicted on the player
	private int effectchance;
	private int effectchance2;
	private int effectchance3;
	private boolean defdown = false;
	private boolean poison = false;
	private boolean burn = false;
	private boolean mpdrain = false;
	private boolean bleed = false;

	// Assigning values to the enemy. There won't be multi enemy encounters in this
	// game for the sake of simplicity. I have a week, not a month.
	// Enemies are separated into their own routes.
	public Enemy(String enName, int kplayerdef, String playerName) {
		// Setting en to the name of the Enemy
		en = enName;

		// Setting player to the player's name
		player = playerName;

		// This is to make the player's defense changable
		playerdef = kplayerdef;

		// Clearing the intent list dictionary
		intentList.clear();

		// The comments to the left of the intent describe what it does
		// You can go to the intent's respective method to see the code
		if (enName.equals("Phantom")) {
			enhp = 175;
			enmp = 70;
			endef = 5;
			intentList.put("1", "Multi-Strike"); // 30-45 dmg x 3 | 20 MP
			intentList.put("2", "Fear"); // [Target] -15 def for 3 turns | 25 MP
			intentList.put("3", "Glow"); // [Self] +35 hp & +30 mp

		} else if (enName.equals("Nightwalker")) {
			enhp = 225;
			enmp = 40;
			endef = 5;
			intentList.put("1", "Basic Strike"); // 50-60 dmg
			intentList.put("2", "Piercing Wail"); // [Target] -5 dmg on the player(+5 def to self lol) | 35 MP
			intentList.put("3", "Gather"); // [Self] +50 hp | 10 MP

		} else if (enName.equals("The Collective")) {
			enhp = 275;
			enmp = 250;
			endef = 10;
			intentList.put("1", "Synchronous Strike"); // 40-55 dmg x 4 | All four strikes deal the same amount of dmg | 30 MP
			intentList.put("2", "Emotional Disarray"); // [Self] -15 hp | [Target] -15 def for the rest of combat | 100 MP
			intentList.put("3", "Fear"); // [Target] -20 def for 3 turns | 25 MP
			intentList.put("4", "Wrap"); // [Self] +50 hp & +70 mp

		} else if (enName.equals("The Marionette")) {
			enhp = 600;
			enmp = 70;
			endef = 0;
			intentList.put("1", "Reckless Bash"); // [Self] -40 hp | [Target] 60-70 dmg & -10 def for 3 turns
			intentList.put("2", "Whirlwind"); // [Self] -65 hp | [Target] 5-25 dmg x 5 | Min/Max dmg increases every hit
			intentList.put("3", "Reaper"); // [Target] 60-75 dmg | [Self] Heal for dmg dealt | 50 MP
			intentList.put("4", "Shine"); // [Self] +35 hp & +70 MP

		} else if (enName.equals(player)) {
			enhp = 300;
			enmp = 150;
			endef = 10;
			intentList.put("1", "Basic Attack"); // 25-30 dmg
			intentList.put("2", "Multi-Attack"); // 25-30 dmg x 3 | 30 MP
			intentList.put("3", "Minor Heal"); // [Self] +55 hp | 20 MP
			intentList.put("4", "Recall"); // [Self] +70 MP
			intentList.put("5", "Light Flash"); // 40-50 dmg | +45 hp | 35 MP
			intentList.put("6", "Black Star"); // 45-65 dmg | +5 dmg for the rest of combat (-5 player def) | 45 MP

		} else if (enName.equals("Moose")) {
			enhp = 150;
			enmp = 10;
			endef = 5;
			intentList.put("1", "Ram"); // 30-40 dmg
			intentList.put("2", "Stare"); // Nothing
			intentList.put("3", "Bellow"); // Nothing

		} else if (enName.equals("Rogue Hound")) {
			enhp = 125;
			enmp = 10;
			endef = 0;
			intentList.put("1", "Bite"); // 35-40 dmg | 10% chance to inflict Bleed (3 turns)
			intentList.put("2", "Howl"); // [Self] +5 dmg for the rest of combat (-5 player def) | 10 MP
			intentList.put("3", "Scratch"); // 40-45 dmg

		} else if (enName.equals("Skeleton")) {
			enhp = 150;
			enmp = 50;
			endef = 5;
			intentList.put("1", "Bone Bash"); // 35-40 dmg | 10% chance to bring def down (3 turns)
			intentList.put("2", "Clatter"); // [Self] +20 hp | 10 MP
			intentList.put("3", "Scratch"); // 40-45 dmg

		} else if (enName.equals("Witch")) {
			enhp = 175;
			enmp = 200;
			endef = 0;
			intentList.put("1", "Cackle"); // [Self] +7 dmg for the rest of combat | 20 MP
			intentList.put("2", "Alchemize"); // Do one of three effects | Deal 50-60 dmg | Heal for 50 hp | Inflict Burn | 70
																				// MP
			intentList.put("3", "Magic Shot"); // 45-50 dmg | 40 MP
			intentList.put("4", "Corrode"); // [Target] -5 def for 3 turns | 10 MP
			intentList.put("5", "Mana Potion"); // [Self] +100 MP

		} else if (enName.equals("Fairy")) {
			enhp = 125;
			enmp = 120;
			endef = 0;
			intentList.put("1", "Light Burst"); // 30-40 dmg | 10 MP
			intentList.put("2", "Mana Drain"); // Inflicts MPDrain (5 turns) | 70 MP
			intentList.put("3", "Recall"); // [Self] +70 MP

		} else if (enName.equals("Giant Snake")) {
			enhp = 200;
			enmp = 30;
			endef = 0;
			intentList.put("1", "Fang Strike"); // 35-45 dmg
			intentList.put("2", "Venom Strike"); // 30-40 dmg | 30% chance to inflict Poison (5 turns)
			intentList.put("3", "Coil"); // +5 def for the rest of combat

		} else if (enName.equals("Giant Spider")) {
			enhp = 225;
			enmp = 40;
			endef = 5;
			intentList.put("1", "Spider Strike"); // 40-45 dmg
			intentList.put("2", "Spin Web"); // [Self] +50 hp & +5 def for the rest of combat| 10 MP
			intentList.put("3", "Venom Strike"); // 30-40 dmg | 30% chance to inflict Poison (5 turns)

		} else if (enName.equals("Dragon")) {
			enhp = 350;
			enmp = 150;
			endef = 10;
			intentList.put("1", "Claw Strike"); // 50-55 dmg
			intentList.put("2", "Fire Breathing"); // 60-65 dmg | 20% chance to inflict Burn (3 turns) | 30 MP
			intentList.put("3", "Tail Swipe"); // 40-65 dmg
			intentList.put("4", "Mana Drain"); // Inflicts MPDrain (5 turns) | 70 MP
			intentList.put("5", "Soar"); // [Self] +70 hp & +70 mp

		} else if (enName.equals("Slime")) {
			enhp = 100;
			enmp = 100;
			endef = 0;
			intentList.put("1", "Corrode"); // [Target] -5 def for 3 turns | 10 MP
			intentList.put("2", "Slime Strike"); // 25-30 dmg

		} else if (enName.equals("Bandit")) {
			enhp = 150;
			enmp = 75;
			endef = 5;
			intentList.put("1", "Basic Attack"); // 25-30 dmg
			intentList.put("2", "Multi-Attack"); // 25-30 dmg x 3 | 30 MP
			intentList.put("3", "Slice"); // 35-40 dmg | 30% chance to inflict Bleed (3 turns) | 20 MP

		} else if (enName.equals("Bandit Leader")) {
			enhp = 250;
			enmp = 125;
			endef = 10;
			intentList.put("1", "Blade Dance"); // 30-35 dmg x 3 | Min/Max dmg increases with each attack | 10% chance to
																					// inflict Bleed per hit (2 turns) | 30 MP
			intentList.put("2", "Spot Weakness"); // [Self] +7 dmg for the rest of combat | 50 MP
			intentList.put("3", "Smoke Bomb"); // [Self] +5 def for the rest of combat | 40 MP
			intentList.put("4", "Setup"); // [Self] +125 MP

		} else if (enName.equals("Ace")) {
			enhp = 400;
			enmp = 150;
			endef = 15;
			intentList.put("1", "Sword Strike"); // 45-50 dmg
			intentList.put("2", "Light Flash"); // 40-50 dmg | +45 hp | 35 MP
			intentList.put("3", "Holy Strike"); // 65-70 dmg | 30 MP
			intentList.put("4", "Advanced Heal"); // [Self] +80 hp | 25 MP
			intentList.put("5", "Inspiration"); // [Self] +100 MP

		} else if (enName.equals("Gloria")) {
			enhp = 100;
			enmp = 150;
			endef = 0;
			intentList.put("1", "Basic Attack"); // 25-30 dmg
			intentList.put("2", "Minor Heal"); // [Self] +55 hp | 20 MP
			intentList.put("3", "Recall"); // [Self] +70 MP

		} else if (enName.equals("GAIA")) {
			enhp = 650;
			enmp = 300;
			endef = 20;
			intentList.put("1", "Holy Strike"); // 65-70 dmg | 30 MP
			intentList.put("2", "Major Heal"); // [Self] +150 hp | 40 MP
			intentList.put("3", "Mana Drain"); // Inflicts MPDrain | 70 MP
			intentList.put("4", "Equilibrium"); // Deal damage equal to (hp/5) | Take (hp/15) dmg | 130 MP | Cannot be cast if
																					// hp is less than 150
			intentList.put("5", "Mana Sink"); // Deal damage equal to total ((mp/3) + 75) | All MP | Can only be cast if mp is
																				// greater than or equal to 200
			intentList.put("6", "Divine Inspiration"); // [Self] Set MP to 300 | Cannot be used if at max mp

		}

	}

	// Descriptions of the enemies

	// When you're in the nightmare route, it's actually you who's inspecting the
	// enemies.
	// You're much more analytical, but emotionally weak.

	// Outside the nightmare, it's the "other you" that you fight in the nightmare
	// route who's the narrator.
	// They're more joking in the forest.
	// They start demanding more blood in the city route.

	public String enemyCheck() {
		if (en.equals("Phantom")) {
			enemyCheck = "A creature that floats in the air. \nIt has two 'wings' made of scythes that it uses to strike. \nIt also has a long tail with a strange red orb at the end of it. \nMax HP: 150 \nMax MP: 70 \nDef: 5 \nThis enemy can use: \n'Multi-Strike' \n'Fear' \n'Glow'";
			return enemyCheck;

		} else if (en.equals("Nightwalker")) {
			enemyCheck = "A strange humanoid creature that limps across the ground. \nAll its body parts are covered in some black mist. \nIt's hard to determine what exactly it looks like. \nMax HP: 225 \nMax MP: 40 \nDef: 5 \nThis enemy can use: \n'Basic Strike' \n'Piercing Wail' \n'Gather'";
			return enemyCheck;

		} else if (en.equals("The Collective")) {
			enemyCheck = "It looks like some strange creature with four arms, legs, and heads. \nThe heads seem to work as a collective. \nThe forearms are replaced with blades that the creature uses to lash out. \nMax HP: 350 \nMax MP: 250 \nDef: 10 \nThis enemy can use: \n'Synchronous Strike' \n'Emotional Disarray' \n'Fear' \n'Calm Down'";
			return enemyCheck;

		} else if (en.equals("The Marionette")) {
			enemyCheck = "A thin humanoid creature about 7ft tall. \nIt has a hunchback and seems to not be in control. \nIt wildly bashes about, daling damage to itself in the process. \nSurrounding its 'fingers' are very long strings with an orb attatched to them. \nMax HP: 600 \nMax MP: 70 \nDef: 0 \nThis enemy can use: \n'Reckless Bash' \n'Whirlwind' \n'Reaper' \n'Shine'";
			return enemyCheck;

		} else if (en.equals(player)) {
			enemyCheck = "It's you, hands covered in blood and grinning from ear to ear. \nIt's the first time you're the one confronting them \nMax HP: 300 \nMax MP: 150 \nDef: 20 \nThis enemy can use: \n'Basic Attack' \n'Multi-Attack' \n'Minor Heal' \n'Recall' \n'Light Flash' \n'Black Star' ";
			return enemyCheck;

		} else if (en.equals("Moose")) {
			enemyCheck = "Yep, it's a moose. \nIt has antlers. \nMax HP: 100 \nMax MP: 10 \nDef: 0 \nThis enemy can use: \n'Ram' \n'Stare' \n'Bellow'";
			return enemyCheck;

		} else if (en.equals("Rogue Hound")) {
			enemyCheck = "Think of a dog, but it's wild and wants to kill you. \nMax HP: 125 \nMax MP: 20 \nDef: 0 \nThis enemy can use: \n'Bite' \n'Howl' \n'Scratch'";
			return enemyCheck;

		} else if (en.equals("Skeleton")) {
			enemyCheck = "Spooky Scare Skeleton. \nI'm sorry. \nMax HP: 150 \nMax MP: 50 \nDef: 5 \nThis enemy can use: \n'Bone Bash' \n'Clatter' \n'Scratch'";
			return enemyCheck;

		} else if (en.equals("Witch")) {
			enemyCheck = "A weird looking witch riding a broom \nI wonder if she sweeps with it. \nMax HP: 175 \nMax MP: 200 \nDef: 0 \nThis enemy can use: \n'Cackle' \n'Alchemize' \n'Magic Shot' \n'Corrode'";
			return enemyCheck;

		} else if (en.equals("Fairy")) {
			enemyCheck = "Some weird glowing thing. \nIt sounds annoying to be honest. \nMax HP: 125 \nMax MP: 120 \nDef: 0 \nThis enemy can use: \n'Light Burst' \n'Mana Drain' \n'Recall'";
			return enemyCheck;

		} else if (en.equals("Giant Snake")) {
			enemyCheck = "snek but big. \nMax HP: 200 \nMax MP: 30 \nDef: 0 \nThis enemy can use: \n'Fang Strike' \n'Venom Strike' \n'Coil'";
			return enemyCheck;

		} else if (en.equals("Giant Spider")) {
			enemyCheck = "IT'S SPIDERMAN OH MY GOD. \nOn a serious note, you're genuinely scared of it, so you do less damage. \nMax HP: 200 \nMax MP: 40 \nDef: 0 \nThis enemy can use: \n'Spider Strike' \n'Spin Web' \n'Venom Strike'";
			return enemyCheck;

		} else if (en.equals("Dragon")) {
			enemyCheck = "It's the cliche dragon creature thing that's in every rpg ever. \nMax HP: 350 \nMax MP: 150 \nDef: 10 \nThis enemy can use: \n'Claw Strike' \n'Fire Breathing' \n'Tail Swipe' \n'Mana Drain' \n'Soar' ";
			return enemyCheck;

		} else if (en.equals("Slime")) {
			enemyCheck = "Basic slime with an acid body. \nLooks like gelatin. \nMax HP: 100 \nMax MP: 100 \nDef: 0 \nThis enemy can use: \n'Slime Strike' \n'Corrode'";
			return enemyCheck;

		} else if (en.equals("Bandit")) {
			enemyCheck = "Basic robber. \nThey shouldn't be too much of a hassle. \nMax HP: 150 \nMax MP: 75 \nDef: 5 \nThis enemy can use: \n'Basic Attack' \n'Multi-Attack' \n'Slice'";
			return enemyCheck;

		} else if (en.equals("Bandit Leader")) {
			enemyCheck = "Leader of the robbers. \nThey took your stuff, so you're taking their life. \nMax HP: 250 \nMax MP: 125 \nDef: 10 \nThis enemy can use: \n'Blade Dance' \n'Spot Weakness' \n'Smoke Bomb' \n'Setup'";
			return enemyCheck;

		} else if (en.equals("ACE")) {
			enemyCheck = "Paladin under the guidance of GAIA. \nOnce friend now turned enemy. \nTraitors don't deserve to live. \nMax HP: 300 \nMax MP: 150 \nDef: 0 \nThis enemy can use: \n'Sword Strike' \n'Light Flash' \n'Holy Strike' \n'Advanced Heal' \n'Inspiration'";
			return enemyCheck;

		} else if (en.equals("GLORIA")) {
			enemyCheck = "A weak cleric under GAIA. \nHer brother is dead, and she's enacting revenge to the best of her ability. \nMax HP: 100 \nMax MP: 150 \nDef: 0 \nThis enemy can use: \n'Basic Attack' \n'Minor Heal' \n'Recall'";
			return enemyCheck;

		} else if (en.equals("GAIA")) {
			enemyCheck = "A lesser god that commands light and healing. \nShe doesn't like that you killed two of her devouts. \nKill the god and force her to learn her place. \nMax HP: 550 \nMax MP: 300 \nDef: 20 \nThis enemy can use: \n'Holy Strike' \n'Major Heal' \n'Mana Drain' \n'Equilibrium' \n'Mana Sink' \n'Divine Inspiration'";
			return enemyCheck;

		} else {
			return "No Information";
		}

	}

	// Declaring intent for the enemy using intentList and Math.random
	public void declareIntent() {
		intentNumber = ((int) (Math.random() * intentList.size())) + 1;
		intent = intentList.get(String.valueOf(intentNumber));

		// This while loop is used to keep cycling through the intents if the enemy
		// doesn't have sufficient hp or mp
		// This could also be used for AI shenanigans, but I don't feel like it haha
		while (true) {
			if (intent.equals("Fear")) {
				if (enmp >= 25) {
					break;
				} else {
					intentNumber = ((int) (Math.random() * intentList.size())) + 1;
					intent = intentList.get(String.valueOf(intentNumber));
				}
			} else if (intent.equals("Piercing Wail")) {
				if (enmp >= 35) {
					break;
				} else {
					intentNumber = ((int) (Math.random() * intentList.size())) + 1;
					intent = intentList.get(String.valueOf(intentNumber));
				}
			} else if (intent.equals("Gather")) {
				if (enmp >= 10) {
					break;
				} else {
					intentNumber = ((int) (Math.random() * intentList.size())) + 1;
					intent = intentList.get(String.valueOf(intentNumber));
				}
			} else if (intent.equals("Emotional Disarray")) {
				if ((enmp >= 100) && (enhp > 15)) {
					break;
				} else {
					intentNumber = ((int) (Math.random() * intentList.size())) + 1;
					intent = intentList.get(String.valueOf(intentNumber));
				}
			} else if (intent.equals("Reckless Bash")) {
				if (enhp > 40) {
					break;
				} else {
					intentNumber = ((int) (Math.random() * intentList.size())) + 1;
					intent = intentList.get(String.valueOf(intentNumber));
				}
			} else if (intent.equals("Whirlwind")) {
				if (enhp > 65) {
					break;
				} else {
					intentNumber = ((int) (Math.random() * intentList.size())) + 1;
					intent = intentList.get(String.valueOf(intentNumber));
				}
			} else if (intent.equals("Reaper")) {
				if (enmp >= 100) {
					break;
				} else {
					intentNumber = ((int) (Math.random() * intentList.size())) + 1;
					intent = intentList.get(String.valueOf(intentNumber));
				}
			} else if (intent.equals("Multi-Attack")) {
				if (enmp >= 30) {
					break;
				} else {
					intentNumber = ((int) (Math.random() * intentList.size())) + 1;
					intent = intentList.get(String.valueOf(intentNumber));
				}
			} else if (intent.equals("Minor Heal")) {
				if (enmp >= 20) {
					break;
				} else {
					intentNumber = ((int) (Math.random() * intentList.size())) + 1;
					intent = intentList.get(String.valueOf(intentNumber));
				}
			} else if (intent.equals("Light Flash")) {
				if (enmp >= 35) {
					break;
				} else {
					intentNumber = ((int) (Math.random() * intentList.size())) + 1;
					intent = intentList.get(String.valueOf(intentNumber));
				}
			} else if (intent.equals("Black Star")) {
				if (enmp >= 45) {
					break;
				} else {
					intentNumber = ((int) (Math.random() * intentList.size())) + 1;
					intent = intentList.get(String.valueOf(intentNumber));
				}
			} else if (intent.equals("Howl")) {
				if (enmp >= 10) {
					break;
				} else {
					intentNumber = ((int) (Math.random() * intentList.size())) + 1;
					intent = intentList.get(String.valueOf(intentNumber));
				}
			} else if (intent.equals("Clatter")) {
				if (enmp >= 10) {
					break;
				} else {
					intentNumber = ((int) (Math.random() * intentList.size())) + 1;
					intent = intentList.get(String.valueOf(intentNumber));
				}
			} else if (intent.equals("Alchemize")) {
				if (enmp >= 70) {
					break;
				} else {
					intentNumber = ((int) (Math.random() * intentList.size())) + 1;
					intent = intentList.get(String.valueOf(intentNumber));
				}
			} else if (intent.equals("Magic Shot")) {
				if (enmp >= 40) {
					break;
				} else {
					intentNumber = ((int) (Math.random() * intentList.size())) + 1;
					intent = intentList.get(String.valueOf(intentNumber));
				}
			} else if (intent.equals("Light Burst")) {
				if (enmp >= 10) {
					break;
				} else {
					intentNumber = ((int) (Math.random() * intentList.size())) + 1;
					intent = intentList.get(String.valueOf(intentNumber));
				}
			} else if (intent.equals("Spin Web")) {
				if (enmp >= 10) {
					break;
				} else {
					intentNumber = ((int) (Math.random() * intentList.size())) + 1;
					intent = intentList.get(String.valueOf(intentNumber));
				}
			} else if (intent.equals("Fire Breathing")) {
				if (enmp >= 30) {
					break;
				} else {
					intentNumber = ((int) (Math.random() * intentList.size())) + 1;
					intent = intentList.get(String.valueOf(intentNumber));
				}
			} else if (intent.equals("Mana Drain")) {
				if (enmp >= 70 && !mpdrain) {
					break;
				} else {
					intentNumber = ((int) (Math.random() * intentList.size())) + 1;
					intent = intentList.get(String.valueOf(intentNumber));
				}
			} else if (intent.equals("Corrode")) {
				if (enmp >= 10) {
					break;
				} else {
					intentNumber = ((int) (Math.random() * intentList.size())) + 1;
					intent = intentList.get(String.valueOf(intentNumber));
				}
			} else if (intent.equals("Slice")) {
				if (enmp >= 20) {
					break;
				} else {
					intentNumber = ((int) (Math.random() * intentList.size())) + 1;
					intent = intentList.get(String.valueOf(intentNumber));
				}
			} else if (intent.equals("Blade Dance")) {
				if (enmp >= 30) {
					break;
				} else {
					intentNumber = ((int) (Math.random() * intentList.size())) + 1;
					intent = intentList.get(String.valueOf(intentNumber));
				}
			} else if (intent.equals("Spot Weakness")) {
				if (enmp >= 50) {
					break;
				} else {
					intentNumber = ((int) (Math.random() * intentList.size())) + 1;
					intent = intentList.get(String.valueOf(intentNumber));
				}
			} else if (intent.equals("Smoke Bomb")) {
				if (enmp >= 40) {
					break;
				} else {
					intentNumber = ((int) (Math.random() * intentList.size())) + 1;
					intent = intentList.get(String.valueOf(intentNumber));
				}
			} else if (intent.equals("Holy Strike")) {
				if (enmp >= 30) {
					break;
				} else {
					intentNumber = ((int) (Math.random() * intentList.size())) + 1;
					intent = intentList.get(String.valueOf(intentNumber));
				}
			} else if (intent.equals("Advanced Heal")) {
				if (enmp >= 30) {
					break;
				} else {
					intentNumber = ((int) (Math.random() * intentList.size())) + 1;
					intent = intentList.get(String.valueOf(intentNumber));
				}
			} else if (intent.equals("Major Heal")) {
				if (enmp >= 40) {
					break;
				} else {
					intentNumber = ((int) (Math.random() * intentList.size())) + 1;
					intent = intentList.get(String.valueOf(intentNumber));
				}
			} else if (intent.equals("Equilibrium")) {
				if ((enmp >= 150) && (enhp >= 225)) {
					break;
				} else {
					intentNumber = ((int) (Math.random() * intentList.size())) + 1;
					intent = intentList.get(String.valueOf(intentNumber));
				}
			} else if (intent.equals("Mana Sink")) {
				if (enmp >= 150) {
					break;
				} else {
					intentNumber = ((int) (Math.random() * intentList.size())) + 1;
					intent = intentList.get(String.valueOf(intentNumber));
				}
			} else if (intent.equals("Divine Inspiration")) {
				if (enmp != 300) {
					break;
				} else {
					intentNumber = ((int) (Math.random() * intentList.size())) + 1;
					intent = intentList.get(String.valueOf(intentNumber));
				}
			} else {
				break;
			}
		}
	}

	// Excecutes the intent that was selected by the enemy depending on its name
	public void doIntent() {
		// Nightmare route intents
		if (intent.equals("Multi-Strike")) {
			multiStrike();
		} else if (intent.equals("Fear")) {
			fear();
		} else if (intent.equals("Glow")) {
			glow();
		} else if (intent.equals("Basic Strike")) {
			basicStrike();
		} else if (intent.equals("Piercing Wail")) {
			piercingWail();
		} else if (intent.equals("Gather")) {
			gather();
		} else if (intent.equals("Synchronous Strike")) {
			synchronousStrike();
		} else if (intent.equals("Emotional Disarray")) {
			emotionalDisarray();
		} else if (intent.equals("Wrap")) {
			wrap();
		} else if (intent.equals("Reckless Bash")) {
			recklessBash();
		} else if (intent.equals("Whirlwind")) {
			whirlwind();
		} else if (intent.equals("Reaper")) {
			reaper();
		} else if (intent.equals("Shine")) {
			shine();
		} else if (intent.equals("Basic Attack")) {
			basicAttack();
		} else if (intent.equals("Multi-Attack")) {
			multiAttack();
		} else if (intent.equals("Minor Heal")) {
			minorHeal();
		} else if (intent.equals("Recall")) {
			recall();
		} else if (intent.equals("Light Flash")) {
			lightFlash();
		} else if (intent.equals("Black Star")) {
			blackStar();
		} else if (intent.equals("Ram")) {
			ram();
		} else if (intent.equals("Stare")) {
			stare();
		} else if (intent.equals("Bellow")) {
			bellow();
		} else if (intent.equals("Bite")) {
			bite();
		} else if (intent.equals("Howl")) {
			howl();
		} else if (intent.equals("Scratch")) {
			scratch();
		} else if (intent.equals("Bone Bash")) {
			boneBash();
		} else if (intent.equals("Clatter")) {
			clatter();
		} else if (intent.equals("Cackle")) {
			cackle();
		} else if (intent.equals("Alchemize")) {
			alchemize();
		} else if (intent.equals("Magic Shot")) {
			magicShot();
		} else if (intent.equals("Mana Potion")) {
			manaPotion();
		} else if (intent.equals("Light Burst")) {
			lightBurst();
		} else if (intent.equals("Fang Strike")) {
			fangStrike();
		} else if (intent.equals("Coil")) {
			coil();
		} else if (intent.equals("Spider Strike")) {
			spiderStrike();
		} else if (intent.equals("Spin Web")) {
			spinWeb();
		} else if (intent.equals("Venom Strike")) {
			venomStrike();
		} else if (intent.equals("Claw Strike")) {
			clawStrike();
		} else if (intent.equals("Fire Breathing")) {
			fireBreathing();
		} else if (intent.equals("Tail Swipe")) {
			tailSwipe();
		} else if (intent.equals("Mana Drain")) {
			manaDrain();
		} else if (intent.equals("Soar")) {
			soar();
		} else if (intent.equals("Corrode")) {
			corrode();
		} else if (intent.equals("Slime Strike")) {
			slimeStrike();
		} else if (intent.equals("Slice")) {
			slice();
		} else if (intent.equals("Blade Dance")) {
			bladeDance();
		} else if (intent.equals("Spot Weakness")) {
			spotWeakness();
		} else if (intent.equals("Smoke Bomb")) {
			smokeBomb();
		} else if (intent.equals("Setup")) {
			setup();
		} else if (intent.equals("Sword Strike")) {
			swordStrike();
		} else if (intent.equals("Holy Strike")) {
			holyStrike();
		} else if (intent.equals("Advanced Heal")) {
			advancedHeal();
		} else if (intent.equals("Major Heal")) {
			majorHeal();
		} else if (intent.equals("Inspiration")) {
			inspiration();
		} else if (intent.equals("Equilibrium")) {
			equilibrium();
		} else if (intent.equals("Mana Sink")) {
			manaSink();
		} else if (intent.equals("Divine Inspiration")) {
			divineInspiration();
		}
		System.out.println("");
	}

	// I made the enemy intents into separate methods to make the code look cleaner.
	private void multiStrike() {
		multi1 = ((int) (Math.random() * 6) + 25) - playerdef;
		multi2 = ((int) (Math.random() * 6) + 25) - playerdef;
		multi3 = ((int) (Math.random() * 6) + 25) - playerdef;
		System.out.println("The " + en + " strikes three times");
		System.out.println(en + " dealt " + multi1 + " dmg");
		System.out.println(en + " dealt " + multi2 + " dmg");
		System.out.println(en + " dealt " + multi3 + " dmg");
		endmg = multi1 + multi2 + multi3;
	}

	private void fear() {
		System.out.println("The " + en + " strikes fear into your very soul");
		if (!defdown) {
			System.out.println("You have -7 def for 3 turns");
			playerdef -= 7;
			timer += 3;
		} else {
			System.out.println("The def down lasts longer");
			timer += 3;
		}
	}

	private void glow() {
		System.out.println("The orb on the Phantom's tail glows an eerie red light");
		System.out.println("The Phantom recovered 35hp and 30mp");
		enhp += 35;
		enmp += 30;
	}

	private void basicStrike() {
		endmg = ((int) (Math.random() * 11) + 50) - playerdef;
		System.out.println("The " + en + " strikes you");
		System.out.println(en + " dealt " + endmg + " dmg");
	}

	private void piercingWail() {
		System.out.println("The " + en + " lets out an ear-piercing scream");
		System.out.println("You do 5 less dmg for the rest of combat");
		endef += 5;
	}

	private void gather() {
		System.out.println("The " + en + " gathers the energy around it");
		System.out.println("The " + en + " recovered 50hp");
		enhp += 50;
		enmp -= 10;
	}

	private void synchronousStrike() {
		endmg = ((int) (Math.random() * 11) + 35) - playerdef;
		System.out.println(en + " strikes four times in unison");
		System.out.println(en + " dealt " + endmg + " dmg");
		System.out.println(en + " dealt " + endmg + " dmg");
		System.out.println(en + " dealt " + endmg + " dmg");
		System.out.println(en + " dealt " + endmg + " dmg");
		endmg *= 4;
	}

	private void emotionalDisarray() {
		System.out.println(en + " lets out a psychic wave in anguish");
		System.out.println("You're suddenly angry, sad, and afraid");
		System.out.println(en + " took 15 dmg");
		System.out.println("You have -10 def for the rest of combat");
		playerdef -= 10;
		enhp -= 15;
		enmp -= 100;
	}

	private void wrap() {
		System.out.println(en + " wraps itself with it's arms to confort itself");
		System.out.println(en + " recovered 35hp and 70mp");
		enhp += 35;
		enmp += 70;
	}

	private void recklessBash() {
		endmg = ((int) (Math.random() * 11) + 60) - playerdef;
		System.out.println(en + " rams its head into you at an astonishing speed");
		System.out.println(en + " dealt " + endmg + " dmg");
		System.out.println(en + " took 40 dmg");
		if (!defdown) {
			System.out.println("You have -5 def for 3 turns");
			playerdef -= 5;
			timer += 3;
		} else {
			System.out.println("The def down lasts longer");
			timer += 3;
		}
		enhp -= 40;
	}

	private void whirlwind() {
		multi1 = ((int) (Math.random() * 21) + 5) - playerdef;
		multi2 = ((int) (Math.random() * 21) + 10) - playerdef;
		multi3 = ((int) (Math.random() * 21) + 15) - playerdef;
		multi4 = ((int) (Math.random() * 21) + 20) - playerdef;
		multi5 = ((int) (Math.random() * 21) + 25) - playerdef;
		System.out.println(en + " spins rapidly, letting its arms and threads hit everything");
		System.out.println(en + " does " + multi1 + " dmg");
		System.out.println(en + " does " + multi2 + " dmg");
		System.out.println(en + " does " + multi3 + " dmg");
		System.out.println(en + " does " + multi4 + " dmg");
		System.out.println(en + " does " + multi5 + " dmg");
		System.out.println(en + " took 65 dmg");
		endmg = multi1 + multi2 + multi3 + multi4 + multi5;
		enhp -= 65;
	}

	private void reaper() {
		endmg = ((int) (Math.random() * 16) + 60) - playerdef;
		System.out.println(en + " absorbs your life force");
		System.out.println("You took " + endmg + " dmg");
		System.out.println(en + " recovered " + endmg + " hp");
		enhp += endmg;
		enmp -= 100;
	}

	private void shine() {
		System.out.println("The orbs on " + en + "'s threads begin to shine an eerie red");
		System.out.println(en + " recovered 35hp and 70mp");
		enhp += 35;
		enmp += 70;
	}

	private void basicAttack() {
		endmg = ((int) (Math.random() * 6) + 25) - playerdef;
		System.out.println(en + " strikes with thier DAGGER");
		System.out.println(en + " does " + endmg);
	}

	private void multiAttack() {
		multi1 = ((int) (Math.random() * 6) + 25) - playerdef;
		multi2 = ((int) (Math.random() * 6) + 25) - playerdef;
		multi3 = ((int) (Math.random() * 6) + 25) - playerdef;
		System.out.println("The " + en + " strikes three times with their DAGGER");
		System.out.println(en + " does " + multi1 + " dmg");
		System.out.println(en + " does " + multi2 + " dmg");
		System.out.println(en + " does " + multi3 + " dmg");
		endmg = multi1 + multi2 + multi3;
	}

	private void minorHeal() {
		System.out.println(en + " used magic to heal minor wounds");
		System.out.println(en + " recovered 55hp");
		enhp += 55;
		enmp -= 20;
	}

	private void recall() {
		System.out.println(en + " takes a moment to collect yourself");
		System.out.println(en + " recovered 70mp");
		enmp += 70;
	}

	private void lightFlash() {
		endmg = (int) ((Math.random() * 11) + 40) - playerdef;
		System.out.println(en + " channels a flash of light");
		System.out.println("Its energy calms them and blinds you");
		System.out.println(en + " dealt " + endmg + " dmg");
		System.out.println(en + " recovered 45hp");
		System.out.println(en + " gained 3 def for the rest of combat");
		enhp += 45;
		endef += 3;
		enmp -= 40;

	}

	private void blackStar() {
		endmg = (int) ((Math.random() * 21) + 50) - playerdef;
		System.out.println(en + " channels a black star");
		System.out.println("Its energy empowers them and lashes out at you");
		System.out.println(en + " dealt " + endmg + " dmg");
		System.out.println(en + " does 5 more dmg for the rest of combat");
		enmp -= 45;
		playerdef -= 5;
	}

	private void ram() {
		endmg = (int) ((Math.random() * 11) + 30) - playerdef;
		System.out.println("The " + en + " rams into you");
		System.out.println("The " + en + " dealt " + endmg + " dmg");
	}

	private void stare() {
		System.out.println("The " + en + " stares at you");
	}

	private void bellow() {
		System.out.println("The " + en + " calls out in pain");
	}

	private void bite() {
		effectchance = (int) ((Math.random() * 10) + 1);
		endmg = (int) ((Math.random() * 6) + 35) - playerdef;
		System.out.println("The " + en + " bites viciously");
		System.out.println("The " + en + " dealt " + endmg + " dmg");
		if ((effectchance == 1) && (!bleed)) {
			System.out.println("You are now Bleeding");
			bleed = true;
			timer += 3;
		} else if ((effectchance == 1) && (bleed)) {
			System.out.println("The Bleeding lasts longer");
			timer += 3;
		}
	}

	private void howl() {
		System.out.println("The " + en + " howls to the sky");
		System.out.println("The " + en + " gained +5 dmg for the rest of combat");
		playerdef -= 5;
		enmp -= 10;
	}

	private void scratch() {
		endmg = (int) ((Math.random() * 6) + 40) - playerdef;
		System.out.println("The " + en + " scratches you");
		System.out.println("The " + en + " dealt " + endmg + " dmg");
	}

	private void boneBash() {
		effectchance = (int) ((Math.random() * 10) + 1);
		endmg = (int) ((Math.random() * 6) + 35) - playerdef;
		System.out.println("The " + en + " bases you with bones");
		System.out.println("The " + en + " dealt " + endmg + " dmg");
		if ((effectchance == 1) && (!defdown)) {
			System.out.println("You have -5 def for 3 turns");
			defdown = true;
			timer += 3;
		} else if ((effectchance == 1) && (defdown)) {
			timer += 3;
		}
	}

	private void clatter() {
		System.out.println("The " + en + " clatters it bones");
		System.out.println("The " + en + " recovered 20 hp");
		enhp += 20;
		enmp -= 10;
	}

	private void cackle() {
		System.out.println("The " + en + " cackles manically");
		System.out.println("The " + en + " gained 7 dmg for the rest of combat");
		playerdef -= 7;
		enmp -= 20;
	}

	private void alchemize() {
		effectchance = (int) ((Math.random() * 3) + 1);
		System.out.println("The " + en + " brews a potion");
		if (effectchance == 1) {
			System.out.println("It's an Exploding Potion");
			endmg = (int) ((Math.random() * 11) + 50) - playerdef;
			System.out.println("She throws it at you");
			System.out.println(en + " dealt " + endmg + " dmg");

		} else if (effectchance == 2) {
			System.out.println("It's a Healing Potion");
			System.out.println("The " + en + " recovered 50 hp");
			enhp += 50;

		} else {
			System.out.println("It's a Fire Potion");
			if (!burn) {
				System.out.println("You are now Burning");
				burn = true;
				timer += 3;
			} else if (burn) {
				System.out.println("The Burning lasts longer");
				timer += 3;
			}
		}
		enmp -= 70;
	}

	private void magicShot() {
		endmg = (int) ((Math.random() * 6) + 45) - playerdef;
		System.out.println("The " + en + " shoots a magical bolt at you");
		System.out.println("The " + en + " dealt " + endmg + " dmg");
		enmp -= 40;
	}

	private void manaPotion() {
		System.out.println("The " + en + " drinks a mana potion");
		System.out.println("The " + en + " recovered 100 mp");
		enmp += 100;
	}

	private void lightBurst() {
		endmg = (int) ((Math.random() * 11) + 30) - playerdef;
		System.out.println("The " + en + " shoots a weak beam of light at you");
		System.out.println("The " + en + " dealt " + endmg + " dmg");
		enmp -= 10;
	}

	private void fangStrike() {
		endmg = (int) ((Math.random() * 11) + 35) - playerdef;
		System.out.println("The " + en + " bites at you with its fangs");
		System.out.println("The " + en + " dealt " + endmg + " dmg");
	}

	private void coil() {
		System.out.println("The " + en + " spins itself into a coil");
		System.out.println("The " + en + " gained 5 def for the rest of combat");
		endef += 5;
	}

	private void spiderStrike() {
		endmg = (int) ((Math.random() * 11) + 35) - playerdef;
		System.out.println("The " + en + " strikes you");
		System.out.println("The " + en + " dealt " + endmg + " dmg");
	}

	private void spinWeb() {
		System.out.println("The Giant Spider spun itself a web");
		System.out.println("The Giant Spider recovered 50 hp");
		System.out.println("The Giant Spider gained +5 def for the rest of combat");
		endef += 5;
		enhp += 50;
		enmp -= 10;
	}

	private void venomStrike() {
		effectchance = (int) ((Math.random() * 10) + 1);
		endmg = (int) ((Math.random() * 11) + 30) - playerdef;
		System.out.println("The " + en + " sinks its venom into you");
		System.out.println("The " + en + " dealt " + endmg + " dmg");
		if ((effectchance <= 3) && (!poison)) {
			System.out.println("You are now Poisoned");
			poison = true;
			timer += 5;
		} else if ((effectchance <= 3) && (poison)) {
			System.out.println("The Poison lasts longer");
			timer += 5;
		}
	}

	private void clawStrike() {
		endmg = (int) ((Math.random() * 6) + 50) - playerdef;
		System.out.println("The " + en + " attacks you with its claws");
		System.out.println("The " + en + " dealt " + endmg + " dmg");
	}

	private void fireBreathing() {
		effectchance = (int) ((Math.random() * 10) + 1);
		endmg = (int) ((Math.random() * 6) + 60) - playerdef;
		System.out.println("The " + en + " breathes fire all around you");
		System.out.println("The " + en + " dealt " + endmg + " dmg");
		if ((effectchance <= 2) && (!burn)) {
			System.out.println("You are now Burning");
			burn = true;
			timer += 3;
		} else if ((effectchance <= 2) && (burn)) {
			System.out.println("The Burning lasts longer");
			timer += 3;
		}
	}

	private void tailSwipe() {
		endmg = (int) ((Math.random() * 16) + 40) - playerdef;
		System.out.println("The " + en + " seeps its tail under you");
		System.out.println("The " + en + " dealt " + endmg + " dmg");
	}

	private void manaDrain() {
		effectchance = (int) ((Math.random() * 10) + 1);
		System.out.println("The " + en + " chants a spell that drains your mana");
		if ((effectchance <= 2) && (!mpdrain)) {
			System.out.println("You are now under the effects of MPDrain");
			mpdrain = true;
			timer += 5;
		} else if ((effectchance <= 2) && (mpdrain)) {
			System.out.println("MPDrain lasts longer");
			timer += 5;
		}
	}

	private void soar() {
		System.out.println(en + " soars into the sky");
		System.out.println(en + " recovered 70hp & 70mp");
		enhp += 70;
		enmp += 70;
	}

	private void corrode() {
		System.out.println(en + " spews slime that corrodes armor");
		System.out.println("You have -5 def for 3 turns");
		enmp -= 10;
		playerdef -= 5;
		timer += 3;
	}

	private void slimeStrike() {
		endmg = (int) ((Math.random() * 6) + 25) - playerdef;
		System.out.println("The " + en + " strikes you with its gelatinous body");
		System.out.println("The " + en + " dealt " + endmg + " dmg");
	}

	private void slice() {
		effectchance = (int) ((Math.random() * 10) + 1);
		endmg = (int) ((Math.random() * 6) + 35) - playerdef;
		System.out.println("The " + en + " slices you");
		System.out.println("The " + en + " dealt " + endmg + " dmg");
		if ((effectchance <= 2) && (!bleed)) {
			System.out.println("You are now Bleeding");
			bleed = true;
			timer += 3;
		} else if ((effectchance <= 2) && (bleed)) {
			System.out.println("The Bleeding lasts longer");
			timer += 3;
		}
	}

	private void bladeDance() {
		multi1 = (int) ((Math.random() * 6) + 30) - playerdef;
		effectchance = (int) ((Math.random() * 10) + 1);
		multi2 = (int) ((Math.random() * 6) + 35) - playerdef;
		effectchance2 = (int) ((Math.random() * 10) + 1);
		multi3 = (int) ((Math.random() * 6) + 40) - playerdef;
		effectchance3 = (int) ((Math.random() * 10) + 1);
		System.out.println(en + " performs a lethal dance of blades");
		System.out.println(en + " dealt " + multi1 + " dmg");
		System.out.println(en + " dealt " + multi2 + " dmg");
		System.out.println(en + " dealt " + multi3 + " dmg");
		if ((effectchance == 1) && (!bleed)) {
			System.out.println("You are now Bleeding");
			bleed = true;
			timer += 2;
		} else if ((effectchance == 1) && (bleed)) {
			System.out.println("The Bleeding lasts longer");
			timer += 2;
		}
		if ((effectchance2 == 1) && (!bleed)) {
			System.out.println("You are now Bleeding");
			bleed = true;
			timer += 2;
		} else if ((effectchance2 == 1) && (bleed)) {
			System.out.println("The Bleeding lasts longer");
			timer += 2;
		}
		if ((effectchance3 == 1) && (!bleed)) {
			System.out.println("You are now Bleeding");
			bleed = true;
			timer += 2;
		} else if ((effectchance3 == 1) && (bleed)) {
			System.out.println("The Bleeding lasts longer");
			timer += 2;
		}
		endmg = multi1 + multi2 + multi3;
		enmp -= 30;
	}

	private void spotWeakness() {
		System.out.println(en + " leers at you to find weak points");
		System.out.println(en + " gains +7 dmg until the end of combat");
		playerdef -= 7;
		enmp -= 50;
	}

	private void smokeBomb() {
		System.out.println(en + " throws down a wall of smoke");
		System.out.println(en + " gains +5 def until the end of combat");
		endef += 5;
		enmp -= 40;
	}

	private void setup() {
		System.out.println(en + " reorganizes her gear");
		System.out.println(en + " recovered 125mp");
		enmp += 125;
	}

	private void swordStrike() {
		endmg = (int) ((Math.random() * 6) + 50) - playerdef;
		System.out.println(en + " strikes you with his sword");
		System.out.println(en + " dealt " + endmg + " dmg");
	}

	private void holyStrike() {
		endmg = (int) ((Math.random() * 6) + 65) - playerdef;
		System.out.println(en + " channels holy energy unleashes it");
		System.out.println(en + " dealt " + endmg + " dmg");
		enmp -= 30;
	}

	private void advancedHeal() {
		System.out.println(en + " casts an advanced healing spell");
		System.out.println(en + " recovered 80hp");
		enhp += 80;
		enmp -= 25;
	}

	private void inspiration() {
		System.out.println(en + " looks up to GAIA for inspiration");
		System.out.println(en + " recovered 100mp");
		enmp += 100;
	}

	private void majorHeal() {
		System.out.println(en + " casts a major healing spell");
		System.out.println(en + " recovered 150hp");
		enhp += 100;
		enmp -= 40;
	}

	private void equilibrium() {
		endmg = (enhp / 5);
		System.out.println(en + " uses her own lifeforce to smite you");
		System.out.println(en + " took " + (enhp / 15) + " dmg");
		System.out.println(en + " dealt " + endmg + " dmg");
		enhp -= (enhp / 15);
		enmp -= 130;
	}

	private void manaSink() {
		endmg = (enmp / 3) + 75;
		System.out.println(en + " unleashes all her energy in a dazzling light");
		System.out.println(en + " dealt " + endmg + "dmg");
		enmp = 0;
	}

	private void divineInspiration() {
		System.out.println(en + " calls upon the gods to give her inspiration");
		System.out.println(en + " recovered up to 300mp");
		enmp = 300;
	}

	// Returns the intent
	public String getIntent() {
		return intent;
	}

	// Resets intent at the beginning of the round
	public void resetIntent() {
		intent = "";
	}

	// Resets the enemy damage at the beginning of the round
	public void resetDamage() {
		endmg = 0;
	}

	// Resets player effects back to normal
	public void resetPlayerEffect() {
		defdown = false;
		playerdef = 10;
		poison = false;
		burn = false;
		bleed = false;
		mpdrain = false;
		timer = 0;
	}

	// Checks for statuses
	public boolean poisonCheck() {
		return poison;
	}

	public boolean burnCheck() {
		return burn;
	}

	public boolean bleedCheck() {
		return bleed;
	}

	public boolean mpdrainCheck() {
		return mpdrain;
	}

	// Returns the enemy's name
	public String getEnName() {
		return en;
	}

	// Returns enemy hp
	public int getEnHp() {
		return enhp;
	}

	// Returns enemy mp
	public int getEnMp() {
		return enmp;
	}

	// Returns enemy def
	public int getEnDef() {
		return endef;
	}

	// Returns enemy dmg
	public int enDamage() {
		return endmg;
	}

	// Updates the player's def for the enemy class
	public void setPlayerDef(int pdef, int extradef, int extrapermadef) {
		playerdef = pdef + extradef + extrapermadef;
		extraplayerdef = extradef;
		extrapermaplayerdef = extrapermadef;
	}

	// Used to change the def in the player class
	public int reduceDef() {
		return playerdef - extraplayerdef - extrapermaplayerdef;
	}

	// Advances a timer if there is an effect on the player (def down, extra hp
	// lost, etc.)
	public void advanceTimer() {
		timer -= 1;
	}

	// Checks to see if there's an active timer
	public boolean timerCheck() {
		if (timer > 0) {
			return true;
		} else {
			return false;
		}
	}

	// Changes the enemy's hp when they take damage
	public void takeDamage(int endmgtaken) {
		enhp -= endmgtaken;
	}

}