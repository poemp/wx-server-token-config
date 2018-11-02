# wx-server-token-config
微信服务器配置token验证


|参数	|描述 |
| ------| -----|
|signature	|微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。|
|timestamp	|时间戳|
|nonce	|随机数|
|echostr	|随机字符串 |

开发者通过检验signature对请求进行校验（下面有校验方式）。若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败。加密/校验流程如下：

1）将token、timestamp、nonce三个参数进行字典序排序 

2）将三个参数字符串拼接成一个字符串进行sha1加密
 
3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信