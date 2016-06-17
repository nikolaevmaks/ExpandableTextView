import android.animation.Animator;
import android.animation.ValueAnimator;

public abstract class UpdateListener implements ValueAnimator.AnimatorUpdateListener, ValueAnimator.AnimatorListener {
	private boolean isCancelled;
	private boolean isEnded;

	@Override
	public void onAnimationUpdate(ValueAnimator animation) {
	}

	@Override
	public void onAnimationStart(Animator animation) {
	}

	@Override
	public void onAnimationEnd(Animator animation) {
		isEnded = true;
	}

	@Override
	public final void onAnimationCancel(Animator animation) {
		isCancelled = true;
	}

	@Override
	public void onAnimationRepeat(Animator animation) {
	}

	public final boolean isCancelled() {
		return isCancelled;
	}

	public final boolean isEnded() {
		return isEnded;
	}
}
