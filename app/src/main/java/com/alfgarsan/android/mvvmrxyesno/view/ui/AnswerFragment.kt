package com.alfgarsan.android.mvvmrxyesno.view.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alfgarsan.android.mvvmrxyesno.R
import com.alfgarsan.android.mvvmrxyesno.model.domain.Result
import com.alfgarsan.android.mvvmrxyesno.viewmodel.AnswerViewModel
import com.alfgarsan.android.mvvmrxyesno.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.main_fragment.*
import kotlin.random.Random

/**
 * This fragment manages both layouts: question layout and answer layout
 */
class AnswerFragment : Fragment() {

    companion object {
        fun newInstance() =
            AnswerFragment()
    }

    private val answerViewModel by lazy {
        ViewModelProvider(viewModelStore,
            ViewModelFactory()
        ).get(AnswerViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLiveData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        visibleQuestion()

        buttonAnswer.setOnClickListener{
            visibleLoading()
            answerViewModel.loadAnswer()
        }

        buttonClear.setOnClickListener{
            visibleQuestion()
        }
    }

    private fun observeLiveData() {
        activity?.let {
            answerViewModel.answer.observe(it, Observer { result ->
                when (result) {
                    is Result.Success -> {
                        loadGifFromURL(result.value.imageUrl, requireContext(), answerGif)
                    }
                    is Result.Failure -> {
                        resolveAnswerLocally()
                    }
                }
            })
        }
    }

    private fun resolveAnswerLocally(){
        when(Random.nextInt(0, 2)){
            0 -> loadGifFromLocal(R.drawable.yes_default, requireContext(), answerGif)
            1 -> loadGifFromLocal(R.drawable.no_default, requireContext(), answerGif)
        }
    }

    private fun showMessage(resId: Int){
        val snackbar = Snackbar.make(
            main,
            resId,
            Snackbar.LENGTH_LONG
        )
        snackbar.view.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorSnackbarError))
        snackbar.show()
    }

    /**
     * Visibility methods
     */

    private fun visibleQuestion(){
        title.text = getString(R.string.titleQuestion)
        answerGif.visibility = View.INVISIBLE
        questionSymbol.visibility = View.VISIBLE
        loadingAnswer.visibility = View.INVISIBLE
        buttonAnswer.visibility = View.VISIBLE
        buttonClear.visibility = View.INVISIBLE
    }

    private fun visibleLoading(){
        title.text = getString(R.string.titleAnswer)
        answerGif.visibility = View.INVISIBLE
        questionSymbol.visibility = View.INVISIBLE
        buttonAnswer.visibility = View.INVISIBLE
        loadingAnswer.visibility = View.VISIBLE
        buttonClear.visibility = View.INVISIBLE
    }

    private fun visibleAnswer(){
        title.text = getString(R.string.titleAnswer)
        answerGif.visibility = View.VISIBLE
        questionSymbol.visibility = View.INVISIBLE
        loadingAnswer.visibility = View.INVISIBLE
        buttonAnswer.visibility = View.INVISIBLE
        buttonClear.visibility = View.VISIBLE
    }

    /**
     * Glide methods
     */

    private fun loadGifFromURL(url: String, context: Context, imageview: ImageView){
        Glide
            .with(context)
            .load(url)
            .listener(object: RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    //If resource from URL fail then resolve using local resource
                    resolveAnswerLocally()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    visibleAnswer()
                    return false
                }
            })
            .into(imageview)
    }

    /**
     * Analogue to previous method's comment
     */
    private fun loadGifFromLocal(rawResId: Int, context: Context, imageview: ImageView){
        Glide
            .with(context)
            .load(rawResId)
            .listener(object: RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    visibleQuestion()
                    showMessage(R.string.error_msg_no_network_no_local_gif)
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    visibleAnswer()
                    showMessage(R.string.warn_msg_no_network_show_local_gif)
                    return false
                }

            })
            .into(imageview)
    }
}
