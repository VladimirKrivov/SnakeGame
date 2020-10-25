import javax.swing.*;
//Здесь будут описаны основные настройки окна
//Так же будет добавленно игровое поле (GameField)

public class MainWindow extends JFrame{
    //Создание конструктора
    public MainWindow() {
        setTitle("Змейка");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //Размер окна:
        setSize(320, 345);
        setLocation(400, 400);
        // Добавление экземпляра класса на окно:
        add(new GameField());
        setVisible(true);
    }

    public static void main(String[] args) {
        //Создание экземпляра. Место начала работы программы
        MainWindow mw = new MainWindow();
    }
}
