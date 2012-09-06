package com.realapps.android.dailypicross.scene;

import java.io.IOException;

import android.view.MotionEvent;

import com.realapps.android.dailypicross.R;
import com.realapps.android.dailypicross.util.PicrossMap;
import com.realapps.engine.Game;
import com.realapps.engine.core.debug.logger.Debug;
import com.realapps.engine.core.drawable.TextDrawable;
import com.realapps.engine.core.drawable.TextDrawable.TextBuilder;
import com.realapps.engine.core.drawable.shape.RectShapeDrawable;
import com.realapps.engine.core.drawable.shape.RectShapeDrawable.RectShapeBuilder;
import com.realapps.engine.core.scene.GameScene;

public class PlayScene extends GameScene {
	private PicrossMap mMap = null;
	
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
		
		loadButton("marker", R.drawable.tab_btn_marker_off, R.drawable.tab_btn_marker_on, 0, 680);
		loadButton("pencil", R.drawable.tab_btn_pencil_off, R.drawable.tab_btn_pencil_on, 120, 680);
		loadButton("not", R.drawable.tab_btn_not_off, R.drawable.tab_btn_not_on, 240, 680);
		loadButton("eraser", R.drawable.tab_btn_eraser_off, R.drawable.tab_btn_eraser_on, 360, 680);
		
		int size = mMap.getSize();
		for(int x=0; x<size; x++) {
			int[] arr = mMap.getColHint(x);
			String hint = "";
			for(int item: arr) {
				hint+=item+"\n";
			}
			new TextBuilder().setText(hint).setSize(24).setAlign(TextDrawable.ALIGN_BOTTOM).setPosition(100+x*(380/size), 90).build("text_x"+x);
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
				new RectShapeBuilder(100+(x*(380/size)), 100+(y*(380/size)), 100+((x+1)*(380/size)), 100+((y+1)*(380/size))).setMode(1).build("map["+x+","+y+"]");
			}
		}
	}
	@Override
	public void onPreRender() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onTouch(MotionEvent event) {
		super.onTouch(event);
	
		if(event.getAction() == MotionEvent.ACTION_UP) {
			int x = (int)event.getX(); 
			int y = (int)event.getY();
			
			if(x >= 100 && y >= 100 && x <= 480 && y <= 480) {
				x = (x-100)/(380/mMap.getSize());
				y = (y-100)/(380/mMap.getSize());
				
				RectShapeDrawable drawable = (RectShapeDrawable)findDrawableById("map["+x+","+y+"]");
				if(mMap.check(PicrossMap.TYPE_MARKER, x, y)) {
					// Å¬¸®¾î
					Debug.toast("CLEAR!", true);
					finish(true);
				}
				switch(mMap.getInput(x, y)) {
					case PicrossMap.TYPE_MARKER: {
						drawable.setColor(0xFF000000);
						drawable.setMode(RectShapeDrawable.MODE_FILL);
					} break;
					case PicrossMap.TYPE_NOT: {
						drawable.setColor(0xFFBB0000);
						drawable.setMode(RectShapeDrawable.MODE_FILL);
					} break;
					case PicrossMap.TYPE_PENCIL: {
						drawable.setColor(0xFF666666);
						drawable.setMode(RectShapeDrawable.MODE_FILL);
					} break;
					default: {
						drawable.setColor(0xFF000000);
						drawable.setMode(RectShapeDrawable.MODE_LINE);
					} break;
				}
			}
		}
	}
}
