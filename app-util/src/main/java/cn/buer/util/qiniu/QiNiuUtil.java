package cn.buer.util.qiniu;

import java.net.URL;
import java.util.Date;
import java.util.List;

import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.StringUtils;

public final class QiNiuUtil {
	private static final String accessKey = "qUnic6KdB_jpnDYCEzoRpMqaCRzz44TKeWx2lDTc";
	private static final String secretKey = "LpAtxy6852b1s8T_LAdhNKjuxv9fRULrU1qqQdt4";
	private static final long expires = 10000;
	private static final String returnBody = "{\"key\": $(key), \"hash\": $(etag),\"mimeType\": $(mimeType)}";
	private static final String callbackBody = "{\"key\": $(key), \"hash\": $(etag),\"mimeType\": $(mimeType)}";
	public static final String getUploadToken(String bucketName,String mimeType) {
		String fileName = getFileKey(mimeType);
		return uploadToken(bucketName, fileName, expires, null, true);
	}
	public static final String getUploadToken(String bucketName, String mimeType, long expires) {
		String fileName = getFileKey(mimeType);
		return uploadToken(bucketName, fileName, expires, null, true);
	}

	public static final String getUploadToken(String bucketName, String mimeType, long expires, URL returnUrl,
			List<URL> callbackUrlList) {
		String fileName = getFileKey(mimeType);
		StringMap policy = new StringMap();
		if (returnUrl != null) {
			policy.put("returnUrl", returnUrl);
			policy.put("returnBody", returnBody);
		}
		if (callbackUrlList != null && !callbackUrlList.isEmpty()) {
			policy.put("callbackUrl", StringUtils.join(callbackUrlList.toArray(), ","));
			policy.put("callbackBody", callbackBody);
		}
		return uploadToken(bucketName, fileName, expires, policy, true);
	}
	
	private static String uploadToken(String bucket, String key, long expires, StringMap policy, boolean strict) {
		Auth auth = Auth.create(accessKey, secretKey);
		if(policy == null){
			policy = new StringMap();
		}
		policy.put("saveKey", key);
		return auth.uploadToken(bucket, null, expires, policy, strict);
	}
	
	private static String getFileKey(String mimeType) {
		String keyp = String.valueOf(new Date().getTime());
		String keys = String.valueOf((int) (Math.random() * 10000));
		StringBuffer buffer = new StringBuffer();
		buffer.append(keyp).append(keys).append(".").append(mimeType);
		return buffer.toString();
	}
}
