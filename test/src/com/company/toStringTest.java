package com.company;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import com.company.URL;

public class toStringTest {

    @Test
    public void TypeTest(){
        String str = "http://www.example.com/path/";
        URL url = new URL(str);
        String expectedURLInString = "http://www.example.com/path/";
        String URLInString = url.toString();

        Assert.assertEquals(expectedURLInString, URLInString);

    }

    @Test
    public void URLContainsPort(){
        String str = "http://www.example.com:8080/path/";
        URL url = new URL(str);
        String expectedURLInString = "http://www.example.com:8080/path/";
        String URLInString = url.toString();

        Assert.assertEquals(expectedURLInString, URLInString);

    }

    @Test
    public void pathPointsToDirectoryAndDirectoryDoesNotEndWithSlash(){
        String str = "http://www.example.com/path";
        URL url = new URL(str);
        String expectedURLInString = "http://www.example.com/path/";
        String URLInString = url.toString();

        Assert.assertEquals(expectedURLInString, URLInString);

    }

    @Test
    public void pathPointsToFile(){
        String str = "http://www.example.com/file.txt";
        URL url = new URL(str);
        String expectedURLInString = "http://www.example.com/file.txt";
        String URLInString = url.toString();

        Assert.assertEquals(expectedURLInString, URLInString);

    }
    @Test
    public void pathMoreThanOneDirectory(){
        String str = "http://www.example.com/path/path2/";
        URL url = new URL(str);
        String expectedURLInString = "http://www.example.com/path/path2/";
        String URLInString = url.toString();

        Assert.assertEquals(expectedURLInString, URLInString);

    }

    @Test
    public void pathMoreThanOneDirectoryAndPointsToFile(){
        String str = "http://www.example.com/path/path2/file.txt";
        URL url = new URL(str);
        String expectedURLInString = "http://www.example.com/path/path2/file.txt";
        String URLInString = url.toString();

        Assert.assertEquals(expectedURLInString, URLInString);

    }

    @Test
    public void URLcontainsFragment1(){
        String str = "http://www.example.com/path/#ancher";
        URL url = new URL(str);
        String expectedURLInString = "http://www.example.com/path/#ancher";
        String URLInString = url.toString();

        Assert.assertEquals(expectedURLInString, URLInString);

    }

    @Test
    public void URLcontainsFragment2(){
        String str = "http://www.example.com/path#ancher";
        URL url = new URL(str);
        String expectedURLInString = "http://www.example.com/path/#ancher";
        String URLInString = url.toString();

        Assert.assertEquals(expectedURLInString, URLInString);

    }

    @Test
    public void pathEndWithFileAndURLContainsFragment(){
        String str = "http://www.example.com/path/file.txt#ancher";
        URL url = new URL(str);
        String expectedURLInString = "http://www.example.com/path/file.txt#ancher";
        String URLInString = url.toString();

        Assert.assertEquals(expectedURLInString, URLInString);

    }

    @Test
    public void URLNotContainsPath1(){
        String str = "http://www.example.com/";
        URL url = new URL(str);
        String expectedURLInString = "http://www.example.com/";
        String URLInString = url.toString();

        Assert.assertEquals(expectedURLInString, URLInString);

    }

    @Test
    public void URLNotContainsPath2(){
        String str = "http://www.example.com";
        URL url = new URL(str);
        String expectedURLInString = "http://www.example.com/";
        String URLInString = url.toString();

        Assert.assertEquals(expectedURLInString, URLInString);

    }

    @Test
    public void URLNotContainsProtocol(){
        String str = "www.example.com/path/";
        URL url = new URL(str);
        String expectedURLInString = "www.example.com/path/";
        String URLInString = url.toString();

        Assert.assertEquals(expectedURLInString, URLInString);

    }

    @Test
    public void URLContainsOnlyHost1(){
        String str = "www.example.com";
        URL url = new URL(str);
        String expectedURLInString = "www.example.com/";
        String URLInString = url.toString();

        Assert.assertEquals(expectedURLInString, URLInString);

    }

    @Test
    public void URLContainsOnlyHost2(){
        String str = "www.example.com:8080";
        URL url = new URL(str);
        String expectedURLInString = "www.example.com:8080/";
        String URLInString = url.toString();

        Assert.assertEquals(expectedURLInString, URLInString);

    }

    @Test
    public void URLNotContainsHostAndPathIsSimilarToDomain(){
        String str = "/www.example.com/path/";
        URL url = new URL(str);
        String expectedURLInString = "www.example.com/path/";
        String URLInString = url.toString();

        Assert.assertEquals(expectedURLInString, URLInString);

    }


    @Test
    public void URLIsOnlyPath1(){
        String str = "/path/";
        URL url = new URL(str);
        String expectedURLInString = "path/";
        String URLInString = url.toString();

        Assert.assertEquals(expectedURLInString, URLInString);

    }

    @Test
    public void URLIsOnlyPath2(){
        String str = "path";
        URL url = new URL(str);
        String expectedURLInString = "path/";
        String URLInString = url.toString();

        Assert.assertEquals(expectedURLInString, URLInString);

    }

    @Test
    public void URLIsOnlyPath3(){
        String str = "/path";
        URL url = new URL(str);
        String expectedURLInString = "path/";
        String URLInString = url.toString();

        Assert.assertEquals(expectedURLInString, URLInString);

    }

    @Test
    public void URLIsOnlyPathAndPathIsDirectoryButSimilarFile(){
        String str = "file.txt/";
        URL url = new URL(str);
        String expectedURLInString = "file.txt/";
        String URLInString = url.toString();

        Assert.assertEquals(expectedURLInString, URLInString);

    }



    @Test
    public void URLIsOnlyPathAndPathIsFile(){
        String str = "/file.txt";
        URL url = new URL(str);
        String expectedURLInString = "file.txt";
        String URLInString = url.toString();

        Assert.assertEquals(expectedURLInString, URLInString);

    }
}
