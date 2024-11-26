package data_access;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * DAO for images.
 */
public class ImageDataAccessObject {

    /**
     * Takes a URL string as a parameter, validates it, and returns a BufferedImage if it's a valid image.
     * Throws an exception if the URL is not valid or does not point to an image.
     *
     * @param urlString The URL of the image as a String.
     * @return A BufferedImage object.
     * @throws IllegalArgumentException if the URL is invalid or does not point to an image.
     * @throws IOException if an error occurs during the image reading process.
     */
    public static BufferedImage fetchImage(String urlString) throws IllegalArgumentException, IOException {
        try {
            // Convert the string to a URL object
            final URL url = new URL(urlString);

            // Attempt to read the image from the URL
            final BufferedImage image = ImageIO.read(url);

            // Check if the content at the URL is an image
            if (image == null) {
                throw new IllegalArgumentException("The URL does not point to a valid image.");
            }

            return image;
        }
        catch (MalformedURLException exception) {
            throw new IllegalArgumentException("The provided URL is invalid: " + urlString, exception);
        }
        catch (IOException exception) {
            throw new IOException("An error occurred while trying to read the image from the URL: "
                                    + urlString, exception);
        }
    }
}