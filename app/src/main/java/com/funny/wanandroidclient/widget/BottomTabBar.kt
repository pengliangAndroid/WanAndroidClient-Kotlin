package com.funny.wanandroidclient.widget

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.funny.wanandroidclient.R


/**
 * @author pengl
 */
class BottomTabBar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {

    private var tabBackgroundColor: Int = 0

    /**
     * tab高度
     */
    private var tabBarHeight: Int = 0

    /**
     * Tab选中的文字颜色，默认颜色#fe5977
     */
    private var selectedColor: Int = 0

    /**
     * Tab未选中的文字颜色，默认颜色#666666
     */
    private var unSelectedColor: Int = 0

    /**
     * 文字尺寸
     */
    private var fontSize: Int = 0

    /**
     * tab的图片宽高
     */
    private var imgWidth: Int = 0
    private var imgHeight: Int = 0


    /**
     * tab的文字顶部间距
     */
    private var tabTextMarginTop: Int = 0


    /**
     * tab的消息布局宽度
     */
    private var tabMsgWidth: Int = 0

    /**
     * tab的消息布局顶部间距
     */
    private var tabMsgMarginTop: Int = 0


    private var tabItemList: MutableList<TabItem>? = ArrayList()

    var selectPosition = -1
        private set


    private var containerViewId = -1

    private var fragmentList: List<Fragment>? = null

    private var fragmentManager: FragmentManager? = null

    private var onTabChangedListener: OnTabChangedListener? = null


    private val internalListener = object : View.OnClickListener {
        override fun onClick(v: View) {
            val index = v.getTag() as Int

            setCurrentTab(index)
        }
    }

    interface OnTabChangedListener {
        fun onTabChanged(position: Int)
    }




    init {
        initAttrs(context, attrs)
    }

    private fun initAttrs(context: Context, attrs: AttributeSet?) {
        val array : TypedArray ? = context.obtainStyledAttributes(attrs, R.styleable.BottomTabBar)
        if (array != null) {
            tabBackgroundColor = array.getColor(R.styleable.BottomTabBar_tab_bar_background, Color.WHITE)
            selectedColor = array.getColor(R.styleable.BottomTabBar_tab_selected_color, DEFAULT_SELECTED_COLOR)
            unSelectedColor = array.getColor(R.styleable.BottomTabBar_tab_unselected_color, DEFAULT_UNSELECTED_COLOR)

            tabBarHeight = array.getDimensionPixelSize(R.styleable.BottomTabBar_tab_bar_height, dp2px(DEFAULT_HEIGHT))
            fontSize = array.getDimensionPixelSize(R.styleable.BottomTabBar_tab_font_size, dp2px(DEFAULT_TEXT_SIZE))
            imgWidth = array.getDimensionPixelSize(R.styleable.BottomTabBar_tab_img_width, dp2px(DEFAULT_HEIGHT_WITH_ICON))
            imgHeight = array.getDimensionPixelSize(R.styleable.BottomTabBar_tab_img_height, dp2px(DEFAULT_HEIGHT_WITH_ICON))

            tabTextMarginTop = array.getDimensionPixelSize(R.styleable.BottomTabBar_tab_text_margin_top, dp2px(DEFAULT_GAP_TEXT))
            tabMsgMarginTop = array.getDimensionPixelSize(R.styleable.BottomTabBar_tab_msg_margin_top, dp2px(DEFAULT_GAP_MSG))
            tabMsgWidth = array.getDimensionPixelSize(R.styleable.BottomTabBar_tab_msg_width, dp2px(DEFAULT_WIDTH_MSG))

            array.recycle()
        }

        initTabBar()
    }

    private fun initTabBar() {
        orientation = LinearLayout.HORIZONTAL
        gravity = Gravity.CENTER
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                tabBarHeight)
        setLayoutParams(layoutParams)
        setBackgroundColor(tabBackgroundColor)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val heightMode = View.MeasureSpec.getMode(heightMeasureSpec)
        var heightSize = View.MeasureSpec.getSize(heightMeasureSpec)

        if (heightMode == View.MeasureSpec.AT_MOST) {
            heightSize = tabBarHeight
        }

        setMeasuredDimension(widthMeasureSpec, View.MeasureSpec.makeMeasureSpec(heightSize, heightMode))
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        Log.d("onSizeChanged","width:$w,height:$h")
    }


    fun addTabItem(item: TabItem): BottomTabBar {
        addTabItemView(item)
        return this
    }

    fun setupFragmets(containerViewId: Int, fm: FragmentManager?, fragments: List<Fragment>?): BottomTabBar {
        if (fm == null)
            throw RuntimeException("FragmentManager is null")

        if (fragments == null)
            throw RuntimeException("fragments is null")

        this.containerViewId = containerViewId
        this.fragmentManager = fm
        this.fragmentList = fragments
        return this
    }

    fun setCurrentTab(position: Int) {
        if (selectPosition == position)
            return

        selectPosition = position

        setSelectedFragment(position)
        for (i in tabItemList!!.indices) {
            setSelectedTabItem(tabItemList!![i], position)
        }

        if (onTabChangedListener != null) {
            onTabChangedListener!!.onTabChanged(position)
        }
    }


    fun setOnTabChangeListener(listener: OnTabChangedListener): BottomTabBar {
        this.onTabChangedListener = listener
        return this
    }


    fun setTabBarHeight(tabBarHeight: Int): BottomTabBar {
        this.tabBarHeight = tabBarHeight
        return this
    }

    fun setTextColor(selectedColor: Int, unSelectedColor: Int): BottomTabBar {
        this.selectedColor = selectedColor
        this.unSelectedColor = unSelectedColor
        return this
    }

    fun setFontSize(fontSize: Int): BottomTabBar {
        this.fontSize = fontSize
        return this
    }

    fun setImgSize(imgWidth: Int, imgHeight: Int): BottomTabBar {
        this.imgWidth = imgWidth
        this.imgHeight = imgHeight
        return this
    }

    fun setTabTextMarginTop(tabTextMarginTop: Int): BottomTabBar {
        this.tabTextMarginTop = tabTextMarginTop
        return this
    }

    fun setTabMsgWidth(tabMsgWidth: Int): BottomTabBar {
        this.tabMsgWidth = tabMsgWidth
        return this
    }

    fun setTabMsgMarginTop(tabMsgMarginTop: Int): BottomTabBar {
        this.tabMsgMarginTop = tabMsgMarginTop
        return this
    }

    fun getTabViewHolder(index: Int): TabViewHolder? {
        val item = tabItemList!![index]
        return if (item == null || item.tabViewHolder == null) null else tabItemList!![index].tabViewHolder

    }

    fun newTabItem(text: String, selectedResId: Int, unselectedResID: Int): TabItem {
        return newTabItem(text, ContextCompat.getDrawable(context, selectedResId),
                ContextCompat.getDrawable(context, unselectedResID))
    }

    fun newTabItem(text: String, selectedIcon: Drawable?, unselectedIcon: Drawable?): TabItem {
        return TabItem(text, selectedIcon, unselectedIcon)
    }

    private fun addTabItemView(tabItem: TabItem) {
        val itemView = LayoutInflater.from(context).inflate(R.layout.bottom_tab_bar_item, null)
        val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 1.0f)
        itemView.setLayoutParams(lp)


        val msgLayout = itemView.findViewById(R.id.fl_msg_layout) as FrameLayout
        val msgView = itemView.findViewById(R.id.iv_msg) as ImageView
        val iconView = itemView.findViewById(R.id.tab_bar_img) as ImageView
        val textView = itemView.findViewById(R.id.tab_bar_tv) as TextView

        val viewHolder = TabViewHolder(iconView,textView,msgLayout,msgView)

        viewHolder.iconView.setLayoutParams(LinearLayout.LayoutParams(imgWidth, imgHeight))
        viewHolder.iconView.setImageDrawable(tabItem.unselectedIcon)

        val params = viewHolder.textView.layoutParams as MarginLayoutParams
        params.topMargin = tabTextMarginTop
        viewHolder.textView.layoutParams = params
        viewHolder.textView.text = tabItem.text
        viewHolder.textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, fontSize.toFloat())
        viewHolder.textView.setTextColor(unSelectedColor)

        viewHolder.msgLayout.layoutParams = RelativeLayout.LayoutParams(tabMsgWidth,
                RelativeLayout.LayoutParams.MATCH_PARENT)

        val params2 = viewHolder.msgView.getLayoutParams() as MarginLayoutParams
        params2.topMargin = tabMsgMarginTop
        viewHolder.msgView.setLayoutParams(params2)

        itemView.setTag(tabItemList!!.size)
        itemView.setOnClickListener(internalListener)

        tabItem.tabViewHolder = viewHolder
        tabItem.position = tabItemList!!.size
        tabItemList!!.add(tabItem)

        addView(itemView)
    }

    private fun setSelectedTabItem(item: TabItem?, position: Int) {
        if (item == null || item.tabViewHolder == null)
            return

        val holder = item.tabViewHolder
        val selected = position == item.position

        holder!!.iconView.setImageDrawable(if (selected) item.selectIcon else item.unselectedIcon)
        holder.textView.setTextColor(if (selected) selectedColor else unSelectedColor)
    }

    private fun setSelectedFragment(position: Int) {
        if (fragmentList == null || fragmentManager == null)
            return

        val fragment = fragmentList!![position]
        val transaction = fragmentManager!!.beginTransaction()
        hideFragments(transaction)

        if (fragmentManager!!.getFragments() != null && fragmentManager!!.getFragments().contains(fragment)) {
            transaction.show(fragment)
        } else {
            transaction.add(containerViewId, fragment, fragment.javaClass.getSimpleName())
            transaction.show(fragment)
        }

        transaction.commitAllowingStateLoss()
    }

    private fun hideFragments(transaction: FragmentTransaction) {
        if (fragmentList == null)
            return

        for (i in fragmentList!!.indices) {
            if (fragmentList!![i] == null)
                continue

            transaction.hide(fragmentList!![i])
        }
    }

    private fun dp2px(dps: Int): Int {
        return Math.round(resources.displayMetrics.density * dps)
    }

    fun clear() {
        fragmentList = null
        fragmentManager = null
        tabItemList = null
    }

    class TabItem(var text: CharSequence?, var selectIcon: Drawable?, var unselectedIcon: Drawable?) {
        var tag: Any? = null

        var position = -1

        var tabViewHolder: TabViewHolder? = null
    }

    data class TabViewHolder(var iconView: ImageView, var textView: TextView, var msgLayout: FrameLayout, var msgView: ImageView) /* {
        var iconView: ImageView? = null
        var textView: TextView? = null
        var msgLayout: FrameLayout? = null
        var msgView: ImageView? = null

        constructor() {}

        constructor(iconView: ImageView, textView: TextView, msgLayout: FrameLayout, msgView: ImageView) {
            this.iconView = iconView
            this.textView = textView
            this.msgLayout = msgLayout
            this.msgView = msgView
        }
    }*/

    companion object {

        private val DEFAULT_SELECTED_COLOR = -0x1a689

        private val DEFAULT_UNSELECTED_COLOR = -0x99999a

        private val DEFAULT_TEXT_SIZE = 12 //sp

        private val DEFAULT_HEIGHT_WITH_ICON = 20 // dps

        private val DEFAULT_GAP_TEXT = 3 // dps

        private val DEFAULT_GAP_MSG = 4 // dps

        private val DEFAULT_HEIGHT = 52 // dps

        private val DEFAULT_WIDTH_MSG = 46 // dps
    }

}