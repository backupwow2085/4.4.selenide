import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @Test
    void shouldSubmitCardDeliveryForm() {
        open("http://localhost:9999");

        // город
        $("[data-test-id=city] input").setValue("Москва");

        // дата
        String date = LocalDate.now().plusDays(3)
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        $("[data-test-id=date] input")
                .doubleClick()
                .sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(date);

        // ФИО
        $("[data-test-id=name] input").setValue("Иванов Иван");

        // телефон
        $("[data-test-id=phone] input").setValue("+79000000000");

        // чекбокс
        $("[data-test-id=agreement]").click();

        // отправка
        $$("button")
                .find(Condition.text("Забронировать"))
                .click();

        // проверка уведомления
        $("[data-test-id=notification]")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Успешно"));
    }
}
