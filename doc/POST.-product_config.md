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
		"data":{
		  "id": 8,
		  "device": "look",
		  "factory": "APPLE",
		  "productLine": 2,
		  "startSn": "L001001",
		  "snCount": 2000,
		  "endSn": 3000
		}
	
	}
```
	  
	  
	  - 返回数据：说明返回新增数据并包含ID
