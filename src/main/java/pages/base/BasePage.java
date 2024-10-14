package pages.base;

import org.openqa.selenium.WebElement;

public class BasePage {

    public void sendKeysSlowly(WebElement element, String text, int delayInMillis) {
        for (char ch : text.toCharArray()) {
            element.sendKeys(String.valueOf(ch));  // Sends one character at a time
            try {
                Thread.sleep(delayInMillis);  // Pause between each character
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
