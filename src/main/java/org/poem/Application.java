package org.poem;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author poem
 */
@SpringBootApplication
@RestController
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication();
        application.setBannerMode(Banner.Mode.OFF);
        SpringApplication.run(Application.class,args);

    }

    @RequestMapping("")
    public String config(HttpServletRequest request){
        //微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        String signature = request.getParameter("signature");
        //时间戳
        String timestamp = request.getParameter("timestamp");
        //随机数
        String nonce = request.getParameter("nonce");
        //随机字符串
        String echostr = request.getParameter("echostr");

        logger.info("[signature]:"+ signature);
        logger.info("[timestamp]:"+ timestamp);
        logger.info("[nonce]:"+ nonce);
        logger.info("[echostr]:"+ echostr);

        //字典排序
        List<String> tmpArr = Arrays.asList(signature,timestamp,nonce);
        Collections.sort(tmpArr);
        //链接
        String tmpStr = String.join("",tmpArr);
        //sha1 加密
        String sign = DigestUtils.sha1Hex(tmpStr);
        if(sign.equals(signature)){
            logger.info("配置生效");
        }else{
            logger.info("配置不生效");
        }
        return echostr;
    }
}
