package com.temena.poer;

import org.flixel.FlxG;
import org.flixel.FlxState;
import org.flixel.FlxSprite;
import org.flixel.system.*;
import org.flixel.*;
import org.flixel.ui.*;
import com.badlogic.gdx.utils.*;
import org.flixel.event.*;

public class LevelFour extends FlxState
{	
	Player player;
	Enemy enemy;
	FlxSprite hearts;
	FlxSprite portal;
	FlxTilemap level;
	FlxTilemap level2;
	FlxTileblock floor, platformOne, platformTwo, platformThree;
	FlxVirtualPad pad;
	
	int health;
	int hurtWait;
	
	int data[] = {
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
	    0,0,0,0,0,0,0,0,0,0,0,0,0,2,3,3,3,4,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,5,5,0,0,0,5,5,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,2,4,5,0,0,0,5,2,4,0,0,0,0,0,
	    10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,
		1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
		1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
	    1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1
	};
	int data2[] = {
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
	    0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
	    10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,
		1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
		1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
	    1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1
	};
	
	@Override
	public void create() {
		FlxG.setBgColor(0xFF00BBFF);
		health = 4;
		hurtWait = 0;
		level = new FlxTilemap();
		level.loadMap(FlxTilemap.arrayToCSV(new IntArray(data), 25), "map.png", 16, 16);
		level2 = new FlxTilemap();
		level2.loadMap(FlxTilemap.arrayToCSV(new IntArray(data2), 25), "map.png", 16, 16);
		level2.x = 400;
		add(level);
		add(level2);
		hearts = new FlxSprite(1, 1);
		hearts.loadGraphic("untitled.png", true, true, 36, 8);
		hearts.addAnimation("full", new int[]{3}, 0, false);
		hearts.addAnimation("three", new int[]{2}, 0, false);
		hearts.addAnimation("two", new int[]{1}, 0, false);
		hearts.addAnimation("one", new int[]{0}, 0, false);
		hearts.play("full");
		hearts.immovable = true;
		add(hearts);
		portal = new FlxSprite(112, 112);
		portal.loadGraphic("portal.png", true, true, 16, 16);
		portal.addAnimation("normal", new int[]{3,2,1,0,1,2}, 10, true);
		portal.play("normal");
		portal.immovable = true;
		//add(portal);
		pad = new FlxVirtualPad(FlxVirtualPad.LEFT_RIGHT, FlxVirtualPad.A_B);
		player = new Player(10, 10, 8, 16, pad);
		player.setHasGravityToggle(false);
		enemy = new Enemy(240, 160, 16, 16);
		enemy.followSprite(player);
		FlxG.camera.follow(player, FlxCamera.STYLE_PLATFORMER);
		FlxG.camera.setBounds(0, 0, 800, 240);
		add(player);
		add(enemy);
		add(pad);
		add(new FlxText(375, 160, 200, "->"));
		add(new FlxText(150, 80, 300, "Avoid enemys. He looks very happy to see you!\n(Hint: though, you shouldn't be happy to see him.)"));
	}
	public void hurtPlayer() {
		hurtWait = 150;
		if(health == 4) {
			hearts.play("three");
			health = 3;
		} else if(health == 3) {
			hearts.play("two");
			health = 2;
		} else if(health == 2) {
			hearts.play("one");
			health = 1;
		} else if(health == 1) {
			player.kill();
			hearts.kill();
			FlxG.fade(0xFF000000, 2, new IFlxCamera() {

					@Override
					public void callback()
					{
						//Die
						FlxG.resetState();
					}
					
				
			});
		}
	}
	@Override
	public void update() {
		super.update();
		if(hurtWait != 0) {
			hurtWait -= 1;
		}
		/* FlxG.overlap(enemy, player, new IFlxCollision(){
			@Override
			public void callback(FlxObject Object1, FlxObject Object2) {
				if(hurtWait==0){hurtPlayer();}
			}
		}); */
		FlxG.overlap(portal, player, new IFlxCollision(){
				@Override
				public void callback(FlxObject Object1, FlxObject Object2) {
					FlxG.switchState(new PlayState());
				}
			});
		if(player.y > FlxG.height) {
			FlxG.resetState();
		}
		if(player.y < 0) {
			FlxG.resetState();
		}
		FlxG.collide(player, level);
		FlxG.collide(player, level2);
		FlxG.collide(enemy, level);
	}
			 
}
