import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dto.Car;
import dto.User;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        System.out.println("main");
        ObjectMapper objectMapper = new ObjectMapper();

        User user = new User();
        user.setName("홍길동");
        user.setAge(10);

        Car c1 = new Car();
        c1.setName("K5");
        c1.setCarNumber("11가 1111");
        c1.setType("sedan");
        Car c2 = new Car();
        c2.setName("Q5");
        c2.setCarNumber("21가 1111");
        c2.setType("SUV");

        List<Car> carList = Arrays.asList(c1, c2);

        user.setCars(carList);

        String json = objectMapper.writeValueAsString(user);
        System.out.println(json);

        JsonNode jsonNode = objectMapper.readTree(json);
        String _name = jsonNode.get("name").asText();
        int _age = jsonNode.get("age").asInt();
        System.out.println("name: "+_name);
        System.out.println("age: "+_age);

        // 해당 노드가 어떤 클래스에 속해있는지 미리 알고 있을 경우 이렇게 파싱할 수 있다.

        JsonNode cars = jsonNode.get("cars");
        ArrayNode arrayNode = (ArrayNode) cars;

        // convertValue(원하는 배열 형태의 노드, 파싱하길 원하는 타입 레퍼런스)
        List<Car> _cars = objectMapper.convertValue(arrayNode, new TypeReference<List<Car>>() {});
        System.out.println(_cars);

        // json 노드의 값을 변경하고자 할 때 (ObjectNode 클래스를 사용)
        ObjectNode objectNode = (ObjectNode) jsonNode;
        objectNode.put("name", "steve");
        objectNode.put("age", 12);
        System.out.println(objectNode.toPrettyString());
    }
}
