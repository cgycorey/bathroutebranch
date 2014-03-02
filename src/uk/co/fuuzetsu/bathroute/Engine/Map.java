package uk.co.fuuzetsu.bathroute.Engine;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.R.menu;
import android.content.res.Resources;
import android.location.Location;
import android.view.Display;

import java.lang.Math;

import uk.co.fuuzetsu.bathroute.Engine.Utils;

public class Map {

    private Resources res;
    private Bitmap bmap;
    private Location bl, tr;
    private Display disp;
    private int oobImg;
    private int crpx,crpy;
    float curScale = 1F;
    float curRotate = 0F;
    float curSkewX = 0F;
    float curSkewY = 0F;
    
    public int getcropx()
    {
    	return crpx;
    }
    public int getcropy()
    {
    	return crpy;
    }
    
    public Map(Resources res, final Location bl, final Location tr,
               final Display disp, final int imageId, final int oobImg) {
        this.res = res;
        this.bl = bl;
        this.tr = tr;
        this.disp = disp;

        Point ds = new Point();
        disp.getSize(ds);

        this.bmap = Utils.decodeSampledBitmapFromResource(this.res, imageId,
                                                          ds.x, ds.y);
        this.oobImg = oobImg;
    }
    public void setTopRight(Location tRight)
    {
    	this.tr=tRight;
    }
    public Location getTopRight()
    {
    	return this.tr;
    }
    public Location getBottomLeft()
    {
    	return this.bl;
    }
    public Bitmap getBitmap() {
        return this.bmap;
    }
     
    //return a new map based on new altitude
    
    //returns a new map based on new coordinates
    public Bitmap getBitmap(final Location l,Matrix m) {
    	 /* Canvas canvas = new Canvas(this.bmap);
		  // new antialised Paint
		  Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		  // text color - #3D3D3D
		  paint.setColor(Color.rgb(61, 61, 61));
		
		 
		 canvas.drawLine(0, 0, canvas.getWidth(), canvas.getHeight(), paint);*/
        Point ds = new Point();
        disp.getSize(ds);
    	m.postScale(curScale, curScale);
        m.postRotate(curRotate);
        m.postSkew(curSkewX, curSkewY);
        
         
//        canvas.setBitmap(bmap);
//        Paint paint = new Paint();
        if (this.containsLocation(l)) {
            Point p = this.locToMapPoint(l);
            Integer cropX = p.x - ds.x/2;
            Integer cropY = p.y - ds.y/2;

            int h = bmap.getHeight();
            int w = bmap.getWidth();

       //     canvas.drawLine(crpx, crpy, w, h, paint);
            cropX = cropX < 0 ? 0 : cropX;
            cropY = cropY < 0 ? 0 : cropY;
            crpx=cropX;
            crpy=cropY;
            
            //modify bmap
            //canvas.setBitmap(bmap);
    
            return Bitmap.createBitmap(this.bmap, cropX, cropY, ds.x, ds.y,m,true);

        } else {
            return Utils.decodeSampledBitmapFromResource(this.res,
                                                         oobImg, ds.x, ds.y);
        }
    }
    public Bitmap drawLineToBitmap() {
    		  
    		 
    		  Canvas canvas = new Canvas(this.bmap);
    		  // new antialised Paint
    		  Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    		  // text color - #3D3D3D
    		  paint.setColor(Color.rgb(61, 61, 61));
    		
    		 
    		 canvas.drawLine(0, 0, 10, 10, paint);
    		 
    		  return this.bmap;
    }
    
    public Point locToMapPoint(final Location l) {
        final Integer h = this.bmap.getHeight();
        final Integer w = this.bmap.getWidth();

        /* The size of our map in longitude and latitude interval */
        final Location tl = Utils.makeLocation(this.bl.getLongitude(), this.tr.getLatitude());

        final Integer newH = (int)
            (h.doubleValue() - h.doubleValue() * ((l.getLatitude() - this.bl.getLatitude())
                                            / (this.tr.getLatitude() - this.bl.getLatitude())));

        final Integer newW = (int)
            (w.doubleValue() * ((l.getLongitude() - this.bl.getLongitude())
                             / (this.tr.getLongitude() - this.bl.getLongitude())));

        return new Point(newW, newH);

    }

    public Boolean containsLocation(final Location l) {
        return Utils.containsLocation(l, this.bl, this.tr);
    }
}
