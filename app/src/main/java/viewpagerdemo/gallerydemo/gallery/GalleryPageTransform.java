package viewpagerdemo.gallerydemo.gallery;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by Administrator on 2017/8/25.
 */

public class GalleryPageTransform implements ViewPager.PageTransformer{

//    private static final String TAG = GalleryPageTransform.class.getSimpleName();
//    final float SCALE_MAX = 0.8f;
//    final float ALPHA_MAX = 0.5f;
//
//    @Override
//    public void transformPage(View page, float position) {
//        if ((int) position < -1 || (int) position > 1) {
//            return;
//        }
//
//        float scale = (position < 0)
//                ? ((1 - SCALE_MAX) * position + 1)
//                : ((SCALE_MAX - 1) * position + 1);
//        float alpha = (position < 0)
//                ? ((1 - ALPHA_MAX) * position + 1)
//                : ((ALPHA_MAX - 1) * position + 1);
//        if (position < 0) {
//            ViewCompat.setPivotX(page, page.getWidth());
//            ViewCompat.setPivotY(page, page.getHeight() / 2);
//        } else {
//            ViewCompat.setPivotX(page, 0);
//            ViewCompat.setPivotY(page, page.getHeight() / 2);
//        }
//        Log.w(TAG, "position: " + position + ",scale:" + scale);
//
//        ViewCompat.setScaleX(page, scale);
//        ViewCompat.setScaleY(page, scale);
//        ViewCompat.setAlpha(page, Math.abs(alpha));
//    }


    private static final float MIN_SCALE = 0.9f;
    private static final float MIN_ALPHA = 0.5f;

    private static float defaultScale = 0.9f;

    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
//          view.setAlpha(0);
            view.setScaleX(defaultScale);
            view.setScaleY(defaultScale);
        } else if (position <= 1) { // [-1,1]
            // Modify the default slide transition to shrink the page as well
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            float vertMargin = pageHeight * (1 - scaleFactor) / 2;
            float horzMargin = pageWidth * (1 - scaleFactor) / 2;
            if (position < 0) {
                view.setTranslationX(horzMargin - vertMargin / 2);
            } else {
                view.setTranslationX(-horzMargin + vertMargin / 2);
            }

            // Scale the page down (between MIN_SCALE and 1)
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

            // Fade the page relative to its size.
//          view.setAlpha(MIN_ALPHA +
//                  (scaleFactor - MIN_SCALE) /
//                          (1 - MIN_SCALE) * (1 - MIN_ALPHA));

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
//          view.setAlpha(0);
            view.setScaleX(defaultScale);
            view.setScaleY(defaultScale);
        }
    }

}
