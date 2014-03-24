package uk.co.fuuzetsu.bathroute;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
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
        longi=51.37989;
       
        
        final Location bl = Utils.makeLocation(-2.33556, 51.37515);
        final Location tr = Utils.makeLocation(-2.31754, 51.38239);
      
        setContentView(R.layout.map);
        final ImageView iv = (ImageView) findViewById(R.id.map_view);
         Display d = getWindowManager().getDefaultDisplay();
       
        final Resources r = getResources();

        this.m = new Map(r, bl, tr, d, R.drawable.map, R.drawable.no_map);
        float Height = m.getBitmap().getHeight();
        float Width = m.getBitmap().getWidth();
        final double scaleX = (-2.31754 - (-2.33556))/Width; 
        final double scaleY = (51.38239 - 51.37515)/Height;
        /* Some dummy locations */
        final Location stv = Utils.makeLocation(-2.32446, 51.37756);
        final Location lib = Utils.makeLocation(-2.32798, 51.37989);

        
        iv.setImageBitmap(this.m.getBitmap(lib,mat));
        Log.v("MapActivity", "Started MapActivity!");
        
        Button button = (Button)findViewById(R.id.zoom_button2);
        Button button2 = (Button)findViewById(R.id.button1);
        //user clicks UP
        button.setOnClickListener(new OnClickListener() {
			
			//user clicks button
			@Override
			public void onClick(View v) {
		    //testing whether button works
			//Toast.makeText(getApplicationContext(),m.getBitmap().+"" , 1000).show();
		     m.setCurScale(0.05);
		     iv.setImageBitmap(m.getBitmap(Utils.makeLocation(lat, longi),mat));
		  //  iv.setImageBitmap(m.drawLineToBitmap());
	       // longi = longi+0.0001;
	        iv.invalidate();
	       
			
			}
		});
        //user clicks down
        button2.setOnClickListener(new OnClickListener() {
			
			//user clicks button
			@Override
			public void onClick(View v) {
		    //testing whether button works
		//	Toast.makeText(getApplicationContext()," " , 1000).show();
		//	//	 m.setCurScale(-0.05);
		 //   iv.setImageBitmap(m.getBitmap(Utils.makeLocation(lat, longi),mat));
	       // longi = longi-0.0001;
				Toast.makeText(getApplicationContext()," ", 1000).show();
				
			    
				
				
				
				
				//	iv.setImageBitmap(
					//		m.getBitmap(Utils.makeLocation(lat, longi),mat)  );
					Bitmap imageBitmap = m.getBitmap(Utils.makeLocation(lat, longi),mat);
					Canvas canvas = new Canvas(imageBitmap);
					float scale = getResources().getDisplayMetrics().density;
					Paint p = new Paint();
					p.setColor(Color.BLUE);
					p.setTextSize(24*scale);
					
					canvas.drawLine(0, 0, 200,  500, p);
					//canvas.drawText("Hello", iv.getWidth()/2,iv.getHeight()/2,p);
					
					iv.setImageBitmap(imageBitmap);
				    // iv.setImageBitmap(m.drawLineToBitmap());
			        //longi = longi+0.0001;
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
                	iv.setImageBitmap(m.getBitmap(
							Utils.makeLocation(-2.33556
									+ ((3279 / 720.0) * event.getX() * 0.01802)
									/ 3279.0, 51.37515 + ((2121 / 1038.0)
									* event.getY() * 0.00724) / 2121.0), mat));
                	
                	
                	iv.invalidate();
                }
                case MotionEvent.ACTION_POINTER_DOWN: {
                	iv.setImageBitmap(m.getBitmap(
							Utils.makeLocation(-2.33556
									+ ((3279 / 720.0) * event.getX() * 0.01802)
									/ 3279.0, 51.37515 + ((2121 / 1038.0)
									* event.getY() * 0.00724) / 2121.0), mat));
                }
                case MotionEvent.ACTION_MOVE: { // a pointer was moved
                  // TODO use data
                	Toast.makeText(getApplicationContext(),touchX*scaleX+" "+touchY*scaleY , 1000).show();
                	iv.setImageBitmap(m.getBitmap(
							Utils.makeLocation(-2.33556
									+ ((3279 / 720.0) * event.getX() * 0.01802)
									/ 3279.0, 51.37515 + ((2121 / 1038.0)
									* event.getY() * 0.00724) / 2121.0), mat));
                    iv.invalidate();
                }
                case MotionEvent.ACTION_UP: 
                {
                	Toast.makeText(getApplicationContext(),touchX*scaleX+" "+touchY*scaleY , 1000).show();
                	iv.setImageBitmap(m.getBitmap(
							Utils.makeLocation(-2.33556
									+ ((3279 / 720.0) * event.getX() * 0.01802)
									/ 3279.0, 51.37515 + ((2121 / 1038.0)
									* event.getY() * 0.00724) / 2121.0), mat));
                    iv.invalidate();
                }
                case MotionEvent.ACTION_POINTER_UP: 
                {
                	iv.setImageBitmap(m.getBitmap(
							Utils.makeLocation(-2.33556
									+ ((3279 / 720.0) * event.getX() * 0.01802)
									/ 3279.0, 51.37515 + ((2121 / 1038.0)
									* event.getY() * 0.00724) / 2121.0), mat));
                }
                case MotionEvent.ACTION_CANCEL: {
                	iv.setImageBitmap(m.getBitmap(
							Utils.makeLocation(-2.33556
									+ ((3279 / 720.0) * event.getX() * 0.01802)
									/ 3279.0, 51.37515 + ((2121 / 1038.0)
									* event.getY() * 0.00724) / 2121.0), mat));
                }
                }
                //invalidate();
        	    return true;
        	}});
        
        		
        //draw
       
        
        	
        
           
        
    }
    
   
    
    
}
