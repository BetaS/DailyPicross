package com.realapps.android.dailypicross;

import android.os.Bundle;

import com.realapps.android.dailypicross.scene.TitleScene;
import com.realapps.engine.Game;

public class GameActivity extends Game {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, new TitleScene(), 720, 1280, 30, true);
	}
}
