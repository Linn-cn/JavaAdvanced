package edu.changda.serialize;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.*;

/**
 * @author Linn-cn
 * @create 2020/12/18
 */
public class serializeDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // testSerializable("D:\\file01.txt");
        testDeserializable("D:\\file01.txt");
    }

    private static void testSerializable(String fileName) throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            // "XXX" 的String也可以直接作为对象进行反序列化的
            objectOutputStream.writeObject("test serializable");
            SerializableData data = new SerializableData(1, "testStr");
            objectOutputStream.writeObject(data);
        }
    }

    private static void testDeserializable(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            String str = (String) objectInputStream.readObject();
            System.out.println("String的反序列化: " + str);
            SerializableData readData = (SerializableData) objectInputStream.readObject();
            System.out.println("反序列化的对象: " + readData.toString());
            // 输出:反序列化的对象: SerializableData(testInt=1, testStr=testStr)
        }
    }

}

@Data
@AllArgsConstructor
class SerializableData implements Serializable {
    private Integer testInt;

    private String testStr;

}
