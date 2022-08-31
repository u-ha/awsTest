package uha.awsTest.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloReponseDtoTest {

    @Test
    public void LombokTest(){
        HelloReponseDto helloReponseDto=new HelloReponseDto("A",1);
        Assertions.assertEquals(1,helloReponseDto.getAmount());
        Assertions.assertEquals("A",helloReponseDto.getName());
    }
}