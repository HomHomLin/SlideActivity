package sa.lhh.com.slideactivity;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lhh.sa.utils.SlideActivityUtil;

/**
 * Created by linhonghong on 2015/7/13.
 */
public class Sample2Activity extends Activity {
    private static final String TAG = "Sample2Activity";
    private GestureDetector mGestureDetector;
    private int[] mDisplaySize;
//    private LinearLayout.LayoutParams mLayoutParams;
    private int verticalMinDistance = 20;
    private int minVelocity         = 0;
//    private static final int ACTION_MOVE_LEFT = 1;
//    private static final int ACTION_MOVE_RIGHT = 2;
    private static final int ACTION_MOVE = 3;
    private static final int ACTION_MOVE_UNKOWN = 0;

    private Window mWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample2);

        mWindow = getWindow();

        WindowManager.LayoutParams windowLayoutParams = mWindow.getAttributes(); // 获取对话框当前的参数值

        windowLayoutParams.flags = WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
        windowLayoutParams.gravity = Gravity.LEFT;
        mWindow.setAttributes(windowLayoutParams);

        mDisplaySize = SlideActivityUtil.getDisplaySize(this);

        mGestureDetector = new GestureDetector(new SlideGestureListener());

    }

    private void setActivityX(int x){
//        Display display = getWindowManager().getDefaultDisplay(); // 为获取屏幕宽、高
        WindowManager.LayoutParams windowLayoutParams = mWindow.getAttributes(); // 获取对话框当前的参数值
        windowLayoutParams.x = x + windowLayoutParams.x;
//        window.setBackgroundDrawable(new ColorDrawable(0xb0000000));//半透明,Color.argb(0, 0, 0, 0)
//        windowLayoutParams.alpha = 0.0f;// 设置透明度
//        p.alpha = 1.0f;      //设置本身透明度
//        windowLayoutParams.dimAmount = 0.0f;      //设置黑暗度
        mWindow.setAttributes(windowLayoutParams);
    }

    private void doGestureUp(){
        WindowManager.LayoutParams windowLayoutParams = mWindow.getAttributes(); // 获取对话框当前的参数值
        if(windowLayoutParams.x >= SlideActivityUtil.getDisplayActionStartX(this, 0.3f)){
            this.finish();
        }else{
            setActivityX(-windowLayoutParams.x);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() != MotionEvent.ACTION_UP){
            mGestureDetector.onTouchEvent(event);
        }else{
            doGestureUp();
        }
        return super.onTouchEvent(event);
    }

    class SlideGestureListener extends GestureDetector.SimpleOnGestureListener {
        private int mLastDisx = 0;


        public boolean onDown(MotionEvent e) {
            // TODO Auto-generated method stub
//            Log.i(TAG,"onDown");
            return false;
        }

        public void onShowPress(MotionEvent e) {
            // TODO Auto-generated method stub
//            Log.i(TAG,"onShowPress");

        }

        public boolean onSingleTapUp(MotionEvent e) {
            // TODO Auto-generated method stub
//            Log.i(TAG,"onSingleTapUp");
            return false;
        }

        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            // TODO Auto-generated method stub
            if(e1.getX() < SlideActivityUtil.getDisplayActionStartX(Sample2Activity.this) && Math.abs(distanceX) > 5){
                setActivityX(-(int)(distanceX * 0.82));
            }
//            if(e1.getX() < SlideActivityUtil.getDisplayActionStartX(Sample2Activity.this)){
//                Log.i(TAG,String.valueOf((int)(e2.getX() - e1.getX())));
//                Log.i(TAG,"onScroll: e1: (x)" + e1.getX()+ ", e2: (x)" + e2.getX() + ",disx : " + distanceX );
//                setActivityX((int)(e2.getX() - e1.getX()));
//            }
            return false;
        }

        public void onLongPress(MotionEvent e) {
            // TODO Auto-generated method stub
//            Log.i(TAG,"onLongPress");

        }

        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            // TODO Auto-generated method stub
//            if (e1.getX() - e2.getX() > verticalMinDistance && Math.abs(velocityX) > minVelocity) {
//                mActionEvent = ACTION_MOVE_LEFT;
////                setActivityX((int)velocityX);
//                // 切换Activity
//                // Intent intent = new Intent(ViewSnsActivity.this, UpdateStatusActivity.class);
//                // startActivity(intent);
////                Toast.makeText(this, "向左手势", Toast.LENGTH_SHORT).show();
//            } else if (e2.getX() - e1.getX() > verticalMinDistance && Math.abs(velocityX) > minVelocity) {
////                setActivityX((int)(e2.getX() - e1.getX()));
//                // 切换Activity
//                // Intent intent = new Intent(ViewSnsActivity.this, UpdateStatusActivity.class);
//                // startActivity(intent);
////                Toast.makeText(this, "向右手势", Toast.LENGTH_SHORT).show();
//            }
            Log.i(TAG,"onFling");
//            Log.i(TAG,"onFling: e1: (x)" + e1.getX() + "(y)" + e1.getY() + ", e2: (x)" + e2.getX() + "(y)" + e2.getY() + ",velox : " + velocityX + " ,veloy : " + velocityY);
            return false;
        }
    }
}
