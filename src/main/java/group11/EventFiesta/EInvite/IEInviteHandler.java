package group11.EventFiesta.EInvite;

import java.awt.image.BufferedImage;
import java.awt.*;

public interface IEInviteHandler {

    public void AddTextInImage();

    public BufferedImage getOriginalImage();

    public BufferedImage getBufferdImage();

    public Graphics2D getGraphicsObject(BufferedImage bold);
}
