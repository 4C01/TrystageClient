package cn.trystage.ui.font;

import java.awt.Font;
import java.io.InputStream;

import cn.trystage.Client;
import net.minecraft.util.ResourceLocation;

public class FontManager {

    public static CFontRenderer font10 = getFont("font.ttf", 10);


    public static CFontRenderer font12 = getFont("font.ttf", 12);
    public static CFontRenderer font13 = getFont("font.ttf", 13);
    public static CFontRenderer font14 = getFont("font.ttf", 14);
    public static CFontRenderer font15 = getFont("font.ttf", 15);

    public static CFontRenderer font16 = getFont("font.ttf", 16);
    public static CFontRenderer font18 = getFont("font.ttf", 18);
    public static CFontRenderer font19 = getFont("font.ttf", 19);
    public static CFontRenderer font20 = getFont("font.ttf", 20);
    public static CFontRenderer font22 = getFont("font.ttf", 22);
    public static CFontRenderer font24 = getFont("font.ttf", 24);
    public static CFontRenderer font26 = getFont("font.ttf", 26);
    public static CFontRenderer font28 = getFont("font.ttf", 28);
    public static CFontRenderer font32 = getFont("font.ttf", 32);
    public static CFontRenderer font34 = getFont("font.ttf", 34);

    public static CFontRenderer font40 = getFont("font.ttf", 40);
    public static CFontRenderer font42 = getFont("font.ttf", 42);
    public static CFontRenderer font64 = getFont("font.ttf", 64);

    public static CFontRenderer other14 = getFont("ico.ttf", 14);

    public static CFontRenderer bold26 = getFont("bold.ttf", 26);
    public static CFontRenderer bold32 = getFont("bold.ttf", 32);
    public static CFontRenderer bold12 = getFont("bold.ttf", 12);
    public static CFontRenderer bold18 = getFont("bold.ttf", 18);
    public static CFontRenderer bold20 = getFont("bold.ttf", 20);
    public static CFontRenderer bold24 = getFont("bold.ttf", 24);
    public static CFontRenderer bold38 = getFont("bold.ttf", 38);
    public static CFontRenderer bold34 = getFont("bold.ttf", 34);

    public static CFontRenderer icontestFont40 = getFont("icont.ttf", 40);
    public static CFontRenderer icontestFont35 = getFont("icont.ttf", 35);
    public static CFontRenderer icontestFont75 = getFont("icont.ttf", 75);

    public static CFontRenderer icon22 = getFont("iconfont.ttf", 22);
    public static CFontRenderer museo18 = getFont("museo500.ttf", 18);

    public static CFontRenderer tenacitybold = getFont("tenacity-bold.ttf", 20);
    public static CFontRenderer tenacitybold22 = getFont("tenacity-bold.ttf", 22);
    public static CFontRenderer tenacitybold34 = getFont("tenacity-bold.ttf", 34);
    public static CFontRenderer tenacitybold18 = getFont("tenacity-bold.ttf", 18);

    public static CFontRenderer axBold20 = getFont("ax-bold.ttf", 20);
    public static CFontRenderer axRegular18 = getFont("ax-regular.ttf", 18);

    public static CFontRenderer material18 = getFont("material.ttf", 18);
    public static CFontRenderer icon20 = getFont("Icon-1.ttf", 20);
    
    private static CFontRenderer getFont(String fontName, float fontSize) {
        Font font = null;
        try {

            InputStream inputStream = Client.class.getResourceAsStream("/assets/minecraft/Trystage/font/" + fontName);
            assert inputStream != null;
            font = Font.createFont(Font.PLAIN, inputStream);
            font = font.deriveFont(fontSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new CFontRenderer(font);
    }
}
