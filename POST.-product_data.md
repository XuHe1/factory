### 新增产品检测数据


- POST /api/product

	```
     @Param required  device,String,设备名
     @Param required  factory,String,工厂名
     @Param required  productLine,int,生产线号
     @Param required  testResult,int,测试结果，0：测试通过 1：测试未通过
     @Param required  hwVersion,String,硬件版本号
     @Param required  swVersion,String,软件版本号
     @Param required  chipId,String, 芯片ID
     @Param optional  sn,String,Sn号
     @Param required  iccid,String
     @Param required  gpsCount,int
     @Param required  flash,int
     @Param required  eeprom,int
     @Param required  gprs,int
     @Param required  batteryVoltage,int,电压
     @Param required  electricCurrent,int,电流
     @Param required  acceX,String
     @Param required  acceY,String
     @Param required  acceZ,String
     @Param required  gyroX,String
     @Param required  gyroY,String
     @Param required  gyroZ,String
	```
 
- 返回数据结构

  ```
	{
		"data":{
			  "id": 43,
			  "device": "look",
			  "factory": "APPLE",
			  "productLine": 0,
			  "hwVersion": "0x0100",
			  "swVersion": "0x0123",
			  "chipId": "0x000102030405060708091011",
			  "sn": "C000982",
			  "iccid": "898602b0101680011023",
			  "gpsCount": 2,
			  "flash": 1,
			  "eeprom": 1,
			  "gprs": 1,
			  "batteryVoltage": 11,
			  "electricCurrent": 5,
			  "acceX": "-0.5",
			  "acceY": "0.8",
			  "acceZ": "-0.6",
			  "gyroX": "120",
			  "gyroY": "120",
			  "gyroZ": "-120",
			  "testResult": 0,
			  "samplingTime": 1494057482469
		}
	
	}
```
	  
	  
	  - 返回数据：说明返回新增数据并包含ID
