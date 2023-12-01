package com.example.myapplication.Screens

import android.content.DialogInterface
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.util.MimeTypes

class HorizontalView : AppCompatActivity() {
    private  val TAG = "HorizontalView"
    private var progressBar: ProgressBar? = null

    private var image_contain: ImageView? = null

    protected var player: ExoPlayer? = null
    protected var exoPlayerView //playerView;
            : PlayerView? = null
    private val startAutoPlay = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horizontal_view)
        releasePlayer()
        intiViews()

        if("media_type"=="audio"){
            image_contain!!.setVisibility(View.VISIBLE)

        }else{
            image_contain!!.setVisibility(View.GONE)
            initVideoPlayer("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4", "mp4")

        }
     /*   Glide.with(this)
            .load(model!!.cardImageUrl)
            .into(image_contain!!)
*/
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

            val dataSourceFactory: DataSource.Factory = DefaultHttpDataSource.Factory()

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
                        player!!.seekTo(0)
                        // Start playback again
                        player!!.play()
/*
                        if(next_media_id.isNotEmpty()&&next_media_id!="null"){
                            getDataEpisode(next_media_type,next_media_id)

                        }
*/


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