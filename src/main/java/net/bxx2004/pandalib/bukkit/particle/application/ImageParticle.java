package net.bxx2004.pandalib.bukkit.particle.application;

import net.bxx2004.pandalib.bukkit.particle.Particle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageParticle implements Particle {
    int width = 0;
    int height = 0;
    private File file;
    private BufferedImage image;

    public ImageParticle(String path) {
        this.file = new File(path);
        try {
            image = ImageIO.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    private boolean isSpawn(int r, int g, int b) {
        int grary = (int) (0.299 * r + 0.587 * g + 0.114 * b);
        if (grary < 132) {
            return true;
        }
        return false;
    }
}
