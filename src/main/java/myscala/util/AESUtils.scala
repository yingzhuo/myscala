/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.util

import java.security.MessageDigest
import java.util
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

import myscala.Charsets

object AESUtils {

    def encrypt(strToEncrypt: String, secret: String): String = {
        val secretKey = getSecretKeySpec(secret)
        val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        Base64.getEncoder.encodeToString(cipher.doFinal(strToEncrypt.getBytes(Charsets.UTF_8)))
    }

    def decrypt(strToDecrypt: String, secret: String): String = {
        val secretKey = getSecretKeySpec(secret)
        val cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING")
        cipher.init(Cipher.DECRYPT_MODE, secretKey)
        new String(cipher.doFinal(Base64.getDecoder.decode(strToDecrypt)))
    }

    private def getSecretKeySpec(myKey: String): SecretKeySpec = {
        var key = myKey.getBytes(Charsets.UTF_8)
        val sha = MessageDigest.getInstance("SHA-1")
        key = sha.digest(key)
        key = util.Arrays.copyOf(key, 16)
        new SecretKeySpec(key, "AES")
    }

}
