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
	  "data": {
	    "id": "6",
	    "device": "look",
	    "factory": "APPLE",
	    "product_line": 0,
	    "hw_version": "0x0100",
	    "sw_version": "0x0123",
	    "chip_id": "0x000102030405060708091011",
	    "iccid": "898602b0101680011023",
	    "gps_count": 2,
	    "flash": 1,
	    "eeprom": 1,
	    "gprs": 1,
	    "battery_voltage": 11,
	    "electric_current": 5,
	    "acce_x": "-0.5",
	    "acce_y": "0.8",
	    "acce_z": "-0.6",
	    "gyro_x": "120",
	    "gyro_y": "120",
	    "gyro_z": "-120",
	    "test_result": 1,
	    "receive_time": "2017-05-11T10:36:13.901+0800"
	  }
}
```
	  
	  
	  - 返回数据：说明返回新增数据并包含ID
