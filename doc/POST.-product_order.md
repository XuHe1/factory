### 获取PO基本信息
根据用户名密码获取生产订单基本信息。

- POST /order/user

	```
     @Param  required  user_name,String,用户名
     @Param  required  api_key,String,约定的密文
	  
	```
	
	* api_key
  

	  * 构建url：http://factory.ms.getqood.com/user/login?username=xxx&passsword=xxx
	  * 对url使用HmacSHA1(密钥：4bbf90\_SnRequestAPI\_50a7abf)加密
 
- 返回数据结构

  ```
	
	{
	  "data": {
	    "id": "23",
	    "order_no_prefix": "kyx",
	    "order_no": "kyx000023",
	    "device": "look",
	    "quantity": 200,
	    "start_sn": 501,
	    "sn_index": 501,
	    "end_sn": 700,
	    "project": "13",
	    "fw_version": "513",
	    "fw_download": "http://nbfb.test.getqood.com/upload/13/1.50B/BT_150_B.bin",
	    "hw_version": "256",
	    "factory": "JinXin",
	    "username": "kyx04",
	    "delivery_count": 30,
	    "create_time": "2017-06-12T17:44:11.000+0800",
	    "update_time": "2017-06-12T17:44:11.000+0800",
	    "state": 0
	  }
	}



	```
	  
	  - 返回数据：
	   
	  ```
		
		{
		  "data": {
		    "id": ID,
		    "order_no_prefix": 订单号前缀,
		    "order_no": 订单号（上报测试数据使用）,
		    "device": 设备类型,
		    "quantity": 订单总数,
		    "start_sn": sn起始数,
		    "sn_index": 501,
		    "end_sn": sn号段数,
		    "project": "13",
		    "fw_version": 固件版本(uint16),
		    "fw_download": 固件下载地址(uint16),
		    "hw_version": 硬件版本,
		    "factory": 工厂,
		    "username": 账号,
		    "delivery_count": sn单次下发数,
		    "create_time": 创建时间,
		    "update_time": 更新时间,
		    "state": 状态
		  }
		}
	  ```
