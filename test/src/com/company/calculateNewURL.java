package com.company;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class calculateNewURL {

    @Test
    public void TypeTest(){

        String urlString = "http://www.example.com/path/";
        String relativeAddress = "/1/";

        URL url = new URL(urlString);
        URL newUrl = url.calculateNewUrl(relativeAddress);
        String expectedUrlString = "http://www.example.com/path/1/";
        URL expectedNewUrl =  new URL(expectedUrlString);

        Assert.assertEquals(expectedNewUrl, newUrl);
    }

    @Test
    public void RelativeAddressContainsOfOnlyOneDirectory1(){

        String urlString = "http://www.example.com/path/";
        String relativeAddress = "1";

        URL url = new URL(urlString);
        URL newUrl = url.calculateNewUrl(relativeAddress);
        String expectedUrlString = "http://www.example.com/path/1/";
        URL expectedNewUrl =  new URL(expectedUrlString);

        Assert.assertEquals(expectedNewUrl, newUrl);
    }

    @Test
    public void RelativeAddressContainsOfOnlyOneDirectory2(){

        String urlString = "http://www.example.com/path/";
        String relativeAddress = "1/";

        URL url = new URL(urlString);
        URL newUrl = url.calculateNewUrl(relativeAddress);
        String expectedUrlString = "http://www.example.com/path/1/";
        URL expectedNewUrl =  new URL(expectedUrlString);

        Assert.assertEquals(expectedNewUrl, newUrl);
    }

    @Test
    public void RelativeAddressContainsOfOnlyOneDirectory3(){

        String urlString = "http://www.example.com/path/";
        String relativeAddress = "1-";

        URL url = new URL(urlString);
        URL newUrl = url.calculateNewUrl(relativeAddress);
        String expectedUrlString = "http://www.example.com/path/1-/";
        URL expectedNewUrl =  new URL(expectedUrlString);

        Assert.assertEquals(expectedNewUrl, newUrl);
    }

    @Test
    public void RelativeAddressContainsOfOnlyOneDirectory4(){

        String urlString = "http://www.example.com/path/";
        String relativeAddress = "1_";

        URL url = new URL(urlString);
        URL newUrl = url.calculateNewUrl(relativeAddress);
        String expectedUrlString = "http://www.example.com/path/1_/";
        URL expectedNewUrl =  new URL(expectedUrlString);

        Assert.assertEquals(expectedNewUrl, newUrl);
    }

    @Test
    public void RelativeAddressContainsOfSeveralDirectory1(){

        String urlString = "http://www.example.com/path/";
        String relativeAddress = "/1/2";

        URL url = new URL(urlString);
        URL newUrl = url.calculateNewUrl(relativeAddress);
        String expectedUrlString = "http://www.example.com/path/1/2/";
        URL expectedNewUrl =  new URL(expectedUrlString);

        Assert.assertEquals(expectedNewUrl, newUrl);
    }

    @Test
    public void RelativeAddressContainsOfSeveralDirectory2(){

        String urlString = "http://www.example.com/path/";
        String relativeAddress = "/1/2/";

        URL url = new URL(urlString);
        URL newUrl = url.calculateNewUrl(relativeAddress);
        String expectedUrlString = "http://www.example.com/path/1/2/";
        URL expectedNewUrl =  new URL(expectedUrlString);

        Assert.assertEquals(expectedNewUrl, newUrl);
    }

    @Test
    public void RelativeAddressContainsOfSeveralDirectory3(){

        String urlString = "http://www.example.com/path/";
        String relativeAddress = "1/2/";

        URL url = new URL(urlString);
        URL newUrl = url.calculateNewUrl(relativeAddress);
        String expectedUrlString = "http://www.example.com/path/1/2/";
        URL expectedNewUrl =  new URL(expectedUrlString);

        Assert.assertEquals(expectedNewUrl, newUrl);
    }

    @Test
    public void urlIsRelative(){

        String urlString = "www.example.com/path/";
        String relativeAddress = "/1/";

        URL url = new URL(urlString);
        URL newUrl = url.calculateNewUrl(relativeAddress);
        String expectedUrlString = "www.example.com/path/1/";
        URL expectedNewUrl =  new URL(expectedUrlString);

        Assert.assertEquals(expectedNewUrl, newUrl);
    }


    @Test
    public void RelativeAddressIsFile(){

        String urlString = "http://www.example.com/path/";
        String relativeAddress = "/file.exe";

        URL url = new URL(urlString);
        URL newUrl = url.calculateNewUrl(relativeAddress);
        String expectedUrlString = "http://www.example.com/path/file.exe";
        URL expectedNewUrl =  new URL(expectedUrlString);

        Assert.assertEquals(expectedNewUrl, newUrl);
    }

    @Test
    public void RelativeAddressAndUrlPointToFile(){

        String urlString = "http://www.example.com/file.com";
        String relativeAddress = "/file.exe";
        URL url = new URL(urlString);
        Assert.assertThrows(URLNotCreatedException.class, () -> {url.calculateNewUrl(relativeAddress);} );

    }

    @Test
    public void urlContainsFragment(){

        String urlString = "http://www.example.com/path/#ancher";
        String relativeAddress = "/1";

        URL url = new URL(urlString);
        URL newUrl = url.calculateNewUrl(relativeAddress);
        String expectedUrlString = "http://www.example.com/path/1/";
        URL expectedNewUrl =  new URL(expectedUrlString);

        Assert.assertEquals(expectedNewUrl, newUrl);
    }

    @Test
    public void relativeAddressIsFragment(){

        String urlString = "http://www.example.com/path/";
        String relativeAddress = "#ancher";

        URL url = new URL(urlString);
        URL newUrl = url.calculateNewUrl(relativeAddress);
        String expectedUrlString = "http://www.example.com/path/#ancher";
        URL expectedNewUrl =  new URL(expectedUrlString);

        Assert.assertEquals(expectedNewUrl, newUrl);

    }

    @Test
    public void relativeAddressIsFragment2(){

        String urlString = "http://www.example.com/path/";
        String relativeAddress = "/#ancher";

        URL url = new URL(urlString);
        URL newUrl = url.calculateNewUrl(relativeAddress);
        String expectedUrlString = "http://www.example.com/path/#ancher";
        URL expectedNewUrl =  new URL(expectedUrlString);

        Assert.assertEquals(expectedNewUrl, newUrl);

    }

    @Test
    public void relativeAddressContainsFragment1(){

        String urlString = "http://www.example.com/path/";
        String relativeAddress = "/1#ancher";

        URL url = new URL(urlString);
        URL newUrl = url.calculateNewUrl(relativeAddress);
        String expectedUrlString = "http://www.example.com/path/1/#ancher";
        URL expectedNewUrl =  new URL(expectedUrlString);

        Assert.assertEquals(expectedNewUrl, newUrl);
    }

    @Test
    public void relativeAddressContainsFragment2(){

        String urlString = "http://www.example.com/path/";
        String relativeAddress = "/1/#ancher";

        URL url = new URL(urlString);
        URL newUrl = url.calculateNewUrl(relativeAddress);
        String expectedUrlString = "http://www.example.com/path/1/#ancher";
        URL expectedNewUrl =  new URL(expectedUrlString);

        Assert.assertEquals(expectedNewUrl, newUrl);
    }
    @Test
    public void relativeAddressContainsProtocol(){

        String urlString = "http://www.example.com/path/";
        String relativeAddress = "http:://1";

        URL url = new URL(urlString);
        Assert.assertThrows(URLNotCreatedException.class, () -> url.calculateNewUrl(relativeAddress) );
    }

    @Test
    public void relativeAddressContainsClimbUp(){

        String urlString = "http://www.example.com/path/";
        String relativeAddress = "../1";

        URL url = new URL(urlString);
        URL newUrl = url.calculateNewUrl(relativeAddress);
        String expectedUrlString = "http://www.example.com/1/";
        URL expectedNewUrl =  new URL(expectedUrlString);

        Assert.assertEquals(expectedNewUrl, newUrl);
    }

    @Test
    public void relativeAddressContainsClimbUpAndSlash(){

        String urlString = "http://www.example.com/path/";
        String relativeAddress = "..//1";

        URL url = new URL(urlString);
        Assert.assertThrows(URLNotCreatedException.class, () -> url.calculateNewUrl(relativeAddress) );
    }

    @Test
    public void relativeAddressContainsSeveralClimbUp(){

        String urlString = "http://www.example.com/path/path2";
        String relativeAddress = "../../1";

        URL url = new URL(urlString);
        URL newUrl = url.calculateNewUrl(relativeAddress);
        String expectedUrlString = "http://www.example.com/1/";
        URL expectedNewUrl =  new URL(expectedUrlString);

        Assert.assertEquals(expectedNewUrl, newUrl);
    }

    @Test
    public void relativeAddressContainsClimbUpButUrlContainsPathIsEmpty(){

        String urlString = "http://www.example.com/path/";
        String relativeAddress = "../../1";

        URL url = new URL(urlString);
        Assert.assertThrows(URLNotCreatedException.class, () -> url.calculateNewUrl(relativeAddress) );
    }

    @Test
    public void relativeAddressContainsClimbUpAndURLContainsSeveralDirectoriesInPath(){

        String urlString = "http://www.example.com/path/path2";
        String relativeAddress = "../1";

        URL url = new URL(urlString);
        URL newUrl = url.calculateNewUrl(relativeAddress);
        String expectedUrlString = "http://www.example.com/path/1/";
        URL expectedNewUrl =  new URL(expectedUrlString);

        Assert.assertEquals(expectedNewUrl, newUrl);
    }

    @Test
    public void relativeAddressIsNull(){

        String urlString = "http://www.example.com/path/";
        String relativeAddress = null;


        URL url = new URL(urlString);
        Assert.assertThrows(URLNotCreatedException.class, () -> url.calculateNewUrl(relativeAddress) );
    }

    @Test
    public void relativeAddressIsEmpty(){

        String urlString = "http://www.example.com/path/";
        String relativeAddress = "";

        URL url = new URL(urlString);
        URL newUrl = url.calculateNewUrl(relativeAddress);
        String expectedUrlString = "http://www.example.com/path/";
        URL expectedNewUrl =  new URL(expectedUrlString);

        Assert.assertEquals(expectedNewUrl, newUrl);
    }

    @Test
    public void relativeAddressIsAbsoluteAddress(){

        String urlString = "http://www.example.com/path/";
        String relativeAddress = "http://www.example.com/path/";

        URL url = new URL(urlString);
        Assert.assertThrows(URLNotCreatedException.class, () -> url.calculateNewUrl(relativeAddress) );
    }

    @Test
    public void relativeAddressContainsOnlyClimbUp(){

        String urlString = "http://www.example.com/path/";
        String relativeAddress = "../";

        URL url = new URL(urlString);
        URL newUrl = url.calculateNewUrl(relativeAddress);
        String expectedUrlString = "http://www.example.com/";
        URL expectedNewUrl =  new URL(expectedUrlString);

        Assert.assertEquals(expectedNewUrl, newUrl);
    }

    @Test
    public void relativeAddressHasSlashTwoPointSlash(){

        String urlString = "http://www.example.com/path/";
        String relativeAddress = "/../1";

        URL url = new URL(urlString);
        Assert.assertThrows(URLNotCreatedException.class, () -> url.calculateNewUrl(relativeAddress) );
    }

    @Test
    public void relativeAddressContainsInvalidChar1(){

        String urlString = "http://www.example.com/path/";
        String relativeAddress = "/path!";

        URL url = new URL(urlString);
        Assert.assertThrows(URLNotCreatedException.class, () -> url.calculateNewUrl(relativeAddress) );
    }

    @Test
    public void relativeAddressContainsInvalidChar2(){

        String urlString = "http://www.example.com/path/";
        String relativeAddress = "/path\\";

        URL url = new URL(urlString);
        Assert.assertThrows(URLNotCreatedException.class, () -> url.calculateNewUrl(relativeAddress) );
    }
}
