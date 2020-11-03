package com.think.asu.aty

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.think.asu.R
import com.think.asu.base.BaseApplication
import com.think.asu.tools.Constants
import com.think.asu.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.aty_main.*
import java.util.*


/**
+--------------------------------------+
+ @author Catherine Liu
+--------------------------------------+
+ 2020/11/2 14:27
+--------------------------------------+
+ Des:首页分为【随机模式】、【自定义模式】
+--------------------------------------+
 */

class MainAty : AppCompatActivity(), TextToSpeech.OnInitListener {

    private val mainViewModel: MainViewModel by lazy { MainViewModel(BaseApplication.instance) }

    //声明语音播报对象
    private val tts: TextToSpeech by lazy { TextToSpeech(this, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aty_main)
        initView()
        initListener()
    }

    private fun initView() {
        // 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
        tts.setPitch(1.0f)
        // 设置语速
//        tts.setSpeechRate(0.5f);

    }

    private var delayTime = 0
    private fun initListener() {
        tvAscMode.setOnClickListener { initDataByMode(Constants.ASCMode) }
        tvDescMode.setOnClickListener { initDataByMode(Constants.DESCMode) }
        tvAIMode.setOnClickListener { initDataByMode(Constants.AIMode) }
        tvSystemMode.setOnClickListener { initDataByMode(Constants.SystemMode) }
        tvRandomMode.setOnClickListener { initDataByMode(Constants.RandomMode) }
        tvDIYMode.setOnClickListener { initDataByMode(Constants.DIYMode) }
        mainViewModel.result.observe(this,
            Observer<String> {
                Log.e("tts observe：", "收到数据： $it")
                //Step 3:根据最后生成的数据，转化为语音间隔输出，一起玩起来吧~
                val msg = Message()
                val bundle = Bundle()
                bundle.putString("speak", it)
                msg.data = bundle
                speakHandler.sendMessageDelayed(msg, delayTime * 3000L)
                delayTime++

            })
    }

    /**
     * 根据不同的模式，生成相对应的符合规则的数据
     */
    private fun initDataByMode(mode: Int) {
        delayTime = 0
        when (mode) {
            Constants.ASCMode -> {
                /**
                 * 顺序
                 */
            }
            Constants.DESCMode -> {
                /**
                 * 倒序
                 */
            }
            Constants.AIMode -> {
                /**
                 * 智能
                 */
                mainViewModel.getRandomData(10, 20, 1, 6)
            }
            Constants.SystemMode -> {
                /**
                 * 系统
                 */
            }
            Constants.RandomMode -> {
                /**
                 * 随机
                 */
            }
            Constants.DIYMode -> {
                /**
                 * 自定义
                 */
            }
            else -> {

            }
        }

    }

    private val speakHandler: Handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            Log.e("tts handleMessage：", "收到消息，");
            msg.data?.let {
                speakText(it.getString("speak", "wrong"))
            }
        }

    }

    private fun speakText(text: String) {
        if (!tts.isSpeaking) {
            /*
                TextToSpeech的speak方法有两个重载。
                // 执行朗读的方法
                speak(CharSequence text,int queueMode,Bundle params,String utteranceId);
                // 将朗读的的声音记录成音频文件
                synthesizeToFile(CharSequence text,Bundle params,File file,String utteranceId);
                第二个参数queueMode用于指定发音队列模式，两种模式选择
                （1）TextToSpeech.QUEUE_FLUSH：该模式下在有新任务时候会清除当前语音任务，执行新的语音任务
                （2）TextToSpeech.QUEUE_ADD：该模式下会把新的语音任务放到语音任务之后，
                等前面的语音任务执行完了才会执行新的语音任务
             */
            Log.e("tts speakText：", "开始朗读");
            tts.speak(text, TextToSpeech.QUEUE_ADD, null, null);
        }
    }


    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // setLanguage
            val result: Int = tts.setLanguage(Locale.CHINA)
            val resultAnother: Int = tts.setLanguage(Locale.CHINESE)
            Log.e("tts", "result of language set is:$result  $resultAnother")
            // TextToSpeech.LANG_MISSING_DATA：表示语言的数据丢失
            // TextToSpeech.LANG_NOT_SUPPORTED：不支持
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(this, "数据丢失 或不支持", Toast.LENGTH_SHORT).show()
            } else {
                //不支持中文就将语言设置为英文
                tts.language = Locale.US
            }
        }
    }

    override fun onStop() {
        super.onStop()
        releaseTTS(false)
    }

    override fun onDestroy() {
        releaseTTS(true)
        super.onDestroy()
    }

    private fun releaseTTS(needNull: Boolean) {
        tts.run {
            //不管是否正在朗读TTS都被打断
            stop()
            //关闭，释放资源
            shutdown()
            if (!needNull) {
                return@run
            }
            null
        }
    }


}