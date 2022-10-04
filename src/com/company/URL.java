package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class URL {

    private String protocol = "";
    private String host = "";
    private String path = "";
    private String fragment = "";
    private  TypeOfURL typeOfURL;

    private static List<String> protocolList = Arrays.asList(new String[]{"http", "https", "ftp"}); // список протоколов
    private static List<String > topLevelDomainList = Arrays.asList(new String[]{"com", "edu", "ru", "org", "net", "mil"});

    /*--------------------- Конструкторы ------------------------------ */
    /** конструктор
     *
     * @param url URL-адрес
     */
    public  URL(String url){

        boolean isValid = isValid(url);

        if (!isValid){
            throw  new URLNotCreatedException("String is not valid for url ");
        }

        List<String> urlAndFragment = Arrays.asList(url.split("#")); // отделяем путь от якоря
        if (urlAndFragment.size() == 2) this.fragment = urlAndFragment.get(1); // если якорь присутствует, то присвоить якоря объекта значение этого якоря
        List<String> protocolAndPath = new ArrayList<>(Arrays.asList(urlAndFragment.get(0).split("://")));

        if (protocolAndPath.size() == 2){
            this.protocol = protocolAndPath.get(0);
            protocolAndPath.remove(0);
        }

        String hostAndPath = protocolAndPath.get(0);
        int firstSlashIndex = hostAndPath.indexOf("/");

        //boolean isHost = false;
        if(firstSlashIndex != -1) {

            if(isValidHost(hostAndPath.substring(0, firstSlashIndex)))
                this.host = hostAndPath.substring(0, firstSlashIndex);
        }
        else {
            if(isValidHost(hostAndPath))
                this.host = hostAndPath;
        }

        String path = "";
        if(!host.isEmpty()){
            if(firstSlashIndex + 1 <= hostAndPath.length() - 1 && firstSlashIndex != -1)
                path =  hostAndPath.substring(firstSlashIndex + 1, hostAndPath.length());
        }else {
            path = hostAndPath;
        }

        this.path = path;
        if(isFile(path)){ //   путь указывает на файл
            this.typeOfURL = TypeOfURL.FILE; // считать, что ссылка указывает на файл
        }
        else
        {
            this.typeOfURL = TypeOfURL.DIRECTORY; // считать, что ссылка указывает на каталог
            if (!path.isEmpty() && !(path.endsWith("/")))  this.path += "/"; // добавить слэщ в конце url пути
        }

        System.out.println("protocol: " + protocol + "\n domain:" + host + "\n path:" + this.path);

    }

    /* ----------------- Функции для проверки валидности ----------------*/
    /*** проверка, является ли URL-адресч валидным
     *
     * @param str-адрес в виде строки
     * @return true - если url-адрес является валидным, иначе false
     */
    public  static  boolean isValid(String str) {

        boolean isValid = true; // считать, что строка является валидной

        isValid = isValid && str.indexOf("##") == -1; // если в строке стоять две # подряд, то считать, что строка не является url-адресом
        isValid = isValid && str.startsWith("#") == false  && str.endsWith("#") == false; // если строка начинается или оканчивается #, то считать, что строка не является url- адресом
        List<String> urlAndFragment = Arrays.asList(str.split("#")); // разделить строку на url и якорь
        isValid = isValid && urlAndFragment.size() <= 2; // если количество # в строке больше 1, считать, что строка не является url-адресом

        if (urlAndFragment.size()== 2){ // якорь есть в url
            isValid = isValid && isValidFragment(urlAndFragment.get(1)); // если вторая строка-якорь не является якорем , то считать, что строка не является url-адресов
        }

        isValid = isValid && str.indexOf("://://") == -1; // если в строке встречается разделитель между протоколом и доменом два раза подряд, то считать, что строка не является url-адресом

        if(urlAndFragment.get(0).endsWith("://")) isValid = false; // если строка заканчивается разделителем между протоколом и доменом, считать, что строка не является протоколом
//        if(urlAndFragment.get(0).endsWith("/."))  isValid = false; // если строка заканчивается путем до каталога с именем /., то считать, что строка не является протоколом
        List<String> protocolAndPath = new ArrayList<>(Arrays.asList(urlAndFragment.get(0).split("://"))); // разделить url на протокол и путь
        isValid = isValid && protocolAndPath.size() <= 2; // если количество разделителей между протоколом и url больше 1, то считать, что url не является строкой

        boolean isDomainRequired = false; // cчитать, что домен не обязателен
        if (protocolAndPath.size() == 2){ // присутствует разделитель между протоколом и путем
            isValid = isValid && isValidProtocol(protocolAndPath.get(0)); // если первая строка списка не является протоколом, то считать, что строка не является url
//            if(protocolAndPath.get(1).startsWith("/"))  isValid = false;
            protocolAndPath.remove(0); // удалить строку-протокол из списка
            isDomainRequired = true; // считать, что протокол обязателен.
        }

        isValid = isValid && protocolAndPath.get(0).indexOf("//") ==-1;  // если в строке стоять два / подряд, то считать, что строка не является url-адресом
        isValid = isValid && protocolAndPath.get(0).indexOf("::") ==-1; // если в строке стоять два # подряд, то считать, что строка не является url-адресом
        isValid = isValid && protocolAndPath.get(0).isEmpty() == false; // если строка пустая, то считать, что строка не является url-адресом
        List<String> pathList = Arrays.asList(protocolAndPath.get(0).split("/")); // разделить путь на список поддиректорий

        if (isDomainRequired){ // домен обязателен в записи
            isValid = isValid && isValidHost(pathList.get(0)); // если первая строка в списке пути не является доменом, считать, что строка не является url-адресом
        }

        if (isValidHost(pathList.get(0)) == false && pathList.get(0).indexOf(":") != -1){ // первая строка в списке путей не является доменом, но есть разделитель для порта
            isValid = false; // считать, что строка не является url-адресом
        }

        String path = null;
        if (isValidHost(pathList.get(0)) == false){ // первая строка не является доменом
            path = pathList.get(0); // добавить первую строку списка путей в строку пути
        }

        // составить путь url-адреса без протокола и домена с портом
        for(int i = 1; i < pathList.size(); i++){
            path = path == null? pathList.get(i) : path + "/" + pathList.get(i);
        }

        if(path != null) {
          isValid = isValid && isValidPath(path);
        }

        return  isValid;
    }

    /** Проверяет является ли строка путем
     *
     * @param str - строка
     * @return true - если является, иначе false
     */
    private static boolean isValidPath(String str){

        if (str == null) return false;

        boolean isPath = isValidSymbol(str, "./_-");
        str = "/" + str;

        List<String> regexList = Arrays.asList(new String[]{"/[\\.]+/", "(^[\\.]+/)|(/[\\.]+$)"});

        for (String regex : regexList){
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(str);
            isPath = isPath && !matcher.find();
        };


        return isPath;
    }

    /** проверяет, является ли строка подстрокой
     *
     * @param str - строка
     * @return true - если является, иначе false
     */
    private static boolean isValidProtocol(String str)  {

        boolean isProtocol = false; // считать, что строка не является протоколом

        for(String protocol : protocolList){ // для всех доступных протоколов
            isProtocol = isProtocol || str.equals(protocol); // если строка равна протоколу
        }

        return isProtocol;
    }

    /** проверка, является ли строка правильным хостом
     *
     * @param str строка
     * @return true - если строка является доменом, иначе false
     */
    private static boolean isValidHost(String str){
        if(str == null || str.isEmpty())
            return  false;

        List<String>  domainArr = Arrays.asList(str.split(":"));
        String domain = domainArr.get(0); // считать, что строка является доменным именем

        boolean isHost = true; // считать, что строка  является хостом

        if (domainArr.size() > 2){ // количество ":" больше одного
            isHost = false; // считать, что строка не является хостом
        }

        if (domainArr.size() == 2){ // если в строке есть ":"
            isHost = isHost && isValidPort(domainArr.get(1)); // проверить, есть ли порт. Если порта нет, то считать, что строка не является хостом.
        }

        isHost = isHost && isValidDomain(domain); // проверить является ли первая часть строки доменным именем

        return isHost;
    }

    /** проверяет, является ли строка хостом
     * @param  str -строка
     * @return  true- если строка является доменом, иначе false
     */
    private static boolean isValidDomain(String str){

        boolean isDomain = true ; // считать, что строка является доменом

        if (!str.isEmpty()) {
            String regex = "^((?!-)[A-Za-z0-9-]"
                    + "{1,63}(?<!-)\\.)"
                    + "+[A-Za-z]{2,6}"; // регулярное выражение для доме

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher =pattern.matcher(str);
            isDomain = isDomain && matcher.matches(); // проверить соответствует ли доменное имя регулярному выражению
            isDomain = isDomain || str.equals("localhost");
        }
        else {
            isDomain = true; // строка не является доменом
        }

        return  isDomain;
    }

    /** проверка, является ли строка портом или нет
     *
     * @param str строка
     * @return true - если является портом иначе false
     */
    private static boolean isValidPort(String str){
        boolean isPort  = true; // считать, что строка является портом

        try {
            int port = Integer.parseInt(str); // строка является числом в пределах int

            if (port < 0 || port > 65535){ // число больше количества портов
                isPort = false; // считать, что строка не является портом
            }

        }catch (NumberFormatException ex){ // строка не является числом в пределах int
            isPort = false; // считать, что строка не является портом.
        }

        return isPort;
    }

    /*** проверка, указывает ли URL-адрес на файл или директорию
     *
     * @return true- если URL-адрес указывает на файл, false - если указывает на директорию
     */
    private static  boolean isFile(String str){
        System.out.println(str);
        boolean isFile = false; // считать, что строка не является файлом
        isFile = str.matches("[\\w/\\.]+\\.[\\w]+"); // если строка  имеет  точку и расширение, то считать, что строка является файлом

        return isFile;
    }


    /** проверяет, является ли строка якорем
     *
     * @param str - строка
     * @return true - если является, иначе false
     */
    private static boolean isValidFragment(String str)  {

        boolean isFragment = true; // считать, что строка не является якорем
        if(str == null || str.isEmpty()) isFragment = false; // если строка пустая, то считать, что строка не является якорем
        isFragment = isFragment && isValidSymbol(str, "._-"); // если строка содержит недопустимые символы,то считать, что строка не является фрагментом

        return isFragment;
    }

    /** проверка, является ли строка валидной для url- адреса
     * @param  str - строка, которую проверяют
     * @param  additionalSymbols - дополнительные символы, допустимые в строке
     * @return true - если валидная строка, иначе false
     */
    private static boolean isValidSymbol(String str, String additionalSymbols){

        if(str == null || str.isEmpty())
            return false;

        boolean isValid = true; // считать, что строка валидна

        char[] chars = str.toCharArray(); // перевод строки в массив символов

        for(char c : chars){ // для всех символов
            isValid = isValid && (Character.isLetterOrDigit(c)
                                || (additionalSymbols!= null &&  additionalSymbols.indexOf(c) != -1)); // символ является числом, буквой или дополнительным символом
        }

        return isValid;
    }

    /* ------------------- Операциии сравнения -------------------------*/
    /*** проверка, равны ли адреса между друг другом
     *
     * @param other другой url-адрес с которым происходит сравнение
     * @return true- если адреса эквивалентны, иначе false
     */
    @Override
    public boolean equals(Object other){

        if (other == null || getClass() != other.getClass()){ // other не является объектом класса URL
            return  false;
        }

        URL otherUrl = (URL) other;
        return  this.toString().equals(otherUrl.toString()); // сравниваем адреса
    }

    /*------------------------ Геттеры ------------------------------------ */

    /** возвращает на что указывает ссылка
     *
     * @return TypeOfUrl.FILE, если url указывает на файл, TypeOfURL.DIRECTORY, если указывает на каталог
     */
    public TypeOfURL getTypeOfURL() {
        return typeOfURL;
    }

    /*------------------- Операции вычисления адресов ------------------*/

    /** вычислить новый url-адрес
     *
     * @param relativeAddress относительный адрес
     * @return новый url-адрес
     */
    public  URL calculateNewUrl(String relativeAddress){

        if (this.typeOfURL == TypeOfURL.FILE){
            throw new  URLNotCreatedException("Cannot create new URL");
        }

        int substrCount = substrCount(relativeAddress, "/../"); // находим количество вхождений пдъема наверх по иерархии в url.
        if (relativeAddress.startsWith("../")) substrCount++;

        String tmpRelativeAddress = null;
        int startPosition = 0;
        if (substrCount == 0 && relativeAddress.startsWith("/")){
            startPosition++;
        }
        if(substrCount == 0){
            tmpRelativeAddress = relativeAddress.substring(startPosition);
        }

        if (substrCount > 0){
            tmpRelativeAddress = relativeAddress.substring(substrCount * "../".length());
        }

        if(isValid(tmpRelativeAddress) == false) throw  new URLNotCreatedException("Cannot create new URL");
        List<String> pathList = Arrays.asList(path.split("/"))
                    .stream().filter(s->s.isEmpty() == false).collect(Collectors.toList());

        if(pathList.size() < substrCount){
            throw  new URLNotCreatedException("Cannot create new URL, because can`t go upstairs");
        }

        String newPath = "";

        for (int i = 0; i < pathList.size() - substrCount; i++){
            newPath+= pathList.get(i) + "/";
        }

        newPath+= tmpRelativeAddress;

        String newUrl = "";

        if (protocol.isEmpty() == false) newUrl += protocol + "://";
        if (host.isEmpty() == false) newUrl += host + "/";
        newUrl+=newPath;

        try {
            return new URL(newUrl);
        }
        catch (URLNotCreatedException e){
            throw  new URLNotCreatedException(e.getMessage());
        }
    }

    /** определить количество вхождений подстроки в строке
     *
     * @param str - строка, в которой мы ищем подстроку
     * @param substr - подстрока
     * @return количество подстрок в строке
     */
    private   int substrCount(String str, String substr){

        int count = (int) IntStream.iterate(
                str.indexOf(substr), // начинаем с первого вхождения
                i -> i != -1, // пока не получим негативный ответ
                i -> str.indexOf(substr, i + 1)) // ищем следующее вхождение
                .count(); // считаем вхождения

        return count;
    }
    /*------------ Перевод в другие объекты -----------------------------*/

    /*** Перевод URL в строку
     *
     * @return строковое представление URL-адреса
     */
    @Override
    public String toString(){
        String str = "";
        if (protocol.isEmpty() == false) str += protocol + "://";
        if (host.isEmpty() == false) str += host + "/";
        if (path.isEmpty() == false) str += path;
        if (fragment.isEmpty() == false) str+= "#" + fragment;

        return str;
    }
}
