package com.librarymanagementsystem.helper;

/**
 * Helper class for Google reCaptcha Service
 * @author furkanzumrut
 */
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CaptchaHelper {

	private final String USER_AGENT = "Mozilla/5.0";
	private final String VERIFY_API = "https://www.google.com/recaptcha/api/siteverify";
	private final String SECRET_KEY = "6Lf3wgcTAAAAAP6Wxrt9_IxXlf77MzPMM1aomTf4";
	private static final Logger log = LoggerFactory
			.getLogger(CaptchaHelper.class);

	/**
	 * 
	 * @param resp User response code to verify via google verify api
	 * @return if captcha is valid It returns true
	 * @throws Exception
	 */
	public boolean validateCaptcha(String resp) throws Exception {

		URL obj = new URL(VERIFY_API);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		// add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		String urlParameters = "secret=" + SECRET_KEY + "&response=" + resp;

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		log.info("\nSending 'POST' request to URL : " + VERIFY_API);
		log.info("Post parameters : " + urlParameters);
		log.info("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		if (response.toString().contains("true")) {
			return true;
		} else {
			return false;
		}

	}
}
