import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;

public class ValueAnimatorWithData {

	private ValueAnimator valueAnimator;
	private Object data;
	private UpdateListener listener;

	private ValueAnimatorWithData() {}

	public static ValueAnimatorWithData ofInt(int... values) {
		final ValueAnimatorWithData valueAnimatorWithData = new ValueAnimatorWithData();
		valueAnimatorWithData.valueAnimator = ValueAnimator.ofInt(values);
		return valueAnimatorWithData;
	}

	public static ValueAnimatorWithData ofInt(long duration, int... values) {
		return ValueAnimatorWithData.ofInt(duration, null, values);
	}

	public static ValueAnimatorWithData ofInt(long duration, UpdateListener updateListener, int... values) {
		return ValueAnimatorWithData.ofInt(duration, null, updateListener, values);
	}

	public static ValueAnimatorWithData ofInt(long duration, Object data, UpdateListener updateListener, int... values) {
		final ValueAnimatorWithData valueAnimatorWithData = ValueAnimatorWithData.ofInt(values);
		valueAnimatorWithData.set(duration, data, updateListener);
		return valueAnimatorWithData;
	}

	public static ValueAnimatorWithData ofInt(long startDelay, long duration, Object data, UpdateListener updateListener, int... values) {
		final ValueAnimatorWithData valueAnimatorWithData = ValueAnimatorWithData.ofInt(values);
		valueAnimatorWithData.set(startDelay, duration, data, updateListener);
		return valueAnimatorWithData;
	}

	public static ValueAnimatorWithData ofInt(long duration, int repeatCount, int repeatMode, Object data, UpdateListener updateListener, int... values) {
		final ValueAnimatorWithData valueAnimatorWithData = ValueAnimatorWithData.ofInt(values);
		valueAnimatorWithData.set(duration, repeatCount, repeatMode, data, updateListener);
		return valueAnimatorWithData;
	}

	public static ValueAnimatorWithData ofInt(long startDelay, long duration, int repeatCount, int repeatMode, Object data, UpdateListener updateListener, int... values) {
		final ValueAnimatorWithData valueAnimatorWithData = ValueAnimatorWithData.ofInt(values);
		valueAnimatorWithData.set(startDelay, duration, repeatCount, repeatMode, data, updateListener);
		return valueAnimatorWithData;
	}




	public static ValueAnimatorWithData ofFloat(float... values) {
		final ValueAnimatorWithData valueAnimatorWithData = new ValueAnimatorWithData();
		valueAnimatorWithData.valueAnimator = ValueAnimator.ofFloat(values);
		return valueAnimatorWithData;
	}

	public static ValueAnimatorWithData ofFloat(long duration, float... values) {
		return ValueAnimatorWithData.ofFloat(duration, null, values);
	}

	public static ValueAnimatorWithData ofFloat(long duration, UpdateListener updateListener, float... values) {
		return ValueAnimatorWithData.ofFloat(duration, null, updateListener, values);
	}

	public static ValueAnimatorWithData ofFloat(long duration, Object data, UpdateListener updateListener, float... values) {
		final ValueAnimatorWithData valueAnimatorWithData = ValueAnimatorWithData.ofFloat(values);
		valueAnimatorWithData.set(duration, data, updateListener);
		return valueAnimatorWithData;
	}

	public static ValueAnimatorWithData ofFloat(long duration, int repeatCount, int repeatMode, Object data, UpdateListener updateListener, float... values) {
		final ValueAnimatorWithData valueAnimatorWithData = ValueAnimatorWithData.ofFloat(values);
		valueAnimatorWithData.set(duration, repeatCount, repeatMode, data, updateListener);
		return valueAnimatorWithData;
	}

	public static ValueAnimatorWithData ofFloat(long startDelay, long duration, Object data, UpdateListener updateListener, float... values) {
		final ValueAnimatorWithData valueAnimatorWithData = ValueAnimatorWithData.ofFloat(values);
		valueAnimatorWithData.set(startDelay, duration, data, updateListener);
		return valueAnimatorWithData;
	}

	public static ValueAnimatorWithData ofFloat(long startDelay, long duration, int repeatCount, int repeatMode, Object data, UpdateListener updateListener, float... values) {
		final ValueAnimatorWithData valueAnimatorWithData = ValueAnimatorWithData.ofFloat(values);
		valueAnimatorWithData.set(startDelay, duration, repeatCount, repeatMode, data, updateListener);
		return valueAnimatorWithData;
	}



	public ValueAnimatorWithData setListener(UpdateListener updateListener) {
		valueAnimator.addUpdateListener(updateListener);
		valueAnimator.addListener(updateListener);
		return this;
	}

	public ValueAnimatorWithData setDuration(long duration) {
		valueAnimator.setDuration(duration);
		return this;
	}

	public ValueAnimatorWithData setRepeatCount(int value) {
		valueAnimator.setRepeatCount(value);
		return this;
	}

	public ValueAnimatorWithData setRepeatMode(int value) {
		valueAnimator.setRepeatMode(value);
		return this;
	}

	public ValueAnimatorWithData setStartDelay(long startDelay) {
		valueAnimator.setStartDelay(startDelay);
		return this;
	}

	private ValueAnimatorWithData set(long startDelay, long duration, int repeatCount, int repeatMode, Object data, UpdateListener updateListener) {
		set(startDelay, duration, data, updateListener);
		valueAnimator.setRepeatCount(repeatCount);
		valueAnimator.setRepeatMode(repeatMode);
		return this;
	}

	private ValueAnimatorWithData set(long duration, int repeatCount, int repeatMode, Object data, UpdateListener updateListener) {
		set(duration, data, updateListener);
		valueAnimator.setRepeatCount(repeatCount);
		valueAnimator.setRepeatMode(repeatMode);
		return this;
	}

	private ValueAnimatorWithData set(long startDelay, long duration, Object data, UpdateListener updateListener) {
		set(duration, data, updateListener);
		valueAnimator.setStartDelay(startDelay);
		return this;
	}

	private ValueAnimatorWithData set(long duration, Object data, UpdateListener updateListener) {
		valueAnimator.setDuration(duration);
		if (updateListener != null) {
			valueAnimator.addUpdateListener(updateListener);
			valueAnimator.addListener(updateListener);
		}
		listener = updateListener;
		this.data = data;
		return this;
	}

	public static ValueAnimatorWithData waitAnimator(long duration) {
		final ValueAnimatorWithData valueAnimatorWithData = ValueAnimatorWithData.ofInt(duration, 0, 1);
		return valueAnimatorWithData;
	}

	public static ValueAnimatorWithData waitAnimator(long duration, UpdateListener updateListener) {
		final ValueAnimatorWithData valueAnimatorWithData = ValueAnimatorWithData.ofInt(duration, updateListener, 0, 1);
		return valueAnimatorWithData;
	}


	public ValueAnimatorWithData start() {
		valueAnimator.start();
		return this;
	}

	public void cancel() {
		valueAnimator.cancel();
	}

	public void end() {
		valueAnimator.end();
	}

	public boolean isStarted() {
		return valueAnimator.isStarted();
	}

	public boolean isStartedWithData(Object data) {
		return valueAnimator.isStarted() && data.equals(getData());
	}

	public ValueAnimatorWithData setInterpolator(TimeInterpolator value) {
		valueAnimator.setInterpolator(value);
		return this;
	}

	public ValueAnimator getAnimator() {
		return valueAnimator;
	}

	public ValueAnimatorWithData setCurrentPlayTime(long playTime) {
		valueAnimator.setCurrentPlayTime(playTime);
		return this;
	}

	public long getCurrentPlayTime() {
		return valueAnimator.getCurrentPlayTime();
	}

	public ValueAnimatorWithData setData(Object data) {
		this.data = data;
		return this;
	}

	public Object getData() {
		return data;
	}

	public UpdateListener getUpdateListener() {
		return listener;
	}
}
