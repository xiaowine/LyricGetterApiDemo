package cn.lyrics.getter.api

import android.annotation.SuppressLint
import android.content.Context
import android.content.IntentFilter
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.util.Base64
import cn.lyrics.getter.api.data.LyricData
import java.io.ByteArrayOutputStream

object EventTools {
    fun hasEnable(): Boolean {
        return false
    }

    /**
     * 可拉以base64
     *
     * @param drawable 图片
     * @return [String] 返回图片的Base64
     */
    fun drawableToBase64(drawable: BitmapDrawable): String {
        val bitmap = drawable.bitmap
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val bytes = stream.toByteArray()
        return Base64.encodeToString(bytes, Base64.DEFAULT)
    }

    /**
     * 发送歌词
     *
     * @param context     Context
     * @param lyric       歌词
     * @param packageName 音乐包名
     */
    fun sendLyric(context: Context?, lyric: String?, packageName: String?) {
        sendLyric(context, lyric, false, "", false, "", packageName)
    }

    /**
     * 把歌词
     *
     * @param context               Context
     * @param lyric                 歌词
     * @param customIcon            是否传入自定义图标
     * @param base64Icon            Base64图标，仅在customIcon为true时生效
     * @param useOwnMusicController 音乐软件自己控制歌词暂停
     * @param serviceName           音乐服务名称，仅在useOwnMusicController为false时生效
     * @param packageName           音乐包名
     */
    fun sendLyric(context: Context?, lyric: String?, customIcon: Boolean, base64Icon: String?, useOwnMusicController: Boolean, serviceName: String?, packageName: String?) {}


    /**
     * 接待抒情
     * @param [context] Context
     * @param [callback] 收到歌词的回调
     */
    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    fun receptionLyric(context: Context, callback: (LyricData) -> Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.registerReceiver(LyricReceiver(callback), IntentFilter().apply {
                addAction("Lyric_Data")
            }, Context.RECEIVER_EXPORTED)
        } else {
            context.registerReceiver(LyricReceiver(callback), IntentFilter().apply {
                addAction("Lyric_Data")
            })
        }
    }
}
