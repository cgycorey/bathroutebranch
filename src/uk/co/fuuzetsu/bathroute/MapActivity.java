package uk.co.fuuzetsu.bathroute;

import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.location.Location;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import java.lang.Math;
import java.security.PublicKey;
import javax.crypto.spec.IvParameterSpec;
import uk.co.fuuzetsu.bathroute.Engine.Map;
import uk.co.fuuzetsu.bathroute.Engine.Utils;

public class MapActivity extends Activity {
	
	Matrix mat = new Matrix();
    private Map m;
    private double lat;
    private double longi;
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* We set the co-ordinates coresponding to the bottom-left corner
           and top-right corner of our image. We can work out the rest that
           way.
        */
        lat = -2.32798;
        longi = 51.37989;
       
        //this simple encapsulates lat and long in object
        final Location bl = Utils.makeLocation(-2.33556, 51.37515);
        final Location tr = Utils.makeLocation(-2.31754, 51.38239);
      
       
        setContentView(R.layout.map);
        final ImageView iv = (ImageView) findViewById(R.id.map_view);
        Display d = getWindowManager().getDefaultDisplay();
        final Resources r = getResources();
        this.m = new Map(r, bl, tr, d, R.drawable.map, R.drawable.no_map);
      
        /* Some dummy locations */
        final Location stv = Utils.makeLocation(-2.32446, 51.37756);
        final Location lib = Utils.makeLocation(-2.32798, 51.37989);

        
        iv.setImageBitmap(this.m.getBitmap(lib,mat));
        Log.v("MapActivity", "Started MapActivity!");
      //  final double Height = iv.getHeight();
       // final double Width = iv.getWidth();
    
        //**have not divided by width, height since canvas has not been defined
        final double scaleX = (m.getTopRight().getLongitude() - m.getBottomLeft().getLongitude()); // /width 
        final double scaleY = (m.getTopRight().getLatitude() - m.getBottomLeft().getLatitude()); //   /height
        
        Button button = (Button)findViewById(R.id.zoom_button2);
        Button button2 = (Button)findViewById(R.id.button1);
        
        
          
        //user clicks UP
        button.setOnClickListener(new OnClickListener() {
			
			//user clicks button
			public void onClick(View v) {
		    //testing whether button works
			
			Bitmap imageBitmap = m.getBitmap(Utils.makeLocation(lat, longi),mat);
			Canvas canvas = new Canvas(imageBitmap);
			float scale = getResources().getDisplayMetrics().density;
			Paint p = new Paint();
			p.setColor(Color.BLACK);
			
			p.setTextSize(24*scale);
			canvas.drawText("Path", iv.getWidth()/2,iv.getHeight()/3,p);
			p.setStrokeWidth(3F);
			canvas.drawLine(iv.getWidth()/3, iv.getHeight()/3, iv.getWidth()/2, iv.getHeight()/2, p);
			canvas.drawLine(iv.getWidth()/2+10, iv.getHeight()/2+40, iv.getWidth()-40, iv.getHeight()-40, p);
			
			
			
			
			
			
			
		Toast.makeText(getApplicationContext(),(scaleX/iv.getWidth())+"= ScaleX", 1000).show();
			
			iv.setImageBitmap(imageBitmap);
		    // iv.setImageBitmap(m.drawLineToBitmap());
	        longi = longi+0.0001;
	        iv.invalidate();
	       
			
			}
		});
        
        //user clicks down
        button2.setOnClickListener(new OnClickListener() {
			
			//user clicks button
			public void onClick(View v) {
		  
		    iv.setImageBitmap( m.getBitmap(Utils.makeLocation(lat, longi),mat) );
	        longi = longi-0.0001;
	        iv.invalidate();
	       
			
			}
		});
        
        
        //touch event
        iv.setOnTouchListener(new OnTouchListener(){

        	@Override
        	public boolean onTouch(View v, MotionEvent event)
        	{
        		float touchX = event.getX();
        		float touchY = event.getY();
        		
        		
        		// get pointer index from the event object
                int pointerIndex = event.getActionIndex();

                // get pointer ID
                int pointerId = event.getPointerId(pointerIndex);

                // get masked (not specific to a pointer) action
                int maskedAction = event.getActionMasked();
                
                switch (maskedAction) {

                case MotionEvent.ACTION_DOWN: 
                {
                	
                	Toast.makeText(getApplicationContext(),m.getBottomLeft().getLongitude()+(touchY*scaleY)/iv.getHeight()+" - "+m.getBottomLeft().getLatitude()+touchX*(scaleX/iv.getWidth()) , 1000).show();
                    //this will go OUT OF BOUNDS because result is slightly off - CHECK THIS PART
                	iv.setImageBitmap(m.getBitmap(Utils.makeLocation(m.getBottomLeft().getLongitude()+touchY*(scaleY/iv.getHeight()), m.getBottomLeft().getLatitude()+touchX*scaleX/iv.getWidth()),mat));
                
                	//works
                	//TO CHECK WHETHER TOUCH WORKS ----------iv.setImageBitmap(m.getBitmap(stv,mat));
                	iv.invalidate();
                }
                case MotionEvent.ACTION_POINTER_DOWN: {
                  // TODO use data
                	break;
                }
                case MotionEvent.ACTION_MOVE: { // a pointer was moved
                  // TODO use data
                	//iv.setImageBitmap(m.getBitmap(Utils.makeLocation(m.getBottomLeft().getLongitude()+touchY*(scaleY/iv.getHeight()), m.getBottomLeft().getLatitude()+touchX*scaleX/iv.getWidth()),mat));
                	iv.setImageBitmap(m.getBitmap(stv,mat));
                	iv.invalidate();
                }
                case MotionEvent.ACTION_UP: 
                {
                	//iv.setImageBitmap(m.getBitmap(Utils.makeLocation(m.getBottomLeft().getLongitude()+touchY*(scaleY/iv.getHeight()), m.getBottomLeft().getLatitude()+touchX*scaleX/iv.getWidth()),mat));
                	iv.setImageBitmap(m.getBitmap(stv,mat));
                	iv.invalidate();
                }
                case MotionEvent.ACTION_POINTER_UP: 
                {
                	//iv.setImageBitmap(m.getBitmap(Utils.makeLocation(m.getBottomLeft().getLongitude()+touchY*(scaleY/iv.getHeight()), m.getBottomLeft().getLatitude()+touchX*scaleX/iv.getWidth()),mat));
                	iv.setImageBitmap(m.getBitmap(stv,mat));
                }
                case MotionEvent.ACTION_CANCEL: {
                  // TODO use data
                  break;
                }
                }
                iv.invalidate();
        	    return true;
        	}});
        
        		
        //draw
       
        
        	
        
           
        
    }
    
   
    
    
}
