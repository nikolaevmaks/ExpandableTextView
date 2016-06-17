import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import ru.bringo247.library.view.utils.TextDimensionsUtils;
import ru.bringo247.library.view.utils.UpdateListener;
import ru.bringo247.library.view.utils.ValueAnimatorWithData;

public class ExpandableTextView extends TextView {

	private static final int ANIMATION_DURATION = 0;
	private CharSequence yetText;
	private int maxLineCount = 5;
	private int heightCollapsed, heightExpanded;
	private int oldWidth = -1;
	private boolean isMeasureAllowed;
	private Boolean isExpandAllowed;
	private CharSequence actualText;
	private State state = State.Collapsed;
	private ValueAnimatorWithData animator;
	private Listener listener;

	public ExpandableTextView(Context context) {
		super(context);
		init();
	}

	public ExpandableTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ExpandableTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		actualText = getText();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		final int width = getMeasuredWidth();

		if (isMeasureAllowed || oldWidth != width) {
			stopAnimator();
			isMeasureAllowed = false;

			isExpandAllowed = isExpandAllowed();
			if (isExpandAllowed) {
				final StaticLayout layout = new StaticLayout(actualText, getLayout().getPaint(), getLayout().getWidth(),
						getLayout().getAlignment(), getLayout().getSpacingMultiplier(), getLayout().getSpacingAdd(), true);

				heightCollapsed = layout.getLineTop(maxLineCount - 1) - layout.getLineAscent(maxLineCount - 1) + layout.getLineDescent(maxLineCount - 1) + layout.getBottomPadding();
				heightExpanded = layout.getLineTop(layout.getLineCount()) + layout.getBottomPadding();

				final int maxLineWidth = width - getPaddingLeft() - getPaddingRight();
				final float lineWidth = layout.getLineMax(maxLineCount - 1);
				final int offsetEnd = layout.getLineVisibleEnd(maxLineCount - 1);

				if (state == State.Collapsed) {
					float yetTextWidth = TextDimensionsUtils.getTextWidth(yetText.toString(), getPaint());
					float yetTextGap = yetTextWidth + yetTextWidth / 3;

					if (maxLineWidth - lineWidth > yetTextGap) {
						setText(TextUtils.concat(actualText.subSequence(0, offsetEnd), yetText));
					} else {
						float sum = 0;
						int i = offsetEnd - 1;
						while (maxLineWidth - (lineWidth - sum) < yetTextGap) {
							sum += TextDimensionsUtils.getTextWidth(actualText.charAt(i) + "", getPaint());
							i--;
						}
						setText(TextUtils.concat(actualText.subSequence(0, i + 1), yetText));
					}

					setHeight(getHeightCollapsed());
				} else {
					setText(actualText);
					setHeight(getHeightExpanded());
				}
			}

			if (listener != null) {
				listener.updateAppearance(this, isExpandAllowed);
			}
		}
		oldWidth = width;
	}

	private boolean isExpandAllowed() {
		return isExpandAllowed == null ? maxLineCount < getLineCount() : isExpandAllowed;
	}

	private int getHeightCollapsed() {
		return getPaddingTop() + heightCollapsed + getPaddingBottom();
	}

	private int getHeightExpanded() {
		return getPaddingTop() + heightExpanded + getPaddingBottom();
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		stopAnimator();
	}

	public void setActualText(CharSequence text) {
		stopAnimator();

		actualText = text;
		setText(actualText);
	}

	public void setYetText(CharSequence yetText) {
		this.yetText = yetText;
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}

	public void expandOrCollapse(boolean expand) {
		stopAnimator();

		final State oldState = state;
		state = expand ? State.Expanded : State.Collapsed;
		if (oldState != state && getWidth() != 0 && isExpandAllowed()) {
			// after layout
			if (state == State.Collapsed) {
				animator = ValueAnimatorWithData.ofInt(ANIMATION_DURATION, new UpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						setHeight((Integer) animation.getAnimatedValue());
					}

					@Override
					public void onAnimationEnd(Animator animation) {
						if (!isCancelled()) {
							isMeasureAllowed = true;
							requestLayout();
						}
					}
				}, getHeight(), getHeightCollapsed()).start();
			} else {
				setText(actualText);
				animator = ValueAnimatorWithData.ofInt(ANIMATION_DURATION, new UpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						setHeight((Integer) animation.getAnimatedValue());
					}
				}, getHeight(), getHeightExpanded()).start();
			}
		}
	}

	public boolean isExpanded() {
		return state == State.Expanded;
	}

	private void stopAnimator() {
		if (animator != null) {
			animator.cancel();
		}
	}

	@Override
	public Parcelable onSaveInstanceState() {
		final Bundle bundle = new Bundle();
		bundle.putParcelable("super", super.onSaveInstanceState());
		bundle.putSerializable("isExpanded", state == State.Expanded);
		return bundle;
	}

	@Override
	public void onRestoreInstanceState(Parcelable state) {
		final Bundle bundle = (Bundle) state;
		super.onRestoreInstanceState(bundle.getParcelable("super"));
		this.state = bundle.getBoolean("isExpanded") ? State.Expanded : State.Collapsed;
	}

	private enum State {
		Expanded, Collapsed
	}

	public interface Listener {
		void updateAppearance(ExpandableTextView expandableTextView, boolean isExpandAllowed);
	}
}
