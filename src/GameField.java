import javax.swing.*;
import java.awt.*;

public class GameField extends JPanel {
    //Создание игровых компонентов
    private final int SIZE = 320; //Размер игрового поля
    private final int DOT_SIZE = 16; //Размер ячейки
    private final int ALL_DOTS = 400;
    private Image dot;
    private Image apple;
    //Храним в переменных позицию яблок
    private int appleX;
    private int appleY;
    // Два масива для того чтобы хранить положение змейки
    private int[] x = new int[ALL_DOTS]; //ALL_DOTS В теории змейка может занимать все игровое поле
    private int[] y = new int[ALL_DOTS];
    //Размер змейки в данный момент времени:
    private int dots;
    //Стандыртный таймер Swing:
    private Timer timer;
    // Пять булеан полей, которые отвечают за текущее направление змейки:
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    // Значение определяющее в игре мы или нет
    private boolean inGame = true;

    //Создание конструктора
    public GameField(){
        setBackground(Color.black);
        //Вызов метода который водгружает картинки
        loadImages();
    }
    //Метод ддля загрузки картинок
    public void loadImages() {
        //Для того чтобы загрузить картинки с изображением dot и apple:
        ImageIcon iia = new ImageIcon("apple.png");
        // Инициализация изображения
        apple = iia.getImage();

        ImageIcon iid = new ImageIcon("dot.png");
        dot = iid.getImage();

    }


}
