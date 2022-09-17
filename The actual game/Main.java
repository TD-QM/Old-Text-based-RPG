class Main 
{
  public static void main(String[] args) 
  {
    TextAdventure adventure = new TextAdventure();
    adventure.play();
  }
}

/*
Did you complete the Player Class?
Probably not as intended, but yeah

Did you change the image upon entering each zone?
Yes

Did you give a description and choices for each zone?
Yeah, or at least to the best of my ability

What are your 6 zones?
Doc said at least 6 zones, so I put in 8
  0) Death
  1) Rest
  2) Nightmare
  3) Forest
  4) Graveyard
  5) Mountain
  6) Theif Hideout
  7) Abandoned City
The 0th one is the death state that you get sent to if you die in combat

Where do you use a random number?
All throughout combat with your damage values and most of the enemies' damage 
  values having a possible range of damage that they can do depending on the attack.

Where do you use an and (&&) or an or (||)?
I'll be honest, this question was confusing when I first read it haha
[And] and [Or] statements are found within combat again to check for sufficient 
  mana or hp for an ability

Where do you use a not (!)?
It's used in the player class to check to see if the late game skill DEMON FORM
  was active or not. If it wasn't, then the skill could be cast. If it was already
  active, then the player is unable to cast it.
It's also used in the enemy class to check to see if a debuff is active already or not

Where do you use an if, else if, else block?
Mainly in user input to check their answer and to prompt them if they input an 
  invaid choice, such as when prompted to choose a skill or a location to go to.
*/