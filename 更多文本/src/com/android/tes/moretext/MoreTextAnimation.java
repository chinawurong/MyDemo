package com.android.tes.moretext;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class MoreTextAnimation extends Animation {
	private View view;
	private int maxHeight, minHeight;
	private int thisCount = 0;
	private boolean showView;

	public MoreTextAnimation(View view, int maxHeight, int minHeight) {
		this.view = view;
		this.maxHeight = maxHeight;
		this.minHeight = minHeight;
		this.showView = true;
	}

	@Override
	public void initialize(int width, int height, int parentWidth, int parentHeight) {
		super.initialize(width, height, parentWidth, parentHeight);
	}

	@Override
	protected void applyTransformation(float interpolatedTime, Transformation t) {
		super.applyTransformation(interpolatedTime, t);
		int count = (int) (maxHeight * interpolatedTime);
		if (showView) {
			count = maxHeight - count;
		}
		if (thisCount != count && count > minHeight) {
			thisCount = count;
			LayoutParams params = view.getLayoutParams();
			params.height = count;
			view.setLayoutParams(params);
		}
	}

	public boolean isShowView() {
		return showView;
	}

	public void setShowView(boolean showView) {
		this.showView = showView;
	}
}
