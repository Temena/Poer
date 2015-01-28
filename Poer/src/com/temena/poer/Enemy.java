package com.temena.poer;
import org.flixel.*;
import org.flixel.event.*;

public class Enemy extends FlxSprite {
	boolean isFollowingSprite;
	FlxSprite spriteToFollow;
	FlxSprite kills[];
	FlxSprite getsKilledBy[];
	int _jumpPower;
	@Override
	public Enemy(int x, int y, int width, int height) {
		super(width, height);
		this.x = x;
		this.y = y;
		int runSpeed = 80;
		_jumpPower = 200;
		maxVelocity.y = _jumpPower;
		drag.x = runSpeed*8;
		maxVelocity.x = runSpeed;
		acceleration.y = 500;
		loadGraphic("manClothed.png", true, true, 8, 16);
		addAnimation("stand", new int[]{0},0, false);
		addAnimation("walk", new int[]{0,1,2,3}, 4, true);
		addAnimation("jump", new int[]{1}, 1, true);
		play("stand");
		isFollowingSprite = false;
	}
	@Override
	public void update() {
		acceleration.x = 0;
		super.update();
		if(isFollowingSprite && spriteToFollow != null) {
			if(spriteToFollow.x != x) {
				if(isTouching(FlxObject.WALL))jump();
				if(spriteToFollow.x > x) {
					//if(isTouching(FlxObject.FLOOR))
					goRight();
				} else if(spriteToFollow.x < x) {
					//if(isTouching(FlxObject.FLOOR))
					goLeft();
				}
			} else {
				stop();
			}
		}
		//FlxG.collide(spriteToFollow, this, new IFlxCollision() {

				//@Override
				//public void callback(FlxObject p1, FlxObject p2)
				//{
				//	p1.kill();
				//}
				
			
		//});
	}
	public void followSprite(FlxSprite sprite) {
		spriteToFollow = sprite;
		isFollowingSprite = true;
	}
	public void goLeft() {
		acceleration.x -= drag.x;
		setFacing(LEFT);
		play("walk");
	}
	public void goRight() {
		acceleration.x += drag.x;
		setFacing(RIGHT);
		play("walk");
	}
	public void stop() {
		acceleration.x = 0;
		play("stop");
	}
	public void jump() {
		if(isTouching(FlxObject.FLOOR))velocity.y = - maxVelocity.y/1.2f;
	}
}
