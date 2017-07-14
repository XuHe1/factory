### 新增产品检测数据


- POST /product

	```
	  
	 @Param required  order_id,String,订单号
     @Param required  device,String,设备名
     @Param required  factory,String,工厂名
     @Param required  product_line,int,生产线号
     @Param required  test_result,int,测试结果，0：测试通过 >=1：测试未通过
     @Param required  hw_version,String,硬件版本号
     @Param required  sw_version,String,软件版本号
     @Param required  chip_id,String, 芯片ID
     @Param optional  sn,String,Sn号
     @Param required  iccid,String
     @Param required  gps_count,int
     @Param required  flash,int
     @Param required  eeprom,int
     @Param required  gprs,int
     @Param optional  battery_voltage,int,电压
     @Param required  electric_current,int,电流
     @Param required  acce_x,String
     @Param required  acce_y,String
     @Param required  acce_z,String
     @Param required  gyro_x,String
     @Param required  gyro_y,String
     @Param required  gyro_z,String
     @Param required  last_check_end,long,上个设备产测结束时间
     @Param required  download_start, long,烧录开始时间
     @Param required  check_start, long,产测开始时间（烧录完成时间）
     @Param required  check_end, long,产测结束时间
     @Param optional  can, int  1:成功 0:失败
     @Param optional  kline, int 1:成功 0:失败
     
	```
 
- 返回数据结构

  ```
	{
	  "data": {
	    "id": "327",
	    "order_id": "kyx001",
	    "device": "look",
	    "factory": "JinXin",
	    "product_line": "20:c9:d0:80:df:4d",
	    "hw_version": "256",
	    "sw_version": "256",
	    "chip_id": "09801b000543485739303121",
	    "sn": "L100001",
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
	    "test_result": 0,
	    "last_check_end": "1496992609",
	    "download_start": "1496992809",
	    "check_start": "1496993009",
	    "check_end": "1496993109",
	    "check_total": "500",
	    "receive_time": "2017-06-10T10:19:04.749+0800",
	    "invalid": 0,
	    "can": 1,
	    "kline": 1
	  }
	}
```
	  
	  
	  - 返回数据：说明返回新增数据并包含ID
