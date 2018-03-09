package com.moreno.stephania.schoolroute.activities;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.moreno.stephania.schoolroute.R;

/**
 * Splash
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno.</a>
 */
public class SplashActivity extends AppCompatActivity {

    /** Constante que contiene duracion de la animacion **/
    private static final int ANIM_DURATION = 1000;

    /** Constante que retrasa la vista del splash **/
    private static final int SPLASH_DELAY = 1500;

    /** ImageView del logo **/
    private ImageView mLogoIv;

    /** Transition Name **/
    private String mTransitionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splah);

        // Inicializa el control ImageView
        mLogoIv = (ImageView)findViewById(R.id.ic_logo_iv);

        //Inicializa el string de la trransición
        mTransitionView = getString(R.string.transition_view);
        fadeAnimation(mLogoIv);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goListUser();
            }
        }, SPLASH_DELAY);
    }

    /**
     * Este metodo inicializa el evento de la actividad
     */
    private void goListUser() {
        Intent loginIntent = new Intent(SplashActivity.this, RouteActivity.class);

        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(this, mLogoIv, mTransitionView);
        startActivity(loginIntent, options.toBundle());
    }

    /**
     * Metodo que crea el objeto de animacion
     * animated and the initial value and final value
     *
     * @param view
     *         Target view
     * @param property
     *         Property to be animated
     * @param init
     *         Initial value
     * @param end
     *         Final value
     *
     * @return ObjectAnimator with the given animated property
     */
    @NonNull
    private ObjectAnimator createObjectAnimator(View view, String property, float init, float end) {
        ObjectAnimator scaleXAnimation = ObjectAnimator.ofFloat(view, property, init, end);
        scaleXAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleXAnimation.setDuration(ANIM_DURATION);
        return scaleXAnimation;
    }

    /**
     * Metodo que realiza la animacion
     */
    private void fadeAnimation(View view) {
        ObjectAnimator scaleXAnimation = createObjectAnimator(view, "scaleX", 5.0F, 1.0F);
        ObjectAnimator scaleYAnimation = createObjectAnimator(view, "scaleY", 5.0F, 1.0F);
        ObjectAnimator alphaAnimation = createObjectAnimator(view, "alpha", 0.0F, 1.0F);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleXAnimation).with(scaleYAnimation).with(alphaAnimation);
        animatorSet.start();
    }

}
