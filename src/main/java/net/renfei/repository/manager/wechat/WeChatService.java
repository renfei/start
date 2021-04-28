package net.renfei.repository.manager.wechat;

import net.renfei.repository.manager.wechat.model.AccessTokenDO;
import net.renfei.repository.manager.wechat.model.Code2SessionDO;
import net.renfei.repository.manager.wechat.model.PaidUnionIdDO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 微信服务接口
 *
 * @author renfei
 */
@FeignClient(name = "WeChatService", url = "https://api.weixin.qq.com")
public interface WeChatService {
    /**
     * access_token是公众号的全局唯一接口调用凭据，公众号调用各接口时都需使用access_token。
     * 开发者需要进行妥善保存。access_token的存储至少要保留512个字符空间。
     * access_token的有效期目前为2个小时，需定时刷新，重复获取将导致上次获取的access_token失效。
     *
     * @param appId            第三方用户唯一凭证
     * @param secret           第三方用户唯一凭证密钥，即appsecret
     * @param clientCredential 获取access_token填写client_credential
     */
    @GetMapping("/cgi-bin/token")
    AccessTokenDO getAccessToken(@RequestParam("appid") String appId,
                                 @RequestParam("secret") String secret,
                                 @RequestParam("grant_type") String clientCredential);

    /**
     * 登录凭证校验。
     * 通过 wx.login 接口获得临时登录凭证 code 后传到开发者服务器调用此接口完成登录流程。
     *
     * @param appId             小程序 appId
     * @param secret            小程序 appSecret
     * @param jsCode            登录时获取的 code
     * @param authorizationCode 授权类型，此处只需填写 authorization_code
     */
    @GetMapping("/sns/jscode2session")
    Code2SessionDO code2Session(@RequestParam("appid") String appId,
                                @RequestParam("secret") String secret,
                                @RequestParam("js_code") String jsCode,
                                @RequestParam("grant_type") String authorizationCode);

    /**
     * 用户支付完成后，获取该用户的 UnionId，无需用户授权。
     *
     * @param openid        支付用户唯一标识
     * @param accessToken   接口调用凭证
     * @param transactionId 微信支付订单号
     * @param mchId         微信支付分配的商户号，和商户订单号配合使用
     * @param outTradeNo    微信支付商户订单号，和商户号配合使用
     */
    @GetMapping("/wxa/getpaidunionid")
    PaidUnionIdDO getPaidUnionId(@RequestParam("openid") String openid,
                                 @RequestParam("access_token") String accessToken,
                                 @RequestParam(value = "transaction_id", required = false) String transactionId,
                                 @RequestParam(value = "mch_id", required = false) String mchId,
                                 @RequestParam(value = "out_trade_no", required = false) String outTradeNo);
}
