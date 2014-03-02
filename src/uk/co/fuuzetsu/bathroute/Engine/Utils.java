package uk.co.fuuzetsu.bathroute.Engine;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;

public class Utils {

    private Utils() {}

    public static Location makeLocation(final Double longitude,
                                        final Double latitude) {
        Location l = new Location("hardcoded");
        l.setLongitude(longitude);
        l.setLatitude(latitude);
        return l;
    }

    public static Boolean containsLocation(final Location l, final Location bl,
                                           final Location tr) {
        Double lg = l.getLongitude();
        Double lt = l.getLatitude();
        return lg >= bl.getLongitude() && lg <= tr.getLongitude()
            && lt >= bl.getLatitude() && lt <= tr.getLatitude();
    }


    /* We ‘borrow’ next two methods from the android developer pages */
    public static Bitmap decodeSampledBitmapFromResource
        (Resources res, int resId, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = Utils.calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                   && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

}
