package com.example.rafaelanastacioalves.pokedex.ui.pokemon_listing

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.example.rafaelanastacioalves.pokedex.R

/**
 * Created by rafaelanastacioalves on 9/17/16.
 * Adapted from http://frogermcs.github.io/instamaterial-recyclerview-animations-done-right/#disqus_thread
 */
class AnimationIntroAnimator : DefaultItemAnimator() {
    private val lastAddAnimatedItem = 0
    override fun animateAdd(holder: RecyclerView.ViewHolder): Boolean {

//        if(holder.getLayoutPosition() > lastAddAnimatedItem ) {
//            lastAddAnimatedItem++;
        runEnterAnimation(holder as PokemonLIstingViewHolder)
        return false
        //        }
//        dispatchAddFinished(holder);
//        return false;
    }


    override fun animateRemove(holder: RecyclerView.ViewHolder): Boolean {
        val view: View = holder.itemView
        //        view.setVisibility(View.INVISIBLE);
        runExitAnimation(holder as PokemonLIstingViewHolder)
        return false
    }

    private fun runEnterAnimation(holder: PokemonLIstingViewHolder) {
//        int count = mRecyclerView.getChildCount();
//        Log.i(TAG,"Animation: counting " + count + " elements");
        val context: Context = holder.itemView.getContext()
        var offset = context.resources.getDimensionPixelSize(R.dimen.offset_y).toFloat()
        val interpolator = AnimationUtils.loadInterpolator(holder.itemView.getContext(), android.R.interpolator.decelerate_quint)
        //
//        // loop over the children setting an increasing translation y but the same animation
//        // duration + interpolation
        val count: Int = holder.layoutPosition
        for (i in 0..count) {
            offset *= 1.5f
        }
        val view: View = holder.itemView
        view.visibility = View.VISIBLE
        view.translationY = offset
        view.alpha = 0.1f
        // then animate back to natural position
        view.animate()
                .translationY(0f)
                .alpha(1f)
                .setInterpolator(interpolator)
                .setDuration(1400L)
                .start()
    }

    private fun runExitAnimation(holder: PokemonLIstingViewHolder) {
        //        int count = mRecyclerView.getChildCount();
//        Log.i(TAG,"Animation: counting " + count + " elements");
        val context: Context = holder.itemView.context
        var offset = context.resources.getDimensionPixelSize(R.dimen.offset_y).toFloat()
        val interpolator = AnimationUtils.loadInterpolator(holder.itemView.context, android.R.interpolator.linear_out_slow_in)
        //
//        // loop over the children setting an increasing translation y but the same animation
//        // duration + interpolation
        val count: Int = holder.layoutPosition
        for (i in 0..count) {
            offset *= 1.5f
        }
        val view: View = holder.itemView
        view.alpha = 1f
        // then animate back to natural position
        view.animate()
                .translationY(offset)
                .alpha(0f)
                .setInterpolator(interpolator)
                .setDuration(2000L) //                .setListener(new Animator.AnimatorListener() {
                //                    @Override
                //                    public void onAnimationStart(Animator animator) {
                //                        view.setVisibility(View.INVISIBLE);
                //                    }
                //
                //                    @Override
                //                    public void onAnimationEnd(Animator animator) {
                //
                //                    }
                //
                //                    @Override
                //                    public void onAnimationCancel(Animator animator) {
                //
                //                    }
                //
                //                    @Override
                //                    public void onAnimationRepeat(Animator animator) {
                //
                //                    }
                //                })
                .start()

        // increase the offset distance for the next view
    }
}