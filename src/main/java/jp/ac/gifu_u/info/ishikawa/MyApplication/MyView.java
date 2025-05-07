package jp.ac.gifu_u.info.ishikawa.MyApplication;

import android.content.Context;
import android.graphics.*;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class MyView extends View {

    // イベント発生時のX座標,Y座標を保存するための動的配列
    private ArrayList array_x, array_y;
    private ArrayList array_status;

    // コンストラクタ（ここではこの引数のものを追加）
    public MyView(Context context) {
        super(context);

        array_x     = new ArrayList();
        array_y     = new ArrayList();
        array_status= new ArrayList();
    }

    // ビューの描画を行うときに呼ばれるメソッド
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 線を引く
        // MyDrawLine(canvas);

        // 指で線を引く
        MyHandDraw(canvas);
    }

    // 線を引くメソッドを使う
    private void MyDrawLine(Canvas canvas)
    {
        // 画面の大きさを取得
        int C_Width = getWidth();
        int C_Height = getHeight();

        // 描画設定
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);

        // 画面の右上から左下にかけて線を引く
        canvas.drawLine(C_Width,0,0,C_Height,paint);
    }

    // タッチパネルを操作したときに呼ばれるメソッド
    @Override
    public  boolean onTouchEvent(MotionEvent event)
    {
        // 座標を取得
        int x = (int) event.getX();
        int y = (int) event.getY();

        // イベントに応じて動作を変更
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                array_x.add(x);  // 座標を配列に保存
                array_y.add(y);  // 線の描画はしない(false)
                array_status.add(false);
                invalidate(); // 画面を強制的に再描画
                break;
            case MotionEvent.ACTION_MOVE:
                array_x.add(x);  // 座標を配列に保存
                array_y.add(y);  // 線の描画をする(true)
                array_status.add(true);
                invalidate();
                break;
            case MotionEvent.ACTION_UP: // 画面を強制的に再描画
                // タッチパネルから離れたとき
            case MotionEvent.ACTION_POINTER_UP:
                array_x.add(x);  // 座標を配列に保存
                array_y.add(y);  // 線の描画をする(true)
                array_status.add(true);
                invalidate();// 画面を強制的に再描画
                break;
        }
        return true;
    }

    // 指で線を書くメソッド
    private void MyHandDraw(Canvas canvas) {
        // 画面の大きさを取得
        int C_Width = getWidth();
        int C_Height = getHeight();

        // 背景を白色で塗りつぶす(初期化)
        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.WHITE);
        canvas.drawRect(new Rect(0, 0, C_Width, C_Height), p);

        // 描画用の Paint オブジェクトを用意
        p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.BLUE);
        p.setStrokeWidth(10);

        // 配列内の座標を読み出して線（軌跡）を描画
        for (int i = 1; i < array_status.size(); i++) {

            // 描画するように(true)状態値が与えられているとき
            // 一度離してしてから次に押されるまでの移動分は描画しない
            if ((Boolean) array_status.get(i)) {
                // 開始点の終了点の座標の値を取得
                int x1 = (Integer) array_x.get(i - 1);
                int x2 = (Integer) array_x.get(i);
                int y1 = (Integer) array_y.get(i - 1);
                int y2 = (Integer) array_y.get(i);
                // 線を描画
                canvas.drawLine(x1, y1, x2, y2, p);
            }
        }
    }
}
