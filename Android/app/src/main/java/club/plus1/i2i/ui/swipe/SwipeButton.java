package club.plus1.i2i.ui.swipe;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

import club.plus1.i2i.presenter.App;

public class SwipeButton {

    private String text;
    public int width;
    private int image;
    private int color;
    private int pos;
    private RectF clickRegion;
    private ClickListener clickListener;

    public SwipeButton(int width, String text, int image, int color, ClickListener clickListener) {
        this.width = width;
        this.text = text;
        this.image = image;
        this.color = color;
        this.clickListener = clickListener;
    }

    public boolean onClick(float x, float y){
        if (clickRegion != null && clickRegion.contains(x, y)){
            clickListener.onClick(pos);
            return true;
        }

        return false;
    }

    public void onDraw(Canvas canvas, RectF rectF, int pos){
        Paint paint = new Paint();

        // Draw background
        paint.setColor(color);
        canvas.drawRect(rectF, paint);

        // Draw Text
        if (text.length() > 0){
            paint.setColor(Color.WHITE);
            paint.setTextSize(rectF.width()/text.length());
        }

        Rect r = new Rect();
        float cHeight = rectF.height();
        float cWidth = rectF.width();
        paint.setTextAlign(Paint.Align.LEFT);
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = cHeight / 2f + r.height() / 2f - r.bottom;
        canvas.drawText(text, rectF.left + x, rectF.top + y, paint);

        // Draw icon
        if (image > 0 && android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Drawable icon = App.app.getResources().getDrawable(image, null);
            if (text.length() > 0){
                int icon_size = (int)(rectF.height() / 3);
                int icon_margin = icon_size / 5;
                int vertical_margin = (int)(rectF.bottom - rectF.top - icon_size)/2;
                icon.setBounds((int)rectF.left + icon_margin, (int)rectF.top + vertical_margin,
                        (int)rectF.left + icon_margin + icon_size, (int)rectF.bottom - vertical_margin);
            } else {
                int icon_size = (int)(rectF.height() / 2);
                int vertical_margin = (int)(rectF.bottom - rectF.top - icon_size)/2;
                int horizontal_margin = (int)(rectF.right - rectF.left - icon_size)/2;
                icon.setBounds((int)rectF.left + horizontal_margin, (int)rectF.top + vertical_margin,
                        (int)rectF.left + horizontal_margin + icon_size, (int)rectF.bottom - vertical_margin);
            }
            icon.draw(canvas);
        }

        clickRegion = rectF;
        this.pos = pos;
    }

    public interface ClickListener {
        void onClick(int pos);
    }
}
