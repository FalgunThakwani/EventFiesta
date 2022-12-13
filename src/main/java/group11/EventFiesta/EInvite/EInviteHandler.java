package group11.EventFiesta.EInvite;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.geom.Rectangle2D;

import javax.imageio.ImageIO;

public class EInviteHandler {
    EInviteModel eObject;

    public EInviteHandler(EInviteModel model) {
        this.eObject = model;
    }

    public void AddTextInImage() {
        File input = new File("src/main/resources/static/images/einviteTemplate2.jpg");
        File outPut = new File("src/main/resources/static/images/generatedEInvite.jpg");
        

        try {
            BufferedImage image = ImageIO.read(input);
            int imageType = "jpg".equalsIgnoreCase("jpg") ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
            BufferedImage bold = new BufferedImage(image.getWidth(), image.getHeight(), imageType);

            Graphics2D w = (Graphics2D) bold.getGraphics();
            w.drawImage(image, 1, 2, null);
            AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f);
            w.setComposite(alpha);

            FontMetrics fmatrics = w.getFontMetrics();
            Rectangle2D rect = fmatrics.getStringBounds(eObject.getCeremonyName(), w);

            int centerX = (image.getWidth() - (int) rect.getWidth()) / 2;
            int centerY = image.getHeight() / 2;
            int topX = image.getWidth() / 3;
            int topY = image.getHeight() / 4;

            w.setColor(Color.white);
            w.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
            w.drawString(eObject.getCeremonyName(), topX, topY);

            w.setColor(Color.white);
            w.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 50));
            w.drawString(eObject.getEventHost1(), centerX, centerY);
            ImageIO.write(bold, "jpg", outPut);

            w.dispose();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
