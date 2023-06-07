package cn.lyrics.getter.api.demo


import android.app.Activity
import android.os.Bundle
import cn.lyrics.getter.api.EventTools.receptionLyric
import cn.lyrics.getter.api.data.DataType

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        receptionLyric(applicationContext) {
            title = if (it.type==DataType.UPDATE) it.lyric else "暂停播放"
        }

    }
}