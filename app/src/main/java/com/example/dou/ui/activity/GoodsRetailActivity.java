package com.example.dou.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dou.App;
import com.example.dou.R;
import com.example.dou.pojo.User;
import com.example.dou.pojo.Video;
import com.example.dou.utils.HttpUtil;
import com.example.dou.widget.ObservableScrollView;

import java.io.IOException;

import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class GoodsRetailActivity extends AppCompatActivity {

    TextView addBuyCar;
    ImageView ivBack;
    Toolbar toolbar;
    ObservableScrollView scrollView;
    LinearLayout lvBottom;
    ImageView ivMore;
    ImageView ivShoppingCart;
    LinearLayout content;
    View spiteLine;
    ImageView ivHeader;
    LinearLayout lvHeader;
    TextView goodsText;
    TextView goodsPrice;
    Video goods;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_retail);
        init();
        initView();
    }

    private void init() {
        user = ((App)getApplication()).getUser();
        scrollView=findViewById(R.id.scrollView);
        toolbar=findViewById(R.id.toolbar);
        ivBack=findViewById(R.id.iv_back);
        lvBottom=findViewById(R.id.lv_bottom);
        ivMore=findViewById(R.id.iv_more);
        ivShoppingCart=findViewById(R.id.iv_shopping_cart);
        content=findViewById(R.id.content);
        spiteLine=findViewById(R.id.spite_line);
        ivHeader=findViewById(R.id.iv_header);
        lvHeader=findViewById(R.id.lv_header);
        addBuyCar=findViewById(R.id.addBuyCar);
        goodsText=findViewById(R.id.goods_text);
        goodsPrice=findViewById(R.id.goods_price);
        goods= (Video) getIntent().getSerializableExtra("goods");
        goodsText.setText(goods.getInfo());
        goodsPrice.setText("价格："+goods.getGoodsPrice());
        Glide.with(this).load(goods.getImageUrl())
                .into(ivHeader);
        ivShoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GoodsRetailActivity.this,BuyCarActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });
        addBuyCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url= HttpUtil.host+"addCartFromUserIdAndVideoId";
                HttpUtil.addBuyCarHttp(url,user.getUserId(),goods.getVideoId().toString(), new okhttp3.Callback() {
                    @Override
                    public void onFailure(final Call call, final IOException e) {

                    }

                    @Override
                    public void onResponse(final Call call, final Response response) throws IOException {
                        if(response.body().string().equals("true")){
                            new Handler(getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(GoodsRetailActivity.this,"成功加入购物车",Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    }
                });
            }
        });
    }

    private void initView() {

        //获取dimen属性中 标题和头部图片的高度
        final float title_height = getResources().getDimension(R.dimen.title_height);
        final float head_height = getResources().getDimension(R.dimen.head_height);

        //滑动事件回调监听（一次滑动的过程一般会连续触发多次）
        scrollView.setOnScrollListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScroll(int oldy, int dy, boolean isUp) {

                float move_distance = head_height - title_height;
                if (!isUp && dy <= move_distance) {//手指往上滑,距离未超过200dp
                    //标题栏逐渐从透明变成不透明
                    toolbar.setBackgroundColor(ContextCompat.getColor(GoodsRetailActivity.this, R.color.color_white));
                    TitleAlphaChange(dy, move_distance);//标题栏渐变
                    HeaderTranslate(dy);//图片视差平移

                } else if (!isUp && dy > move_distance) {//手指往上滑,距离超过200dp
                    TitleAlphaChange(1, 1);//设置不透明百分比为100%，防止因滑动速度过快，导致距离超过200dp,而标题栏透明度却还没变成完全不透的情况。

                    HeaderTranslate(head_height);//这里也设置平移，是因为不设置的话，如果滑动速度过快，会导致图片没有完全隐藏。

                    ivBack.setImageResource(R.mipmap.ic_back_dark);
                    ivMore.setImageResource(R.mipmap.ic_more_dark);
                    ivShoppingCart.setImageResource(R.mipmap.ic_shopping_dark);
                    spiteLine.setVisibility(View.VISIBLE);

                } else if (isUp && dy > move_distance) {//返回顶部，但距离头部位置大于200dp
                    //不做处理

                } else if (isUp && dy <= move_distance) {//返回顶部，但距离头部位置小于200dp
                    //标题栏逐渐从不透明变成透明
                    TitleAlphaChange(dy, move_distance);//标题栏渐变
                    HeaderTranslate(dy);//图片视差平移

                    ivBack.setImageResource(R.mipmap.ic_back);
                    ivMore.setImageResource(R.mipmap.ic_more);
                    ivShoppingCart.setImageResource(R.mipmap.ic_shopping_cart);
                    spiteLine.setVisibility(View.GONE);
                }
            }
        });
    }

    private void HeaderTranslate(float distance) {
        lvHeader.setTranslationY(-distance);
        ivHeader.setTranslationY(distance/2);
    }

    private void TitleAlphaChange(int dy, float mHeaderHeight_px) {//设置标题栏透明度变化
        float percent = (float) Math.abs(dy) / Math.abs(mHeaderHeight_px);
        //如果是设置背景透明度，则传入的参数是int类型，取值范围0-255
        //如果是设置控件透明度，传入的参数是float类型，取值范围0.0-1.0
        //这里我们是设置背景透明度就好，因为设置控件透明度的话，返回ICON等也会变成透明的。
        //alpha 值越小越透明
        int alpha = (int) (percent * 255);
        toolbar.getBackground().setAlpha(alpha);//设置控件背景的透明度，传入int类型的参数（范围0~255）

        ivBack.getBackground().setAlpha(255 - alpha);
        ivMore.getBackground().setAlpha(255 - alpha);
        ivShoppingCart.getBackground().setAlpha(255 - alpha);
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
