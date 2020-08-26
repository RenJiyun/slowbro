package com.eggip.slowbro.weixin;

import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * 处理微信公众号接入
 */
@RestController
@RequestMapping("gongzhonghao")
public class GongzhonghaoController {

    @Value("${slowbro.weixin.gongzhonghao.token}")
    private String token;

    @Autowired
    private GongzhonghaoService gongzhonghaoService;

    /**
     * 微信公众号接入
     * 参考文档：https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Access_Overview.html
     *
     * @return
     */
    @RequestMapping(value = "jieru", method = {RequestMethod.POST, RequestMethod.GET})
    public String jieru(HttpServletRequest request,
                        @RequestParam(value = "signature", required = false) String signature,
                        @RequestParam(value = "timestamp", required = false) String timestamp,
                        @RequestParam(value = "nonce", required = false) String nonce,
                        @RequestParam(value = "echostr", required = false) String echostr,
                        @RequestBody(required = false) String xmlStr) {

        if (request.getMethod().equals("GET") && !StringUtils.isEmpty(signature)) {
            if (checkSignature(token, timestamp, nonce, signature)) {
                return echostr;
            } else {
                throw new IllegalArgumentException();
            }
        } else if (request.getMethod().equals("POST") && !StringUtils.isEmpty(xmlStr)) {
            return gongzhonghaoService.dispatch(xmlStr);
        } else {
            throw new RuntimeException();
        }
    }

    private boolean checkSignature(String token, String timestamp, String nonce, String signature) {
        String tmpStr = Arrays.asList(token, timestamp, nonce).stream().sorted(String::compareTo).reduce("", String::concat);
        String sha1 = Hashing.sha1().hashString(tmpStr, Charset.defaultCharset()).toString();
        return signature.equals(sha1);
    }


}
