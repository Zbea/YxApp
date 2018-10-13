package com.yx.Pharmacy.net;

import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.net.api.HomeApi;
import com.yx.Pharmacy.util.L;
import com.yx.Pharmacy.util.LogUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络操作类
 */

public class HomeNet {

    private static final String TAG = "HomeNet";
    private static HomeApi recyclerApi;
    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
                         .addInterceptor(new Interceptor() {
                             @Override
                             public Response intercept(Chain chain) throws IOException
                             {
                                 Request request=chain.request();

                                 Request.Builder builder = chain.request().newBuilder();
                                 builder.addHeader("token", CustomEncryptHelper.Encrypt(NetUtil.isStringNull(NetUtil.getToken())));
                                 builder.addHeader("version", "v1");
                                 builder.addHeader("platform","android");
                                 builder.addHeader("storeid", CustomEncryptHelper.Encrypt(NetUtil.isStringNull(NetUtil.getStoreid())));
                                 builder.addHeader("itemid", CustomEncryptHelper.Encrypt(NetUtil.isStringNull(NetUtil.getItemId())));

//                                 RequestBody requestBody = request.body();
//                                 if (requestBody != null) {
//                                     okio.Buffer buffer = new okio.Buffer();
//                                     requestBody.writeTo(buffer);
//                                     Charset charset = Charset.forName("UTF-8");
//                                     MediaType contentType = requestBody.contentType();
//                                     if (contentType != null) {
//                                         charset = contentType.charset(charset);
//                                     }
//                                     String string = buffer.readString(charset);
//                                     String encryptStr =  CustomEncryptHelper.Encrypt(string);
//                                     RequestBody body = MultipartBody.create(contentType, encryptStr);
//                                     builder.post(body);
//
//                                 }


//                                 //拼接timestamp
//                                 String url = request.url().toString();
//                                 //获取参数列表
//                                 String[] parts = url.split("\\?");
//                                 //TreeMap里面的数据会按照key值自动升序排列
//                                 TreeMap<String, String> param_map = new TreeMap<String, String>();
//                                 //获取参数对
//                                 String[] param_pairs = parts[1].split("&");
//                                 for (String pair : param_pairs) {
//                                     String[] param = pair.split("=");
//                                     if (param.length != 2) {
//                                         //没有value的参数不进行处理
//                                         continue;
//                                     }
//                                     param_map.put(param[0], param[1]);
//                                 }
//                                 StringBuilder sign = new StringBuilder();
//                                 Iterator it = param_map.keySet().iterator();
//                                 //拼接参数
//                                 while (it.hasNext()) {
//                                     String key = it.next().toString();
//                                     String value = param_map.get(key).toString();
//                                     sign.append(key + value);
//                                 }
//
//                                 String sing_s=CustomEncryptHelper.Decrypt(sign.toString());
//                                 RequestBody body = MultipartBody.create(request.body().contentType(), sing_s);
//                                 builder.post(body);
                                 if (request.body()!=null)
                                 {
                                     if(request.body() instanceof FormBody){
                                         FormBody.Builder newFormBody = new FormBody.Builder();
                                         FormBody oidFormBody = (FormBody) request.body();
                                         for (int i = 0;i<oidFormBody.size();i++){
                                             L.i("key:"+oidFormBody.encodedName(i));
                                             L.i("value:"+oidFormBody.encodedValue(i));
                                             newFormBody.addEncoded(oidFormBody.encodedName(i),CustomEncryptHelper.Encrypt(oidFormBody.encodedValue(i)));
                                         }
//                                     builder.method(request.method(),newFormBody.build());
                                         builder.post(newFormBody.build());
                                     }
                                 }


                                 Response response;
                                 try {
                                     //发送请求，获得相应，
                                     response = chain.proceed(builder.build());
                                 } catch (Exception e) {
                                     throw e;
                                 }
                                 ResponseBody   responseBody = response.body();
                                 BufferedSource source       = responseBody.source();
                                 source.request(Long.MAX_VALUE); // Buffer the entire body.
                                 //获得返回的数据
                                 Buffer buffer = source.buffer();
                                 //使用前clone()下，避免直接消耗
                                 LogUtils.e("response:" + buffer.clone().readString(Charset.forName("UTF-8")));


                                 //                                 return chain.proceed(builder.build());
                                 return response;
                             }
                         }).connectTimeout(30, TimeUnit.SECONDS)
                         .readTimeout(30, TimeUnit.SECONDS)
                         .build();

    public static HomeApi getHomeApi() {
        LogUtils.e(NetUtil.getToken()+"  itemid = " +NetUtil.getItemId()+"  storeid = " +NetUtil.getStoreid());
        if (recyclerApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            recyclerApi = retrofit.create(HomeApi.class);
        }
        return recyclerApi;
    }


}
