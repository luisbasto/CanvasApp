package nicolas.aplicaionuno.canvas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ConstraintLayout dibujo = (ConstraintLayout) findViewById(R.id.dibujar);
        Vista vista = new Vista(dibujo.getContext());
        setContentView(R.layout.activity_main);

        View view2= (View) findViewById(R.id.view2);

    }



    class Vista extends View {
        float x=50;
        float y=50;
        String accion="nada";
        Path path = new Path();
        Drawable imagen;

        public Vista (Context context){
            super(context);
            imagen =  context.getResources().getDrawable(R.drawable.test);
        }

        public void onDraw(Canvas canvas){
            canvas.drawColor(Color.GREEN);

            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(4);
            paint.setColor(Color.argb(250, 110, 70, 200));

           int ancho = imagen.getIntrinsicWidth();
           int alto = imagen.getIntrinsicHeight();
           imagen.setBounds(0,0,ancho, alto);
           imagen.draw(canvas);

            if (accion == "down") {
                path.moveTo(x, y);
            }
            if(accion== "move"){
                path.lineTo(x,y);
            }
            canvas.drawPath(path,paint);
        }

        public boolean onTouchEvent(MotionEvent evento){
            int axion = evento.getAction();
            x= evento.getX();
            y = evento.getY();

            if(axion ==MotionEvent.ACTION_DOWN){
                accion = "down";
            }
            if(axion == MotionEvent.ACTION_MOVE){
                accion = "move";
            }
            invalidate();
            return true;

        }
    }
}
