package com.kyx.factory;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.kyx.factory.dal.domain.DeviceData;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by XuHe on 17/4/7.
 */
public class LocalApiClient {
    private final static Logger logger = LoggerFactory.getLogger(LocalApiClient.class);

    private HttpClient client = new HttpClient();

    private final static String secretKey = "abcd!@#$1234";

    public LocalApiClient() {
    	Map<String ,String> credentials = new HashMap<>();
    	credentials.put("secretKey", secretKey);
        client.setCredentials(credentials);
    }



	public DeviceData uploadData() throws HttpApiException, IOException {

		String url = "http://localhost:8080/product";

		Map<String, String> params = new HashMap<>();

		params.put("device", "look");
		params.put("factory", "APPLE");
		params.put("productLine","0");
		params.put("testResult", "0");
		params.put("hwVersion", "0x0100");
		params.put("swVersion", "0x0123");
		params.put("chipId", "0x000102030405060708091011");
		params.put("sn", "C000100");
		params.put("iccid", "898602b0101680011023");
		params.put("gpsCount", "2");
		params.put("flash" , "1");
		params.put("eeprom", "1");
		params.put("gprs", "1");
		params.put("batteryVoltage", "11");
		params.put("electricCurrent", "5");
		params.put("acceX", "-0.5");
		params.put("acceY" , "0.8");
		params.put("acceZ", "-0.6");
		params.put("gyroX", "120");
		params.put("gyroY", "120");
		params.put("gyroZ", "-120");



		HttpApiResponse response = client.post(url, params);

		String responseStr = response.toString();
		System.out.println(responseStr);
		if (StringUtils.isBlank(responseStr) || responseStr.length() <= 2) {
			return null;
		}

		return  new Gson().fromJson(responseStr, new TypeToken<DeviceData>(){}.getType());

	}

	public DeviceData conf() throws HttpApiException, IOException {

		String url = "http://localhost:8080/product_config";

		Map<String, String> params = new HashMap<>();

		params.put("device", "look");
		params.put("factory", "APPLES");
		params.put("productLine", "1");
		params.put("startSn", "L001001");
		params.put("snCount", "800");

		HttpApiResponse response = client.post(url, params);

		String responseStr = response.toString();
		System.out.println(responseStr);
		if (StringUtils.isBlank(responseStr) || responseStr.length() <= 2) {
			return null;
		}

		return  new Gson().fromJson(responseStr, new TypeToken<DeviceData>(){}.getType());

	}


	public static void main(String[] args) throws HttpApiException, IOException {
		LocalApiClient client= new  LocalApiClient();
		client.uploadData();
		//client.conf();

	}

}
