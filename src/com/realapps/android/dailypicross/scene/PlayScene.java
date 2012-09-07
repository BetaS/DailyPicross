package com.realapps.android.dailypicross.scene;

import java.io.IOException;

import android.view.MotionEvent;

import com.realapps.android.dailypicross.R;
import com.realapps.android.dailypicross.util.PicrossMap;
import com.realapps.android.dailypicross.util.PicrossTile;
import com.realapps.engine.Game;
import com.realapps.engine.core.debug.logger.Debug;
import com.realapps.engine.core.drawable.TextDrawable;
import com.realapps.engine.core.drawable.TextDrawable.TextBuilder;
import com.realapps.engine.core.scene.GameScene;
import com.realapps.engine.core.scene.ui.UIButton;
import com.realapps.engine.core.scene.ui.UISwitchButton;
import com.realapps.engine.core.scene.ui.UIView;

public class PlayScene extends GameScene {
	private PicrossMap mMap = null;
	private int mMode = PicrossMap.TYPE_MARKER;
	
	private UISwitchButton btnMarker, btnPencil, btnErase, btnNot;
	
	public PlayScene(String file) {
		try {
			mMap = PicrossMap.getInstance(file);
		} catch (IOException e) {
			Game.getContext().finish();
			e.printStackTrace();
		}
	}
	
	@Override
	public void onInit() {
		setBackgroundColor(255, 255, 255);
		
		btnMarker = loadSwitchButton("marker", R.drawable.tab_btn_marker_off, R.drawable.tab_btn_marker_on, 0, 680);
		btnPencil = loadSwitchButton("pencil", R.drawable.tab_btn_pencil_off, R.drawable.tab_btn_pencil_on, 120, 680);
		btnNot = loadSwitchButton("not", R.drawable.tab_btn_not_off, R.drawable.tab_btn_not_on, 240, 680);
		btnErase = loadSwitchButton("eraser", R.drawable.tab_btn_eraser_off, R.drawable.tab_btn_eraser_on, 360, 680);
		
		int size = mMap.getSize();
		for(int x=0; x<size; x++) {
			int[] arr = mMap.getColHint(x);
			String hint = "";
			for(int item: arr) {
				hint+=item+"\n";
			}
			new TextBuilder().setText(hint).setSize(24).setAlign(TextDrawable.ALIGN_BOTTOM).setPosition(110+x*(380/size), 90).build("text_x"+x);
		}
		
		for(int y=0; y<size; y++) {
			int[] arr = mMap.getRowHint(y);
			String hint = "";
			for(int item: arr) {
				hint+=item+" ";
			}
			new TextBuilder().setText(hint).setSize(24).setAlign(TextDrawable.ALIGN_RIGHT).setPosition(100, 100+y*(380/size)).build("text_y"+y);
		}
		
		for(int y=0; y<size; y++) {
			for(int x=0; x<size; x++) {
				PicrossTile.makeTile("map["+x+","+y+"]", 100+(x*(380/size)), 100+(y*(380/size)), 100+((x+1)*(380/size)), 100+((y+1)*(380/size)));
			}
		}
		
		onClick(btnMarker);
	}
	@Override
	public void onPreRender() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onBackPressed() {
		finish(true);
	}
	
	@Override
	public void onTouch(MotionEvent event) {
		super.onTouch(event);
	
		if(event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_MOVE) {
			int x = (int)event.getX(); 
			int y = (int)event.getY();
			
			if(x >= 100 && y >= 100 && x <= 480 && y <= 480) {
				x = (x-100)/(380/mMap.getSize());
				y = (y-100)/(380/mMap.getSize());
				
				PicrossTile drawable = (PicrossTile)findDrawableById("map["+x+","+y+"]");
				if(mMap.check(mMode, x, y)) {
					// Å¬¸®¾î
					Debug.toast("CLEAR!", true);
					finish(true);
				}
				drawable.setMode(mMap.getInput(x, y));
			}
		}
	}
	
	@Override
	public void onClick(UIView view) {
		super.onClick(view);
	
		btnMarker.setState(UIButton.BUTTON_UP);
		btnPencil.setState(UIButton.BUTTON_UP);
		btnNot.setState(UIButton.BUTTON_UP);
		btnErase.setState(UIButton.BUTTON_UP);

		((UIButton)view).setState(UIButton.BUTTON_DOWN);
		
		if(view == btnMarker) {
			mMode = PicrossMap.TYPE_MARKER;
		} else if(view == btnPencil) {
			mMode = PicrossMap.TYPE_PENCIL;
		} else if(view == btnNot) {
			mMode = PicrossMap.TYPE_NOT;
		} else if(view == btnErase) {
			mMode = PicrossMap.TYPE_NONE;
		}
	}
}
