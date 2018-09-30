package com.yx.Pharmacy.constant;

/**
 * Created time  2018/3/26 0026
 * @author : mcx
 * 类描述 : 
 */

public class Constants {
//     测试
    public static final String BASE_URL            = "https://o2o.yuanxinyy.com:8801/index.php/";
    // H5连接
    public static final String H5_URL              = "h5_url";


    //签到页面地址
    public static final String WEB_SIGN       = "https://o2o.yuanxinyy.com:8801/index.php/web/sign";
    //兑换优惠券地址
    public static final String WEB_COUPON       = "https://o2o.yuanxinyy.com:8801/index.php/web/coupon";
    //退换政策
    public static final String WEB_EXCHANGE       = "https://o2o.yuanxinyy.com:8801/index.php/web/exchange";
    //有奖任务
    public static final String WEB_PRIZETASK       = "https://o2o.yuanxinyy.com:8801/index.php/web/prizetask";
    //关于源鑫
    public static final String WEB_ABOUT       = "https://o2o.yuanxinyy.com:8801/index.php/web/about";
    // 获取验证码
    public static final String GET_CHECK_CODE      = "sms/send";
    // 注册
    public static final String MEMBER_REGIST      = "regist";
    // 用户密码登录
    public static final String LOGIN_PWD      = "login/pwd";
    // 用户验证码登录
    public static final String LOGIN_SMS      = "login/sms";
    // 获取用户数据
    public static final String USER_INFO      = "user/info";
    // 分类数据
    public static final String CATEGORY_DATA      = "category/list";
    // 根据关键字或者分类id获取商品列表
    public static final String PRODUCT_LIST      = "product/list";
    // 热门搜索
    public static final String SEARCH_HOT     = "search/hot";


    // 获取首页活动及产品
    public static final String ACTIVITY_HOME      = "activity/home";
    // 获取首页活动及产品
    public static final String ACTIVITY_CUTOM     = "advance/custom";
    // 获取首页广告信息
    public static final String ADVANCE_HOME      = "advance/home";
    // 商品列表/详情
    public static final String PRODUCT_DETAIL      = "product/detail";
    // 收藏
    public static final String PRODUCT_COLLECT    = "product/collect";
    // 收藏列表
    public static final String PRODUCT_COLLECT_LIST    = "product/collectList";

    // 个人中心优惠券列表
    public static final String USER_COUPON    = "coupon/user";
    // 获取店铺列表信息
    public static final String STORE_SELECT      = "store/select";
    // 获取店铺类型
    public static final String STORE_TYPE      = "store/type";
    // 添加/修改门店信息（带有门店id就是修改 没有门店id就是添加）
    public static final String STORE_ADD      = "store/add";
    // 店铺详情
    public static final String STORE_DETAIL      = "store/detail";
    // 地址数据
    public static final String ADDRESS_LIST      = "address/list";
    // 图片上传
    public static final String UPLOAD_FILE      = "upload/image";
    // 扫描条形码获取对应的商品
    public static final String SCAN_PR      = "product/qr";
    //获取订单列表
    public static final String ORDER_LIST      = "order/list";
    //订单详情
    public static final String ORDER_DETAIL      = "order/detail";
    //售后订单列表
    public static final String AFTER_ORDER_LIST      = "order/back_list";
    //再次购买
    public static final String BUY_AGAIN      = "order/buyagain";
    //提醒发货
    public static final String NOTIFY_ORDER      = "order/notify";
    //确认订单
    public static final String COMFIRM_ORDER      = "order/confirm";
    //取消订单
    public static final String CANCEL_ORDER      = "order/cancel";
    //售后订单详情
    public static final String BACK_ORDER_DETAIL      = "order/back_detail";
    //撤销售后订单申请
    public static final String CANCEL_ORDER_BACK      = "order/back_cancel";
    //提交退货信息接口
    public static final String TUIHUO_ORDER_BACK      = "order/backTranslate";
    //订单售后申请页面
    public static final String ORDER_ORDER_BACK      = "order/order_back";
    //到货通知
    public static final String PRODUCT_ARRIVE      = "product/arrive";
    //获取用户积分数以及近一个月的积分记录
    public static final String CREDIT      = "credit";
    //钱包余额和近一个月的消费明细
    public static final String WALLET_INFO      = "wallet/info";
    //获取所有资金明细数据
    public static final String WALLET_LIST      = "wallet/list";
    //获取所有资金明细数据
    public static final String CREDIT_LIST      = "credit/list";
    //提交订单售后申请
    public static final String ORDER_BACK    = "order/back";
    //取消商品收藏
    public static final String PRODUCT_DIASCOLLECT    = "product/disCollect";
    //修改的登录密码
    public static final String CHANGE_PASSWORD      = "user/updatepsw";
    //修改登录手机号码
    public static final String CHANGE_PHONE     = "user/updatemobile";
    //获取消息列表
    public static final String MESSAGE_LIST     = "message/list";
    //根据消息类型获取消息列表
    public static final String MESSAGE_TYPE_LIST   = "message/typelist";
    //消息已读
    public static final String MESSAGE_READ  = "message/read";
    //所有消息已读
    public static final String MESSAGE_ALLREAD  = "message/allread";

    //生成订单
    public static final String ORDER_CREATE      = "order/create";
    //验证下单购物车金额
    public static final String ORDER_CHECK_MONEY      = "order/check_money";
    //订单支付
    public static final String ORDER_PAY      = "pay/order";
    //充值
    public static final String CHARGE_WALLET      = "wallet/charge";
    //添加银行卡
    public static final String ADD_BANKCARD      = "bankcard/add";



    //添加商品到购物车
    public static final String SHOP_CART_ADD      = "cart/add";
    //获取购物车商品总数
    public static final String SHOP_CART_COUNT      = "cart/count";
    //获取购物车列表
    public static final String SHOP_CART_LIST      = "cart/list";
    //删除购物车信息
    public static final String SHOP_CART_DELETE      = "cart/del";
    //修改购物车数量
    public static final String SHOP_CART_UPDATE      = "cart/update";
    //提交商品需求
    public static final String PRODUCT_NEED      = "product/need";
    //领取优惠券
    public static final String COUPON_SAVE      = "coupon/save";
    //申请提现
    public static final String BANK_DEPOSIT  = "bank/deposit";
    //查看物流
    public static final String CHECK_WULIU  = "order/translate";
    //销售记录
    public static final String SALE_RECORD  = "product/buylist";
    //商品详情查看领取优惠券
    public static final String PRODUCT_COUPON  = "coupon/product";
    //获取欢迎页广告数据
    public static final String SPLASH_ADVANCE  = "advance/splash";
    //秒杀专区商品秒杀价购买（实质就是加入购物车）
    public static final String MIAOSHA_BUY  = "cart/activityadd";
    //企业资质
    public static final String STORE_VALIDE  = "store/valide";

    public static final String KEY_TOKEN = "key_token";
    public static final String KEY_STORE_ID = "key_store_id";// 仓库id
    public static final String KEY_ITEM_ID = "key_item_id";// 门店id
    public static final String KEY_STORENAME = "key_storename";// 门店名
    public static final String KEY_ADDRESS = "key_storeaddress"; // 门店地址
    public static final String KEY_COLLECT = "key_collectcount";// 门店收藏数量
    public static final String KEY_AVATAR = "key_avatar";// 门头照-头像
    public static final String KEY_TRUENAME = "key_truename";// 门店对应姓名
    public static final String KEY_MOBILE = "key_mobile";// 门店对应手机号
    public static final String KEY_CARCOUNT = "key_carcount";// 门店对应购物车数量
    public static final String KEY_COMPANY = "key_company"; // 公司名
    public static final String KEY_CREDIT = "key_credit"; // 积分
    public static final String KEY_MONEY = "key_money"; // 余额
    public static final String KEY_QRCODE = "key_qrcode"; // 二维码
    public static final String KEY_BANK_USERNAME= "key_bank_usernam"; // 银行卡开户名
    public static final String KEY_BANK = "key_bank"; // 银行卡
    public static final String KEY_BRANCH = "key_branch"; // 网点
    public static final String KEY_ACCOUNT = "key_account"; // 银行卡号
    public static final String KEY_HAVEBANK = "key_havebank"; // 是否有银行卡
    public static final String KEY_STORE_CERTIFY = "key_storecertify"; // 是否有认证的门店

    public static final String SEARCH_HISTORY="search_history";
    public static final String KEY_ORDER_NUMBER="KEY_order_number";//订单号

    public static final int PAGESIZE    = 20;
    public static final String WX_ID    = "wx2e654dca1ada96c7";
    public static final String QIYU_ID    = "de70e4386f910414a1a14bfb65e24d8d";
    public static int type_used         = 1;
    public static int type_useful       =2;
    public static int type_outtime      = 3;
    public static final String hasEnterApp      = "hasEnterApp";
    public static final String KEY_AD      = "ad";
    public static final String KEY_LAST_CLICK_SP_AD      = "last_click_sp_ad";//最后一次点击启动页广告
}
