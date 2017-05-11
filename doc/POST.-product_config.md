### 新增／修改生产计划


- POST /api/product_config

	```
     @Param required  device,String,设备名
     @Param required  factory,String,工厂名
     @Param required  productLine,int,生产线号
     @Param required  startSn, String,起始sn
     @Param required  snCount, int,可连续写入的SN数量
	  
	```
 
- 返回数据结构

  ```
{
	  "data": {
	    "id": "3",
	    "device": "look",
	    "factory": "APPLES",
	    "product_line": 1,
	    "start_sn": "L001001",
	    "sn_count": 800,
	    "end_sn": 1800,
	    "create_time": "2017-05-09T20:24:16.000+0800",
	    "update_time": "2017-05-11T10:39:53.456+0800"
	  }
}
```
	  
	  
	  - 返回数据：说明返回新增数据并包含ID
