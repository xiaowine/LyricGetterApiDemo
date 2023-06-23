package cn.lyric.getter.api.demo


import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import cn.lyric.getter.api.data.DataType
import cn.lyric.getter.api.tools.EventTools
import cn.lyric.getter.api.tools.Tools

class MainActivity : Activity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.activation).text = "激活状态：${EventTools.hasEnable()}"
        findViewById<Button>(R.id.send).setOnClickListener {
            EventTools.sendLyric(applicationContext, "${(0..1000).random()}${getString(R.string.app_name)}", applicationContext.packageName)
        }
        findViewById<Button>(R.id.clean).setOnClickListener {
            EventTools.stopLyric(applicationContext)
        }

        Tools.receptionLyric(applicationContext) {
            findViewById<TextView>(R.id.lyric).text = "Lyric：${if (it.type == DataType.UPDATE) it.lyric else " 暂停播放 "}"
            findViewById<TextView>(R.id.type).text = "Type：${it.type}"
            findViewById<TextView>(R.id.customIcon).text = "CustomIcon：${it.customIcon}"
            findViewById<TextView>(R.id.serviceName).text = "ServiceName：${it.serviceName}"
            findViewById<TextView>(R.id.packageName).text = "PackageName：${it.packageName}"
            findViewById<TextView>(R.id.base64Icon).text = "Base64Icon：${it.base64Icon}"
            findViewById<TextView>(R.id.useOwnMusicController).text = "useOwnMusicController：${it.useOwnMusicController}"
            findViewById<TextView>(R.id.toString).text = "toString：$it"
            if (it.customIcon) {
                findViewById<ImageView>(R.id.icon).setImageBitmap(Tools.base64ToDrawable(it.base64Icon))
            }

        }

    }

    override fun onDestroy() {
        EventTools.stopLyric(applicationContext)
        super.onDestroy()
    }
}