### 分配sn号段
客户端向服务器请求分配sn号段，每次请求的数目由订单预先设定。

- POST /sn_range/user

	```
     @Param  required  user_name,String,用户名
     @Param  required  api_key,String,约定的密文
     @Header required  request_id,String,唯一标示当前请求
	  
	```
	
	* api_key
  

	  * 构建url：http://factory.ms.getqood.com/order/login?username=xxx&passsword=xxx
	  * 对url使用HmacSHA1(密钥：4bbf90\_SnRequestAPI\_50a7abf)加密
 
- 返回数据结构

  ```
	
	{
	  "data": {
	    "request_id": "1",
	    "start_sn": "L000361",
	    "end_sn": "L000420"
	  }
	}


	```
	  
	  - 返回数据：
	   (左右区间均为闭区间)
	   
	  ```
		
		{
		  "data": {
		    "request_id": 请求id,
		    "start_sn": 可用sn号开始,
		    "end_sn": 可用sn号结束
	  		}
		}
	  ```
