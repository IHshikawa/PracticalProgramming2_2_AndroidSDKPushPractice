package jp.ac.gifu_u.info.ishikawa.MyApplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MyView extends View {

    // コンストラクタ（ここではこの引数のものを追加）
    public MyView(Context context) {
        super(context);
    }

    // ビューの描画を行うときに呼ばれるメソッド
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 線を引く
        MyDrawLine(canvas);

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


}
