
 
## 通用规范

---

* 接口调用认证：暂无
* 接口返回内容统一使用JSON格式
* api中的{pn}表示通配符

## HTTP Status Code 约定

---

|状态码 |	含义                |   	说明      |
|------ | --------------------- | ------------    |
|200	|OK	                    | 请求成功        |
|201	|CREATED	            | 创建成功        |
|202	|ACCEPTED	            | 更新成功        |
|204	|NO CONTENT	            | 请求成功，但服务器不需要返回实体内容       |
|400	|BAD REQUEST            | 包含不支持的参数|
|401	|UNAUTHORIZED           | 未授权          |
|403	|FORBIDDEN	            | 被禁止访问      |
|404	|NOT FOUND	            | 请求的资源不存在|
|500	|INTERNAL SERVER ERROR  | 内部错误        |

## 错误代码定义
 - @error FA100001 签名参数缺失
 - @error FA100002 请求超时
 - @error FA100003 nonce已使用
 - @error FA100004 签名错误
 - @error FA110001 未知错误
 - @error FA110002 其他错误
 - @error FA200001 参数不合法
 - @error FA130001 资源已存在
 - @error FA130002 资源不存在
 - @error FA200002 SN冲突
 - @error FA130003 工厂不存在
 - @error FA130004 生产线不存在
 - @error FA130005 暂不支持该种设备
 - @error FA130006 SN号前缀错误
 - @error FA000001 device不能为空
 - @error FA000002 SN号格式错误
 - @error FA000003 factory不能为空
 - @error FA000004 productLine不能为空
 - @error FA000005 hwVersion不能为空
 - @error FA000006 swVersion不能为空
 - @error FA000007 chipId不能为空
 - @error FA000008 SN长度必须为7位
 - @error FA000009 iccid不能为空
 - @error FA000010 gps不能为空
 - @error FA000011 flash不能为空
 - @error FA000012 eeprom不能为空
 - @error FA000013 gprs不能为空
 - @error FA000014 batteryVoltage不能为空
 - @error FA000015 electricCurrent不能为空
 - @error FA000016 acceX不能为空
 - @error FA000017 acceY不能为空
 - @error FA000018 acceZ不能为空
 - @error FA000019 gyroX不能为空
 - @error FA000020 gyroY不能为空
 - @error FA000021 gyroZ不能为空
 - @error FA000022 testResult不能为空
 - @error FA000034 用户名错误
 - @error FA000035 密码错误
 - @error FA000036 订单已暂停
 - @error FA000037 无可用sn,请新建订单


## 错误返回结构样列

 ```
 {
    "code": "FA200001",
    "msg": "参数不合法"
 }
 ```
## 接口签名
所有接口均须添加下列参数供服务端验证签名实用。

```
	@Param required nonce
	@Param required timestamp
	@Param required signature
```
*  nonce: 随机数，[获取nonce接口](https://git.1tianxia.net/h.xu/factory/blob/master/doc/GET.-auth_nonce.md)
* timestamp: 时间戳，单位秒，nonce截取后10位
* signature: 签名
   * 构建(url?nonce=xxx&timestamp=xxxx)
   * 对url使用HmacSHA1(密钥：4bbf90\_SnRequestAPI\_50a7abf)加密

## 接口定义
* [生产检测数据上报接口](https://git.1tianxia.net/h.xu/factory/blob/master/doc/POST.-product_data.md)

* [生产计划配置接口](https://git.1tianxia.net/h.xu/factory/blob/master/doc/POST.-product_config.md)

* [sn号段分配接口](https://git.1tianxia.net/h.xu/factory/blob/master/doc/POST.-sn_segment.md)


* [获取订单基本信息接口](https://git.1tianxia.net/h.xu/factory/blob/master/doc/POST.-product_order.md)