package com.ll.basic1.boundedContext.home.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.*;

public class HomeController {
    private int i;
    List<Person> person = new ArrayList<>();

    public HomeController() {
        i = 0;
    }

    @GetMapping("/home/main")
    @ResponseBody
    public String showMain(){
        return "안녕하세요!";
    }

    @GetMapping("/home/main2")
    @ResponseBody
    public String showMain2(){
        return "반갑습니다.";
    }

    @GetMapping("/home/main3")
    @ResponseBody
    public String showMain3(){
        return "즐거웠습니다!";
    }

    @GetMapping("/home/increase")
    @ResponseBody
    public int showIncrease(){
        return i++;
    }

    @GetMapping("/home/plus")
    @ResponseBody
    public int showPlus(@RequestParam(defaultValue = "0") int a, int b){
        return a + b;
    }

    @GetMapping("/home/addPerson")
    @ResponseBody
    public String addPerson(@RequestParam String name, int age){
        Person person1 = new Person(name, age);

        person.add(person1);
        return person1.getId() + "번 사람이 추가되었습니다.";
    }

    @GetMapping("/home/removePerson")
    @ResponseBody
    public String removePerson(int id){
        for(Person p : person){
            if (p.getId() == id) {
                person.remove(p);
                return id + "번 사람이 삭제되었습니다.";
            }
        }

        return id + "번 사람은 존재하지 않습니다.";
    }

    @GetMapping("/home/modifyPerson")
    @ResponseBody
    public String modifyPerson(int id, String name, int age){
        for(Person p : person){
            if (p.getId() == id) {
                p.setName(name);
                p.setAge(age);
                return id + "번 사람이 수정되었습니다.";
            }
        }
        return id + "번 사람은 존재하지 않습니다.";
    }


    @GetMapping("/home/people")
    @ResponseBody
    public List<Person> showPeople(){
        return person;
    }

    @GetMapping("/home/cookie/increase")
    @ResponseBody
    public int showCookieIncrease(HttpServletRequest req, HttpServletResponse resp) throws IOException { // 리턴되는 int 값은 String 화 되어서 고객(브라우저)에게 전달된다.
        int cntCookie = 0;

        if (req.getCookies() != null) {
            cntCookie = Arrays.stream(req.getCookies())
                    .filter(cookie -> cookie.getName().equals("cnt"))
                    .map(Cookie::getValue)
                    .mapToInt(Integer::parseInt)
                    .findFirst()
                    .orElse(0);
        }

        int newCntCookie = ++cntCookie;

        resp.addCookie(new Cookie("cnt", newCntCookie + ""));

        return newCntCookie;
    }

    @GetMapping("member/login")
    @ResponseBody
    public List<Login> loginResult(String username, String password){
        List<Login> lr = new ArrayList<>();
        Login login = new Login("0", "null");
        if (username.equals("user1")){
            if (password.equals("1234")){
                login.setResultCode("S-1");
                login.setMsg("user1 님 환영합니다.");
            }
            else{
                login.setResultCode("F-1");
                login.setMsg("비밀번호가 일치하지 않습니다.");
            }
        }
        else{
            login.setResultCode("F-2");
            login.setMsg(username + "(은)는 존재하지 않는 회원입니다.");
        }
        lr.add(login);
        return lr;
    }


    @GetMapping("/home/returnBoolean")
    @ResponseBody
    public boolean showReturnBoolean() {
        return true;
    }

    @GetMapping("/home/returnDouble")
    @ResponseBody
    public double showReturnDouble() {
        return Math.PI;
    }

    @GetMapping("/home/returnIntArray")
    @ResponseBody
    public int[] showReturnIntArray() {
        int[] arr = new int[]{10, 20, 30};

        return arr;
    }

    @GetMapping("/home/returnIntList")
    @ResponseBody
    public List<Integer> showReturnIntList() {
        List<Integer> list = new ArrayList<>() {{
            add(10);
            add(20);
            add(30);
        }};

        return list;
    }

    @GetMapping("/home/returnMap")
    @ResponseBody
    public Map<String, Object> showReturnMap() {
        Map<String, Object> map = new LinkedHashMap<>() {{
            put("id", 1);
            put("speed", 100);
            put("name", "카니발");
            put("relatedIds", new ArrayList<>() {{
                add(2);
                add(3);
                add(4);
            }});
        }};

        return map;
    }

    @GetMapping("/home/returnCar")
    @ResponseBody
    public Car showReturnCar() {
        Car car = new Car(1, 100, "카니발", new ArrayList<>() {{
            add(2);
            add(3);
            add(4);
        }});

        return car;
    }

    @GetMapping("/home/returnCarV2")
    @ResponseBody
    public CarV2 showReturnCarV2() {
        CarV2 car = new CarV2(1, 100, "카니발", new ArrayList<>() {{
            add(2);
            add(3);
            add(4);
        }});

        car.setName(car.getName() + "V2");

        return car;
    }

    @GetMapping("/home/returnCarMapList")
    @ResponseBody
    public List<Map<String, Object>> showReturnCarMapList() {
        Map<String, Object> carMap1 = new LinkedHashMap<>() {{
            put("id", 1);
            put("speed", 100);
            put("name", "카니발");
            put("relatedIds", new ArrayList<>() {{
                add(2);
                add(3);
                add(4);
            }});
        }};

        Map<String, Object> carMap2 = new LinkedHashMap<>() {{
            put("id", 2);
            put("speed", 200);
            put("name", "포르쉐");
            put("relatedIds", new ArrayList<>() {{
                add(5);
                add(6);
                add(7);
            }});
        }};

        List<Map<String, Object>> list = new ArrayList<>();

        list.add(carMap1);
        list.add(carMap2);

        return list;
    }

    @GetMapping("/home/returnCarList")
    @ResponseBody
    public List<CarV2> showReturnCarList() {
        CarV2 car1 = new CarV2(1, 100, "카니발", new ArrayList<>() {{
            add(2);
            add(3);
            add(4);
        }});

        CarV2 car2 = new CarV2(2, 200, "포르쉐", new ArrayList<>() {{
            add(5);
            add(6);
            add(7);
        }});

        List<CarV2> list = new ArrayList<>();

        list.add(car1);
        list.add(car2);

        return list;
    }
}

class Car {
    private final int id;
    private final int speed;
    private final String name;
    private final List<Integer> relatedIds;

    public Car(int id, int speed, String name, List<Integer> relatedIds) {
        this.id = id;
        this.speed = speed;
        this.name = name;
        this.relatedIds = relatedIds;
    }

    public int getId() {
        return id;
    }

    public int getSpeed() {
        return speed;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getRelatedIds() {
        return relatedIds;
    }
}

@AllArgsConstructor
@Getter
class CarV2 {
    private final int id;
    private final int speed;
    @Setter
    private String name;
    private final List<Integer> relatedIds;
}

@AllArgsConstructor
@Getter
@Setter
class Person{
    private static int lastId = 0;
    private final int id;
    private String name;
    private int age;

    Person(String name, int age){
        this(++lastId, name, age);
    }
}

@AllArgsConstructor
@Getter
@Setter
class Login{
    private String resultCode;
    private String msg;

}