package com.company;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class equalsTest {

    @Test
    public void TypeTest(){

        String urlString1 = "http://www.example.com/path/";
        String urlString2 = "http://www.example.com/path/";

        URL url = new URL(urlString1);
        URL otherUrl = new URL(urlString2);

        boolean expectedIsEqual = true;
        boolean isEqual = url.equals(otherUrl);

        Assert.assertEquals(expectedIsEqual, isEqual);
    }

    @Test
    public void DirectoriesHaveDifferent(){

        String urlString1 = "http://www.example.com/path/";
        String urlString2 = "http://www.example.com/path";

        URL url = new URL(urlString1);
        URL otherUrl = new URL(urlString2);

        boolean expectedIsEqual = true;
        boolean isEqual = url.equals(otherUrl);

        Assert.assertEquals(expectedIsEqual, isEqual);
    }

    @Test
    public void relativeAddress(){

        String urlString1 = "www.example.com/path";
        String urlString2 = "www.example.com/path";

        URL url = new URL(urlString1);
        URL otherUrl = new URL(urlString2);

        boolean expectedIsEqual = true;
        boolean isEqual = url.equals(otherUrl);

        Assert.assertEquals(expectedIsEqual, isEqual);
    }

    @Test
    public void pathIsMoreThanOne1 (){

        String urlString1 = "http://www.example.com/path/path2/";
        String urlString2 = "http://www.example.com/path/path2/";

        URL url = new URL(urlString1);
        URL otherUrl = new URL(urlString2);

        boolean expectedIsEqual = true;
        boolean isEqual = url.equals(otherUrl);

        Assert.assertEquals(expectedIsEqual, isEqual);
    }

    @Test
    public void pathIsMoreThanOne2(){

        String urlString1 = "http://www.example.com/path/path2/";
        String urlString2 = "http://www.example.com/path/path2";

        URL url = new URL(urlString1);
        URL otherUrl = new URL(urlString2);

        boolean expectedIsEqual = true;
        boolean isEqual = url.equals(otherUrl);

        Assert.assertEquals(expectedIsEqual, isEqual);
    }

    @Test
    public void urlHaveDifferentProtocols(){

        String urlString1 = "http://www.example.com/path/";
        String urlString2 = "https://www.example.com/path/";

        URL url = new URL(urlString1);
        URL otherUrl = new URL(urlString2);

        boolean expectedIsEqual = false;
        boolean isEqual = url.equals(otherUrl);

        Assert.assertEquals(expectedIsEqual, isEqual);
    }

    @Test
    public void urlHaveDifferentHost(){

        String urlString1 = "http://www.example.com/path/";
        String urlString2 = "http://www.example1.com/path/";

        URL url = new URL(urlString1);
        URL otherUrl = new URL(urlString2);

        boolean expectedIsEqual = false;
        boolean isEqual = url.equals(otherUrl);

        Assert.assertEquals(expectedIsEqual, isEqual);
    }

    @Test
    public void urlHaveDifferentPath(){

        String urlString1 = "http://www.example.com/path/";
        String urlString2 = "http://www.example.com/path1/";

        URL url = new URL(urlString1);
        URL otherUrl = new URL(urlString2);

        boolean expectedIsEqual = false;
        boolean isEqual = url.equals(otherUrl);

        Assert.assertEquals(expectedIsEqual, isEqual);
    }

    @Test
    public void urlHavePort(){

        String urlString1 = "http://www.example.com:8080/path/";
        String urlString2 = "http://www.example.com:8080/path/";

        URL url = new URL(urlString1);
        URL otherUrl = new URL(urlString2);

        boolean expectedIsEqual = true;
        boolean isEqual = url.equals(otherUrl);

        Assert.assertEquals(expectedIsEqual, isEqual);
    }

    @Test
    public void oneURLIsPartOfOther(){

        String urlString1 = "http://www.example.com:8080/path/";
        String urlString2 = "http://www.example.com:8080/path/path2";

        URL url = new URL(urlString1);
        URL otherUrl = new URL(urlString2);

        boolean expectedIsEqual = false;
        boolean isEqual = url.equals(otherUrl);

        Assert.assertEquals(expectedIsEqual, isEqual);
    }

    @Test
    public void oneURLHavePort(){

        String urlString1 = "http://www.example.com:8080/path/";
        String urlString2 = "http://www.example.com/path/";

        URL url = new URL(urlString1);
        URL otherUrl = new URL(urlString2);

        boolean expectedIsEqual = false;
        boolean isEqual = url.equals(otherUrl);

        Assert.assertEquals(expectedIsEqual, isEqual);
    }


    @Test
    public void urlHaveDifferentPort(){

        String urlString1 = "http://www.example.com:8080/path/";
        String urlString2 = "http://www.example.com:8081/path/";

        URL url = new URL(urlString1);
        URL otherUrl = new URL(urlString2);

        boolean expectedIsEqual = false;
        boolean isEqual = url.equals(otherUrl);

        Assert.assertEquals(expectedIsEqual, isEqual);
    }

    @Test
    public void oneURLHaveFragment(){

        String urlString1 = "http://www.example.com/path/#ancher";
        String urlString2 = "http://www.example.com/path/";

        URL url = new URL(urlString1);
        URL otherUrl = new URL(urlString2);

        boolean expectedIsEqual = false;
        boolean isEqual = url.equals(otherUrl);

        Assert.assertEquals(expectedIsEqual, isEqual);
    }

    @Test
    public void URLHaveSimilarFragment(){

        String urlString1 = "http://www.example.com/path/#ancher";
        String urlString2 = "http://www.example.com/path/#ancher";

        URL url = new URL(urlString1);
        URL otherUrl = new URL(urlString2);

        boolean expectedIsEqual = true;
        boolean isEqual = url.equals(otherUrl);

        Assert.assertEquals(expectedIsEqual, isEqual);
    }

    @Test
    public void URLHaveDifferentFragment(){

        String urlString1 = "http://www.example.com/path/#ancher";
        String urlString2 = "http://www.example.com/path/#amcher";

        URL url = new URL(urlString1);
        URL otherUrl = new URL(urlString2);

        boolean expectedIsEqual = false;
        boolean isEqual = url.equals(otherUrl);

        Assert.assertEquals(expectedIsEqual, isEqual);
    }

    @Test
    public void oneURLIsRelativeAddressOfOtherURL1(){

        String urlString1 = "http://www.example.com/path/";
        String urlString2 = "www.example.com/path/";

        URL url = new URL(urlString1);
        URL otherUrl = new URL(urlString2);

        boolean expectedIsEqual = false;
        boolean isEqual = url.equals(otherUrl);

        Assert.assertEquals(expectedIsEqual, isEqual);
    }

    @Test
    public void oneURLIsRelativeAddressOfOtherURL2(){

        String urlString1 = "http://www.example.com/path/";
        String urlString2 = "/path/";

        URL url = new URL(urlString1);
        URL otherUrl = new URL(urlString2);

        boolean expectedIsEqual = false;
        boolean isEqual = url.equals(otherUrl);

        Assert.assertEquals(expectedIsEqual, isEqual);
    }

    @Test
    public void oneURLIsRelativeAddressOfOtherURL3(){

        String urlString1 = "http://www.example.com/path/";
        String urlString2 = "path/";

        URL url = new URL(urlString1);
        URL otherUrl = new URL(urlString2);

        boolean expectedIsEqual = false;
        boolean isEqual = url.equals(otherUrl);

        Assert.assertEquals(expectedIsEqual, isEqual);
    }

    @Test
    public void pathPointsToFile(){

        String urlString1 = "http://www.example.com/file.exe";
        String urlString2 = "http://www.example.com/file.exe";

        URL url = new URL(urlString1);
        URL otherUrl = new URL(urlString2);

        boolean expectedIsEqual = true;
        boolean isEqual = url.equals(otherUrl);

        Assert.assertEquals(expectedIsEqual, isEqual);
    }

    @Test
    public void DirectoryHaveSimilarNameLikeFileInOtherURL(){

        String urlString1 = "http://www.example.com/file.exe";
        String urlString2 = "http://www.example.com/file.exe/";

        URL url = new URL(urlString1);
        URL otherUrl = new URL(urlString2);

        boolean expectedIsEqual = false;
        boolean isEqual = url.equals(otherUrl);

        Assert.assertEquals(expectedIsEqual, isEqual);
    }

    @Test
    public void URLHaveSimilarNameButOneStartWithDomain(){

        String urlString1 = "/www.example.com/path/";
        String urlString2 = "www.example.com/path/";

        URL url = new URL(urlString1);
        URL otherUrl = new URL(urlString2);

        boolean expectedIsEqual = false;
        boolean isEqual = url.equals(otherUrl);

        Assert.assertEquals(expectedIsEqual, isEqual);
    }
}
