package com.realapps.android.dailypicross.scene;

import android.view.MotionEvent;

import com.realapps.engine.core.scene.GameScene;
import com.realapps.game.R;

public class Title2 extends GameScene {

	@Override
	public void onInit() {
		setBackgroundColor(255, 255, 255);

		loadImage("bg", R.drawable.title_bg, 0, 0, 0);
	}

	@Override
	public void onPreRender() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTouchScreen(MotionEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTimer(int timer_idx) {
		// TODO Auto-generated method stub
	}
}
