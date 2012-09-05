package com.realapps.android.dailypicross;

import android.os.Bundle;

import com.realapps.android.dailypicross.scene.TitleScene;
import com.realapps.engine.Game;

public class GameActivity extends Game {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, new TitleScene(), 480, 800, true, 30, true);
	}
}
