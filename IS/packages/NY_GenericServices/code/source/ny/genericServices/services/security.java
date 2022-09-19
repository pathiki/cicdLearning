package ny.genericServices.services;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.util.Base64;
import com.wm.util.JournalLogger;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.List;
// --- <<IS-END-IMPORTS>> ---

public final class security

{
	// ---( internal utility methods )---

	final static security _instance = new security();

	static security _newInstance() { return new security(); }

	static security _cast(Object o) { return (security)o; }

	// ---( server methods )---




	public static final void hmacHash (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(hmacHash)>> ---
		// @sigtype java 3.5
		// [i] field:0:required algorithm
		// [i] field:0:required inputString
		// [i] field:0:required secretKey
		// [o] field:0:required hashValue
		// [o] field:0:required errorMessage
			
		
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	algorithm = IDataUtil.getString( pipelineCursor, "algorithm" );
			String	inputString = IDataUtil.getString( pipelineCursor, "inputString" );
			String	secretKey = IDataUtil.getString( pipelineCursor, "secretKey" );
		pipelineCursor.destroy();
		
		String hashValue= null;
		String errorMessage = null;
		try{
		
			// 1. Get an algorithm instance.
		    Mac sha256_hmac = Mac.getInstance(algorithm);
		
		    // 2. Create secret key.
		    SecretKeySpec secret_key = new SecretKeySpec(secretKey.getBytes("UTF-8"), algorithm);
		
		    // 3. Assign secret key algorithm.
		    sha256_hmac.init(secret_key);
		
		    // 4. Generate Base64 encoded cipher string.
		   //byte[] hash = Base64.encode(sha256_hmac.doFinal(inputString.getBytes("UTF-8")));
		    
		   byte[] hash = sha256_hmac.doFinal(inputString.getBytes("UTF-8"));
		   byte[] hashNew=  new byte[16];
		   int len = hashNew.length;
		   
		   for(int i=0; i<len;i++){
			   hashNew[i]=hash[i];
		   }
		   
		   byte [] encodeHash =  Base64.encode(hashNew);
		  
		   
		   String hashString = new String(encodeHash);
		   
		   byte[] hashFinal = hashString.replaceAll("\r\n","").getBytes();
		   
		   hashValue = new String(hashFinal);
			
		}catch (Exception e) { 
			 errorMessage = e.toString();
		}
		
		
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "hashValue", hashValue );
		IDataUtil.put( pipelineCursor_1, "errorMessage", errorMessage );
		pipelineCursor_1.destroy();
			
			
		// --- <<IS-END>> ---

                
	}
}

