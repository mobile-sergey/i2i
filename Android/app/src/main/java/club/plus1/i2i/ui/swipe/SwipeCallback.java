package club.plus1.i2i.ui.swipe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

public class SwipeCallback extends ItemTouchHelper.SimpleCallback {

    public List<SwipeButton> rightButtons;
    public List<SwipeButton> leftButtons;

    enum ButtonsState {
        GONE,
        LEFT_VISIBLE,
        RIGHT_VISIBLE
    }

    private RecyclerView recyclerView;
    private List<SwipeButton> buttons;
    private GestureDetector gestureDetector;
    private int swipedPos = -1;
    private Map<Integer, List<SwipeButton>> buttonsBuffer;
    private Queue<Integer> recoverQueue;
    private ButtonsState buttonShowedState = ButtonsState.GONE;

    // Конструктор готовит механизм к работе
    @SuppressLint("ClickableViewAccessibility")
    public SwipeCallback(Context context, RecyclerView recyclerView) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.recyclerView = recyclerView;
        this.buttons = new ArrayList<>();
        this.gestureDetector = new GestureDetector(context, gestureListener);
        this.recyclerView.setOnTouchListener(onTouchListener);
        buttonsBuffer = new HashMap<>();
        recoverQueue = new LinkedList<Integer>(){
            @Override
            public boolean add(Integer o) {
                if (contains(o))
                    return false;
                else
                    return super.add(o);
            }
        };

        attachSwipe();
    }

    // Подключение свайпа к списку RecyclerView
    public void attachSwipe(){
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(this);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    // Определяет на какую свайп-кнопку нажали
    private GestureDetector.SimpleOnGestureListener gestureListener = new GestureDetector.SimpleOnGestureListener(){
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
             for (SwipeButton button : buttons) {
                if (button.onClick(e.getX(), e.getY()))
                    break;
            }
            return true;
        }
    };

    // При свайпе подключается обработчик свайпа только для того элемента, на котором он находится
    @SuppressLint("ClickableViewAccessibility")
    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent e) {
            if (swipedPos < 0) return false;
            Point point = new Point((int) e.getRawX(), (int) e.getRawY());

            RecyclerView.ViewHolder swipedViewHolder = recyclerView.findViewHolderForAdapterPosition(swipedPos);
            if (swipedViewHolder == null)
                return false;
            View swipedItem = swipedViewHolder.itemView;
            Rect rect = new Rect();
            swipedItem.getGlobalVisibleRect(rect);

            if (e.getAction() == MotionEvent.ACTION_DOWN || e.getAction() == MotionEvent.ACTION_UP ||e.getAction() == MotionEvent.ACTION_MOVE) {
                if (rect.top < point.y && rect.bottom > point.y)
                    gestureDetector.onTouchEvent(e);
                else {
                    recoverQueue.add(swipedPos);
                    swipedPos = -1;
                    recoverSwipedItem();
                }
            }
            return false;
        }
    };

    // Запрещен перенос элементов списка вверх/вниз
    @Override
    public boolean onMove(@NotNull RecyclerView recyclerView, @NotNull RecyclerView.ViewHolder viewHolder,
                          @NotNull RecyclerView.ViewHolder target) {
        return false;
    }

    // При окончании свайпа сохраняем кнопки доступные к нажатию и очищаем буфер кнопок
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int pos = viewHolder.getAdapterPosition();

        if (swipedPos != pos)
            recoverQueue.add(swipedPos);

        swipedPos = pos;

        if(direction == ItemTouchHelper.LEFT){
            if (buttonsBuffer.containsKey(swipedPos))
                buttons = buttonsBuffer.get(swipedPos);
            else
                buttons.clear();
        } else if(direction == ItemTouchHelper.RIGHT) {
            if (buttonsBuffer.containsKey(-swipedPos))
                buttons = buttonsBuffer.get(-swipedPos);
            else
                buttons.clear();
        }

        buttonsBuffer.clear();
        recoverSwipedItem();
    }

    // В зависимости от смещения свайпом готовим кнопки к показу
    @Override
    public void onChildDraw(@NotNull Canvas canvas, @NotNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                            float dX, float dY, int actionState, boolean isCurrentlyActive) {
        int pos = viewHolder.getAdapterPosition();
        float translationX = dX;

        if (pos < 0){
            swipedPos = pos;
            return;
        }

        // При смещении влево - справа рисуются кнопки справа
        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
            if(dX != 0) {
                List<SwipeButton> buffer;

                if (dX < 0){ // dX < 0 - сдвиг влево, надо показать правые кнопки
                    buttonShowedState = ButtonsState.RIGHT_VISIBLE;
                    if (buttonsBuffer.containsKey(pos)){
                        buffer = buttonsBuffer.get(pos);
                    } else {
                        buffer = rightButtons;
                        buttonsBuffer.put(pos, buffer);
                    }
                } else { // dX > 0 - сдвиг вправо, надо показать левые кнопки
                    buttonShowedState  = ButtonsState.LEFT_VISIBLE;
                    if (buttonsBuffer.containsKey(-pos)){
                        buffer = buttonsBuffer.get(-pos);
                    } else {
                        buffer = leftButtons;
                        buttonsBuffer.put(-pos, buffer);
                    }
                }

                assert buffer != null;
                translationX = dX * buttonsWidth(buffer) / viewHolder.itemView.getWidth();
                drawButtons(canvas, viewHolder.itemView, buffer, pos, translationX);
            }
        }

        super.onChildDraw(canvas, recyclerView, viewHolder, translationX, dY, actionState, isCurrentlyActive);
    }

    // Прорисовка кнопок в зависимости от направления смещения
    private void drawButtons(Canvas canvas, View itemView, List<SwipeButton> buffer, int pos, float dX){
        if (buttonShowedState == ButtonsState.RIGHT_VISIBLE) {
            float right = itemView.getRight();
            for (SwipeButton button : buffer) {
                float left = right - button.width;
                RectF rectF = new RectF(left, itemView.getTop(), right, itemView.getBottom());
                button.onDraw(canvas, rectF, pos);
                right = left;
            }
        } else if (buttonShowedState == ButtonsState.LEFT_VISIBLE) {
            float left = itemView.getLeft();
            for (SwipeButton button : buffer) {
                float right = left + button.width;
                RectF rectF = new RectF(left, itemView.getTop(), right, itemView.getBottom());
                button.onDraw(canvas, rectF, pos);
                left = right;
            }
        }
    }

    // Вычисление ширины кнопок
    private int buttonsWidth(List<SwipeButton> list){
        int width = 0;
        for (SwipeButton button: list) {
            width += button.width;
        }
        return width;
    }

    // Восстановление положения прежних строк (когда одна строка выдвигается, то другие задвигаются)
    private synchronized void recoverSwipedItem(){
        while (!recoverQueue.isEmpty()){
            Integer position = recoverQueue.poll();
            int pos = (position != null ? position : -1);
            if (pos > -1) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    Objects.requireNonNull(recyclerView.getAdapter()).notifyItemChanged(pos);
                }
            }
        }
        buttonShowedState = ButtonsState.GONE;
    }
}
