package com.realapps.android.dailypicross.scene;

import android.view.MotionEvent;

import com.realapps.engine.core.drawable.ImageDrawable;
import com.realapps.engine.core.renderer.RenderManager;
import com.realapps.engine.core.scene.GameScene;
import com.realapps.game.R;

public class TitleScene extends GameScene {

	@Override
	public void onInit() {
		setBackgroundColor(255, 0, 255);
		
		new ImageDrawable.ImageBuilder().setSource(R.drawable.icon).setPosition(100, 100).build("icon");
	}

	@Override
	public void onDestroy() {
		RenderManager.clearDrawable();
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
