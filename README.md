
 
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

## 错误返回结构样列

 ```
 {
    "code": "FA200001",
    "msg": "参数不合法"
 }
 ```

