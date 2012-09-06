package com.realapps.android.dailypicross.scene;

import android.view.MotionEvent;

import com.realapps.engine.core.drawable.Drawable;
import com.realapps.engine.core.scene.GameScene;
import com.realapps.game.R;

public class TitleScene extends GameScene {

	@Override
	public void onInit() {
		setBackgroundColor(255, 255, 255);
		
		loadImage("bg", R.drawable.title_bg, 0, 0, 0);
		loadImage("key", R.drawable.txt_presskey, 1, 240, 600).setOffsetToCenter();
		
		loadText("ver", "ver 1.0", 30, 1, 10, 800-30);
		
		setTimer(0, 500, 0);
	}

	@Override
	public void onPreRender() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTouch(MotionEvent event) {
		super.onTouch(event);
		replaceScene(new MenuScene(), true, false);
	}

	@Override
	public void onBackPressed() {
		finish(true);
	}
	
	@Override
	public void onTimer(int timer_idx) {
		if(timer_idx == 0) {
			Drawable drawable = findDrawableById("key");
			if(drawable.isShow())
				drawable.hide();
			else
				drawable.show();
		}
	}
}
