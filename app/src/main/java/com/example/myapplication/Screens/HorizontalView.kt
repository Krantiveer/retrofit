package com.example.myapplication.Screens

import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.network.model.Content
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource

class HorizontalView : AppCompatActivity() {
    private val TAG = "HorizontalView"
    private var progressBar: ProgressBar? = null

    private var image_contain: ImageView? = null

    protected var player: ExoPlayer? = null
    protected var exoPlayerView //playerView;
            : PlayerView? = null
    private val startAutoPlay = true
    lateinit var sharedPreferences: SharedPreferences
    lateinit var receivedContentList: ArrayList<Content>
    private var size: Int? = null
    private var currentsize: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horizontal_view)
        releasePlayer()
        intiViews()

        receivedContentList = intent.getParcelableArrayListExtra("CONTENT_LIST")!!
        size = receivedContentList.size
        if (receivedContentList != null) {
            if (receivedContentList.get(0).contentType != "VIDEO") {
                image_contain!!.setVisibility(View.VISIBLE)
                updateCardViewImage(receivedContentList.get(currentsize!!).permaLink)


            } else {
                image_contain!!.setVisibility(View.GONE)
                initVideoPlayer(
                    receivedContentList.get(0).permaLink.toString(),
                    receivedContentList.get(0).format.toString()
                )
                // initVideoPlayer("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4", "mp4")

            }
            // Use the receivedContentList in your HorizontalView activity
        }


        /*   Glide.with(this)
               .load(model!!.cardImageUrl)
               .into(image_contain!!)
   */
    }
    protected fun updateCardViewImage(url: String?) {

        Glide.with(applicationContext)
            .load(url) /*.override(100,300)*/
            .error(applicationContext
                .getResources()
                .getDrawable(R.drawable.logo))

            .into(image_contain!!)
    }

    private fun intiViews() {
        progressBar = findViewById(R.id.progress_bar)
        exoPlayerView = findViewById(R.id.player_view)
        image_contain = findViewById(R.id.image_contain)

        /*
            val imageUrl = PreferenceUtils.getInstance().getWatermarkLogoUrlPref(this)
            if(media_type=="audio"){
                image_contain!!.setVisibility(View.VISIBLE)

            }else{
                image_contain!!.setVisibility(View.GONE)

            }*/

        // Replace with your image URL
        /* Log.i(
             TAG,
             "intiViews:playerAcitivity " + imageUrl.equals("1", ignoreCase = true) + imageUrl
         )*/
        /* if (!imageUrl.isEmpty() && !imageUrl.equals("1", ignoreCase = true)) {
             Glide.with(this)
                 .load(imageUrl)
                 .into(watermark_live!!)
             Glide.with(this)
                 .load(imageUrl)
                 .into(watermark!!)
         }

         //  webView=findViewById(R.id.webview);
         if (!model!!.cardImageUrl.isNullOrEmpty()) {
             Glide.with(this)
                 .load(model!!.cardImageUrl)
                 .into(image_contain!!)

         }
 */
        // PreferenceUtils.getInstance().getWatermarkLogoUrlPref(this);
    }
fun logic(){
    if (receivedContentList != null) {
        if (receivedContentList.get(0).contentType != "VIDEO") {
            exoPlayerView!!.visibility=View.INVISIBLE

            image_contain!!.setVisibility(View.VISIBLE)

        } else {
            exoPlayerView!!.visibility=View.VISIBLE
            image_contain!!.setVisibility(View.INVISIBLE)
            initVideoPlayer(
                receivedContentList.get(currentsize!!).permaLink.toString(),
                receivedContentList.get(currentsize!!).format.toString()
            )
            // initVideoPlayer("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4", "mp4")

        }
}}
    fun initVideoPlayer(url: String?, type: String) {
        Log.i(TAG, "initVideoPlayer: $type")
        if (player != null) {
            player!!.stop()
            player!!.release()
        }

        /*    if (type.equals("youtube", ignoreCase = true)) {
                 Log.i(TAG, "initVideoPlayer: $type")
                 initYoutubeVideo(url, this@PlayerActivityNewCode, 18)
             } else {*/


        val mediaItem = MediaItem.Builder()
            .setUri(Uri.parse((url)))
            .build()


        player = ExoPlayer.Builder(this).build()
        player!!.setMediaItem(mediaItem)
        player!!.trackSelector
        player!!.prepare()
        player!!.playWhenReady = startAutoPlay


        exoPlayerView!!.player = player

        player!!.addListener(object : Player.Listener {

            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                Log.d(TAG, "onPlayerStateChanged + $playbackState")
                if (playbackState == ExoPlayer.STATE_ENDED) {
                    Log.d(TAG, "onPlayerStateChanged + $playbackState")
                    /*  player!!.seekTo(0)
                      // Start playback again
                      player!!.play()*/
                    // loadNextVideo("M",related_id)
                    currentsize = currentsize?.plus(1)
                    Log.i(TAG, "onPlayerStateChanged: -->" + currentsize)
                    if (size!! >= currentsize!!) {
                        if (receivedContentList.get(currentsize!!).contentType == "VIDEO") {
                            image_contain!!.setVisibility(View.GONE)
                            initVideoPlayer(
                                receivedContentList.get(currentsize!!).permaLink.toString(),
                                receivedContentList.get(currentsize!!).format.toString()
                            )
                        }else{
                            image_contain!!.setVisibility(View.VISIBLE)
                            exoPlayerView!!.visibility=View.INVISIBLE
                            var sec =receivedContentList.get(currentsize!!).duration * 1000

                            updateCardViewImage(receivedContentList.get(currentsize!!).permaLink)

                            Handler().postDelayed(Runnable {
                                currentsize = currentsize?.plus(1)
                                logic()

                            }, sec.toLong())


                        }

                    }

                }

            }


            fun onPlayWhenReadyCommitted() {}
            fun onPlayerError(error: ExoPlaybackException?) {}
        })


    }

    private fun releasePlayer() {
        if (player != null) {
            player!!.playWhenReady = false
            player!!.stop()
            player!!.release()
            player = null
            exoPlayerView!!.player = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
    }

}