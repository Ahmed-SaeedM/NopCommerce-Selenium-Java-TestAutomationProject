package uilities;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.*;
import java.util.List;
import java.util.stream.Collectors;

public class UtilityMethods {
    private final WebDriver driver;
    private final By pageLinks = By.cssSelector("a[href]");
    private final By pageImages = By.tagName("img");

    public UtilityMethods(WebDriver driver) {
        this.driver = driver;
    }

    public List<String> getAllPageLinks() {
        List<WebElement> links = driver.findElements(pageLinks);
        System.out.println(links.size());
        return links.stream().map(e -> e.getAttribute("href")).collect(Collectors.toList());
    }

    /**
     * This Methode should be implemented in utility
     */
    public void verifyLinks(List<String> list) {
        for (String link : list) {
            try {
                URI uri = new URI(link);
                URL url = uri.toURL();
                //create a connection using url object
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setReadTimeout(10000);
                httpURLConnection.connect();
                // use get response code
                if (httpURLConnection.getResponseCode() == 200) System.out.println(link +
                        "ـــ" + httpURLConnection.getResponseMessage());
                if (httpURLConnection.getResponseCode() == 404) System.out.println(link +
                        "ـــ" + httpURLConnection.getResponseMessage());
//                httpURLConnection.disconnect();
            } catch (SocketTimeoutException e) {
                System.err.println("Timeout for URL: " + link);
            } catch (MalformedURLException e) {
                System.err.println("Invalid URL: " + link);
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<WebElement> getAllPageImages() {
        return driver.findElements(pageImages);
    }

    public void verifyImages(WebElement image) {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(image.getAttribute("src"));
        try {
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == 200)
                System.out.println("Image With src : " + image.getAttribute("src") + " Is OK");
            if (response.getStatusLine().getStatusCode() != 200) System.out.println("Broken Image: " + image);
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }
}
