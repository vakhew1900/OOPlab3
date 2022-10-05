package com.company;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import com.company.URL;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class isValidTest {

    @Test
    public void typeTest(){

        List<String> urlList = Arrays.asList(new String[]{"http://www.example.com/path/", "http://www.example.com/path_1/",
                                            "http://www.example.com/path-1/"});

        boolean expectedIsValid = true;

        boolean isValid = true;

        for (String url : urlList){
            isValid = isValid && URL.isValid(url);
        }

        Assert.assertEquals(expectedIsValid, isValid);
    }

    @Test
    public void URLContainsPort(){
        String url = "http://www.example.com:8080/path/";

        boolean expectedIsValid = true;
        boolean isValid = URL.isValid(url);

        Assert.assertEquals(expectedIsValid, isValid);
    }

    @Test
    public void pathPointsToDirectoryAndDirectoryDoesNotEndWithSlash(){
        String url = "http://www.example.com:8080/path";

        boolean expectedIsValid = true;
        boolean isValid = URL.isValid(url);

        Assert.assertEquals(expectedIsValid, isValid);
    }

    @Test
    public void pathPointsToFile(){
        String url = "http://www.example.com:8080/file.txt";

        boolean expectedIsValid = true;
        boolean isValid = URL.isValid(url);

        Assert.assertEquals(expectedIsValid, isValid);
    }

    @Test
    public void pathMoreThanOneDirectory(){
        String url = "http://www.example.com/path/path2/";

        boolean expectedIsValid = true;
        boolean isValid = URL.isValid(url);

        Assert.assertEquals(expectedIsValid, isValid);
    }

    @Test
    public void pathMoreThanOneDirectoryAndPointsToFile(){
        String url = "http://www.example.com/path/path2/file.txt";

        boolean expectedIsValid = true;
        boolean isValid = URL.isValid(url);

        Assert.assertEquals(expectedIsValid, isValid);
    }


    @Test
    public void URLcontainsFragment(){
        List<String> urlList = Arrays.asList(new String[]{"http://www.example.com/path/#ancher", "http://www.example.com/path#ancher"});

        boolean expectedIsValid = true;

        boolean isValid = true;

        for (String url : urlList){
            isValid = isValid && URL.isValid(url);
        }

        Assert.assertEquals(expectedIsValid, isValid);
    }

    @Test
    public void URLContainsFragmentAndFragmentIsEmpty(){
        String url = "http://www.example.com/path/#";

        boolean expectedIsValid = false;
        boolean isValid = URL.isValid(url);

        Assert.assertEquals(expectedIsValid, isValid);
    }

    @Test
    public void pathEndWithFileAndURLContainsFragment(){
        String url = "http://www.example.com/path/file.txt#ancher";

        boolean expectedIsValid = true;
        boolean isValid = URL.isValid(url);

        Assert.assertEquals(expectedIsValid, isValid);
    }

    @Test
    public void protocolIsEmpty(){
        String url = "://www.example.com/path/";

        boolean expectedIsValid = false;
        boolean isValid = URL.isValid(url);

        Assert.assertEquals(expectedIsValid, isValid);
    }

    @Test
    public void URLIsFragment(){
        String url = "#ancher";

        boolean expectedIsValid = false;
        boolean isValid = URL.isValid(url);

        Assert.assertEquals(expectedIsValid, isValid);
    }

    @Test
    public void URLIsProtocol(){
        String url = "http://";

        boolean expectedIsValid = false;
        boolean isValid = URL.isValid(url);

        Assert.assertEquals(expectedIsValid, isValid);
    }

    @Test
    public void URLNotContainsPath(){
        List<String> urlList = Arrays.asList(new String[]{"http://www.example.com", "http://www.example.com/"});

        boolean expectedIsValid = true;

        boolean isValid = true;

        for (String url : urlList){
            isValid = isValid && URL.isValid(url);
        }

        Assert.assertEquals(expectedIsValid, isValid);
    }

    @Test
    public void URLNotContainsProtocol(){
        String url = "www.example.com/path/";

        boolean expectedIsValid = true;
        boolean isValid = URL.isValid(url);

        Assert.assertEquals(expectedIsValid, isValid);
    }

    @Test
    public void URLNotContainsProtocolAndPath(){
        String url = "www.example.com/";

        boolean expectedIsValid = true;
        boolean isValid = URL.isValid(url);

        Assert.assertEquals(expectedIsValid, isValid);
    }

    @Test
    public void URLNotContainsHostAndPathIsSimilarToDomain(){
        String url = "/www.example.com/path";

        boolean expectedIsValid = true;
        boolean isValid = URL.isValid(url);

        Assert.assertEquals(expectedIsValid, isValid);
    }

    @Test
    public void URLNotContainsHost(){
        List<String> urlList = Arrays.asList(new String[]{"/path", "/path/", "path/", "path"});

        boolean expectedIsValid = true;

        boolean isValid = true;

        for (String url : urlList){
            isValid = isValid && URL.isValid(url);
        }

        Assert.assertEquals(expectedIsValid, isValid);
    }

    @Test
    public void URLNotContainsHostAndPathIsFile(){
        String url = "/file.txt";

        boolean expectedIsValid = true;
        boolean isValid = URL.isValid(url);

        Assert.assertEquals(expectedIsValid, isValid);
    }

    @Test
    public void URLNotContainsHostAndContainsProtocol(){
        String url = "http://path";

        boolean expectedIsValid = false;
        boolean isValid = URL.isValid(url);

        Assert.assertEquals(expectedIsValid, isValid);
    }

    @Test
    public void URLContainsTwoSeparatorsWhichSeparateProtocolAndHost(){

        List<String> urlList = Arrays.asList(new String[]{"http://www.example://.com/path/", "http://www.example.com://path/", "http://www.example.com/path://", "http://://www.example.com/path/"});

        boolean expectedIsValid = false;

        boolean isValid = false;

        for (String url : urlList){
            isValid = isValid || URL.isValid(url);
        }

        Assert.assertEquals(expectedIsValid, isValid);
    }


    @Test
    public void URLContainsTwoSlashInRow(){

        List<String> urlList = Arrays.asList(new String[]{"http://www.example//.com/path/", "http://www.example.com//path/", "http://www.example.com/path//", "http:///www.example.com/path/"});

        boolean expectedIsValid = false;

        boolean isValid = false;

        for (String url : urlList){
            isValid = isValid || URL.isValid(url);
        }

        Assert.assertEquals(expectedIsValid, isValid);
    }

    @Test
    public void URLContainsTwoSharpInRow(){

        List<String> urlList = Arrays.asList(new String[]{"http://www.example##.com/path/", "http##://www.example.com/path/", "http://www.example.com/path##/"});

        boolean expectedIsValid = false;

        boolean isValid = false;

        for (String url : urlList){
            isValid = isValid || URL.isValid(url);
        }

        Assert.assertEquals(expectedIsValid, isValid);
    }



    @Test
    public void URLContainsClimbUp(){

        List<String> urlList = Arrays.asList(new String[]{"http://www.example.com/../path/", "http://www.example.com/path/../",});

        boolean expectedIsValid = false;

        boolean isValid = false;

        for (String url : urlList){
            isValid = isValid || URL.isValid(url);
        }

        Assert.assertEquals(expectedIsValid, isValid);
    }

    @Test
    public void DirectoryContainsOnlyPoints(){

        List<String> urlList = Arrays.asList(new String[]{"http://www.example##.com/path/.", "http##://www.example.com/../", "http://www.example.com/.."});

        boolean expectedIsValid = false;

        boolean isValid = false;

        for (String url : urlList){
            isValid = isValid || URL.isValid(url);
        }

        Assert.assertEquals(expectedIsValid, isValid);
    }


    @Test
    public void DirectoryContainsInvalidChar(){

        List<String> urlList = Arrays.asList(new String[]{"http://www.example.com/pat!h", "http://www.exampl!e.com/", "htt!p://www.example.com",
                 "http://www.example.com/pat,h", "http://www.exampl,e.com/", "htt,p://www.exampl,e.com/path", "www.example:.com", "www.exam.com/1:"});

        boolean expectedIsValid = false;

        boolean isValid = false;

        for (String url : urlList){
            isValid = isValid || URL.isValid(url);
        }

        Assert.assertEquals(expectedIsValid, isValid);
    }

    @Test
    public void URLContainsTwoFragment(){
        String url = "http://www.example.com/path/#anc#her";

        boolean expectedIsValid = false;
        boolean isValid = URL.isValid(url);

        Assert.assertEquals(expectedIsValid, isValid);
    }

    @Test
    public void InvalidProtocol(){
        String url = "htp://www.example.com/path/#anc#her";

        boolean expectedIsValid = false;
        boolean isValid = URL.isValid(url);

        Assert.assertEquals(expectedIsValid, isValid);
    }


    @Test
    public void InvalidPort(){

        List<String> urlList = Arrays.asList(new String[]{"http://www.example.com:-1/path/", "http://www.example.com:65536/path/", "http://www.example.com:abc/path/"});

        boolean expectedIsValid = false;

        boolean isValid = false;

        for (String url : urlList){
            isValid = isValid || URL.isValid(url);
        }

        Assert.assertEquals(expectedIsValid, isValid);
    }

    @Test
    public void InvalidDomain(){
        String url = "http://.com/path/";

        boolean expectedIsValid = false;
        boolean isValid = URL.isValid(url);

        Assert.assertEquals(expectedIsValid, isValid);
    }

    @Test
    public void URLIsEmpty(){
        String url = "";

        boolean expectedIsValid = false;
        boolean isValid = URL.isValid(url);

        Assert.assertEquals(expectedIsValid, isValid);
    }

    @Test
    public void URLIsNull(){
        String url = null;

        boolean expectedIsValid = false;
        boolean isValid = URL.isValid(url);

        Assert.assertEquals(expectedIsValid, isValid);
    }

    @Test
    public void URLIsSpace(){
        String url = " ";

        boolean expectedIsValid = false;
        boolean isValid = URL.isValid(url);

        Assert.assertEquals(expectedIsValid, isValid);
    }



}