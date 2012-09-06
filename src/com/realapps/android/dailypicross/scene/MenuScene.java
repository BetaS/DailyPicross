package com.realapps.android.dailypicross.scene;

import com.realapps.android.dailypicross.R;
import com.realapps.engine.core.scene.GameScene;
import com.realapps.engine.core.scene.ui.UIView;

public class MenuScene extends GameScene {

	@Override
	public void onInit() {
		setBackgroundColor(255, 255, 255);

		loadImage("bg", R.drawable.title_bg, 0, 0, 0);
		loadButton("play", R.drawable.btn_play_up, R.drawable.btn_play_down, UIView.CENTER, 500);
	}

	@Override
	public void onPreRender() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTimer(int timer_idx) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void onClick(UIView view) {
		if(view.getId().equals("play")) {
			startScene(new PlayScene("/mnt/sdcard/picross.txt"));
		}
	}
}
