package inject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import net.minecraft.launchwrapper.IClassTransformer;

public class ClassTransformer implements IClassTransformer {

   private ZipFile ZipFile = null;


   public ClassTransformer() {
      try {
         URLClassLoader e = (URLClassLoader)ClassTransformer.class.getClassLoader();
         URL[] urls = e.getURLs();

         for(int i = 0; i < urls.length; ++i) {
            URL url = urls[i];
            ZipFile zipFile = getZipFile(url);
            if(zipFile != null) {
               this.ZipFile = zipFile;
               dbg("ClassTransformer");
               dbg("URL: " + url);
               dbg("ZIP file: " + zipFile);
               break;
            }
         }
      } catch (Exception var6) {
         var6.printStackTrace();
      }
   }

   private static ZipFile getZipFile(URL url) {
      try {
         URI uri = url.toURI();
         File file = new File(uri);
         ZipFile zipFile = new ZipFile(file);
         if(zipFile.getEntry("inject/ClassTransformer.class") == null) {
            zipFile.close();
            return null;
         } else {
            return zipFile;
         }
      } catch (Exception var4) {
         return null;
      }
   }

   public byte[] transform(String name, String transformedName, byte[] bytes) {
      byte[] Bytes = this.getClass(name);
      return Bytes != null?Bytes:bytes;
   }

   private byte[] getClass(String name) {
      if(this.ZipFile == null) {
         return null;
      } else {
         String fullName = name + ".class";
         ZipEntry ze = this.ZipFile.getEntry(fullName);
         if(ze == null) {
            return null;
         } else {
            try {
               InputStream e = this.ZipFile.getInputStream(ze);
               byte[] bytes = readAll(e);
               if((long)bytes.length != ze.getSize()) {
                  dbg("Invalid size for " + fullName + ": " + bytes.length + ", should be: " + ze.getSize());
                  return null;
               } else {
                  return bytes;
               }
            } catch (IOException var6) {
               var6.printStackTrace();
               return null;
            }
         }
      }
   }

   public static byte[] readAll(InputStream is) throws IOException {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      byte[] buf = new byte[1024];

      while(true) {
         int bytes = is.read(buf);
         if(bytes < 0) {
            is.close();
            byte[] bytes1 = baos.toByteArray();
            return bytes1;
         }

         baos.write(buf, 0, bytes);
      }
   }

   private static void dbg(String str) {
      System.out.println(str);
   }
}
