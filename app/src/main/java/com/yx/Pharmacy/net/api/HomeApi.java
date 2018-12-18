package com.yx.Pharmacy.net.api;

import com.yx.Pharmacy.base.BasisBean;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.model.AddShopCartModel;
import com.yx.Pharmacy.model.AddressModel;
import com.yx.Pharmacy.model.AskModel;
import com.yx.Pharmacy.model.CouponData;
import com.yx.Pharmacy.model.CouponModel;
import com.yx.Pharmacy.model.CreateOrderModel;
import com.yx.Pharmacy.model.CreditData;
import com.yx.Pharmacy.model.DrugModel;
import com.yx.Pharmacy.model.HomeAdvanceModel;
import com.yx.Pharmacy.model.HomeDataModel;
import com.yx.Pharmacy.model.LoginModel;
import com.yx.Pharmacy.model.MessageData;
import com.yx.Pharmacy.model.MyOrderNumModel;
import com.yx.Pharmacy.model.MyShopModel;
import com.yx.Pharmacy.model.OrderModel;
import com.yx.Pharmacy.model.PayOrderModel;
import com.yx.Pharmacy.model.ProductDetailModel;
import com.yx.Pharmacy.model.SaleRecordModel;
import com.yx.Pharmacy.model.SavaCouponModel;
import com.yx.Pharmacy.model.ShopCartModel;
import com.yx.Pharmacy.model.SplashData;
import com.yx.Pharmacy.model.StoreDetailModel;
import com.yx.Pharmacy.model.StoreTypeModel;
import com.yx.Pharmacy.model.TagModel;
import com.yx.Pharmacy.model.UploadModel;
import com.yx.Pharmacy.model.UrlBean;
import com.yx.Pharmacy.model.WalletData;
import com.yx.Pharmacy.model.WuliuData;
import com.yx.Pharmacy.model.YaoType1;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * @author mcx
 * Created by Administrator on 2017/5/8.
 */
public interface HomeApi {

    /**
     *  得到本地url
     */
    @POST(Constants.LOCAL_URL)
    Observable<BasisBean<UrlBean>> getUrlData();
    /**
     *  上传文件
     */
    @Multipart
    @POST(Constants.UPLOAD_FILE) //参数比较多但最主要的就是标红的地方
    Observable<BasisBean<UploadModel>> uploadFile(@Part MultipartBody.Part file);
    /**
     *  会员登录
     */
    @FormUrlEncoded
    @POST(Constants.GET_CHECK_CODE)
    Observable<BasisBean<String>> getCheckCode(@FieldMap HashMap<String, String> map);
    /**
     *  用户注册
     */
    @FormUrlEncoded
    @POST(Constants.MEMBER_REGIST)
    Observable<BasisBean<String>> register(@FieldMap HashMap<String, String> map);
    /**
     *  用户密码登录
     */
    @FormUrlEncoded
    @POST(Constants.LOGIN_PWD)
    Observable<BasisBean<LoginModel>> loginPwd(@FieldMap HashMap<String, String> map);
    /**
     *  用户验证码登录
     */
    @FormUrlEncoded
    @POST(Constants.LOGIN_SMS)
    Observable<BasisBean<LoginModel>> loginCode(@FieldMap HashMap<String, String> map);

    /**
     *  首页分类列表数据
     */
    @FormUrlEncoded
    @POST(Constants.CATEGORY_DATA)
    Observable<BasisBean<List<YaoType1>>> getCategoryData(@FieldMap HashMap<String, String> map);
    /**
     *  分类详情页面数据
     */
    @FormUrlEncoded
    @POST(Constants.PRODUCT_LIST)
    Observable<BasisBean<List<DrugModel>>> getProductList(@FieldMap HashMap<String, String> map);
    /**
     *  热门搜索
     */
    @FormUrlEncoded
    @POST(Constants.SEARCH_HOT)
    Observable<BasisBean<List<TagModel>>> getSearchHotList(@FieldMap HashMap<String, String> map);
    /**
     *  获取首页活动及产品
     */
    @POST(Constants.ACTIVITY_HOME)
    Observable<BasisBean<List<HomeDataModel>>> getHomeData();

    /**
     *  获取首页广告信息
     */
    @POST(Constants.ADVANCE_HOME)
    Observable<BasisBean<HomeAdvanceModel>> getBannerData();
    /**
     *  获取首页公告
     */
    @POST(Constants.ACTIVITY_MESSAGE)
    Observable<BasisBean<HomeAdvanceModel>> getMessageData();
    /**
     *  商品列表/详情
     */
    @FormUrlEncoded
    @POST(Constants.PRODUCT_DETAIL)
    Observable<BasisBean<ProductDetailModel>> getProductDetail(@FieldMap HashMap<String, String> map);
    /**
     *  收藏商品
     */
    @FormUrlEncoded
    @POST(Constants.PRODUCT_COLLECT)
    Observable<BasisBean<Boolean>> collectProductDetail(@FieldMap HashMap<String, String> map);
    /**
     *  收藏商品列表
     */
    @FormUrlEncoded
    @POST(Constants.PRODUCT_COLLECT_LIST)
    Observable<BasisBean<List<DrugModel>>> getCollectionList(@FieldMap HashMap<String, String> map);
    /**
     *  获取店铺列表信息
     */
    @FormUrlEncoded
    @POST(Constants.STORE_SELECT)
    Observable<BasisBean<List<MyShopModel>>> getMyShop(@FieldMap HashMap<String, String> map);
    /**
     *  地址数据
     */
    @POST(Constants.ADDRESS_LIST)
    Observable<BasisBean<List<AddressModel>>> getAddressList();
    /**
     *  获取店铺类型
     */
    @POST(Constants.STORE_TYPE)
    Observable<BasisBean<List<StoreTypeModel>>> getStoreType();
    /**
     *  添加/修改门店信息（带有门店id就是修改 没有门店id就是添加）
     */
    @FormUrlEncoded
    @POST(Constants.STORE_ADD)
    Observable<BasisBean<Object>> storeAdd(@FieldMap HashMap<String, String> map);
    /**
     *  获取店铺详情
     */
    @FormUrlEncoded
    @POST(Constants.STORE_DETAIL)
    Observable<BasisBean<StoreDetailModel>> storeDetail(@FieldMap HashMap<String, String> map);
    /**
     *  获取各种订单状态中订单的总数量
     */
    @POST(Constants.ORDER_NUM)
    Observable<BasisBean<MyOrderNumModel>> getOrderNum();

    @POST(Constants.TEST)
    Observable<BasisBean<String>> getTest();
    /**
     *  个人中心优惠劵列表
     */
    @FormUrlEncoded
    @POST(Constants.USER_COUPON)
    Observable<BasisBean<CouponData>> getUserCouponList(@FieldMap HashMap<String, String> map);
    /**
     *  添加/修改门店信息（带有门店id就是修改 没有门店id就是添加）
     */
    @FormUrlEncoded
    @POST(Constants.SCAN_PR)
    Observable<BasisBean<DrugModel>> getScanProduct(@FieldMap HashMap<String, String> map);
    /**
     *  获取订单列表
     */
    @FormUrlEncoded
    @POST(Constants.ORDER_LIST)
    Observable<BasisBean<List<OrderModel>>> getOrderList(@FieldMap HashMap<String, String> map);
    /**
     *  获取广告信息
     */
    @FormUrlEncoded
    @POST(Constants.ACTIVITY_CUTOM)
    Observable<BasisBean<HomeAdvanceModel>> getAdData(@FieldMap HashMap<String, String> map);

    /**
     *  订单详情
     */
    @FormUrlEncoded
    @POST(Constants.ORDER_DETAIL)
    Observable<BasisBean<OrderModel>> getOrderDetail(@FieldMap HashMap<String, String> map);
    /**
     *  获取售后订单列表
     */
    @FormUrlEncoded
    @POST(Constants.AFTER_ORDER_LIST)
    Observable<BasisBean<List<OrderModel>>> getAfterOrderList(@FieldMap HashMap<String, String> map);
    /**
     *  获取购物车列表
     */
    @POST(Constants.SHOP_CART_LIST)
    Observable<BasisBean<ShopCartModel>> getShopCartList();
    /**
     *  修改购物车数量
     */
    @FormUrlEncoded
    @POST(Constants.SHOP_CART_UPDATE)
    Observable<BasisBean<String>> updateShopcart(@FieldMap HashMap<String, String> map);
    /**
     *  删除购物车信息
     */
    @FormUrlEncoded
    @POST(Constants.SHOP_CART_DELETE)
    Observable<BasisBean<String>> deleteShopcart(@FieldMap HashMap<String, String> map);
    /**
     *  添加商品到购物车
     */
    @FormUrlEncoded
    @POST(Constants.SHOP_CART_ADD)
    Observable<BasisBean<AddShopCartModel>> addShopcart(@FieldMap HashMap<String, String> map);
    /**
     *  商品到货提醒通知申请
     */
    @FormUrlEncoded
    @POST(Constants.PRODUCT_ARRIVE)
    Observable<BasisBean<String>> productArrive(@FieldMap HashMap<String, String> map);
    /**
     *  获取购物车数量
     */
    @FormUrlEncoded
    @POST(Constants.SHOP_CART_COUNT)
    Observable<BasisBean<AddShopCartModel>> getShopcartNum(@FieldMap HashMap<String, String> map);
    /**
     * 再次购买
     */
    @FormUrlEncoded
    @POST(Constants.BUY_AGAIN)
    Observable<BasisBean<String>> buyAgain(@FieldMap HashMap<String, String> map);
    /**
     *  上传文件
     */
    @FormUrlEncoded
    @POST(Constants.UPLOAD_TRANSFER)
    Observable<BasisBean<String>> uploadFileTansfer(@FieldMap HashMap<String, String> map);
    /**
     * 提醒发货
     */
    @FormUrlEncoded
    @POST(Constants.NOTIFY_ORDER)
    Observable<BasisBean<String>> nofitySendOrder(@FieldMap HashMap<String, String> map);
    /**
     * 确认订单
     */
    @FormUrlEncoded
    @POST(Constants.COMFIRM_ORDER)
    Observable<BasisBean<String>> comfirmOrder(@FieldMap HashMap<String, String> map);
    /**
     * 取消订单
     */
    @FormUrlEncoded
    @POST(Constants.CANCEL_ORDER)
    Observable<BasisBean<String>> cancelOrder(@FieldMap HashMap<String, String> map);
    /**
     * 售后订单详情
     */
    @FormUrlEncoded
    @POST(Constants.BACK_ORDER_DETAIL)
    Observable<BasisBean<OrderModel>> getBackOrderDetail(@FieldMap HashMap<String, String> map);
    /**
     * 撤销售后订单申请
     */
    @FormUrlEncoded
    @POST(Constants.CANCEL_ORDER_BACK)
    Observable<BasisBean<String>> cancelBackOrderAsk(@FieldMap HashMap<String, String> map);
    /**
     * 撤销售后订单申请
     */
    @FormUrlEncoded
    @POST(Constants.TUIHUO_ORDER_BACK)
    Observable<BasisBean<String>> tuihuoOrderAsk(@FieldMap HashMap<String, String> map);
    /**
     * 订单售后申请页面
     */
    @FormUrlEncoded
    @POST(Constants.ORDER_ORDER_BACK)
    Observable<BasisBean<AskModel>> ask_order_back(@FieldMap HashMap<String, String> map);
    /**
     * 到货提醒
     */
    @FormUrlEncoded
    @POST(Constants.PRODUCT_ARRIVE)
    Observable<BasisBean<AddShopCartModel>> product_arriveNotify(@FieldMap HashMap<String, String> map);
    /**
     * 获取用户积分数以及近一个月的积分记录
     */
    @FormUrlEncoded
    @POST(Constants.CREDIT)
    Observable<BasisBean<CreditData>> getMyInteral(@FieldMap HashMap<String, String> map);
    /**
     * 钱包余额和近一个月的消费明细
     */
    @FormUrlEncoded
    @POST(Constants.WALLET_INFO)
    Observable<BasisBean<WalletData>> getMyWalletInfo(@FieldMap HashMap<String, String> map);
    //获取所有资金明细数据
    @FormUrlEncoded
    @POST(Constants.WALLET_LIST)
    Observable<BasisBean<List<WalletData.WallatModel>>> getMyWalletList(@FieldMap HashMap<String, String> map);
    //获取所有资金明细数据
    @FormUrlEncoded
    @POST(Constants.CREDIT_LIST)
    Observable<BasisBean<List<CreditData.CreditModel>>> getMyIntegralList(@FieldMap HashMap<String, String> map);
    /**
     * 提交订单售后申请
     */
    @FormUrlEncoded
    @POST(Constants.ORDER_BACK)
    Observable<BasisBean<Object>> commitAfterSale(@FieldMap HashMap<String, String> map);
    /**
     *  收藏商品
     */
    @FormUrlEncoded
    @POST(Constants.PRODUCT_DIASCOLLECT)
    Observable<BasisBean<Boolean>> cancelcollect(@FieldMap HashMap<String, String> map);

    //生成订单
    @FormUrlEncoded
    @POST(Constants.ORDER_CREATE)
    Observable<BasisBean<CreateOrderModel>> createOrder(@FieldMap HashMap<String, String> map);

    //验证下单购物车金额
    @FormUrlEncoded
    @POST(Constants.ORDER_CHECK_MONEY)
    Observable<BasisBean<String>> checkMoney(@FieldMap HashMap<String, String> map);

    //订单支付
    @FormUrlEncoded
    @POST(Constants.ORDER_PAY)
    Observable<BasisBean<PayOrderModel>> payOrder(@FieldMap HashMap<String, String> map);
    //充值
    @FormUrlEncoded
    @POST(Constants.CHARGE_WALLET)
    Observable<BasisBean<Boolean>> chargeWallet(@FieldMap HashMap<String, String> map);
    //添加银行卡
    @FormUrlEncoded
    @POST(Constants.ADD_BANKCARD)
    Observable<BasisBean<String>> addBankCard(@FieldMap HashMap<String, String> map);
    /**
     *  修改密码
     */
    @FormUrlEncoded
    @POST(Constants.CHANGE_PASSWORD)
    Observable<BasisBean<Object>> changePassword(@FieldMap HashMap<String, String> map);
    /**
     *  修改密码
     */
    @FormUrlEncoded
    @POST(Constants.CHANGE_PHONE)
    Observable<BasisBean<Object>> changePhone(@FieldMap HashMap<String, String> map);
    /**
     *  获取消息列表
     */
    @FormUrlEncoded
    @POST(Constants.MESSAGE_LIST)
    Observable<BasisBean<MessageData>> getMessageData(@FieldMap HashMap<String, String> map);
    /**
     *  获取消息列表
     */
    @FormUrlEncoded
    @POST(Constants.MESSAGE_TYPE_LIST)
    Observable<BasisBean<List<MessageData.MessageModel>>> getMessageList(@FieldMap HashMap<String, String> map);
    /**
     *  消息已读
     */
    @FormUrlEncoded
    @POST(Constants.MESSAGE_READ)
    Observable<BasisBean<Object>> sendReadMessage(@FieldMap HashMap<String, String> map);
    /**
     *  消息所有消息已读
     */
    @FormUrlEncoded
    @POST(Constants.MESSAGE_ALLREAD)
    Observable<BasisBean<Object>> sendReadAllMessage(@FieldMap HashMap<String, String> map);
    /**
     *  提交商品需求
     */
    @FormUrlEncoded
    @POST(Constants.PRODUCT_NEED)
    Observable<BasisBean<String>> sendProductNeed(@FieldMap HashMap<String, String> map);
    /**
     *  领取优惠券
     */
    @FormUrlEncoded
    @POST(Constants.COUPON_SAVE)
    Observable<BasisBean<SavaCouponModel>> saveCoupon(@FieldMap HashMap<String, String> map);
    /**
     * 申请提现
     */
    @FormUrlEncoded
    @POST(Constants.BANK_DEPOSIT)
    Observable<BasisBean<Object>> commit_Withdrawal(@FieldMap HashMap<String, String> map);
    /**
     * 查看
     */
    @FormUrlEncoded
    @POST(Constants.CHECK_WULIU)
    Observable<BasisBean<WuliuData>> check_wuliu(@FieldMap HashMap<String, String> map);
    /**
     * 销售记录
     */
    @FormUrlEncoded
    @POST(Constants.SALE_RECORD)
    Observable<BasisBean<List<SaleRecordModel>>> getSaleRecordList(@FieldMap HashMap<String, String> map);
    /**
     * 商品详情查看领取优惠券
     */
    @FormUrlEncoded
    @POST(Constants.PRODUCT_COUPON)
    Observable<BasisBean<List<CouponModel>>> getProduct_Coupon(@FieldMap HashMap<String, String> map);
    /**
     * 获取欢迎页广告数据
     */
    @FormUrlEncoded
    @POST(Constants.SPLASH_ADVANCE)
    Observable<BasisBean<SplashData>> getSplashAd(@FieldMap HashMap<String, String> map);
    /**
     * 秒杀专区商品秒杀价购买（实质就是加入购物车）
     */
    @FormUrlEncoded
    @POST(Constants.MIAOSHA_BUY)
    Observable<BasisBean<AddShopCartModel>> miaoshaBuy(@FieldMap HashMap<String, String> map);

    /**
     * 企业资质
     */
    @POST(Constants.STORE_VALIDE)
    Observable<BasisBean<List<String>>> getStoreValide();
}



