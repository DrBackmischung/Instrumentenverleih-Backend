package de.wi2020sebgroup1.cinema;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import de.wi2020sebgroup1.instrumentenverleih.InstrumentenverleihApplication;

@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
class CinemaApplicationTests {
   @Test
   public void main() {
      InstrumentenverleihApplication.main(new String[] {});
   }

}
