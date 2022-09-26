package com.company;

public class URLNotCreatedException extends  RuntimeException{

    public URLNotCreatedException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
    public  URLNotCreatedException(String errorMessage){
        super(errorMessage);
    }
}
