package net.minecraft.client.resources;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.imageio.ImageIO;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FileResourcePack extends AbstractResourcePack implements Closeable
{
    public static final Splitter entryNameSplitter = Splitter.on('/').omitEmptyStrings().limit(3);
    private ZipFile resourcePackZipFile;
    private static final String __OBFID = "CL_00001075";
    static final String[] patterns = new String[]{
        "redstone_block",
        "redstone_dust_cross",
        "redstone_dust_cross_overlay",
        "redstone_dust_line",
        "redstone_dust_line_overlay",
        "nether_wart_stage_0",
        "nether_wart_stage_1",
        "nether_wart_stage_2",
        "double_plant_sunflower_back",
        "double_plant_sunflower_bottom",
        "double_plant_sunflower_front",
        "double_plant_sunflower_top",
        "double_plant_paeonia_bottom",
        "double_plant_paeonia_top",
        "water_flow",
        "water_still",
        "glass_pane_top",
        "glass_pane_top_black",
        "glass_pane_top_blue",
        "glass_pane_top_brown",
        "glass_pane_top_cyan",
        "glass_pane_top_gray",
        "glass_pane_top_green",
        "glass_pane_top_light_blue",
        "glass_pane_top_lime",
        "glass_pane_top_magenta",
        "glass_pane_top_orange",
        "glass_pane_top_pink",
        "glass_pane_top_purple",
        "glass_pane_top_red",
        "glass_pane_top_silver",
        "glass_pane_top_white",
        "glass_pane_top_yellow",
        "leaves_acacia",
        "leaves_acacia_opaque",
        "leaves_big_oak",
        "leaves_big_oak_opaque",    
        "double_plant_fern_bottom",
        "double_plant_fern_top",
        "double_plant_paeonia_bootom",
        "double_plant_paeonia_top",
        "double_plant_grass_bottom",
        "double_plant_grass_top",
        "double_plant_rose_bottom",
        "double_plant_rose_top",
        "double_plant_sunflower_back",
        "double_plant_sunflower_bottom",
        "double_plant_sunflower_front",
        "double_plant_sundlower_top",
        "double_plant_syringa_bottom",
        "double_plant_syringa_top",
        "flower_houstonia",
        "flower_oxeye_daisy",
        "flower_rose",
        "glass_black",
        "glass_blue",
        "glass_brown",
        "glass_cyan",
        "glass_gray",
        "glass_green",
        "glass_light_blue",
        "glass_lime",
        "glass_pink",
        "glass_purple",
        "glass_red",
        "glass_silver",
        "glass_white",
        "glass_yellow",
        "glass_magenta",
        "glass_orange",
        "glass_pane_top",
        "glass_pane_top_black",
        "flower_blue_orchid",
        "acacia_sapling",
        "flower_dandelion",
        "dark_oak_sapling",
        "flower_allium",
        "flower_paeonia",
        "flower_tulip_orange",
        "flower_tulip_pink",
        "flower_tulip_red",
        "flower_tulip_white",
        "iron_bars",
    	"rail_(activator|activator_powered|detector|detector_powered|golden|golden_powered|normal|normal_turned)?",
    	"anvil_(top(_damaged_[1-2])?|base)",
    	"bed_(feet|head)_(end|side|top)",
    	"brewing_stand",
    	"cactus_(bottom|side|top)",
    	"cake_(bottom|inner|side|top)",
    	"carrots_stage_[0-3]",
    	"cauldron_(bottom|inner|side|top)",
    	"cocoa_stage_[0-2]",
    	"deadbush",
    	"destroy_stage_[0-9]",
    	"detectorRail",
    	"door_(iron|wood)_(lower|upper)",
    	"fenceIron",
    	"fern",
    	"fire_layer_[0-1]",
    	"flower_(pot|dandelion|rose)?",
    	"glass",
    	"goldenRail(_powered)?",
    	"grass_side_overlay",
    	"hopper_top",
    	"ice",
    	"ladder",
    	"lava(_flow)?",
    	"leaves_(birch|birch_opaque|jungle|jungle_opaque|oak|oak_opaque|spruce|spruce_opaque)?",
    	"lever",
    	"mob_spawner",
    	"mushroom_(brown|red)",
    	"netherStalk_[0-2]",
    	"portal",
    	"potatoes_stage_[0-3]",
    	"redstoneDust_(cross|line)(_overlay)?",
    	"redstone_torch_(on|off)?",
    	"trip_wire(_source)?",
    	"reeds",
    	"sapling(_(oak|birch|jungle|spruce|acacia|oak|roofed_oak))?",
    	"stem_(bent|straight)",
    	"tallgrass",
    	"thinglass_top",
    	"torch_on",
    	"trapdoor",
    	"tripWire(Source)?",
    	"vine",
    	"water(_flow)?",
    	"waterlily",
    	"wheat_stage_[0-7]",
    	"melon_stem_(connected|disconnected)?",
    	"pumpkin_stem_(connected|disconnected)?",
    	"nether_wa rt_stage_[0-2]",
    	"web"};

    public FileResourcePack(File par1File)
    {
        super(par1File);
    }

    private ZipFile getResourcePackZipFile() throws IOException
    {
        if (this.resourcePackZipFile == null)
        {
            this.resourcePackZipFile = new ZipFile(this.resourcePackFile);
        }

        return this.resourcePackZipFile;
    }

    protected InputStream getInputStreamByName(String par1Str) throws IOException
    {
        ZipFile zipfile = this.getResourcePackZipFile();
        ZipEntry zipentry = zipfile.getEntry(par1Str);

        if (zipentry == null)
        {
            throw new ResourcePackFileNotFoundException(this.resourcePackFile, par1Str);
        }
        else
        {
            return filterStream(par1Str, zipfile.getInputStream(zipentry));
        }
    }

    public boolean hasResourceName(String par1Str)
    {
        try
        {
            return this.getResourcePackZipFile().getEntry(par1Str) != null;
        }
        catch (IOException ioexception)
        {
            return false;
        }
    }

    public Set getResourceDomains()
    {
        ZipFile zipfile;

        try
        {
            zipfile = this.getResourcePackZipFile();
        }
        catch (IOException ioexception)
        {
            return Collections.emptySet();
        }

        Enumeration enumeration = zipfile.entries();
        HashSet hashset = Sets.newHashSet();

        while (enumeration.hasMoreElements())
        {
            ZipEntry zipentry = (ZipEntry)enumeration.nextElement();
            String s = zipentry.getName();

            if (s.startsWith("assets/"))
            {
                ArrayList arraylist = Lists.newArrayList(entryNameSplitter.split(s));

                if (arraylist.size() > 1)
                {
                    String s1 = (String)arraylist.get(1);

                    if (!s1.equals(s1.toLowerCase()))
                    {
                        this.logNameNotLowercase(s1);
                    }
                    else
                    {
                        hashset.add(s1);
                    }
                }
            }
        }

        return hashset;
    }

    protected void finalize() throws Throwable
    {
        this.close();

        try
        {
            super.finalize();
        }
        catch (Throwable t)
        {
        }
    }

    public void close() throws IOException
    {
        if (this.resourcePackZipFile != null)
        {
            try
            {
                this.resourcePackZipFile.close();
            }
            catch (Exception ex)
            {
            }

            this.resourcePackZipFile = null;
        }
    }
    
    public static InputStream filterStream(String entryname, InputStream is) throws IOException {
        try {
           entryname = entryname.replace('\\', '/');
           if(entryname.contains("assets/minecraft/textures/blocks/") && entryname.endsWith(".png")) {
              String ex = entryname.substring(entryname.lastIndexOf(47) + 1, entryname.lastIndexOf(".png"));
              String[] img = patterns;
              int w = img.length;

              int h;
              for(h = 0; h < w; ++h) {
                 String r = img[h];
                 if(Pattern.compile("^" + r + "$").matcher(ex).matches()) {
                    return is;
                 }
              }

              BufferedImage var9 = ImageIO.read(is);
              w = var9.getWidth();
              h = var9.getHeight();
              int[] var10 = var9.getRGB(0, 0, w, h, (int[])null, 0, w);

              for(int baos = 0; baos < var10.length; ++baos) {
                 var10[baos] |= -16777216;
              }

              var9.setRGB(0, 0, w, h, var10, 0, w);
              ByteArrayOutputStream var11 = new ByteArrayOutputStream();
              ImageIO.write(var9, "png", var11);
              return new ByteArrayInputStream(var11.toByteArray());
           } else {
              return is;
           }
        } catch (Exception var8) {
           var8.printStackTrace();
           return null;
        }
     }
}
