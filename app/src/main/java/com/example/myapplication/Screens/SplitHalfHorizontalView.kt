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

class SplitHalfHorizontalView : AppCompatActivity() {
    private val TAG = "HorizontalView"
    private var progressBar: ProgressBar? = null;
    private var progressBarSecond: ProgressBar? = null

    private var image_contain: ImageView? = null;
    private var image_containSecond: ImageView? = null


    protected var exoPlayerView: PlayerView? = null;
    protected var exoPlayerViewSecond: PlayerView? = null

    protected var player: ExoPlayer? = null;
    protected var playerSecond: ExoPlayer? = null
    private val startAutoPlay = true
    lateinit var sharedPreferences: SharedPreferences
    lateinit var receivedContentList: ArrayList<Content>;
    lateinit var receivedContentListSecond: ArrayList<Content>
    private var size: Int? = null;
    private var sizeSecond: Int? = null
    private var currentsize: Int? = 0
    private var currentsizeSecond: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horizontal_view)
        releasePlayer()
        intiViews()

        receivedContentList = intent.getParcelableArrayListExtra("CONTENT_LIST")!!
        receivedContentListSecond = intent.getParcelableArrayListExtra("CONTENT_LIST_TWO")!!
        size = receivedContentList.size
        sizeSecond = receivedContentListSecond.size
        if (receivedContentList != null) {
            if (receivedContentList.get(0).contentType != "VIDEO") {
                image_contain!!.setVisibility(View.VISIBLE)

            } else {
                image_contain!!.setVisibility(View.GONE)
                initVideoPlayer(
                    receivedContentList.get(0).permaLink.toString(),
                    receivedContentList.get(0).format.toString()
                )
                // initVideoPlayer("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4", "mp4")

            }
        }
        if (receivedContentListSecond != null) {
            if (receivedContentListSecond.get(0).contentType != "VIDEO") {
                image_containSecond!!.setVisibility(View.VISIBLE)

            } else {
                image_containSecond!!.setVisibility(View.GONE)
                initVideoPlayerSecond(
                    receivedContentListSecond.get(0).permaLink.toString(),
                    receivedContentListSecond.get(0).format.toString()
                )
                // initVideoPlayer("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4", "mp4")

            }
        }


        /*   Glide.with(this)
               .load(model!!.cardImageUrl)
               .into(image_contain!!)
   */
    }

    protected fun updateCardViewImage(url: String?,second:String) {

        if(second.contentEquals("Second")){


            Glide.with(applicationContext)
                .load(url) /*.override(100,300)*/
                .error(
                    applicationContext
                        .getResources()
                        .getDrawable(R.drawable.logo)
                )

                .into(image_containSecond!!)
        }else{

            Glide.with(applicationContext)
                .load(url) /*.override(100,300)*/
                .error(
                    applicationContext
                        .getResources()
                        .getDrawable(R.drawable.logo)
                )

                .into(image_contain!!)
        }
    }

    private fun intiViews() {
        progressBar = findViewById(R.id.progress_bar)
        exoPlayerView = findViewById(R.id.player_view)
        image_contain = findViewById(R.id.image_contain)

        progressBarSecond = findViewById(R.id.progress_bar_second)
        exoPlayerViewSecond = findViewById(R.id.player_view_second)
        image_containSecond = findViewById(R.id.image_contain_second)

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

    fun logic() {
        if (receivedContentList != null) {
            if (receivedContentList.get(0).contentType != "VIDEO") {
                exoPlayerView!!.visibility = View.INVISIBLE

                image_contain!!.setVisibility(View.VISIBLE)

            } else {
                exoPlayerView!!.visibility = View.VISIBLE
                image_contain!!.setVisibility(View.INVISIBLE)
                initVideoPlayer(
                    receivedContentList.get(currentsize!!).permaLink.toString(),
                    receivedContentList.get(currentsize!!).format.toString()
                )
                // initVideoPlayer("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4", "mp4")

            }
        }
    }
    fun logicSecond() {
        if (receivedContentListSecond != null) {
            if (receivedContentListSecond.get(0).contentType != "VIDEO") {
                exoPlayerViewSecond!!.visibility = View.INVISIBLE

                image_containSecond!!.setVisibility(View.VISIBLE)

            } else {
                exoPlayerViewSecond!!.visibility = View.VISIBLE
                image_containSecond!!.setVisibility(View.INVISIBLE)
                initVideoPlayer(
                    receivedContentListSecond.get(currentsizeSecond!!).permaLink.toString(),
                    receivedContentListSecond.get(currentsizeSecond!!).format.toString()
                )
                // initVideoPlayer("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4", "mp4")

            }
        }
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
                        } else {
                            image_contain!!.setVisibility(View.VISIBLE)
                            exoPlayerView!!.visibility = View.INVISIBLE
                            var sec = receivedContentList.get(currentsize!!).duration * 1000

                            updateCardViewImage(receivedContentList.get(currentsize!!).permaLink,"")

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

    fun initVideoPlayerSecond(url: String?, type: String) {
        Log.i(TAG, "initVideoPlayer: $type")
        if (playerSecond != null) {
            playerSecond!!.stop()
            playerSecond!!.release()
        }

        /*    if (type.equals("youtube", ignoreCase = true)) {
                 Log.i(TAG, "initVideoPlayer: $type")
                 initYoutubeVideo(url, this@PlayerActivityNewCode, 18)
             } else {*/


        val mediaItem = MediaItem.Builder()
            .setUri(Uri.parse((url)))
            .build()


        playerSecond = ExoPlayer.Builder(this).build()
        playerSecond!!.setMediaItem(mediaItem)
        playerSecond!!.trackSelector
        playerSecond!!.prepare()
        playerSecond!!.playWhenReady = startAutoPlay


        exoPlayerViewSecond!!.player = playerSecond

        playerSecond!!.addListener(object : Player.Listener {

            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                Log.d(TAG, "onPlayerStateChanged + $playbackState")
                if (playbackState == ExoPlayer.STATE_ENDED) {
                    Log.d(TAG, "onPlayerStateChanged + $playbackState")
                    /*  player!!.seekTo(0)
                      // Start playback again
                      player!!.play()*/
                    // loadNextVideo("M",related_id)
                    currentsizeSecond = currentsizeSecond?.plus(1)
                    Log.i(TAG, "onPlayerStateChanged: -->" + currentsizeSecond)
                    if (sizeSecond!! >= currentsizeSecond!!) {
                        if (receivedContentListSecond.get(currentsizeSecond!!).contentType == "VIDEO") {
                            image_containSecond!!.setVisibility(View.GONE)
                            initVideoPlayer(
                                receivedContentListSecond.get(currentsizeSecond!!).permaLink.toString(),
                                receivedContentListSecond.get(currentsizeSecond!!).format.toString()
                            )
                        } else {

                            image_containSecond!!.setVisibility(View.VISIBLE)
                            exoPlayerViewSecond!!.visibility = View.INVISIBLE
                            var sec =
                                receivedContentListSecond.get(currentsizeSecond!!).duration * 1000

                            updateCardViewImage(receivedContentListSecond.get(currentsizeSecond!!).permaLink,"Second")

                            Handler().postDelayed(Runnable {
                                currentsizeSecond = currentsizeSecond?.plus(1)
                                logicSecond()

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
        if (playerSecond != null) {
            playerSecond!!.playWhenReady = false
            playerSecond!!.stop()
            playerSecond!!.release()
            playerSecond = null
            exoPlayerViewSecond!!.player = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
    }

}