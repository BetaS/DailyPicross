package com.realapps.android.dailypicross.util;

import android.graphics.Canvas;
import android.graphics.Paint.Style;

import com.realapps.engine.core.drawable.shape.RectShapeDrawable;

public class PicrossTile extends RectShapeDrawable {
	public static class PicrossTileBuilder extends RectShapeBuilder {
		public PicrossTileBuilder(int arg0, int arg1, int arg2, int arg3) {
			super(arg0, arg1, arg2, arg3);
		}
		
		public PicrossTile build(String id) {
			mID = id;
			return new PicrossTile(this);
		}
	}
	
	private PicrossTile(PicrossTileBuilder builder) {
		super(builder);
	}
	
	public static void makeTile(String name, int left, int top, int right, int bottom) {
		new PicrossTileBuilder(left, top, right, bottom).setMode(PicrossMap.TYPE_NONE).build(name);
	}

	@Override
	public void draw(Canvas canvas) {
		mPaint.setStyle(Style.FILL);
		if(getMode() == PicrossMap.TYPE_MARKER) {
			mPaint.setColor(0xFF000000);
			canvas.drawRect(vertices.get(0).getX(), vertices.get(0).getY(), vertices.get(1).getX(), vertices.get(1).getY(), mPaint);
		} else if(getMode() == PicrossMap.TYPE_PENCIL) {
			mPaint.setColor(0xFFAAAAAA);
			canvas.drawRect(vertices.get(0).getX(), vertices.get(0).getY(), vertices.get(1).getX(), vertices.get(1).getY(), mPaint);
		} else if(getMode() == PicrossMap.TYPE_NOT) {
			mPaint.setColor(0xFFAA0000);
			canvas.drawRect(vertices.get(0).getX(), vertices.get(0).getY(), vertices.get(1).getX(), vertices.get(1).getY(), mPaint);
		}

		mPaint.setStyle(Style.STROKE);
		mPaint.setColor(0xFF000000);
		canvas.drawRect(vertices.get(0).getX(), vertices.get(0).getY(), vertices.get(1).getX(), vertices.get(1).getY(), mPaint);
	}
}
