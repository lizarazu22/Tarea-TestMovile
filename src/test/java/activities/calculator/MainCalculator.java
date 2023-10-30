package activities.calculator;

import control.Button;
import control.Label;
import org.openqa.selenium.By;

public class MainCalculator {
    public Button fiveButton = new Button(By.xpath("//android.widget.Button[@text='5']"));
    public Button sevenButton = new Button(By.xpath("//android.widget.Button[@text='7']"));
    public Button addButton = new Button(By.xpath("//android.widget.Button[@text='+']"));
    public Button equalButton = new Button(By.xpath("//android.widget.Button[@content-desc=\"equals\"]"));

    public Label resultLabel = new Label(By.xpath("//android.widget.TextView[@resource-id=\"com.android.calculator2:id/result\"]"));

}
