package com.realapps.android.dailypicross.scene;

import android.view.MotionEvent;

import com.realapps.engine.core.scene.GameScene;
import com.realapps.game.R;

public class Title2 extends GameScene {

	@Override
	public void onInit() {
		setBackgroundColor(255, 0, 0);
		
		loadImage("icon2", R.drawable.icon, 0, 100, 105);
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
