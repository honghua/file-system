import com.oracle.tools.packager.Log;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileSystemTest {
    static FileSystem fs;
    static Log.Logger log;
    @BeforeAll
    static void setup() {
        log = new Log.Logger(true);
        log.info("@BeforeAll - executes once before all test methods in this class");
        fs = new FileSystem();
    }

//    @BeforeEach
//    void init() {
//        Log.Logger log2 = new Log.Logger(true);
//        log2.info("@BeforeEach - executes before each test method in this class");
//    }


    @org.junit.jupiter.api.Test
    void main() {
        fs.mkdir("/", "users");
        fs.mkdir("/users", "meng");
        fs.mkdir("/users/meng", "work");
        fs.createFile("/users/meng/work", "helloWorld");
        fs.createFile("/users/meng/work", "java");

        fs.mkdir("/users", "honghua");
        fs.mkdir("/users/honghua", "music");
        fs.createFile("/users/honghua", "beethoven");

        fs.mkdir("/users", "yinglong");
        fs.mkdir("/users/yinglong", "work");
        fs.createFile("/users/yinglong/work", "excel");

        List<Entry> list = fs.lsR("/");
        System.out.println(list.size());
        for (Entry e : list) {
//            System.out.print(e.getPath() + ", ");
            log.info(e.getPath());
        }

        fs.delete("/users/yinglong");
        list = fs.lsR("/");
        System.out.println(list.size());
        for (Entry e : list) {
            log.info(e.getPath());
        }

        System.out.println(fs.delete("/"));
        list = fs.lsR("/");
        System.out.println(list.size());
        for (Entry e : list) {
            log.info(e.getPath());
        }
    }

    @org.junit.jupiter.api.Test
    void createFile() {
        String s = "";
        String[] array = s.split("/");
        System.out.println(array.length);
//        [""] -> 1

        s = "/";
        array = s.split("/");
        System.out.println(array.length);
//        [] -> 0

        s = "/user";
        array = s.split("/");
        System.out.println(array.length);
//        ["", "user"].length -> 2

        s = "/user/";
        array = s.split("/");
        System.out.println(array.length);
//        ["", "user"].length -> 2

        for (String e : array) {
            System.out.println(e);
        }

    }

    @org.junit.jupiter.api.Test
    void mkdir() {
    }

    @org.junit.jupiter.api.Test
    void ls() {
    }

    @org.junit.jupiter.api.Test
    void open() {
    }

    @org.junit.jupiter.api.Test
    void mvTo() {
    }

    @org.junit.jupiter.api.Test
    void append() {
    }

    @org.junit.jupiter.api.Test
    void delete() {
    }
}