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

class VerticalViewFour : AppCompatActivity() {


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


    lateinit var receivedContentListTriple: ArrayList<Content>
    private var sizeTriple: Int? = null
    private var currentsizeTriple: Int? = 0
    private var image_containTriple: ImageView? = null
    private var progressBarTriple: ProgressBar? = null
    protected var exoPlayerViewTriple: PlayerView? = null
    protected var playerTriple: ExoPlayer? = null

    lateinit var receivedContentListFour: ArrayList<Content>
    private var sizeFour: Int? = null
    private var currentsizeFour: Int? = 0
    private var image_containFour: ImageView? = null
    private var progressBarFour: ProgressBar? = null
    protected var exoPlayerViewFour: PlayerView? = null
    protected var playerFour: ExoPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vertical_four_view)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE
        releasePlayer()
        intiViews()


        receivedContentList = intent.getParcelableArrayListExtra("CONTENT_LIST")!!
        receivedContentListSecond = intent.getParcelableArrayListExtra("CONTENT_LIST_TWO")!!
        receivedContentListTriple = intent.getParcelableArrayListExtra("CONTENT_LIST_TRIPLE")!!
        receivedContentListFour = intent.getParcelableArrayListExtra("CONTENT_LIST_FOUR")!!
        size = receivedContentList.size
        sizeSecond = receivedContentListSecond.size
        sizeTriple = receivedContentListTriple.size
        sizeFour = receivedContentListFour.size
        var sec = receivedContentList.get(currentsize!!).duration * 1000
        /*
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
                    var sec = receivedContentListSecond.get(currentsizeSecond!!).duration * 1000

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

        */
        firstscreen()
        secondscreen()
        Triplescreen()
        Fourscreen()
    }
    fun firstscreen(){
        receivedContentList = intent.getParcelableArrayListExtra("CONTENT_LIST")!!
        size = receivedContentList.size
        var sec = receivedContentList.get(currentsize!!).duration * 1000

        if (receivedContentList != null) {
            if (receivedContentList.get(0).contentType != "VIDEO") {
                image_contain!!.setVisibility(View.VISIBLE)
                exoPlayerView!!.setVisibility(View.INVISIBLE)
                updateCardViewImage(receivedContentList.get(currentsize!!).permaLink,"")
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
    }
    fun secondscreen(){
        receivedContentListSecond = intent.getParcelableArrayListExtra("CONTENT_LIST_TWO")!!
        sizeSecond = receivedContentListSecond.size
        var sec = receivedContentListSecond.get(currentsizeSecond!!).duration * 1000

        if (receivedContentListSecond != null) {
            if (receivedContentListSecond.get(0).contentType != "VIDEO") {
                image_containSecond!!.setVisibility(View.VISIBLE)
                exoPlayerViewSecond!!.setVisibility(View.INVISIBLE)
                updateCardViewImage(receivedContentListSecond.get(currentsizeSecond!!).permaLink,"Second")
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
            // Use the receivedContentList in your HorizontalView activity
        }
    }
    fun Triplescreen(){
        receivedContentListTriple = intent.getParcelableArrayListExtra("CONTENT_LIST_TRIPLE")!!
        sizeTriple = receivedContentListTriple.size
        var sec = receivedContentListTriple.get(currentsizeTriple!!).duration * 1000

        if (receivedContentListTriple != null) {
            if (receivedContentListTriple.get(0).contentType != "VIDEO") {
                image_containTriple!!.setVisibility(View.VISIBLE)
                exoPlayerViewTriple!!.setVisibility(View.INVISIBLE)
                updateCardViewImage(receivedContentListTriple.get(currentsizeTriple!!).permaLink,"Triple")
                Handler().postDelayed(Runnable {
                    currentsizeTriple = currentsizeTriple?.plus(1)
                    logicTriple()

                }, sec.toLong())


            } else {
                image_containTriple!!.setVisibility(View.GONE)
                exoPlayerViewTriple!!.setVisibility(View.VISIBLE)

                initVideoPlayerTriple(
                    receivedContentListTriple.get(0).permaLink.toString(),
                    receivedContentListTriple.get(0).format.toString()
                )
                // initVideoPlayer("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4", "mp4")

            }
            // Use the receivedContentList in your HorizontalView activity
        }
    }
    fun Fourscreen(){
        receivedContentListFour = intent.getParcelableArrayListExtra("CONTENT_LIST_FOUR")!!
        sizeFour = receivedContentListFour.size
        var sec = receivedContentListFour.get(currentsizeFour!!).duration * 1000

        if (receivedContentListFour != null) {
            if (receivedContentListFour.get(0).contentType != "VIDEO") {
                image_containFour!!.setVisibility(View.VISIBLE)
                exoPlayerViewFour!!.setVisibility(View.INVISIBLE)
                updateCardViewImage(receivedContentListFour.get(currentsizeFour!!).permaLink,"Four")
                Handler().postDelayed(Runnable {
                    currentsizeFour = currentsizeFour?.plus(1)
                    logicFour()

                }, sec.toLong())


            } else {
                image_containFour!!.setVisibility(View.GONE)
                exoPlayerViewFour!!.setVisibility(View.VISIBLE)

                initVideoPlayerFour(
                    receivedContentListFour.get(0).permaLink.toString(),
                    receivedContentListFour.get(0).format.toString()
                )
                // initVideoPlayer("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4", "mp4")

            }
            // Use the receivedContentList in your HorizontalView activity
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
        }
        else if (second.contentEquals("Triple")) {


            Glide.with(applicationContext)
                .load(url) /*.override(100,300)*/
                .error(
                    applicationContext
                        .getResources()
                        .getDrawable(R.drawable.logo)
                )
                .transition(DrawableTransitionOptions.withCrossFade()) // Adding crossfade transition

                .into(image_containTriple!!)
        }else if (second.contentEquals("Four")) {


            Glide.with(applicationContext)
                .load(url) /*.override(100,300)*/
                .error(
                    applicationContext
                        .getResources()
                        .getDrawable(R.drawable.logo)
                )
                .transition(DrawableTransitionOptions.withCrossFade()) // Adding crossfade transition

                .into(image_containFour!!)
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

    private fun intiViews() {
        progressBar = findViewById(R.id.progress_bar)
        exoPlayerView = findViewById(R.id.player_view)
        image_contain = findViewById(R.id.image_contain)

        progressBarSecond = findViewById(R.id.progress_bar_second)
        exoPlayerViewSecond = findViewById(R.id.player_view_second)
        image_containSecond = findViewById(R.id.image_contain_second)

        progressBarTriple = findViewById(R.id.progress_bar_triple)
        exoPlayerViewTriple = findViewById(R.id.player_view_triple)
        image_containTriple = findViewById(R.id.image_contain_triple)

        progressBarFour = findViewById(R.id.progress_bar_four)
        exoPlayerViewFour = findViewById(R.id.player_view_four)
        image_containFour = findViewById(R.id.image_contain_four)

    }
    fun logic() {
        if (receivedContentList != null) {
            if (currentsize!! >= size!!) {

                currentsize = 0
                Log.i(TAG, "logic: " + currentsize + size)
                Log.i(TAG, "restart: calling1")

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

    /*
        fun logic() {
            if (receivedContentList != null) {
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
    */

    fun logicSecond() {
        if (receivedContentListSecond != null) {
            if (currentsizeSecond!! >= size!!) {

                currentsizeSecond = 0
                Log.i(TAG, "logic: " + currentsizeSecond + sizeSecond)
                Log.i(TAG, "restart: calling1")

                restartSecond()
                return

            }
            if (receivedContentListSecond.get(currentsizeSecond!!).contentType != "VIDEO") {
                exoPlayerViewSecond!!.visibility = View.INVISIBLE
                image_containSecond!!.setVisibility(View.VISIBLE)
                updateCardViewImage(receivedContentListSecond.get(currentsizeSecond!!).permaLink, "")
                var sec = receivedContentListSecond.get(currentsizeSecond!!).duration * 1000
                Log.i(TAG, "logic: " + currentsizeSecond + sizeSecond)
                Handler().postDelayed(Runnable {
                    currentsizeSecond = currentsizeSecond?.plus(1)
                    if (currentsizeSecond!! >= sizeSecond!!) {

                        currentsizeSecond = 0
                        Log.i(TAG, "logic: " + currentsizeSecond + sizeSecond)

                        restartSecond()

                    } else {
                        Log.i(TAG, "logic:1 " + currentsizeSecond + sizeSecond)

                        logic()
                    }

                }, sec.toLong())


            } else {
                exoPlayerViewSecond!!.visibility = View.VISIBLE
                image_containSecond!!.setVisibility(View.INVISIBLE)
                initVideoPlayerSecond(
                    receivedContentListSecond.get(currentsizeSecond!!).permaLink.toString(),
                    receivedContentListSecond.get(currentsizeSecond!!).format.toString()
                )
                // initVideoPlayer("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4", "mp4")

            }
        }
    }
    fun logicTriple() {
        if (receivedContentListTriple != null) {
            if (currentsizeTriple!! >= size!!) {

                currentsizeTriple = 0
                Log.i(TAG, "logic: " + currentsizeTriple + sizeTriple)
                Log.i(TAG, "restart: calling1")

                restartTriple()
                return

            }
            if (receivedContentListTriple.get(currentsizeTriple!!).contentType != "VIDEO") {
                exoPlayerViewTriple!!.visibility = View.INVISIBLE

                image_containTriple!!.setVisibility(View.VISIBLE)
                updateCardViewImage(receivedContentListTriple.get(currentsizeTriple!!).permaLink, "")
                var sec = receivedContentListTriple.get(currentsizeTriple!!).duration * 1000
                Log.i(TAG, "logic: " + currentsizeTriple + sizeTriple)
                Handler().postDelayed(Runnable {
                    currentsizeTriple = currentsizeTriple?.plus(1)
                    if (currentsizeTriple!! >= sizeTriple!!) {

                        currentsizeTriple = 0
                        Log.i(TAG, "logic: " + currentsizeTriple + sizeTriple)

                        restartTriple()

                    } else {
                        Log.i(TAG, "logic:1 " + currentsizeTriple + sizeTriple)

                        logic()
                    }

                }, sec.toLong())


            } else {
                exoPlayerViewTriple!!.visibility = View.VISIBLE
                image_containTriple!!.setVisibility(View.INVISIBLE)
                initVideoPlayerTriple(
                    receivedContentListTriple.get(currentsizeTriple!!).permaLink.toString(),
                    receivedContentListTriple.get(currentsizeTriple!!).format.toString()
                )
                // initVideoPlayer("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4", "mp4")

            }
        }
    }
    fun logicFour() {
        if (receivedContentListFour != null) {
            if (currentsizeFour!! >= size!!) {

                currentsizeFour = 0
                Log.i(TAG, "logic: " + currentsizeFour + sizeFour)
                Log.i(TAG, "restart: calling1")

                restartFour()
                return

            }
            if (receivedContentListFour.get(currentsizeFour!!).contentType != "VIDEO") {
                exoPlayerViewFour!!.visibility = View.INVISIBLE

                image_containFour!!.setVisibility(View.VISIBLE)
                updateCardViewImage(receivedContentListFour.get(currentsizeFour!!).permaLink, "")
                var sec = receivedContentListFour.get(currentsizeFour!!).duration * 1000
                Log.i(TAG, "logic: " + currentsizeFour + sizeFour)
                Handler().postDelayed(Runnable {
                    currentsizeFour = currentsizeFour?.plus(1)
                    if (currentsizeFour!! >= sizeFour!!) {

                        currentsizeFour = 0
                        Log.i(TAG, "logic: " + currentsizeFour + sizeFour)

                        restartFour()

                    } else {
                        Log.i(TAG, "logic:1 " + currentsizeFour + sizeFour)

                        logic()
                    }

                }, sec.toLong())


            } else {
                exoPlayerViewFour!!.visibility = View.VISIBLE
                image_containFour!!.setVisibility(View.INVISIBLE)
                initVideoPlayerFour(
                    receivedContentListFour.get(currentsizeFour!!).permaLink.toString(),
                    receivedContentListFour.get(currentsizeFour!!).format.toString()
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

                    currentsizeSecond = currentsizeSecond?.plus(1)
                    Log.i(TAG, "onPlayerStateChanged: -->" + currentsizeSecond)
                    if (sizeSecond!! >= currentsizeSecond!!) {
                        currentsizeSecond = 0
                        if (receivedContentListSecond.get(currentsizeSecond!!).contentType == "VIDEO") {
                            image_containSecond!!.setVisibility(View.GONE)
                            initVideoPlayerSecond(
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
    fun initVideoPlayerTriple(url: String?, type: String) {
        Log.i(TAG, "initVideoPlayer: $type")
        if (playerTriple != null) {
            playerTriple!!.stop()
            playerTriple!!.release()
        }

        /*    if (type.equals("youtube", ignoreCase = true)) {
                 Log.i(TAG, "initVideoPlayer: $type")
                 initYoutubeVideo(url, this@PlayerActivityNewCode, 18)
             } else {*/


        val mediaItem = MediaItem.Builder()
            .setUri(Uri.parse((url)))
            .build()


        playerTriple = ExoPlayer.Builder(this).build()
        playerTriple!!.setMediaItem(mediaItem)
        playerTriple!!.trackSelector
        playerTriple!!.prepare()
        playerTriple!!.playWhenReady = startAutoPlay


        exoPlayerViewTriple!!.player = playerTriple

        playerTriple!!.addListener(object : Player.Listener {

            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                Log.d(TAG, "onPlayerStateChanged + $playbackState")
                if (playbackState == ExoPlayer.STATE_ENDED) {
                    Log.d(TAG, "onPlayerStateChanged + $playbackState")

                    currentsizeTriple = currentsizeTriple?.plus(1)
                    Log.i(TAG, "onPlayerStateChanged: -->" + currentsizeTriple)
                    if (sizeTriple!! >= currentsizeTriple!!) {
                        currentsizeTriple = 0
                        if (receivedContentListTriple.get(currentsizeTriple!!).contentType == "VIDEO") {
                            image_containTriple!!.setVisibility(View.GONE)
                            initVideoPlayerTriple(
                                receivedContentListTriple.get(currentsizeTriple!!).permaLink.toString(),
                                receivedContentListTriple.get(currentsizeTriple!!).format.toString()
                            )
                        } else {

                            image_containTriple!!.setVisibility(View.VISIBLE)
                            exoPlayerViewTriple!!.visibility = View.INVISIBLE
                            var sec =
                                receivedContentListTriple.get(currentsizeTriple!!).duration * 1000

                            updateCardViewImage(
                                receivedContentListTriple.get(currentsizeTriple!!).permaLink,
                                "Triple"
                            )

                            Handler().postDelayed(Runnable {
                                currentsizeTriple = currentsizeTriple?.plus(1)
                                logicTriple()

                            }, sec.toLong())


                        }

                    }

                }

            }


            fun onPlayWhenReadyCommitted() {}
            fun onPlayerError(error: ExoPlaybackException?) {}
        })


    }
    fun initVideoPlayerFour(url: String?, type: String) {
        Log.i(TAG, "initVideoPlayer: $type")
        if (playerFour != null) {
            playerFour!!.stop()
            playerFour!!.release()
        }

        val mediaItem = MediaItem.Builder()
            .setUri(Uri.parse((url)))
            .build()


        playerFour = ExoPlayer.Builder(this).build()
        playerFour!!.setMediaItem(mediaItem)
        playerFour!!.trackSelector
        playerFour!!.prepare()
        playerFour!!.playWhenReady = startAutoPlay


        exoPlayerViewFour!!.player = playerFour

        playerFour!!.addListener(object : Player.Listener {

            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                Log.d(TAG, "onPlayerStateChanged + $playbackState")
                if (playbackState == ExoPlayer.STATE_ENDED) {
                    Log.d(TAG, "onPlayerStateChanged + $playbackState")

                    currentsizeFour = currentsizeFour?.plus(1)
                    Log.i(TAG, "onPlayerStateChanged: -->" + currentsizeFour)
                    if (sizeFour!! >= currentsizeFour!!) {
                        currentsizeFour = 0
                        if (receivedContentListFour.get(currentsizeFour!!).contentType == "VIDEO") {
                            image_containFour!!.setVisibility(View.GONE)
                            initVideoPlayerFour(
                                receivedContentListFour.get(currentsizeFour!!).permaLink.toString(),
                                receivedContentListFour.get(currentsizeFour!!).format.toString()
                            )
                        } else {

                            image_containFour!!.setVisibility(View.VISIBLE)
                            exoPlayerViewFour!!.visibility = View.INVISIBLE
                            var sec =
                                receivedContentListFour.get(currentsizeFour!!).duration * 1000

                            updateCardViewImage(
                                receivedContentListFour.get(currentsizeFour!!).permaLink,
                                "Four"
                            )

                            Handler().postDelayed(Runnable {
                                currentsizeFour = currentsizeFour?.plus(1)
                                logicFour()

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
        if (playerTriple != null) {
            playerTriple!!.playWhenReady = false
            playerTriple!!.stop()
            playerTriple!!.release()
            playerTriple = null
            exoPlayerViewTriple!!.player = null
        }
        if (playerFour != null) {
            playerFour!!.playWhenReady = false
            playerFour!!.stop()
            playerFour!!.release()
            playerFour = null
            exoPlayerViewFour!!.player = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
    }
    fun restart() {
        Log.i(TAG, "restart: calling")
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


    }
    fun restartSecond() {
        var sec = receivedContentListSecond.get(currentsizeSecond!!).duration * 1000

        if (receivedContentListSecond != null) {
            if (receivedContentListSecond.get(0).contentType != "VIDEO") {
                image_containSecond!!.setVisibility(View.VISIBLE)
                exoPlayerViewSecond!!.setVisibility(View.INVISIBLE)
                updateCardViewImage(receivedContentListSecond.get(currentsize!!).permaLink,"Second")
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
            // Use the receivedContentList in your HorizontalView activity
        }

    }
    fun restartTriple() {
        var sec = receivedContentListTriple.get(currentsizeTriple!!).duration * 1000

        if (receivedContentListTriple != null) {
            if (receivedContentListTriple.get(0).contentType != "VIDEO") {
                image_containTriple!!.setVisibility(View.VISIBLE)
                exoPlayerViewTriple!!.setVisibility(View.INVISIBLE)
                updateCardViewImage(receivedContentListTriple.get(currentsize!!).permaLink,"Triple")
                Handler().postDelayed(Runnable {
                    currentsizeTriple = currentsizeTriple?.plus(1)
                    logicTriple()

                }, sec.toLong())


            } else {
                image_containTriple!!.setVisibility(View.GONE)
                exoPlayerViewTriple!!.setVisibility(View.VISIBLE)

                initVideoPlayerTriple(
                    receivedContentListTriple.get(0).permaLink.toString(),
                    receivedContentListTriple.get(0).format.toString()
                )
                // initVideoPlayer("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4", "mp4")

            }
            // Use the receivedContentList in your HorizontalView activity
        }

    }fun restartFour() {
        var sec = receivedContentListFour.get(currentsizeFour!!).duration * 1000

        if (receivedContentListFour != null) {
            if (receivedContentListFour.get(0).contentType != "VIDEO") {
                image_containFour!!.setVisibility(View.VISIBLE)
                exoPlayerViewFour!!.setVisibility(View.INVISIBLE)
                updateCardViewImage(receivedContentListFour.get(currentsize!!).permaLink,"Four")
                Handler().postDelayed(Runnable {
                    currentsizeFour = currentsizeFour?.plus(1)
                    logicFour()

                }, sec.toLong())


            } else {
                image_containFour!!.setVisibility(View.GONE)
                exoPlayerViewFour!!.setVisibility(View.VISIBLE)

                initVideoPlayerFour(
                    receivedContentListFour.get(0).permaLink.toString(),
                    receivedContentListFour.get(0).format.toString()
                )
                // initVideoPlayer("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4", "mp4")

            }
            // Use the receivedContentList in your HorizontalView activity
        }

    }
}