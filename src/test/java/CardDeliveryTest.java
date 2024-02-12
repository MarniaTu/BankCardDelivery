import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.google.common.io.RecursiveDeleteOption;
import org.checkerframework.checker.index.qual.LessThan;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.conditions.datetime.DateConditions.date;

public class CardDeliveryTest {

    @Test
    public void shouldCheckApplicationSendingSuccess() {

        open("http://localhost:9999/");
        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue("Якутск");
        form.$("[data-test-id='date'] input").doubleClick().sendKeys("\b");
        String meetingDate = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] .input__control").sendKeys(meetingDate);
        form.$("[data-test-id='name'] .input__control").sendKeys("Светлова Оксана");
        form.$("[data-test-id='phone'] .input__control").sendKeys("+79051236789");
        form.$("[data-test-id='agreement'] .checkbox__box").click();
        form.$$("button").last().click();
        $(withText("Встреча успешно забронирована на")).shouldBe(visible, Duration.ofSeconds(15));

    }
}


