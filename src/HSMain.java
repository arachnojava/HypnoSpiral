import mhframework.MHGameApplication;
import mhframework.MHScreen;
import mhframework.MHVideoSettings;


public class HSMain
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        final MHScreen screen = new HSScreen();

        final MHVideoSettings settings = new MHVideoSettings();
        settings.fullScreen = true;
        settings.displayWidth = 1024;
        settings.displayHeight = 768;

        new MHGameApplication(screen, settings);

        System.exit(0);
    }

}
