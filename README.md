# LudumDare32-Entry
Jacklify85's Ludum Dare Entry for LD32 

YOU CAN PLAY THE GAME HERE: http://ludumdare.com/compo/ludum-dare-32/?action=preview&uid=49513

Super ZDefeat is a 2D shooter. The goal of the game is to survive and kill as many zombies as possible to get the highest score possible. Since this is my first game I've ever released, I decided to keep it simple. 

The zombies have path-finding implemented into them to try to attack you and spawn in waves. However, you are not completely alone! There are pickups that spawn relatively near you to help you with this goal! You are left with 4 health boost pickups and 4 ammo resupply pickups. You also are left with 3 maximum health boost pickups and 3 temporary speed boost pickups. Every 7 seconds during a zombie wave a new pickup of a random type will spawn. You also start off with 200 ammo for your weapon. 

Pickups: 
- Health Boost -> Heals your health to match your maximum health 
- Ammo resupply -> Adds 100 bullets to your current bullet count. 
- Max Health Boost -> Increases your maximum health by 150 
- Speed Boost -> Allows you to run faster for 5 seconds to give you a big advantage over the zombies 

The Weapons: 
- A fire bullet shooting pistol! Any zombie that gets hit with these bullets will ignite and start to quickly die from the inflicted damage from the wound 
- You, yourself, are a weapon. Your blood is infected with a virus that when zombies attack you, they instantly perish. However, this weapon takes a big toll on your health. It is recommended that you save this except for last resorts. 

Controls: 
- WASD or arrow keys -> to move the player around 
- Space Bar -> To fire the pistol 
- Escape -> To pause / un-pause the game 

Requirements: 
- An OpenGL-capable system 
- Java (A version w/o requiring Java will be posted soon) 

Credits: 
- The libGDX project and related extensions for their amazing framework that powers Super ZDefeat! 
- The box2d project for their amazing physics engine 
- autotracker.py for their awesome music generator script!


