package com.yiqiu.shirohaquiz.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.yiqiu.shirohaquiz.state.QuizRepository
import kotlin.math.atan2
import kotlin.math.cbrt
import kotlin.math.cos
import kotlin.math.hypot
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sin

/**
 * Material You（Monet）动态取色 + 种子色兜底。
 *
 * Android 12 及以上直接使用系统壁纸取色；更低版本（或用户关闭动态取色时）
 * 由 [ShirohaTonalPalette] 依据品牌种子色推导整套 M3 色调板，
 * 两条路径产出的角色色在语义与对比度上保持一致。
 */
@Composable
fun ShirohaQuizTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = QuizRepository.dynamicColorEnabled,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        else -> if (darkTheme) ShirohaStaticDark else ShirohaStaticLight
    }

    // 让既有 ShirohaColors 令牌整体跟随 M3 角色色，避免逐屏改造。
    ShirohaColors.bind(colorScheme, darkTheme)

    MaterialTheme(
        colorScheme = colorScheme,
        typography = ShirohaTypography,
        shapes = ShirohaShapes,
        content = content
    )
}

/** M3 形状尺度：整体偏大圆角，贴近 Material You 的柔和观感。 */
val ShirohaShapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp),
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(20.dp),
    extraLarge = RoundedCornerShape(28.dp)
)

/** 品牌种子色：没有动态取色能力时的兜底色相来源。 */
const val SHIROHA_SEED_ARGB: Long = 0xFF4F7CFF

val ShirohaStaticLight: ColorScheme by lazy { shirohaLightScheme(SHIROHA_SEED_ARGB) }
val ShirohaStaticDark: ColorScheme by lazy { shirohaDarkScheme(SHIROHA_SEED_ARGB) }

fun shirohaLightScheme(seedArgb: Long): ColorScheme {
    val hct = Oklch.fromArgb(seedArgb.toInt())
    val p = ShirohaTonalPalette.of(hct.hue, max(hct.chroma, 0.085))
    val s = ShirohaTonalPalette.of(hct.hue, 0.035)
    val t = ShirohaTonalPalette.of((hct.hue + 62.0) % 360.0, 0.10)
    val n = ShirohaTonalPalette.of(hct.hue, 0.006)
    val nv = ShirohaTonalPalette.of(hct.hue, 0.020)
    val e = ShirohaTonalPalette.of(25.0, 0.135)

    return lightColorScheme(
        primary = p.tone(40),
        onPrimary = p.tone(100),
        primaryContainer = p.tone(90),
        onPrimaryContainer = p.tone(10),
        inversePrimary = p.tone(80),
        secondary = s.tone(40),
        onSecondary = s.tone(100),
        secondaryContainer = s.tone(90),
        onSecondaryContainer = s.tone(10),
        tertiary = t.tone(40),
        onTertiary = t.tone(100),
        tertiaryContainer = t.tone(90),
        onTertiaryContainer = t.tone(10),
        background = n.tone(98),
        onBackground = n.tone(10),
        surface = n.tone(98),
        onSurface = n.tone(10),
        surfaceVariant = nv.tone(90),
        onSurfaceVariant = nv.tone(30),
        surfaceTint = p.tone(40),
        surfaceBright = n.tone(98),
        surfaceDim = n.tone(87),
        surfaceContainerLowest = n.tone(100),
        surfaceContainerLow = n.tone(96),
        surfaceContainer = n.tone(94),
        surfaceContainerHigh = n.tone(92),
        surfaceContainerHighest = n.tone(90),
        inverseSurface = n.tone(20),
        inverseOnSurface = n.tone(95),
        error = e.tone(40),
        onError = e.tone(100),
        errorContainer = e.tone(90),
        onErrorContainer = e.tone(10),
        outline = nv.tone(50),
        outlineVariant = nv.tone(80),
        scrim = Color.Black
    )
}

fun shirohaDarkScheme(seedArgb: Long): ColorScheme {
    val hct = Oklch.fromArgb(seedArgb.toInt())
    val p = ShirohaTonalPalette.of(hct.hue, max(hct.chroma, 0.085))
    val s = ShirohaTonalPalette.of(hct.hue, 0.035)
    val t = ShirohaTonalPalette.of((hct.hue + 62.0) % 360.0, 0.10)
    val n = ShirohaTonalPalette.of(hct.hue, 0.006)
    val nv = ShirohaTonalPalette.of(hct.hue, 0.020)
    val e = ShirohaTonalPalette.of(25.0, 0.135)

    return darkColorScheme(
        primary = p.tone(80),
        onPrimary = p.tone(20),
        primaryContainer = p.tone(30),
        onPrimaryContainer = p.tone(90),
        inversePrimary = p.tone(40),
        secondary = s.tone(80),
        onSecondary = s.tone(20),
        secondaryContainer = s.tone(30),
        onSecondaryContainer = s.tone(90),
        tertiary = t.tone(80),
        onTertiary = t.tone(20),
        tertiaryContainer = t.tone(30),
        onTertiaryContainer = t.tone(90),
        background = n.tone(6),
        onBackground = n.tone(90),
        surface = n.tone(6),
        onSurface = n.tone(90),
        surfaceVariant = nv.tone(30),
        onSurfaceVariant = nv.tone(80),
        surfaceTint = p.tone(80),
        surfaceBright = n.tone(24),
        surfaceDim = n.tone(6),
        surfaceContainerLowest = n.tone(4),
        surfaceContainerLow = n.tone(10),
        surfaceContainer = n.tone(12),
        surfaceContainerHigh = n.tone(17),
        surfaceContainerHighest = n.tone(22),
        inverseSurface = n.tone(90),
        inverseOnSurface = n.tone(20),
        error = e.tone(80),
        onError = e.tone(20),
        errorContainer = e.tone(30),
        onErrorContainer = e.tone(90),
        outline = nv.tone(60),
        outlineVariant = nv.tone(30),
        scrim = Color.Black
    )
}

/** OKLCh 描述：色相（度）/ 彩度 / 亮度。 */
internal data class Oklch(val hue: Double, val chroma: Double, val lightness: Double) {
    companion object {
        fun fromArgb(argb: Int): Oklch {
            val r = linearOf((argb shr 16) and 0xFF)
            val g = linearOf((argb shr 8) and 0xFF)
            val b = linearOf(argb and 0xFF)
            val lab = OklchMath.linearToOklab(r, g, b)
            val c = hypot(lab[1], lab[2])
            var h = Math.toDegrees(atan2(lab[2], lab[1]))
            if (h < 0) h += 360.0
            return Oklch(h, c, lab[0])
        }

        private fun linearOf(channel: Int): Double {
            val v = channel / 255.0
            return if (v <= 0.04045) v / 12.92 else ((v + 0.055) / 1.055).pow(2.4)
        }
    }
}

/**
 * M3 色调板：给定色相与彩度，按 CIE L*（即 M3 的 tone）取值，
 * 超出 sRGB 色域时二分裁剪彩度，保证输出永远合法。
 */
internal class ShirohaTonalPalette private constructor(
    private val hue: Double,
    private val chroma: Double
) {
    private val cache = HashMap<Int, Color>()

    fun tone(tone: Int): Color {
        val key = tone * 10
        cache[key]?.let { return it }
        val color = OklchMath.tone(hue, chroma, tone.toDouble())
        cache[key] = color
        return color
    }

    companion object {
        fun of(hue: Double, chroma: Double) = ShirohaTonalPalette(hue, chroma)
    }
}

internal object OklchMath {
    fun tone(hue: Double, chroma: Double, tone: Double): Color {
        val y = if (tone > 8.0) ((tone + 16.0) / 116.0).pow(3) else tone / 903.2962962
        val okL = cbrt(max(0.0, min(1.0, y)))
        val c = maxChroma(okL, hue, chroma)
        val rad = Math.toRadians(hue)
        val rgb = oklabToLinear(okL, c * cos(rad), c * sin(rad))
        return Color(
            red = channel(rgb[0]),
            green = channel(rgb[1]),
            blue = channel(rgb[2]),
            alpha = 255
        )
    }

    private fun channel(linear: Double): Int {
        val encoded = if (linear <= 0.0031308) {
            linear * 12.92
        } else {
            1.055 * linear.pow(1.0 / 2.4) - 0.055
        }
        return (encoded * 255.0).roundToInt().coerceIn(0, 255)
    }

    private fun maxChroma(okL: Double, hue: Double, target: Double): Double {
        if (target <= 0.0) return 0.0
        if (inGamut(okL, hue, target)) return target
        var lo = 0.0
        var hi = target
        repeat(18) {
            val mid = (lo + hi) / 2.0
            if (inGamut(okL, hue, mid)) lo = mid else hi = mid
        }
        return lo
    }

    private fun inGamut(okL: Double, hue: Double, chroma: Double): Boolean {
        val rad = Math.toRadians(hue)
        val rgb = oklabToLinear(okL, chroma * cos(rad), chroma * sin(rad))
        return rgb[0] >= -0.0005 && rgb[0] <= 1.0005 &&
            rgb[1] >= -0.0005 && rgb[1] <= 1.0005 &&
            rgb[2] >= -0.0005 && rgb[2] <= 1.0005
    }

    fun linearToOklab(r: Double, g: Double, b: Double): DoubleArray {
        val l = 0.4122214708 * r + 0.5363325363 * g + 0.0514459929 * b
        val m = 0.2119034982 * r + 0.6806995451 * g + 0.1073969566 * b
        val s = 0.0883024619 * r + 0.2817188376 * g + 0.6299787005 * b
        val l_ = cbrt(l)
        val m_ = cbrt(m)
        val s_ = cbrt(s)
        return doubleArrayOf(
            0.2104542553 * l_ + 0.7936177850 * m_ - 0.0040720468 * s_,
            1.9779984951 * l_ - 2.4285922050 * m_ + 0.4505937099 * s_,
            0.0259040371 * l_ + 0.7827717662 * m_ - 0.8086757660 * s_
        )
    }

    fun oklabToLinear(okL: Double, a: Double, b: Double): DoubleArray {
        val l_ = okL + 0.3963377774 * a + 0.2158037573 * b
        val m_ = okL - 0.1055613458 * a - 0.0638541728 * b
        val s_ = okL - 0.0894841775 * a - 1.2914855480 * b
        val l = l_ * l_ * l_
        val m = m_ * m_ * m_
        val s = s_ * s_ * s_
        return doubleArrayOf(
            4.0767416621 * l - 3.3077115913 * m + 0.2309699292 * s,
            -1.2684380046 * l + 2.6097574011 * m - 0.3413193965 * s,
            -0.0041960863 * l - 0.7034186147 * m + 1.7076147010 * s
        )
    }
}
