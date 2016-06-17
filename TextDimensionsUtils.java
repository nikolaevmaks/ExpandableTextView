import android.graphics.Paint;

public class TextDimensionsUtils {

	public static float getTextWidth(String text, Paint paint) {
		return paint.measureText(text, 0, text.length());
	}
}
