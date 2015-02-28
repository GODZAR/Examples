package inject;

import java.io.File;
import java.util.List;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;

public class Tweaker implements ITweaker {

   public void acceptOptions(List args, File gameDir, File assetsDir, String profile) {
      dbg("Tweaker: acceptOptions");
   }

   public void injectIntoClassLoader(LaunchClassLoader classLoader) {
      dbg("Tweaker: injectIntoClassLoader");
      classLoader.registerTransformer("inject.ClassTransformer");
   }

   public String getLaunchTarget() {
      dbg("Tweaker: getLaunchTarget");
      return "net.minecraft.client.main.Main";
   }

   public String[] getLaunchArguments() {
      dbg("Tweaker: getLaunchArguments");
      return new String[0];
   }

   private static void dbg(String str) {
      System.out.println(str);
   }
}
