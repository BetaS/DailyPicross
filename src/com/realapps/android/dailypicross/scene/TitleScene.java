package com.realapps.android.dailypicross.scene;

import android.view.MotionEvent;

import com.realapps.engine.core.scene.GameScene;
import com.realapps.game.R;

public class TitleScene extends GameScene {

	@Override
	public void onInit() {
		setBackgroundColor(255, 0, 255);
		
		loadImage("icon", R.drawable.icon, 0, 100, 100);
	}

	@Override
	public void onPreRender() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTouchScreen(MotionEvent event) {
		// TODO Auto-generated method stub
		startScene(new Title2(), true, true);
	}

	@Override
	public void onBackPressed() {
		finish(true);
	}
	
	@Override
	public void onTimer(int timer_idx) {
		// TODO Auto-generated method stub
	}
}
