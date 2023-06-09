package cn.lyric.getter.api.demo


import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import cn.lyric.getter.api.data.DataType
import cn.lyric.getter.api.tools.EventTools.receptionLyric
import cn.lyric.getter.api.tools.Tools.base64ToDrawable

class MainActivity : Activity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        receptionLyric(applicationContext) {
            findViewById<TextView>(R.id.lyric).text = "Lyric：${if (it.type == DataType.UPDATE) it.lyric else " 暂停播放 "}"
            findViewById<TextView>(R.id.type).text = "Type：${it.type}"
            findViewById<TextView>(R.id.customIcon).text = "CustomIcon：${it.customIcon}"
            findViewById<TextView>(R.id.serviceName).text = "ServiceName：${it.serviceName}"
            findViewById<TextView>(R.id.packageName).text = "PackageName：${it.packageName}"
            findViewById<TextView>(R.id.base64Icon).text = "Base64Icon：${it.base64Icon}"
            findViewById<TextView>(R.id.useOwnMusicController).text = "useOwnMusicController：${it.useOwnMusicController}"
            findViewById<TextView>(R.id.toString).text = "toString：$it"
            if (it.customIcon) {
                findViewById<ImageView>(R.id.icon).setImageBitmap(base64ToDrawable(it.base64Icon))
            }

        }

    }
}