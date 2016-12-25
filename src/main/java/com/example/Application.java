package com.example;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import java.io.File;
import java.io.FileOutputStream;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class Application {
public static void main(String[] args)
{
SpringApplication.run(Application.class, args);
}
}
@RestController
class GreetingController {
@RequestMapping("/hello/{name}")
    String hello(@PathVariable String name) {
        return "Hello, " + name + "!";
  }
@RequestMapping(value = "/hello1", method = RequestMethod.POST)
public String hello1(@RequestBody String base64String) {
String encodeFromFileString="";
try {
String src="srcfile.pdf";
String dest="destfile.pdf";
String USER_PASS = "hello123";
String OWNER_PASS = "hello123";
//String base64String="JVBERi0xLjQKJeLjz9MKNCAwIG9iaiA8PC9GaWx0ZXIvRmxhdGVEZWNvZGUvTGVuZ3RoIDE5MT4+c3RyZWFtCnicXY9BC4JAEIXv8yvesS62u7pudiwqCAKFhQ7RQXDVItdSQ/r3rdqlmDl8zOO9mXnSWhODigR0RltNCT2JecxXEj0JHJx4I85wpPOFISM/hJIhKpJqovtIfuixwLH/g5Ne0omsyxmqKYaFix2HCKBz4uOUIxBQQnlyCV3RbFPbokm71z3trrVt5/o2nDYaBbj4M4aR9AI1GnV5beH6Xb8aWNMjTguzQpzle2ONi6wbbaqHyzXf0OHfhD5Rwj76CmVuZHN0cmVhbQplbmRvYmoKMSAwIG9iajw8L0NvbnRlbnRzIDQgMCBSL1R5cGUvUGFnZS9SZXNvdXJjZXM8PC9Qcm9jU2V0IFsvUERGIC9UZXh0IC9JbWFnZUIgL0ltYWdlQyAvSW1hZ2VJXS9Gb250PDwvRjEgMiAwIFIvRjIgMyAwIFI+Pj4+L1BhcmVudCA1IDAgUi9NZWRpYUJveFswIDAgNjEyIDc5Ml0+PgplbmRvYmoKMiAwIG9iajw8L1N1YnR5cGUvVHlwZTEvVHlwZS9Gb250L0Jhc2VGb250L1RpbWVzLUJvbGQvRW5jb2RpbmcvV2luQW5zaUVuY29kaW5nPj4KZW5kb2JqCjMgMCBvYmo8PC9TdWJ0eXBlL1R5cGUxL1R5cGUvRm9udC9CYXNlRm9udC9UaW1lcy1Sb21hbi9FbmNvZGluZy9XaW5BbnNpRW5jb2Rpbmc+PgplbmRvYmoKNSAwIG9iajw8L0tpZHNbMSAwIFJdL1R5cGUvUGFnZXMvQ291bnQgMT4+CmVuZG9iago2IDAgb2JqPDwvVHlwZS9DYXRhbG9nL1BhZ2VzIDUgMCBSPj4KZW5kb2JqCjcgMCBvYmo8PC9Nb2REYXRlKEQ6MjAxNjEyMTkwODIwNDlaKS9DcmVhdGlvbkRhdGUoRDoyMDE2MTIxOTA4MjA0OVopL1Byb2R1Y2VyKGlUZXh0IDIuMC44IFwoYnkgbG93YWdpZS5jb21cKSk+PgplbmRvYmoKeHJlZgowIDgKMDAwMDAwMDAwMCA2NTUzNSBmIAowMDAwMDAwMjczIDAwMDAwIG4gCjAwMDAwMDA0MzggMDAwMDAgbiAKMDAwMDAwMDUyNiAwMDAwMCBuIAowMDAwMDAwMDE1IDAwMDAwIG4gCjAwMDAwMDA2MTUgMDAwMDAgbiAKMDAwMDAwMDY2NSAwMDAwMCBuIAowMDAwMDAwNzA5IDAwMDAwIG4gCnRyYWlsZXIKPDwvSW5mbyA3IDAgUi9JRCBbPDc5NjYwODJjZDc0ZTc4NDVjNzFkYTFmYWU2OTVhZjg0Pjw3MzgyMDU3NDc4MTUwYjYxMjA3NGRjYjFmNzU5ZjA4Mj5dL1Jvb3QgNiAwIFIvU2l6ZSA4Pj4Kc3RhcnR4cmVmCjgyOAolJUVPRgo=";   
//file creation based on request
File file1 = new File(src);
FileOutputStream fos = new FileOutputStream(file1);
fos.write(Base64.decode(base64String, Base64.DECODE));
fos.close();
			 
//file creation with password based on src file
PdfReader reader = new PdfReader(src);
PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
stamper.setEncryption(USER_PASS.getBytes(), OWNER_PASS.getBytes(),PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128 | PdfWriter.DO_NOT_ENCRYPT_METADATA);
stamper.close();
reader.close();
encodeFromFileString=Base64.encodeFromFile(dest);
} catch (Exception e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
return   encodeFromFileString;
}
}
