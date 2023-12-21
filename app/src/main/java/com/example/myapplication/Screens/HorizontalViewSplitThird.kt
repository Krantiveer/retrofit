package com.example.myapplication.Screens

import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.myapplication.R
import com.example.myapplication.network.model.Content
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerView

class HorizontalViewSplitThird : AppCompatActivity() {

    private fun intiViews() {
        progressBar = findViewById(R.id.progress_bar)
        exoPlayerView = findViewById(R.id.player_view)
        image_contain = findViewById(R.id.image_contain)

        progressBarSecond = findViewById(R.id.progress_bar_second)
        exoPlayerViewSecond = findViewById(R.id.player_view_second)
        image_containSecond = findViewById(R.id.image_contain_second)

        progressBarThird = findViewById(R.id.progress_bar_third)
        exoPlayerViewThird = findViewById(R.id.player_view_third)
        image_containThird = findViewById(R.id.image_contain_third)

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
            if (currentsize!! >= size!!) {

                currentsize = 0
                Log.i(TAG, "logic: " + currentsize + size)

                restart()
                return

            }
            if (receivedContentList.get(currentsize!!).contentType != "VIDEO") {
                exoPlayerView!!.visibility = View.INVISIBLE

                image_contain!!.setVisibility(View.VISIBLE)
                updateCardViewImage(receivedContentList.get(currentsize!!).permaLink, "")
                var sec = receivedContentList.get(currentsize!!).duration * 1000
                Log.i(TAG, "logic: " + currentsize + size)
                Handler().postDelayed(Runnable {
                    currentsize = currentsize?.plus(1)
                    if (currentsize!! >= size!!) {

                        currentsize = 0
                        Log.i(TAG, "logic: " + currentsize + size)

                        restart()

                    } else {
                        Log.i(TAG, "logic:1 " + currentsize + size)

                        logic()
                    }

                }, sec.toLong())


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
            if (currentsizeSecond!! >= sizeSecond!!) {

                currentsizeSecond = 0
                Log.i(TAG, "logic: " + currentsizeSecond + sizeSecond)

                restart()
                return

            }


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

    fun logicThird() {
        if (receivedContentListThird != null) {
            if (currentsizeThird!! >= sizeThird!!) {

                currentsizeThird = 0
                Log.i(TAG, "logic: " + currentsizeThird + sizeThird)

                restart()
                return

            }

            if (receivedContentListThird.get(0).contentType != "VIDEO") {
                exoPlayerViewThird!!.visibility = View.INVISIBLE

                image_containThird!!.setVisibility(View.VISIBLE)

            } else {
                exoPlayerViewThird!!.visibility = View.VISIBLE
                image_containThird!!.setVisibility(View.INVISIBLE)
                initVideoPlayer(
                    receivedContentListThird.get(currentsizeThird!!).permaLink.toString(),
                    receivedContentListThird.get(currentsizeThird!!).format.toString()
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
                    Log.i(TAG, "onPlayerStateChangedfirst: -->" + currentsize + size)
                    if (size!! >= currentsize!!) {
                        currentsize = 0


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

                            updateCardViewImage(
                                receivedContentList.get(currentsize!!).permaLink,
                                ""
                            )

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
                        currentsizeSecond = 0

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

                            updateCardViewImage(
                                receivedContentListSecond.get(currentsizeSecond!!).permaLink,
                                "Second"
                            )

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

    fun initVideoPlayerThird(url: String?, type: String) {
        Log.i(TAG, "initVideoPlayer: $type")
        if (playerThird != null) {
            playerThird!!.stop()
            playerThird!!.release()
        }

        /*    if (type.equals("youtube", ignoreCase = true)) {
                 Log.i(TAG, "initVideoPlayer: $type")
                 initYoutubeVideo(url, this@PlayerActivityNewCode, 18)
             } else {*/


        val mediaItem = MediaItem.Builder()
            .setUri(Uri.parse((url)))
            .build()


        playerThird = ExoPlayer.Builder(this).build()
        playerThird!!.setMediaItem(mediaItem)
        playerThird!!.trackSelector
        playerThird!!.prepare()
        playerThird!!.playWhenReady = startAutoPlay


        exoPlayerViewThird!!.player = playerThird

        playerThird!!.addListener(object : Player.Listener {

            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                Log.d(TAG, "onPlayerStateChanged + $playbackState")
                if (playbackState == ExoPlayer.STATE_ENDED) {
                    Log.d(TAG, "onPlayerStateChanged + $playbackState")
                    /*  player!!.seekTo(0)
                      // Start playback again
                      player!!.play()*/
                    // loadNextVideo("M",related_id)
                    currentsizeThird = currentsizeThird?.plus(1)
                    Log.i(TAG, "onPlayerStateChanged: -->" + currentsizeThird)
                    if (sizeThird!! >= currentsizeThird!!) {
                        currentsizeThird = 0

                        if (receivedContentListThird.get(currentsizeThird!!).contentType == "VIDEO") {
                            image_containThird!!.setVisibility(View.GONE)
                            initVideoPlayer(
                                receivedContentListThird.get(currentsizeThird!!).permaLink.toString(),
                                receivedContentListThird.get(currentsizeThird!!).format.toString()
                            )
                        } else {

                            image_containThird!!.setVisibility(View.VISIBLE)
                            exoPlayerViewThird!!.visibility = View.INVISIBLE
                            var sec =
                                receivedContentListThird.get(currentsizeThird!!).duration * 1000

                            updateCardViewImage(
                                receivedContentListThird.get(currentsizeThird!!).permaLink,
                                "Third"
                            )

                            Handler().postDelayed(Runnable {
                                currentsizeThird = currentsizeThird?.plus(1)
                                logicThird()

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
        if (playerThird != null) {
            playerThird!!.playWhenReady = false
            playerThird!!.stop()
            playerThird!!.release()
            playerThird = null
            exoPlayerViewThird!!.player = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
    }

    private val TAG = "HorizontalView"
    private var progressBar: ProgressBar? = null;
    private var progressBarSecond: ProgressBar? = null;
    private var progressBarThird: ProgressBar? = null

    private var image_contain: ImageView? = null;
    private var image_containSecond: ImageView? = null;
    private var image_containThird: ImageView? = null


    protected var exoPlayerView: PlayerView? = null;
    protected var exoPlayerViewSecond: PlayerView? = null;
    protected var exoPlayerViewThird: PlayerView? = null

    protected var player: ExoPlayer? = null;
    protected var playerSecond: ExoPlayer? = null;
    protected var playerThird: ExoPlayer? = null
    private val startAutoPlay = true
    lateinit var sharedPreferences: SharedPreferences
    lateinit var receivedContentList: ArrayList<Content>;
    lateinit var receivedContentListSecond: ArrayList<Content>;
    lateinit var receivedContentListThird: ArrayList<Content>
    private var size: Int? = null;
    private var sizeSecond: Int? = null;
    private var sizeThird: Int? = null
    private var currentsize: Int? = 0;
    private var currentsizeSecond: Int? = 0;
    private var currentsizeThird: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horizontal_third_view)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE
        releasePlayer()
        intiViews()

        receivedContentList = intent.getParcelableArrayListExtra("CONTENT_LIST")!!
        receivedContentListSecond = intent.getParcelableArrayListExtra("CONTENT_LIST_TWO")!!
        receivedContentListThird = intent.getParcelableArrayListExtra("CONTENT_LIST_Third")!!
        size = receivedContentList.size
        sizeSecond = receivedContentListSecond.size
        sizeThird = receivedContentListThird.size
        var sec = receivedContentList.get(currentsize!!).duration * 1000

        if (receivedContentList != null) {
            if (receivedContentList.get(0).contentType != "VIDEO") {
                image_contain!!.setVisibility(View.VISIBLE)
                exoPlayerView!!.setVisibility(View.INVISIBLE)
                updateCardViewImage(receivedContentList.get(currentsize!!).permaLink, "")
                Handler().postDelayed(Runnable {
                    currentsize = currentsize?.plus(1)
                    logic()

                }, sec.toLong())


            } else {
                image_contain!!.setVisibility(View.GONE)
                exoPlayerView!!.setVisibility(View.VISIBLE)

                initVideoPlayer(
                    receivedContentList.get(0).permaLink.toString(),
                    receivedContentList.get(0).format.toString()
                )
                // initVideoPlayer("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4", "mp4")

            }
        }
        if (receivedContentListSecond != null) {
            var sec = receivedContentList.get(currentsizeSecond!!).duration * 1000

            if (receivedContentListSecond.get(0).contentType != "VIDEO") {
                image_containSecond!!.setVisibility(View.VISIBLE)
                exoPlayerViewSecond!!.setVisibility(View.INVISIBLE)
                updateCardViewImage(
                    receivedContentListSecond.get(currentsizeSecond!!).permaLink,
                    "Second"
                )
                Handler().postDelayed(Runnable {
                    currentsizeSecond = currentsizeSecond?.plus(1)
                    logicSecond()

                }, sec.toLong())


            } else {
                image_containSecond!!.setVisibility(View.GONE)
                exoPlayerViewSecond!!.setVisibility(View.VISIBLE)

                initVideoPlayerSecond(
                    receivedContentListSecond.get(0).permaLink.toString(),
                    receivedContentListSecond.get(0).format.toString()
                )
                // initVideoPlayer("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4", "mp4")

            }
        }
        if (receivedContentListThird != null) {
            var sec = receivedContentListThird.get(currentsizeThird!!).duration * 1000

            if (receivedContentListThird.get(0).contentType != "VIDEO") {
                image_containThird!!.setVisibility(View.VISIBLE)
                image_containThird!!.setVisibility(View.VISIBLE)
                exoPlayerViewThird!!.setVisibility(View.INVISIBLE)
                updateCardViewImage(receivedContentListThird.get(currentsize!!).permaLink, "Third")
                Handler().postDelayed(Runnable {
                    currentsizeThird = currentsizeThird?.plus(1)
                    logicThird()

                }, sec.toLong())


            } else {
                image_containThird!!.setVisibility(View.GONE)
                exoPlayerViewThird!!.setVisibility(View.VISIBLE)

                initVideoPlayerThird(
                    receivedContentListThird.get(0).permaLink.toString(),
                    receivedContentListThird.get(0).format.toString()
                )
                // initVideoPlayer("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4", "mp4")

            }
        }


        /*   Glide.with(this)
               .load(model!!.cardImageUrl)
               .into(image_contain!!)
   */
    }

    fun restart() {
        var sec = receivedContentList.get(currentsize!!).duration * 1000

        if (receivedContentList != null) {
            if (receivedContentList.get(0).contentType != "VIDEO") {
                image_contain!!.setVisibility(View.VISIBLE)
                exoPlayerView!!.setVisibility(View.INVISIBLE)
                updateCardViewImage(receivedContentList.get(currentsize!!).permaLink, "")
                Handler().postDelayed(Runnable {
                    currentsize = currentsize?.plus(1)
                    logic()

                }, sec.toLong())


            } else {
                image_contain!!.setVisibility(View.GONE)
                exoPlayerView!!.setVisibility(View.VISIBLE)

                initVideoPlayer(
                    receivedContentList.get(0).permaLink.toString(),
                    receivedContentList.get(0).format.toString()
                )
                // initVideoPlayer("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4", "mp4")

            }
            // Use the receivedContentList in your HorizontalView activity
        }
        var secSecond = receivedContentListSecond.get(currentsizeSecond!!).duration * 1000

        if (receivedContentListSecond != null) {
            if (receivedContentListSecond.get(0).contentType != "VIDEO") {
                image_containSecond!!.setVisibility(View.VISIBLE)
                exoPlayerViewSecond!!.setVisibility(View.INVISIBLE)
                updateCardViewImage(
                    receivedContentListSecond.get(currentsizeSecond!!).permaLink,
                    "Second"
                )
                Handler().postDelayed(Runnable {
                    currentsizeSecond = currentsizeSecond?.plus(1)
                    logicSecond()

                }, secSecond.toLong())


            } else {
                image_containSecond!!.setVisibility(View.GONE)
                exoPlayerViewSecond!!.setVisibility(View.VISIBLE)

                initVideoPlayer(
                    receivedContentListSecond.get(0).permaLink.toString(),
                    receivedContentListSecond.get(0).format.toString()
                )

            }
        }
        var secThird = receivedContentListThird.get(currentsizeThird!!).duration * 1000

        if (receivedContentListThird != null) {
            if (receivedContentListThird.get(0).contentType != "VIDEO") {
                image_containThird!!.setVisibility(View.VISIBLE)
                exoPlayerViewThird!!.setVisibility(View.INVISIBLE)
                updateCardViewImage(
                    receivedContentListThird.get(currentsizeThird!!).permaLink,
                    "Third"
                )
                Handler().postDelayed(Runnable {
                    currentsizeThird = currentsizeThird?.plus(1)
                    logicThird()

                }, secThird.toLong())


            } else {
                image_containThird!!.setVisibility(View.GONE)
                exoPlayerViewThird!!.setVisibility(View.VISIBLE)

                initVideoPlayer(
                    receivedContentListThird.get(0).permaLink.toString(),
                    receivedContentListThird.get(0).format.toString()
                )

            }
        }

    }

    protected fun updateCardViewImage(url: String?, second: String) {

        if (second.contentEquals("Second")) {


            Glide.with(applicationContext)
                .load(url) /*.override(100,300)*/
                .error(
                    applicationContext
                        .getResources()
                        .getDrawable(R.drawable.logo)
                )

                .transition(DrawableTransitionOptions.withCrossFade()) // Adding crossfade transition

                .into(image_containSecond!!)
        } else if (second.contentEquals("Third")) {


            Glide.with(applicationContext)
                .load(url) /*.override(100,300)*/
                .error(
                    applicationContext
                        .getResources()
                        .getDrawable(R.drawable.logo)
                )
                .transition(DrawableTransitionOptions.withCrossFade()) // Adding crossfade transition

                .into(image_containThird!!)
        } else {

            Glide.with(applicationContext)
                .load(url) /*.override(100,300)*/
                .error(
                    applicationContext
                        .getResources()
                        .getDrawable(R.drawable.logo)
                )
                .transition(DrawableTransitionOptions.withCrossFade()) // Adding crossfade transition

                .into(image_contain!!)
        }
    }


}