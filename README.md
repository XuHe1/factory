
 
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


## 错误返回结构样列

 ```
 {
    "code": "FA200001",
    "msg": "参数不合法"
 }
 ```
## 接口定义
* [生产检测数据上报接口](https://git.1tianxia.net/h.xu/factory/blob/master/doc/POST.-product_data.md)

* [生产计划配置接口](https://git.1tianxia.net/h.xu/factory/blob/master/doc/POST.-product_config.md)