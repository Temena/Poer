package com.temena.poer;

import org.flixel.FlxGame;
import org.flixel.*;

public class PoerGame extends FlxGame
{
	public PoerGame()
	{
		super(400, 240, PlayState.class, 2, 60, 50, false, FlxCamera.FILL_X);
	}
}
