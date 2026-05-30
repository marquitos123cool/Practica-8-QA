import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TextBoxTestPOM {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();
            driver.get("https://demoqa.com/text-box");

            TextBoxPage textBoxPage = new TextBoxPage(driver);

            textBoxPage.fillFullName("Juan Pérez López");
            textBoxPage.fillEmail("juan.perez@email.com");
            textBoxPage.fillCurrentAddress("Calle Principal 123, Colonia Centro, Ciudad de México, CP 06000");
            textBoxPage.fillPermanentAddress("Av. Reforma 456, Colonia Juárez, Ciudad de México, CP 06600");
            
            textBoxPage.clickSubmit();

            System.out.println("Resultados desde POM:");
            System.out.println("Nombre: " + textBoxPage.getOutputName());
            System.out.println("Email: " + textBoxPage.getOutputEmail());

        } catch (Exception e) {
            System.err.println("La prueba falló: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.quit();
        }
    }
}
