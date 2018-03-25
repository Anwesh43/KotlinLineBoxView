package ui.anwesome.com.lineboxview

import java.util.concurrent.ConcurrentLinkedQueue

/**
 * Created by anweshmishra on 26/03/18.
 */
class MainAnimator {
    private val runner : Runner = Runner(0)
    private var thread : Thread? = null
    private var startcb : () -> Unit = {
        thread = Thread(runner)
        thread?.start()
    }
    private var stopcb : () -> Unit = {
        while(true) {
            try {
                thread?.join()
                break
            }
            catch (ex : Exception) {

            }
        }
    }
    fun addView(view : LineBoxView) {
        runner.addView(view, startcb)
    }
    fun pause() {
        runner.pause(stopcb)
    }
    data class Runner(var i : Int) : Runnable {
        val updatingViews : ConcurrentLinkedQueue<LineBoxView> = ConcurrentLinkedQueue()
        var running : Boolean = false
        var paused : Boolean = false
        override fun run() {
            while(running) {
                try {
                    Thread.sleep(50)
                    updatingViews.forEach { view ->
                        view.updateFromOtherThread {
                            updatingViews.remove(view)
                            if (updatingViews.size == 0) {
                                running = false
                            }
                        }
                    }
                }
                catch(ex : Exception) {

                }
            }
        }
        fun addView(view : LineBoxView, startcb : () -> Unit) {
            updatingViews.add(view)
            if (updatingViews.size == 1) {
                running = true
                startcb()
            }
        }
        fun pause(pausecb : () -> Unit) {
            if (running) {
                running = false
                paused  = true
                pausecb()
            }
        }
        fun resume(resumecb : () -> Unit) {
            if (!running && paused) {
                running = true
                paused = false
                resumecb()
            }
        }
    }
    companion object {
        val ANIMATOR : MainAnimator = MainAnimator()
    }
}