package group11.EventFiesta.EInvite;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;
import java.awt.*;
import java.awt.geom.Rectangle2D;

import javax.imageio.ImageIO;

public class EInviteHandler implements IEInviteHandler {
    EInviteModel eObject;
    private File input;
    private File output;
    private String title;
    private String subTitle;
    private String templateName;
    private String outputFileName;
    private SimpleDateFormat sdf;
    private BufferedImage img;
    int centerX = 0;
    int centerY = 0;
    int topX = 0;
    int topY = 0;
    int bottomY = 0;

    public EInviteHandler(EInviteModel model) {
        this.eObject = model;
        init();
    }

    @Override
    public void AddTextInImage() {
        try {
            BufferedImage orgImg = getOriginalImage();

            BufferedImage buffImg = getBufferdImage();
            Graphics2D graphics = getGraphicsObject(buffImg);

            FontMetrics fmatrics = graphics.getFontMetrics();
            Rectangle2D rect = fmatrics.getStringBounds(eObject.getCeremonyName(), graphics);

            centerX = (orgImg.getWidth() - (int) rect.getWidth()) / 2;
            centerX = orgImg.getWidth() / 2;
            centerY = orgImg.getHeight() / 2;
            topX = orgImg.getWidth() / 4;
            topY = orgImg.getHeight() / 4;
            bottomY = orgImg.getHeight() / 5;

            graphics.setColor(Color.white);
            graphics.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
            graphics.drawString(this.title, topX + 30, topY);
            graphics.drawString(this.subTitle, centerX - 20, topY + 40);

            graphics.setFont(new Font(Font.DIALOG_INPUT, Font.ITALIC, 50));
            String host2 = eObject.getEventHost2();
            if (host2 != null && host2 != "") {
                graphics.drawString("&", centerX, centerY - 10);
                graphics.drawString(eObject.getEventHost2(), centerX + 20, centerY + 60);
                graphics.drawString(eObject.getEventHost1(), topX + 30, topY + 140);
            } else {
                graphics.drawString(eObject.getEventHost1(), centerX - 30, centerY);
            }

            graphics.setFont(new Font(Font.SERIF, Font.ITALIC, 30));
            graphics.drawString(getFormatedDate(), topX, centerY + bottomY);
            graphics.drawString(getFormatedTime(), centerX - 20, centerY + bottomY + 50);
            graphics.drawString(eObject.getVenue(), centerX - 20, centerY + bottomY + 100);

            ImageIO.write(buffImg, "jpg", output);

            graphics.dispose();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BufferedImage getOriginalImage() {
        try {
            img = ImageIO.read(input);
        } catch (IOException e) {
            System.out.println("Error in reading Image");
            e.printStackTrace();
        }
        return img;
    }

    @Override
    public Graphics2D getGraphicsObject(BufferedImage bold) {
        Graphics2D graphics2d = (Graphics2D) bold.getGraphics();
        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f);
        graphics2d.drawImage(img, 1, 2, null);
        graphics2d.setComposite(alphaComposite);
        return graphics2d;
    }

    @Override
    public BufferedImage getBufferdImage() {
        int imgType = "jpg".equalsIgnoreCase("jpg") ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage bufferedImage = new BufferedImage(img.getWidth(), img.getHeight(), imgType);
        return bufferedImage;
    }

    private String getFormatedDate() {
        try {
            Date parsedDate = sdf.parse(eObject.getTimeOfEvent());
            SimpleDateFormat date = new SimpleDateFormat("EEEEEE MMMMMM d, yyyy");
            return date.format(parsedDate);
        } catch (ParseException e) {
            System.out.println("Error in Parsing Formated Date");
            e.printStackTrace();
            return null;
        }
    }

    private String getFormatedTime() {
        try {
            Date parsedTime = sdf.parse(eObject.getTimeOfEvent());
            SimpleDateFormat time = new SimpleDateFormat("hh:mm aa");
            return time.format(parsedTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void init() {

        this.title = "Please join us to celebrate " + eObject.getCeremonyName();
        this.subTitle = "ceremony of";
        this.templateName = "einviteTemplate2.jpg";
        this.outputFileName = "generatedEInvite.jpg";
        this.input = new File("src/main/resources/static/images/" + templateName);
        this.output = new File("src/main/resources/static/images/" + outputFileName);
        this.sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm",
                Locale.ENGLISH);

    }
}
