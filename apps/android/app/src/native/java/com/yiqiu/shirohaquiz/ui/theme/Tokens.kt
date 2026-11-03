package com.yiqiu.shirohaquiz.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * 兼容层：既有界面全部通过 ShirohaColors 取色。
 *
 * 这里把每个令牌映射到当前 Material 3 角色色（含 Monet 动态取色），
 * 因此只要 [ShirohaQuizTheme] 换配色，全 App 的底色/卡片/文字/描边
 * 会一起切换，无需逐屏改造。
 *
 * scheme 用 Snapshot 状态持有：读取令牌即可触发重组。
 */
object ShirohaColors {
    var isDarkMode: Boolean = false
        private set

    private var schemeState by mutableStateOf<ColorScheme>(fallbackScheme(false))

    private val scheme: ColorScheme
        get() = schemeState

    private fun fallbackScheme(dark: Boolean): ColorScheme =
        if (dark) darkColorScheme() else androidx.compose.material3.lightColorScheme()

    internal fun bind(scheme: ColorScheme, dark: Boolean) {
        isDarkMode = dark
        if (schemeState !== scheme) schemeState = scheme
    }

    // ---------- 底与容器 ----------
    val BgApp: Color get() = scheme.surface
    val BgElevated: Color get() = scheme.surfaceContainerLow

    /** 专注/沉浸态底色：比普通底色更深一档。 */
    val BgDeepFocus: Color
        get() = if (isDarkMode) scheme.surfaceContainerLowest else scheme.surfaceContainer

    val BgGradientTop: Color get() = scheme.surface
    val BgGradientMiddle: Color get() = scheme.surface
    val BgGradientBottom: Color get() = scheme.surface

    val CardGlass: Color get() = scheme.surfaceContainer
    val CardSoft: Color get() = scheme.surfaceContainerLow
    val CardMuted: Color get() = scheme.surfaceContainerHigh

    val CardWhite84: Color get() = scheme.surfaceContainerLow
    val CardWhite86: Color get() = scheme.surfaceContainerLow
    val CardWhite78: Color get() = scheme.surfaceContainer
    val CardWhite72: Color get() = scheme.surfaceContainer
    val CardWhite68: Color get() = scheme.surfaceContainerHigh
    val CardWhite62: Color get() = scheme.surfaceContainerHigh

    val BottomBar: Color get() = scheme.surfaceContainer

    // ---------- 分隔与描边 ----------
    val LineSoft: Color get() = scheme.outlineVariant
    val LineStrong: Color get() = scheme.outline
    val LineSelected: Color get() = scheme.primary

    // ---------- 文字 ----------
    val TextPrimary: Color get() = scheme.onSurface
    val TextSecondary: Color get() = scheme.onSurfaceVariant
    val TextTertiary: Color
        get() = scheme.onSurfaceVariant.copy(alpha = 0.72f)
    val TextOnBrand: Color get() = scheme.onPrimary
    val TextWarning: Color
        get() = if (isDarkMode) Color(0xFFF7C978) else Color(0xFF7A4F00)
    val IconWarning: Color
        get() = if (isDarkMode) Color(0xFFF4B348) else Color(0xFF9A6700)

    // ---------- 品牌 ----------
    val BrandPrimary: Color get() = scheme.primary
    val BrandPrimarySoft: Color get() = scheme.primaryContainer
    val BrandSecondary: Color get() = scheme.secondary

    val OptionLabelIdle: Color get() = scheme.surfaceContainerHighest

    // ---------- 语义色（M3 未内置 success，保留稳定语义） ----------
    val StateSuccess: Color
        get() = if (isDarkMode) Color(0xFF6FE0A8) else Color(0xFF116B45)
    val StateSuccessSoft: Color
        get() = if (isDarkMode) Color(0xFF17362A) else Color(0xFFDCF5E8)
    val StateWarning: Color
        get() = if (isDarkMode) Color(0xFFF9B955) else Color(0xFF8A5A00)
    val StateWarningSoft: Color
        get() = if (isDarkMode) Color(0xFF3A2C13) else Color(0xFFFFF3DC)
    val StateDanger: Color get() = scheme.error
    val StateDangerSoft: Color get() = scheme.errorContainer
}

object ShirohaRadius {
    val Sm = 12.dp
    val Md = 16.dp
    val Lg = 22.dp
    val Xl = 26.dp
    val Pill = 999.dp
}

object ShirohaSpacing {
    val Xs = 4.dp
    val Sm = 8.dp
    val Md = 12.dp
    val Lg = 16.dp
    val Xl = 20.dp
    val Xxl = 24.dp
    val Xxxl = 32.dp
}

object ShirohaDimens {
    val Hairline = 1.dp
    val PageHorizontalPadding = ShirohaSpacing.Xl
    val PageVerticalPadding = ShirohaSpacing.Sm

    val HeroCardHeight = 132.dp
    val HeroImageSize = 92.dp
    val HeroImageFrameSize = 100.dp
    val HeroImageFrameExtra = 8.dp
    val HeroImageAlpha = 0.92f

    val StepPillWidth = 136.dp
    val StepPillMinHeight = 28.dp
    val StepPillHorizontalPadding = 10.dp
    val StepPillVerticalPadding = 5.dp

    val StatusChipMinHeight = 30.dp
    val StatusChipHorizontalPadding = 11.dp
    val StatusChipVerticalPadding = 6.dp

    /** M3 按钮标准高度：48dp 满足最小点击区域。 */
    val ActionButtonMinHeight = 48.dp
    val ActionButtonIconSize = 18.dp
    val ActionButtonHorizontalPadding = 16.dp
    val ActionButtonEqualHorizontalPadding = 10.dp
    val ActionButtonIconTextGap = 8.dp
    val ActionButtonEqualIconTextGap = 6.dp
    val DisabledAlpha = 0.38f

    /** 答题页底部操作栏：固定高度，保证判题前后不重排。 */
    val QuizActionBarHeight = 76.dp
    val QuizActionBarHorizontalPadding = 12.dp

    val OptionCardHorizontalPadding = 14.dp
    val OptionCardVerticalPadding = 12.dp
    val OptionLabelSize = 30.dp
    val OptionLabelTextGap = 12.dp
    val OptionRowMinHeight = 58.dp

    val BottomBarHorizontalPadding = 8.dp
    val BottomBarVerticalPadding = 4.dp
    val BottomNavItemGap = 4.dp
    val BottomNavItemHeight = 56.dp
    val BottomNavItemHorizontalPadding = 4.dp
    val BottomNavIconSize = 22.dp
    val BottomNavIconSelectedScale = 1.02f

    val EmptyStateImageSize = 140.dp
    val LoadingImageSize = 112.dp
}

object ShirohaMotion {
    const val PageTransitionMillis = 180
    const val PageFadeOutMillis = 90
    const val BottomNavMillis = 140
    const val HeroFloatMillis = 1700
    const val LoadingScaleMillis = 900
    const val SplashFadeMillis = 420
    const val SplashHoldMillis = 1450

    const val PageTransitionOffsetPx = 6
    val HeroFloatDistance = 2.6.dp
    const val LoadingScaleMin = 0.98f
    const val LoadingScaleMax = 1.02f
}
