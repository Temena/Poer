package com.temena.poer;
import org.flixel.*;
import org.flixel.ui.*;
import org.flixel.event.*;

public class Player extends FlxSprite
{
	FlxVirtualPad pad;
	int _jumpPower;
	boolean isUpsideDown;
	boolean hasGravityToggle;
	
	public Player(int x, int y, int width, int height, FlxVirtualPad pad) {
		super(width, height);
		this.pad = pad;
		isUpsideDown = false;
		hasGravityToggle = false;
		//Currently not needed
		pad.buttonB.onDown = new IFlxButton(){@Override public void callback(){toggle();}};
		//makeGraphic(width, height, 0xFF921700);
		loadGraphic("man.png", true, true, 8, 16);
		addAnimation("stand", new int[]{0},0, false);
		addAnimation("walk", new int[]{0,1,2,3}, 4, true);
		addAnimation("jump", new int[]{1}, 1, true);
		addAnimation("uStand", new int[]{4}, 0, false);
		addAnimation("uWalk", new int[]{4,5,6,7}, 4, true);
		addAnimation("uJump", new int[]{5}, 1, true);
		play("stand");
		this.x = x;
		this.y = y;
		int runSpeed = 80;
		drag.x = runSpeed*8;
		acceleration.y = 500;
		_jumpPower = 200;
		maxVelocity.x = runSpeed;
		maxVelocity.y = _jumpPower;
		//pad.buttonA.x = 300;
		//pad.buttonA.y = 185;
		pad.setDPadPositionX(10);
	}
	
	public void setHasGravityToggle(boolean hasGravityToggle) {
		this.hasGravityToggle = hasGravityToggle;
	}
	
	public void toggle() {
		if(!hasGravityToggle)
			return;
		if(isUpsideDown) {
			isUpsideDown = false;
			acceleration.y = 500;
			play("walk");
			return;
		} else {
			isUpsideDown = true;
			acceleration.y = -500;
			play("uWalk");
			return;
		}
	}
	
	@Override
	public void update(){
		acceleration.x = 0;
		if(pad.buttonA.status == FlxButton.PRESSED)
		{
			if(!isUpsideDown) {
				play("jump");
			}
			else {
				play("uJump");
			}
			if(!isUpsideDown) {
			    if(isTouching(FlxObject.FLOOR)) {
				    velocity.y = - maxVelocity.y/1.2f;
			    }
			} else {
				if(isTouching(FlxObject.CEILING)) {
					velocity.y = + maxVelocity.y/1.2f;
				}
			}
		}
		/* if(pad.buttonB.status == FlxButton.PRESSED) {
			toggle();
		} */
		if(pad.buttonLeft.status == FlxButton.PRESSED)
		{
			if(isTouching(FlxObject.FLOOR))
				if(!isUpsideDown){play("walk");}
			if(isTouching(FlxObject.CEILING))
				if(isUpsideDown){play("uWalk");}
			setFacing(LEFT);
			acceleration.x -= drag.x;
		}
		else if(pad.buttonRight.status == FlxButton.PRESSED)
		{
			if(isTouching(FlxObject.FLOOR))
				if(!isUpsideDown){play("walk");}
			if(isTouching(FlxObject.CEILING))
				if(isUpsideDown){play("uWalk");}
			setFacing(RIGHT);
			acceleration.x += drag.x;
		}else{
			if(isTouching(FlxObject.FLOOR))
				if(!isUpsideDown){play("stand");}
			if(isTouching(FlxObject.CEILING)) {
				if(isUpsideDown){play("uStand");}
			}
		}
		super.update();
	}
}
